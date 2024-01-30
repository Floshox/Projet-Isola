import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;
import javax.imageio.*;
import java.lang.Math;
import javax.swing.*;

/**
* Cette classe permet de créer une brique.
*
* @see Curseur
*
* @author Florian Cassagne
*/
public class Brique
{
    
/**
* Pour définir les coordonnés <b>x</b> et <b>y</b> de la brique.
*/
    private int x, y;
    
/**
* Constructeur pour créer une brique à partir des coordonnés x et y du curseur. 
*
* @param x_curseur 
* @param y_curseur 
*/
    public Brique(int x_curseur, int y_curseur){
        this.x = x_curseur;
        this.y = y_curseur;
    }
    
/**
* Pour obtenir la position x de la brique. 
*
* @return x
*/
    public int getX(){
        return x;
    }
    
/**
* Pour obtenir la position y de la brique. 
*
* @return y
*/
    public int getY(){
        return y;
    }

/**
* Pour modifier la position x de la brique. 
*
* @param x
*/
    public void setX(int _x){
        this.x = _x;
    }
    
/**
* Pour modifier la position y de la brique. 
*
* @param y
*/
    public void setY(int _y){
        this.y = _y;
    }
    
        
}

