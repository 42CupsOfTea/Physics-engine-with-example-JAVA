import java.util.ArrayList;

public class Physics {
    public boolean alreadyGaveDXAndDY = false;
    public boolean enteredRecursion = false;
    private double finalDx;
    private double finalDy;


    boolean platform1 = false;
    GameLoop game;

    //temp game remove after working
    Physics(GameLoop game) {
        this.game = game;
    }
    public double gravity (double dY) {
        if (dY <= 11) {
            dY += 1;
        }
        return dY;

    }
    public boolean touchingGroundOrObject (GameLoop game) {
        //touching ground
        if (game.player.y + game.player.height >= game.getGround()) {
            game.player.y =  game.getGround() - game.player.height;
            return true;
        }
        
        for (int i = 0; i < game.platforms.size(); i++) {
            if (game.player.getY() + game.player.getHeight() == game.platforms.get(i).getY()-1) {
                if (game.player.getX() + game.player.getWidth() > game.platforms.get(i).getX() && game.player.getX() < game.platforms.get(i).getX() + game.platforms.get(i).getWidth()) {
                    return true;
                }
            } 
        }  
        return false;
    }

    public boolean checkIfPlayerIsCollidingWithPlatform(Points [] points1, Points [] points2) {
        game.screen.enter = false;

        Vector [] axis = new Vector[points1.length + points2.length];
        Points tempPoints [] = points1;
        int a = 0;
        //get all the axis
        for (int i = 0; i + a < axis.length; i++) {
            if (i == 4) {
                a = i;
                tempPoints = points2;
                i =  i - a;
            }
            int nextIndex = (i+1) % points1.length;
            axis[(i+a)] = new Vector((tempPoints[nextIndex].x - tempPoints[i].x) ,(tempPoints[nextIndex].y - tempPoints[i].y));
        }
        //game.screen.drawAxisLineCheck(axis,points2);

        for (int i = 0; i < axis.length; i++) {

            //creates the perpendicular vector with a length of 1
            axis[i] = axis[i].normalize();
            axis[i] = axis[i].makePerpVector();

            double maxPos1 = Double.NEGATIVE_INFINITY;
            double minPos1 = Double.POSITIVE_INFINITY;

            double maxPos2 = Double.NEGATIVE_INFINITY;
            double minPos2 = Double.POSITIVE_INFINITY;
            //sets all corners onto the axis and selects the max and the min for both shapes
            for (int j = 0; j < points1.length; j++) {
                double scalarValue = axis[i].projectPoint(points1[j]);
                if (maxPos1 < scalarValue) {
                    maxPos1 = scalarValue;
                }
                else if (minPos1 > scalarValue) {
                    minPos1 = scalarValue;
                }
            }
            for (int j = 0; j < points2.length; j++) {
                double scalarValue = axis[i].projectPoint(points2[j]);
                if (maxPos2 < scalarValue) {
                    maxPos2 = scalarValue;
                }
                else if (minPos2 > scalarValue) {
                    minPos2 = scalarValue;
                }
            }
            //checks to see if they are overlapping (colliding)
            if (maxPos1 < minPos2 || maxPos2 < minPos1) {
                return false;
            }
        }
        return true;   
    }

    public double [] loopForCheckingIfPlayerIsCollidingWithPlatform (double dX, double dY,Player player, ArrayList<Platforms> platform,double orginalDX, double orginalDy) {

        boolean collidingWithPlatform = false;
        double angle = Math.atan2(dY,dX);
        double velocity = Math.sqrt(dX*dX + dY*dY);

        Points[] points1 = player.getVertices(dX,dY);
        for (int index = 0; index < platform.size(); index++) {
            Points[] points2 = platform.get(index).getVertices(0, 0);

            collidingWithPlatform = checkIfPlayerIsCollidingWithPlatform(points1,points2);
            if (alreadyGaveDXAndDY == false) {
                if (collidingWithPlatform) {
                    enteredRecursion = true;
                    velocity -=  Math.signum(velocity);
                    dX = (velocity * Math.cos(angle));
                    dY = (velocity * Math.sin(angle));
                    loopForCheckingIfPlayerIsCollidingWithPlatform(dX,dY,player,platform,orginalDX,orginalDy);                                      
                }   
            }
                
        }
        
        if (alreadyGaveDXAndDY == false) {
            
            if (enteredRecursion) {
                double ogDX = dX;
                double ogDy = dY;
                Points[] points1X = player.getVertices((orginalDX), 0);
                Points[] points1Y = player.getVertices(0, (orginalDy));
                boolean dontGiveX = false;
                boolean dontGiveY = false;
                for (int index = 0; index < platform.size(); index++) {
                    Points[] pointsPlatform = platform.get(index).getVertices(0, 0);
                    if (checkIfPlayerIsCollidingWithPlatform(points1X,pointsPlatform)) {
                        dontGiveX = true;
                    }
                    if (checkIfPlayerIsCollidingWithPlatform(points1Y,pointsPlatform)) {
                        dontGiveY = true;
                    }
                }
                if (dontGiveX == false) {
                    dX = orginalDX;
                }
                if (dontGiveY == false) {
                    dY = orginalDy;
                }
                Points[] testToSeeIfCrashes = player.getVertices(dX, dY);
                for (int index = 0; index < platform.size(); index++) {
                    Points[] pointsPlatform = platform.get(index).getVertices(0, 0);
                    if (checkIfPlayerIsCollidingWithPlatform(testToSeeIfCrashes,pointsPlatform)) {
                        dX = ogDX;
                        dY = ogDy;
                    }

                }
            }   
            
            finalDx = dX;
            finalDy = dY;
        }
        

        alreadyGaveDXAndDY = true;
        return new double[] {finalDx,finalDy}; 

    }
    
    
}