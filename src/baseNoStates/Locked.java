package baseNoStates;

public class Locked extends DoorState
{
  public Locked(Door door) {super(door);}

  @Override
  public void open() {
    System.out.println("Cannot open the door, it's already locked.");
  }

  @Override
  public void close() {
    if (!door.isClosed())
    {
      door.setClosed(true);
      System.out.println("Door is now closed.");
    }
    else
      System.out.println("Door is already closed.");
  }

  @Override
  public void lock() {
    System.out.println("Door is already locked.");
  }

  @Override
  public void unlock() {
    door.setState(new Unlocked(door)); // Cambiamos el estado a Unlocked
    System.out.println("Door is now unlocked.");
  }
}
