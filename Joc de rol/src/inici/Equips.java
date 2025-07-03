package inici;

import altres.Equip;
import java.util.ArrayList;
import java.util.Scanner;
/*vaig a intentar crear els quips, porte un jaleo bo...
pero poc a poc
 */
public class Equips {
    static ArrayList<Equip> llista = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    public static void menu() {
        int opcio;
        do {
            System.out.println("\n-- MENÚ EQUIPS --");
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
        System.out.print("Nom de l'equip: ");
        String nom = sc.nextLine();
        Equip e = new Equip(nom);
        if (!llista.contains(e)) {
            llista.add(e);
            System.out.println("Equip creat.");
        } else {
            System.out.println("Ja existix un equip amb eixe nom.");
        }
    }

    public static void consultar() {
        for (Equip e : llista) {
            System.out.println(e);
        }
    }

    public static void eliminar() {
        System.out.print("Nom de l'equip a eliminar: ");
        String nom = sc.nextLine();
        llista.removeIf(e -> e.getNom().equalsIgnoreCase(nom));
    }

    public static Equip trobarEquip(String nom) {
        for (Equip e : llista) {
            if (e.getNom().equalsIgnoreCase(nom)) return e;
        }
        return null;
    }
}
