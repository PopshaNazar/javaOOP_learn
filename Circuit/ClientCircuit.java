package Circuit;

import java.util.*;

abstract class CircuitElectric {

    public abstract double RezistentaEcivalenta();

    public abstract double Curent(double tensiune);
}

class Rezistor extends CircuitElectric {
    private double rezistenta;

    public Rezistor(double rezistenta) {
        this.rezistenta = rezistenta;
    }

    public double RezistentaEcivalenta() {
        return rezistenta;
    }

    public boolean SubCircuit(CircuitElectric subCE) {
        if (subCE.equals(rezistenta))
            return true;
        return false;
    }

    public double Curent(double tensiune) {
        return tensiune / rezistenta;
    }

}

class CircuitSerie extends CircuitElectric {
    List<CircuitElectric> SR;

    public CircuitSerie() {
        this.SR = new ArrayList<CircuitElectric>();
    }

    public void addCircuit(CircuitElectric CE) {
        SR.add(CE);
    }

    public boolean SubCircuit(CircuitElectric subCE) {
        for (CircuitElectric CE : SR) {
            if (subCE.equals(CE))
                return true;
        }
        return false;
    }

    public double RezistentaEcivalenta() {
        double rezistenta = 0;
        for (CircuitElectric rez : SR) {
            rezistenta += rez.RezistentaEcivalenta();
        }
        return rezistenta;
    }

    public double Curent(double tensiune) {
        return tensiune / RezistentaEcivalenta();
    }

}

class CircuitParalel extends CircuitElectric {
    List<CircuitElectric> PR;

    public CircuitParalel() {
        this.PR = new ArrayList<CircuitElectric>();
    }

    public void addCircuit(CircuitElectric CE) {
        PR.add(CE);
    }

    public boolean SubCircuit(CircuitElectric subCE) {
        for (CircuitElectric CE : PR) {
            if (subCE.equals(CE))
                return true;
        }
        return false;
    }

    public double RezistentaEcivalenta() {
        double result = 0;
        for (CircuitElectric rez : PR) {
            result += (1 / rez.RezistentaEcivalenta());
        }
        result = 1 / result;
        return result;
    }

    public double Curent(double tensiune) {
        return tensiune / RezistentaEcivalenta();
    }
}

class ClientCircuit {
    public static void main(String[] args) {
        Rezistor R1 = new Rezistor(20);
        Rezistor R2 = new Rezistor(10);
        Rezistor R3 = new Rezistor(40);
        Rezistor R4 = new Rezistor(20);

        CircuitSerie CS1 = new CircuitSerie();
        CS1.addCircuit(R1);
        CS1.addCircuit(R2);
        System.out.println(CS1.RezistentaEcivalenta());

        CircuitSerie CS2 = new CircuitSerie();
        CS2.addCircuit(R3);
        CS2.addCircuit(R4);
        System.out.println(CS2.RezistentaEcivalenta());

        CircuitParalel CP = new CircuitParalel();
        CP.addCircuit(CS1);
        CP.addCircuit(CS2);
        System.out.println(CP.RezistentaEcivalenta());
        System.out.println(CP.Curent(8));
        System.out.println(CP.SubCircuit(CS2));
    }
}
