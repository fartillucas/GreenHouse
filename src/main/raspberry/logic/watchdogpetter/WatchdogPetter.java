package raspberry.logic.watchdogpetter;

import raspberry.logic.OutFacadeLogic;

public class WatchdogPetter implements Runnable {

    @Override
    public void run() {
        boolean good = false;
        while(true){

            if(good) {
                OutFacadeLogic.getInstance().petWatchdog();
            }
        }
    }
}
