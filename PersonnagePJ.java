

public class PersonnagePJ {
    private String nom;
    private int pointsDeVieCourants;
    private Metier metier;
    private int niveau;
    private int pointsDeVieMax;
    private String equipement;

    public PersonnagePJ(String nom, Metier metier) {
        this.nom = nom;
        this.metier = metier;
        this.pointsDeVieMax = metier.getPvMaxNiveau();
        this.pointsDeVieCourants = this.pointsDeVieMax;
        this.niveau = 1;
        this.equipement = "";
    }

    public void parler(PNJ pnj) {
        System.out.println(nom + " : " + pnj.getPhraseDialogue());
    }

    public void attaquer(Monstre monstre) {
        int degats = metier.getDegatsArme();
        monstre.subirDegats(degats);
        System.out.println(nom + " attaque " + monstre.getNom() + " et lui inflige " + degats + " dégâts.");
        if (!monstre.estEnVie()) {
            System.out.println(nom + " a vaincu " + monstre.getNom() + " !");
            niveau++; // Augmenter le niveau du PJ après avoir vaincu un monstre
        }
    }

    public void seReposer() {
        pointsDeVieCourants = pointsDeVieMax;
        System.out.println(nom + " se repose et récupère tous ses points de vie.");
        if (metier instanceof Magicien) {
            Magicien magicien = (Magicien) metier;
            magicien.rechargerMagie();
            System.out.println(nom + " recharge sa magie.");
        }
    }

    public void lancerSort(Monstre monstre, String nomSort, int degatsSort, int coutMagie) {
        if (metier instanceof Magicien) {
            Magicien magicien = (Magicien) metier;
            int magieDisponible = magicien.getMagieNiveau();
            if (magieDisponible >= coutMagie) {
                monstre.subirDegats(degatsSort);
                magicien.decrementerMagie(coutMagie);
                System.out.println(nom + " lance " + nomSort + " sur " + monstre.getNom() +
                        " et lui inflige " + degatsSort + " dégâts.");
                if (!monstre.estEnVie()) {
                    System.out.println(nom + " a vaincu " + monstre.getNom() + " !");
                    niveau++;
                }
            } else {
                System.out.println("Pas assez de magie pour lancer le sort.");
            }
        } else {
            System.out.println(nom + " ne peut pas lancer de sorts. Il n'est pas un magicien.");
        }
    }

    public void subirDegats(int degats) {
        pointsDeVieCourants -= degats;
        if (pointsDeVieCourants < 0) {
            pointsDeVieCourants = 0;
        }
        System.out.println(nom + " subit " + degats + " points de dégâts.");
        if (pointsDeVieCourants == 0) {
            System.out.println(nom + " est K.O.");
        }
    }

    public void gagnerNiveau() {
        niveau++;
        System.out.println("bravo! vous etes actuellement dans le niveau : " + niveau);
    }

    public boolean estKo() {
        return pointsDeVieCourants <= 0;
    }

    public String getNom() {
        return nom;
    }

    public Metier getMetier() {
        return metier;
    }

    public int getNiveau() {
        return niveau;
    }

    public int getPointsDeVieCourants() {
        return pointsDeVieCourants;
    }

    public int getPointsDeVieMax() {
        return pointsDeVieMax;
    }

    public String getEquipement() {
        return equipement;
    }
}
