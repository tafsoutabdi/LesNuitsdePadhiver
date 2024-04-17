//Projet de: Tafsout ABDI && Oumaima BAKKAR

import java.util.List;
import java.util.Scanner;

public class JeuPrincipal {
    private static final int SE_DEPLACER = 1;
    private static final int SE_REPOSER = 2;
    private static final int PARLER_AUX_PNJ = 3;
    private static final int ATTAQUER_LES_MONSTRES = 4;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Entrez le nom du personnage : ");
        String nomPJ = sc.nextLine();

        System.out.println("Choisissez un métier :");
        System.out.println("1. Barbare");
        System.out.println("2. Guerrier");
        System.out.println("3. Magicien");
        int choixMetier = sc.nextInt();

        Metier metierPJ;

        switch (choixMetier) {
            case 1:
                metierPJ = new Barbare();
                break;
            case 2:
                metierPJ = new Guerrier();
                break;
            case 3:
                metierPJ = new Magicien();
                break;
            default:
                System.out.println("Choix de métier invalide. Le personnage sera un Barbare par défaut.");
                metierPJ = new Barbare();
                break;
        }

        PersonnagePJ joueur = new PersonnagePJ(nomPJ, metierPJ);

        PNJ pnj1 = new PNJ("PNJ 1", 10, "Bienvenue à Padhiver.");
        PNJ pnj2 = new PNJ("PNJ 2", 10, "Autre dialogue ici.");
        PNJ pnj3 = new PNJ("PNJ 3", 8, "Vous allez vers le nord.");
        PNJ pnj4 = new PNJ("PNJ 4", 12, "Prenez garde aux orcs.");
        PNJ pnj6 = new PNJ("PNJ 6", 20, "Méfiez-vous des chauve-souris.");
        PNJ pnj7 = new PNJ("PNJ 7", 25, "Bienvenue dans la crypte.");

        Monstre chauveSouris1 = new Monstre("Chauve-Souris 1", 5, 0, 0);
        Monstre chauveSouris2 = new Monstre("Chauve-Souris 2", 5, 0, 0);
        Monstre chauveSouris3 = new Monstre("Chauve-Souris 3", 5, 0, 0);
        Monstre orc1 = new Monstre("Orc 1", 5, 0, 0);
        Monstre orc2 = new Monstre("Orc 2", 5, 0, 0);
        Monstre nécromant = new Monstre("Nécromant", 30, 10, 0);
        Monstre gobelin1 = new Monstre("Gobelin 1", 4, 0, 0);
        Monstre gobelin2 = new Monstre("Gobelin 2", 4, 0, 0);
        Monstre dragon = new Monstre("Dragon", 12, 24, 0);

        Lieu padhiver = new Lieu("Padhiver", List.of(pnj1, pnj2), null, null);
        Lieu routeNord = new Lieu("Route Nord", List.of(pnj3), List.of(gobelin1), null);
        Lieu routeSud = new Lieu("Route Sud", List.of(pnj4), List.of(orc1, orc2), null);
        Lieu foret = new Lieu("Forêt", null, List.of(gobelin2), null);
        Lieu maraisDesMorts = new Lieu("Marais des Morts", List.of(pnj6),
                List.of(chauveSouris1, chauveSouris2, chauveSouris3), null);
        Lieu crypte = new Lieu("Crypte", List.of(pnj7), List.of(nécromant), null);
        Lieu volcan = new Lieu("Volcan", null, List.of(dragon), null);

        padhiver.setLieuxAccessibles(List.of(routeNord, routeSud));
        routeNord.setLieuxAccessibles(List.of(foret, padhiver));
        routeSud.setLieuxAccessibles(List.of(maraisDesMorts, padhiver));
        foret.setLieuxAccessibles(List.of(routeNord, volcan));
        maraisDesMorts.setLieuxAccessibles(List.of(crypte, routeSud));
        crypte.setLieuxAccessibles(List.of(maraisDesMorts));
        volcan.setLieuxAccessibles(List.of(foret));

        Lieu lieuActuel = padhiver;

        while (true) {
            System.out.println("Vous êtes à " + lieuActuel.getNom());
            System.out.println("Actions possibles :");
            System.out.println(SE_DEPLACER + ". Se déplacer");
            System.out.println(SE_REPOSER + ". Se reposer");
            System.out.println(PARLER_AUX_PNJ + ". Parler aux PNJ");
            System.out.println(ATTAQUER_LES_MONSTRES + ". Attaquer les monstres");
            System.out.println("0. Quitter");

            int choixAction = sc.nextInt();

            switch (choixAction) {
                case 0:
                    System.out.println("Vous avez quitté le combat.");
                    break;
                case SE_DEPLACER:
                    if (lieuActuel.hasLieuxAccessibles()) {
                        lieuActuel.afficherLieuxAccessibles();
                        int choixDeplacement = sc.nextInt();
                        if (choixDeplacement >= 2 && choixDeplacement <= lieuActuel.getLieuxAccessibles().size() + 1) {
                            lieuActuel = lieuActuel.getLieuxAccessibles().get(choixDeplacement - 2);
                        } else {
                            System.out.println("Choix de lieu invalide.");
                        }
                    } else {
                        System.out.println("Aucun lieu accessible depuis " + lieuActuel.getNom());
                    }
                    break;
                case SE_REPOSER:
                    if (joueur.getPointsDeVieCourants() < joueur.getPointsDeVieMax()) {
                        joueur.seReposer();
                        System.out.println("Vous vous reposez et regagnez des points de vie.");
                    } else {
                        System.out.println("Vous avez déjà tous vos points de vie.");
                    }
                    break;
                case PARLER_AUX_PNJ:
                    lieuActuel.interagirAvecPNJ(joueur);
                    break;
                case ATTAQUER_LES_MONSTRES:
                    boolean attaqueEnCours = true;

                    while (attaqueEnCours) {
                        System.out.println("Actions possibles :");
                        System.out.println("1. Attaquer");
                        System.out.println("0. Retour au menu principal");
                        int choixAttaque = sc.nextInt();

                        if (choixAttaque == 1) {
                            List<Monstre> monstresDansLaZone = lieuActuel.getMonstres();
                            if (monstresDansLaZone != null && !monstresDansLaZone.isEmpty()) {

                                System.out.println("Monstres dans la zone :");
                                for (int i = 0; i < monstresDansLaZone.size(); i++) {
                                    System.out.println((i + 1) + ". " + monstresDansLaZone.get(i).getNom());
                                }
                                System.out.println("0. Retour au menu principal");

                                int choixMonstre = sc.nextInt();
                                if (choixMonstre > 0 && choixMonstre <= monstresDansLaZone.size()) {

                                    lieuActuel.combattreMonstre(joueur, monstresDansLaZone.get(choixMonstre - 1));
                                    if (joueur.estKo()) {
                                        System.out.println("Vous avez perdu. Game Over.");
                                        break;
                                    }
                                } else if (choixMonstre == 0) {
                                    attaqueEnCours = false;
                                } else {
                                    System.out.println("Choix de monstre invalide.");
                                }
                            } else {
                                System.out.println("Il n'y a pas de monstres à combattre dans cette zone.");
                            }
                        } else if (choixAttaque == 0) {
                            attaqueEnCours = false;
                        } else {
                            System.out.println("Action invalide.");
                        }
                    }

                    break;
                default:
                    System.out.println("Action invalide.");
                    break;
            }

            if (choixAction == 0) {
                System.out.println("Vous avez quitté le combat.");
                break;
            }

            if (joueur.getNiveau() >= 10) {
                System.out.println("Félicitations, vous avez gagné !");
                break;
            } else if (joueur.estKo()) {
                System.out.println("Vous avez perdu. Game Over.");
                break;
            }
        }
    }
}
