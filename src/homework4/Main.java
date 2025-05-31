package homework4;
/*
Создать справочник сотрудников
Необходимо:
Создать класс справочник сотрудников, который содержит внутри
коллекцию сотрудников - каждый сотрудник должен иметь следующие атрибуты:
Табельный номер
Номер телефона
Имя
Стаж
Добавить метод, который ищет сотрудника по стажу (может быть список)
Добавить метод, который возвращает номер телефона сотрудника по имени (может быть список)
Добавить метод, который ищет сотрудника по табельному номеру
Добавить метод добавления нового сотрудника в справочник

 */

public class Main {
    public static void main(String[] args) {
        Directory directory = new Directory();

        directory.addEmployee("89175551122", "Марк", "Петров", 4);
        directory.addEmployee("89053312255", "Иван", "Сурков", 3);
        directory.addEmployee("89604512237", "Иван", "Николаев", 6);
        directory.addEmployee("89076615544", "Ольга", "Чапаева", 10);
        directory.addEmployee("89876654411", "Светлана", "Иванова", 6);


        System.out.println("Сотрудники со стажем 6 лет: \n" + directory.getEmployeesByExperience(6));
        System.out.println("Номер телефона Ивана: " + directory.getPhoneByFirstName("иВан"));
        System.out.println("Номер телефона Ивана: " + directory.getPhoneByFirstName("Иван"));
        System.out.println("С табельным номером 2: " + directory.getEmployeeByPersonnelNumber(2));

        directory.addEmployee("89063335511", "Карл", "Крылов", 2);
        Employee employee1 = new Employee("89523364477", "Ирина", "Сидорова", 5);
        directory.addEmployee(employee1);
        System.out.println("Список сотрудников: \n" + directory.getAllEmployees());
    }
}
