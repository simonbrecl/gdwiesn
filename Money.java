import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;
/**
 * Write a description of class Money here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
*/

public class Money extends Actor
{
    
     private GreenfootImage sb;
     private GreenfootImage board;
     private int money = 0;
     
    public Money () {
        int boardWidth = 110;
        int boardHeight = 30;
        int boardTransparency = 150;
        board = new GreenfootImage (boardWidth, boardHeight);
        board.setColor(Color.white);
        board.setTransparency (boardTransparency);
        board.fillRect (0, 0, boardWidth, boardHeight);
        this.setImage(board);
        update();   
    }
    
     public void act () {      
    }
       
    public void setMoney (int score) {
        this.money=money;
    }
    
    public int getMoney() {
        return money;
    }
  
    public void addMoney (int pts) {
        money += pts;
        update();
    }
    
    private void update () {
        sb = new GreenfootImage(board);
        sb.drawImage (new GreenfootImage(money+"€",18,Color.black, Color.white),25,5);
        this.setImage(sb);
    }
}
