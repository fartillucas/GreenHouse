package raspberry.Acquaintance;

public interface ICurrentMeasurementsFacade {

	 Double getInternalTemperature();

	 void setInternalTemperature(Double temp);

	 Double getExternalTemperature();

	 void setExternalTemperature(Double temp2);

	 Double getHumdity();

	 void setMoisture(Double moist);

	 Double getWaterlevel();

	 void setWaterLevel(Double level);
}


