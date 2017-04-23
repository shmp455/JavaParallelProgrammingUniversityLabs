package ru.spbstu.telematics.sharagin.lab04.myClient;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.Socket;
import java.util.Scanner;
import java.util.Date;

/**
 * Created by maksim on 22.04.17.
 */
public class ChatClientWindow extends JFrame {
    // адрес сервера
    private static final String SERVER_HOST = "localhost";
    // порт
    private static final int SERVER_PORT = 8082;
    // клиентский сокет
    private Socket clientSocket;
    // входящее сообщение
    private Scanner inMessage;
    // исходящее сообщение
    private PrintWriter outMessage;

    // элементы формы
    private JTextField message;
    private JTextField currentClientName;
    private JTextArea textArea;
    private JScrollPane scroll;
    private JLabel clientsNumber;
    private JPanel messagePanel;
    private JButton sendButton;
    private ImageIcon sendButtonIcon;
    private JPanel infoPanel;
    private JLabel chosenClientName;

    public ChatClientWindow(String chosenName) {
        try {
            // подключаемся к серверу
            clientSocket = new Socket(SERVER_HOST, SERVER_PORT);
            inMessage = new Scanner(clientSocket.getInputStream());
            outMessage = new PrintWriter(clientSocket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }


        // расположение элементов на форме
        setBounds(600, 400, 600, 400);
        setTitle("Client window");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        textArea = new JTextArea();
        scroll = new JScrollPane(textArea);
        clientsNumber = new JLabel("Clients online: ");
        messagePanel = new JPanel(new BorderLayout());
        sendButtonIcon = new ImageIcon("src/main/resources/lab04/send.png");
        sendButton = new JButton(sendButtonIcon);
        message = new JTextField("Message");
        currentClientName = new JTextField(chosenName);
        infoPanel = new JPanel(new BorderLayout());
        chosenClientName = new JLabel("Client: ");

        textArea.setEditable(false);
        textArea.setForeground(Color.BLACK);
        textArea.setBackground(Color.getColor(RGBToString(220,220,200)));
        currentClientName.setEditable(false);

        add(scroll, BorderLayout.CENTER);

        messagePanel.add(message, BorderLayout.CENTER);
        messagePanel.add(sendButton, BorderLayout.EAST);
        add(messagePanel, BorderLayout.SOUTH);

        infoPanel.add(chosenClientName, BorderLayout.WEST);
        infoPanel.add(currentClientName, BorderLayout.CENTER);
        infoPanel.add(clientsNumber, BorderLayout.EAST);
        add(infoPanel, BorderLayout.NORTH);


        // обработчик события нажатия кнопки отправки сообщения
        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // если имя клиента, и сообщение непустые, то отправляем сообщение
                if (!message.getText().trim().isEmpty() && !currentClientName.getText().trim().isEmpty()) {
                    sendMsg();
                    // фокус на текстовое поле с сообщением
                    message.grabFocus();
                }
            }
        });
        // при фокусе поле сообщения очищается
        message.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                message.setText("");
            }
        });
        // в отдельном потоке начинаем работу с сервером
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true) {
                        // если есть входящее сообщение
                        if (inMessage.hasNext()) {
                            // считываем его
                            String inMes = inMessage.nextLine();
                            String clientsInChat = "Clients online: ";
                            if (inMes.indexOf(clientsInChat) == 0) {
                                clientsNumber.setText(inMes);
                            } else {
                                // выводим сообщение
                                textArea.append(inMes);
                                // добавляем строку перехода
                                textArea.append("\n");
                            }
                        }
                    }
                } catch (Exception e) {
                }
            }
        }).start();
        // добавляем обработчик события закрытия окна клиентского приложения
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                try {
                    // здесь проверяем, что имя клиента непустое
                    if (!currentClientName.getText().isEmpty()) {
                        outMessage.println(currentClientName.getText() + " is out!");
                    }
                    // отправляем служебное сообщение, которое является признаком того, что клиент вышел из чата
                    outMessage.println("exit");
                    outMessage.flush();
                    outMessage.close();
                    inMessage.close();
                    clientSocket.close();
                } catch (IOException exc) {
                    exc.printStackTrace();
                }
            }
        });
        // отображаем форму
        setVisible(true);
    }

    // отправка сообщения
    public void sendMsg() {
        // формируем сообщение для отправки на сервер
        String messageStr = currentClientName.getText() + " ("+new Date().toString()+"): " + message.getText();
        // отправляем сообщение
        outMessage.println(messageStr);
        outMessage.flush();
        message.setText("");
    }

    public String RGBToString(int r, int g, int b){
        String rs = Integer.toHexString(r * 256);
        String gs = Integer.toHexString(g * 256);
        String bs = Integer.toHexString(b * 256);
        return rs + gs + bs;
    }
}
