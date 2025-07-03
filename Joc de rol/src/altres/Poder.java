package altres;

public class Poder {
    private String nom;
    private int bonusAtac;
    private int bonusDefensa;

    public Poder(String nom, int bonusAtac, int bonusDefensa) {
        this.nom = nom;
        this.bonusAtac = bonusAtac;
        this.bonusDefensa = bonusDefensa;
    }

    public String getNom() {
        return nom;
    }

    public int getBonusAtac() {
        return bonusAtac;
    }

    public int getBonusDefensa() {
        return bonusDefensa;
    }

    @Override
    public String toString() {
        return nom + " (BA:" + bonusAtac + ", BD:" + bonusDefensa + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Poder p) {
            return this.nom.equalsIgnoreCase(p.nom);
        }
        return false;
    }
}
