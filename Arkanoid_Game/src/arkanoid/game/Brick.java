package arkanoid.game;

import java.awt.Color;
import java.awt.Rectangle;
import jdk.nashorn.internal.parser.TokenType;

public class Brick {
    private int value; //power up id
    private Color brickColor;
    private int collisionState; //no. of hits to be broken
    private int hitCounter;
    private boolean certainBrick;
    static private int height,width;
    
    public Brick()
    {
        initBrick();
    }
    
    private void initBrick(){
        hitCounter = 0;
        value = (int)(Math.random()*6 +1);
        collisionState = (int)(Math.random()*2 +1);
    }
    
  
    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setWidth(int width) {
        this.width = width;
    }
    
    boolean isCertain(){
        return(collisionState==3);
    }
    
    public Color SetColor(){
        if(collisionState==1)
            brickColor=Color.BLUE;
        
        else if(collisionState==2)
            brickColor=Color.CYAN;
        
        else if(collisionState==3)
            brickColor=Color.GRAY;
        return brickColor;
    }

    public int getCollisionState() {
        return collisionState;
    }
    /**/
    Rectangle getRect(int x,int y){
        return new Rectangle(x, y, width, height);
    }

    public int getValue() {
        return value;
    }

    public void setCollisionState(int collisionState) {
        this.collisionState = collisionState;
    }
            
    
}
