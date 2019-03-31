package raspberry.Acquaintance;

public interface IDatabaseFacade {

    void uploadInternalTemperature(double internalTemperature);
    void uploadExternalTemperature(double externalTemperature);
    void uploadHumidity(double humidity);
    void uploadWaterLevel(double waterLevel);



}
