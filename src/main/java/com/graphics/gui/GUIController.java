package com.graphics.gui;

import javax.swing.*;

/**
 * Контроль за всем GUI осуществляется тут
 */
public class GUIController {

    public static JFrame frame;

    public static void OpenMainMenu () {
        frame = new MainWindow();

        //2. Optional: What happens when the frame closes?
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        //4. Size the frame.
        frame.pack();

        //5. Show it.
        frame.setVisible(true);
    }
}
