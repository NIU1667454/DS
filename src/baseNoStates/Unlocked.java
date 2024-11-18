package baseNoStates;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*
Classe que representa l'estat "Bloquejat" d'una porta. Durant aquest estat, es poden fer totes
les accions disponibles (obrir, tancar i bloquejar) menys bloquejar-la temporalment.
Hereda de la classe pare DoorState.
*/
public class Unlocked extends DoorState {

  static Logger logger = LoggerFactory.getLogger("baseNoStates.DoorState.Unlocked");

  public Unlocked(Door door) {
    super(door);
    this.name = States.UNLOCKED;
  }

  public void open() {
    if (door.isClosed()) {
      door.setClosed(false);
      logger.info("Door is now opened.");
    } else {
      logger.info("Door is already opened.");
    }
  }

  public void close() {
    if (!door.isClosed()) {
      door.setClosed(true);
      logger.info("Door is now closed.");
    } else {
      logger.info("Door is already closed.");
    }
  }


  public void lock() {
    if (door.isClosed()) {
      //Canviar a estat bloquejat la porta sempre i quan estigui tancada
      door.setState(new Locked(door)); // Cambiamos el estado a Locked
      logger.info("Door is now locked.");
    } else {
      logger.info("Door is opened and can't be locked.");
    }
  }

  public void unlock() {
    logger.info("Door is already unlocked.");
  }

  @Override
  public void unlockShortly() {
    logger.info("Door cannot be unlocked shortly as it is not closed.");
  }
}
