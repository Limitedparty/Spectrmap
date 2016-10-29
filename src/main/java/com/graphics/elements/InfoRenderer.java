package com.graphics.elements;

import com.logics.WindowState;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

/**
 * Отрисовывает информацию (+ в зависимости от режима разную)
 */
public class InfoRenderer {

    public static void Renderer (GameContainer gc, WindowState windowState) {
        Graphics g = gc.getGraphics();
        switch (windowState) {
            case MAP_EDITOR:
                g.setColor(Color.decode("#CCD5FF"));
                g.drawString("Editor Mode", 10, 10);
                g.drawString("x: " + gc.getInput().getMouseX() + "  y:" + gc.getInput().getMouseY(), 200, 10);
                break;
            default:
                break;
        }
    }
}
