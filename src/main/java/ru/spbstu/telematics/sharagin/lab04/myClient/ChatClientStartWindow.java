package ru.spbstu.telematics.sharagin.lab04.myClient;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by maksim on 22.04.17.
 */
public class ChatClientStartWindow extends JFrame {
    private JTextField enteredClientName;
    private JLabel nameLabel;
    private JButton ok;

    public ChatClientStartWindow()
    {
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setBounds(500, 50, 500, 50);
        setTitle("Registration window");

        nameLabel = new JLabel("Enter name: ");
        enteredClientName = new JTextField();
        ok = new JButton("Ok");

        add(nameLabel, BorderLayout.WEST);
        add(enteredClientName, BorderLayout.CENTER);
        add(ok, BorderLayout.EAST);

        ok.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                String name = enteredClientName.getText();
                if(!name.isEmpty())
                {
                    setVisible(false);
                    new ChatClientWindow(name);
                }
            }
        });

        setVisible(true);
    }
}
