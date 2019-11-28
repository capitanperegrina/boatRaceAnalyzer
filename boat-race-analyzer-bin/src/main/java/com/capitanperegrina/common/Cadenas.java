package com.capitanperegrina.common;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.capitanperegrina.sailraceanalyzer.common.Globals;

public class Cadenas
{
	private Cadenas()
	{
	}

	public static String toStringGenerico( final Object[] valor )
	{
		String ret = "";
		final StringBuilder sb = new StringBuilder();
		if ( valor != null )
		{
			for ( final Object o : valor )
			{
				sb.append( Cadenas.toStringGenerico( o ) ).append( "|" );
			}
			ret = sb.toString();
			ret = ret.substring( 0, ret.lastIndexOf( "|" ) );
		}
		return ret;
	}

	public static String toStringGenerico( final Object valor )
	{
		try
		{
			if ( valor != null )
			{
				if ( valor instanceof Calendar )
				{
					return Globals.DATE_TIME_FORMATTER.format( valor);
				}
				if ( valor instanceof HashMap )
				{
					final Map<Object, Object> mapa = (Map<Object, Object>) valor;
					final StringBuilder str = new StringBuilder();
					for ( final Entry<Object, Object> e : mapa.entrySet() )
					{
						str.append( Cadenas.toStringGenerico( e.getKey() ) ).append( " -> " ).append( Cadenas.toStringGenerico( e.getValue() ) ).append( "\n" );

					}
					return str.toString();
				}
				return valor.toString();
			}
			else
			{
				return "null";
			}
		}
		catch ( final Exception e )
		{
			return "null_err";
		}
	}
}
