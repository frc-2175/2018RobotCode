package org.usfirst.frc.team2175.log;

import java.util.HashMap;

public interface Loggable {
	public String getLogType();
	public int getId();
	public HashMap<String, Object> getValues();
}
