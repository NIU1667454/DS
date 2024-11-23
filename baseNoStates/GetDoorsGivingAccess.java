package baseNoStates;

import java.util.ArrayList;

// Class that extends visitor. It finds the doors that the current Area has access to
public class GetDoorsGivingAccess implements Visitor {
    private final ArrayList<Door> collectedDoors = new ArrayList<>();

    // Checks every area to obtain all of its doors
    public void visitPartition(Area partition) {
        for (Area area : partition.getSpaces()) {
            area.acceptVisitor(this);
        }
    }

    public void visitSpace(Area space) {
        collectedDoors.addAll(space.getDoorsGivingAccess());
    }

    public ArrayList<Door> getCollectedDoors() {
        return collectedDoors;
    }
}