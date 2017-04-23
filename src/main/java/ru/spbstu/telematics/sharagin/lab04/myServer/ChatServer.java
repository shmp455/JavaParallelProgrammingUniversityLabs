package ru.spbstu.telematics.sharagin.lab04.myServer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Created by maksim on 22.04.17.
 */
public class ChatServer {
    // порт, который будет прослушивать сервер
    static final int PORT = 8082;
    // список клиентов, которые будут подключаться к серверу
    private ArrayList<ClientOptions> clients = new ArrayList<ClientOptions>();

    public ChatServer() {
        Socket clientSocket = null;
        ServerSocket serverSocket = null;
        try {
            // создаём серверный сокет на определенном порту
            serverSocket = new ServerSocket(PORT);
            System.out.println("Server is starting!");
            while (true) {
                // таким образом ждём подключений от сервера
                clientSocket = serverSocket.accept();
                // клиент, который подключился к серверу
                ClientOptions client = new ClientOptions(clientSocket, this);
                clients.add(client);
                // каждое подключение клиента обрабатываем в новом потоке
                new Thread(client).start();
            }
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
        finally {
            try {
                // закрываем подключение
                clientSocket.close();
                System.out.println("Server is stopped!");
                serverSocket.close();
            }
            catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    // отправляем сообщение всем клиентам
    public void sendMessageToAllClients(String msg) {
        for (ClientOptions o : clients) {
            o.sendMsg(msg);
        }

    }

    // удаляем клиента из коллекции при выходе из чата
    public void removeClient(ClientOptions client) {
        clients.remove(client);
    }
}
