package com.logics;

import org.newdawn.slick.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * Пассивное обновление данных карты с веб сервера
 */
public class PassiveUpdate extends Thread {

    @Override
    public void run() {
        Update(Logic.urlPassive);
    }

    public static void Update (String url) {
        try {
            URL site = new URL(url);

            BufferedReader in = new BufferedReader(new InputStreamReader(site.openStream()));
            String currentLine;
            while ((currentLine = in.readLine()) != null) {
                if (!currentLine.equals("")) {
                    // Дополнительные заголовки в файле
                    boolean rt = true;

                    // Основное потрошение файла
                    if (rt) {
                        try {
                            switch (Integer.parseInt(currentLine.split(new String("=="))[1])) {
                                case 0:
                                    MapMaster.EditObject(ObjectTypes.LINE, currentLine.split(new String("=="))[2], Integer.parseInt(currentLine.split(new String("=="))[0]));
                                    break;
                                case 1:
                                    MapMaster.EditObject(ObjectTypes.RECT, currentLine.split(new String("=="))[2], Integer.parseInt(currentLine.split(new String("=="))[0]));
                                    break;
                                case 2:
                                    MapMaster.EditObject(ObjectTypes.FILLRECT, currentLine.split(new String("=="))[2], Integer.parseInt(currentLine.split(new String("=="))[0]));
                                    break;
                                case 3:
                                    MapMaster.EditObject(ObjectTypes.TEXT, currentLine.split(new String("=="))[2], Integer.parseInt(currentLine.split(new String("=="))[0]));
                                    break;
                                default:
                                    Log.warn("I don't know object type with id - " + Integer.parseInt(currentLine.split(new String("=="))[1]));
                                    break;
                            }
                        } catch (Exception e) {}
                    }
                }
            }
            in.close();
            Logic.udpReadEnded = true;

        } catch (Exception e) {
            Log.error("SITE READ ERROR!", e);
            Logic.udpReadEnded = true;
        }
    }

}
