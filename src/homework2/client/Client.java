package homework2.client;

import homework2.server.Server;

public class Client {
    private boolean connected;
    private String name;
    private ClientWindow clientWindow;
    private Server server;

    public Client(ClientWindow clientWindow, Server server) {
        this.clientWindow = clientWindow;
        this.server = server;
        clientWindow.setClient(this);
    }

    public boolean connectToServer(String name) {
        this.name = name;
        if (server.connectUser(this)) {
            showOnWindow("Вы подключены к серверу!\n");
            connected = true;
            String log = server.getHistory();
            if (log != null) {
                showOnWindow(log);
            }
            return true;
        } else {
            showOnWindow("Подключение не удалось");
            return false;
        }
    }

    public void answerFromServer(String text) {
        showOnWindow(text);
    }

    public void disconnectedFromServer() {
        if (connected) {
            connected = false;
            clientWindow.disconnectedFromServer();
            showOnWindow("Вы отключены от сервера!");
        }
    }

    public void disconnectServer() {
        server.disconnectUser(this);
    }

    public void message(String text) {
        if (connected) {
            if (!text.isEmpty()) {
                server.message(name + ": " + text);
            }
        } else {
            showOnWindow("Нет подключения к серверу");
        }
    }


    public String getName() {
        return name;
    }


    private void showOnWindow(String text) {
        clientWindow.showMessage(text + "\n");
    }
}
