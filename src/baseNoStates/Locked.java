package baseNoStates;

public class Locked extends DoorState
{
  public Locked(Door door)
  {
    super(door);
    this.name = States.LOCKED;
  }

  public void open() {
    System.out.println("Cannot open the door, it's locked.");
  }

  public void close() {
      System.out.println("Door is already closed.");
  }

  public void lock() {
    System.out.println("Door is already locked.");
  }

  public void unlock() {
    door.setState(new Unlocked(door)); // Cambiamos el estado a Unlocked
    System.out.println("Door is now unlocked.");
  }

  @Override
  public void unlock_shortly() {
    door.setState(new UnlockedShortly(door)); // Cambiamos el estado a Unlocked
    System.out.println("Door is now unlocked for some time.");
  }
}