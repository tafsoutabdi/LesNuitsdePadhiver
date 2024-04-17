

public class Metier {
    private String nomMetier;
    private String arme;
    private int degatsArme;
    private int pvMaxNiveau;
    private int magieMaxNiveau;

    public Metier(String nomMetier, String arme, int degatsArme, int pvMaxNiveau, int magieMaxNiveau) {
        this.nomMetier = nomMetier;
        this.arme = arme;
        this.degatsArme = degatsArme;
        this.pvMaxNiveau = pvMaxNiveau;
        this.magieMaxNiveau = magieMaxNiveau;
    }

    public int getPvMaxNiveau() {
        return pvMaxNiveau;
    }

    public void setPvMaxNiveau(int pvMaxNiveau) {
        this.pvMaxNiveau = pvMaxNiveau;
    }

    public int getMagieMaxNiveau() {
        return magieMaxNiveau;
    }

    public void setMagieMaxNiveau(int magieMaxNiveau) {
        this.magieMaxNiveau = magieMaxNiveau;
    }

    public String getNomMetier() {
        return nomMetier;
    }

    public void setNomMetier(String nomMetier) {
        this.nomMetier = nomMetier;
    }

    public String getArme() {
        return arme;
    }

    public void setArme(String arme) {
        this.arme = arme;
    }

    public int getDegatsArme() {
        return degatsArme;
    }

    public void setDegatsArme(int degatsArme) {
        this.degatsArme = degatsArme;
    }
}
