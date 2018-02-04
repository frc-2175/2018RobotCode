package org.usfirst.frc.team2175.control;

import org.usfirst.frc.team2175.ServiceLocator;
import org.usfirst.frc.team2175.info.RobotInfo;
import org.usfirst.frc.team2175.info.SmartDashboardInfo;

import edu.wpi.first.wpilibj.Joystick;

public class DryverStation {
    private Joystick leftJoystick;
    private Joystick rightJoystick;
    private Joystick gamepad;
    private SmartDashboardInfo smartDashboardInfo;

    public DryverStation() {
        ServiceLocator.register(this);
        RobotInfo robotInfo = ServiceLocator.get(RobotInfo.class);
        leftJoystick = robotInfo.get(RobotInfo.LEFT_JOYSTICK);
        rightJoystick = robotInfo.get(RobotInfo.RIGHT_JOYSTICK);
        gamepad = robotInfo.get(RobotInfo.GAMEPAD);
        smartDashboardInfo = ServiceLocator.get(SmartDashboardInfo.class);
    }

    public double getMoveValue() {
        return deadband(leftJoystick.getY(), 
        		smartDashboardInfo.getNumber(SmartDashboardInfo.POSITIVE_DEADBAND), 
        		smartDashboardInfo.getNumber(SmartDashboardInfo.NEGATIVE_DEADBAND));
    }

    public double getTurnValue() {
        return rightJoystick.getX();
    }

    public double getIntakeAxisValue() {
        return gamepad.getRawAxis(2);
    }

    public double getClimberAxisValue() {
        return gamepad.getRawAxis(6);
    }

    public double getElevatorAxisValue() {
        return -gamepad.getRawAxis(1);
    }

    public boolean getIsSpinInButtonPressed() {
        return gamepad.getRawButton(3);
    }

    public boolean getIsSpinOutButtonPressed() {
        return gamepad.getRawButton(4);
    }
    
    public static double deadband(double value, double positiveDeadband, double negativeDeadband) {
    	if(value > positiveDeadband) {
    		return (value - positiveDeadband) / (1.0 - positiveDeadband);
    	} else if(value < negativeDeadband) {
    		return (value - negativeDeadband) / (1.0 + negativeDeadband);
    	} else {
    		return 0;
    	}
    }
}
