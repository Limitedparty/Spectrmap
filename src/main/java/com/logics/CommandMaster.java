package com.logics;

import com.graphics.elements.Grid;
import com.graphics.elements.ObjectsRenderer;
import com.spectrmap.SpectreMap;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.util.Log;

/**
 * Обработка команд (из консоли)
 */
public class CommandMaster {

    public static GameContainer gc;

    public static String EXE (String cmd) {
        String[] commands = cmd.split(" ");

        // Перебираем команды
        if (commands[0].equals("help")) {
            return help();
        } else if (commands[0].equals("mode")) {
            return mode_changer(commands[1]);
        } else if (commands[0].equals("selector") || commands[0].equals("sel")) {
            return selector_changer(commands[1]);
        } else if (commands[0].equals("setcolor") || commands[0].equals("setc")) {
            return color_changer(commands[1]);
        } else if (commands[0].equals("setwidth") || commands[0].equals("setw")) {
            return  width_changer(commands[1]);
        } else if (commands[0].equals("dline") || commands[0].equals("line")) {
            return draw_line();
        } else if (commands[0].equals("drect") || commands[0].equals("rect")) {
            return draw_rect();
        } else if (commands[0].equals("dfillrect") || commands[0].equals("fillrect") || commands[0].equals("frect")) {
            return fill_rect();
        } else if (commands[0].equals("dtext") || commands[0].equals("text")) {
            return draw_text(commands[1]);
        } else if (commands[0].equals("undo") || commands[0].equals("u")) {
            return Undo();
        } else if (commands[0].equals("list") || commands[0].equals("l")) {
            return List();
        } else if (commands[0].equals("save")) {
            return Save(commands[1]);
        } else if (commands[0].equals("open")) {
            return Open(cmd.replace("open ", "").replace(" ", "&&"));
        }

        return "Error. (cmd)";
    }

    public static String help () {
        return new String ("+ SpectreMap help:" +
                "\n  help - see this message" +
                "\n  mode <view/editor> - change mode (for edit or for only view)" +
                "\n  list - get list of objects" +
                "\n+ Editor commands:" +
                "\n  selector <2vert/sector/pointer> - change selector tool method" +
                "\n  setcolor <#hex> - set color for draw" +
                "\n  setwidth <width(int)> - set line width" +
                "\n  dline - draw new object line" +
                "\n  drect - draw new object rect" +
                "\n  dfiilrect - draw new object filled rect" +
                "\n  dtext <your&&text> - draw text" +
                "\n  undo - remove last object (from index)" +
                "\n  save <map_name> - save map" +
                "\n  open <file path> - open map");
    }

    public static String mode_changer (String mode) {

        if (mode.equals("view")) {
            Logic.currentWindowState = WindowState.MAP_VIEW;
            return "Mode changed to view mode.";
        } else if (mode.equals("editor")) {
            Logic.currentWindowState = WindowState.MAP_EDITOR;
            return "Mode changed to editor mode.";
        }

        return "Mode change error. Try editor or view.";
    }

    public static String selector_changer (String mode) {
        if (mode.equals("2vert")) {
            Logic.selectorTool = Grid.GridTool.TWO_VERTEX;
            return "OK.";
        } else if (mode.equals("sector")) {
            Logic.selectorTool = Grid.GridTool.CELL_SELECT;
            return "OK.";
        } else if (mode.equals("pointer")) {
            Logic.selectorTool = Grid.GridTool.MOUSE_POINT;
            return "OK.";
        } else {
            return "Selector error. Try 2vert, sector, pointer";
        }
    }

    public static String color_changer (String color) {
        try {
            Integer.parseInt(color.replace("#", ""), 16);
            Logic.currentColor = color;
            return "Color changed.";
        } catch (Exception e) {
            return "Color error. Please, use color in hex.";
        }
    }
    public static String width_changer (String width) {
        try {
            Logic.LineWidth = Integer.parseInt(width);
            return "Width changed.";
        } catch (Exception e) {
            return "Width error. Please, use int for argument.";
        }
    }

    public static String draw_line () {
        MapMaster.AddObject(ObjectTypes.LINE, Logic.selectedPoints[0][0] + " " + Logic.selectedPoints[0][1] + " " + Logic.selectedPoints[1][0] + " " + Logic.selectedPoints[1][1] + " " + Logic.LineWidth + " " + Logic.currentColor);
        return "OK.";
    }

    public static String draw_rect () {
        int q1 = Logic.selectedPoints[1][0] - Logic.selectedPoints[0][0];
        int q2 = Logic.selectedPoints[1][1] - Logic.selectedPoints[0][1];
        MapMaster.AddObject(ObjectTypes.RECT, Logic.selectedPoints[0][0] + " " + Logic.selectedPoints[0][1] + " " +  q1 + " " + q2 + " " + Logic.LineWidth + " " + Logic.currentColor);
        return "OK.";
    }

    public static String fill_rect () {
        int q1 = Logic.selectedPoints[1][0] - Logic.selectedPoints[0][0];
        int q2 = Logic.selectedPoints[1][1] - Logic.selectedPoints[0][1];
        MapMaster.AddObject(ObjectTypes.FILLRECT, Logic.selectedPoints[0][0] + " " + Logic.selectedPoints[0][1] + " " +  q1 + " " + q2 + " " + Logic.currentColor);
        return "OK.";
    }

    public static String draw_text (String text) {
        MapMaster.AddObject(ObjectTypes.TEXT, Logic.selectedPoints[0][0] + " " + Logic.selectedPoints[0][1] + " " + text + " " + Logic.currentColor);
        return "OK.";
    }

    public static void MouseGrab () {
        gc.setMouseGrabbed(!gc.isMouseGrabbed());
    }

    public static String Undo () {
        try {
            String[] mp_sp = MapMaster.objects.split(" ");
            MapMaster.objects = MapMaster.objects.substring(0, MapMaster.objects.lastIndexOf(" ")) + "";
            return "OK.";
        } catch (Exception e) {
            return "FAIL.";
        }
    }
    public static String List () {
        return  MapMaster.objects;
    }

    public static String Save (String map_name) {
        if (MapMaster.Save(map_name))
            return "OK.";
        else
            return "FAIL.";
    }

    public static String Open (String file_path) {
        if (MapMaster.Open(file_path)) {
            return "OK.";
        } else {
            return "FAIL.";
        }
    }

}
