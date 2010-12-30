/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ioe.core;

import java.awt.Frame;
import com.ioe.ui.Main;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import org.apache.log4j.Logger;

/**
 *
 * @author lakesh
 */
public class KeyboardHandler extends KeyAdapter {

    private Main main;
    private Logger logger;

    public KeyboardHandler(Main main) {
        this.main = main;
        logger = Logger.getLogger("com.ioe.core.KeyboardHandler");
    }
    
    @Override
    public void keyTyped (KeyEvent e) {        
        if (e.isControlDown() == true) {
            if (e.getKeyChar() == 'N' || e.getKeyChar() == 'n') {
                main.newgame();
            } else if (e.getKeyChar() == 'Q' || e.getKeyChar() == 'q') {
                System.exit(0);
            }
        }
    }
}
