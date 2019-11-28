package com.capitanperegrina.common;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Clase utilidad para procesar ficheros
 */
public class Ficheros {
	
	public static final Logger LOGGER = LoggerFactory.getLogger(Ficheros.class);

	private Ficheros() {
	}

	/**
	 * Guardar una cadena en un fichero
	 *
	 * @param nombreFichero
	 *            nombre del fichero
	 * @param contenido
	 *            contenido a guardar en el fichero
	 */
	public static void string2File(String nombreFichero, String contenido) {
		try {
			final File outFile = new File(nombreFichero);
			final FileWriter out = new FileWriter(outFile);
			out.write(contenido);
			out.close();
		} catch (final IOException e) {
			LOGGER.error("", e);
			throw new RuntimeException("Ficheros.string2File.IOException");
		}

	}

	/**
	 * Carga el contenido de un fichero en un string.
	 *
	 * @param pathFichero
	 *            fichero del que cargar.
	 * @return String cadena con el contenido del fichero.
	 */
	public static String file2String(String pathFichero) {
		final StringBuilder salida = new StringBuilder("");

		final File fichero = new File(pathFichero);

		if (!fichero.exists()) {
			LOGGER.warn("El fichero " + pathFichero + "no existe.");
		} else {
			if (!fichero.canRead()) {
				LOGGER.warn("El fichero " + pathFichero + "no se puede leer.");
			} else {
				salida.append(lee(fichero));
			}
		}
		return salida.toString();

	}

	private static String lee(File fichero) {
		try {
			final StringBuilder leido = new StringBuilder("");
			final FileReader reader = new FileReader(fichero);
			int caracter = reader.read();
			while (caracter >= 0) {
				final char charCaracter = (char) caracter;
				leido.append(charCaracter);
				caracter = reader.read();
			}
			reader.close();

			return leido.toString();
		} catch (final FileNotFoundException e) {
			LOGGER.error("", e);
			throw new RuntimeException("Ficheros.lee.FileNotFoundException");
		} catch (final IOException e) {
			LOGGER.error("", e);
			throw new RuntimeException("Ficheros.lee.IOException");
		}
	}

	/**
	 * Carga el contenido de un fichero en un string.
	 *
	 * @param url
	 *            url del fichero del que cargar.
	 * @return String cadena con el contenido del fichero.
	 */
	public static String file2String(URL url) {
		try {
			final StringBuilder sb = new StringBuilder();
			final BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));

			String inputLine;
			while ((inputLine = in.readLine()) != null) {
				sb.append(inputLine);
			}
			in.close();

			return sb.toString();
		} catch (final IOException e) {
			LOGGER.error("", e);
			throw new RuntimeException("Ficheros.file2String.IOException");
		}
	}
}
