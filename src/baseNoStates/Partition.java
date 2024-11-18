package baseNoStates;

import java.util.ArrayList;

/*
Classe Partition representa els llocs de l'edifici que contenen altres subàreas.
Aquesta classe deriva de la classe Àrea i també forma part del patró Composite.
 */
public class Partition extends Area {

  //Llista de subàreas que formen aquesta partició segons la jerarquia de l'edifici
  final ArrayList<Area> areas = new ArrayList<>();

  public Partition(String id, String desc, Partition par) {
    super(id, desc, par);
  }

  /*
  Retorna les portes de tots els seus subespais, recorrent cada subnivell de la partició
  ja que la partició com a tal no conté cap porta
   */
  @Override
  public ArrayList<Door> getDoorsGivingAccess() {
    ArrayList<Door> doors = new ArrayList<>();
    // recorrem els nivells inferiors, volem totes les portes
    for (Area a : areas) {
      for (Door d : a.getDoorsGivingAccess()) {
        if (!doors.contains(d)) {
          doors.add(d);
        }
      }
    }

    return doors;
  }

  // Recorre totes les subàreas que conté la Partició per trobar la que conté aquest id,
  // si no és l'id de la Partició
  // Mirem els id's dels fills
  @Override
  public Area findAreaById(String id) {
    if (this.areaId.equals(id)) {
      return this;
    } else {
      for (Area a : areas) {
        Area currentArea = a.findAreaById(id);

        if (currentArea != null) {
          return currentArea;
        }
      }
    }

    return null;
  }

  @Override
  public ArrayList<Area> getSpaces() {
    return areas;
  }

  @Override
  public String getId() {
    return areaId;
  }

  //Mètode que permet crear els fills d'aquesta branca
  public void addSpace(Area a) {
    areas.add(a);
  }
}
