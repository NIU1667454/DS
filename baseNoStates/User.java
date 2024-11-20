package baseNoStates;

import java.util.ArrayList;


/*
Classe per gestionar els usuaris del sistema, amb el seu nom i el seu identificador, així
com les àrees a les quals té accés.
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
