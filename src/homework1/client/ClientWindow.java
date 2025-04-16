package homework1.client;

import homework1.server.ServerWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class ClientWindow extends JFrame {
    private static final int WIDTH = 400;
    private static final int HEIGHT = 300;

    private final JTextArea logs = new JTextArea();

    private final JPanel panelTop = new JPanel(new GridLayout(2, 3));
    private final JTextField textIPAddress = new JTextField("127.0.0.1");
    private final JTextField textPort = new JTextField("8189");
    private final JTextField textLogin;
    private final JPasswordField textPassword = new JPasswordField("12345");
    private final JButton btnConnect = new JButton("Войти");


    private final JPanel panelBottom = new JPanel(new BorderLayout());
    private final JTextField textMsg = new JTextField();
    private final JButton btnSend = new JButton("Send");
    private boolean isClientOnServer;

    public String getNameClient() {
        return textLogin.getText();
    }

    public JTextArea getLogs() {
        return logs;
    }

    public ClientWindow(ServerWindow serverWindow, String clientName) {
        textLogin = new JTextField(clientName);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(WIDTH, HEIGHT);
        setTitle("ChatClient");

        panelTop.add(textIPAddress);
        panelTop.add(textPort);
        panelTop.add(new JLabel());
        panelTop.add(textLogin);
        panelTop.add(textPassword);
        panelTop.add(btnConnect);
        add(panelTop, BorderLayout.NORTH);

        panelBottom.add(textMsg, BorderLayout.CENTER);
        panelBottom.add(btnSend, BorderLayout.EAST);
        add(panelBottom, BorderLayout.SOUTH);

        logs.setEditable(false);
        JScrollPane scrollLogs = new JScrollPane(logs);
        add(scrollLogs);

        setVisible(true);
        isClientOnServer = false;
        connectToServer(serverWindow, this);
        sendMessageBtn(serverWindow, this);
        sendMessageEtr(serverWindow, this);

    }


    private void connectToServer(ServerWindow serverWindow, ClientWindow clientWindow) {
        btnConnect.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (serverWindow.ConnectClient(clientWindow)) {
                    StringBuilder base = new StringBuilder();
                    try (BufferedReader br = new BufferedReader(new FileReader("src/homework1/resources/"
                            + clientWindow.getNameClient() + ".txt"))) {
                        String line;
                        while ((line = br.readLine()) != null) {
                            base.append(line);
                            base.append('\n');
                        }
                        clientWindow.getLogs().append(base.toString());
                    } catch (FileNotFoundException ex) {
                        System.out.printf("Клиент %s подключен первый раз\n", clientWindow.getNameClient());
                        ;
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    logs.append("Вы успешно подключились!\n");
                    isClientOnServer = true;
                } else {
                    logs.append("Подключение не удалось\n");
                }
            }
        });
    }

    public void disConnectToServer() {
        isClientOnServer = false;
        logs.append("Сервер остановлен\n");
    }


    public void sendMessageBtn(ServerWindow serverWindow, ClientWindow clientWindow) {
        btnSend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isClientOnServer) {
                    String msg = textMsg.getText();
                    logs.append(textLogin.getText() + " :" + msg + "\n");
                    serverWindow.addClientMessage(msg, clientWindow);
                } else {
                    logs.append("Нет подключения к серверу" + "\n");
                }
            }
        });
    }

    public void sendMessageEtr(ServerWindow serverWindow, ClientWindow clientWindow) {
        textMsg.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isClientOnServer) {
                    String msg = textMsg.getText();
                    logs.append(textLogin.getText() + " :" + msg + "\n");
                    serverWindow.addClientMessage(msg, clientWindow);
                } else {
                    logs.append("Нет подключения к серверу" + "\n");
                }
            }
        });
    }


    public void saveLogs(ClientWindow clientWindow) {
        try (FileWriter fileWriter = new FileWriter("src/homework1/resources/"
                + clientWindow.getNameClient() + ".txt")) {
            fileWriter.write(clientWindow.getLogs().getText());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

