package com.capitanperegrina.sailraceanalyzer.common.math;

import com.capitanperegrina.sailraceanalyzer.common.geo.Point;

public class Vector
{
	private double x;
	private double y;
	
	public double modulo()
	{
		return Math.sqrt( this.x * this.x + this.y * this.y );
	}
	
	public Vector substract( Vector otherVector )
	{
		Vector p = new Vector();
		p.x = this.x - otherVector.x;
		p.y = this.y - otherVector.y;
		return p;
	}
		
	public Vector add( Vector otherVector )
	{
		Vector p = new Vector();
		p.y = this.y + otherVector.y;
		p.x = this.x + otherVector.x;
		return p;
	}
	
	public Vector times( double scalar )
	{
		Vector p = new Vector();
		p.y = this.y * scalar;
		p.x = this.x * scalar;
		return p;
	}

	public double getX()
	{
		return x;
	}

	public void setX( double x )
	{
		this.x = x;
	}

	public double getY()
	{
		return y;
	}

	public void setY( double y )
	{
		this.y = y;
	}
	
	public Point toPoint()
	{
		Point p = new Point();
		p.setLatitude( this.y );
		p.setLongitude( this.x );
		return p;
	}
	
	public static double dot( Vector u, Vector v )
	{
		return u.x * v.x + u.y * v.y;
	}
	
	/**
	 * Construye un <code>String</code> con todos los atributos
	 * según el formato "nombre = valor".
	 *
	 * @return Una representación en forma de <code>String</code>
	 * de este objeto.
	 */
	@Override
	public String toString()
	{
		StringBuffer retValue = new StringBuffer();
		retValue.append("( " + this.x + ", " + this.y + ")");
		return retValue.toString();
	}		
}