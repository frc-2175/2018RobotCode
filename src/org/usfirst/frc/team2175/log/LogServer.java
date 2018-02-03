package org.usfirst.frc.team2175.log;
import io.javalin.Javalin;

public class LogServer {
	
	public static class ServerRunnable implements Runnable {
		@Override
		public void run() {
			Javalin app = Javalin.start(7000);
	        app.get("/", ctx -> ctx.result("Hello World"));
	        System.out.println("The server is started!");
		}
	}
	
	public static void runServer() {
		(new Thread(new ServerRunnable())).start();
	}
	
}
