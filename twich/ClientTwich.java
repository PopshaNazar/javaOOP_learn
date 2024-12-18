package twich;

import java.util.*;

abstract class Utilizator {
    public abstract int calculeazaVenit(int minute);

    public abstract String toString();
}

class Subscriber extends Utilizator {
    String nume;
    int nivel;

    public Subscriber(String nume, int nivel) {
        this.nume = nume;
        this.nivel = nivel;
    }

    public int calculeazaVenit(int minute) {
        return 3 * (nivel * minute) / 2; // asa inmultesc la 1,5 cu int :))
    }

    public String toString() {
        return nume + " - subscriber - " + nivel;
    }
}

class Creator extends Utilizator {
    String nume;
    List<Subscriber> subscriberi;

    public Creator(String nume) {
        this.nume = nume;
        this.subscriberi = new ArrayList<>();
    }

    public void adaugaSubscriber(Subscriber sub) {
        subscriberi.add(sub);
    }

    public int calculeazaVenit(int minute) {
        int venit = 0;
        for (Subscriber sub : subscriberi) {
            venit += sub.calculeazaVenit(minute);
        }
        return venit;
    }

    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append(nume + " - creator\nSubs:");
        for (Subscriber sub : subscriberi) {
            result.append("\n" + sub.toString());
        }
        return "\n" + result;
    }

}

class ClientTwich {
    public static void main(String[] args) {
        Subscriber sub1 = new Subscriber("Prapaditu777", 2);
        Subscriber sub2 = new Subscriber("AndreeaSecretara404", 0 + 0 + 0 + 0);
        Subscriber sub3 = new Subscriber("Nazar-Bazar18", 2);
        Subscriber sub4 = new Subscriber("Tata_Grupului_AUE", 3);

        Creator cr1 = new Creator("CTI");
        Creator cr2 = new Creator("cedatiMental");

        cr1.adaugaSubscriber(sub1);
        cr1.adaugaSubscriber(sub2);
        cr1.adaugaSubscriber(sub3);
        cr1.adaugaSubscriber(sub4);
        cr2.adaugaSubscriber(sub2);
        cr2.adaugaSubscriber(sub3);

        System.out.println(cr1.toString());
        System.out.println("venit: " + cr1.calculeazaVenit(40));
        System.out.println(cr2.toString());
        System.out.println("venit: " + cr2.calculeazaVenit(40));

    }
}
