package pr2;

import java.util.*;

abstract class Tip {
    public abstract String getTip();

    public String toString() {
        return getTip();
    }
}

class Intreg extends Tip {
    private int valoare;

    public Intreg(int valoare) {
        this.valoare = valoare;
    }

    public String getTip() {
        return "Tip: Intreg";
    }

    public String toString() {
        return "Valoare: " + valoare;
    }
}

class Sir extends Tip {
    private String valoare;

    public Sir(String valoare) {
        this.valoare = valoare;
    }

    public String getTip() {
        return "Tip: Sir";
    }

    public String toString() {
        return "Valoare: " + valoare;
    }
}

class Colectie extends Tip {
    List<Tip> elemente;

    public Colectie() {
        this.elemente = new ArrayList<>();
    }

    public void adaugaElement(Tip element) {
        elemente.add(element);
    }

    public boolean egalitate(Colectie altaColectie) {
        if (this.elemente.size() != altaColectie.elemente.size()) {
            return false;
        }
        for (int i = 0; i < this.elemente.size(); i++) {
            if (!this.elemente.get(i).toString().equals(altaColectie.elemente.get(i).toString())) {
                return false;
            }
        }
        return true;
    }

    public String getTip() {
        return "Tip: Colectie";
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("(");
        for (int i = 0; i < elemente.size(); i++) {
            sb.append(elemente.get(i).toString());
            if (i < elemente.size() - 1) {
                sb.append(", ");
            }
        }
        sb.append(")");
        return sb.toString();
    }
}

public class ClientTip {
    public static void main(String[] args) {
        // Creăm elemente individuale
        Intreg i1 = new Intreg(7);
        Intreg i2 = new Intreg(4);
        Sir s1 = new Sir("Eu");
        Intreg i3 = new Intreg(12);

        // Creăm o colecție
        Colectie colectie1 = new Colectie();
        colectie1.adaugaElement(i1);
        colectie1.adaugaElement(i2);
        colectie1.adaugaElement(s1);
        colectie1.adaugaElement(i3);

        // Afișăm colecția
        System.out.println("Colectia 1: " + colectie1);

        // Creăm o altă colecție
        Colectie colectie2 = new Colectie();
        colectie2.adaugaElement(i1);
        colectie2.adaugaElement(i2);
        colectie2.adaugaElement(s1);
        colectie2.adaugaElement(new Intreg(12));

        // Afișăm a doua colecție
        System.out.println("Colectia 2: " + colectie2);

        // Testăm egalitatea între colecții
        System.out.println("Colectiile sunt egale? " + colectie1.egalitate(colectie2));
    }
}