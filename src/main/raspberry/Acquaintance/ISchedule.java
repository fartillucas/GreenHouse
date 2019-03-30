package raspberry.Acquaintance;

import raspberry.logic.SetPoints;

import java.util.HashMap;
import java.util.List;

public interface ISchedule {

    ErrorCode apply(List<HashMap<Integer, SetPoints>> schedule);

    SetPoints getSetpoints();

    Double getTemperature();
    Double getHumidity();
    Double getWaterLevel();
    Integer getBlueLight();
    Integer getRedLight();

}
