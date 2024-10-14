public class Client {
    public static void main(String[] args) {
        String openCode = "1111";
        String fireAlarmCode = "2222";
        String unlockCode = "3333";
        CodeHandler chain1 =
                new Log(new Unlock(unlockCode,
                        new FireAlarm(fireAlarmCode,
                                new Open(openCode,
                                        new Lock(null)))));
        CodeHandler chain2 = new Log(new Open(openCode, null));

        CodeHandler chain3 = new Log( new FireAlarm(fireAlarmCode, new Open(openCode, null)));

        Door d1 = new Door("d1", chain1);
        System.out.println("Door d1 behaviour: Log - Unlock - FireAlarm - Open - Lock");
        System.out.println("Open code: ");
        d1.processCode("1111");
        System.out.println("Open and fire the alarm: ");
        d1.processCode("2222");
        System.out.println("1st trial, wrong code: ");
        d1.processCode("1234");
        System.out.println("2nd trial, wrong code: ");
        d1.processCode("4321");
        System.out.println("3th trial, wrong code: ");
        d1.processCode("5555");
        System.out.println("Invalid Unlock code: ");
        d1.processCode("6666");
        System.out.println("Invalid Unlock code: ");
        d1.processCode("7777");
        System.out.println("Invalid Unlock code: ");
        d1.processCode("1111");
        System.out.println("Valid Unlock code: ");
        d1.processCode("3333");

        System.out.println("Change behaviour of door d1: Log - Open");
        d1.setCodeHandler(chain2);
        System.out.println("Open code: ");
        d1.processCode("1111");
        System.out.println("1st trial, wrong code: ");
        d1.processCode("2222"); //door can't handle FireAlarm
        System.out.println("2nd trial, wrong code: ");
        d1.processCode("1234");
        System.out.println("3th trial, wrong code: ");
        d1.processCode("4321");
        System.out.println("4th trial, wrong code: ");
        d1.processCode("3333");
        System.out.println("Door can't handle Lock");

        System.out.println("Change behaviour of door d1: Log - FireAlarm - Open");
        d1.setCodeHandler(chain3);
        System.out.println("Open code: ");
        d1.processCode("1111");
        System.out.println("Open and fire the alarm: ");
        d1.processCode("2222");
        System.out.println("1st trial, wrong code: ");
        d1.processCode("1234");
        System.out.println("2nd trial, wrong code: ");
        d1.processCode("4321");
        System.out.println("3th trial, wrong code: ");
        d1.processCode("5555");
        System.out.println("4th trial, wrong code: ");
        d1.processCode("3333"); //invalid unlock code
        System.out.println("Door can't handle Lock");

        /*
        BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            System.out.print("Input code: ");
            String inputCode = stdin.readLine();
            d1.processCode(inputCode);
        }
         */

    }
}
