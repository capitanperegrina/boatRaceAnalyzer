package com.capitanperegrina.sailraceanalyzer.common.geo;

public class Coordenada 
{
	private double grados;
	private double minutos;
	private double segundos;
	
	public Coordenada( double grados )
	{
		super();
		this.grados = grados;
		this.minutos = 0;
		this.segundos = 0;
	}
	
	public Coordenada( double grados, double minutos )
	{
		super();
		this.grados = grados;
		this.minutos = minutos;
		this.segundos = 0;
	}
	
	public Coordenada( double grados, double minutos, double segundos )
	{
		super();
		this.grados = grados;
		this.minutos = minutos;
		this.segundos = segundos;
	}
	
	public double getDecimalValue()
	{ 	
		double min = this.minutos + ( this.segundos / 60 );
		double ret = this.grados + ( min / 60 );
		return ret; 
	}
	
	/*
	public static void main( String[] args )
	{
		LatLongUtils valor = new LatLongUtils( 42, 4, 15.3 );
		System.out.println( valor.getDecimalValue() );
	}
	*/
}
