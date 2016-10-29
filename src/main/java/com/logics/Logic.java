package com.logics;

/**
 * Вызывает все, что связано с логикой
 */

import com.graphics.Render;
import com.graphics.elements.Grid;
import org.newdawn.slick.GameContainer;

public class Logic {
    public static WindowState currentWindowState;
    public static Grid.GridTool selectorTool;
    public static PointSelector.SelectorType selectorType;
    public static int[][] selectedPoints = new int[4][2];
    public static String currentColor = "#ffffff";
    public static int LineWidth = 1;
    public static String mapName = "";
    public static String urlPassive = "";

    private static int udpcounter = 0;
    public static boolean udpReadEnded = true;
    static PassiveUpdate passiveUrlThread;

    public static void Tick (GameContainer gc, int delta) {
        InputMaster.Tick(gc);

        if (!urlPassive.equals("")) {
            udpcounter += delta;
            if (udpcounter >= 2000 && udpReadEnded) {
                passiveUrlThread = new PassiveUpdate();
                passiveUrlThread.start();
                udpReadEnded = false;
                udpcounter = 0;
            }
        }
    }

}
