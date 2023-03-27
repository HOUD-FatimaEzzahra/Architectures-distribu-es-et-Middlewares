package ma.enset;

import java.util.Date;

public class Compte {
    private int code;
    private double solde;
    private Date date_creation;

    public Compte(int code, double solde, Date date_creation) {
        this.code = code;
        this.solde = solde;
        this.date_creation = date_creation;
    }

    public int getCode() {
        return code;
    }

    public double getSolde() {
        return solde;
    }

    public Date getDate_creation() {
        return date_creation;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setSolde(double solde) {
        this.solde = solde;
    }

    public void setDate_creation(Date date_creation) {
        this.date_creation = date_creation;
    }
}
