package com.capitanperegrina.gpx.service;

import com.capitanperegrina.gpx.elements.GpxType;

public interface IGpxParser {

	GpxType parse(String file);
}
