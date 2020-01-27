package com.capitanperegrina.estela;

import java.math.BigDecimal;
import java.util.Date;

public class EstelaCSV {

    public static final int TIME = 0;
    public static final int LATITUDE = 1;
    public static final int LONGITUDE = 2;
    public static final int SPEED_OVER_GROUND = 3;
    public static final int COURSE_OVER_GROUND = 4;
    
	private Date time;
	private BigDecimal lat;
	private BigDecimal lon;
	private BigDecimal sog;
	private BigDecimal cog;

	public EstelaCSV(Date time, BigDecimal lat, BigDecimal lon, BigDecimal sog, BigDecimal cog) {
		super();
		this.time = time;
		this.lat = lat;
		this.lon = lon;
		this.sog = sog;
		this.cog = cog;
	}

	public Date getTime() {
		return this.time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public BigDecimal getLat() {
		return this.lat;
	}

	public void setLat(BigDecimal lat) {
		this.lat = lat;
	}

	public BigDecimal getLon() {
		return this.lon;
	}

	public void setLon(BigDecimal lon) {
		this.lon = lon;
	}

	public BigDecimal getSog() {
		return this.sog;
	}

	public void setSog(BigDecimal sog) {
		this.sog = sog;
	}

	public BigDecimal getCog() {
		return this.cog;
	}

	public void setCog(BigDecimal cog) {
		this.cog = cog;
	}

	@Override
	public String toString() {
		return "EstelaCSV [time=" + this.time + ", lat=" + this.lat + ", lon=" + this.lon + ", sog=" + this.sog
				+ ", cog=" + this.cog + "]";
	}

}
