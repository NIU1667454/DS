package baseNoStates;

public class Unlocked extends DoorState{

  public Unlocked(Door door)
  {
    super(door);
    this.name = States.UNLOCKED;
  }

  public void open()
  {
    if (door.isClosed())
    {
      door.setClosed(false); // Abrimos la puerta físicamente
      System.out.println("Door is now opened.");
    }
    else
      System.out.println("Door is already opened.");
  }

  public void close()
  {
    if (!door.isClosed())
    {
      door.setClosed(true); // Cerramos la puerta físicamente
      System.out.println("Door is now closed.");
    }
    else
      System.out.println("Door is already closed.");
  }

  public void lock()
  {
    if(door.isClosed())
    {
      door.setState(new Locked(door)); // Cambiamos el estado a Locked
      System.out.println("Door is now locked.");
    } else
      System.out.println("Door is opened and can't be locked.");
  }

  public void unlock()
  {
    System.out.println("Door is already unlocked.");
  }

  @Override
  public void unlock_shortly() {
    System.out.println("Door cannot be unlocked shortly as it is not closed.");
  }
}
