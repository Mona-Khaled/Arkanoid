package arkanoid.game;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class brickMap {
    private Brick map[][];
    private final int HOR_PAD=80;
    private final int VER_PAD=50;
    private int row,col;
    private int nBricks;
    private int nCertain;
 
    public brickMap(int row,int col){
        initMap(row, col);
    }
    
    public void initMap(int row,int col){
       
        map=new Brick[row][col];
  
        for(int i=0;i<map.length;i++){
            for(int j=0;j<map[i].length;j++){
                map[i][j]=new Brick();
                map[i][j].setWidth((ArkanoidGame.WIDTH-HOR_PAD*2)/col);
                map[i][j].setHeight((ArkanoidGame.HEIGHT/2-VER_PAD)/row);
            }
        }
        nCertain=0;
        for(int i=0;i<nCertain;i++){
            int r=(int)(Math.random()*map.length);
            int c=(int)(Math.random()*map[0].length);
            map[r][c].setCollisionState(3);
            
        }
        
         nBricks = (row*col)-nCertain;
    }
    
    public void draw(Graphics2D g){
        for(int i=0;i<map.length;i++){
            for(int j=0;j<map[i].length;j++){
                if(map[i][j].getCollisionState()>0){
                    g.setColor(map[i][j].SetColor());
                    g.fillRect(j*(map[i][j].getWidth())+HOR_PAD, i*(map[i][j].getHeight())+VER_PAD,map[i][j].getWidth(), map[i][j].getHeight());

                    g.setStroke(new BasicStroke(5));
                    g.setColor(Color.white);
                    g.drawRect(j*(map[i][j].getWidth())+HOR_PAD, i*(map[i][j].getHeight())+VER_PAD,map[i][j].getWidth(), map[i][j].getHeight());
                }
            }
        }
    }

    public int getHOR_PAD() {
        return HOR_PAD;
    }

    public int getVER_PAD() {
        return VER_PAD;
    }

    
    public Brick[][] getMap() {
        return map;
    }

    public void setMap(Brick[][] map) {
        this.map = map;
    }

    public int getnBricks() {
        return nBricks;
    }

    public void setnBricks(int nBricks) {
        this.nBricks = nBricks;
    }
    
    
    
    
    
    
    
    
    
    
}
