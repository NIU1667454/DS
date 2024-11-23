package baseNoStates;

import java.util.Timer;

/*
Class which implements Observer pattern.
It is associated to the door's state Unlocked Shortly, and helps to manage the change
of the door's states which are currently in this state.
Moreover, it implements Singleton pattern: there's only one instance of Clock for every state.
*/
public class Clock extends Timer {
  private static Clock clock = null;

  public static Clock getUniqueInstance() {
    if (clock == null) {
      clock = new Clock();
    }

    return clock;
  }
}
