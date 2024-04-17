

import java.util.Scanner;

public class PNJ {
    private String nom;
    private int pointsDeVie;
    private int pointsDeVieCourants;
    private String phraseDialogue;

    public PNJ(String nom, int pointsDeVie, String phraseDialogue) {
        this.nom = nom;
        this.pointsDeVie = pointsDeVie;
        this.pointsDeVieCourants = pointsDeVie;
        this.phraseDialogue = phraseDialogue;
    }

    public String getNom() {
        return nom;
    }

    public int getPointsDeVieCourants() {
        return pointsDeVieCourants;
    }

    public String getPhraseDialogue() {
        return phraseDialogue;
    }

    public boolean estEnVie() {
        return pointsDeVieCourants > 0;
    }

    // Méthode pour interagir avec le PNJ (parler)
    public void interagir(PersonnagePJ personnage) {
        System.out.println(personnage.getNom() + " parle à " + nom + ": " + phraseDialogue);
        System.out.println("Que voulez-vous faire ?");
        System.out.println("1. Continuer");

        Scanner sc = new Scanner(System.in);
        int choix = sc.nextInt();

        if (choix == 1) {
            System.out.println(nom + ": Très bien, bonne chance.");
        } else {
            System.out.println("Choix invalide.");
        }
        sc.close();

    }
}
