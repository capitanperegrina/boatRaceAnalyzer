package com.capitanperegrina.sailraceanalyzer.common.geo;

import com.capitanperegrina.sailraceanalyzer.common.util.Nautical;

public class Locations {
    // BALIZAS
    public final static Point CAMOUCO = new Point(42.396667, -8.911667, "Camouco", Nautical.TIPO_BALIZA_BABOR);
    public final static Point PICAMILLO = new Point(42.404400, -8.890700, "Picamillo", Nautical.TIPO_BALIZA_ESTRIBOR);
    public final static Point TAMBO = new Point(42.406750, -8.710017, "Tambo", Nautical.TIPO_BALIZA_BOYA_AMARILLA);
    public final static Point PORTOCELO = new Point(42.393433, -8.718833, "Portocelo", Nautical.TIPO_BALIZA_BOYA_AMARILLA);
    public final static Point PELADOS = new Point(42.3838833333333, -8.74073333333333, "Pelados", Nautical.TIPO_BALIZA_LATERAL_BABOR);
    public final static Point MOURISCA = new Point(42.3479, -8.82003333333333, "Mourisca", Nautical.TIPO_BALIZA_ESTRIBOR);
    public final static Point MORRAZAN = new Point(42.3728667, -8.7836, "Morrazán", Nautical.TIPO_BALIZA_BABOR);
    public final static Point ESQUEIROS = new Point(42.51181666667, -8.93901666667, "Esqueiros", Nautical.TIPO_BALIZA_LATERAL_BABOR);
    public final static Point MEZOS = new Point( new Coordenada( 42, 30.8665 ).getDecimalValue(), new Coordenada( -8, -55.8226 ).getDecimalValue(), "Mezos", Nautical.TIPO_BALIZA_ESTRIBOR);
    public final static Point POMBEIRO = new Point( new Coordenada( 42, 28.9281 ).getDecimalValue(), new Coordenada( -8, -56.8263 ).getDecimalValue(), "Pombeiro", Nautical.TIPO_BALIZA_ESTRIBOR );
    public final static Point FAGILDA = new Point( new Coordenada( 42, 24.7555 ).getDecimalValue(), new Coordenada( -8, -53.6715 ).getDecimalValue(), "Fagilda", Nautical.TIPO_BALIZA_BABOR );
    
    // PUERTOS
    public final static Point SANXENXO = new Point(42.395822, -8.799872);
    public final static Point PORTONOVO = new Point(42.394602, -8.817923);
    public final static Point SAN_VICENTE_DO_MAR = new Point(42.456834, -8.917104);
    public final static Point ONS = new Point(42.376962, -8.928278);
    public final static Point BELUSO = new Point(42.333467, -8.797339);
    public final static Point AGUETE = new Point(42.377596, -8.733346);
    public final static Point RAXO = new Point(42.401622, -8.754026);
    public final static Point COMBARRO = new Point( new Coordenada( 42, 26, 47 ).getDecimalValue(), new Coordenada( -8, -42, -18 ).getDecimalValue() );
}