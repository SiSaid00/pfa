package sample.entite;

public class Admin {

    private int CIN;
    private String nom, prenom, email, password;

    public int getCIN() {
        return CIN;
    }

    public void setCIN(int CIN) {
        this.CIN = CIN;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }




    public Admin(int CIN, String nom, String prenom, String email, String password) {
        this.CIN = CIN;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.password = password;


    }
}
