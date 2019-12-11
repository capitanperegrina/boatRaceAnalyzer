package com.capitanperegrina.gpx.service.impl;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.capitanperegrina.gpx.elements.GpxType;
import com.capitanperegrina.gpx.service.IGpxParser;

@Service
public class GpxParserImpl implements IGpxParser {

	private static final Logger LOGGER = LoggerFactory.getLogger(GpxParserImpl.class);

	@Override
	@SuppressWarnings("unchecked")
	public final GpxType parse(String filename) {
		try {
			JAXBContext jc = JAXBContext.newInstance("com.capitanperegrina.gpx.elements");
			Unmarshaller unmarshaller = jc.createUnmarshaller();
			JAXBElement<GpxType> root = (JAXBElement<GpxType>) unmarshaller.unmarshal(
					new File(filename));
			return root.getValue();
		} catch (JAXBException ex) {
			LOGGER.error("Can't parse {} ()", filename, ex.getMessage());
			throw new RuntimeException("Can't parse " + filename + "(" + ex.getMessage() + ")", ex);
		}
	}
}
