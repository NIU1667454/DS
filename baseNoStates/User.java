package baseNoStates;

import java.util.ArrayList;


/*
Class that manages the users in the system, with its names and ids, as well as
the areas they can access.
*/
public class User {
  private final String name;
  private final String credential;
  private ArrayList<Area> areas;

  public User(String name, String credential) {
    this.name = name;
    this.credential = credential;
  }

  public String getCredential() {
    return credential;
  }

  @Override
  public String toString() {
    return "User{name=" + name + ", credential=" + credential + "}";
  }

}
