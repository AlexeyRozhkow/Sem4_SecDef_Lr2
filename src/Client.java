import javafx.css.Match;

import java.util.Random;

public class Client {
    private String name;
    private Long personalKey, sharedA, sharedP, sharedY, ressivedY, lastZ;

    Client(String name){
        this.name = name;
    }

    boolean recieveArgs(long p, long a) {
        sharedP = p;
        sharedA = a;
        return true;
    }

    boolean createPersoalKey() {
        Random random = new Random();
        personalKey = Integer.toUnsignedLong(random.nextInt(Main.limitKey));
        return true;
    }
    boolean createSecondKey(){
        sharedY = Integer.toUnsignedLong(Integer.parseInt(String.valueOf(Math.pow(sharedA, personalKey)).split("\\.")[0])) % sharedP;
        return true;
    }
    boolean ressiveSecondKey(Client client){
        ressivedY = client.sharedY;
        return true;
    }
    boolean createFinalKey(){
        lastZ = Integer.toUnsignedLong(Integer.parseInt(String.valueOf(Math.pow(ressivedY, personalKey)).split("\\.")[0])) % sharedP;
        return true;
    }
    String status(int i){
        String[] status = new String[7];
        status[0] = name;
        status[1] = String.valueOf(personalKey);
        status[2] = String.valueOf(sharedA);
        status[3] = String.valueOf(sharedP);
        status[4] = String.valueOf(sharedY);
        status[5] = String.valueOf(ressivedY);
        status[6] = String.valueOf(lastZ);
        return status[i];
    }
}
