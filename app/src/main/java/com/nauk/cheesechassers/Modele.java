package com.nauk.cheesechassers;

import android.util.Log;

public class Modele {

    private int cptTest = 1;
    private int cptSouris = 20;
    private int cptCartes = 40;
    private int cptChats = 7;
    private int cptFromages = 9;
    private int cptPieges = 4;
    private int x = 40;
    private int y = 40;
    private int monTableau[][];
    private int cptPoint = 0;

    /**
     * Constructeur
     */
    public Modele() {
        // Creation du tableau
        monTableau = new int[80][80];
        // Initialisation du tableau à 0
        for (int i = 0; i < 80; i++) {
            for (int j = 0; j < 80; j++) {
                monTableau[i][j] = 0;
            }
        }
        // On determine la carte à placer dans la 1ere case
        Log.d("Modele", "On determine la carte à placer dans la 1ere case ");
        monTableau[x][y] = determinerCarte();
        // On place les +
        gererPlus(x, y);
        // On geres les carte adjacentes et les points
        gererCartesAdjacentes(x, y);

    }

    /**
     * Assignation d'une valeur à une case du tableau
     * 1 : Souris
     * 2 : Fromage
     * 3 : Chat
     * 4 : Piege
     */
    public int determinerCarte() {
        int alea = 0;
        do {
            // Entier entre 0 et 4
            alea = (int) (Math.random() * 5);
            Log.d("Modele", "valeur de la carte (alea) : "+alea);
            switch (alea) {
                // On tire une souris
                case 1:
                    // Si il n'y plus de souris on reboucle
                    if (cptSouris == 0) {
                        cptTest = 0;
                    } else {
                        // Sinon on sort en decrementant le nombre de souris et de cartes
                        cptTest = 1;
                        cptSouris--;
                        cptCartes--;
                    }
                    break;
                case 2:
                    // Si il n'y plus de fromage on reboucle
                    if (cptFromages == 0) {
                        cptTest = 0;
                    } else {
                        // Sinon on sort en decrementant le nombre de fromages et de cartes
                        cptTest = 1;
                        cptFromages--;
                        cptCartes--;
                    }
                    break;
                case 3:
                    // Si il n'y plus de chat on reboucle
                    if (cptChats == 0) {
                        cptTest = 0;
                    } else {
                        // Sinon on sort en decrementant le nombre de chat et de cartes
                        cptTest = 1;
                        cptChats--;
                        cptCartes--;
                    }
                    break;
                case 4:
                    // Si il n'y plus de pieges on reboucle
                    if (cptPieges == 0) {
                        cptTest = 0;
                    } else {
                        // Sinon on sort en decrementant le nombre de souris et de cartes
                        cptTest = 1;
                        cptPieges--;
                        cptCartes--;
                    }
                    break;
            }
        }
        while (cptTest != 1);

        Log.d("Modele", "valeur de alea : "+String.valueOf(alea));
        return alea;
    }

    /**
     * Gestion des + autour de la case courante
     * apres avoir verifier si les cases adjacentes existent
     *
     * @param xTab
     * @param yTab
     */
    public void gererPlus(int xTab, int yTab) {

        if (xTab > 0) {
            // Gauche
            if (monTableau[xTab - 1][yTab] == 0) {
                // On met un plus si c'est vide
                monTableau[xTab - 1][yTab] = -1;
            }
        }
        if (xTab > 0 && yTab > 0) {
            // Haut  Gauche
            if (monTableau[xTab - 1][yTab - 1] == 0) {
                // On met un plus si c'est vide
                monTableau[xTab - 1][yTab - 1] = -1;
            }
        }
        if (yTab > 0) {
            // Haut
            if (monTableau[xTab][yTab - 1] == 0) {
                // On met un plus si c'est vide
                monTableau[xTab][yTab - 1] = -1;
            }
        }
        if (xTab < 80 && yTab > 0) {
            // Haut  Droite
            if (monTableau[xTab + 1][yTab - 1] == 0) {
                // On met un plus si c'est vide
                monTableau[xTab + 1][yTab - 1] = -1;
            }
        }
        if (xTab < 80) {
            // Droite
            if (monTableau[xTab + 1][yTab] == 0) {
                // On met un plus si c'est vide
                monTableau[xTab + 1][yTab] = -1;
            }
        }

        if (xTab < 80 && yTab < 80) {
            // Droite bas
            if (monTableau[xTab + 1][yTab + 1] == 0) {
                // On met un plus si c'est vide
                monTableau[xTab + 1][yTab + 1] = -1;
            }
        }
        if (yTab < 80) {
            // Bas
            if (monTableau[xTab][yTab + 1] == 0) {
                // On met un plus si c'est vide
                monTableau[xTab][yTab + 1] = -1;
            }
        }
        if (xTab > 0 && yTab < 80) {
            // Bas  Gauche
            if (monTableau[xTab - 1][yTab + 1] == 0) {
                // On met un plus si c'est vide
                monTableau[xTab - 1][yTab + 1] = -1;
            }
        }
    }

    /**
     * Gestion du calcul des points et de la desactivation des cartes adjacentes
     * 5 : Souris désactivée
     * 6 : Piège désactivé
     *
     * @param xTab
     * @param yTab
     */
    public void gererCartesAdjacentes(int xTab, int yTab) {
        /**
         * Si la carte posée est une souris
         */
        if (monTableau[xTab][yTab] == 1) {
            // On gagne un point
            cptPoint++;
            // Et on regarde les cases adjacentes
            // Si chat
            if (monTableau[xTab - 1][yTab] == 3 || monTableau[xTab + 1][yTab] == 3 || monTableau[xTab][yTab - 1] == 3 || monTableau[xTab][yTab + 1] == 3) {
                cptPoint--;
                // On desactive la souris
                monTableau[xTab][yTab] = 5;
            } else {
                // Si fromage à gauche
                if (monTableau[xTab - 1][yTab] == 2) {
                    cptPoint++;
                }
                // Si fromage à droite
                if (monTableau[xTab + 1][yTab] == 2) {
                    cptPoint++;
                }
                // Si fromage en haut
                if (monTableau[xTab][yTab - 1] == 2) {
                    cptPoint++;
                }
                // Si fromage en bas
                if (monTableau[xTab][yTab + 1] == 2) {
                    cptPoint++;
                }
            }
        }
        /**
         * Si la carte posée est un fromage
         */
        if (monTableau[xTab][yTab] == 2) {
            // Si souris à gauche
            if (monTableau[xTab - 1][yTab] == 1) {
                cptPoint++;
            }
            // Si souris à droite
            if (monTableau[xTab + 1][yTab] == 1) {
                cptPoint++;
            }
            // Si souris en haut
            if (monTableau[xTab][yTab - 1] == 1) {
                cptPoint++;
            }
            // Si souris en bas
            if (monTableau[xTab][yTab + 1] == 1) {
                cptPoint++;
            }
            // Si fromage entouré de souris
            if (monTableau[xTab - 1][yTab] == 1 && monTableau[xTab + 1][yTab] == 1 && monTableau[xTab][yTab - 1] == 1 && monTableau[xTab][yTab + 1] == 1) {
                cptPoint += 10;
            }
        }
        /**
         * Si la carte posée est un chat
         */
        if (monTableau[xTab][yTab] == 3) {
            // Si souris à gauche
            if (monTableau[xTab - 1][yTab] == 1) {
                cptPoint--;
                // On desactive la souris
                monTableau[xTab - 1][yTab] = 5;
            }
            // Si souris à droite
            if (monTableau[xTab + 1][yTab] == 1) {
                cptPoint--;
                // On desactive la souris
                monTableau[xTab + 1][yTab] = 5;
            }
            // Si souris en haut
            if (monTableau[xTab][yTab - 1] == 1) {
                cptPoint--;
                // On desactive la souris
                monTableau[xTab][yTab - 1] = 5;
            }
            // Si souris en bas
            if (monTableau[xTab][yTab + 1] == 1) {
                cptPoint--;
                // On desactive la souris
                monTableau[xTab][yTab + 1] = 5;
            }
        }

        /**
         * Si la carte posée est un piege
         */
        if (monTableau[xTab][yTab] == 4) {
            cptPieges++;

            // Si piege entouré de souris
            if (monTableau[xTab - 1][yTab] == 1 && monTableau[xTab + 1][yTab] == 1 && monTableau[xTab][yTab - 1] == 1 && monTableau[xTab][yTab + 1] == 1) {
                cptPieges--;
                monTableau[xTab][yTab] = 6;
            }
        }
    }

    public int[][] getMonTableau()
    {
        return monTableau;
    }

    public boolean jouer(int x, int y)
    {
        boolean result = true;

        Log.d("Modele", "cpt cartes : "+cptCartes);
        Log.d("Modele", "monTableau["+x+"]["+y+"] : "+monTableau[x][y]);
        Log.d("Modele", "cpt pieges : "+cptPieges);

       if(cptCartes == 0 || cptPieges == 3)
       {
           result = false;
           Log.d("Modele", "FIN DE LA PARTIE !!!!");
       }
       /*if(monTableau[x][y] != -1)
       {
            result = false;
            Log.d("Modele", "EMPLACEMENT NON DISPONIBLE");
       }*/
       else
       {
           Log.d("Modele", "APPEL DETERMINER CARTE");
           // On determine la carte à placer dans la case correspondante
           monTableau[x][y] = determinerCarte();
           // On place les plus
           gererPlus(x, y);
           // On geres les carte adjacentes et les points
           gererCartesAdjacentes(x, y);
       }

        return result;
    }
}



