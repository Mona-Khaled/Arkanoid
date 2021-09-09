package arkanoid.game;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
public class PowerUp {
    //________________________fields____________________________________________
    private  int x,width,height;
    private static  int y,dy;
    private  boolean isOnscreen;
    private  Color color;
    private  int value;
    public  final static  int WidePaddle=3;
    public  final static int FastBall=2;
    public  final static  int slowBall=1;
    public  final static int smallpaddle=4;
    //________________________constractor________________________________________
    public  PowerUp(int xStart,int yStart,Brick brick,int theWidth,int theHeight)
    {
        x=xStart;
        y=yStart;
        width=theWidth;
        height=theHeight;
        value = brick.getValue();

          dy=4;
         movePowery();
    }
    //________________________functions__________________________________________
    public int getValue() {
        return value;
    }
    
    public  void movePowery()
    {
       SetColor();
    }
    public void setX(int newx) {
        x = newx;
    }
    public int getX() {
        return x;
    }
    public void draw(Graphics2D g)
    {
        g.setColor(SetColor());
        g.fillRect(x, y, width, height);
    }    
    public void Update()
    {
         y+=dy;
        if(y>ArkanoidGame.HEIGHT)
        {
            isOnscreen=false;
        }
        movePowery();
    }
    public void setY(int y) {
        this.y = y;
    }

    public void setDy(int dy) {
        this.dy = dy;
    }
    public int getDy() {
        return dy;
    }
    public int getY() {
        return y;
    }
    
    /**/public  boolean getIsOnScreen()
    {
        return isOnscreen;
    }
    
    /**/public void setIsOnscreen(boolean isOnscreen)
    {
        this.isOnscreen = isOnscreen;
    }
    
    public  Rectangle getRect()
    {
        return new Rectangle(x,y,width,height);
    }
    
    /**/public  Color SetColor()
    {
        if(value==1)
        {
            color=Color.BLACK;
        }
        else if(value==2)
        {
            color=Color.PINK;
        }
        else if(value==3)
        {
            color=Color.YELLOW;
        }
        else if(value==4)
        {
            color=Color.red;
        }
        return color;
    }
}