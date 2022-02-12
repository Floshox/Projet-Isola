import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;
import javax.imageio.*;
import java.lang.Math;
import javax.swing.*;


/**
* Cette classe permet de stocker chaque briques dans 2 différents tableaux : Un vecteur et un booléen, cependant l'utilisation du tableau de booléen est préféré dans beaucoup plus de cas.
*
* @see Brique
*
* @author Florian Cassagne
*/
public class ListeBriques
{
/**
* Pour créer le vecteur de briques.
*/
    public static Vector<Brique> liste_briques;

/**
* Pour créer le tableau 2 dimension de booléens de briques. Pour des coordonnées x et y donnée, si la valeur correspond à "true", c'est qu'il y aura une brique sur les coordonnés données.
*/
    public static boolean [][] case_avec_brique;
        
/**
* Constructeur pour initialiser les tableaux de briques (Vecteur et booléen)
*
*/
    public ListeBriques(){
        liste_briques = new Vector<Brique>();
        case_avec_brique = new boolean[Global.NB_CASES_Y][Global.NB_CASES_X]; 
        for (int i = 0 ; i < Global.NB_CASES_Y ; i++){
            for (int j = 0 ; j < Global.NB_CASES_X ; j++){
                case_avec_brique[i][j] = false;
            }
        }
    }
    
}

