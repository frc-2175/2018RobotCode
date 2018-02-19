package org.usfirst.frc.team2175.factory;

import org.usfirst.frc.team2175.info.RobotInfo;
import org.usfirst.frc.team2175.info.SmartDashboardInfo;

public class InfoFactory {
	public static void makeAllInfos() {
		new RobotInfo();
		new SmartDashboardInfo();
	}
}
