package main;

import main.PLCCommunication.ICommands;
import main.PLCCommunication.Message;
import main.PLCCommunication.PLCConnection;


import java.util.BitSet;

/**
 * API to communicate to the PLC
 * @author Steffen Skov
 */
public class Greenhouse implements IGreenhouse, ICommands
{
    private PLCConnection conn;
    private Message mess;
    
    /**
     * Create greenhouse API
     */
    public Greenhouse()
    {
           
    }
      
    /**
     * Create greenhouse API
     * @param c connection
     */
    public Greenhouse(PLCConnection c)
    {
            this.conn = c;           
    }
      
    /**
     * Setpoint for temperature inside Greenhouse
     * CMD: 1
     * @param kelvin : temperature in kelvin (273 > T > 303)
     * @return true if processed
     */
    
    public boolean SetTemperature(int kelvin)
    {
        mess = new Message(TEMP_SETPOINT);
        if (kelvin > 273 && kelvin < 303) // 0 - 30 grader celcius
        {
            System.out.println("Set temperatur setpoint to " + kelvin);
            mess.setData(kelvin - 273);
            if (conn.send())return true;
            else return false;
        }
        return false;
    }
    
    /**
     * Setpoint for moisture inside Greenhouse
     * CMD:2
     * @param moist in % ( 10 > M > 90 )
     * @return true if processed
     */
    public boolean SetMoisture(int moist)
    {
        mess = new Message(TEMP_SETPOINT);
        if (moist > 10 && moist < 90) 
        {
            mess.setData(moist);
            if (conn.send())return true;
            else return false;
        }
        return false;
    }
    
    /**
     * Setpoint for red light inside Greenhouse
     * CMD:3
     * @param level in percent
     * @return true if processed
     */
    public boolean SetRedLight(int level)
    {
        System.out.println("Set red light to " + level);
        mess = new Message(REDLIGHT_SETPOINT);
        if (level >= 0 && level <= 100) 
        {
            mess.setData(level);
            conn.addMessage(mess);
            if (conn.send())return true;
            else return false;
        }
        return false;
    }
    
    /**
     * Setpoint for red light inside Greenhouse
     * CMD: 4
     * @param level in percent
     * @return true if processed
     */
    public boolean SetBlueLight(int level)
    {
        mess = new Message(BLUELIGHT_SETPOINT);
        if (level >= 0 && level <= 100) 
        {
            mess.setData(level);
            conn.addMessage(mess);
            if (conn.send())return true;
            else return false;
        }
        return false;
    }
    
    /**
     * Add water for some seconds. Pump is stopped if height of water is
     * exceeded
     * CMD: 5
     * @param sec : Second to turn on the pump
     * @return true if processed
     */
    public boolean AddWater(int sec)
    {
        if (sec >0 && sec < 120)
        {
            mess = new Message(ADDWATER);        
            mess.setData(sec);
            if (conn.send())return true;
            else return false;
        }
        return false;
    }
    
    /**
     * NOT IMPLEMENTED
     * Add Fertiliser for some seconds. Pump is stopped if height of water is
     * exceeded
     * CMD: 6
     * @param sec : Secord to turn on the pump
     * @return true if processed
     */
    public boolean AddFertiliser(int sec)
    {
        return true;
    }
    
    /**
     * NOT IMPLEMENTED
     * Add CO2 for some seconds. Pump is stopped if height of water is
     * exceeded
     * CMD: 7
     * @param sec : Secord to turn on the valve
     * @return true if processed
     */
    public boolean AddCO2(int sec)
    {
        return true;
    }
    
    /**
     * Read tempature inside the Greenhouse
     * CMD:8 
     * @return Temperature in kelvin
     */
    public Double ReadTemp1()
    {
        System.out.println("Read greenhouse temperatur ");
        mess = new Message(READ_GREENHOUSE_TEMP);
        Double temp = 0.0;
        mess.setData(); //None data
        conn.addMessage(mess);
        if (conn.send()) {
            if (mess.getResultData() != null){
                temp = (double) (mess.getResultData())[0];
            temp += 273.0;
        }
            else
                temp =  null; // return a dummy value
        }
        return temp;
    }
    
    /**
     * Read tempature outside the Greenhouse
     * CMD: 9
     * @return Temperature in kelvin
     */
    public Double ReadTemp2()
    {
        System.out.println("Read outdoor temperatur ");
        mess = new Message(READ_OUTDOOR_TEMP);
        Double temp2 = 0.0;
        mess.setData(); //None data
        conn.addMessage(mess);
        if (conn.send()) {
            if (mess.getResultData() != null){
                temp2 = (double) (mess.getResultData())[0];
                temp2 += 273.0;
            }
            else
                temp2 =  null; // return a dummy value
        }
        return temp2;

    }
    
    /**
     * Read relative moisture inside the Greenhouse
     * CMD: 10
     * @return Moisture in %
     */
    public Double ReadMoist()
    {
         System.out.println("Read outdoor temperatur ");
        mess = new Message(READ_MOISTURE);
        Double moist = 0.0;
        mess.setData(); //None data
        conn.addMessage(mess);
        if (conn.send())
        {
            if (mess.getResultData() != null)
                moist = (double) (mess.getResultData())[0];
            else
                moist =  null; // return a dummy value
                               // In real world moist will never be so low
        }
        return moist;
    }  
    
    /**
     * Read level of water in the Greenhouse
     * CMD: 11
     * @return Level in millimeter [0 < level < 250]
     */
    public Double ReadWaterLevel()
    {
         System.out.println("Read water level ");
        mess = new Message(READ_WATER_LEVEL);
        Double level = 0.0; // level
        mess.setData(); //None data
        conn.addMessage(mess);
        if (conn.send())
        {
            if (mess.getResultData() != null)
                level = (mess.getResultData())[0] * 10.0;
            else
                level =  null; // return a dummy value
        }
        return level;
    }   
    
    /**
     * NOT IMPLEMENTED
     * Read higths of the plants
     * CMD: 12
     * @return Higths (cm?)
     */
    public Double ReadPlantHeight()
    {
         System.out.println("Read height of plants");
        mess = new Message(READ_PLANT_HEIGHT);
        Double level = 0.0; // level
        mess.setData(); //None data
        conn.addMessage(mess);
        if (conn.send())
        {
            if (mess.getResultData() != null)
                level = (double)(mess.getResultData())[0];
            else
                level =  null; // return a dummy value
        }
        return level;
    }    
    
    /**
     * Read all alarms one bits pr. alarm.
     * CMD: 13
     * @return Alarms as BitSet
     */
    public BitSet ReadErrors()
    {
         System.out.println("Get all alarms ");
        mess = new Message(READ_ALL_ALARMS);
        BitSet alarms = new BitSet(32); 
        
        mess.setData(); //None data
        conn.addMessage(mess);
        if (conn.send())
        {
            alarms = fillBitSet(mess.getResultData());
        }
        System.out.println("Alarm state is: " + alarms);
        return alarms;
    }  
    
    private BitSet fillBitSet(byte [] al)
    {      
        BitSet alarms = new BitSet(32); 
        if (true)
        {
            if (al != null && al.length == 4)
                for (int i = 0; i<4; i++)
                    for (int b =0; b<8; b++)
                    {
                        int ib = (al[i]>>b)&0x1;
                        Boolean bit;
                        if (ib == 1) bit = true;
                        else bit = false;
                        alarms.set(i*8+b, bit);
                    }
        }
        System.out.println("Alarms in set state: " + alarms);
        return alarms;
    }  
    
    /**
     * Reset one alarm
     * CMD: 14
     * @param errorNum
     * @return Done
     */
    public boolean ResetError(int errorNum)
    {
        return true;
    }  
    
    /**
     * Get all values as a byte array
     * CMD: 15
     * @return All values
     */
    public byte [] GetStatus()
    {
        byte [] result = null;
        return result;
    }
    
    /**
     * Set Fane speed
     * @param speed : {OFF (0), LOW (1), HIGH(2)};
     * @return Done
     */
    public boolean SetFanSpeed(int speed)
    {
        System.out.println("Set fan speed " + speed);
        mess = new Message(SET_FAN_SPEED);
        if (speed >= 0 && speed <= 2) 
        {
            mess.setData(speed);
            conn.addMessage(mess);
            if (conn.send())return true;
            else return false;
        }
        return false;





    }


}
