package game;

import city.cs.engine.*;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseHandler extends MouseAdapter {

    // ---------------------- FIELDS ----------------------
    private WorldView view;

    // ---------------------- CONSTRUCTOR ----------------------
    public MouseHandler(WorldView view) {
        this.view = view;
    }

    // ---------------------- METHODS ----------------------
    @Override
    public void mousePressed(MouseEvent e) {

    }
}
