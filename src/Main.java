import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static com.sun.tools.javac.util.StringUtils.toLowerCase;

public class Main {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        Scanner s = new Scanner(System.in);
        String operation;

        while (true) {
            System.out.println("Выберете операцию:");
            System.out.println("1. Добавить покупку");
            System.out.println("2. Показать покупки");
            System.out.println("3. Удалить покупку");
            System.out.println("4. Найти покупку");
            operation = s.nextLine();
            try {
                switch (Integer.parseInt(operation)) {
                    case 1: {
                        add(s, list);
                        continue;
                    }
                    case 2: {
                        show(list);
                        continue;
                    }
                    case 3: {
                        delete(s, list);
                        continue;
                    }
                    case 4: {
                        search(s, list);
                        continue;
                    }
                    default: {
                        System.out.println("Такой операции нет!");
                        continue;
                    }
                }
            } catch (NumberFormatException err) {
                System.out.println("Неверный формат данных!");
                continue;
            }

        }
    }

    public static void add(Scanner s, List<String> list) {
        System.out.println("Какую покупку хотите добавить?");
        list.add(s.nextLine());
        System.out.println("Итого в списке покупок: " + list.size());
    }

    public static void show(List<String> list) {
        if (list.isEmpty()) {
            System.out.println("Список покупок пуст!");
        } else {
            System.out.println("Список покупок:");
            for (int i = 0; i < list.size(); i++) {
                System.out.println(i + 1 + ". " + list.get(i));
            }
        }
    }

    public static void delete(Scanner s, List<String> list) {
        if (list.isEmpty()) {
            System.out.println("Список покупок пуст!");
            return;
        }
        show(list);
        System.out.println("Какую хотите удалить? Введите номер или название");
        String del = s.nextLine();
        try {
            int delInt = Integer.parseInt(del);
            System.out.print("Покупка \"" + list.get(delInt - 1) + "\" удалена, ");
            list.remove(delInt - 1);
            show(list);
        } catch (IndexOutOfBoundsException err) {
            System.out.print("Покупки под номером \"" + del + "\" не найдено в списке покупок, ");
        } catch (NumberFormatException err) {
            if (!list.remove(del)) {
                System.out.print("\"" + del + "\" не найдено в списке покупок, ");
            } else {
                System.out.print("Покупка \"" + del + "\" удалена, ");
                list.remove(del);
                show(list);
            }
        }
    }

    public static void search(Scanner s, List<String> list) {
        if (list.isEmpty()) {
            System.out.println("Список покупок пуст!");
            return;
        }
        System.out.println("Введите текст для поиска:");
        String search = s.nextLine();
        String queryLower = toLowerCase(search);
        String itemLower;
        boolean isSearch = false;
        System.out.println("Найдено:");
        for (int i = 0; i < list.size(); i++) {
            itemLower = toLowerCase((String) list.get(i));
            if (itemLower.contains(queryLower)) {
                System.out.println(i + 1 + "." + list.get(i));
                isSearch = true;
            }
        }
        if (!isSearch) {
            System.out.print("\"" + search + "\" не найдено в списке покупок, ");
        }
    }
}
