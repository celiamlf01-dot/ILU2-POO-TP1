package villagegaulois;

import personnages.Chef;
import personnages.Gaulois;

public class Village {
	private String nom;
	private  Chef chef;
	private Gaulois[] villageois;
	private int nbVillageois = 0;

	public Village(String nom, int nbVillageoisMaximum) {
		this.nom = nom;
		villageois = new Gaulois[nbVillageoisMaximum];
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
                    sb.append(etal.getVendeur().getNom())
                      .append(" vend ")
                      .append(etal.getProduit())
                      .append(" - ")
                      .append(etal.getQuantite())
                      .append(" unités restantes.\n");
                } else {
                    etalsLibres++;
                }
            }

            sb.append("Il reste ").append(etalsLibres).append(" étals non utilisés dans le marché.\n");
            return sb.toString();
        }
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

	public String afficherVillageois() {
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
}