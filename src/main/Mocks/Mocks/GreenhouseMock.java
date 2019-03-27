package Mocks.Mocks;

import main.IGreenhouse;

import java.util.BitSet;

public class GreenhouseMock implements IGreenhouse {

	private static GreenhouseMock instance;

	public static GreenhouseMock getInstanance(){
		if (GreenhouseMock.instance==null)	{
			GreenhouseMock.instance = new GreenhouseMock();
		}
		return GreenhouseMock.instance;
	}


	private Double humidity;
	private Double waterLevel;
	private Double internalTemperature;
	private int fanSpeed;
	private int addWaterSecs;

	public void setInternalTemperature(Double internalTemperature) {
		this.internalTemperature = internalTemperature;
	}
	public void setHumidity(Double humidity) {
		this.humidity = humidity;
	}

	public void setWaterLevel(Double waterLevel) {
		this.waterLevel = waterLevel;
	}

	public boolean fanIsStarted(){

		System.out.println(this.fanSpeed);

		return fanSpeed !=0;
	}

	public boolean waterPumpIsStarted(){
		return addWaterSecs !=0;
	}
	@Override
	public boolean SetTemperature(int kelvin) {
		return false;
	}

	@Override
	public boolean SetMoisture(int moist) {
		return false;
	}

	@Override
	public boolean SetRedLight(int level) {
		return false;
	}

	@Override
	public boolean SetBlueLight(int level) {
		return false;
	}

	@Override
	public boolean AddWater(int sec) {
		this.addWaterSecs=sec;
		return true;
	}

	@Override
	public boolean AddFertiliser(int sec) {
		return false;
	}

	@Override
	public boolean AddCO2(int sec) {
		return false;
	}

	@Override
	public double ReadTemp1() {
		return 0;
	}

	@Override
	public double ReadTemp2() {
		return 0;
	}

	@Override
	public double ReadMoist() {
		return 0;
	}

	@Override
	public double ReadWaterLevel() {
		return 0;
	}

	@Override
	public double ReadPlantHeight() {
		return 0;
	}

	@Override
	public BitSet ReadErrors() {
		return null;
	}

	@Override
	public boolean ResetError(int errorNum) {
		return false;
	}

	@Override
	public byte[] GetStatus() {
		return new byte[0];
	}

	@Override
	public boolean SetFanSpeed(int speed) {
		this.fanSpeed=speed;

		return true;
	}

	public void setExternalTemperature(double Kelvin) {
	}


}
