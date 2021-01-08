import java.util.Scanner;
import java.util.Arrays;
import java.util.Map;
import java.util.LinkedHashMap;

public class HelloWorld {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the  no of rows and columns");

        int noOfRows = scanner.nextInt();
        int noOfColumns = scanner.nextInt();

        int[][] houses = new int[noOfRows][noOfColumns];

        for (int i = 0; i < houses.length; i++) {
            System.out.println("Enter the values in " + (i + 1) + "row");
            for (int j = 0; j < houses[i].length; j++) {
                houses[i][j] = scanner.nextInt();
            }
        }
        System.out.println(Arrays.deepToString(houses));
        int[] indexes = new int[2];
        Map < String, Integer > housesMap = HelloWorld.getGirlsScore(houses);
        if (housesMap.size() > 0) {
            int maxValue = 0;
            int shortestPath = 100000;
            int count = 0;
            for (Map.Entry < String, Integer > entry: housesMap.entrySet()) {
                if (maxValue < entry.getValue()) {
                    maxValue = entry.getValue();
                    indexes = HelloWorld.getIndices(entry);
                }
                if (maxValue == entry.getValue()) {
                    int[] indices = HelloWorld.getIndices(entry);
                    if ((indices[0] + indices[1]) < shortestPath) {
                        shortestPath = indices[0] + indices[1];
                    }
                }
            }
            for (Map.Entry < String, Integer > entry: housesMap.entrySet()) {
                int[] indices = HelloWorld.getIndices(entry);
                if (maxValue == entry.getValue()) {
                    if (indices[0] + indices[1] == shortestPath) {
                        count += 1;
                    }
                }
            }
            if (count > 1) {
                System.out.println("�Polygamy not allowed�");
            } else {
                System.out.println((indexes[0] + 1) + ":" + (indexes[1] + 1) + ":" + (maxValue - 1));
            }
        } else {
            System.out.println("\"No suitable girl found\"");

        }
    }

    public static Map < String, Integer > getGirlsScore(int[][] houses) {
        Map < String, Integer > map = new LinkedHashMap < String, Integer > ();
        for (int i = 0; i < houses.length; i++) {
            for (int j = 0; j < houses[i].length; j++) {
                String ref = Integer.toString(i) + Integer.toString(j);
                if (houses[i][j] == 1 && !(i == 0 && j == 0)) {
                    map.put(ref, 0);
                    for (int p = i - 1; p <= i + 1; p++) {
                        for (int q = j - 1; q <= j + 1; q++) {
                            if (HelloWorld.isInsideArray(houses.length, p, houses[i].length, q) && houses[p][q] == 1) {
                                map.put(ref, map.get(ref) + 1);
                            }
                        }
                    }
                }
            }
        }
        return map;
    }

    public static boolean isInsideArray(int noOfRows, int rowIndex, int noOfColumn, int columnIndex) {
        if (rowIndex >= 0 && rowIndex < noOfRows) {
            if (columnIndex >= 0 && columnIndex < noOfColumn) {
                return true;
            }
        }
        return false;
    }

    public static int[] getIndices(Map.Entry < String, Integer > entry) {
        int[] indices = {
            Integer.parseInt(String.valueOf(entry.getKey().charAt(0))),
            Integer.parseInt(String.valueOf(entry.getKey().charAt(1)))
        };
        return indices;
    }
}