package com.github.bruce;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SplitIdsTest {
    public static void main(String[] args) {
        long totalStartId = 2;
        long totalEndId = 501;
        int pageSize = 100;
        List<Map<Long, Long>> ids = new ArrayList<>();
        long startId = totalStartId;
        long endId = startId + pageSize - 1 >= totalEndId ? totalEndId : startId + pageSize - 1;
        while (startId <= totalEndId) {
            Map<Long, Long> map = new HashMap<>();
            map.put(startId, endId);
            ids.add(map);
            startId = endId + 1;
            endId += pageSize;
            if (endId > totalEndId) {
                endId = totalEndId;
            }
        }
        System.out.println(ids);
    }
}
