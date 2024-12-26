import java.io.*;
import java.util.*;

public class BasicDataOperationUsingList {
    // Шлях до файлу
    private static final String PATH_TO_DATA_FILE = "list/float.data";

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Будь ласка, вкажіть значення для пошуку!");
            return;
        }

        float searchValue = Float.parseFloat(args[0]);
        List<Float> list = new LinkedList<>();
        Float[] array;

        try (BufferedReader br = new BufferedReader(new FileReader(PATH_TO_DATA_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                float floatValue = Float.parseFloat(line);
                list.add(floatValue);
            }
        } catch (IOException e) {
            System.err.println("Помилка читання файлу: " + e.getMessage());
            return;
        }

        array = list.toArray(new Float[0]);

        // Виконання операцій
        searchInArray(array, searchValue);
        findMinAndMaxInArray(array);
        sortArray(array);
        searchInArray(array, searchValue);

        searchInList(list, searchValue);
        findMinAndMaxInList(list);
        sortList(list);
        searchInList(list, searchValue);

        // Збереження відсортованих даних у файл
        saveSortedDataToFile(list, "list/float.data.sorted");
    }

    private static void searchInArray(Float[] array, float searchValue) {
        long startTime = System.nanoTime();
        boolean found = false;
        for (int i = 0; i < array.length; i++) {
            if (array[i] == searchValue) {
                System.out.println("Значення '" + searchValue + "' знайдено в масиві за індексом: " + i);
                found = true;
                break;
            }
        }
        long endTime = System.nanoTime();
        System.out.println(">>>>>>>>> Час виконання операції 'пошук в масиві': " + (endTime - startTime) + " наносекунд");
        if (!found) {
            System.out.println("Значення '" + searchValue + "' у масиві не знайдено.");
        }
    }

    private static void findMinAndMaxInArray(Float[] array) {
        long startTime = System.nanoTime();
        float min = Float.MAX_VALUE;
        float max = Float.MIN_VALUE;
        for (float value : array) {
            if (value < min) min = value;
            if (value > max) max = value;
        }
        long endTime = System.nanoTime();
        System.out.println(">>>>>>>>> Час виконання операції 'пошук мінімального і максимального значення в масиві': " + (endTime - startTime) + " наносекунд");
        System.out.println("Мінімальне значення в масиві: " + min);
        System.out.println("Максимальне значення в масиві: " + max);
    }

    private static void sortArray(Float[] array) {
        long startTime = System.nanoTime();
        Arrays.sort(array);
        long endTime = System.nanoTime();
        System.out.println(">>>>>>>>> Час виконання операції 'сортування масиву': " + (endTime - startTime) + " наносекунд");
    }

    private static void searchInList(List<Float> list, float searchValue) {
        long startTime = System.nanoTime();
        int index = list.indexOf(searchValue);
        long endTime = System.nanoTime();
        System.out.println(">>>>>>>>> Час виконання операції 'пошук у списку': " + (endTime - startTime) + " наносекунд");
        if (index != -1) {
            System.out.println("Значення '" + searchValue + "' знайдено у списку за індексом: " + index);
        } else {
            System.out.println("Значення '" + searchValue + "' у списку не знайдено.");
        }
    }

    private static void findMinAndMaxInList(List<Float> list) {
        long startTime = System.nanoTime();
        float min = Float.MAX_VALUE;
        float max = Float.MIN_VALUE;
        for (float value : list) {
            if (value < min) min = value;
            if (value > max) max = value;
        }
        long endTime = System.nanoTime();
        System.out.println(">>>>>>>>> Час виконання операції 'пошук мінімального і максимального значення у списку': " + (endTime - startTime) + " наносекунд");
        System.out.println("Мінімальне значення у списку: " + min);
        System.out.println("Максимальне значення у списку: " + max);
    }

    private static void sortList(List<Float> list) {
        long startTime = System.nanoTime();
        Collections.sort(list);
        long endTime = System.nanoTime();
        System.out.println(">>>>>>>>> Час виконання операції 'сортування списку': " + (endTime - startTime) + " наносекунд");
    }

    private static void saveSortedDataToFile(List<Float> list, String filePath) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(filePath))) {
            for (float value : list) {
                pw.println(value);
            }
            System.out.println("Відсортовані дані збережено у файл: " + filePath);
        } catch (IOException e) {
            System.err.println("Помилка запису у файл: " + e.getMessage());
        }
    }
}
