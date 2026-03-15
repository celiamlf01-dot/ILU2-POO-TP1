package villagegaulois;

import personnages.Gaulois;

public class Etal {
	private Gaulois vendeur;
	private String produit;
	private int quantiteDebutMarche;
	private int quantite;
	private boolean etalOccupe = false;

	public boolean isEtalOccupe() {
		return etalOccupe;
	}

	public Gaulois getVendeur() {
		return vendeur;
	}

	public void occuperEtal(Gaulois vendeur, String produit, int quantite) {
		this.vendeur = vendeur;
		this.produit = produit;
		this.quantite = quantite;
		quantiteDebutMarche = quantite;
		etalOccupe = true;
	}

	public String libererEtal() {
		if (!etalOccupe) { // ← AJOUTÉ
			throw new IllegalStateException("L'étal n'est pas occupé par un vendeur.");
		}
		StringBuilder chaine = new StringBuilder();
		chaine.append(vendeur.getNom() + " quitte son étal, il a vendu "
				+ (quantiteDebutMarche - quantite) + " " + produit
				+ " parmi les " + quantiteDebutMarche + " qu'il voulait vendre.\n");
		etalOccupe = false;
		vendeur = null;
		produit = null;
		quantiteDebutMarche = 0;
		quantite = 0;
		return chaine.toString();
	}

	public String afficherEtal() {
		if (etalOccupe) {
			return "L'étal de " + vendeur.getNom() + " est garni de " + quantite
					+ " " + produit + "\n";
		}
		return "L'étal est libre";
	}

	public String acheterProduit(int quantiteAcheter, Gaulois acheteur) {
		
		
		if (acheteur == null) {
	        System.err.println("ERREUR : acheteur null !");
	        new Exception("Acheteur null").printStackTrace();  // pile sur stderr
	        return "";  // chaîne vide comme demandé
	    }
		if (quantite < 1) {
		    throw new IllegalArgumentException("La quantité doit être positive !");
		}
		if (!etalOccupe) {
		    throw new IllegalStateException("L'étal n'est pas occupé !");
		}
		
			StringBuilder chaine = new StringBuilder();
			chaine.append(acheteur.getNom() + " veut acheter " + quantiteAcheter
					+ " " + produit + " à " + vendeur.getNom());
			if (quantite == 0) {
				chaine.append(", malheureusement il n'y en a plus !");
				quantiteAcheter = 0;
			}
			if (quantiteAcheter > quantite) {
				chaine.append(", comme il n'y en a plus que " + quantite + ", "
						+ acheteur.getNom() + " vide l'étal de "
						+ vendeur.getNom() + ".\n");
				quantiteAcheter = quantite;
				quantite = 0;
			}
			if (quantite != 0) {
				quantite -= quantiteAcheter;
				chaine.append(". " + acheteur.getNom()
						+ ", est ravi de tout trouver sur l'étal de "
						+ vendeur.getNom() + "\n");
			}
			return chaine.toString();
		
		
	}

	public boolean contientProduit(String produit) {
		return produit.equals(this.produit);
	}

}
