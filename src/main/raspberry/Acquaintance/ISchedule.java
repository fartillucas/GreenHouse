package raspberry.Acquaintance;

import raspberry.logic.SetPoints;

import java.util.HashMap;
import java.util.List;

public interface ISchedule {

    ErrorCode apply(List<HashMap<Integer, SetPoints>> schedule);

    SetPoints getSetpoints();

    double getTemperature();
    double getHumidity();
    double getWaterLevel();
    int getBlueLight();
    int getRedLight();

}
