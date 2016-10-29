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

    public static void Tick (GameContainer gc, int delta) {
        InputMaster.Tick(gc);
    }

}
