package baseNoStates;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/*
Classe Space representa els llocs de l'edifici amb portes.
Aquesta classe deriva de la classe Àrea i també forma part del patró Composite.
 */
public class Space extends Area {

  // Llista de portes que formen part de l'espai actual,
  // que donen accés a un altre espai des d'aquest
  private final ArrayList<Door> doors = new ArrayList<>();

  public Space(String id, String desc, Partition par) {
    super(id, desc, par);
  }

  @Override
  public Area findAreaById(String id) {
    if (this.areaId.equals(id)) {
      return this;
    } else {
      return null;
    }
  }

  @Override
  public ArrayList<Door> getDoorsGivingAccess() {
    return doors;
  }

  //Es retorna a sí mateix (this) en format de llista, ja que no conté altres espais associats
  @Override
  public ArrayList<Area> getSpaces() {
    return new ArrayList<>(List.of(this));
  }

  @Override
  public String getId() {
    return areaId;
  }

  public void addDoor(Door d) {
    doors.add(d);
  }
}
