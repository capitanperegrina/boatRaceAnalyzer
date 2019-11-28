package com.capitanperegrina.sailraceanalyzer.common.geo;

public abstract class ElementoEnMapa
{
	protected String	nombre;
	protected Integer	tipo;

	public abstract double distanceInMeters( ElementoEnMapa otherPoint );

	public abstract String generaString();

	public String getNombre()
	{
		return nombre;
	}

	public void setNombre( String nombre )
	{
		this.nombre = nombre;
	}

	public Integer getTipo()
	{
		return tipo;
	}

	public void setTipo( Integer tipo )
	{
		this.tipo = tipo;
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ( ( nombre == null ) ? 0 : nombre.hashCode() );
		result = prime * result + ( ( tipo == null ) ? 0 : tipo.hashCode() );
		return result;
	}

	@Override
	public boolean equals( Object obj )
	{
		if ( this == obj ) return true;
		if ( obj == null ) return false;
		if ( getClass() != obj.getClass() ) return false;
		ElementoEnMapa other = (ElementoEnMapa) obj;
		if ( nombre == null )
		{
			if ( other.nombre != null ) return false;
		}
		else if ( !nombre.equals( other.nombre ) ) return false;
		if ( tipo == null )
		{
			if ( other.tipo != null ) return false;
		}
		else if ( !tipo.equals( other.tipo ) ) return false;
		return true;
	}

	@Override
	public String toString() {
		return "ElementoEnMapa [nombre=" + nombre + ", tipo=" + tipo + "]";
	}

}
