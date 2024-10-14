package baseNoStates;

public abstract class DoorState {
  protected Door door;
  protected String name;

  public DoorState(Door door) { this.door = door; }

  public String getName() { return this.name; }

  public abstract void open();
  public abstract void close();
  public abstract void lock();
  public abstract void unlock();
  public abstract void unlock_shortly();
}
