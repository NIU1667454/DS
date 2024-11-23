package baseNoStates;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*
Class that manages the existing user groups in the building, it initializes them
and grants them the permissions and established timetables.
To assign the schedules, it is used the class Timetable.
*/
public class DirectoryUserGroups {
  private static final ArrayList<UserGroup> userGroups = new ArrayList<>();
  private static DirectoryUserGroups uniqueInstance = null;
  static Logger logger = LoggerFactory.getLogger("baseNoStates.DirectoryUserGroups");


  public static DirectoryUserGroups getInstance() {
    if (uniqueInstance == null) {
      uniqueInstance = new DirectoryUserGroups();
    }

    return uniqueInstance;
  }

  public static void makeUserGroups() {
    //Every action that can be done to the bulding's doors
    ArrayList<String> allActions = new ArrayList<>();
    allActions.add(Actions.OPEN);
    allActions.add(Actions.CLOSE);
    allActions.add(Actions.LOCK);
    allActions.add(Actions.UNLOCK);
    allActions.add(Actions.UNLOCK_SHORTLY);

    FindAreaById findAreaById = new FindAreaById("building");
    DirectoryAreas.acceptVisitor(findAreaById);

    //Every existing area
    ArrayList<Area> allSpaces = new ArrayList<>();
    allSpaces.add(findAreaById.getArea());

    //Creation of user without privileges, they cannot access the building
    ArrayList<User> users = new ArrayList<>();

    users.add(new User("Bernat", "12345"));
    users.add(new User("Blai", "77532"));

    Timetable timetable = new Timetable(null, null, null, null, null);
    userGroups.add(new UserGroup("", timetable, null, null,  users));

    //Creation of the employees with their respective schedule
    ArrayList<User> employeesUsers = new ArrayList<>();

    employeesUsers.add(new User("Ernest", "74984"));
    employeesUsers.add(new User("Eulalia", "43295"));

    //Every day they have to access the building
    ArrayList<DayOfWeek> employeesDays = new ArrayList<>();
    employeesDays.add(DayOfWeek.MONDAY);
    employeesDays.add(DayOfWeek.TUESDAY);
    employeesDays.add(DayOfWeek.WEDNESDAY);
    employeesDays.add(DayOfWeek.THURSDAY);
    employeesDays.add(DayOfWeek.FRIDAY);

    //Available actions for employees
    ArrayList<String> employeesActions = new ArrayList<>();
    employeesActions.add(Actions.OPEN);
    employeesActions.add(Actions.CLOSE);
    employeesActions.add(Actions.UNLOCK_SHORTLY);


    //Areas they have access to
    ArrayList<Area> employeeAreas = new ArrayList<>();
    findAreaById = new FindAreaById("ground_floor");
    DirectoryAreas.acceptVisitor(findAreaById);
    employeeAreas.add(findAreaById.getArea());
    findAreaById = new FindAreaById("floor1");
    DirectoryAreas.acceptVisitor(findAreaById);
    employeeAreas.add(findAreaById.getArea());
    findAreaById = new FindAreaById("exterior");
    DirectoryAreas.acceptVisitor(findAreaById);
    employeeAreas.add(findAreaById.getArea());

    //Schedules that belong to user: from 1st September to March 2025
    LocalDate employeesInitialDate = LocalDate.of(2024, 9, 1);
    LocalDate employeesFinalDate = LocalDate.of(2025, 3, 1);

    LocalTime employeesInitialHour = LocalTime.of(9, 0); // 9-17h
    LocalTime employeesFinalHour = LocalTime.of(17, 0);

    Timetable employeeTimetable = new Timetable(employeesInitialDate, employeesFinalDate,
      employeesDays, employeesInitialHour, employeesFinalHour);

    userGroups.add(new UserGroup("Employees", employeeTimetable, employeesActions,
      employeeAreas, employeesUsers));

    //Creation of user "manager"
    ArrayList<User>  managerUsers = new ArrayList<>();
    managerUsers.add(new User("Manel", "95783"));
    managerUsers.add(new User("Marta", "05827"));

    LocalDate managerInicialDate = LocalDate.of(2024, 9, 1); // Sep. 1 this year to Mar. 1 next year
    LocalDate managerFinalDate = LocalDate.of(2025, 3, 1);

    LocalTime managerInitialHour = LocalTime.of(8, 0); //  8-20h
    LocalTime managerFinalHour = LocalTime.of(20, 0);

    //Same as employees, but with Saturday access
    ArrayList<DayOfWeek> managersDays = new ArrayList<>();
    managersDays.add(DayOfWeek.MONDAY);
    managersDays.add(DayOfWeek.TUESDAY);
    managersDays.add(DayOfWeek.WEDNESDAY);
    managersDays.add(DayOfWeek.THURSDAY);
    managersDays.add(DayOfWeek.FRIDAY);
    managersDays.add(DayOfWeek.SATURDAY);

    Timetable managerTimetable = new Timetable(managerInicialDate, managerFinalDate, managersDays,
      managerInitialHour, managerFinalHour);

    userGroups.add(new UserGroup("Managers", managerTimetable, allActions, allSpaces,
      managerUsers));

    //Creation of user "admin"
    ArrayList<User> userAdmin = new ArrayList<>();
    userAdmin.add(new User("Ana", "11343"));

    //They can access the building whenever they want
    LocalDate adminInicialDate = LocalDate.of(2024, 1, 1); // always=Jan. 1 this year to 2100
    LocalDate adminFinalDate = LocalDate.of(2100, 1, 1);
    LocalTime adminInicialHour = LocalTime.of(0, 0);
    LocalTime adminFinalHour = LocalTime.of(23, 59);

    //Same as managers, but with Sunday access
    ArrayList<DayOfWeek> adminDays = new ArrayList<>(); // all days of the week
    adminDays.add(DayOfWeek.MONDAY);
    adminDays.add(DayOfWeek.TUESDAY);
    adminDays.add(DayOfWeek.WEDNESDAY);
    adminDays.add(DayOfWeek.THURSDAY);
    adminDays.add(DayOfWeek.FRIDAY);
    adminDays.add(DayOfWeek.SATURDAY);
    adminDays.add(DayOfWeek.SUNDAY);

    Timetable scheduleAdmin = new Timetable(adminInicialDate, adminFinalDate, adminDays,
      adminInicialHour, adminFinalHour);

    userGroups.add(new UserGroup("Admin", scheduleAdmin, allActions, allSpaces, userAdmin));
  }

  //It is tried to find the user id, but it returns the group it belongs to
  public static UserGroup findUserByCredential(String credential) {
    for (UserGroup group : userGroups) {
      User user = group.findUserByCredential(credential);
      if (user != null) {
        return group;
      }
    }

    logger.warn("user with credential " + credential + " not found");
    return null;
  }
}


