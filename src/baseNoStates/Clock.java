package baseNoStates;

import java.util.Timer;

/*
Classe que implementa el patró Observer.
Està associada a l'estat de la porta Unlocked Shortly, i ajuda a gestionar el canvi d'estat de
les portes que es troben en aquest estat.
A més, també implementa el patró Singleton,
només existeix una instància de Clock per a tots els estats.
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
