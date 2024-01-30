import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;
import javax.imageio.*;
import java.lang.Math;
import javax.swing.*;


/**
* Cette classe permet de créer un curseur qui peut être déplacé par l'utilisateur et utilisé pour déplacer un joueur ou poser une brique.
*
* @see Joueur, Brique
*
* @author Florian Cassagne
*/
public class Curseur
{
    
/**
* Pour définir les coordonnés <b>x</b> et <b>y</b> du curseur.
*/
    private int x, y;

/**
* Constructeur de base pour créer un curseur. 
*
*/
    public Curseur(){
        double x_double = Math.floor(Global.NB_CASES_X/2);
        this.x = (int)x_double;
        double y_double = Math.floor(Global.NB_CASES_Y/2);
        this.y = (int)y_double;
        
    }

/**
* Pour obtenir la position x du curseur. 
*
* @return x 
*/
    public int getX(){
        return x;
    }
    
/**
* Pour obtenir la position y du curseur. 
*
* @return y
*/
    public int getY(){
        return y;
    }

/**
* Pour modifier la position x du curseur. 
*
* @param x
*/
    public void setX(int _x){
        this.x = _x;
    }
    
/**
* Pour modifier la position y du curseur. 
*
* @param y 
*/
    public void setY(int _y){
        this.y = _y;
    }
    
/**
* Pour déplacer le curseur à droite. 
*
*/
    public void DeplacerDroite(){
        if (x < Global.NB_CASES_X-2){
            this.x = x + 1;
        }
    }
    
/**
* Pour déplacer le curseur à gauche. 
*
*/
    public void DeplacerGauche(){
        if (x > 1){
            this.x = x - 1;
        }
    }
    
/**
* Pour déplacer le curseur en haut. 
*
*/
    public void DeplacerHaut(){
        if (y > 1){
            this.y = y - 1;
        }
    }
    
/**
* Pour déplacer le curseur en bas. 
*
*/
    public void DeplacerBas(){
        if (y < Global.NB_CASES_Y-2){
            this.y = y + 1;
        }
    }
        
}

