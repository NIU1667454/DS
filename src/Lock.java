public class Lock extends CodeHandler {

    public Lock(CodeHandler nextHandler) {
        super(nextHandler);
    }
    @Override
    public void handleCode(String code, Door door) {
        System.out.println("handle Lock");
        if (!door.isLocked() && door.getNumTrials() >= 3) {
            door.lock();
        }
        else {
            //void, can't open until unlocked
        }

    }
}
