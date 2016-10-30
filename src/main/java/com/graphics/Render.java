package com.graphics;

/**
 * Вызывает все, что всязано с отрисовкой на экране
 */

import com.logics.Logic;
import com.logics.WindowState;
import org.newdawn.slick.*;

import com.graphics.elements.*;

public class Render {

    // Значения текущего смещения
    public static int XposOmegaOffset = 0;
    public static int YposOmegaOffset = 0;
    // Значения стартовой позиции мышки до начала смещения (для системы перетаскивания)
    public static int XposOmegaStart = 0;
    public static int YposOmegaStart = 0;

    public static void Tick (GameContainer gc) {
        Input input = gc.getInput();

        switch (Logic.currentWindowState) {
            case MAP_EDITOR:
                // Установка фона
                gc.getGraphics().setBackground(Color.decode("#001f3f"));

                Grid.Renderer(gc);
                ObjectsRenderer.Renderer(gc);
                Grid.RendererSelectedPoints(gc);
                InfoRenderer.Renderer(gc, WindowState.MAP_EDITOR);
                Grid.RendererTool(gc, Logic.selectorTool, input.getMouseX(), input.getMouseY());
                MouseRenderer.Renderer(gc, input.getMouseX(), input.getMouseY());

                break;
            case MAP_VIEW:
                // Установка фона
                gc.getGraphics().setBackground(Color.decode("#001f3f"));

                Grid.Renderer(gc);
                ObjectsRenderer.Renderer(gc);
                Grid.RendererSelectedPoints(gc);
                InfoRenderer.Renderer(gc, WindowState.MAP_VIEW);
                MouseRenderer.Renderer(gc, input.getMouseX(), input.getMouseY());

                break;
            default:
                break;
        }
    }

    // Система перетаскивания
    public static void OmegaOffsetTick (int xpos, int ypos) {
        XposOmegaOffset =  (int) Math.floor((xpos - XposOmegaStart) / 10) * 10;
        YposOmegaOffset =  (int) Math.floor((ypos - YposOmegaStart) / 10) * 10;
    }
}
