package com.capitanperegrina.sailraceanalyzer.common.geo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Line extends ElementoEnMapa implements Serializable {
	private static final long serialVersionUID = 8764964615045374661L;

	static final double SMALL_NUM = 0.00000001d;

	protected Point point1;
	protected Point point2;

	public Line() {
	}

	public Line(double lat1, double lon1, double lat2, double lon2) {
		Point a = new Point(lat1, lon1);
		Point b = new Point(lat2, lon2);
		this.point1 = a;
		this.point2 = b;
	}

	public Line(Point p1, Point p2) {
		this.point1 = p1;
		this.point2 = p2;
	}

	public Line(double lat1, double lon1, double lat2, double lon2, String nombre, Integer tipo) {
		Point a = new Point(lat1, lon1);
		Point b = new Point(lat2, lon2);
		this.point1 = a;
		this.point2 = b;
		this.nombre = nombre;
		this.tipo = tipo;
	}

	public Line(Point p1, Point p2, String nombre, Integer tipo) {
		this.point1 = p1;
		this.point2 = p2;
		this.nombre = nombre;
		this.tipo = tipo;
	}

	/**
	 * Extraido de http://polplabs.net/blog/2011/02/03/calcular-si-dos-rectas-se-cortan-entre-sus-limites/
	 * 
	 * @param otraLinea
	 * @return
	 */
	public boolean doIntersect(Line otraLinea) {
		// linea 1 va desde (x1,y1) a (x2,y2)
		double x1 = this.getPoint1().getLongitude();
		double y1 = this.getPoint1().getLatitude();
		double x2 = this.getPoint2().getLongitude();
		double y2 = this.getPoint2().getLatitude();

		// linea 2 va desde (x3,y3) a (x4,y4)
		double x3 = otraLinea.getPoint1().getLongitude();
		double y3 = otraLinea.getPoint1().getLatitude();
		double x4 = otraLinea.getPoint2().getLongitude();
		double y4 = otraLinea.getPoint2().getLatitude();

		double temp;
		// Ordenamos las lineas para que la menor x sea el primer punto
		if (x1 > x2) {
			temp = x2;
			x2 = x1;
			x1 = temp;
			temp = y2;
			y2 = y1;
			y1 = temp;
		}

		if (x3 > x4) {
			temp = x4;
			x4 = x3;
			x3 = temp;
			temp = y4;
			y4 = y3;
			y3 = temp;
		}
		// Ya las tenemos ordenadas

		double pendiente1 = 0;
		double pendiente2 = 0;

		if ((y2 - y1) != 0) {
			pendiente1 = (y2 - y1) / (x2 - x1); // pendiente recta 1
		}

		if ((y3 - y4) != 0) {
			pendiente2 = (y4 - y3) / (x4 - x3); // pendiente recta 2
		}

		double ordenada_en_origen1 = y1 - pendiente1 * x1; // corte con eje y
		// linea 1
		double ordenada_en_origen2 = y3 - pendiente2 * x3; // corte con eje y
		// linea 2

		// puntos de corte entre las lineas
		double corte_x = (ordenada_en_origen2 - ordenada_en_origen1) / (pendiente1 - pendiente2);
		// double corte_y = ( ordenada_en_origen1 + pendiente1 * corte_x );

		// comprobamos si esta en el rango dentro de la recta
		if (x1 <= corte_x && x2 >= corte_x && x3 <= corte_x && x4 >= corte_x) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * <pre>
	 * Obtenemos el punto de la recta perpendicular al punto fuera de la misma.
	 *       |-------- u1 -------|   |--------- u2 --------|
	 *       ( Cx - Ax )*( Bx-Ax ) + ( Cy - Ay )*( By - Ay )
	 * u = ---------------------------------------------------
	 *                 ( Bx - Ax ) + ( By -Ay ) 
	 *                 |--------- u3 ---------| 
	 * 
	 * Cx = Ax + u(Bx-Ax)
	 * Cy = Ay + u(By-Ay)
	 * </pre>
	 * 
	 * @param point
	 * @return
	 */
	public Point closestPoint(Point point) {
		double u1 = (point.getLongitude() - this.getPoint1().getLongitude())
				* (this.getPoint2().getLongitude() - this.getPoint1().getLongitude());

		double u2 = (point.getLatitude() - this.getPoint1().getLatitude())
				* (this.getPoint2().getLatitude() - this.getPoint1().getLatitude());

		double u3 = Math.pow((this.getPoint2().getLongitude() - this.getPoint1().getLongitude()), 2)
				+ Math.pow((this.getPoint2().getLatitude() - this.getPoint1().getLatitude()), 2);

		double u = (u1 + u2) / u3;

		Point p = new Point();
		if (u <= 0) {
			// Si es menor que 0, C ha sobrepasado el segmento por A.
			return this.getPoint1();
		} else if (u >= 1) {
			// Si es mayor que 1, C ha sobrepasado el segmento por B.
			return this.getPoint2();
		} else {
			// Si u es menor que 0, el punto C no ha llegado al segmento.
			// Cx = Ax + u(Bx-Ax)
			p.setLongitude(this.getPoint1().getLongitude()
					+ u * (this.getPoint2().getLongitude() - this.getPoint1().getLongitude()));
			// Cy = Ay + u(By-Ay)
			p.setLatitude(this.getPoint1().getLatitude()
					+ u * (this.getPoint2().getLatitude() - this.getPoint1().getLatitude()));
			return p;
		}
	}

	@Override
	public double distanceInMeters(ElementoEnMapa elemento) {
		if (elemento instanceof Point) {
			return distanceInMeters((Point) elemento);
		} else if (elemento instanceof Line) {
			return distanceInMeters((Line) elemento);
		} else {
			return Double.NaN;
		}
	}

	public double distanceInMeters(Point point) {
		return point.distanceInMeters(this.closestPoint(point));
	}

	public double distanceInMeters(Line otherLine) {
		if (this.doIntersect(otherLine)) {
			return 0d;
		}
		List<Double> lista = new ArrayList<Double>(4);
		lista.add(this.distanceInMeters(otherLine.getPoint1()));
		lista.add(this.distanceInMeters(otherLine.getPoint2()));
		lista.add(otherLine.distanceInMeters(this.getPoint1()));
		lista.add(otherLine.distanceInMeters(this.getPoint2()));
		Collections.sort(lista);
		return lista.get(0);
	}

	public double getCog() {
		int cuadrante = this.getQuadrant();
		double cog = 0;
		if (cuadrante == 1) {
			cog = this.getAngle();
		} else if (cuadrante == 2) {
			cog = 90 + (90 - this.getAngle());
		} else if (cuadrante == 3) {
			cog = 180 + this.getAngle();
		} else {
			cog = 270 + (90 - this.getAngle());
		}

		if (cog > 360) {
			cog = cog - 360;
		}

		return cog;
	}

	private int getQuadrant() {
		double x, y;
		x = this.point2.getLongitude() - this.point1.getLongitude();
		y = this.point2.getLatitude() - this.point1.getLatitude();

		if (x >= 0) {
			if (y >= 0) {
				return 1;
			} else {
				return 2;
			}
		} else {
			if (y >= 0) {
				return 4;
			} else {
				return 3;
			}
		}
	}

	/**
	 * <pre>
	 *                        /       ( v1x * v2x ) + ( v1y * v2y )     \
	 *   angle(v1, v2) = acos | --------------------------------------- |
	 *                        |      _____________       _____________  |
	 *                        |  \  /    2      2    \  /    2      2   |
	 *                        \   \/  v1x  + v1y   *  \/  v2x  + v2y    /
	 * </pre>
	 */
	private double getAngle() {
		double angulo = Math.toDegrees(Math.atan((this.point2.getLongitude() - this.point1.getLongitude())
				/ (this.point2.getLatitude() - this.point1.getLatitude())));
		return Math.abs(angulo);
	}
	
	public Point getMiddlePoint()
	{
		return new Point( 
				( this.point1.getLatitude() + this.point2.getLatitude() ) / 2, 
				( this.point1.getLongitude() + this.point2.getLongitude() ) / 2 ); 
	}

	public Point getPoint1() {
		return point1;
	}

	public void setPoint1(Point point1) {
		this.point1 = point1;
	}

	public Point getPoint2() {
		return point2;
	}

	public void setPoint2(Point point2) {
		this.point2 = point2;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((point1 == null) ? 0 : point1.hashCode());
		result = prime * result + ((point2 == null) ? 0 : point2.hashCode());
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
		Line other = (Line) obj;
		if (point1 == null) {
			if (other.point1 != null)
				return false;
		} else if (!point1.equals(other.point1))
			return false;
		if (point2 == null) {
			if (other.point2 != null)
				return false;
		} else if (!point2.equals(other.point2))
			return false;
		return true;
	}

	@Override
	public String generaString() {
		StringBuffer ret = new StringBuffer();
		ret.append(this.point1.generaString() + " -> " + this.point2.generaString());
		return ret.toString();
	}

	/**
	 * Construye un <code>String</code> con todos los atributos según el
	 * formato "nombre = valor".
	 * 
	 * @return Una representación en forma de <code>String</code> de este
	 *         objeto.
	 */
	@Override
	public String toString() {
		StringBuffer retValue = new StringBuffer();
		retValue.append("Line : (" + this.getPoint1().getLongitude() + ", " + this.getPoint1().getLatitude() + ")-("
				+ this.getPoint2().getLongitude() + ", " + this.getPoint2().getLatitude() + ")");
		return retValue.toString();
	}

	/*
	 * public static void main(String[] args) { Point salida = new Point(40,
	 * -8);
	 * 
	 * Point llegada1 = new Point(41, -6); Point llegada2 = new Point(39, -6);
	 * Point llegada3 = new Point(39, -10); Point llegada4 = new Point(41, -10);
	 * Point llegadaN = new Point(41, -8); Point llegadaE = new Point(40, -7);
	 * Point llegadaS = new Point(39, -8); Point llegadaW = new Point(40, -9);
	 * 
	 * Line line1 = new Line(salida, llegada1); Line line2 = new Line(salida,
	 * llegada2); Line line3 = new Line(salida, llegada3); Line line4 = new
	 * Line(salida, llegada4); Line lineN = new Line(salida, llegadaN); Line
	 * lineE = new Line(salida, llegadaE); Line lineS = new Line(salida,
	 * llegadaS); Line lineW = new Line(salida, llegadaW);
	 * 
	 * System.out.println(salida.toString() + " a " + llegada1.toString() +
	 * " - COG " + line1.getCog() + " - Cuadrante: " + line1.getQuadrant() +
	 * " - " + line1.getAngle()); System.out.println(salida.toString() + " a " +
	 * llegada2.toString() + " - COG " + line2.getCog() + " - Cuadrante: " +
	 * line2.getQuadrant() + " - " + line2.getAngle());
	 * System.out.println(salida.toString() + " a " + llegada3.toString() +
	 * " - COG " + line3.getCog() + " - Cuadrante: " + line3.getQuadrant() +
	 * " - " + line3.getAngle()); System.out.println(salida.toString() + " a " +
	 * llegada4.toString() + " - COG " + line4.getCog() + " - Cuadrante: " +
	 * line4.getQuadrant() + " - " + line4.getAngle());
	 * System.out.println(salida.toString() + " a " + llegadaN.toString() +
	 * " - COG " + lineN.getCog() + " - Cuadrante: " + lineN.getQuadrant() +
	 * " - " + lineN.getAngle()); System.out.println(salida.toString() + " a " +
	 * llegadaE.toString() + " - COG " + lineE.getCog() + " - Cuadrante: " +
	 * lineE.getQuadrant() + " - " + lineE.getAngle());
	 * System.out.println(salida.toString() + " a " + llegadaS.toString() +
	 * " - COG " + lineS.getCog() + " - Cuadrante: " + lineS.getQuadrant() +
	 * " - " + lineS.getAngle()); System.out.println(salida.toString() + " a " +
	 * llegadaW.toString() + " - COG " + lineW.getCog() + " - Cuadrante: " +
	 * lineW.getQuadrant() + " - " + lineW.getAngle());
	 * 
	 * } //
	 */
}