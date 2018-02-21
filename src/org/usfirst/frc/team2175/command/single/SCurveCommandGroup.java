package org.usfirst.frc.team2175.command.single;

import org.usfirst.frc.team2175.command.BaseCommandGroup;

public class SCurveCommandGroup extends BaseCommandGroup {

	public SCurveCommandGroup(double dx, double dy, double r) {
		double sign = Math.signum(dx);
		dx = Math.abs(dx);

		double h = Math.sqrt(dy * dy + (dx - 2 * r) * (dx - 2 * r)) / 2;

		double f = Math.sqrt(h * h - r * r);
		double d = 2 * f;

		double phi = Math.atan(dy / r);
		double mu = Math.atan(f / r);
		double theta = Math.PI - phi - mu;

		addSequential(new DriveCurve(sign * r, Math.toDegrees(theta), 1, true, false));
		addSequential(new DriveStraightCommand(.7, d, false, false));
		addSequential(new DriveCurve(-sign * r, Math.toDegrees(theta), 1, false, true));
	}

}
