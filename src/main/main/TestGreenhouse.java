package main;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import main.PLCCommunication.PLCConnection;
import main.PLCCommunication.UDPConnection;

/**
 * API tester
 * @author sps
 */
public class TestGreenhouse 
{
    public static void main(String[] args) 
    {
        //PLCConnection con = new UDPConnection(1025, "localhost");
        PLCConnection con = new UDPConnection(5000, "192.168.0.10");
        //PLCConnection con = new SerialConnection("COM4");
        //SerialConnection.getPortList("COM1");
        //practice comment
        IGreenhouse api = new Greenhouse(con);
        //api.SetRedLight(50);
        //api.SetTemperature(273 + 25);
        //api.SetFanSpeed(1);
        //double outdoorTemperature; 
        //while (true)
        //   outdoorTemperature = api.ReadTemp2();
        

                
        //System.exit(3);
    }
    
}
