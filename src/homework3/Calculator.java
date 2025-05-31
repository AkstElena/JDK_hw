package homework3;

/*
1. Написать класс Калькулятор (необобщенный), который содержит обобщенные статические методы:
sum(), multiply(), divide(), subtract(). Параметры этих методов – два числа разного типа,
над которыми должна быть произведена операция.
*/

public class Calculator {

    // сложение
    public <T extends Number, U extends Number> double sum(T a, U b) {
        return a.doubleValue() + b.doubleValue();
    }


    // умножение
    public <T extends Number, U extends Number> double multiply(T a, U b) {
        return a.doubleValue() * b.doubleValue();
    }

    // деление
    public <T extends Number, U extends Number> double divide(T a, U b) {
        if (b.doubleValue() == 0) {
            throw new ArithmeticException("Division by zero");
        } else return a.doubleValue() / b.doubleValue();
    }


    // вычитание
    public <T extends Number, U extends Number> double subtract(T a, U b) {
        return a.doubleValue() - b.doubleValue();
    }


}
