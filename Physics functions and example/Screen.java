import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.JPanel;

public class Screen extends JPanel{

    boolean enter = false;
    Vector [] axis;
    Points [] points;
    Graphics2D graphics;


    GameLoop game;

    Screen (GameLoop game) {
        this.game = game;
    }
    //@Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        this.graphics = g2d;
        paintPlayer(g2d);
        paintPlatforms(g2d);
        
        // if (enter == true) {

        //     drawAxisLine(axis, points);
        // }
        // drawAxisLine(axis, points);

    }
    // public void drawAxisLineCheck(Vector [] axis, Points [] points) {
    //     this.axis = axis;
    //     this.points = points;
    //     enter = true;
        
    //     repaint();
    // }
    public void update() {
        game.getPlayerMovmentChanges();
        
    }
    
    
    public void paintPlayer(Graphics2D g2d) {
        game.player.paintPolygon(g2d);
    }
    public void paintPlatforms (Graphics2D g2d) {
        ArrayList<Platforms> platforms = game.getPlatforms();
        for (int index = 0; index < platforms.size(); index++) {
            
            platforms.get(index).paintPolygon(g2d);
        }
    }
    public void drawAxisLine(Vector [] axis,Points [] points) {
        
        int counter = 4;
        for (int i = 0; i < game.platforms.size(); i++) {
            
        }
        double minX = 0;
        double minY = 0;

        for (int i = 0; i < axis.length; i++) {
            if (!(axis[i].x == 0 || axis[i].y == 0)) {
                if (minX > axis[i].x) {
                    minX = axis[i].x;
                }
                if (minY > axis[i].y) {
                    minY = axis[i].y;
                }
            }
        }
        System.out.println(minX + " " + minY);
        for (counter = 4;counter < axis.length; counter++) {
            int pointsCounter = counter - 4;

            int x1 =(int) points[pointsCounter].x;
            int y1 =(int) points[pointsCounter].y;

            int x2 = (int) axis[counter].x + x1;
            int y2 = (int) axis[counter].y + y1;

            graphics.setColor(Color.blue);
            graphics.drawLine(x1, y1, x2, y2);
        }

        
    }
}
