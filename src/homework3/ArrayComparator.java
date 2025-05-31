package homework3;

/*
2. Напишите обобщенный метод compareArrays(), который принимает два массива и возвращает true,
если они одинаковые, и false в противном случае.
Массивы могут быть любого типа данных, но должны иметь одинаковую длину и содержать элементы одного типа
(то есть сами значения могут быть разными, но типы данных в массивах одинаковы).
 */
public class ArrayComparator {
    public <T> boolean compareArrays(T[] array1, T[] array2) {

        if (array1 == null || array2 == null) {
            return false;
        }

        if (array1.length != array2.length) {
            return false;
        }

        for (int i = 0; i < array1.length; i++) {
            if (!array1[i].getClass().equals(array2[i].getClass())) {
                return false;
            }
        }
        return true;
    }
}
