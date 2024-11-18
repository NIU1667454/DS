package baseNoStates;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*
Classe que representa l'estat "Bloquejat" d'una porta. Les úniques accions que es poden fer
amb una porta bloquejada són desbloquejar-la normal o temporalment.
Hereda de la classe pare DoorState.
 */
public class Locked extends DoorState {

  static Logger logger = LoggerFactory.getLogger("baseNoStates.DoorState.Locked");

  public Locked(Door door) {
    super(door);
    this.name = States.LOCKED;
  }

  public void open() {
    logger.info("Cannot open the door, it's locked.");
  }

  public void close() {
    logger.info("Door is already closed.");
  }

  public void lock() {
    logger.info("Door is already locked.");
  }

  public void unlock() {
    door.setState(new Unlocked(door)); // Cambiem l'estat  a Unlocked
    logger.info("Door is now unlocked.");
  }

  @Override
  public void unlockShortly() {
    door.setState(new UnlockedShortly(door)); // Cambiem l'estat a unlocked
    logger.info("Door is now unlocked for some time.");
  }
}
