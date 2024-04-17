

public class Monstre {
    private String nom;
    private int pointsDeVie;
    private int pointsDeVieCourants;
    private int magie;
    private int degatsSort;
    private int pvMax;
    private int magieMax;

    public Monstre(String nom, int pointsDeVie, int magie, int degatsSort) {
        this.nom = nom;
        this.pointsDeVie = pointsDeVie;
        this.pointsDeVieCourants = pointsDeVie;
        this.magie = magie;
        this.degatsSort = degatsSort;
        this.pvMax = pointsDeVie;
        this.magieMax = magie;
    }

    public void lancerSort(String nomSort, PersonnagePJ cible, int coutMagieSort) {
        if (magie >= coutMagieSort) {
            System.out.println(nom + " lance " + nomSort + " sur " + cible.getNom());
            cible.subirDegats(degatsSort);
            magie -= coutMagieSort;
        } else {
            System.out.println(nom + " n'a pas assez de magie pour lancer " + nomSort);
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

    public boolean estEnVie() {
        return pointsDeVieCourants > 0;
    }

    public String getNom() {
        return nom;
    }

    public int getPointsDeVie() {
        return pointsDeVie;
    }

    public int getPointsDeVieCourants() {
        return pointsDeVieCourants;
    }

    public int getMagie() {
        return magie;
    }

    public int getDegatsSort() {
        return degatsSort;
    }

    public int getPvMax() {
        return pvMax;
    }

    public int getMagieMax() {
        return magieMax;
    }

}
