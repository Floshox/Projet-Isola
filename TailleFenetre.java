import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;
import javax.imageio.*;
import java.lang.Math;
import javax.swing.*;

/**
* Cette classe permet de prendre en compte la hauteur et la largeur de la fenêtre afin d'instancier par la suite, la fenêtre affichant le plateau.
*
* @see FenetrePlateau
*
* @author Florian Cassagne
*/
public class TailleFenetre{

/**
* Pour définir la largeur de la fenêtre
*
*/
    static private int largeur = 900;
    
/**
* Pour définir la hauteur de la fenêtre
*
*/
    static private int hauteur = 700;
    
/**
* Getter pour obtenir la largeur de la fenêtre
*
* @return largeur
*/
    static public int getLargeur(){
        return largeur;
    }
    
/**
* Getter pour obtenir la hauteur de la fenêtre
*
* @return hauteur
*/
    static public int getHauteur(){
        return hauteur;
    }
    
/**
* Setter pour modifier la largeur de la fenêtre
*
* @param largeur
*/
    static public void setLargeur(int _largeur){
        largeur = _largeur;
    }
    
/**
* Setter pour modifier la hauteur de la fenêtre
*
* @param hauteur
*/
    static public void setHauteur(int _hauteur){
        hauteur = _hauteur;
    }
    
}