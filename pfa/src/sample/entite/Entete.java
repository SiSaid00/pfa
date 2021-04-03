package sample.entite;

public class Entete {
    private String gouvernorat, delegation, secteur, adress;

    public Entete(String gouvernorat, String delegation, String secteur, String adress) {
        this.gouvernorat = gouvernorat;
        this.delegation = delegation;
        this.secteur = secteur;
        this.adress = adress;
    }

    public void setGouvernorat(String gouvernorat) {
        this.gouvernorat = gouvernorat;
    }

    public void setDelegation(String delegation) {
        this.delegation = delegation;
    }

    public void setSecteur(String secteur) {
        this.secteur = secteur;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getGouvernorat() {
        return gouvernorat;
    }

    public String getDelegation() {
        return delegation;
    }

    public String getSecteur() {
        return secteur;
    }

    public String getAdress() {
        return adress;
    }
}
