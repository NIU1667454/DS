package baseNoStates;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*
Classe que representa l'estat "Bloquejat per objecte" d'una porta. L'única acció que es pot fer
amb una porta propped és tancar-la per tornar a l'estat bloquejat.
Hereda de la classe pare DoorState.
 */
public class Propped extends DoorState {

  static Logger logger = LoggerFactory.getLogger("baseNoStates.DoorState.Propped");

  public Propped(Door door) {
    super(door);
    this.name = States.PROPPED;
  }

  //Estat propped és perque la porta està oberta
  @Override
  public void open() {
    logger.info("Door is already opened.");
  }

  //Quan es tanca la porta en estat Propped, torna a estat Bloquejat
  @Override
  public void close() {
    door.setClosed(true);
    logger.info("This door is now closed and therefore locked.");
    door.setState(new Locked(door));
  }

  //No es pot canviar d'estat, només quan es tanca la porta
  @Override
  public void lock() {
    logger.info("Cannot lock the door while it's opened.");
  }

  //No es pot canviar d'estat, només quan es tanca la porta
  @Override
  public void unlock() {
    logger.info("The door is propped and can't be unlocked.");
  }

  //No es pot canviar d'estat, només quan es tanca la porta
  @Override
  public void unlockShortly() {
    logger.info("The door is propped and can't be unlocked shortly.");
  }
}
