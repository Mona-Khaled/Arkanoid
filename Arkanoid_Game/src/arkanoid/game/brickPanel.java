package arkanoid.game;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.JPanel;


public class brickPanel extends JPanel implements drawable

{
    //fields
    protected boolean running;
    protected BufferedImage image;
    protected Graphics2D g;
   
    
    //entities
    
    protected Ball theBall ;
    protected Paddle thePaddle;
    
    private brickMap theBrickMap;
    protected static player thePlayer=new player();
    protected PowerUp thepower;
    protected  ArrayList<PowerUp> powerUps;
    
    //---for mouse
    private myMouseMotionListener themouselistener;
    
    
    public brickPanel()
    {
        init();
    }
   
    
    private void init()
    {   
        theBall =new Ball();
        thePaddle=new Paddle();
        theBrickMap=new brickMap(2,2);
        
        
        powerUps =new ArrayList<PowerUp>();
        
        //---for mouse
        themouselistener = new myMouseMotionListener();
        addMouseMotionListener(themouselistener);
        
        running=true;
        
        image=new BufferedImage(ArkanoidGame.WIDTH,ArkanoidGame.HEIGHT,BufferedImage.TYPE_INT_RGB);
        
        g=(Graphics2D)image.getGraphics();
       
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
       
    }
    
    
    //--for collesion
    public void checkBallPaddleCollisions()
    {
        Rectangle ballRect =theBall.getRect();
        Rectangle paddleRect =thePaddle.getRect();
       
        
        if(ballRect.intersects(paddleRect))
        {
            theBall.setSpeed_Dy(-theBall.getSpeed_Dy());  
        }
        
    }
    
        
    private void checkBallBrickCollisions(){
        Rectangle ballRect =theBall.getRect();
        
        Brick[][] arr= theBrickMap.getMap();
        A:for(int i=0;i<arr.length;i++){
            for(int j=0;j<arr[i].length;j++){
                if( arr[i][j].getCollisionState()>0){
                Rectangle brickRect = arr[i][j].getRect(j*(arr[i][j].getWidth())+theBrickMap.getHOR_PAD(), i*(arr[i][j].getHeight())+theBrickMap.getVER_PAD());
                
                 
                if(ballRect.intersects(brickRect)){
                    if(arr[i][j].getValue()==1)
                    {
                            powerUps.add(new PowerUp(arr[i][j].getWidth(), arr[i][j].getHeight(),arr[i][j],15, 15));
                    } 
                    else if(arr[i][j].getValue()==2)
                    {
                            powerUps.add(new PowerUp(arr[i][j].getWidth(), arr[i][j].getHeight(),arr[i][j],15, 15));
                    }
                    else if(arr[i][j].getValue()==3)
                    {
                           powerUps.add(new PowerUp(arr[i][j].getWidth(), arr[i][j].getHeight(),arr[i][j],15, 15));                           
                    }
                    else if(arr[i][j].getValue()==4)
                    {
                           powerUps.add(new PowerUp(arr[i][j].getWidth(), arr[i][j].getHeight(),arr[i][j],15, 15));
                    }

                    if(arr[i][j].getCollisionState()<3){
                    arr[i][j].setCollisionState(arr[i][j].getCollisionState()-1);
                    thePlayer.increaseHs();
                    
                    if(arr[i][j].getCollisionState()==0)
                    theBrickMap.setnBricks(theBrickMap.getnBricks()-1);
                    
                    }
                    
                    
                    theBall.setSpeed_Dy(-theBall.getSpeed_Dy());
                    theBall.setSpeed_dx(-theBall.getSpeed_dx());
                
                        break A;
                }
                }
            }
        }
    }
    
    public void collisionspower(){
            Rectangle paddlerect= thePaddle.getRect();
            for(int i=0;i<powerUps.size();i++)
            {
                Rectangle recup=powerUps.get(i).getRect();
                if(paddlerect.intersects(recup))
                 {
                     if(powerUps.get(i).getValue()==PowerUp.FastBall)
                     {
                          theBall.setSpeed_dx(30);
                          theBall.setSpeed_Dy(30);
                     }
                     else if(powerUps.get(i).getValue()==PowerUp.slowBall)
                     {
                         theBall.setSpeed_dx(5);
                         theBall.setSpeed_Dy(5);
                     }
                     else if(powerUps.get(i).getValue()==PowerUp.WidePaddle)
                     {
                          thePaddle.setWidth(40);
                     }
                     else if(powerUps.get(i).getValue()==PowerUp.smallpaddle)
                     {
                          thePaddle.setWidth(150);
                     }
                 }
            }
        }  
    
    
    @Override
    public void update()
    {
        checkBallPaddleCollisions();
        checkBallBrickCollisions();
        
        collisionspower();
        theBall.update();
        thePlayer.decreaseLifePoints(theBall.isOutOfBoard());
       
        for(PowerUp pu:powerUps)
        {
            pu.Update();         
        }
        
        thePlayer.update();
        
    }
    
    
    public  void DrawPowerUp()
    {
        for(PowerUp pu:powerUps)
        {
            pu.draw(g);
        }
    }
    
    @Override
    public void draw(Graphics2D g)
    {
        g.setColor(Color.white);
        g.fillRect(0,0, ArkanoidGame.WIDTH,ArkanoidGame.HEIGHT);
        theBall.draw(g);
        thePaddle.draw(g); 
        theBrickMap.draw(g);   
        thePlayer.draw(g);
        DrawPowerUp();
        
        
        if(thePlayer.isWin(theBrickMap.getnBricks()) ){
            thePlayer.drawWin(g);
            running = false;
        }
            
        if(thePlayer.isLose()){
            thePlayer.drawLose(g);
            running=false;
        }
        
    }
    
    @Override
    public void paintComponent(Graphics g)
    {
        Graphics2D g2=(Graphics2D)g ;
        g2.drawImage(image,0, 0,ArkanoidGame.WIDTH,ArkanoidGame.HEIGHT,null);
        g2.dispose();      
    }
    
    public boolean levelPassed(){
        return (thePlayer.isWin(theBrickMap.getnBricks()));
    }
    
    public void run()
    {
        
        while(running)
        {
            update();
            
            draw(g);
         
            repaint();
            
            try
            {
                Thread.sleep(30);
            }
            
            catch(Exception e)
            {
             e.printStackTrace();
            }
            
            
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
