package baseNoStates;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*
Classe que gestiona els grups d'usuaris existents a l'edifici,
els inicialitza i els concedeix els permisos i estableix els seus horaris.
Per assignar els horaris, s'utilitza la classe Timetable.
*/
public class DirectoryUserGroups {
  private static final ArrayList<UserGroup> userGroups = new ArrayList<>();
  private static DirectoryUserGroups uniqueInstance = null;
  private static Logger logger = LoggerFactory.getLogger("baseNoStates.DirectoryUserGroups");


  public static DirectoryUserGroups getInstance() {
    if (uniqueInstance == null) {
      uniqueInstance = new DirectoryUserGroups();
    }

    return uniqueInstance;
  }

  public static void makeUserGroups() {
    //Totes les accions que es poden fer amb les portes de l'edifici
    ArrayList<String> allActions = new ArrayList<>();
    allActions.add(Actions.OPEN);
    allActions.add(Actions.CLOSE);
    allActions.add(Actions.LOCK);
    allActions.add(Actions.UNLOCK);
    allActions.add(Actions.UNLOCK_SHORTLY);

    //Totes les àrees existents
    ArrayList<Area> allSpaces = new ArrayList<>();
    allSpaces.add(DirectoryAreas.findAreaById("building"));

    //Creació d'usuaris sense privilegis, que no podran accedir mai
    ArrayList<User> users = new ArrayList<>();

    users.add(new User("Bernat", "12345"));
    users.add(new User("Blai", "77532"));

    Timetable timetable = new Timetable(null, null, null, null, null);
    userGroups.add(new UserGroup("", timetable, null, null,  users));

    //Creació d'empleats de l'edifici, amb els seus respectius horaris
    ArrayList<User> employeesUsers = new ArrayList<>();

    employeesUsers.add(new User("Ernest", "74984"));
    employeesUsers.add(new User("Eulalia", "43295"));

    //Tots els dies els quals els usuaris tindran accés a l'edifici
    ArrayList<DayOfWeek> employeesDays = new ArrayList<>();
    employeesDays.add(DayOfWeek.MONDAY);
    employeesDays.add(DayOfWeek.TUESDAY);
    employeesDays.add(DayOfWeek.WEDNESDAY);
    employeesDays.add(DayOfWeek.THURSDAY);
    employeesDays.add(DayOfWeek.FRIDAY);

    //Creació de les accions que tenen disponibles
    ArrayList<String> employeesActions = new ArrayList<>();
    employeesActions.add(Actions.OPEN);
    employeesActions.add(Actions.CLOSE);
    employeesActions.add(Actions.UNLOCK_SHORTLY);

    //Àrees a les quals té accés un usuari empleat
    ArrayList<Area> employeeAreas = new ArrayList<>();
    employeeAreas.add(DirectoryAreas.findAreaById("ground_floor"));
    employeeAreas.add(DirectoryAreas.findAreaById("floor1"));
    employeeAreas.add(DirectoryAreas.findAreaById("exterior"));

    //Data que va de l'1 de setembre fins al març de l'any vinent
    LocalDate employeesInitialDate = LocalDate.of(2024, 9, 1);
    LocalDate employeesFinalDate = LocalDate.of(2025, 3, 1);

    LocalTime employeesInitialHour = LocalTime.of(9, 0); // 9-17h
    LocalTime employeesFinalHour = LocalTime.of(17, 0);

    Timetable employeeTimetable = new Timetable(employeesInitialDate, employeesFinalDate,
        employeesDays, employeesInitialHour, employeesFinalHour);

    userGroups.add(new UserGroup("Employees", employeeTimetable, employeesActions,
        employeeAreas, employeesUsers));

    //Creació dels usuaris manager de l'edifici
    ArrayList<User>  managerUsers = new ArrayList<>();
    managerUsers.add(new User("Manel", "95783"));
    managerUsers.add(new User("Marta", "05827"));

    LocalDate managerInicialDate = LocalDate.of(2024, 9, 1); // Sep. 1 this year to Mar. 1 next year
    LocalDate managerFinalDate = LocalDate.of(2025, 3, 1);

    LocalTime managerInitialHour = LocalTime.of(8, 0); //  8-20h
    LocalTime managerFinalHour = LocalTime.of(20, 0);

    //Igual que amb els empleats, però afegint el dissabte com a dia d'accés dels managers
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

    //Creació dels usuaris administradors
    ArrayList<User> userAdmin = new ArrayList<>();
    userAdmin.add(new User("Ana", "11343"));

    //Tenen accés sempre que vulguin a l'edifici, dona igual dia o hora
    LocalDate adminInicialDate = LocalDate.of(2024, 1, 1); // always=Jan. 1 this year to 2100
    LocalDate adminFinalDate = LocalDate.of(2100, 1, 1);
    LocalTime adminInicialHour = LocalTime.of(0, 0);
    LocalTime adminFinalHour = LocalTime.of(23, 59);

    //Igual que amb els managers, però afegint el diumenge
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

  //Es busca l'usuari per la seva identificació, però es retorna el grup al qual pertany
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


