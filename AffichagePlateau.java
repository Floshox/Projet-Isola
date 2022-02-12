import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;
import javax.imageio.*;
import java.lang.Math;
import javax.swing.*;

/**
* Cette classe permet d'afficher en continu les éléments sur le plateau : la grille, les joueurs, les briques et le curseur de l'utilisateur
*
* @see Global, JPanel
*
* @author Florian Cassagne
*/
public class AffichagePlateau extends JPanel
{
    
/**
* Constructeur qui permet de créer une instance de la classe AffichagePlateau
*
*/
   public AffichagePlateau(){
        setPreferredSize(new Dimension(TailleFenetre.getLargeur(), TailleFenetre.getHauteur()));
         
   }
   
/**
* Contient toutes les méthodes pour dessiner les éléments
*
* @param g Ensemble des éléments graphiques (Graphics)
* @see Global
*/
   public void paintComponent(Graphics g){
        afficherFond(g);
        afficherGrille(g);
        afficherJoueur(g, Global.joueur1, Global.joueur2);
        if (Global.brique_actuel != null){
            afficherBrique(g, Global.brique_actuel);
        }
        afficherCurseur(g, Global.curseur);
    }
   
   
/**
* Permet de créer un rectangle d'une certaine couleur pour le mettre en fond. C'est juste pour avoir une couleur de fond du plateau en quelques sortes.
*
* @param g Ensemble des éléments graphiques (Graphics)
* @see Global
*/
   public void afficherFond(Graphics g){
        Color couleur_fond = new Color(195, 185, 175);
        g.setColor(couleur_fond);
        g.fillRect(0, 0, TailleFenetre.getLargeur(), TailleFenetre.getHauteur());
   }
   
/**
* Permet d'afficher les grilles qui s'adapte à la hauteur et largeur de la fenêtre, et aussi faire quelques ajustement au niveau de l'affichage par rapport à l'épaisseur de la grille.
*
* @param g Ensemble des éléments graphiques (Graphics)
* @see Global
*/
   public void afficherGrille(Graphics g){
   
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.black);
        g2.setStroke(new BasicStroke(Global.EPAISSEUR_GRILLE));
        
        float ajustement_h = Global.EPAISSEUR_GRILLE/2; // Correspond à la moitié de l'épaisseur des bordures
        
        for (int hauteur = 0 ; hauteur <= TailleFenetre.getHauteur() ; hauteur+= Global.HAUTEUR_CASE)
        {
            ajustement_h = Global.EPAISSEUR_GRILLE/2 * (TailleFenetre.getHauteur() - hauteur*2)/TailleFenetre.getHauteur();
            g.drawLine(0, hauteur + (int)ajustement_h, TailleFenetre.getLargeur(), hauteur + (int)ajustement_h);
        }
        
        float ajustement_l = Global.EPAISSEUR_GRILLE/2; // Correspond à la moitié de l'épaisseur des bordures
        // Bordures noires d'épaisseur de 8 pixels
        for (int largeur = 0 ; largeur <= TailleFenetre.getLargeur() ; largeur+= Global.LARGEUR_CASE)
        {
            ajustement_l = Global.EPAISSEUR_GRILLE/2 * (TailleFenetre.getLargeur() - largeur*2)/TailleFenetre.getLargeur();
            g.drawLine(largeur + (int)ajustement_l, 0, largeur + (int)ajustement_l, TailleFenetre.getHauteur());
        }
        
        
   }
   
/**
* Permet d'afficher les pions représentant les joueurs. Quelques ajustement sont faits pour l'affichage afin que la position des pions sur le plateau s'adapte à la taille de la fenêtre et à l'épaisseur de la grille.
*
* @param g Ensemble des éléments graphiques (Graphics)
* @param joueur1
* @param joueur2
* @see Global, Joueur
*/
   public void afficherJoueur(Graphics g, Joueur joueur1, Joueur joueur2){

        float joueur1_ajust_l = Global.EPAISSEUR_GRILLE - Global.joueur1.getX() * (Global.EPAISSEUR_GRILLE * 2 / Global.NB_CASES_X);
        float joueur1_ajust_h = Global.EPAISSEUR_GRILLE - Global.joueur1.getY() * 2;
        float joueur2_ajust_l = Global.EPAISSEUR_GRILLE - Global.joueur2.getX() * (Global.EPAISSEUR_GRILLE * 2 / Global.NB_CASES_X);
        float joueur2_ajust_h = Global.EPAISSEUR_GRILLE - Global.joueur2.getY() * 2;


        g.setColor(Color.blue);
        g.fillOval(
        Global.joueur1.getX() * Global.LARGEUR_CASE + (Global.LARGEUR_CASE) / Global.EPAISSEUR_GRILLE + (int)joueur1_ajust_l,
        Global.joueur1.getY() * Global.HAUTEUR_CASE + (Global.HAUTEUR_CASE) / Global.EPAISSEUR_GRILLE + (int)joueur1_ajust_h, 
        (Global.LARGEUR_CASE * 3) / Global.EPAISSEUR_GRILLE,
        (Global.HAUTEUR_CASE*3) / Global.EPAISSEUR_GRILLE
        );

        g.setColor(Color.red);
        g.fillOval(
        Global.joueur2.getX() * Global.LARGEUR_CASE + (Global.LARGEUR_CASE) / Global.EPAISSEUR_GRILLE + (int)joueur2_ajust_l,
        Global.joueur2.getY() * Global.HAUTEUR_CASE + (Global.HAUTEUR_CASE) / Global.EPAISSEUR_GRILLE + (int)joueur2_ajust_h,
        (Global.LARGEUR_CASE * 3) / Global.EPAISSEUR_GRILLE,
        (Global.HAUTEUR_CASE * 3) / Global.EPAISSEUR_GRILLE
        );
      
    }
    
/**
* Permet d'afficher le curseur de l'utilisateur
*
* @param g Ensemble des éléments graphiques (Graphics)
* @param curseur Curseur
* @see Global, Curseur
*/
    public void afficherCurseur(Graphics g, Curseur curseur){
        
        float curseur_ajust_l = Global.EPAISSEUR_GRILLE - (curseur.getX() / Global.NB_CASES_X) * Global.EPAISSEUR_GRILLE * 2;
        float curseur_ajust_h = Global.EPAISSEUR_GRILLE - (curseur.getY() / Global.NB_CASES_Y) * Global.EPAISSEUR_GRILLE * 2;
        
        g.setColor(Color.yellow);
        g.drawRect(
            curseur.getX() * Global.LARGEUR_CASE + (int)curseur_ajust_l, 
            curseur.getY() * Global.HAUTEUR_CASE + (int)curseur_ajust_h,
            (Global.LARGEUR_CASE - Global.EPAISSEUR_GRILLE*2),
            (Global.HAUTEUR_CASE - Global.EPAISSEUR_GRILLE*2)
        );
    }
    
/**
* Permet d'afficher chacunes des briques
*
* @param g Ensemble des éléments graphiques (Graphics)
* @param brique_actuel Dernière brique utilisée
* @see Global, Brique
*/
     public void afficherBrique(Graphics g, Brique brique_actuel){
        float brique_ajust_l = Global.EPAISSEUR_GRILLE - (brique_actuel.getX() / Global.NB_CASES_X) * Global.EPAISSEUR_GRILLE * 2;
        float brique_ajust_h = Global.EPAISSEUR_GRILLE - (brique_actuel.getY() / Global.NB_CASES_Y) * Global.EPAISSEUR_GRILLE * 2;
  
        g.setColor(Color.gray);
        
        int nombre_briques = ListeBriques.liste_briques.size();
        
        if (nombre_briques > 0){
            Brique _brique_actuelle;
            for (int i = 0 ; i < nombre_briques ; i++){
                _brique_actuelle = ListeBriques.liste_briques.elementAt(i);
                g.fillRect(
                    _brique_actuelle.getX() * Global.LARGEUR_CASE, 
                    _brique_actuelle.getY() * Global.HAUTEUR_CASE,
                    Global.LARGEUR_CASE,
                    Global.HAUTEUR_CASE
                );
            }
        }
        
    }
       

} // Fin programme

