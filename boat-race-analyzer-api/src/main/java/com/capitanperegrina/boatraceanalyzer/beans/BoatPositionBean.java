package com.capitanperegrina.boatraceanalyzer.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.capitanperegrina.geo.elements.Point;

public class BoatPositionBean implements Serializable {

	private static final long serialVersionUID = -2734667926218558496L;

	Date tiempo;
	Point position;
	BigDecimal sog;
	BigDecimal cog;
	BigDecimal vmg;
	BigDecimal dtd;

	public BoatPositionBean() {
		super();
	}

	public Date getTiempo() {
		return tiempo;
	}

	public void setTiempo(Date tiempo) {
		this.tiempo = tiempo;
	}

	public Point getPosition() {
		return position;
	}

	public void setPosition(Point position) {
		this.position = position;
	}

	public BigDecimal getSog() {
		return sog;
	}

	public void setSog(BigDecimal sog) {
		this.sog = sog;
	}

	public BigDecimal getCog() {
		return cog;
	}

	public void setCog(BigDecimal cog) {
		this.cog = cog;
	}

	public BigDecimal getVmg() {
		return vmg;
	}

	public void setVmg(BigDecimal vmg) {
		this.vmg = vmg;
	}

	public BigDecimal getDtd() {
		return dtd;
	}

	public void setDtd(BigDecimal dtd) {
		this.dtd = dtd;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("BoatPositionBean [tiempo=").append(tiempo).append(", position=").append(position)
				.append(", sog=").append(sog).append(", cog=").append(cog).append(", vmg=").append(vmg).append(", dtd=")
				.append(dtd).append("]");
		return builder.toString();
	}

}
