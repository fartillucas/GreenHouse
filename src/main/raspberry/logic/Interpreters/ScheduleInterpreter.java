package raspberry.logic.Interpreters;

import org.json.JSONObject;
import raspberry.logic.SetPoints;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ScheduleInterpreter {

    public List<HashMap<Integer, SetPoints>> interpret(JSONObject jsonSchedule) {

        List<HashMap<Integer, SetPoints>> schedule = new ArrayList<>();

        int numberOfDays = jsonSchedule.getInt("days");

        for (int i = 1; i <= numberOfDays; i++) {
            String dayString = jsonSchedule.get("day"+i).toString();

            JSONObject day = new JSONObject(dayString);

            HashMap<Integer, SetPoints> dayMap = new HashMap<>();

            for (int j = 1; j <= 12; j++) {
                String blockString = day.get("block"+j).toString();

                JSONObject block = new JSONObject(blockString);

                double temperature = block.getDouble("temperature");
                double humidity = block.getDouble("humidity");
                double waterlevel = block.getDouble("waterlevel");
                int blueLight = block.getInt("light_blue");
                int redLight = block.getInt("light_red");

                SetPoints setPoints = new SetPoints(temperature, humidity, waterlevel, blueLight, redLight);

                dayMap.put(j,setPoints);
            }

            schedule.add(dayMap);
        }


        return schedule;
    }
}
