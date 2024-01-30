import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;
import javax.imageio.*;
import java.lang.Math;
import javax.swing.*;

/**
* Cette classe permet de créer un joueur et contient les attributs et méthodes indispensables pour n'importe quel type de joueurs (Réel ou Ordinateur).
* Il s'agit d'une classe abstraite puisque seul les 2 types de joueurs (classes filles) seront déclarés.
*
* @see JoueurOrdi, JoueurReel
*
* @author Florian Cassagne
*/
public abstract class Joueur
{
    
/**
* Pour définir les coordonnés <b>x</b> et <b>y</b> du joueur
*/
    protected int x, y;
    
/**
* Pour indiquer si c'est le joueur n°1 (true) ou le joueur n°2 (false)
*/
    protected boolean est_joueur_1;
    
/**
* Pour définir un nom au joueur
*/
    protected String nom;
    
/**
* Pour définir un score au joueur, qui s'incrémente de "1" à chaque victoire.
*/
    protected int score;
    
/**
* Pour définir le nombre de chemins inaccessibles du joueur sur une case donnée
* Ps : Un joueur peut avoir le choix entre 9 différentes directions au maximum, soit : chemins_bloques = 9 - nombre de choix possibles.
*/
    protected int chemins_bloques;
    
/**
* Pour indiquer si le joueur a gagné ou non. Au début d'une partie, logiquement cette variable est toujours à "false".
*/
    protected boolean a_gagne;
    
/**
* Constructeur de base pour créer un joueur. Il sera utilisé que lors de la déclarations de ses classes filles (JoueurReel et JoueurOrdi)
*
* @param est_joueur_1 Si il 'agit du premier joueur
* @param nom Si il s'agit du nom
*/
    public Joueur(boolean est_joueur_1, String nom){
        
        this.est_joueur_1 = est_joueur_1;
        this.nom = nom;
        this.score = 0;
        this.chemins_bloques = 0;
        
        if (est_joueur_1 == true){
            this.a_gagne = false;
            this.x = 1; // Le pion se situera dans la colonne la plus à GAUCHE de la grille.
            double y_double = Math.floor(Global.NB_CASES_Y/2); // Pour que le pion du joueur soit dans la ligne du milieu (sachant qu'on travaille toujours avec un nombre de lignes et colonnes impair concernant la grille du jeu Isola).
            this.y = (int)y_double;
        }
        else if (est_joueur_1 == false){
            this.a_gagne = false;
            this.x = Global.NB_CASES_X-2; // Le pion se situera dans la colonne la plus à DROITE de la grille.
            double y_double = Math.floor(Global.NB_CASES_Y/2); // Pour que le pion du joueur soit dans la ligne du milieu (sachant qu'on travaille toujours avec un nombre de lignes et colonnes impair concernant la grille du jeu Isola).
            this.y = (int)y_double;
        }
        
    }
    
/**
* Pour afficher les caractéristiques d'un joueur : position, score et nom
*
*/
    public void afficherCaracteristiques(){
        System.out.println("Nom : " + nom);
        System.out.println("Score : " + score);
        System.out.println("Position : " + x + " " + y);
        System.out.println("");
    }
    
/**
* Pour obtenir la position x du joueur.
*
* @return x
*/
    public int getX(){
        return x;
    }
    
/**
* Pour obtenir la position y du joueur.
*
* @return y
*/
    public int getY(){
        return y;
    }
    
/**
* Pour obtenir le nom du joueur.
*
* @return nom
*/
    public String getNom(){
        return nom;
    }
    
/**
* Pour modifier la position x et y du joueur.
*
* @param _x
* @param _y
*/
    public void setXY(int _x, int _y){
        x = _x;
        y = _y;
    }

/**
* Pour modifier le nom du joueur.
*
* @param _nom
*/  
    public void setNom(String _nom){
        nom = _nom;
    }
    
/**
* Pour obtenir le score du joueur.
*
* @return score
*/
    public int getScore(){
        return score;
    }
    
/**
* Pour modifier le score du joueur.
*
* @param _score
*/  
    public void setScore(int _score){
        score = _score;
    }
    
/**
* Pour incrémenter le score du joueur de 1.
*
*/  
    public void IncrementerScore(){
        score += 1;
    }
    
/**
* Pour obtenir le nombre de chemins inaccessible à partir d'un joueur.
*
* @return chemins_bloques Nombre de chemins inaccessibles parmi les 9 possibles
*/
    public int getCheminsBloques(){
        return chemins_bloques;
    }

/**
* Pour modifier le nombre de chemins inaccessible à partir d'un joueur.
*
* @param _chemins_bloques Nombre de chemins inaccessibles parmi les 9 possibles
*/
    public void setCheminsBloques(int _chemins_bloques){
        chemins_bloques = _chemins_bloques;
    }
    
/**
* Pour incrémenter le nombre de chemins inaccessible paour un joueur de 1.
*
*/  
    public void IncrementerCheminsBloques(){
        chemins_bloques += 1;
    }
    
/**
* Pour obtenir l'état de victoire du joueur (si il a gagné ou non).
*
* @return a_gagne Si le joueur a gagné
*/
    public boolean getAGagne(){
        return a_gagne;
    }
    
/**
* Pour définir l'état de victoire du joueur à "true".
*
*/  
    public void Gagne(){
        a_gagne = true;
    }
    
}

