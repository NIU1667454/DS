package baseNoStates;

import java.util.Timer;
import java.util.TimerTask;

//Nova classe per representar un nou DoorState
public class UnlockedShortly extends DoorState {

  //declarem el temporitzador que s'utilitza pels 10 segons que ha d'estar desbloquejada la porta
  private Timer timer;

  public UnlockedShortly(Door door) {
    super(door);
    this.name = States.UNLOCKED_SHORTLY;
    startTimer();
  }

  //Utilitem la classe timer per a crear un temporitzador de 10000 ms o 10s
  private void startTimer() {
    timer = new Timer();
    timer.schedule(new TimerTask() {@Override
                                    public void run() { checkDoorState(); }
    }, 10000);
  }

  // Verifiquem l'estat de la porta després de 10 segons
  private void checkDoorState() {
    if (door.isClosed())
    {  // Si la porta està tancada passats els 10 segons, la bloquegem
      door.setState(new Locked(door));
      System.out.println("Door is now locked after being unlocked for 10 seconds.");
    }
    else
    {  // Si la porta està oberta, passa a estat Propped
      door.setState(new Propped(door));
      System.out.println("Door is now propped after being unlocked for 10 seconds and being opened.");
    }
  }

  @Override
  public void open() {
    if(door.isClosed())
    {
      door.setClosed(false);
      System.out.println("The door is now opened.");
    }
    else
      System.out.println("The door is already closed.");
  }

  @Override
  public void close() {
    if(!door.isClosed())
    {
      door.setClosed(true);
      System.out.println("The door is now closed.");
    }
    else
      System.out.println("The door is already opened.");
  }

  @Override
  public void lock() {
    System.out.println("The door can't be locked.");
  }

  @Override
  public void unlock() {
    System.out.println("The door is already unlocked.");
  }

  @Override
  public void unlock_shortly() {
    System.out.println("The door is already unlocked for a short time.");
  }
}
