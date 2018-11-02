import javafx.css.Match;

import java.math.BigDecimal;
import java.util.Random;

public class Client {
    private String name;
    private BigDecimal personalKey, sharedA, sharedP, sharedY, ressivedY, lastZ;

    Client(String name){
        this.name = name;
    }

    boolean recieveArgs(long p, long a) {
        sharedP = new BigDecimal(p);
        sharedA = new BigDecimal(a);
        return true;
    }

    boolean createPersoalKey() {
        Random random = new Random();
        personalKey = new BigDecimal(0);
        while (personalKey.equals(new BigDecimal(0))|| personalKey.equals(new BigDecimal(1))){
            System.out.printf("PK %s", personalKey.doubleValue());
            System.out.println();
            personalKey = new BigDecimal(random.nextInt(Main.limitKey));
        }
        return true;
    }
    boolean createSecondKey(){
        System.out.println(name + " A^X   " + Math.pow(sharedA.doubleValue(), personalKey.doubleValue()));
        sharedY = new BigDecimal(Math.pow(sharedA.doubleValue(), personalKey.doubleValue())% sharedP.doubleValue());
        return true;
    }
    boolean ressiveSecondKey(Client client){
        ressivedY = client.sharedY;
        return true;
    }
    boolean createFinalKey(){
        System.out.println(name + " Y^X   " + Math.pow(ressivedY.doubleValue(), personalKey.doubleValue()));
        lastZ = new BigDecimal(Math.pow(ressivedY.doubleValue(), personalKey.doubleValue())% sharedP.doubleValue());
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
