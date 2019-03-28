package raspberry.logic.Interpreters;

import org.json.JSONObject;
import raspberry.logic.IPAddressPort;
import raspberry.logic.InvalidIPAddressException;


public class GetLiveDataInterpreter {


    public IPAddressPort Interpret(JSONObject jsonLiveData) throws InvalidIPAddressException {
        String ip = jsonLiveData.getString("IPAddress");
        int port = jsonLiveData.getInt("port");

        String[] iparray = ip.split("[.]");

        if (iparray.length != 4) {
            throw new InvalidIPAddressException();
        }

        int[] ipIntArray = new int[4];

        for (int i = 0; i < iparray.length; i++) {

            int ipnumber = Integer.parseInt(iparray[i]);

            if (!(ipnumber <= 255 && ipnumber >= 0)) {
                throw new InvalidIPAddressException();
            }

            ipIntArray[i] = ipnumber;

        }

        if (!(port <= 65535 && port >= 0)) {
            throw new InvalidIPAddressException();
        }

        return new IPAddressPort(ipIntArray, port);

    }



}
