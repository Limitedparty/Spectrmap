package com.graphics.elements;

import com.graphics.Render;
import com.logics.MapMaster;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

/**
 * Отрисовщик графики на карте
 */
public class ObjectsRenderer {

    public static void Renderer (GameContainer gc) {
        Graphics g = gc.getGraphics();
        //g.translate(Render.XposOmegaOffset, Render.YposOmegaOffset);

        // Преобразуем данные
        String[] objects_ids = MapMaster.objects.split(" ");

        // Отрисовываем данные
        for (int x = 1; x < objects_ids.length; x++) {
            int id = Integer.parseInt(objects_ids[x]);
            String[] currentdata = MapMaster.objects_data[id].split(" ");
            switch (MapMaster.objects_type[id]) {
                case LINE:
                    g.setLineWidth(Integer.parseInt(currentdata[4]));
                    try {
                        g.setColor(Color.decode(currentdata[5]));
                    } catch (Exception e) {}
                    g.drawLine(Float.parseFloat(currentdata[0]) + Render.XposOmegaOffset, Float.parseFloat(currentdata[1]) + Render.YposOmegaOffset, Float.parseFloat(currentdata[2]) + Render.XposOmegaOffset, Float.parseFloat(currentdata[3]) + Render.YposOmegaOffset);
                    break;
                case RECT:
                    g.setLineWidth(Integer.parseInt(currentdata[4]));
                    try {
                        g.setColor(Color.decode(currentdata[5]));
                    } catch (Exception e) {}
                    g.drawRect(Float.parseFloat(currentdata[0]) + Render.XposOmegaOffset, Float.parseFloat(currentdata[1]) + Render.YposOmegaOffset, Float.parseFloat(currentdata[2]), Float.parseFloat(currentdata[3]));
                    break;
                case FILLRECT:
                    try {
                        g.setColor(Color.decode(currentdata[4]));
                    } catch (Exception e) {}
                    g.fillRect(Float.parseFloat(currentdata[0]) + Render.XposOmegaOffset, Float.parseFloat(currentdata[1]) + Render.YposOmegaOffset, Float.parseFloat(currentdata[2]), Float.parseFloat(currentdata[3]));
                    break;
                case TEXT:
                    try {
                        g.setColor(Color.decode(currentdata[3]));
                    } catch (Exception e) {}
                    g.drawString(currentdata[2].replace("&&", " "), Integer.parseInt(currentdata[0]) + Render.XposOmegaOffset, Integer.parseInt(currentdata[1]) + Render.YposOmegaOffset);
                default:
                    break;
            }
        }
    }
}
