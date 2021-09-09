package arkanoid.game;
import java.awt.*;
import java.awt.Graphics2D;


public class player implements drawable{
    private int highScore;
    private int lifePoints;
    private boolean win;
    public player(){
        highScore = 0;
        win=false;
        
        lifePoints=3;
    }

    
    @Override
    public void update() {
        
    }

    public void drawLose(Graphics2D g){
        g.setFont(new Font("TimesRoman",Font.PLAIN,50));
        g.setColor(Color.red);
        g.drawString("GAME OVER!", 400, 500);
    }
    
    public void drawWin(Graphics2D g){
        g.setFont(new Font("TimesRoman",Font.PLAIN,50));
        g.setColor(Color.green);
        g.drawString("WINNER!", 400, 500);
    }
    
    @Override
    public void draw(Graphics2D g) {
        String hs="HighScore :"+ highScore;
        String lp = "Life points :" +lifePoints;
        
        g.setFont(new Font("TimesRoman",Font.PLAIN, 20));
        g.setColor(Color.BLACK);
        g.drawString(hs, 0, 20);
        g.drawString(lp, 0, 50);
    }
    
    public void increaseHs(){
        highScore+=10;
    }
    
    

    public void decreaseLifePoints(boolean outOfBoard){
        if(outOfBoard)
        lifePoints--;
    }
    
    public int getHighScore() {
        return highScore;
    }

    public boolean isWin(int nBricks) {
        return(nBricks==0);
    }

    public boolean isLose() {
        
        return(lifePoints<=0);
            
    }

    public void setHighScore(int highScore) {
        this.highScore = highScore;
    }

    public void setWin(boolean win) {
        this.win = win;
    }

    
    
}
