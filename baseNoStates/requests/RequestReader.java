package baseNoStates.requests;

import baseNoStates.*;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.chrono.ChronoLocalDateTime;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

public class RequestReader implements Request {
  private final String credential; // who
  private final String action;     // what
  private final LocalDateTime now; // when
  private final String doorId;     // where
  private String userName;
  private boolean authorized;
  private final ArrayList<String> reasons; // why not authorized
  private String doorStateName;
  private boolean doorClosed;

  public RequestReader(String credential, String action, LocalDateTime now, String doorId) {
    this.credential = credential;
    this.action = action;
    this.doorId = doorId;
    reasons = new ArrayList<>();
    this.now = now;
  }

  public void setDoorStateName(String name) {
    doorStateName = name;
  }

  public String getAction() {
    return action;
  }

  public boolean isAuthorized() {
    return authorized;
  }

  public void addReason(String reason) {
    reasons.add(reason);
  }


  @Override
  public String toString() {
    if (userName == null) {
      userName = "unknown";
    }
    return "Request{"
            + "credential=" + credential
            + ", userName=" + userName
            + ", action=" + action
            + ", now=" + now
            + ", doorID=" + doorId
            + ", closed=" + doorClosed
            + ", authorized=" + authorized
            + ", reasons=" + reasons
            + "}";
  }

  public JSONObject answerToJson() {
    JSONObject json = new JSONObject();
    json.put("authorized", authorized);
    json.put("action", action);
    json.put("doorId", doorId);
    json.put("closed", doorClosed);
    json.put("state", doorStateName);
    json.put("reasons", new JSONArray(reasons));
    return json;
  }

  // see if the request is authorized and put this into the request, then send it to the door.
  // if authorized, perform the action.
  public void process() {
    DirectoryUserGroups directoryUserGroups = DirectoryUserGroups.getInstance();
    DirectoryAreas directoryAreas = DirectoryAreas.getUniqueInstance();
    UserGroup user = DirectoryUserGroups.findUserByCredential(credential);
    Door door = directoryAreas.findDoorById(doorId);
    assert door != null : "door " + doorId + " not found";
    authorize(user, door);
    // this sets the boolean authorize attribute of the request
    door.processRequest(this);
    // even if not authorized we process the request, so that if desired we could log all
    // the requests made to the server as part of processing the request
    doorClosed = door.isClosed();
  }

  // the result is put into the request object plus, if not authorized, why not,
  // only for testing
  private void authorize(UserGroup user, Door door) {
    authorized = false;
    if (user == null) { // si l'usuari és null segurament és un intrús
      addReason("user doesn't exists");
    } else {
      if (user.getGroupName().equals("")) {
        // usuaris que no volem esborrar però que no poden fer cap acció
        addReason("this user cannot do anything");
      } else {
        if (user.getActions().contains(action)) { //comprovem si poden fer l'acció demanada
          if (checkDate(user) && checkTime(user) && checkArea(user, door)) {
            // comprovem que la data, l'hora i l'àrea siguin correctes
            authorized = true;
          }
        } else {
          addReason("this user can't do this action");
        }
      }
    }
  }

  // Checks if the user can access at current time
  private boolean checkTime(UserGroup user) {
    Timetable table = user.getSchedule();
    // Hour at which it is tried to open the door
    LocalTime time = LocalTime.of(now.getHour(), now.getMinute(), now.getSecond());

    // It is checked if the time is between the allowed hours
    if (!time.isBefore(table.getStartTime()) && !time.isAfter(table.getEndTime())) {
      return true;
    }

    addReason("this user cannot access on these hours");
    return false;
  }

  // Checks if the user can get into the building on that day of the week and date
  private boolean checkDate(UserGroup user) {
    Timetable table = user.getSchedule();
    LocalDate today = LocalDate.of(now.getYear(), now.getMonthValue(), now.getDayOfMonth());
    // Firstly we check if the date is an allowed value
    if (!today.isBefore(table.getStartDate()) && !today.isAfter(table.getEndDate())) {
      ArrayList<DayOfWeek> days = table.getDays(); // dies de la setmana que pot accedir

      // If the current day is in the list of days to access, the operation is authorized
      if (days.contains(today.getDayOfWeek())) {
        return true;
      }

      addReason("this user cannot access this day of the week");
      return false;
    }

    addReason("this user cannot access on these dates");
    return false;
  }

  // Checks if the user can get into an area
  private boolean checkArea(UserGroup user, Door door) {
    Area a = door.getToSpace(); // obtenim l'àrea on s'intenta obrir la porta

    // We check every parent node to find one which until it is in an
    // allowed area or the parent is null
    while (a.getParent() != null) {
      if (user.getAreas().contains(a)) {
        return true;
      }
      a = a.getParent();
    }

    // In case of being able to manage every area it will only contain building (root)
    if (user.getAreas().contains(a)) {
      return true;
    }

    addReason("this user cannot access this area");
    return false;
  }
}