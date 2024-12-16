package pr1;

import java.util.Random;
import java.util.Date;

class CoordinateGenerator {
    private Random randomGenerator;

    public CoordinateGenerator() {
        Date now = new Date();
        long sec = now.getTime();
        randomGenerator = new Random(sec);
    }

    public int generateX() {
        int x = randomGenerator.nextInt(101);
        if (x < 5) {
            x = 0;
        } else if (x > 95) {
            x = 100;
        } else {
            x = randomGenerator.nextInt(99) + 1;
        }
        return x;
    }

    public int generateY() {
        int y = randomGenerator.nextInt(101);
        if (y < 5) {
            y = 0;
        } else if (y > 95) {
            y = 50;
        } else {
            y = randomGenerator.nextInt(49) + 1;
        }
        return y;
    }
}

class Out extends Exception {
    public Out(String message) {
        super(message);
    }
};

class Goal extends Exception {
    public Goal(String message) {
        super(message);
    }
};

class Corner extends Exception {
    public Corner(String message) {
        super(message);
    }
};

class Minge {
    private int X;
    private int Y;
    private CoordinateGenerator generate;

    public Minge(int X, int Y) {
        this.X = X;
        this.Y = Y;
        this.generate = new CoordinateGenerator();
    }

    public void suteaza() throws Out, Goal, Corner {
        X = generate.generateX();
        Y = generate.generateY();
        if (Y == 0 || Y == 50) {
            throw new Out("X=" + X + "|Y=" + Y + "  OUT!");
        } else if ((X == 0 || X == 100) && (Y >= 20 && Y <= 20)) {
            throw new Goal("X=" + X + "|Y=" + Y + "  GOAL!!");
        } else if ((X == 0 || X == 100) && ((0 < Y && Y < 20) || (30 < Y && Y < 50))) {
            throw new Corner("X=" + X + "|Y=" + Y + "  Corner!");
        }
    }

    public int getX() {
        return X;
    }

    public int getY() {
        return Y;
    }
}

class Joc {

    private String echipaA;
    private String echipaB;

    private int golsA;
    private int golsB;
    private int outs;
    private int corners;

    private int i;

    public Joc(String echipaA, String echipaB) {
        this.echipaA = echipaA;
        this.echipaB = echipaB;
        this.golsA = 0;
        this.golsB = 0;
        this.outs = 0;
        this.corners = 0;
    }

    public void simuleaza() {

        Minge minge = new Minge(25, 50);

        for (i = 0; i < 50; i++) {
            System.out.println("x" + minge.getX() + "|y:" + minge.getY());
            try {
                minge.suteaza();
            } catch (Out e) {
                outs++;
                System.out.println(e.getMessage());
                minge = new Minge(minge.getX(), minge.getY());
            } catch (Corner e) {
                corners++;
                System.out.println(e.getMessage());
                minge = new Minge(50, 25);

            } catch (Goal e) {
                if (minge.getX() == 0) {
                    golsA++;
                    System.out.println(echipaA + " a batut" + e.getMessage());

                } else {
                    golsB++;
                    System.out.println(echipaB + " a batut" + e.getMessage());
                }
                minge = new Minge(50, 25);
            }
        }
    }

    public String toString() {
        return echipaA + " VS " + echipaB + "\n"
                + "goals " + echipaA + ": " + golsA + "\n"
                + "goals " + echipaB + ": " + golsB + "\n"
                + "outs: " + outs + "\n"
                + "corners: " + corners;
    }

}

class ClientFotbal {
    public static void main(String[] argv) {
        Joc joc = new Joc("Barcelona", "Real");
        joc.simuleaza();
        System.out.println(joc.toString());
    }
}
