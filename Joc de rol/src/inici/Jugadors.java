package inici;

import personatges.*;
import altres.Equip;
import altres.Poder;
import java.util.ArrayList;
import java.util.Scanner;

public class Jugadors {
    static ArrayList<Jugador> llista = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    public static void menu() {
        int opcio;
        do {
            System.out.println("\n-- MENÚ JUGADORS --");
            System.out.println("1. Crear");
            System.out.println("2. Consultar");
            System.out.println("3. Eliminar");
            System.out.println("4. Assignar a equip");
            System.out.println("5. Llevar d’equip");
            System.out.println("6. Assignar poder");
            System.out.println("0. Eixir");
            System.out.print("Opció: ");
            opcio = sc.nextInt(); sc.nextLine();

            switch (opcio) {
                case 1 -> crear();
                case 2 -> consultar();
                case 3 -> eliminar();
                case 4 -> assignarEquip();
                case 5 -> llevarEquip();
                case 6 -> assignarPoder();
            }
        } while (opcio != 0);
    }

    public static void crear() {
        System.out.print("Nom del jugador: ");
        String nom = sc.nextLine();

        System.out.print("Tipus (G = Guerrer, M = Mag, A = Alien): ");
        String tipus = sc.nextLine().toUpperCase();

        System.out.print("Punts d'atac (1-100): ");
        int atac = sc.nextInt(); sc.nextLine();

        Jugador j = switch (tipus) {
            case "G" -> new Guerrer(nom, atac);
            case "M" -> new Huma(nom, atac);
            case "A" -> new Alien(nom, atac);
            default -> null;
        };

        if (j != null && !llista.contains(j)) {
            llista.add(j);
            System.out.println("Jugador creat.");
        } else {
            System.out.println("Jugador no vàlid o ja existent.");
        }
    }

    public static void consultar() {
        for (Jugador j : llista) {
            System.out.println(j);
        }
    }

    public static void eliminar() {
        System.out.print("Nom del jugador a eliminar: ");
        String nom = sc.nextLine();
        llista.removeIf(j -> j.getNom().equalsIgnoreCase(nom));
    }

    public static void assignarEquip() {
        System.out.print("Nom del jugador: ");
        String nomJugador = sc.nextLine();
        System.out.print("Nom de l'equip: ");
        String nomEquip = sc.nextLine();

        Jugador j = trobarJugador(nomJugador);
        Equip e = Equips.trobarEquip(nomEquip);

        if (j != null && e != null) {
            e.posa(j);
            System.out.println("Jugador assignat a equip.");
        } else {
            System.out.println("Jugador o equip no trobat.");
        }
    }

    public static void llevarEquip() {
        System.out.print("Nom del jugador: ");
        String nomJugador = sc.nextLine();

        Jugador j = trobarJugador(nomJugador);
        if (j != null && j.getEquip() != null) {
            j.setEquip(null);
            System.out.println("Jugador llevat de l’equip.");
        } else {
            System.out.println("Jugador no trobat o no té equip.");
        }
    }

    public static void assignarPoder() {
        System.out.print("Nom del jugador: ");
        String nomJugador = sc.nextLine();
        System.out.print("Nom del poder: ");
        String nomPoder = sc.nextLine();

        Jugador j = trobarJugador(nomJugador);
        Poder p = Poders.trobarPoder(nomPoder);

        if (j != null && p != null) {
            j.posa(p);
            System.out.println("Poder assignat.");
        } else {
            System.out.println("Jugador o poder no trobat.");
        }
    }

    public static Jugador trobarJugador(String nom) {
        for (Jugador j : llista) {
            if (j.getNom().equalsIgnoreCase(nom)) return j;
        }
        return null;
    }
}
