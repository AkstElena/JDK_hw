package homework2.client;

public interface ClientController {

    void showMessage(String message);

    void disconnectedFromServer();


    void setClient(Client client);
}
