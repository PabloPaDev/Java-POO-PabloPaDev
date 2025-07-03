package altres;

import personatges.Jugador;
import java.util.ArrayList;

public class Equip {
    private String nom;
    private ArrayList<Jugador> jugadors = new ArrayList<>();

    // Constructor amb nom
    public Equip(String nom) {
        this.nom = nom;
    }

    // Getter
    public String getNom() {
        return nom;
    }

    public ArrayList<Jugador> getJugadors() {
        return jugadors;
    }

    // Setter (opcional)
    public void setNom(String nom) {
        this.nom = nom;
    }

    // Afig un jugador si no està
    public void posa(Jugador j) {
        if (!jugadors.contains(j)) {
            jugadors.add(j);
            j.setEquip(this); // bidireccionalitat
        }
    }

    // Lleva un jugador pel seu nom
    public void lleva(String nomJugador) {
        Jugador trobat = null;
        for (Jugador j : jugadors) {
            if (j.getNom().equalsIgnoreCase(nomJugador)) {
                trobat = j;
                break;
            }
        }
        if (trobat != null) {
            jugadors.remove(trobat);
            trobat.setEquip(null); // trencar bidireccionalitat
        }
    }

    @Override
    public String toString() {
        String txt = "Equip " + nom + ":\n";
        for (Jugador j : jugadors) {
            txt += "- " + j + "\n";
        }
        return txt;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Equip e) {
            return this.nom.equalsIgnoreCase(e.nom);
        }
        return false;
    }
}
