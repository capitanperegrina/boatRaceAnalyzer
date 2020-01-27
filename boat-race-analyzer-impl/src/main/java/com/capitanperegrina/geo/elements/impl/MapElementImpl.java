package com.capitanperegrina.geo.elements.impl;

import java.io.Serializable;

import com.capitanperegrina.boatraceanalyzer.enums.MapElementType;
import com.capitanperegrina.geo.elements.MapElement;
import com.capitanperegrina.geo.elements.Point;

public abstract class MapElementImpl implements MapElement, Serializable {

	private static final long serialVersionUID = 4826351660869399141L;

	protected String name;
	protected MapElementType type;
	
	public MapElementImpl() {
		super();
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public MapElementType getType() {
		return type;
	}

	@Override
	public void setType(MapElementType type) {
		this.type = type;
	}


	@Override
	public abstract Point centralPosition();

	@Override
	public abstract double distanceInMeters(MapElement otherElement);

	@Override
	public abstract Point cornerNW();

	@Override
	public abstract Point cornerNE();

	@Override
	public abstract Point cornerSW();

	@Override
	public abstract Point cornerSE();

	@Override
	public abstract String buildString();
	
	@Override
	public abstract MapElement rotate(double angulo, Point anchor);

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MapElementImpl other = (MapElementImpl) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (type != other.type)
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("MapElementImpl [name=").append(name).append(", type=").append(type).append("]");
		return builder.toString();
	}
}
