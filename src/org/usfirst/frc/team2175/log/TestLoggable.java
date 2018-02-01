package org.usfirst.frc.team2175.log;

import java.util.HashMap;

public class TestLoggable implements Loggable {
	HashMap<String, Object> values;
	public static String TEST_KEY = "test.key";
	
	public TestLoggable() {
		values = new HashMap<>();
		values.put(TEST_KEY, 0.1);
	}
	
	@Override
	public String getLogType() {
		return "test";
	}

	@Override
	public int getId() {
		return 0;
	}

	@Override
	public HashMap<String, Object> getValues() {
		return values;
	}
}
