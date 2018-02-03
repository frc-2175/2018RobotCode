package org.usfirst.frc.team2175.log;
import java.io.File;
import java.util.ArrayList;

import com.google.gson.Gson;

import io.javalin.Javalin;

public class LogServer {
	
	public static class ServerRunnable implements Runnable {
		@Override
		public void run() {
			Javalin app = Javalin.start(7000);
			Gson gson = new Gson();
	        app.get("/", ctx -> {
	        	File baseDirectory = new File(RobotLogger.BASE_DIRECTORY);
	        	ArrayList<File> directoryContents = new ArrayList<>();
	        	for(File file : baseDirectory.listFiles()) {
	        		if(file.isDirectory()) {
	        			directoryContents.add(file);
	        		}
	        	}
	        	String json = gson.toJson(directoryContents);
	        	ctx.header("Content-Type", "application/json");
	        	ctx.result(json);
	        });
	        System.out.println("The server is started!");
		}
	}
	
	public static void runServer() {
		(new Thread(new ServerRunnable())).start();
	}
	
}
