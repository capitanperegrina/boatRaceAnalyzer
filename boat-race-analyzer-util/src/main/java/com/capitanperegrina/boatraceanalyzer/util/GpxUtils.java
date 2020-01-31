package com.capitanperegrina.boatraceanalyzer.util;

import java.io.File;
import java.io.IOException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.capitanperegrina.gpx.elements.GpxType;

import io.jenetics.jpx.GPX;

public class GpxUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(GpxUtils.class);
    
    private GpxUtils() {
        
    }
    
    public static GPX read(String filename) {
    	return read(filename, GPX.Version.V11);
    }
    
    public static GPX read(String filename, GPX.Version version) {
    	try {
    		return GPX.reader(version).read(filename);    		
    	} catch ( IOException e ) {
    		LOGGER.error("{}",e);
    		throw new RuntimeException(e);
    	}
    }

    public static void write(GPX gpx, String filename) {
    	write(gpx, filename, GPX.Version.V11);
    }
    
    public static void write(GPX gpx, String filename, GPX.Version version) {
    	try {
    		final GPX gpxDest = gpx.toBuilder().version(version).build();    		
    		GPX.write(gpxDest, filename);    		
    	} catch ( IOException e ) {
    		LOGGER.error("{}",e);
    		throw new RuntimeException(e);
    	}
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
