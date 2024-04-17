

public class Magicien extends Metier {
    protected int magieNiveau;

    public Magicien() {
        super("Magicien", "Dague", 2, 7, 4);
        this.magieNiveau = getMagieMaxNiveau();
    }

    public int getMagieNiveau() {
        return magieNiveau;
    }

    public void decrementerMagie(int coutMagie) {
        magieNiveau -= coutMagie;
    }

    public void rechargerMagie() {
        magieNiveau = getMagieMaxNiveau();
    }

    public void rayonDeGivre(Monstre monstre) {
        if (magieNiveau >= 2) {
            monstre.subirDegats(4);
            decrementerMagie(2);
        } else {
            System.out.println("Pas assez de magie pour lancer le sort.");
        }
    }

    public void bouleDeFeu(Monstre monstre) {
        if (magieNiveau >= 9) {
            monstre.subirDegats(15);
            decrementerMagie(9);
        } else {
            System.out.println("Pas assez de magie pour lancer le sort.");
        }
    }
}
