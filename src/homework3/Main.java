package homework3;
/*
1. Написать класс Калькулятор (необобщенный), который содержит обобщенные статические методы:
sum(), multiply(), divide(), subtract(). Параметры этих методов – два числа разного типа,
над которыми должна быть произведена операция.
2. Напишите обобщенный метод compareArrays(), который принимает два массива и возвращает true,
если они одинаковые, и false в противном случае.
Массивы могут быть любого типа данных, но должны иметь одинаковую длину и содержать элементы одного типа
(то есть сами значения могут быть разными, но типы данных в массивах одинаковы).
3. Напишите обобщенный класс Pair, который представляет собой пару значений разного типа.
Класс должен иметь методы getFirst(), getSecond() для получения значений каждого из составляющих пары,
а также переопределение метода toString(), возвращающее строковое представление пары.

Работу сдать в виде ссылки на гит репозиторий
 */
public class Main {
    public static void main(String[] args){
        Calculator calculator = new Calculator();

        System.out.println("Сумма: " + calculator.sum(3, 1.2));
        System.out.println("Умножение: " + calculator.multiply(10, 1.5));
        System.out.println("Деление: " + calculator.divide(4.5, 1.5));
//        System.out.println("Деление на ноль: " + calculator.divide(10, 0));
        System.out.println("Вычитание: " + calculator.subtract(1, 3));


        ArrayComparator arrayComparator = new ArrayComparator();

        Integer[] array1 = {1, 2, 3};
        Integer[] array2 = {4, 5, 6};
        Double[] array3 = {1.0, 2.0, 3.0};
        String[] array4 = {"a", "b", "c"};
        String[] array5 = {"d", "e", "f"};

        System.out.println(arrayComparator.compareArrays(array1, array2));
        System.out.println(arrayComparator.compareArrays(array1, array3));
        System.out.println(arrayComparator.compareArrays(array1, array4));
        System.out.println(arrayComparator.compareArrays(array4, array5));


        Pair<Integer, String> pair1 = new Pair<>(25, "name");
        System.out.println(pair1.getFirst() + ", " + pair1.getSecond() + ", " + pair1);

    }
}
