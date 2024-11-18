package baseNoStates;

import java.util.ArrayList;
import java.util.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*
Classe que gestiona l'estructura d'àrees i portes del sistema a tot l'edifici.
Crea les diferents àrees i portes a les quals els usuaris poden accedir i interactuar amb les
portes, amb els diferents requests que envien.
Conté l'àrea arrel "building" a partir de la qual pot accedir a diferents àrees de l'edifici.
 */
public class DirectoryAreas {
  private static Area rootarea;
  private static ArrayList<Door> allDoors;
  private static DirectoryAreas uniqueInstance = null;
  static Logger logger = LoggerFactory.getLogger("baseNoStates.DirectoryAreas");

  public static void makeAreas() {
    //Àrea arrel de l'edifici
    Partition building = new Partition("building", "entire bulding", null);

    //1r nivell d'àrees per sota de building
    Partition basement = new Partition("basement", "low floor", building);
    building.addSpace(basement);
    Partition groundFloor = new Partition("ground_floor", "ground floor", building);
    building.addSpace(groundFloor);
    Partition floor1 = new Partition("floor1", "first floor", building);
    building.addSpace(floor1);
    Space stairs = new Space("stairs", "stairs between floors", building);
    building.addSpace(stairs);
    Space exterior = new Space("exterior", "exterior of building", building);
    building.addSpace(exterior);

    //2n nivell d'àrees de l'edifici2nd level, basement
    Space parking = new Space("parking", "parking for employees", basement);
    basement.addSpace(parking);

    Space hall = new Space("hall", "entrance hall", groundFloor);
    groundFloor.addSpace(hall);
    Space room1 = new Space("room1", "first room", groundFloor);
    groundFloor.addSpace(room1);
    Space room2 = new Space("room2", "second room", groundFloor);
    groundFloor.addSpace(room2);

    Space room3 = new Space("room3", "third room", floor1);
    floor1.addSpace(room3);
    Space corridor = new Space("corridor", "corridor", floor1);
    floor1.addSpace(corridor);
    Space IT = new Space("IT", "IT room", floor1);
    floor1.addSpace(IT);

    //Creació de totes les portes associades als diferents espais
    Door d1 = new Door("D1", exterior, parking); // exterior, parking
    Door d2 = new Door("D2", stairs, parking); // stairs, parking

    // ground floor
    Door d3 = new Door("D3", exterior, hall); // exterior, hall
    Door d4 = new Door("D4", stairs, hall); // stairs, hall
    Door d5 = new Door("D5", hall, room1); // hall, room1
    Door d6 = new Door("D6", hall, room2); // hall, room2

    // first floor
    Door d7 = new Door("D7", stairs, corridor); // stairs, corridor
    Door d8 = new Door("D8", corridor, room3); // corridor, room3
    Door d9 = new Door("D9", corridor, IT); // corridor, IT

    rootarea = building;
    allDoors = new ArrayList<>(Arrays.asList(d1, d2, d3, d4, d5, d6, d7, d8, d9));

    for (Door d : allDoors) {
      d.getToSpace().addDoor(d); //Afegim a cada espai les portes que li corresponen
    }
  }

  public Door findDoorById(String id) {
    for (Door door : allDoors) {
      if (door.getId().equals(id)) {
        return door;
      }
    }

    logger.warn("door with id " + id + " not found");
    return null;
  }

  // Busca les diferents àrees per la seva id, començant pel principi de l'arbre (root)
  public static Area findAreaById(String id) {
    return rootarea.findAreaById(id);
  }

  public static ArrayList<Door> getAllDoors() {
    logger.info(String.valueOf(allDoors));
    return allDoors;
  }

  public static DirectoryAreas getUniqueInstance() {
    if (uniqueInstance == null) {
      uniqueInstance = new DirectoryAreas();
    }

    return uniqueInstance;
  }
}
