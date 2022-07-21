package com.infe.common;

import java.util.*;

public class SortUtil {
    public static void sort(List<Map<String, Object>> list, String keyNm) {
        sort(list, keyNm, false);
    }

    public static void sort(List<Map<String, Object>> list, String keyNm, boolean descOrder) {
        Comparator<Map<String, Object>> comparator = new ListMapComparator(keyNm);
        Collections.sort(list, comparator);
        if (descOrder) {
            Collections.reverse(list);
        }
    }

}

