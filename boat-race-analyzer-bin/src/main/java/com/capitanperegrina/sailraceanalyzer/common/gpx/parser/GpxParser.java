package com.capitanperegrina.sailraceanalyzer.common.gpx.parser;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.capitanperegrina.sailraceanalyzer.common.gpx.GpxType;

public class GpxParser {

	public static final Logger LOGGER = LoggerFactory.getLogger(GpxParser.class);

	@SuppressWarnings("unchecked")
	public static final GpxType parse(String file) {
		try {
			JAXBContext jc = JAXBContext.newInstance("com.capitanperegrina.sailraceanalyzer.common.gpx");
			Unmarshaller unmarshaller = jc.createUnmarshaller();
			JAXBElement<GpxType> root = (JAXBElement<GpxType>) unmarshaller.unmarshal(
					new File(file));
			return root.getValue();
		} catch (JAXBException ex) {
			LOGGER.error("No se puede parsear {} ()", file, ex.getMessage());
			throw new RuntimeException("No se puede parsear " + file + "(" + ex.getMessage() + ")", ex);
		}
	}
}
