

import javax.naming.Context;
import javax.naming.Name;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Hashtable;

public class ExportObject implements javax.naming.spi.ObjectFactory {
    public ExportObject() {
        try {
        	
           System.out.println("this is the hack");

        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Object getObjectInstance(Object obj, Name name, Context nameCtx, Hashtable<?, ?> environment) {
        return null;
    }
}