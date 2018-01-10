package org.usfirst.frc.team2175.property;

public class BotProperty extends BaseProperties {

	private boolean isComp;

	@Override
	protected String getPropertyFileName() {
		return "bot.properties";
	}

	@Override
	protected void populate() {
		isComp = getBooleanPropertyValue("isComp");
	}

	public boolean getIsComp() {
		return isComp;
	}
}
