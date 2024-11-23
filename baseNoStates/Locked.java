package baseNoStates;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*
Class representing Locked state of a door. Only actions that can be done are unlocking
the door normally or temporally.
Inherits DoorState class.
 */
public class Locked extends DoorState {

  private static Logger logger = LoggerFactory.getLogger("baseNoStates.DoorState.Locked");

  public Locked(Door door) {
    super(door);
    this.name = States.LOCKED;
  }

  public void open() {
    logger.info("Cannot open door " + door.getId() + " , it's locked.");
  }

  public void close() {
    logger.info("Door  " + door.getId() + " is already closed.");
  }

  public void lock() {
    logger.info("Door " + door.getId() + " is already locked.");
  }

  public void unlock() {
    door.setState(new Unlocked(door)); // Cambiem l'estat  a Unlocked
    logger.info("Door " + door.getId() + " is now unlocked.");
  }

  @Override
  public void unlockShortly() {
    door.setState(new UnlockedShortly(door)); // Cambiem l'estat a unlocked
    logger.info("Door " + door.getId() + " is now unlocked for some time.");
  }
}
