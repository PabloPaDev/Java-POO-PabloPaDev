package inici;

import personatges.Jugador;
import personatges.Huma;
import personatges.Guerrer;
import personatges.Alien;
import altres.Equip;
import altres.Poder;

import java.util.Scanner;
import java.util.Random;
/*Aquest es el meu joc de rol, primer tindras que configurar els jugadors,
la clase, el poder i els equips als que pertanyen i despres lluitar.
 */
public class JocDeRol {
    static Scanner sc = new Scanner(System.in);
    static Random rnd = new Random();

    public static void main(String[] args) {
        int opcio;
        do {
            System.out.println("\n=== JOC DE ROL ===");
            System.out.println("1. Configuració");
            System.out.println("2. Jugar");
            System.out.println("3. Proves");
            System.out.println("0. Eixir");
            System.out.print("Opció: ");
            opcio = sc.nextInt(); sc.nextLine();

            switch (opcio) {
                case 1 -> menuConfiguracio();
                case 2 -> jugar();
                case 3 -> menuProves();
            }
        } while (opcio != 0);
    }
//jo ja estic cansat...
    // Menú de configuració
    public static void menuConfiguracio() {
        int opcio;
        do {
            System.out.println("\n--- CONFIGURACIÓ ---");
            System.out.println("1. Jugadors");
            System.out.println("2. Equips");
            System.out.println("3. Poders");
            System.out.println("0. Eixir");
            System.out.print("Opció: ");
            opcio = sc.nextInt(); sc.nextLine();

            switch (opcio) {
                case 1 -> Jugadors.menu();
                case 2 -> Equips.menu();
                case 3 -> Poders.menu();
            }
        } while (opcio != 0);
    }

    // Joc automàtic fins que quede 1 viu
    public static void jugar() {
        if (Jugadors.llista.size() < 2) {
            System.out.println("Cal almenys 2 jugadors per jugar.");
            return;
        }

        while (quantsVius() > 1) {
            Jugador atacant = triaAleatoriViu();
            Jugador atacat;
            do {
                atacat = triaAleatoriViu();
            } while (atacant == atacat);
            atacant.ataca(atacat);
        }

        System.out.println("\nGUANYADOR:");
        for (Jugador j : Jugadors.llista) {
            if (j.getVides() > 0) {
                System.out.println(j);
            }
        }
    }

    // Tria jugador viu aleatori
    public static Jugador triaAleatoriViu() {
        Jugador j;
        do {
            j = Jugadors.llista.get(rnd.nextInt(Jugadors.llista.size()));
        } while (j.getVides() <= 0);
        return j;
    }

    // Compta quants jugadors vius queden
    public static int quantsVius() {
        int vius = 0;
        for (Jugador j : Jugadors.llista) {
            if (j.getVides() > 0) vius++;
        }
        return vius;
    }

    // Menú de proves de fases
    public static void menuProves() {
        int opcio;
        do {
            System.out.println("\n--- MENÚ PROVES ---");
            System.out.println("1. Prova Fase 2");
            System.out.println("2. Prova Fase 3");
            System.out.println("3. Prova Fase 4");
            System.out.println("4. Prova Fase 5");
            System.out.println("0. Eixir");
            System.out.print("Opció: ");
            opcio = sc.nextInt(); sc.nextLine();

            switch (opcio) {
                case 1 -> provaFase2();
                case 2 -> provaFase3();
                case 3 -> provaFase4();
                case 4 -> provaFase5();
            }
        } while (opcio != 0);
    }

    // Proves FASE 2
    public static void provaFase2() {
        Jugador j1 = new Huma("Merlí", 50);
        Jugador j2 = new Guerrer("Lancelot", 70);

        System.out.println(j1);
        System.out.println(j2);

        j1.ataca(j2);
    }

    // Proves FASE 3: Polimorfisme
    public static void provaFase3() {
        Jugador h = new Huma("Anna", 90);     // màxim 100 vides
        Jugador g = new Guerrer("Thor", 60);
        Jugador a = new Alien("Zorg", 80);

        System.out.println(h);
        System.out.println(g);
        System.out.println(a);

        h.ataca(g); // atac normal
        a.ataca(g); // alien augmenta atac i baixa defensa
        g.ataca(a); // guerrer ignora ferides lleus
    }

    // Proves FASE 4: Equips
    public static void provaFase4() {
        Jugador j1 = new Huma("Gandalf", 50);
        Jugador j2 = new Guerrer("Aragorn", 60);

        Equip e1 = new Equip("Els Valents");
        e1.posa(j1);
        e1.posa(j2);

        System.out.println(e1);
        e1.lleva("Gandalf");
        System.out.println(e1);
        System.out.println(j1); // hauria de tindre equip null
    }

    // Proves FASE 5: Poders
    public static void provaFase5() {
        Jugador j1 = new Huma("Elric", 40);
        Poder p1 = new Poder("Invisibilitat", 10, 6);
        Poder p2 = new Poder("Volar", 10, 20);

        j1.posa(p1);
        j1.posa(p2);

        System.out.println(j1);

        Jugador j2 = new Guerrer("Drake", 70);
        j1.ataca(j2);
    }
}
