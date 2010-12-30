/*
 * MouseHandler.java
 * 
 * Created on Apr 3, 2008, 11:40:21 AM
 * 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ioe.core;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import org.apache.log4j.Logger;

import com.ioe.ui.GameCanvas;
/**
 *
 * @author lakesh
 */
public class MouseHandler extends MouseAdapter {
    private GameCanvas gameCanvas;
    private Logger logger;
    
    
    public MouseHandler(GameCanvas gameCanvas) {
        this.gameCanvas = gameCanvas;
        logger = Logger.getLogger("com.ioe.core.MouseHandler");
    }
    
    
    public void mouseClicked(MouseEvent e) {
        logger.info("Mouse clicked");        
        int button = e.getButton();
        int x,y;
        
     
        if(button == e.BUTTON1)  {
            x = e.getX();
            y = e.getY();  
            gameCanvas.MouseResponse(x, y);
        } else if(button == e.BUTTON3) {
            logger.info("Right mouse button clicked");            
            if(gameCanvas.isMovePassAllowed() == true){
                gameCanvas.setmovemade();
            } else {
                logger.info("Pass not allowed");
            }
            
        }
    }
}
