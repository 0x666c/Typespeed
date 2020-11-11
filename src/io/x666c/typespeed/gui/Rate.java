package io.x666c.typespeed.gui;

public class Rate {

	private double normalizedRate; // event rate / window
	private long windowSizeTicks;
	private long lastEventTicks;

	public Rate() {
		windowSizeTicks = 1000;
		lastEventTicks = System.nanoTime();
	}

	public double newEvent() {
		long currentTicks = System.nanoTime();
		long period = currentTicks - lastEventTicks;
		lastEventTicks = currentTicks;
		double normalizedFrequency = (double) windowSizeTicks / (double) period;

		double alpha = Math.min(1.0 / normalizedFrequency, 1.0);
		normalizedRate = (alpha * normalizedFrequency) + ((1.0 - alpha) * normalizedRate);
		
		return getRate();
	}

	public double getRate() {
		return normalizedRate * 1000L / windowSizeTicks;
	}
}
