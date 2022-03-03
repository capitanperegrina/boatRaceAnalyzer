package com.capitanperegrina.boatraceanalyzer.util;

import com.capitanperegrina.boatraceanalyzer.exceptions.ServiceErrorException;
import org.apache.commons.io.FileUtils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Base64;

public class ExtendedFileUtils {

    private ExtendedFileUtils() {
        // empty and private as it's an utils class.
    }

    public static String image2base64(final String filePath) {
        try {
            final byte[] fileContent = FileUtils.readFileToByteArray(new File(filePath));
            return Base64.getEncoder().encodeToString(fileContent);
        } catch (final IOException ioe) {
            throw new ServiceErrorException(ioe);
        }
    }

    public static final void writeFile(final String content, final String fileName) {
        try (final BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(content);
        } catch (final IOException ioe) {
            throw new ServiceErrorException(ioe);
        }
    }

}
