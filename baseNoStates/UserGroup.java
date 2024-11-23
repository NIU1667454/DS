package baseNoStates;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

/*
Class UserGroup is used to manage groups of users that enter the building.
They have an associated name, a schedule and the actions they can do in the
different areas to which they can get into.
 */
public class UserGroup {
  private final String groupName;
  private final Timetable timetables;
  private final ArrayList<String> actions;
  private final ArrayList<Area> areas;
  private final ArrayList<User> users;

  public UserGroup(String nameGroup, Timetable schedules, ArrayList<String> actions,
                   ArrayList<Area> areas, ArrayList<User> usuarios) {
    this.groupName = nameGroup;
    this.timetables = schedules;
    this.actions = actions;
    this.areas = areas;
    this.users = usuarios;
  }

  public ArrayList<String> getActions() {
    return actions;
  }

  public ArrayList<Area> getAreas() {
    return areas;
  }

  public ArrayList<User> getUsers() {
    return users;
  }

  public String getGroupName() {
    return groupName;
  }

  public Timetable getSchedule() {
    return timetables;
  }

  public User findUserByCredential(String credential) {
    for (User user : users) {
      if (user.getCredential().equals(credential)) {
        return user;
      }
    }
    return null;
  }
}