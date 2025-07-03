package personatges;

import altres.Equip;
import altres.Poder;

import java.util.ArrayList;
//definixc el jugado, en tots els atributs que tindrá
public abstract class Jugador {
    protected String nom;
    protected int puntsAtac;
    protected int puntsDefensa;
    protected int vides;
    protected Equip equip; // bidireccionalitat amb Equip
    protected ArrayList<Poder> poders = new ArrayList<>();

    public static int videsInicials = 200; // vides per defecte per a tots

    // Constructor
    public Jugador(String nom, int puntsAtac) {
        this.nom = nom;
        this.puntsAtac = puntsAtac;
        this.puntsDefensa = 100 - puntsAtac;
        this.vides = videsInicials;
    }

    public String getNom() { return nom; }
    public int getVides() { return vides; }
    public Equip getEquip() { return equip; }

    protected void setVides(int v) {
        vides = Math.max(v, 0);
    }

    public void setEquip(Equip nouEquip) {
        if (this.equip != null && this.equip != nouEquip) {
            this.equip.lleva(nom); // lleve de l'antic equip
        }
        this.equip = nouEquip;
        if (nouEquip != null && !nouEquip.getJugadors().contains(this)) {
            nouEquip.posa(this); // assegurem bidireccionalitat
        }
    }

    public void posa(Poder p) {
        if (!poders.contains(p)) {
            poders.add(p);
        }
    }

    public void lleva(Poder p) {
        poders.remove(p);
    }

    protected int getBonusAtac() {
        int bonus = 0;
        for (Poder p : poders) bonus += p.getBonusAtac();
        return bonus;
    }

    protected int getBonusDefensa() {
        int bonus = 0;
        for (Poder p : poders) bonus += p.getBonusDefensa();
        return bonus;
    }
/*va a atacar i com l´altre ni sap esquivar ni fer
 parry...doncs li donará segur*/
    public void ataca(Jugador defensor) {
        if (defensor == this) {
            System.out.println("Un jugador no pot atacar-se a si mateix!");
            return;
        }

        System.out.println("\nABANS DE L’ATAC:");
        System.out.println("Atacant: " + this);
        System.out.println("Atacat: " + defensor);

        int atacTotal = this.puntsAtac + this.getBonusAtac();
        defensor.esColpejatAmb(atacTotal);

        int contraAtac = defensor.puntsAtac + defensor.getBonusAtac();
        this.esColpejatAmb(contraAtac);

        System.out.println("DESPRÉS DE L’ATAC:");
        System.out.println("Atacant: " + this);
        System.out.println("Atacat: " + defensor);
    }
//metode per a quan els colpejen perque aci nin´gu sap fer parry...
    protected void esColpejatAmb(int quantitat) {
        int defensaTotal = this.puntsDefensa + this.getBonusDefensa();
        int dany = quantitat - defensaTotal;
        dany = Math.max(dany, 0);
        int videsAntigues = this.vides;
        this.vides = Math.max(this.vides - dany, 0);

        System.out.println(nom + " és colpejat amb " + quantitat + " punts i es defén amb " + defensaTotal
                + ". Vides: " + videsAntigues + " - " + dany + " = " + this.vides);
    }

    @Override
    public String toString() {
        String info = nom;
        if (equip != null) info += " [" + equip.getNom() + "]";
        info += " (PA:" + puntsAtac + ", PD:" + puntsDefensa + ", PV:" + vides + ")";
        if (!poders.isEmpty()) {
            info += " té els poders:";
            for (Poder p : poders) info += "\n- " + p;
        }
        return info;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Jugador j) {
            return this.nom.equalsIgnoreCase(j.nom);
        }
        return false;
    }
}
