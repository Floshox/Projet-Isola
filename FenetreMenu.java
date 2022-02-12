import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;
import javax.imageio.*;
import java.lang.Math;
import javax.swing.*;


/**
* Cette classe permet d'afficher les différents menus proposés :
*<br>-Au lancement du programme, permettant à(aux) l'utilisateur(s) de choisir son mode de jeu et le nom des joueurs.
*<br>-Après la victoire d'un joueur, afin de proposer à l'utilisateur de relancer une nouvelle partie ou de quitter le programme + pour afficher le score de chaque joueur (1 victoire = 1 point en plus pour le score global de chaque joueurs).
*
* @see Global, JFrame
*
* @author Florian Cassagne
*/
public class FenetreMenu extends JFrame
{
    
    
/**
* Tableau de 2 chaînes de caractères prenant en compte les 2 propositions de mode de jeu.
*/
    private String[] mode_de_jeu = {"Joueur contre ordinateur", "Joueur contre Joueur"};
    
/**
* Tableau de 3 chaînes de caractères prenant en compte les 3 propositions de choix possible après la victoire d'un joueur :
*<br>-Rejouer
*<br>-Retour Menu
*<br>-Quitter
*/
    private String[] choix_apres_victoire = {"Rejouer", "Changer Mode", "Quitter"};
    
/**
* Pour créer un panel qui prendra la forme d'une boîte de dialogue, et qui proposera la liste des choix de modes de jeu.
*
* @see JOptionPane
*/
    private JOptionPane ensemble_modes = new JOptionPane();
    
/**
* Pour créer un panel qui prendra la forme d'une boîte de dialogue, et qui proposera de rentrer le nom du premier joueur.
*
* @see JOptionPane
*/     
    private JOptionPane entrer_nom_j1 = new JOptionPane();
    
/**
* Pour créer un panel qui prendra la forme d'une boîte de dialogue, et qui proposera de rentrer le nom du deuxième joueur.
*
* @see JOptionPane
*/     
    private JOptionPane entrer_nom_j2 = new JOptionPane();
    
/**
* Pour créer un panel qui prendra la forme d'une boîte de dialogue, et qui proposera de valider les choix du joueur.
*
* @see JOptionPane
*/     
    private JOptionPane validation = new JOptionPane();
    
/**
* Pour créer un panel qui prendra la forme d'une boîte de dialogue, et qui affichera le nom du gagnant et le score accumulé de chacun.
*
* @see JOptionPane
*/     
    private JOptionPane resultat_gagnant;
    
    
/**
* Constructeur pour créer une instance d'une fenêtre de "menu"
*
*/
    public FenetreMenu(){
        
    }

/**
* Permet d'ouvrir une série de boîtes de dialogues (soit un menu en quelques sortes) pour permettre à l'utilisateur de définir son mode de jeu et le nom des joueurs.
*
* @see JOptionPane
*/
    public void MenuChoix(){
        int mode_choisi = ensemble_modes.showOptionDialog(
        null,
        "Salut ! Choisissez un mode de jeu !",
        "Choix mode de jeu",
        JOptionPane.YES_NO_CANCEL_OPTION,
        JOptionPane.QUESTION_MESSAGE,
        null,
        mode_de_jeu,
        mode_de_jeu[0]);
        
        if (mode_choisi == 0){
            
            String nom_j1 = entrer_nom_j1.showInputDialog(null, "Entrez le pseudo du joueur 1 s'il vous plait : ", "Pseudo joueur 1", JOptionPane.QUESTION_MESSAGE);
            validation.showMessageDialog(null,
            "Mode de jeu : " + mode_de_jeu[mode_choisi] +
            "\r\nPseudo joueur 1 :" + nom_j1,
            "Validation", JOptionPane.INFORMATION_MESSAGE);
            
            if(nom_j1 == null){
                nom_j1 = "Joueur 1";
            }
            
            Global.nom_joueur1 = nom_j1;
            
            Global.mode_2_joueur = false;
            Global.plateau_ouvert = true;
            
        }
        else if (mode_choisi == 1){
            String nom_j1 = entrer_nom_j1.showInputDialog(null, "Entrez le pseudo du joueur 1 s'il vous plait : ", "Pseudo joueur 1", JOptionPane.QUESTION_MESSAGE);
            String nom_j2 = entrer_nom_j2.showInputDialog(null, "Entrez le pseudo du joueur 2 s'il vous plait : ", "Pseudo joueur 2", JOptionPane.QUESTION_MESSAGE);

            if(nom_j1 == null){
                nom_j1 = "Joueur 1";
            }
            else if(nom_j2 == null){
                nom_j2 = "Joueur 2";
            }
            
            Global.nom_joueur1 = nom_j1;
            Global.nom_joueur2 = nom_j2;
            
            validation.showMessageDialog(null,
            "Mode de jeu : " + mode_de_jeu[mode_choisi] +
            "\r\nPseudo joueur 1 : " + Global.nom_joueur1 +
            "\r\nPseudo joueur 2 : " + Global.nom_joueur2,
            "Validation", JOptionPane.INFORMATION_MESSAGE);
            
            Global.mode_2_joueur = true;
            Global.plateau_ouvert = true;
            
        }
        else{
            System.exit(1);
        }
        
        Global.score_joueur1 = 0;
        Global.score_joueur2 = 0;
        
    }
    
/**
* Permet d'ouvrir une boîte de dialogue permettant d'afficher les résultat du vainqueur et le score des joueurs à l'utilisateur
*
* @see JOptionPane
*/
    public void MenuApresVictoire(){
        String nom_gagnant = "";
        if (Global.joueur1.getAGagne() == true){
            nom_gagnant = Global.joueur1.getNom();
        }
        else if (Global.joueur2.getAGagne() == true){
            nom_gagnant = Global.joueur2.getNom();
        }
        
        int choix_selectionne = resultat_gagnant.showOptionDialog(
        null,
        nom_gagnant + " a gagne !" +
        "\r\nScore de " + Global.joueur1.getNom() + " : " + Global.score_joueur1 + 
        "\r\nScore de " + Global.joueur2.getNom() + " : " + Global.score_joueur2,
        "Resultats",
        JOptionPane.YES_NO_CANCEL_OPTION,
        JOptionPane.QUESTION_MESSAGE,
        null,
        choix_apres_victoire,
        choix_apres_victoire[0]);

        if(choix_selectionne == 1){
            this.MenuChoix();
        }
        else if(choix_selectionne == 2){
            System.exit(1);
        }
        
        
    }
    
    
} // Fin programme

