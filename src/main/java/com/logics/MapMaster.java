package com.logics;

import org.newdawn.slick.util.Log;

import java.io.PrintWriter;

/**
 * Хранит и обрабатывает карту
 */
public class MapMaster {

    public static String objects = "0";
    public static ObjectTypes[] objects_type = new ObjectTypes[1000000];
    public static String[] objects_data = new String[1000000];

    public static void Init () {
    }

    public static void Tick () {

    }

    public static int GenerateObjectID () {
        int get_id = 0;
        String[] splited_ids = objects.split(" ");

        for (int x = 0; x < splited_ids.length; x++) {
            if (get_id < Integer.parseInt(splited_ids[x])) {
                get_id = x;
            }
        }
        get_id++;
        return get_id;
    }

    public static void AddObject (ObjectTypes type, String data) {
        int id = GenerateObjectID();
        objects_type[id] = type;
        objects_data[id] = data;
        objects = objects + " " + id;
    }

    public static boolean Save (String map_name) {
        try{
            PrintWriter writer = new PrintWriter("test-by-test.txt", "UTF-8");

            String[] objects_ids = MapMaster.objects.split(" ");
            writer.println("SPECTRMAP-FILE");
            writer.println("MAP-NAME+==" + map_name);
            for (int x = 1; x < objects_ids.length; x++) {
                int id = Integer.parseInt(objects_ids[x]);
                switch (MapMaster.objects_type[id]) {
                    case LINE:
                        writer.println(id + "+==0+==" + MapMaster.objects_data[id]);
                        break;
                    case RECT:
                        writer.println(id + "+==1+==" + MapMaster.objects_data[id]);
                        break;
                    case FILLRECT:
                        writer.println(id + "+==2+==" + MapMaster.objects_data[id]);
                        break;
                    case TEXT:
                        writer.println(id + "+==3+==" + MapMaster.objects_data[id]);
                        break;
                    default:
                        break;
                }
            }

            writer.close();
            return true;
        } catch (Exception e) {
            Log.error("SAVE ERROR. ", e);
            return false;
        }
    }
}
