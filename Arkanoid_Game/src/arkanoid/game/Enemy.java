package arkanoid.game;
import java.awt.Color;
import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Window;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
public class Enemy  {
    
   private int lifePoints;  //of the enemy
   private final int weaponID;
   private int numHits;
   boolean gameover;
   boolean destroyed;
   boolean win;
   private int x,y,dx,dy;
   private int enemysize;
   public final int YPOS = 70;
   private double width, height, startWidth, startHeight;  //height of rect
   private BufferedImage Enemy_image;
   Graphics2D g;
   
    public Enemy()
    {
        numHits=0;   
        weaponID=10;
        gameover=false;
        destroyed=false;
        win=false;
        x=50;
        y=300;
        width=130;      //of image
        startWidth=130;  //height of image
        dx=7;
        dy=7;
        enemysize=40;
        
             
        try{
                Enemy_image = ImageIO.read(new File("C:\\Users\\HP\\Documents\\NetBeansProjects\\Arkanoid Game\\resources\\Enemy.png"));

        }
        catch(Exception e)
        {
                e.printStackTrace();
        }

    }
    
   
   
   public void setPosition()
   {      
        x+=dx;

        if(x<=0||x>=ArkanoidGame.WIDTH-enemysize)  //if hit left wall or if hit right  wall
        {
            dx=-dx;
        }            
   }
   
    
     public  void shooting()
    {
        
        
        
        
    }
    
     
    public boolean destroyed()
    {
           if(numHits==5 && lifePoints==0)
            {
               return true;
            }
           return false;
    }
     public void update()  //move enemy
    {
        setPosition();
    }
    
    public Rectangle getRect() {
		return new Rectangle((int)x, YPOS, 225, 225);
	}
    
    public void draw(Graphics2D g) {
				
		g.drawImage(Enemy_image, (int) x,  YPOS, (int)width, (int)startWidth, null);
    }

    public int getNumHits() {
        return numHits;
    }

    public void setNumHits(int numHits) {
        this.numHits = numHits;
    }

    public int getLifePoints() {
        return lifePoints;
    }

    public void setLifePoints(int lifePoints) {
        this.lifePoints = lifePoints;
    }
    
    
}
