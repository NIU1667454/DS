package baseNoStates;

import java.util.ArrayList;

/*
Class Partition represents the different places in the building that contain
subareas. This class extends class Area and is part of Composite pattern.
 */
public class Partition extends Area {

  //list of subareas that form the partition
  final ArrayList<Area> areas = new ArrayList<>();

  public Partition(String id, String desc, Partition par) {
    super(id, desc, par);
  }

  @Override
  public ArrayList<Door> getDoorsGivingAccess() {
    return null;
  }


  @Override
  public void acceptVisitor(Visitor visitor) {
    visitor.visitPartition(this);
  }

  @Override
  public ArrayList<Area> getSpaces() {
    return areas;
  }

  @Override
  public String getId() {
    return areaId;
  }

  // Method that lets create children of this branch
  public void addSpace(Area a) {
    areas.add(a);
  }
}
