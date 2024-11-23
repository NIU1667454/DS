package baseNoStates;

import baseNoStates.requests.RequestReader;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*
Door class represents a door in the system which has a state and it is associated to a space
from which it is accessed and one to which it accesses. This objects is part of the final part
of the areas hierarchy of the building.
 */
public class Door {
  private final String id;
  private boolean closed;
  private DoorState state;
  //Represents the spaces from both sided of the door
  private final Space fromSpace;
  private final Space toSpace;
  private static Logger logger = LoggerFactory.getLogger("baseNoStates.Door");

  public Door(String id, Space from, Space to) {
    this.id = id;
    closed = true;
    state = new Locked(this); // At the beginning, every door is locked
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
  Manages the requests from the doors and executes the requested action,
  it knows the current state and whether it is closed or not.
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

  // DoorState manages how to check the requested operation
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
