package homework1.server;

import homework1.client.ClientWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import java.util.ArrayList;
import java.util.List;

public class ServerWindow extends JFrame {
    private static final int POS_X = 500;
    private static final int POS_Y = 550;
    private static final int WIDTH = 400;
    private static final int HEIGHT = 500;

    private final JPanel panelBottom = new JPanel(new GridLayout(1, 2));

    private final JButton btnStart = new JButton("START");
    private final JButton btnStop = new JButton("STOP");
    private final JTextArea serverLogs = new JTextArea();
    private boolean isServerWorking;

    private final List<ClientWindow> clients = new ArrayList<>();


    public static void main(String[] args) {
        new ServerWindow();
    }

    public ServerWindow() {
        isServerWorking = false;
        btnStop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isServerWorking = false;
                for (ClientWindow client : clients) {
                    client.saveLogs(client);
                    client.disConnectToServer();
                }
                serverLogs.setText(serverLogs.getText() + "Сервер остановлен! " + "\n");
            }
        });

        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isServerWorking = true;
                serverLogs.setText(serverLogs.getText() + "Сервер запущен! " + "\n");
            }
        });

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBounds(POS_X, POS_Y, WIDTH, HEIGHT);
        setResizable(false);
        setTitle("Chat server");
        setAlwaysOnTop(true);
        panelBottom.add(btnStart);
        panelBottom.add(btnStop);
        add(panelBottom, BorderLayout.SOUTH);

        add(serverLogs);
        setVisible(true);

    }

    public boolean ConnectClient(ClientWindow clientWindow) {
        if (isServerWorking) {
            serverLogs.append(clientWindow.getNameClient() + " подключился к беседе " + "\n");
            clients.add(clientWindow);
            return true;
        } else {
            return false;
        }
    }


    public void addClientMessage(String message, ClientWindow clientWindow) {
        if (isServerWorking) {
            serverLogs.append(clientWindow.getNameClient() + ": " + message + "\n");
            for (ClientWindow client : clients) {
                if (client != clientWindow) {
                    client.getLogs().append(clientWindow.getNameClient() + ": " + message + "\n");
                }
            }
        }
    }

}
