


import java.net.InetAddress;
import java.net.UnknownHostException;

public class Config {

    public static String command = "/System/Applications/Calculator.app/Contents/MacOS/Calculator";

    public static String hostname;

    static {
        try { 
            hostname = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            hostname = "127.0.0.1";
        }
    }

    public static int ldapPort = 1389;

    public static int httpPort = 8000;
}