public class Log extends CodeHandler {
    public Log(CodeHandler nextHandler) { super(nextHandler); }

    @Override
    public void handleCode(String code, Door door) {
        System.out.println("Code: " + code);
        System.out.println("Door id: " + door.getId());
        if(door.isLocked())
            System.out.println("Door state: Locked");
        else
            System.out.println("Door state: Unolocked");

        //Imprimir plus date and time

        super.handleCode(code, door);
    }
}
