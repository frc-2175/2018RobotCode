package org.usfirst.frc.team2175.log;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import com.google.gson.Gson;

import io.javalin.Javalin;

public class LogServer {
	public boolean isRunning;

	public static class ServerRunnable implements Runnable {
		@Override
		public void run() {
			Javalin app = Javalin.create().port(7000).start();
			Gson gson = new Gson();

			app.before(ctx -> {
				ctx.header("Access-Control-Allow-Origin", "*");
			});

			app.get("/", ctx -> {
				File baseDirectory = new File(RobotLogger.BASE_DIRECTORY);
				ArrayList<String> directoryContents = new ArrayList<>();
				for (File file : baseDirectory.listFiles()) {
					if (file.isDirectory()) {
						directoryContents.add(file.getName());
					}
				}
				String json = gson.toJson(directoryContents);

				ctx.header("Content-Type", "application/json");
				ctx.result(json);
			});

			app.get("/:name", ctx -> {
				File logFileFolder = new File(RobotLogger.BASE_DIRECTORY + "/" + ctx.param("name"));
				if (!logFileFolder.exists()) {
					ctx.status(404);
					return;
				}

				ArrayList<String> directoryContents = new ArrayList<>();
				for (File file : logFileFolder.listFiles()) {
					directoryContents.add(file.getName());
				}
				String json = gson.toJson(directoryContents);

				ctx.header("Content-Type", "application/json");
				ctx.result(json);
			});

			app.get("/:foldername/:filename", ctx -> {
				File logFile = new File(
					RobotLogger.BASE_DIRECTORY + "/" + ctx.param("foldername") + "/" + ctx.param("filename"));
				if (!logFile.exists()) {
					ctx.status(404);
					return;
				}

				ctx.result(new String(Files.readAllBytes(Paths.get(logFile.getAbsolutePath()))));
			});
		}
	}

	public void runServer() {
		if (!isRunning) {
			(new Thread(new ServerRunnable())).start();
			isRunning = true;
		}
	}

}
