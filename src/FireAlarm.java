public class FireAlarm extends CodeHandler {

    private String codeFireAlarm;

    public FireAlarm(String code, CodeHandler nextHandler) {
        super(nextHandler);
        codeFireAlarm = code;
    }

    @Override
    public void handleCode(String code, Door door) {
        System.out.println("handle FireAlarm");
        if (!door.isLocked()) {
            if (codeFireAlarm.equals(code)) {
                door.resetState();
                door.open();
                System.out.println("NINOOOOO");
            } else
                super.handleCode(code, door);
        }
        else {
            //void, can't open until unlocked
        }
    }
}
