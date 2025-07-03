package inici;

import altres.Poder;
import java.util.ArrayList;
import java.util.Scanner;
/*No tenia prou en els equips, que tambe vols poders...
que es diumenge paco
 */
public class Poders {
    static ArrayList<Poder> llista = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    public static void menu() {
        int opcio;
        do {
            System.out.println("\n-- MENÚ PODERS --");
            System.out.println("1. Crear");
            System.out.println("2. Consultar");
            System.out.println("3. Eliminar");
            System.out.println("0. Eixir");
            System.out.print("Opció: ");
            opcio = sc.nextInt(); sc.nextLine();

            switch (opcio) {
                case 1 -> crear();
                case 2 -> consultar();
                case 3 -> eliminar();
            }
        } while (opcio != 0);
    }

    public static void crear() {
        System.out.print("Nom del poder: ");
        String nom = sc.nextLine();
        System.out.print("Bonus atac: ");
        int ba = sc.nextInt();
        System.out.print("Bonus defensa: ");
        int bd = sc.nextInt(); sc.nextLine();

        Poder p = new Poder(nom, ba, bd);
        if (!llista.contains(p)) {
            llista.add(p);
            System.out.println("Poder creat.");
        } else {
            System.out.println("Ja existix un poder amb eixe nom.");
        }
    }

    public static void consultar() {
        for (Poder p : llista) {
            System.out.println(p);
        }
    }

    public static void eliminar() {
        System.out.print("Nom del poder a eliminar: ");
        String nom = sc.nextLine();
        llista.removeIf(p -> p.getNom().equalsIgnoreCase(nom));
    }

    public static Poder trobarPoder(String nom) {
        for (Poder p : llista) {
            if (p.getNom().equalsIgnoreCase(nom)) return p;
        }
        return null;
    }
}
