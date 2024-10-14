public class Unlock extends CodeHandler {

    private String codeUnlock;

    public Unlock(String code, CodeHandler nextHandler) {
        super(nextHandler);
        codeUnlock = code;
    }

    @Override
    public void handleCode(String code, Door door) {
        System.out.println("handle Unlocked");
        if (door.isLocked() && codeUnlock.equals(code)) {
            door.resetState();
        }
        else {
            //void, can't open until unlocked
            super.handleCode(code, door);
        }

    }
}
