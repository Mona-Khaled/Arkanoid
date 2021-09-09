package arkanoid.game;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public  class Paddle implements drawable 
{
    private double x;
    private int width,height;
     private Graphics2D g;
    public final int y_pos=ArkanoidGame.HEIGHT-100;
    private Rectangle paddleRect;
    
    
    public Paddle()
    {
        width=100;
        height=20;
        x=ArkanoidGame.WIDTH/2-width/2;    
    }
    
    @Override
    public void update()
    {
        
    }
    //------------------------- Draw paddle---------------------------------
    
    @Override
      public void draw(Graphics2D g)
    {
        g.setColor(Color.darkGray);
        g.fillRect((int)x, y_pos, width, height);   
    }
      
      
      public void mousemoved(int mouseXpos)
      {
          x=mouseXpos-width/2;
          
          if(x>ArkanoidGame.WIDTH-width)
          {
           x=ArkanoidGame.WIDTH-width;
          }
           if(x<0)
          {
           x=0;
          }     
      }
      
      //---------------------------------------------------
      
      //--for collision
       public Rectangle getRect()
    {
      return new Rectangle((int)x,y_pos,width,height);
    }

    public double getX() {
        return x;
    }

    public void setWidth(int width) {
        this.width = width;
    }
       
       


    
}