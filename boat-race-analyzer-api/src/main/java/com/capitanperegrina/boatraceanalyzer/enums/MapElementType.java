package com.capitanperegrina.boatraceanalyzer.enums;

public enum MapElementType {
	POINT(1),
	LINE(2),
	SORTED_ROUTE(3),
	TRACK_LINE_SEGMENT(4);
	
    private final int value;

	MapElementType(final int newValue) {
        value = newValue;
    }

    public int getValue() { return value; }	
}
