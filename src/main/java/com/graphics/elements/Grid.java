package com.graphics.elements;

/**
 * Рисует сетку и выделенные точки и так же инструмент
 */

import com.graphics.Render;
import com.logics.Logic;
import com.sun.org.apache.xerces.internal.impl.xpath.regex.Match;
import com.sun.org.apache.xml.internal.serializer.utils.SystemIDResolver;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import com.spectrmap.SpectreMap;

public class Grid {

    public static int row_count = 100;
    public static int cell_size = 20;

    public static void Renderer (GameContainer gc) {
        gc.getGraphics().setColor(Color.decode("#003d7c"));
        for (int x = row_count; x > 0; x = x - 1) {
            gc.getGraphics().drawLine(x * cell_size + Render.XposOmegaOffset, Render.YposOmegaOffset, x * cell_size + Render.XposOmegaOffset, cell_size * row_count +  Render.YposOmegaOffset);
        }
        for (int x = row_count; x > 0; x = x - 1) {
            gc.getGraphics().drawLine(0 + Render.XposOmegaOffset, x * cell_size + Render.YposOmegaOffset, cell_size * row_count + Render.XposOmegaOffset, x * cell_size + Render.YposOmegaOffset);
        }
    }

    public enum GridTool {
        MOUSE_POINT,
        TWO_VERTEX,
        CELL_SELECT
    }

    public static void RendererTool (GameContainer gc, GridTool tool, int xpos, int ypos) {
        Graphics g = gc.getGraphics();
        g.setLineWidth(1);

        switch (tool) {
            case CELL_SELECT:
                int cellx = (int) Math.floor(xpos / cell_size);
                int celly = (int) Math.floor(ypos / cell_size);
                g.setColor(Color.decode("#1b5591"));
                g.fillRect(cellx * cell_size + Render.XposOmegaOffset,celly * cell_size + Render.YposOmegaOffset, cell_size, cell_size);
                break;
            case TWO_VERTEX:
                int twocellx = (int) Math.floor(xpos / (cell_size / 2));
                int twocelly = (int) Math.floor(ypos / (cell_size / 2));
                g.setColor(Color.decode("#1b5591"));
                g.fillRect(twocellx * (cell_size / 2) - (cell_size / 4), twocelly * (cell_size / 2) - (cell_size / 4), cell_size / 2, cell_size / 2);
                break;
            default:
                break;
        }
    }

    public static void RendererSelectedPoints (GameContainer gc) {
        Graphics g = gc.getGraphics();
        g.setLineWidth(1);
        for (int x = 0; x < 4; x++) {
            int xpos = Logic.selectedPoints[x][0];
            int ypos = Logic.selectedPoints[x][1];
            int twocellx = (int) Math.floor(xpos / (cell_size / 2));
            int twocelly = (int) Math.floor(ypos / (cell_size / 2));
            switch (x) {
                case 0:
                    g.setColor(Color.decode("#ff0202"));
                    break;
                case 1:
                    g.setColor(Color.decode("#48ff00"));
                    break;
                case 2:
                    g.setColor(Color.decode("#fff000"));
                    break;
                case 3:
                    g.setColor(Color.decode("#ba00ff"));
            }
            if (xpos != 0 && ypos != 0)
                g.fillRect(Render.XposOmegaOffset + twocellx * (cell_size / 2) - (cell_size / 4), Render.YposOmegaOffset + twocelly * (cell_size / 2) - (cell_size / 4), cell_size / 2, cell_size / 2);
        }
    }
}
