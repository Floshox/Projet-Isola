import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;
import javax.imageio.*;
import java.lang.Math;
import javax.swing.*;

/**
* Cette classe permet d'afficher le plateau de jeu Isola, mais également de gérer les appuis sur certaines touches de clavier afin de lancer certaines méthodes, qui serviront par la suite.
*
* @see Global, JFrame, KeyListener
*
* @author Florian Cassagne
*/
public class FenetrePlateau extends JFrame implements KeyListener
{
    
/**
* Cet attribut permet de dessiner les éléments (plateau avec grille, joueurs...) sur la fenêtre (JFrame) en question
*
* @see AffichagePlateau
*/
    private AffichagePlateau plateau;
    
/**
* Permet de créer une fenêtre avec une taille définie dans la classe TailleFenetre
*
* @see TailleFenetre
*/
    public FenetrePlateau(){
        setSize(TailleFenetre.getLargeur(), TailleFenetre.getHauteur());
        setTitle("Isola - Cassagne - M3105");
        setVisible(true);
        setLocationRelativeTo(null);
        setResizable(false);
        setAlwaysOnTop(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addKeyListener(this);
    }
    
/**
* Permet de créer un objet de type plateau permettant d'afficher les éléments comme les joueurs et la grille
*
* @see AffichagePlateau
*/
    public void Plateau(){
        plateau = new AffichagePlateau();
        setContentPane(plateau);
        pack();
    }
    
/**
* Méthode qui provient de l'interface <b>KeyListener</b>
*
* @param e L'évènement (action) qui peut être produit en appuyant sur une touche
* @see KeyListener
*/
    public void keyPressed(KeyEvent e) {
        int touche_tape = e.getKeyCode();
            if (touche_tape==37){
                Global.curseur.DeplacerGauche();
            }

            else if (touche_tape==38){
                Global.curseur.DeplacerHaut();
            }

            else if (touche_tape==39){
                Global.curseur.DeplacerDroite();
            }

            else if (touche_tape==40){
                Global.curseur.DeplacerBas();
            }

            else if (touche_tape==10){
                Global.ActionJoueur();
            }

    }

/**
* Méthode qui provient de l'interface <b>KeyListener</b>
*
* @param e L'évènement (action) qui peut être produit en appuyant sur une touche
* @see KeyListener
*/
    public void keyReleased(KeyEvent e) {
        
    }
    
/**
* Méthode qui provient de l'interface <b>KeyListener</b>
*
* @param e L'évènement (action) qui peut être produit en appuyant sur une touche
* @see KeyListener
*/
    public void keyTyped(KeyEvent e) {
        
    }
    
} // Fin programme

