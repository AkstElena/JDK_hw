package homework2;

/*
На семинаре мы разделили класс отвечающий за клиентское приложение на класс, отвечающий за логику приложения и за
графическую часть приложения. А также создали слабую связь между ними с помощью интерфейса.
Аналогичным образом вам надо преобразовать серверную часть приложения. Схема, которую требуется реализовать,
также есть в материалах к уроку. Вы можете работать со своим проектом из первой домашки, а можете работать с проектом
с семинара (ссылка в материалах к уроку).

Требуется разделить класс серверного приложения на контроллер, GUI и репозиторий.
Если вы работаете со своим проектом, то клиентскую часть также надо разделить на контроллер и GUI.
Связь между составляющими проекта реализовать с помощью интерфейсов
 */

import homework2.client.Client;
import homework2.client.ClientWindow;
import homework2.server.FileStorage;
import homework2.server.Server;
import homework2.server.ServerWindow;

public class Main {
    public static void main(String[] args) {

        Server server = new Server(new ServerWindow(), new FileStorage());

        new Client(new ClientWindow("Elena"), server);
        new Client(new ClientWindow("Renat"), server);


    }
}
