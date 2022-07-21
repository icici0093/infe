package com.infe.common;

import java.util.*;

public class ListMapComparator implements Comparator<Map<String, Object>> {
    private String keyNm;
    public ListMapComparator(String keyNm) {
        this.keyNm = keyNm;
    }
    public int compare(Map<String, Object> map1, Map<String, Object> map2) {
        String value1 = String.valueOf(map1.get(keyNm));
        String value2 = String.valueOf(map2.get(keyNm));
        return value1.compareTo(value2);
    }
}

