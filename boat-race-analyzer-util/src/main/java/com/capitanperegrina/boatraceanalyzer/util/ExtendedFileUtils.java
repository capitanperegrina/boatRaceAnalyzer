package com.capitanperegrina.boatraceanalyzer.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Base64;

import org.apache.commons.io.FileUtils;

import com.capitanperegrina.boatraceanalyzer.exceptions.ServiceErrorException;

public class ExtendedFileUtils {

	public static String image2base64(String filePath) {
		try {
			byte[] fileContent = FileUtils.readFileToByteArray(new File(filePath));
			return Base64.getEncoder().encodeToString(fileContent);			
		} catch ( IOException ioe ) {
			throw new ServiceErrorException(ioe);
		}
	}
	
	public static final void writeFile(String content, String fileName) {
		try ( BufferedWriter writer = new BufferedWriter(new FileWriter(fileName)) ) {
			writer.write(content);
			writer.close();			
		} catch ( IOException ioe ) {
			throw new ServiceErrorException(ioe);
		}
	}

}
