package baseNoStates;


//Creem classe Propped com a nou estat de les portes
public class Propped extends DoorState {

  public Propped(Door door) {
    super(door);
    this.name = States.PROPPED;
  }

  @Override
  public void open() {
    System.out.println("Door is already opened.");
  }

  @Override
  public void close() {
    door.setClosed(true);
    System.out.println("This door is now closed and therefore locked.");
    door.setState(new Locked(door));
  }

  @Override
  public void lock() {
    System.out.println("Cannot lock the door while it's opened.");
  }

  @Override
  public void unlock() {
    System.out.println("The door is propped and can't be unlocked.");
  }

  @Override
  public void unlock_shortly() {
    System.out.println("The door is propped and can't be unlocked shortly.");
  }
}
