package baseNoStates;

import java.util.ArrayList;

/*
Classe abstracta que representa una àrea general en el sistema,
estesa per diferents tipus de llocs de l'edifici: Partitions & Spaces.
Classe necessària per gestionar l'accés d'usuaris a les diferents parts de l'edifici.
Forma part del patró Composite: una Partition pot tenir altres Partitions o Spaces fills.
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

  //Retorna la partició pare dins l'estructura jeràrquica de l'edifici a la qual pertany l'àrea
  public Partition getParent() {
    return parent;
  }

  public abstract ArrayList<Door> getDoorsGivingAccess();

  //Retorna una àrea segons un identificador únic que se li ha assignat al principi
  public abstract Area findAreaById(String id);

  public abstract ArrayList<Area> getSpaces();

  public abstract String getId();
}
