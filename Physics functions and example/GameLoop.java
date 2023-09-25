import java.awt.Color;

import java.awt.event.ActionEvent;
import javax.swing.Timer;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class GameLoop {
    Player player;
    EventTracker tracker = new EventTracker();
    Physics physics = new Physics(this);
    Screen screen;

    ArrayList<Platforms> platforms = new ArrayList<Platforms>();
    private int ground = 700;

    private double dY,dX;

    public void passInScreen(Screen screen) {
        this.screen = screen;
    }

    GameLoop() {
        player = new Player(200, 0, 50, 50, Color.BLUE,0);
        makePlatforms();
    }
    private void makePlatforms () {
        platforms.add(new Platforms(200,590,300,50,Color.white,0));




        platforms.add(new Platforms(600,600,300,50,Color.white,0));
        platforms.add(new Platforms(800,500,300,50,Color.white,0));
        platforms.add(new Platforms(500,380,300,50,Color.white,0));

    }

    public void initialize() {
        screen.repaint();
        mainLoop();
    }

    public void mainLoop() {
        Timer timer = new Timer (5, new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                //System.out.println("moo");
                screen.update();
                screen.repaint();
            
            }
            
        });
        timer.start();
    }
    public void getPlayerMovmentChanges () {
        boolean [] keyPressedArray = tracker.getMovmentKeyPresses();
        int speed = tracker.getSpeedForPlayer();
        dX = 0;
        
        if (keyPressedArray[2]) {
            dX = dX - speed;
        }
        if (keyPressedArray[3]) {
            dX = dX + speed;
        }   
        if (physics.touchingGroundOrObject(this) ) {
            dY = 0;
            if (keyPressedArray[0]) {
                
                dY = - 15;
            }
        }
        else {
            dY = physics.gravity(dY);
        }
        
        physics.alreadyGaveDXAndDY = false;
        physics.enteredRecursion = false;
        double orginalDX = dX;
        double orginalDy = dY;
        double [] tempArrayOfVelocity = physics.loopForCheckingIfPlayerIsCollidingWithPlatform(dX,dY,player,platforms,orginalDX,orginalDy);
        
        int passIndX = (int) tempArrayOfVelocity[0];
        int passIndY = (int) tempArrayOfVelocity[1];

        //player.changeXAndYValues((int) dX, (int)dY);
        player.changeXAndYValues( passIndX, passIndY);
    }
    
    

    public void changeDY(int changeTo) {
        dY = changeTo;
    }
    public void changeDX(int changeTo) {
        dX = changeTo;
    }
    public int getGround() {
        return ground;
    }
    public double getDX () {
        return dX;
    }
    public double getDY () {
        return dY;
    }
    public ArrayList<Platforms> getPlatforms () {
        return platforms;
    }

}
