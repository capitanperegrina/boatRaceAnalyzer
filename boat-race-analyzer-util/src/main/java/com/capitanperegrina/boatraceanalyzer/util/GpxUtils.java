package com.capitanperegrina.boatraceanalyzer.util;

import java.io.File;

import javax.xml.bind.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.capitanperegrina.gpx.elements.GpxType;

public class GpxUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(GpxUtils.class);
    
    private GpxUtils() {
        
    }
    
    @SuppressWarnings("unchecked")
    public static final GpxType parse(String filename) {
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
    
    public static final void write(GpxType gpx, String fileNameOut) {
        try {
            JAXBContext jc = JAXBContext.newInstance("com.capitanperegrina.gpx.elements");
            Marshaller marshaller = jc.createMarshaller();
            marshaller.marshal(gpx, new File(fileNameOut));
        } catch (JAXBException ex) {
            LOGGER.error("Can't write {} ()", fileNameOut, ex.getMessage());
            throw new RuntimeException("Can't write " + fileNameOut + "(" + ex.getMessage() + ")", ex);
        }
    }
    
}
