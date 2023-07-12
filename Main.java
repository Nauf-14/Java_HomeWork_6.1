// Инструкция пользования:
// Пользователь может вводить критерии фильтрации в формате "номер_критерия=значение". 
// Каждый критерий должен быть разделен пробелом. Например, пользователь может ввести "1=8 3=Windows", 
// чтобы найти ноутбуки с 8 ГБ ОЗУ и операционной системой Windows. Если введенный формат или значение некорректны, 
// программа пропустит этот критерий и продолжит запросить следующие критерии.


import java.util.*;

public class Main {
    public static void main(String[] args) {
        Catalog catalog = new Catalog();

        // Добавляем ноутбуки в каталог
        catalog.addNotebook(new Notebook("Brand 1", 8, 512, "Windows", "Black"));
        catalog.addNotebook(new Notebook("Brand 2", 16, 1024, "MacOS", "Silver"));
        catalog.addNotebook(new Notebook("Brand 3", 8, 256, "Linux", "Red"));
        catalog.addNotebook(new Notebook("Brand 4", 16, 512, "Windows", "Blue"));

        // Запрашиваем критерии фильтрации от пользователя
        Map<Integer, Object> filterCriteria = getFilterCriteriaFromUser();

        // Выводим отфильтрованные ноутбуки
        catalog.printFilteredNotebooks(filterCriteria);
    }

    private static Map<Integer, Object> getFilterCriteriaFromUser() {
        Map<Integer, Object> filterCriteria = new HashMap<>();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите критерии фильтрации в формате \"номер_критерия=значение\" (через пробел):");
        System.out.println("1 - ОЗУ");
        System.out.println("2 - Объем ЖД");
        System.out.println("3 - Операционная система");
        System.out.println("4 - Цвет");

        String[] choices = scanner.nextLine().split(" ");
        for (String choice : choices) {
            String[] criteria = choice.split("=");
            if (criteria.length != 2) {
                System.out.println("Некорректный формат: " + choice);
                continue;
            }

            int key = Integer.parseInt(criteria[0]);
            Object value = parseValue(criteria[1], key);
            if (value == null) {
                System.out.println("Некорректное значение: " + criteria[1]);
                continue;
            }

            filterCriteria.put(key, value);
        }

        return filterCriteria;
    }

    private static Object parseValue(String valueStr, int key) {
        try {
            switch (key) {
                case 1: // RAM
                case 2: // Storage
                    return Integer.parseInt(valueStr);
                case 3: // OS
                case 4: // Color
                    return valueStr;
                default:
                    return null;
            }
        } catch (NumberFormatException e) {
            return null;
        }
    }
}


