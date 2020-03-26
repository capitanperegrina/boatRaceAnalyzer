package com.capitanperegrina.boatraceanalyzer.enums;

import java.util.HashMap;
import java.util.Map;

public enum MapType {

	OPEN_STREET_MAP(1),
	NAVIONICS(2);
	
    private Integer value;
    private static Map<Integer,MapType> map = new HashMap<>();

    private MapType(int value) {
        this.value = value;
    }

    static {
        for (MapType mapType : MapType.values()) {
            map.put(mapType.value, mapType);
        }
    }

    public static MapType valueOf(Integer mapType) {
        return map.get(mapType);
    }

    public Integer getValue() {
        return this.value;
    }
}
