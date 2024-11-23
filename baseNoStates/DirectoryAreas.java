package baseNoStates;

import java.util.ArrayList;
import java.util.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*
Class that manages the areas and doors structure system of the whole building.
It creates the different areas and doors so the user can access and interact
with doors thanks to the different requests sent.
Contains the root area "building", from which it can be accessed the different building areas.
 */
public class DirectoryAreas {
  private static Area rootarea;
  private static ArrayList<Door> allDoors;
  private static DirectoryAreas uniqueInstance = null;
  static Logger logger = LoggerFactory.getLogger("baseNoStates.DirectoryAreas");

  public static void acceptVisitor(Visitor visitor) {
    visitor.visitPartition(rootarea);
  }

  public static void makeAreas() {
    //Root area of the building
    Partition building = new Partition("building", "entire bulding", null);

    //1st level of areas under building in the tree
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

    //2nd level of areas in the building in the tree
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

    //Creation of every door associated to the different spaces
    Door d1 = new Door("D1", exterior, parking);
    Door d2 = new Door("D2", stairs, parking);

    // Ground floor
    Door d3 = new Door("D3", exterior, hall);
    Door d4 = new Door("D4", stairs, hall);
    Door d5 = new Door("D5", hall, room1);
    Door d6 = new Door("D6", hall, room2);

    // First floor
    Door d7 = new Door("D7", stairs, corridor);
    Door d8 = new Door("D8", corridor, room3);
    Door d9 = new Door("D9", corridor, IT);

    rootarea = building;
    allDoors = new ArrayList<>(Arrays.asList(d1, d2, d3, d4, d5, d6, d7, d8, d9));

    for (Door d : allDoors) {
      d.getToSpace().addDoor(d); //We add to each space the doors it is responsible for
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
