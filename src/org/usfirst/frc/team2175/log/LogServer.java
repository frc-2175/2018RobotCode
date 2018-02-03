package org.usfirst.frc.team2175.log;
import java.io.BufferedReader;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
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
	        	ArrayList<String> directoryContents = new ArrayList<>();
	        	for(File file : baseDirectory.listFiles()) {
	        		if(file.isDirectory()) {
	        			directoryContents.add(file.getName());
	        		}
	        	}
	        	String json = gson.toJson(directoryContents);
	        	ctx.header("Content-Type", "application/json");
	        	ctx.result(json);
	        });
	        
	        app.get("/:name", ctx -> {
	        	File logFileFolder = new File(RobotLogger.BASE_DIRECTORY + "/" + ctx.param("name"));
	        	ArrayList<String> directoryContents = new ArrayList<>();
	        	for(File file : logFileFolder.listFiles()) {
	        		directoryContents.add(file.getName());
	        	}
	        	String json = gson.toJson(directoryContents);
	        	ctx.header("Content-Type", "application/json");
	        	ctx.result(json);
	        });
	        
	        app.get("/:foldername/:filename", ctx -> {
	        	File logFile = new File(RobotLogger.BASE_DIRECTORY 
	        			+ "/" + ctx.param("foldername") 
	        			+ "/" + ctx.param("filename"));
	        	ctx.result(new String(Files.readAllBytes(Paths.get(logFile.getAbsolutePath()))));
	        });
		}
	}
	
	public static void runServer() {
		(new Thread(new ServerRunnable())).start();
	}
	
}
