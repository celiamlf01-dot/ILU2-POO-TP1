package histoire;

import personnages.Gaulois;
import villagegaulois.Etal;

public class ScenarioCasDegrade {
    public static void main(String[] args) {
        Etal etal = new Etal();

        // 1. libererEtal
        try {
            etal.libererEtal();
        } catch (IllegalStateException e) {
            System.err.println("ERREUR libererEtal : " + e.getMessage());
            e.printStackTrace();
        }

        // 2a. acheteur null
        try {
            String resultat = etal.acheterProduit(5, null);
            System.out.println("Résultat achat null : '" + resultat + "'");
        } catch (Exception e) {
            System.err.println("ERREUR inattendue : " + e.getMessage());
        }

        // 2b. quantité <= 0
        try {
            String resultat = etal.acheterProduit(0, new Gaulois("Testeur", 5));
            System.out.println("Résultat quantité 0 : " + resultat);
        } catch (IllegalArgumentException e) {
            System.err.println("ERREUR quantité : " + e.getMessage());
            e.printStackTrace();
        }

        // === NOUVEAU TEST 2c : étal vide ===
        try {
            String resultat = etal.acheterProduit(10, new Gaulois("Abraracourcix", 5));
            System.out.println("Résultat étal vide : " + resultat);
        } catch (IllegalStateException e) {
            System.err.println("ERREUR étal vide : " + e.getMessage());
            e.printStackTrace();
        }

        System.out.println("Fin du test");
    }
}