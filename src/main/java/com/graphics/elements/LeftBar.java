package com.graphics.elements;

/**
 * Панель инструментов с левой стороны
 */

import com.graphics.Render;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

public class LeftBar {

    public static void Renderer (GameContainer gc) {
        Graphics g = gc.getGraphics();
        //g.translate(Render.XposOmegaOffset, Render.YposOmegaOffset);

        // Отрисоваваем блок
        g.setColor(Color.decode("#272727"));
        g.fillRect(0,0,220,700);
        // Отрисовываем грани блоку
        g.setColor(Color.decode("#dddddd"));
        g.drawLine(220, 0, 220, 700);
        g.drawLine(0, 700, 220, 700);
    }

}
