package baseNoStates;

/*
Classe abstracta que gestiona els canvis estats d'una porta.
Hereden 5 estats d'aquesta classe , i totes les classes juntes formen el patró State.
L'estat el gestiona l'usuari, segons el seu nivell d'accés als diferents espais de l'edifici.
Cada cop que es vol canviar d'estat una porta, creem una nova instància
d'una subclasse de DoorState (new State())
*/
public abstract class DoorState {
  //Porta a la que està associada l'estat que volem gestionar
  protected Door door;
  protected String name;

  public DoorState(Door door) {
    this.door = door;
  }

  public String getName() {
    return this.name;
  }

  public abstract void open();

  public abstract void close();

  public abstract void lock();

  public abstract void unlock();

  public abstract void unlockShortly();
}