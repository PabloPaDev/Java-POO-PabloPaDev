package personatges;
//aquest personatge el que millor stats te
public class Alien extends Jugador {
    public Alien(String nom, int atac) {
        super(nom, atac);
    }

    @Override
    public void ataca(Jugador defensor) {
        if (this.vides > 20) {
            this.puntsAtac += 3;
            this.puntsDefensa -= 3;
        }
        super.ataca(defensor);
    }
}
