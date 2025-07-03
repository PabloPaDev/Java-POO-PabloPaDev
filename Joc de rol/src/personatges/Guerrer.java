package personatges;

public class Guerrer extends Jugador {
    public Guerrer(String nom, int atac) {
        super(nom, atac);
    }

    @Override
    protected void esColpejatAmb(int quantitat) {
        int defensaTotal = this.puntsDefensa + this.getBonusDefensa();
        int dany = quantitat - defensaTotal;
        if (dany < 5) dany = 0;

        int videsAntigues = this.vides;
        this.vides = Math.max(this.vides - dany, 0);
        System.out.println(nom + " és colpejat amb " + quantitat + " punts i es defén amb " + defensaTotal
                + ". Vides: " + videsAntigues + " - " + dany + " = " + this.vides);
    }
}
