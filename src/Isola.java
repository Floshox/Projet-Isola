import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;
import javax.imageio.*;
import java.lang.Math;
import javax.swing.*;

public class Isola
{

/**
* Pour créer un timer
*
* @see java.util.Timer
*
*/
    static java.util.Timer timer;
    
/**
* Constructeur d'Isola qui contient une méthode venant de la classe TimerTask
*
*/
    public Isola(){
        java.util.TimerTask task = new java.util.TimerTask(){
             public void run(){
                if(Global.fenetre_plateau != null){
                    Global.fenetre_plateau.repaint();
                }
            }
        };

        timer = new java.util.Timer();
        timer.scheduleAtFixedRate(task, 0, 10);
        

    }
    
/**
* Fonction indispensable au fonctionnement du programme, elle contient une instance de la classe Isola, puis dans une boucle on exécute en continu la méthode LancementJeu (venant de Global) afin de faire fonctionner le jeu en continu (tant qu'on a pas décidé d'arrêter le jeu avec bouton "Quitter" qui apparaît après une victoire).
*
* @param arg : Chaîne caractère []
*/
    public static void main (String [] arg) {
        Isola isola = new Isola();
        while (Global.jeu_ouvert == true){
            Global.LancementJeu();
        }
        
    }
    

} // Fin programme
