package com.bizlog.rms.utils;

import jakarta.persistence.Tuple;
import jakarta.persistence.criteria.Root;
import jakarta.persistence.criteria.Selection;

import java.util.*;

public class ProjectionsUtil {

    public static List<ProjectionRow> convert(List<Tuple> results) {
        List<ProjectionRow> projectionRows = new ArrayList<>();
        results.forEach(tuple -> {
            Map<String, String> entityMap = new HashMap<>();
            tuple.getElements().forEach(element -> {
                String alias = element.getAlias();
                Object valObj = tuple.get(alias);
                String value = Objects.isNull(valObj) ? null : String.valueOf(valObj);
                entityMap.put(alias, value);
            });
            projectionRows.add(new ProjectionRow(entityMap));
        });
        return projectionRows;
    }

    public static List<Selection<?>> getSelectionList(Root<?> root, Set<String> attributes) {
        List<Selection<?>> selectionList = new ArrayList<>();
        attributes.add("id");
        attributes.stream().forEach(attr -> {
            selectionList.add(root.get(attr).alias(attr));
        });
        return selectionList;
    }

}
