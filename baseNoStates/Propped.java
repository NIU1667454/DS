package baseNoStates;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*
Class representing Propped state of a door (blocked by something). Only actions that can be
done are closing the door to return to Locked state
Inherits DoorState class.
 */
public class Propped extends DoorState {

  private static Logger logger = LoggerFactory.getLogger("baseNoStates.DoorState.Propped");

  public Propped(Door door) {
    super(door);
    this.name = States.PROPPED;
  }

  @Override
  public void open() {
    logger.info("Door " + door.getId() + " is already opened.");
  }

  //When a Propped door is closed, it changes to Locked state
  @Override
  public void close() {
    door.setClosed(true);
    logger.info("Door  " + door.getId() + " is now closed and therefore locked.");
    door.setState(new Locked(door));
  }

  @Override
  public void lock() {
    logger.info("Cannot lock door " + door.getId() + " while it's opened.");
  }

  @Override
  public void unlock() {
    logger.info("Door " + door.getId() + " is propped and can't be unlocked.");
  }

  @Override
  public void unlockShortly() {
    logger.info("Door " + door.getId() + " is propped and can't be unlocked shortly.");
  }
}
