package baseNoStates;

import java.util.TimerTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*
Classe que representa l'estat "Bloquejat temporalment" d'una porta.
Durant aquest estat, només es poden fer
les accions d'obrir i tancar la porta.
A més, conté una classe rellotge que implementa el patró Observer,
la qual s'utilitza per comprovar
com està la porta (10 segons després de canviar l'estat a UnlockedShortly)
i així gestionar el canvi d'estat
de la porta a Locked si està tancada o Propped si està oberta.
Hereda de la classe pare DoorState.
*/
public class UnlockedShortly extends DoorState {

  static Logger logger = LoggerFactory.getLogger("baseNoStates.DoorState.UnlockedShortly");

  public UnlockedShortly(Door door) {
    super(door);
    this.name = States.UNLOCKED_SHORTLY;
    startTimer();
  }

  @Override
  public void open() {
    if (door.isClosed()) {
      door.setClosed(false);
      logger.info("The door is now opened.");
    } else {
      logger.info("The door is already closed.");
    }
  }

  @Override
  public void close() {
    if (!door.isClosed()) {
      door.setClosed(true);
      logger.info("The door is now closed.");
    } else {
      logger.info("The door is already opened.");
    }
  }

  @Override
  public void lock() {
    logger.info("The door can't be locked.");
  }

  @Override
  public void unlock() {
    logger.info("The door is already unlocked.");
  }

  @Override
  public void unlockShortly() {
    logger.info("The door is already unlocked for a short time.");
  }

  //Utilitem la classe timer per a crear un temporitzador de 10000 ms = 10s
  private void startTimer() {
    //Declarem el temporitzador que s'utilitza pels 10 segons que ha d'estar desbloquejada la porta
    Clock timer = Clock.getUniqueInstance();
    timer.schedule(new TimerTask() {
      @Override public void run() {
        checkDoorState();
      }
    }, 10000);
  }

  // Verifiquem l'estat de la porta després de 10 segons, i la bloquegem o passa a estat Propped
  private void checkDoorState() {
    if (door.isClosed()) {
      door.setState(new Locked(door));
      logger.info("Door is now locked after being unlocked for 10 seconds.");
    } else {
      door.setState(new Propped(door));
      logger.info("Door is now propped after being unlocked 10 seconds and being opened.");
    }
  }
}
