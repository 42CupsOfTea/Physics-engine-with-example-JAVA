import java.awt.Color;

public class Player extends Square {
    

    public Player(int x, int y, int width, int height, Color colour, double rotation) {
        super(x, y, width, height, colour, rotation);
    }


    
    public void changeXAndYValues (int dX, int dY) {
        x += dX;
        y += dY;
        
        
    }
    public void makePlayerOnTopOfPlatform(int platformY) {
        y = platformY - height ;
    }
    
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public int getHeight() {
        return height;
    }
    public int getWidth() {
        return width;
    }
    
}
