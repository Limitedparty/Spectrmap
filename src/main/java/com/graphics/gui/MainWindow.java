package com.graphics.gui;

import com.logics.CommandMaster;
import com.sun.java.swing.plaf.motif.MotifLookAndFeel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * Тут вообще не главное меню если что, а консоль
 */
public class MainWindow extends JFrame {

    public JPanel rootPanel;
    public JTextField textField1;
    public JButton EXEButton;
    public JTextArea textAreaConsole;

    public MainWindow() {
        setContentPane(rootPanel);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        EXEButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Отправка команды
                textAreaConsole.setText(textAreaConsole.getText() + "\n>> " + textField1.getText() + "\n" + CommandMaster.EXE(textField1.getText()));
                // Очистка поля ввода

            }
        });
    }
}
