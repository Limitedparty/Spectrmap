package com.logics;

import com.graphics.elements.Grid;

/**
 * Задает координаты для точки в зависимости от режима и прочей фигни
 */
public class PointSelector {

    public enum SelectorType {
        MOUSEBUTTONS,
        KEYS
    }

    public static void Select (int id, int xpos, int ypos) {
        Logic.selectedPoints[id][0] = xpos;
        Logic.selectedPoints[id][1] = ypos;
    }

    public static void Clear () {
        Logic.selectedPoints = new int[4][2];
    }

    public static int GetPos (Grid.GridTool type, int pos) {
        switch (type) {
            case CELL_SELECT:
                return (int) Math.floor(pos / Grid.cell_size) * Grid.cell_size + Grid.cell_size / 2;
            case TWO_VERTEX:
                return (int) Math.floor(pos / (Grid.cell_size / 2)) * (Grid.cell_size / 2);
            default:
                break;
        }
        return 0;
    }

}
