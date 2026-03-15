package villagegaulois;

import personnages.Chef;
import personnages.Gaulois;

public class Village {
	private String nom;
	private  Chef chef;
	private Gaulois[] villageois;
	private int nbVillageois = 0;
	private Marche marche;

	public Village(String nom, int nbVillageoisMaximum,int nbEtalsDuMarche) {
		this.nom = nom;
		villageois = new Gaulois[nbVillageoisMaximum];
		this.marche= new Marche(nbEtalsDuMarche);
	}

	public String getNom() {
		return nom;
	}

	public void setChef(Chef chef) {
		this.chef = chef;
	}
	public static class Marche{
	
		private Etal[] etals; 
		public Marche(int nbretal)
		{
			etals=new Etal [nbretal];
			for (int i = 0; i < nbretal; i++) {
	                etals[i] = new Etal(); // Créer un étal vide pour chaque case
}
			
		}
		public void utiliserEtal(int indiceEtal, Gaulois vendeur,String produit, int nbProduit)
		{
			if (indiceEtal >= 0 && indiceEtal < etals.length && !etals[indiceEtal].isEtalOccupe()) {
			
			etals[indiceEtal].occuperEtal(vendeur,produit, nbProduit);
			}
			else {
				System.out.println("Cet etal est deja occupe ou indice incrt");
			}
			
		}
		public int trouverEtalLibre() {
			for (int i=0;i<etals.length;++i)
			{
				if(!etals[i].isEtalOccupe())
				{return i;}
			}
			return -1;
		}
		public Etal[] trouverEtals(String produit) {
            int count = 0;
            for (Etal etal : etals) {
                if (etal.contientProduit(produit)) {
                    count++;
                }
            }

            Etal[] result = new Etal[count];
            int index = 0;
            for (Etal etal : etals) {
                if (etal.contientProduit(produit)) {
                    result[index++] = etal;
                }
            }
            return result;
        }
		public Etal trouverVendeur(Gaulois gaulois)
		{
			   for (Etal etal : etals) {
	                if (etal.getVendeur() != null && etal.getVendeur().equals(gaulois)) {
	                    return etal;
	                }
	            }
	            return null;
		}
		public String afficherMarche() {
		    StringBuilder sb = new StringBuilder();
		    int etalsLibres = 0;

		    for (Etal etal : etals) {
		        if (etal.isEtalOccupe()) {
		            sb.append(etal.afficherEtal());  // Appeler la méthode afficherEtal() pour chaque étal
		        } else {
		            etalsLibres++;
		        }
		    }

		    sb.append("Il reste ").append(etalsLibres).append(" étals non utilisés dans le marché.\n");
		    return sb.toString();
		}

		
		
		}
	
		
		
	

	public void ajouterHabitant(Gaulois gaulois) {
		if (nbVillageois < villageois.length) {
			villageois[nbVillageois] = gaulois;
			nbVillageois++;
		}
	}

	public Gaulois trouverHabitant(String nomGaulois) {
		if (nomGaulois.equals(chef.getNom())) {
			return chef;
		}
		for (int i = 0; i < nbVillageois; i++) {
			Gaulois gaulois = villageois[i];
			if (gaulois.getNom().equals(nomGaulois)) {
				return gaulois;
			}
		}
		return null;
	}

	public String afficherVillageois() throws VillageSansChefException {
	    if (chef == null) {
	        throw new VillageSansChefException("Le village n'a pas de chef !");
	    }

	    StringBuilder chaine = new StringBuilder();
	    if (nbVillageois < 1) {
	        chaine.append("Il n'y a encore aucun habitant au village du chef "
	                + chef.getNom() + ".\n");
	    } else {
	        chaine.append("Au village du chef " + chef.getNom()
	                + " vivent les légendaires gaulois :\n");
	        for (int i = 0; i < nbVillageois; i++) {
	            chaine.append("- " + villageois[i].getNom() + "\n");
	        }
	    }
	    return chaine.toString();
	}
	public String installerVendeur(Gaulois vendeur, String produit, int nbProduit) {
	    int etalLibre = marche.trouverEtalLibre();
	    if (etalLibre == -1) {
	        return vendeur.getNom() + " cherche un endroit pour vendre " + nbProduit + " " + produit + ". Il n'y a plus d'étals libres.\n";
	    }
	    
	    marche.utiliserEtal(etalLibre, vendeur, produit, nbProduit);
	    return "Le vendeur " + vendeur.getNom() + " vend des " + produit + " à l'étal n°" + (etalLibre + 1) + ".\n";
	}
	public String rechercherVendeursProduit(String produit) {
	    Etal[] etals = marche.trouverEtals(produit);
	    if (etals.length == 0) {
	        return "Il n'y a pas de vendeur qui propose des " + produit + " au marché.\n";
	    }

	    StringBuilder sb = new StringBuilder("Les vendeurs qui proposent des " + produit + " sont :\n");
	    for (Etal etal : etals) {
	        sb.append("- " + etal.getVendeur().getNom() + "\n");
	    }
	    return sb.toString();
	}
	public Etal rechercherEtal(Gaulois vendeur) {
	    return marche.trouverVendeur(vendeur);
	}
	public String partirVendeur(Gaulois vendeur) {
	    Etal etal = rechercherEtal(vendeur);
	    if (etal != null) {
	        return etal.libererEtal();  // Libère l'étal et retourne le message
	    } else {
	        return "Le vendeur " + vendeur.getNom() + " n'a pas de place sur le marché.\n";
	    }
	}
	public String afficherMarche() {
	    return marche.afficherMarche();
	}
	
}