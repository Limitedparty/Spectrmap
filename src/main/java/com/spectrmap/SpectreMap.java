package com.spectrmap;

/**
 * Very main file
 * Самый главный файл. Отвечает за работу всего. Прямо всего.
 */

// Imports
import java.util.logging.Level;
import java.util.logging.Logger;

import com.graphics.elements.Grid;
import com.logics.*;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.util.Log;

// Core imports
import com.graphics.Render;
import com.graphics.gui.GUIController;

public class SpectreMap extends BasicGame {

    public static AppGameContainer app;

    public SpectreMap(String title) {
        super(title);
    }

    // Инициализация
    @Override
    public void init(GameContainer container) throws SlickException {
        // Устанавливаем контейнер для обработчика консоли
        CommandMaster.gc = container;
        // Выставляем дефолтные инструменты
        Logic.currentWindowState = WindowState.MAP_EDITOR;
        Logic.selectorTool = Grid.GridTool.TWO_VERTEX;
        Logic.selectorType = PointSelector.SelectorType.MOUSEBUTTONS;
        // Подготавливаем карту, если нужно
        MapMaster.Init();
    }

    // Логика
    @Override
    public void update(GameContainer container, int delta) throws SlickException {
        Logic.Tick(container, delta);
    }

    // Отрисовка графики
    @Override
    public void render(GameContainer container, Graphics g) throws SlickException {
        Render.Tick(container);
    }


    public static void main (String[] args) throws SlickException {
        Log.info("Starting SpectrMap...");
        Log.info("Setup GUI");
        GUIController.OpenMainMenu();

        app = new AppGameContainer(new SpectreMap("SpectreMap"));

        app.setDisplayMode(1200, 800, false);
        app.setTargetFrameRate(60);
        app.setShowFPS(false);
        app.setAlwaysRender(true);
        //app.setMouseGrabbed(true);

        app.start();
    }
}
