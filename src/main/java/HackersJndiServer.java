

import com.beust.jcommander.JCommander;
import com.beust.jcommander.UnixStyleUsageFormatter;

public class HackersJndiServer {

    public static void main(String[] args) throws Exception {
        
    	JCommander jc = JCommander.newBuilder()
                .addObject(new Config())
                .build();
        jc.setProgramName("java -jar target/RogueJndi-1.0.jar");
        jc.setUsageFormatter(new UnixStyleUsageFormatter(jc));
        
        HttpServer.start();
        LdapServer.start();
    }
}