package org.usfirst.frc.team2175.control;

import org.usfirst.frc.team2175.ServiceLocator;

import edu.wpi.first.wpilibj.DriverStation;

public class JoystickEventMapper {

		public JoystickEventMapper() {
			DryverStation driverStation = ServiceLocator.get(DryverStation.class);
		}
}
