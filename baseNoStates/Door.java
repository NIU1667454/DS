package baseNoStates;

import baseNoStates.requests.RequestReader;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*
La classe door representa una porta del sistema que té un estat i està associada a dos espais,
un des d'on s'accedeix i un altre al que dona accés. Aquest objecte forma la part final de la
jerarquia d'àrees de l'edifici (fulles)
 */
public class Door {
  private final String id;
  private boolean closed;
  private DoorState state;
  //Representen els espais que trobem a les dues bandes de la porta físicament
  private final Space fromSpace;
  private final Space toSpace;
  private static Logger logger = LoggerFactory.getLogger("baseNoStates.Door");

  public Door(String id, Space from, Space to) {
    this.id = id;
    closed = true;
    state = new Locked(this); // Al principi totes les portes estan bloquejades
    fromSpace = from;
    toSpace = to;
  }

  public Space getToSpace() {
    return this.toSpace;
  }

  public Space getFromSpace() {
    return this.fromSpace;
  }

  /*
  Processa una sol·licitud a la porta i executa l'acció sol·licitada,
  ja que coneix l'estat en que es troba i
  si està oberta o tancada
   */
  public void processRequest(RequestReader request) {

    if (request.isAuthorized()) {
      String action = request.getAction();
      doAction(action);
    } else {
      logger.info("not authorized");
    }
    request.setDoorStateName(getStateName());
  }

  // DoorState s'encarrega de comprovar l'operació que es fa,
  // aquí només es criden els seus respectius mètodes
  private void doAction(String action) {
    switch (action) {
      case Actions.OPEN:
        state.open();
        break;
      case Actions.CLOSE:
        state.close();
        break;
      case Actions.LOCK:
        state.lock();
        break;
      case Actions.UNLOCK:
        state.unlock();
        break;
      case Actions.UNLOCK_SHORTLY:
        state.unlockShortly();
        break;
      default:
        assert false : "Unknown action " + action;
        System.exit(-1);
    }
  }

  public boolean isClosed() {
    return this.closed;
  }

  public void setClosed(boolean close) {
    this.closed = close;
  }

  public void setState(DoorState state) {
    this.state = state;
  }

  public String getId() {
    return this.id;
  }

  public String getStateName() {
    return this.state.getName();
  }

  @Override
  public String toString() {
    return "Door{"
        + ", id='" + id + '\''
        + ", closed=" + closed
        + ", state=" + getStateName()
        + "}";
  }

  public JSONObject toJson() {
    JSONObject json = new JSONObject();
    json.put("id", id);
    json.put("state", getStateName());
    json.put("closed", closed);
    return json;
  }
}
