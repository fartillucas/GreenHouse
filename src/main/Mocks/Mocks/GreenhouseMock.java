package Mocks.Mocks;

import main.IGreenhouse;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.BitSet;

public class GreenhouseMock implements IGreenhouse {

	private static GreenhouseMock instance;

	private Double humidity;
	private Double waterLevel;
	private Double internalTemperature;
	private Double externalTemperature;

	private int fanSpeed;
	private int addWaterSecs;

	private int redLightLevel;
	private int blueLightLevel;

	private boolean waterLevelWasSet;
	private boolean fanWasSet;

	public void resetMock(){
		this.humidity = 40.0;
		this.waterLevel = 5.0;
		this.internalTemperature = 20.0;
		this.externalTemperature = 20.0;
		this.fanSpeed = 0;
		this.addWaterSecs = 0;
		this.redLightLevel = 0;
		this.blueLightLevel = 0;
		this.waterLevelWasSet = false;
		this.fanWasSet = false;
	}

	public static GreenhouseMock getInstance(){
		if (GreenhouseMock.instance==null)	{
			GreenhouseMock.instance = new GreenhouseMock();
		}

		return GreenhouseMock.instance;
	}

	private GreenhouseMock(){
		resetMock();
	}

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
//		return fanSpeed !=0;
		return  this.fanWasSet;
	}

	public boolean waterPumpIsStarted(){
//		return addWaterSecs !=0;
		return this.waterLevelWasSet;
	}

	@Override
	public boolean SetTemperature(int kelvin) {
		this.internalTemperature = this.internalTemperature-(internalTemperature-kelvin)/2;
		return true;
	}

	@Override
	public boolean SetMoisture(int moist) {
		this.humidity = this.humidity-(humidity-moist)/2;
		return true;
	}

	@Override
	public boolean SetRedLight(int level) {
		this.redLightLevel = level;
		return true;
	}

	@Override
	public boolean SetBlueLight(int level) {
		this.blueLightLevel = level;
		return true;
	}

	@Override
	public boolean AddWater(int sec) {
		this.addWaterSecs=sec;
		if (sec != 0){
			this.waterLevelWasSet = true;
		}
		return true;
	}

	@Override
	public boolean AddFertiliser(int sec) {
		throw new NotImplementedException();
	}

	@Override
	public boolean AddCO2(int sec) {
		throw new NotImplementedException();
	}

	@Override
	public double ReadTemp1() {
		return this.internalTemperature;
	}

	@Override
	public double ReadTemp2() {
		return this.externalTemperature;
	}

	@Override
	public double ReadMoist() {
		return this.humidity;
	}

	@Override
	public double ReadWaterLevel() {
		return this.waterLevel;
	}

	@Override
	public double ReadPlantHeight() {
		throw new NotImplementedException();
	}

	@Override
	public BitSet ReadErrors() {
		throw new NotImplementedException();
	}

	@Override
	public boolean ResetError(int errorNum) {
		throw new NotImplementedException();
	}

	@Override
	public byte[] GetStatus() {
		throw new NotImplementedException();
	}

	@Override
	public boolean SetFanSpeed(int speed) {
		this.fanSpeed=speed;
		if (speed != 0){
			this.fanWasSet = true;
		}
		return true;
	}

	public void setExternalTemperature(double Kelvin) {
		this.externalTemperature=Kelvin;
	}


}
