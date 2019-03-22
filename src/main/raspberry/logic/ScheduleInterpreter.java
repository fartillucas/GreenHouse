package raspberry.logic;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ScheduleInterpreter {

    public void interpret(JSONObject jsonObj) {

        JSONObject day1 = new JSONObject(jsonObj.get("day1").toString());

        System.out.println("Interpreter day 1: "+day1.toString());

        SetPoints setPoints = new SetPoints();
        HashMap<Integer, SetPoints> day = new HashMap<>();
        List<HashMap<Integer, SetPoints>> schedule = new ArrayList<>();






    }
}
