package baseNoStates;

import java.util.TimerTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*
Class representing Unlocked Shortly (for some time) state of a door.
In this state, only actions that can be done are opening and closing the doors.
Moreover, it contains a Clock that implements Observer pattern and checks how the door
is after 10 seconds.
Inherits DoorState class.
*/
public class UnlockedShortly extends DoorState {

  private static Logger logger = LoggerFactory.getLogger("baseNoStates.DoorState.UnlockedShortly");

  public UnlockedShortly(Door door) {
    super(door);
    this.name = States.UNLOCKED_SHORTLY;
    startTimer();
  }

  @Override
  public void open() {
    if (door.isClosed()) {
      door.setClosed(false);
      logger.info("Door " + door.getId() + " is now opened.");
    } else {
      logger.info("Door " + door.getId() + " is already closed.");
    }
  }

  @Override
  public void close() {
    if (!door.isClosed()) {
      door.setClosed(true);
      logger.info("Door " + door.getId() + " is now closed.");
    } else {
      logger.info("Door " + door.getId() + " is already opened.");
    }
  }

  @Override
  public void lock() {
    logger.info("Door " + door.getId() + " can't be locked.");
  }

  @Override
  public void unlock() {
    logger.info("Door " + door.getId() + " is already unlocked.");
  }

  @Override
  public void unlockShortly() {
    logger.info("Door " + door.getId() + " is already unlocked for a short time.");
  }

  //We use the timer class to create a 10s timer
  private void startTimer() {
    Clock timer = Clock.getUniqueInstance();
    timer.schedule(new TimerTask() {
      @Override public void run() {
        checkDoorState();
      }
    }, 10000);
  }

  // We check the state of the door after 10 seconds and we Lock it if it is closed
  private void checkDoorState() {
    if (door.isClosed()) {
      door.setState(new Locked(door));
      logger.info("Door " + door.getId() + " is now locked after being unlocked for 10 seconds.");
    } else {
      door.setState(new Propped(door));
      logger.info("Door " + door.getId() + " is now propped after being unlocked 10 seconds and being opened.");
    }
  }
}
