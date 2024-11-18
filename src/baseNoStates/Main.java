package baseNoStates;

// Before executing enable assertions :
// https://se-education.org/guides/tutorials/intellijUsefulSettings.html

public class Main {
  public static void main(String[] args) {
    //Cridar el mètode per crear les Àrees i les Doors alhora
    DirectoryAreas.makeAreas();
    DirectoryUserGroups.makeUserGroups();
    WebServer webServer = WebServer.getUniqueInstance();
  }
}