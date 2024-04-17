

import java.util.List;
import java.util.Scanner;

public class Lieu {
    private String nom;
    private List<PNJ> pnjs;
    private List<Monstre> monstres;
    private List<Lieu> lieuxAccessibles;

    public Lieu(String nom, List<PNJ> pnjs, List<Monstre> monstres, List<Lieu> lieuxAccessibles) {
        this.nom = nom;
        this.pnjs = pnjs;
        this.monstres = monstres;
        this.lieuxAccessibles = lieuxAccessibles;
    }

    public void setLieuxAccessibles(List<Lieu> lieuxAccessibles) {
        this.lieuxAccessibles = lieuxAccessibles;
    }

    public String getNom() {
        return nom;
    }

    public List<Lieu> getLieuxAccessibles() {
        return lieuxAccessibles;
    }

    public boolean hasLieuxAccessibles() {
        return lieuxAccessibles != null && !lieuxAccessibles.isEmpty();
    }

    public void afficherLieuxAccessibles() {
        if (hasLieuxAccessibles()) {
            System.out.println("Actions possibles à " + nom + ":");
            System.out.println("1. Se déplacer vers :");

            for (int i = 0; i < lieuxAccessibles.size(); i++) {
                System.out.println((i + 2) + ". " + lieuxAccessibles.get(i).getNom());
            }
        }
    }

    public List<PNJ> getPNJs() {
        return pnjs;
    }

    public void interagirAvecPNJ(PersonnagePJ personnage) {
        for (PNJ pnj : pnjs) {
            System.out.println("Vous parlez à " + pnj.getNom() + ": " + pnj.getPhraseDialogue());

            Scanner sc = new Scanner(System.in);
            System.out.println("1. Continuer");

            int choix = sc.nextInt();

            switch (choix) {
                case 1:
                    System.out.println(pnj.getNom() + " : Très bien, bonne chance dans votre quête.");
                    break;
                default:
                    System.out.println("Choix invalide.");
                    break;
            }
        }
    }

    public List<Monstre> getMonstres() {
        return monstres;
    }

    public void combattreMonstre(PersonnagePJ personnage, Monstre monstre) {
        System.out.println("Vous combattez " + monstre.getNom() + "!");

        Scanner sc = new Scanner(System.in);

        while (personnage.getPointsDeVieCourants() > 0 && monstre.estEnVie()) {
            System.out.println("Actions possibles :");
            System.out.println("1. Attaquer");
            System.out.println("0. Retour au menu principal");

            int choixCombat = sc.nextInt();

            if (choixCombat == 1) {
                int degats = personnage.getMetier().getDegatsArme();
                monstre.subirDegats(degats);
                System.out.println("Vous attaquez " + monstre.getNom() + " et lui infligez " + degats + " dégâts.");

                if (!monstre.estEnVie()) {
                    System.out.println("Vous avez vaincu " + monstre.getNom() + " !");
                    personnage.gagnerNiveau();
                } else {
                    int degatsMonstre = monstre.getDegatsSort();
                    personnage.subirDegats(degatsMonstre);
                    System.out.println(monstre.getNom() + " riposte et vous inflige " + degatsMonstre + " dégâts.");

                    if (personnage.estKo()) {
                        System.out.println("Vous êtes K.O.");
                        break;
                    }
                }
            } else if (choixCombat == 0) {
                break;
            } else {
                System.out.println("Action invalide.");
            }
        }

        if (personnage.estKo()) {
            System.out.println("Vous avez perdu. Game Over.");
        }
    }

}
