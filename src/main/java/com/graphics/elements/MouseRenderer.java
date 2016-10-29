package com.graphics.elements;

/**
 * Отрисовщик мышки
 */

import com.graphics.Render;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

public class MouseRenderer {
    public static void Renderer (GameContainer gc, int xpos, int ypos) {
        // Коррекция центра курсора
        xpos -= 10;
        ypos -= 10;
        // Рисуем курсор
        Graphics g = gc.getGraphics();
        g.setLineWidth(1);
        //g.translate(Render.XposOmegaOffset, Render.YposOmegaOffset);
        g.setColor(Color.white);
        g.fillRect(9 + xpos, 0 + ypos, 2, 20);
        g.fillRect(0 + xpos, 9 + ypos, 20, 2);
    }
}
