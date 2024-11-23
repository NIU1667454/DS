package baseNoStates;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/*
Class Space represents places with doors. It extends class Area and
also forms Composite pattern.
 */
public class Space extends Area {

  // List of doors of current Space, they give access to another Space
  private final ArrayList<Door> doors = new ArrayList<>();

  public Space(String id, String desc, Partition par) {
    super(id, desc, par);
  }


  @Override
  public void acceptVisitor(Visitor visitor) {
    visitor.visitSpace(this);
  }

  @Override
  public ArrayList<Door> getDoorsGivingAccess() {
    return doors;
  }

  // It returns itself as a List, as it doesn't contain other spaces
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
