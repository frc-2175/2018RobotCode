/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team2175.robot;

import java.util.logging.Logger;

import org.usfirst.frc.team2175.ServiceLocator;
import org.usfirst.frc.team2175.command.DefaultCommandFactory;
import org.usfirst.frc.team2175.command.autonomous.AutonSelector;
import org.usfirst.frc.team2175.control.DryverStation;
import org.usfirst.frc.team2175.control.JoystickEventMapper;
import org.usfirst.frc.team2175.factory.InfoFactory;
import org.usfirst.frc.team2175.factory.SubsystemsFactory;
import org.usfirst.frc.team2175.log.LogServer;
import org.usfirst.frc.team2175.log.LoggingConfig;
import org.usfirst.frc.team2175.log.RobotLogger;
import org.usfirst.frc.team2175.subsystem.DrivetrainSubsystem;
import org.usfirst.frc.team2175.subsystem.ElevatorSubsystem;
import org.usfirst.frc.team2175.subsystem.LimeLight;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in the
 * project.
 */
public class Robot extends TimedRobot {
	private final Logger log = RobotLogger.getLogger(this);
	private Command m_autoSelected;
	private DrivetrainSubsystem drivetrainSubsystem;
	private ElevatorSubsystem elevatorSubsystem;
	private RobotLogger robotLogger;
	private LogServer logServer;
	private AutonSelector autonSelector;

	@Override
	public void robotInit() {
		LoggingConfig.initialize();
		ServiceLocator.register(this);
		robotLogger = new RobotLogger();
		InfoFactory.makeAllInfos();
		new DryverStation();
		SubsystemsFactory.makeAllSubsystems();
		autonSelector = new AutonSelector();
		robotLogger.log();
		logServer = new LogServer();
		drivetrainSubsystem = ServiceLocator.get(DrivetrainSubsystem.class);
		elevatorSubsystem = ServiceLocator.get(ElevatorSubsystem.class);
		new JoystickEventMapper();
		new DefaultCommandFactory();

		logServer.runServer();
	}

	@Override
	public void robotPeriodic() {
		SmartDashboard.putNumber("AutoPopulate/PSI Value", drivetrainSubsystem.getPSIValue());
		SmartDashboard.putNumber("LeftEncoder", drivetrainSubsystem.getLeftEncoderDistance());
		SmartDashboard.putNumber("RightEncoder", drivetrainSubsystem.getRightEncoderDistance());
		SmartDashboard.putNumber("Left Ultrasonic Sensor Value", drivetrainSubsystem.getLeftUltraVal());
		SmartDashboard.putNumber("gyroAdjusted", drivetrainSubsystem.getGyroValueAdjusted());
		// SmartDashboard.putNumber("gyroUnadjusted",
		// drivetrainSubsystem.getGyroValueUnadjusted());

		SmartDashboard.putNumber("TX", LimeLight.getTx());
		SmartDashboard.putNumber("TY", LimeLight.getTy());
		SmartDashboard.putNumber("TS", LimeLight.getTs());
	}

	@Override
	public void disabledInit() {
		log.info("Robot program is disabled and ready.");
		robotLogger.moveLogFile();
		robotLogger.flush();
	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void autonomousInit() {
		drivetrainSubsystem.resetAllSensors();
		m_autoSelected = autonSelector.getCommand();
		m_autoSelected.start();
	}

	@Override
	public void autonomousPeriodic() {
		robotLogger.log();
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
		drivetrainSubsystem.resetAllSensors();
		if (m_autoSelected != null) {
			m_autoSelected.cancel();
		}
	}

	@Override
	public void teleopPeriodic() {
		robotLogger.log();
		Scheduler.getInstance().run();
	}

	@Override
	public void testInit() {
		robotLogger.moveLogFile();
	}

	@Override
	public void testPeriodic() {
	}
}
