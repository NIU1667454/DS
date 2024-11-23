package baseNoStates;

/*
Abstract class that manages the state changes of a door.
5 classes extend from this one, all of which form State pattern.
The state is managed by the user, taking into account his access level to the different
spaces. Every time we want to change the state, we create a new instance
of a subclass (new State()).
*/
public abstract class DoorState {
  //Door to which the state is associated
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