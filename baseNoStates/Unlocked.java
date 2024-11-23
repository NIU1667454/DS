package baseNoStates;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*
Class representing Unlocked state of a door. During this state, there can be done
all the actions except unlocking it shortly.
Inherits DoorState class.
*/
public class Unlocked extends DoorState {

  private static Logger logger = LoggerFactory.getLogger("baseNoStates.DoorState.Unlocked");

  public Unlocked(Door door) {
    super(door);
    this.name = States.UNLOCKED;
  }

  public void open() {
    if (door.isClosed()) {
      door.setClosed(false);
      logger.info("Door " + door.getId() + " is now opened.");
    } else {
      logger.info("Door " + door.getId() + " is already opened.");
    }
  }

  public void close() {
    if (!door.isClosed()) {
      door.setClosed(true);
      logger.info("Door " + door.getId() + " is now closed.");
    } else {
      logger.info("Door " + door.getId() + " is already closed.");
    }
  }


  public void lock() {
    if (door.isClosed()) {
      //Change to Locked state if the door is closed
      door.setState(new Locked(door));
      logger.info("Door " + door.getId() + " is now locked.");
    } else {
      logger.info("Door " + door.getId() + " is opened and can't be locked.");
    }
  }

  public void unlock() {
    logger.info("Door " + door.getId() + " is already unlocked.");
  }

  @Override
  public void unlockShortly() {
    logger.info("Door " + door.getId() + " cannot be unlocked shortly as it is not closed.");
  }
}
