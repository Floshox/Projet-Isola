import java.awt.*;
import java.util.*;
import java.awt.event.*;
import java.io.*;
import javax.imageio.*;
import java.lang.Math;
import javax.swing.*;


/**
* Cette classe est indispensable pour le fonctionnement du jeu Isola et pour pleins d'actions différentes à effectuer : Analyser la position des joueurs selon les briques, l'affichage des résultats lors de la victoire d'un joueurs, le posage d'un élément (pion ou brique) et bien plus.
<br>La quasi totalité des attributs sont statiques afin qu'ils soient accessibles facilement par les autres classes.
*
* @see Joueur, Brique, ListeBriques, Curseur, FenetrePlateau, FenetreMenu
*
* @author Florian Cassagne
*/
public class Global{
    
/**
* Pour définir le nombre de cases du plateau en largeur.
*/
    public static final int NB_CASES_X = 9;
    
/**
* Pour définir le nombre de cases du plateau en hauteur.
*/
    public static final int NB_CASES_Y = 7;
    
/**
* Pour définir l'épaisseur de la grille.
*/
    public static final int EPAISSEUR_GRILLE = 5;
    
/**
* Pour définir la largeur de chaque cases.
*/
    public static final int LARGEUR_CASE = 100;
    
/**
* Pour définir la hauteur de chaque cases.
*/
    public static final int HAUTEUR_CASE = 100;
    
/**
* Pour créer une instance du curseur et l'initialiser (Ps : seul 1 curseur est utilisé dans notre programme).
*/
    public static Curseur curseur = new Curseur();
    
/**
* Pour indiquer si le jeu est lancé. Variable toujours vraie tant qu'on y a pas accédé à un bouton "Quitter" (présent dans le menu après la victoire d'un joueur).
*/
    public static boolean jeu_ouvert = true;
    
/**
* Pour indiquer si la fenêtre affichant le plateau est ouverte ou non.
*/
    public static boolean plateau_ouvert = false;
    
/**
* Pour définir si le mode "Joueur Vs Joueur" est utilisé.
*/
    public static boolean mode_2_joueur = false;
    
/**
* Pour définir l'étape actuelle du joueur. Explications :
<br>-Il est à "1" quand le joueur doit se déplacer.
<br>-Il est à "2" quand le joueur doit poser une brique.
<br>-Il est à "0" avant que la partie soit lancée.
*/
    public static int joueur_etape = 0;
    
/**
* Pour dire si c'est au joueur 1 ou 2 de jouer. Explications :
<br>-Il est à "1" quand c'est au joueur 1 de jouer.
<br>-Il est à "2" quand c'est au joueur 2 de jouer.
<br>-Il est à "0" avant que la partie soit lancée.
*/
    public static int tour_joueur = 0;
        
/*
* Pour stocker le joueur 1 dans une variable statique. Sert principalement à faciliter l'accès concernant certaines méthodes de d'autres classes.
*/
    public static Joueur joueur1;
    
/*
* Pour stocker le joueur 2 dans une variable statique. Sert principalement à faciliter l'accès concernant certaines méthodes de d'autres classes.
*/
    public static Joueur joueur2;

/*
* Pour stocker le nom du joueur 1 en variable statique. Nécessaire après avoir rentré le nom du joueur 1 dans le menu de lancement du jeu.
*/
    public static String nom_joueur1 = "Joueur1";
    
/*
* Pour stocker le nom du joueur 2 en variable statique. Nécessaire après avoir rentré le nom du joueur 2 dans le menu de lancement du jeu.
*/
    public static String nom_joueur2 = "Joueur2";
    
/*
* Pour stocker un tableau de booléens dans le but de définir les coordonnés x et y dans lesquelles il y a un joueur ou non.
*/
    public static boolean [][] case_avec_joueur = new boolean[NB_CASES_Y][NB_CASES_X]; 
    
/*
* Pour stocker le score du joueur 1. Variable statique indispensable pour pouvoir accumuler le score au fil des parties.
*/
    public static int score_joueur1 = 0;
    
/*
* Pour stocker le score du joueur 2. Variable statique indispensable pour pouvoir accumuler le score au fil des parties.
*/
    public static int score_joueur2 = 0;
    
/*
* Pour stocker le joueur qui joue actuellement (joueur 1 ou joueur 2)
*/
    public static Joueur joueur_actuel;
/*
* Pour stocker la dernière brique utilisée
*/
    public static Brique brique_actuel;
    
/*
* Cette variable est plutôt considéré comme un indicateur pour dire si une fenêtre de plateau existe ou non.
*/
    public static FenetrePlateau fenetre_plateau = null;
    
/*
* Cette variable est plutôt considéré comme un indicateur pour dire si une fenêtre de menu (ensemble de boîtes de dialogues) existe ou non.
*/
    public static FenetreMenu fenetre_menu = null;
    
    
/**
* Méthode utilisé pour lancer le jeu, elle affiche d'abord le menu, puis le plateau, créé les joueurs, le tableau de briques et exécuter les autres fonctions de la classe (ex : <b>PartieEnCours()</b> pour lancer une partie qui se termine que lorsqu'un joueur à gagné).
*
*/
    static public void LancementJeu(){
        if ( plateau_ouvert == false ) {
            fenetre_menu = new FenetreMenu();
            fenetre_menu.MenuChoix();
            fenetre_plateau = new FenetrePlateau();
            fenetre_plateau.Plateau();
        }
        else{
            JoueurReel joueur_1 = new JoueurReel(true, nom_joueur1);
            joueur1 = joueur_1;
            ListeBriques liste_briques = new ListeBriques();

            if (mode_2_joueur == true){
                JoueurReel joueur_2 = new JoueurReel(false, nom_joueur2);
                joueur2 = joueur_2;
                Global.InitialisationDonneesJeu();
                Global.PartieEnCours();
                Global.AffichageResultat();
            }
            else{
                JoueurOrdi joueur_2 = new JoueurOrdi();
                joueur2 = joueur_2;
                Global.InitialisationDonneesJeu();
                Global.PartieEnCours();
                Global.AffichageResultat();
            }
        }
    }
    

/**
* Méthode utilisé pour initialiser certaines données statiques pour déterminer quel joueur doit jouer actuellement, quel action à faire (se déplacer ou poser une brique) et poser les briques de base (celles qui délimitent le plateau)
*
*/
    static public void InitialisationDonneesJeu(){
        joueur_actuel = joueur1;
        tour_joueur = 1;
        joueur_etape = 1;
        Global.InitialisationBriques();
    }
    
/**
* Méthode utilisé pour vérifier en boucle certaines autres méthodes dont celle qui vérifie si un joueur est totalement coincé ou non, et une autre pour mettre à jour la position des joueurs (du tableau <b>case_avec_joueurs[][]</b>), tant que personne n'a encore gagné.
*
*/
    static public void PartieEnCours(){
        while ( (joueur1.getAGagne() == false) && (joueur2.getAGagne() == false) ){
            Global.MAJCasesAvecJoueurs();
            Global.VerificationVictoireJoueurs(joueur1, joueur2);
            Global.MAJCasesAvecJoueurs();
            Global.VerificationVictoireJoueurs(joueur2, joueur1);
        }
    }
    
/**
* Méthode utilisé pour poser toutes les briques qui délimitent le plateau de jeu.
*
*/
    static public void InitialisationBriques(){
        for (int position_y = 0 ; position_y < NB_CASES_Y ; position_y++){
            for (int position_x = 0 ; position_x < NB_CASES_X ; position_x++){
                if ( (position_y == 0) || (position_y == NB_CASES_Y-1) || (position_x == 0) || (position_x == NB_CASES_X-1) ){
                   Brique brique = new Brique(position_x,position_y);
                    brique_actuel = brique;
                    ListeBriques.liste_briques.addElement(brique);
                    ListeBriques.case_avec_brique[position_y][position_x] = true; 
                }
            }
        }
    }
    
    
/**
* Méthode utilisé pour mettre à jour la position des joueurs dans le tableau <b>case_avec_joueur</b>
*
*/
    static public void MAJCasesAvecJoueurs(){
        for (int position_y = 0 ; position_y < NB_CASES_Y ; position_y++){
            for (int position_x = 0 ; position_x < NB_CASES_X ; position_x++){
                if ( ( (joueur1.getX() == position_x ) && (joueur1.getY() == position_y ) ) ||
                   ( (joueur2.getX() == position_x ) && (joueur2.getY() == position_y ) )
                   ){
                    case_avec_joueur[position_y][position_x] = true;
                }
                else{
                    case_avec_joueur[position_y][position_x] = false;
                }
            }
        }
    }
    

/**
* Méthode utilisé pour poser soit pour déplacer un joueur, soit pour poser une brique. Dépend du joueur qui joue actuellement, de l'étape (1 ou 2) et du mode de jeu également. Si le mode "Joueur Vs Ordinateur" est sélectionné, alors on pourra contrôler que le 1er joueur, et le 2eme joueur sera contrôlé automatiquement par l'ordinateur.
*
*/
    public static void ActionJoueur(){
            int ecart_x_joueur = joueur_actuel.getX() - curseur.getX();
            int ecart_y_joueur = joueur_actuel.getY() - curseur.getY();
        
            if ( mode_2_joueur == true ){
                if( (joueur_etape == 1 ) && ( ecart_x_joueur >= -1 ) && ( ecart_x_joueur <= 1 ) && ( ecart_y_joueur >= -1 ) && ( ecart_y_joueur <= 1 ) &&
                ( (joueur1.getX() != curseur.getX()) || (joueur1.getY() != curseur.getY() ) )&&
                ( (joueur2.getX() != curseur.getX()) || (joueur2.getY() != curseur.getY() ) ) &&
                ( ListeBriques.case_avec_brique[curseur.getY()][curseur.getX()] == false )  )
                {
                    joueur_actuel.setXY(curseur.getX(), curseur.getY());

                    joueur_etape = 2;
                }

                else if( (joueur_etape == 2 ) &&
                ( (joueur1.getX() !=curseur.getX()) || (joueur1.getY() != curseur.getY() ) ) &&
                ( (joueur2.getX() != curseur.getX()) || (joueur2.getY() != curseur.getY() ) ) &&
                ( ListeBriques.case_avec_brique[curseur.getY()][curseur.getX()] == false ) )
                {

                    Brique brique = new Brique(curseur.getX() , curseur.getY());
                    brique_actuel = brique;
                    ListeBriques.liste_briques.addElement(brique);
                    ListeBriques.case_avec_brique[curseur.getY()][curseur.getX()] = true;

                    if ( tour_joueur == 1 ){
                        tour_joueur = 2;
                        joueur_actuel = joueur2;
                    }
                    else if ( tour_joueur == 2 ){
                        tour_joueur = 1;
                        joueur_actuel = joueur1;
                    }

                    joueur_etape = 1;
                }
            }
        
            else if ( ( mode_2_joueur == false ) && (tour_joueur == 1 ) ){
                
                if( (joueur_etape == 1 ) && ( ecart_x_joueur >= -1 ) && ( ecart_x_joueur <= 1 ) && ( ecart_y_joueur >= -1 ) && ( ecart_y_joueur <= 1 ) &&
                ( (joueur1.getX() != curseur.getX()) || (joueur1.getY() != curseur.getY() ) )&&
                ( (joueur2.getX() != curseur.getX()) || (joueur2.getY() != curseur.getY() ) ) &&
                ( ListeBriques.case_avec_brique[curseur.getY()][curseur.getX()] == false )  )
                {
                   joueur_actuel.setXY(curseur.getX(), curseur.getY());

                    joueur_etape = 2;
                }

                else if( (joueur_etape == 2 ) &&
                ( (joueur1.getX() != curseur.getX()) || (joueur1.getY() != curseur.getY() ) ) &&
                ( (joueur2.getX() != curseur.getX()) || (joueur2.getY() != curseur.getY() ) ) &&
                ( ListeBriques.case_avec_brique[curseur.getY()][curseur.getX()] == false ) )
                {
                    Brique brique = new Brique(curseur.getX() , curseur.getY());
                    brique_actuel = brique;
                    ListeBriques.liste_briques.addElement(brique);
                    ListeBriques.case_avec_brique[curseur.getY()][curseur.getX()] = true;

                    tour_joueur = 2;
                    joueur_actuel = joueur2;
                    joueur_etape = 1;
                    
                }
            }
            else if ( ( mode_2_joueur == false ) && (tour_joueur == 2 ) )
            {
                OrdiActionAleatoire();
                tour_joueur = 1;
                joueur_etape = 1;
                joueur_actuel = joueur1;
            }
    }
    

/**
* Méthode utilisé pour que l'ordinateur fasse une action : poser une brique ou se déplacer.
*
*/
    public static void OrdiActionAleatoire(){
        
        boolean deplacement_reussie = false;
        
        while (deplacement_reussie == false){
            int direction_aleatoire_x = -1 + (int)(Math.random() * ((1 - (-1)) + 1));
            int direction_aleatoire_y = -1 + (int)(Math.random() * ((1 - (-1)) + 1));
            
            if ( !((direction_aleatoire_x == 0) && (direction_aleatoire_y == 0) ) &&
            (ListeBriques.case_avec_brique[joueur_actuel.getY() + direction_aleatoire_y][joueur_actuel.getX() + direction_aleatoire_x] == false)  && 
            (case_avec_joueur[joueur_actuel.getY() + direction_aleatoire_y] [joueur_actuel.getX() + direction_aleatoire_x] == false) &&
            ( (joueur_actuel.getX() + direction_aleatoire_x ) > 0 ) &&
            ( (joueur_actuel.getY() + direction_aleatoire_y ) > 0 ) &&
            ( (joueur_actuel.getX() + direction_aleatoire_x ) < NB_CASES_X - 1 ) &&
            ( (joueur_actuel.getY() + direction_aleatoire_y ) < NB_CASES_Y - 1 )
            ) {
                joueur_actuel.setXY( joueur_actuel.getX() + direction_aleatoire_x,
                joueur_actuel.getY() + direction_aleatoire_y );
                deplacement_reussie = true;
            }
                
        }
        boolean placement_brique_reussie = false;

        while (placement_brique_reussie == false){
            int case_aleatoire_x = 1 + (int)(Math.random() * ((NB_CASES_X-2 - (1)) + 1));
            int case_aleatoire_y = 1 + (int)(Math.random() * ((NB_CASES_Y-2 - (1)) + 1));

            if ( ( (ListeBriques.case_avec_brique
            [case_aleatoire_y][case_aleatoire_x] == false) && (case_avec_joueur[case_aleatoire_y][case_aleatoire_x] == false)
            )
               ){
                Brique brique = new Brique(case_aleatoire_x , case_aleatoire_y);
                brique_actuel = brique;
                ListeBriques.liste_briques.addElement(brique);
                ListeBriques.case_avec_brique[case_aleatoire_y][case_aleatoire_x] = true;
                placement_brique_reussie = true;
            }
        }     
        tour_joueur = 1;
        joueur_etape = 1;
    }
    
/**
* Méthode importante pour vérifier si un joueur est coincé par des brique et/ou un autre joueur ou non.
*
* @param joueur_qui_joue
* @param joueur_en_attente
*/
     public static void VerificationVictoireJoueurs(Joueur joueur_qui_joue, Joueur joueur_en_attente){
         
         joueur_qui_joue.setCheminsBloques(0);
         
         if ( (joueur_qui_joue.getX() > 0 ) && (joueur_qui_joue.getY() > 0 ) && (joueur_qui_joue.getX() < NB_CASES_X - 1 ) && (joueur_qui_joue.getY() < NB_CASES_Y - 1 ) )
         {
            for (int i = -1 ; i <= 1 ; i++){
                for (int j = -1 ; j <= 1 ; j++){                    
                    if (
                    ( ListeBriques.case_avec_brique[joueur_qui_joue.getY() + i ][joueur_qui_joue.getX() + j] == true ) ||
                    ( case_avec_joueur[joueur_qui_joue.getY() + i ][joueur_qui_joue.getX() + j] == true ) ||
                    ( case_avec_joueur[joueur_en_attente.getY() + i ][joueur_en_attente.getX() + j] == true ) || ( (joueur_qui_joue.getX() == joueur_en_attente.getX() ) && (joueur_qui_joue.getY() == joueur_en_attente.getY() ) ) ||
                    ( ListeBriques.case_avec_brique[joueur_qui_joue.getY()][joueur_qui_joue.getX()] == true )
                    )
                    {
                        if (joueur1 == joueur_qui_joue) {
                            joueur1.IncrementerCheminsBloques();
                            if (joueur1.getCheminsBloques() == 9){
                                joueur2.Gagne();
                                joueur2.setScore(joueur2.getScore() + 1);
                                score_joueur2 += 1;
                            }
                       
                        }
                        else if (joueur2 == joueur_qui_joue) {
                            joueur2.IncrementerCheminsBloques();
                            if (joueur2.getCheminsBloques() == 9){
                                joueur1.Gagne();
                                joueur1.setScore(joueur1.getScore() + 1);
                                score_joueur1 += 1;
                            }
                       }
                    }
                }
            }
         }  
    }
    
    
/**
* Méthode permettant d'afficher le menu d'après victoire et également les caractéristiques des joueurs (affiché simplement dans la console)
*
* @param joueur1
* @param joueur2
*/
     public static void AffichageResultat(){
        if ( (joueur1.getAGagne() == true) || (joueur2.getAGagne() == true) ){
            fenetre_menu = new FenetreMenu();
            fenetre_menu.MenuApresVictoire();
            joueur1.afficherCaracteristiques();
            joueur2.afficherCaracteristiques();
        }
    }
    
}