package baseNoStates;

import java.util.ArrayList;

/*
Abstract class which represents a general area in the system,
extended by different places in the building: Partitions & Spaces.
Class needed to manage the user accesses to the different parts of the building.
It is part of Composite pattern: a Partition can have other partitions or Spaces as children.
 */

public abstract class Area {
  String areaId; // nom
  String areaDescription;  // descripció
  Partition parent; // node de l'arbre pare

  public Area(String id, String desc, Partition par) {
    areaId = id;
    areaDescription = desc;
    parent = par;
  }

  //Returns the parent partition in the hierarchical structure of the building it belongs the area
  public Partition getParent() {
    return parent;
  }

  public abstract void acceptVisitor(Visitor visitor);

  public abstract ArrayList<Door> getDoorsGivingAccess();

  public abstract ArrayList<Area> getSpaces();

  public abstract String getId();
}
