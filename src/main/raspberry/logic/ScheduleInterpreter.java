package raspberry.logic;

import org.json.JSONObject;

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
                String blcckString = day.get("block"+j).toString();

                JSONObject block = new JSONObject(blcckString);

                double temperature = block.getDouble("temperature");
                double humidity = block.getDouble("humidity");
                double waterlevel = block.getDouble("waterlevel");
                double blueLight = block.getDouble("light_blue");
                double redLight = block.getDouble("light_red");

                SetPoints setPoints = new SetPoints(temperature, humidity, waterlevel, blueLight, redLight);

                dayMap.put(j,setPoints);
            }

            schedule.add(dayMap);
        }


        return schedule;
    }
}
