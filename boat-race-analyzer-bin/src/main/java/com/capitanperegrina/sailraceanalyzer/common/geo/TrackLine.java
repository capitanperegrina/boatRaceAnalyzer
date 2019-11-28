package com.capitanperegrina.sailraceanalyzer.common.geo;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import com.capitanperegrina.common.Cadenas;
import com.capitanperegrina.sailraceanalyzer.common.Globals;
import com.capitanperegrina.sailraceanalyzer.common.gpx.WptType;
import com.capitanperegrina.sailraceanalyzer.common.util.Nautical;
import com.capitanperegrina.sailraceanalyzer.common.weather.Wind;

public class TrackLine extends Line implements Serializable
{
	private static final long	serialVersionUID	= -198202301694376025L;
	
	private Date				timeIni;
	private Date				timeFin;
	private Wind				viento;

	
	
	public TrackLine( WptType wp1, WptType wp2 )
	{
		super( wp1.getLat().doubleValue(), wp1.getLon().doubleValue(), wp2.getLat().doubleValue(), wp2.getLon().doubleValue() );
		this.timeIni = wp1.getTime().toGregorianCalendar().getTime();
		this.timeFin = wp2.getTime().toGregorianCalendar().getTime();
		this.viento = new Wind();
		this.viento.setIntensidad( 0 );
		this.viento.setRumbo( 0 );
	}

	public Date getTimeIni()
	{
		return timeIni;
	}

	public void setTimeIni( Date timeIni )
	{
		this.timeIni = timeIni;
	}

	public Date getTimeFin()
	{
		return timeFin;
	}

	public void setTimeFin( Date timeFin )
	{
		this.timeFin = timeFin;
	}

	public Date getTiempo()
	{
		long diff = Math.abs( this.timeFin.getTime() - this.timeIni.getTime() );
		return new Date( diff );
	}

	public Wind getViento()
	{
		return viento;
	}

	public void setViento( Wind viento )
	{
		this.viento = viento;
	}

	public String getTiempoString()
	{
		Date tiempo = this.getTiempo();
		Calendar data = Calendar.getInstance();
		data.setTime( tiempo );
		return Globals.TIME_FORMATTER.format(data.getTime());
	}

	public String getTimeIniString()
	{
		Calendar data = Calendar.getInstance();
		data.setTime( this.timeIni );
		data.add( Calendar.HOUR, 1 );
		return Globals.TIME_FORMATTER.format(data.getTime());
	}

	public String getTimeFinString()
	{
		Calendar data = Calendar.getInstance();
		data.setTime( this.timeFin );
		data.add( Calendar.HOUR, 1 );
		return Globals.TIME_FORMATTER.format(data.getTime());
	}

	public double getVmg( ElementoEnMapa destino )
	{
		double distanciaInicial = this.getPoint1().distanceInMeters( destino );
		double distanciaFinal = this.getPoint2().distanceInMeters( destino );
		double distanciaRecorrida = distanciaInicial - distanciaFinal;
		distanciaRecorrida = Nautical.fromMeters( distanciaRecorrida );
		double tiempo = this.getTiempo().getTime();
		tiempo = tiempo / ( 1000 * 60 * 60 );
		if ( tiempo > 0 )
		{
			return distanciaRecorrida / tiempo;
		}
		else
		{
			return Double.NaN;
		}
	}

	public double getCmg( ElementoEnMapa destino )
	{
		Line cmg = null;
		if ( destino instanceof Point )
		{
			cmg = new Line( this.point1, (Point) destino );
		}
		else
		{
			cmg = new Line( this.point1, ( (Line) destino ).closestPoint( this.point1 ) );
		}
		return cmg.getCog();
	}

	public double getSog()
	{
		double distancia = this.getPoint1().distanceInMeters( this.getPoint2() );
		distancia = Nautical.fromMeters( distancia );
		double tiempo = this.getTiempo().getTime();
		tiempo = tiempo / ( 1000 * 60 * 60 );
		if ( tiempo > 0 )
		{
			return distancia / tiempo;
		}
		else
		{
			return Double.NaN;
		}
	}

	public String toUnl()
	{
		StringBuffer retValue = new StringBuffer();
		retValue.append( this.getPoint1().getLongitude() + "|" + this.getPoint1().getLatitude() + "|" + this.getTimeIniString() + "|" + this.getPoint2().getLongitude() + "|" + this.getPoint2().getLatitude() + "|" + this.getTimeFinString() + "|" + this.getTiempoString() + "|" + this.getPoint1().distanceInMeters( this.getPoint2() ) + "|" + this.getCog() + "|" + this.getSog() + "|" + Nautical.formateaDistancia( this.getSog() ) + "|"

				+ this.getViento().getIntensidad() + "|" + this.getViento().getRumbo() );
		return retValue.toString();
	}

	/**
	 * Construye un <code>String</code> con todos los atributos según el
	 * formato "nombre = valor".
	 *
	 * @return Una representación en forma de <code>String</code> de este
	 *         objeto.
	 */
	@Override
	public String toString()
	{
		StringBuffer retValue = new StringBuffer();
		retValue.append( "TrackLine : " ).append( super.toString() );
		retValue.append( "\n" ).append( "timeIni = " ).append( Cadenas.toStringGenerico( this.timeIni ) );
		retValue.append( "\n" ).append( "timeFin = " ).append( Cadenas.toStringGenerico( this.timeFin ) );
		retValue.append( "\n" ).append( "viento = " ).append( Cadenas.toStringGenerico( this.viento ) );
		return retValue.toString();
	}
}
