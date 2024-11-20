package baseNoStates;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

/*
Classe que serveix per gestionar grups d'usuaris que existeixen a l'edifici.
Aquests tenen associat un nom concret, un horari concret i accions per a realitzar amb les diferents
àrees a que tinguin permís de l'edifici.
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

  public void addAction(String action) {
    actions.add(action);
  }

  public void addArea(Area area) {
    areas.add(area);
  }

  public void addUser(User user) {
    users.add(user);
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