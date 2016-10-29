package com.logics;

import com.graphics.Render;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.util.Log;

/**
 * Ловит нужные нажатия
 */
public class InputMaster {

    public static boolean mouseBut2 = false;

    public static void Tick (GameContainer gc) {
        Input input = gc.getInput();

        // LEFT MOUSE BUTTON PRESSED
        if (input.isMousePressed(0)) {
            if (PointSelector.SelectorType.MOUSEBUTTONS == Logic.selectorType) {
                PointSelector.Select(0, PointSelector.GetPos(Logic.selectorTool, input.getMouseX() - Render.XposOmegaOffset), PointSelector.GetPos(Logic.selectorTool, input.getMouseY() - Render.YposOmegaOffset));
            }
        }

        // RIGHT MOUSE BUTTON PRESSED
        if (input.isMousePressed(1)) {
            if (PointSelector.SelectorType.MOUSEBUTTONS == Logic.selectorType) {
                PointSelector.Select(1, PointSelector.GetPos(Logic.selectorTool, input.getMouseX() - Render.XposOmegaOffset), PointSelector.GetPos(Logic.selectorTool, input.getMouseY() - Render.YposOmegaOffset));
            }
        }

        // MIDDLE MOUSE BUTTON PRESSED
        if (input.isMousePressed(2)) {
            mouseBut2 = true;
            Render.XposOmegaStart = input.getMouseX() - Render.XposOmegaOffset;
            Render.YposOmegaStart = input.getMouseY() - Render.YposOmegaOffset;
        }

        // MIDDLE MOUSE BUTTON DOWNED
        if (input.isMouseButtonDown(2)) {
            Render.OmegaOffsetTick(input.getMouseX(), input.getMouseY());
        } else {
            mouseBut2 = false;
        }

        // ESCAPE BUTTON PRESSED
        if (input.isKeyPressed(Input.KEY_ESCAPE)) {
            CommandMaster.MouseGrab();
        }
    }
}
