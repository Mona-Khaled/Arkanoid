package arkanoid.game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class enemyPanel extends brickPanel{
    private Enemy theEnemy;
    private myMouseMotionListener themouselistener;
   
    public enemyPanel(){
        init();
    }
    
    
    private void init(){
        theBall =new Ball();
        thePaddle=new Paddle();
        
        theEnemy =new Enemy();
        powerUps =new ArrayList<PowerUp>();
        
        //---for mouse
        themouselistener = new myMouseMotionListener();
        addMouseMotionListener(themouselistener);
        
        running=true;
        
        image=new BufferedImage(ArkanoidGame.WIDTH,ArkanoidGame.HEIGHT,BufferedImage.TYPE_INT_RGB);
        
        g=(Graphics2D)image.getGraphics();
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
       
    }
    
    private void checkBallEnemyCollisions(){
        Rectangle ballRect =theBall.getRect();
        Rectangle enemyRect = theEnemy.getRect();
        
        if(ballRect.intersects(enemyRect))
        {
            theBall.setY(thePaddle.y_pos - theBall.getBallSize() - 1);

            theBall.setSpeed_Dy(-theBall.getSpeed_Dy());

            if(theBall.getX() + theBall.getBallSize()< thePaddle.getX()) {
                    theBall.setSpeed_dx(theBall.getSpeed_dx() -.5);
            }
            if(theBall.getX() >= thePaddle.getX()) {
                    theBall.setSpeed_dx(theBall.getSpeed_dx()+ .5);
            }
            
            theEnemy.setNumHits(theEnemy.getNumHits()+1);
            theEnemy.setLifePoints(theEnemy.getLifePoints()-1);
            
        }
    }
    
    @Override
    public void update()
    {
        checkBallPaddleCollisions();
        
        checkBallEnemyCollisions();
        collisionspower();
        theBall.update();
        thePlayer.decreaseLifePoints(theBall.isOutOfBoard());
        
        for(PowerUp pu:powerUps)
        {
            pu.Update();         
        }
        theEnemy.update();
        thePlayer.update();
        
    }
    
    @Override
    public void draw(Graphics2D g)
    {
        g.setColor(Color.white);
        g.fillRect(0,0, ArkanoidGame.WIDTH,ArkanoidGame.HEIGHT);
        theBall.draw(g);
        thePaddle.draw(g); 
          
        thePlayer.draw(g);
        DrawPowerUp();
        theEnemy.draw(g);
        
        if(theEnemy.destroyed()){
            thePlayer.drawWin(g);
            running = false;
        }
            
        if(thePlayer.isLose()){
            thePlayer.drawLose(g);
            running=false;
        }
        
    }
    
    
    private class myMouseMotionListener implements MouseMotionListener{

        @Override
        public void mouseDragged(MouseEvent e) {}

        @Override
        public void mouseMoved(MouseEvent e) 
                
        { 
            thePaddle.mousemoved(e.getX());
        }
    }
   
    
}
