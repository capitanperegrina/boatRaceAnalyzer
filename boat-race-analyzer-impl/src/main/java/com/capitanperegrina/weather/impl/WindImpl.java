package com.capitanperegrina.weather.impl;

import java.io.Serializable;

import com.capitanperegrina.weather.elements.Wind;

public class WindImpl implements Wind, Serializable {

	private static final long serialVersionUID = 4623744615627360192L;
	
	private double direction;
	private double intensity;
    
    public WindImpl() {
    }

	@Override
	public double getDirection() {
		return direction;
	}

	@Override
	public void setDirection(double direction) {
		this.direction = direction;
	}

	@Override
	public double getIntensity() {
		return intensity;
	}

	@Override
	public void setIntensity(double intensity) {
		this.intensity = intensity;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("WindImpl [direction=").append(direction).append(", intensity=").append(intensity).append("]");
		return builder.toString();
	}
}
