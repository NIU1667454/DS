package baseNoStates;

// Class that extends visitor. It finds an area by its id assigned at the beginning
public class FindAreaById implements Visitor{
    private final String id;
    private Area area = null;

    public FindAreaById(String id) {
        this.id = id;
    }

    // it visits every subarea that contains the Partition to find the one with
    // its id, if it's not the Partition's id
    @Override
    public void visitPartition(Area partition) {
        if (partition.getId().equals(id))
            area = partition;
        else {
            for (Area area1 : partition.getSpaces()) {
                area1.acceptVisitor(this);
                if (area != null) {
                    area.acceptVisitor(this);
                }
            }
        }
    }

    // We check the children ids
    @Override
    public void visitSpace(Area space) {
        if (space.getId().equals(id)) {
            area = space;
        }
    }

    public Area getArea() {
        return area;
    }
}

