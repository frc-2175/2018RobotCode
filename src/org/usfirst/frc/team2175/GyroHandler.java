package org.usfirst.frc.team2175;

import java.util.HashMap;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.SPI;

public class GyroHandler {
	private AHRS navx;
	private double degrees;
	private boolean startPredicting;

	private boolean lsrl;
	private double startTime;
	private double lastEnterTime;
	private double lastRetrieveTime;

	private double m;
	private double b;

	private HashMap<Double, Double> gyroVals;

	public GyroHandler() {
		navx = new AHRS(SPI.Port.kMXP);
		navx.reset();
		startPredicting = false;
	}

	public void reset() {
		navx.reset();
	}

	public double getAngle() {
		double angle = navx.getAngle();
		if (angle * 4 / 3 > degrees) {
			if (lsrl) {
				lastRetrieveTime = System.currentTimeMillis() / 1000 - 0.31;
				lsrl = false;
			}
			double time = System.currentTimeMillis() / 1000;
			if (time > lastRetrieveTime + 0.5) {
				linearRegression();
			}

			// y = mx + b
			return m * (time - startTime) + b;
		}
		if (angle > degrees / 10) {
			if (startPredicting) {
				gyroVals = new HashMap<Double, Double>();

				lastEnterTime = System.currentTimeMillis() / 1000;

				gyroVals.put(lastEnterTime, angle);
				startPredicting = false;
				lsrl = true;
			}
			double time = System.currentTimeMillis() / 1000;
			if (time > lastEnterTime + 0.2) {
				gyroVals.put(time, angle);
				lastEnterTime = time;
			}
		}
		return angle;
	}

	private void linearRegression() {
		double tsum = 0;
		double gsum = 0;
		for (Double t : gyroVals.keySet()) {
			tsum += t;
		}
		for (Double g : gyroVals.values()) {
			gsum += g;
		}
		int size = gyroVals.size();
		double tmean = tsum / size;
		double gmean = gsum / size;

		double num = 0;
		double den = 0;
		for (Double t : gyroVals.keySet()) {
			double g = gyroVals.get(t);
			num += (t - tmean) * (g - gmean);
			den += (t - tmean) * (t - tmean);
		}

		m = num / den;
		b = gmean - m * tmean;
	}

	public double getAngleRadians() {
		return Math.toRadians(navx.getAngle());
	}

	public void giveRadians(double radians) {
		degrees = Math.toDegrees(radians);
		startPredicting = true;
		startTime = System.currentTimeMillis() / 1000;
	}

}
