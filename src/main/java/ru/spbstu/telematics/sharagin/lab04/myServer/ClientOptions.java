package ru.spbstu.telematics.sharagin.lab04.myServer;

import ru.spbstu.telematics.sharagin.lab04.myServer.ChatServer;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Date;
import java.util.Scanner;

/**
 * Created by maksim on 22.04.17.
 */
public class ClientOptions implements Runnable {
    // экземпляр нашего сервера
    private ChatServer server;
    // исходящее сообщение
    private PrintWriter outMessage;
    // входящее собщение
    private Scanner inMessage;
    private static final String HOST = "localhost";
    private static final int PORT = 8082;
    // клиентский сокет
    private Socket clientSocket = null;
    // количество клиентов в чате
    private static int clients_count = 0;

    public ClientOptions(Socket socket, ChatServer server) {
        try {
            clients_count++;
            this.server = server;
            this.clientSocket = socket;
            this.outMessage = new PrintWriter(socket.getOutputStream());
            this.inMessage = new Scanner(socket.getInputStream());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            while (true) {
                // сервер отправляет сообщение
                server.sendMessageToAllClients("New client is online ("+new Date().toString()+")");
                server.sendMessageToAllClients("Clients online: " + clients_count);
                break;
            }

            while (true) {
                synchronized (this) {
                    // Если от клиента пришло сообщение
                    if (inMessage.hasNext()) {
                        String clientMessage = inMessage.nextLine();
                        // если клиент отправляет данное сообщение, то цикл прерывается и
                        // клиент выходит из чата
                        if (clientMessage.equalsIgnoreCase("exit")) {
                            break;
                        }
                        System.out.println(clientMessage);
                        // отправляем данное сообщение всем клиентам
                        server.sendMessageToAllClients(clientMessage);
                    }
                }
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        finally {
            this.close();
        }
    }
    // отправляем сообщение
    public void sendMsg(String msg) {
        try {
            outMessage.println(msg);
            outMessage.flush();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    // клиент выходит из чата
    public void close() {
        // удаляем клиента из списка
        server.removeClient(this);
        clients_count--;
        server.sendMessageToAllClients("Clients online: " + clients_count);
    }
}
