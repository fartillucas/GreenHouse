package raspberry.logic.watchdogpetter;

import raspberry.logic.OutFacadeLogic;

import static java.lang.Thread.sleep;

public class WatchdogPetter implements Runnable {

    @Override
    public void run() {
        boolean good = true;
        while(true){
            if(good) {
                good = OutFacadeLogic.getInstance().petWatchdog();
            } else {
                good = OutFacadeLogic.getInstance().SendIPAddress();
            }

            try {
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
