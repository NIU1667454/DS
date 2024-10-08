package baseNoStates;

public class Unlocked extends DoorState{

  public Unlocked(Door door)
  {
    super(door);
    this.name = "unlocked";
  }

  public void open()
  {
    if (door.isClosed())
    {
      door.setClosed(false); // Abrimos la puerta físicamente
      System.out.println("Door is now open.");
    }
    else
      System.out.println("Door is already open.");
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
    door.setState(new Locked(door)); // Cambiamos el estado a Locked
    System.out.println("Door is now locked.");
  }

  public void unlock()
  {
    System.out.println("Door is already unlocked.");
  }
}
