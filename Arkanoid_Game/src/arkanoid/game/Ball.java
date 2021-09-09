
package arkanoid.game;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;


public class Ball implements drawable
{
    private double x,y,speed_dx,speed_dy;
    private int ballSize =60;
    private Rectangle paddleRect;
    
    public Ball()
    {
      x=500;
      y=500;
      speed_dx=8;
      speed_dy=8;
    }
    
    @Override
    public void update()
    {
        setPosition();
    }
    
    public void setPosition()
    {
        if(isOutOfBoard()){
            x=500;
            y=500;
        }
            
          x+=speed_dx;
          y+=speed_dy;

          if(x<=0||x>=ArkanoidGame.WIDTH-ballSize)
              speed_dx=-speed_dx;

          if(y<=0)
              speed_dy=-speed_dy;
          
      
    }
    
    public boolean isOutOfBoard(){
        return(y>=ArkanoidGame.HEIGHT-ballSize);
    }
    
    @Override
    public void draw(Graphics2D g)
    {
      g.setColor(Color.yellow);
      g.fillOval((int)x,(int)y,ballSize,ballSize);
      g.setStroke(new BasicStroke(4));
      g.drawOval((int)x,(int) y, ballSize, ballSize);
    }

    public int getBallSize() {
        return ballSize;
    }
    
    public Rectangle getRect()
    {
     return new Rectangle((int)x, (int)y,ballSize,ballSize);
    }

    public void setSpeed_dx(double speed_dx) {
        this.speed_dx = speed_dx;
    }

    public double getSpeed_dx() {
        return speed_dx;
    }
    
    
    public double getSpeed_Dy()
    {
     return speed_dy;
    }
    
    public void setSpeed_Dy(double theDy)
    {
        speed_dy=theDy;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getX() {
        return x;
    }
    
    
}