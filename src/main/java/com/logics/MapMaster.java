package com.logics;

import org.newdawn.slick.util.Log;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.concurrent.ExecutionException;

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

    public static void EditObject (ObjectTypes type, String data, int id) {
        objects_type[id] = type;
        objects_data[id] = data;
    }

    public static boolean Save (String map_name) {
        try{
            PrintWriter writer = new PrintWriter("" + map_name.replace(" ", "") + ".txt", "UTF-8");

            String[] objects_ids = MapMaster.objects.split(" ");
            writer.println("SPECTRMAP-FILE");
            writer.println("MAP-NAME==" + map_name);
            for (int x = 1; x < objects_ids.length; x++) {
                int id = Integer.parseInt(objects_ids[x]);
                switch (MapMaster.objects_type[id]) {
                    case LINE:
                        writer.println(id + "==0==" + MapMaster.objects_data[id]);
                        break;
                    case RECT:
                        writer.println(id + "==1==" + MapMaster.objects_data[id]);
                        break;
                    case FILLRECT:
                        writer.println(id + "==2==" + MapMaster.objects_data[id]);
                        break;
                    case TEXT:
                        writer.println(id + "==3==" + MapMaster.objects_data[id]);
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

    public static boolean Open (String filepath) {

        // Чистим переменные
        Logic.mapName = "no_name";
        objects = "0";

        try {
            String currentLine;
            BufferedReader br = new BufferedReader(new FileReader(filepath.replace("&&", " ")));
            int ln = 0;
            while ((currentLine = br.readLine()) != null) {
                if (!currentLine.equals("")) {
                    // Дополнительные заголовки в файле
                    boolean rt = true;
                    if (currentLine.split(new String("=="))[0].equals("MAP-NAME")) {
                        Logic.mapName = currentLine.split(new String("=="))[1];
                        rt = false;
                    }
                    if (currentLine.split(new String("=="))[0].equals("CONNECT-PASSIVE")) {
                        Logic.urlPassive = currentLine.split(new String("=="))[1];
                        rt = false;
                    }


                    // Основное потрошение файла
                    if (rt) {
                        try {
                            switch (Integer.parseInt(currentLine.split(new String("=="))[1])) {
                                case 0:
                                    AddObject(ObjectTypes.LINE, currentLine.split(new String("=="))[2]);
                                    break;
                                case 1:
                                    AddObject(ObjectTypes.RECT, currentLine.split(new String("=="))[2]);
                                    break;
                                case 2:
                                    AddObject(ObjectTypes.FILLRECT, currentLine.split(new String("=="))[2]);
                                    break;
                                case 3:
                                    AddObject(ObjectTypes.TEXT, currentLine.split(new String("=="))[2]);
                                    break;
                                default:
                                    Log.warn("I don't know object type with id - " + Integer.parseInt(currentLine.split(new String("=="))[1]));
                                    break;
                            }
                        } catch (Exception e) {
                        }
                    }
                }
            }
            return true;
        } catch (Exception e) {
            Log.error("Error", e);
            return false;
        }
    }
}
