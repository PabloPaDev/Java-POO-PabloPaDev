package personatges;
//també conegut com a MAG
public class Huma extends Jugador {
    public Huma(String nom, int atac) {
        super(nom, atac);
        if (vides > 100) this.vides = 100; // màxim de 100 vides
    }
}
