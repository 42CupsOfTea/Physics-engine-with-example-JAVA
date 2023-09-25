import java.awt.Color;
import java.awt.Graphics2D;

public class Square {
    protected int x, y, width, height;
    protected double rotation;
    protected Color colour;
    protected double centreX,centreY;
    
    public Square(int x, int y, int width, int height, Color colour,double rotation) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.colour = colour;
        this.rotation = rotation;


    }
    
    public void paint(Graphics2D g) {
        g.setColor(colour);
        g.fillRect(x, y, width, height);
    }

    public Points [] getVertices(double tempX, double tempY) {
            centreX = tempX + x + width/2;
        centreY = tempY + y + height/2;
        //how the array works for the vertices and midpoints
        //0   1   2   3   4   5   6   7
        //x1, y1, x2, y2, x3, y3, x4, y4 


        Points [] points = new Points[4];
        points[0] = new Points(tempX + x , tempY + y);
        points[1] = new Points(tempX + x + width , tempY + y);
        points[2] = new Points(tempX + x + width , tempY + y + height);
        points[3] = new Points(tempX + x , tempY + y + height);

        if (rotation != 0 && rotation != 360) {
            Points [] rotationPoints = new Points[points.length];
            for (int i = 0; i < rotationPoints.length; i++) {
                rotationPoints[i] = points[i];
            }
            double angle = Math.toRadians(rotation);
            //offsets the points so the centre is at 0,0 this allows for more simple calculations.
            for (int i = 0; i < points.length; i++) {
                rotationPoints[i].x = (rotationPoints[i].x) - centreX;
                rotationPoints[i].y = (rotationPoints[i].y ) - centreY;
            }
            //rotates each corner and shifts back to original location
            for (int i = 0; i < points.length; i++) {
                
                double newX = (rotationPoints[i].x * Math.cos(angle) - rotationPoints[i].y * Math.sin(angle)) + (centreX);
                double newY = (rotationPoints[i].x * Math.sin(angle) + rotationPoints[i].y * Math.cos(angle)) + (centreY);
                rotationPoints[i].x = newX;
                rotationPoints[i].y = newY;
                
            }
            return rotationPoints;
        }
        else {
            return points;
        }   
    }
    public void paintPolygon(Graphics2D g2d) {
        //getting points as a 2d array
        Points [] tempPoints = getVertices(0, 0);
        int [] Xpoints = new int[tempPoints.length]; 
        int [] Ypoints = new int[tempPoints.length]; 
        for (int i = 0; i < tempPoints.length; i++) {
            Xpoints[i] = (int) tempPoints[i].x;
            Ypoints[i] =(int) tempPoints[i].y;
        }


        g2d.setColor(colour);
        g2d.drawPolygon(Xpoints, Ypoints, tempPoints.length);
        g2d.fillPolygon(Xpoints, Ypoints, tempPoints.length);

        g2d.drawRect(x, y, width, height);;
        
    }
    
    
   
}
