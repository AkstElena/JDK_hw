package homework2.server;

import homework2.client.Client;

import java.util.ArrayList;
import java.util.List;

public class Server {
    private boolean work;
    private ServerController serverController;
    private List<Client> clientList;
    private Repository<String> repository;

    public Server(ServerController serverController, Repository<String> repository) {
        this.serverController = serverController;
        this.repository = repository;
        clientList = new ArrayList<>();
        serverController.setServer(this);
    }

    public void start() {
        if (work) {
            showOnWindow("Сервер уже запущен");
        } else {
            work = true;
            showOnWindow("Сервер запущен!");
        }
    }

    public void stop() {
        if (!work) {
            showOnWindow("Сервер уже остановлен");
        } else {
            work = false;
            while (!clientList.isEmpty()) {
                disconnectUser(clientList.get(clientList.size() - 1));
            }
            showOnWindow("Сервер остановлен!");
        }
    }

    public void disconnectUser(Client client) {
        clientList.remove(client);
        if (client != null) {
            client.disconnectedFromServer();
            showOnWindow(client.getName() + " отключился от беседы");
        }
    }

    public boolean connectUser(Client client) {
        if (!work) {
            return false;
        }
        clientList.add(client);
        showOnWindow(client.getName() + " подключился к беседе");
        return true;
    }

    public void message(String text) {
        if (!work) {
            return;
        }
        showOnWindow(text);
        answerAll(text);
        saveInHistory(text);
    }

    public String getHistory() {
        return repository.load();
    }

    private void answerAll(String text) {
        for (Client clientController : clientList) {
            clientController.answerFromServer(text);
        }
    }

    private void showOnWindow(String text) {
        serverController.showMessage(text + "\n");
    }

    private void saveInHistory(String text) {
        repository.save(text);
    }
}
