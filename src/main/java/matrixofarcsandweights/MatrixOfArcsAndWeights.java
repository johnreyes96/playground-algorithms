package main.java.matrixofarcsandweights;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.*;

public class MatrixOfArcsAndWeights {

    public static void main(String[] args) {
        Map<Integer, List<Integer>> table = getTable();
        System.out.println(getMatrixOfArcsAndWeights(table));
    }

    private static String getMatrixOfArcsAndWeights(Map<Integer, List<Integer>> table) {
        StringBuilder matrixOfArcsAndWeights = new StringBuilder("[");
        table.forEach((k, v) -> {
            table.forEach((k2, v2) -> {
                if (k.compareTo(k2) != 0) {
                    Integer numberOfSharedParts = getNumberOfSharedParts(table.get(k), table.get(k2));
                    Integer numberOfUnsharedParts = getNumberOfUnsharedParts(table.get(k), table.get(k2));
                    Double disparityMeasure = getDisparityMeasure(numberOfSharedParts, numberOfUnsharedParts);
                    matrixOfArcsAndWeights.append("(").append(k).append(",").append(k2).append(",")
                            .append(disparityMeasure).append("),\n");
                }
            });
        });
        return matrixOfArcsAndWeights.substring(0, matrixOfArcsAndWeights.length() - 2).concat("\n]");
    }

    private static Double getDisparityMeasure(Integer numberOfSharedParts, Integer numberOfUnsharedParts) {
        return Math.abs(new BigDecimal((double) 1 - (double) numberOfSharedParts /
                ((double) numberOfSharedParts + (double) numberOfUnsharedParts), MathContext.DECIMAL64).doubleValue());
    }

    private static Integer getNumberOfUnsharedParts(List<Integer> list1, List<Integer> list2) {
        List<Integer> differenceList1 = list1.stream()
                .filter(one -> list2.stream()
                        .allMatch(two -> one.compareTo(two) != 0))
                .toList();
        List<Integer> differenceList2 = list2.stream()
                .filter(one -> list1.stream()
                        .allMatch(two -> one.compareTo(two) != 0))
                .toList();
        return differenceList1.size() + differenceList2.size();
    }

    private static Integer getNumberOfSharedParts(List<Integer> list1, List<Integer> list2) {
        return list1.stream()
                .filter(one -> list2.stream()
                        .anyMatch(two -> one.compareTo(two) == 0))
                .toList().size();
    }

    private static Map<Integer, List<Integer>> getTable() {
        Map<Integer, List<Integer>> table = new HashMap<>();
        table.put(1, Arrays.asList(1, 5, 7));
        table.put(2, Arrays.asList(2, 3, 4, 7, 8, 9));
        table.put(3, Arrays.asList(2, 3, 4, 5, 7, 8, 9, 10));
        table.put(4, Arrays.asList(6, 7, 8, 9, 10));
        table.put(5, Arrays.asList(2, 5, 7, 8, 9, 10));
        table.put(6, Arrays.asList(1, 2, 3, 7, 8));
        table.put(7, Arrays.asList(2, 5, 9));
        table.put(8, Arrays.asList(3, 4, 7, 9, 10));
        table.put(9, Arrays.asList(1, 7, 9, 10));
        table.put(10, Arrays.asList(7, 8, 9, 10));
        return table;
    }
}
