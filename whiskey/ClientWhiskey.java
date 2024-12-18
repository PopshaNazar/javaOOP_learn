import java.util.*;

abstract class Whiskey{
    abstract public double getNrCalorii(double mililitri);
    abstract public double getConcetratieAlcool();
    abstract public String toString();
}

class ClassicWhiskey extends Whiskey{
    private String nume;
    private double alcool;

    public ClassicWhiskey(String nume, double alcool){
	this.nume = nume;
	this.alcool = alcool;
    }

    public double getNrCalorii(double mililitri){
	return alcool * mililitri * 5;
    }
    public double getConcetratieAlcool(){
	return alcool;
    }
    
    public String toString(){
	return this.nume+", Concetratie alcool: "+ this.getConcetratieAlcool() +", Concetratie alcool pentru 100ml " + this.getNrCalorii(100);
    }
}
class JackAndHoney extends Whiskey{
    private String nume;
    private double alcool;
    private int indulcitor;

    public JackAndHoney(String nume, double alcool, int indulcitor){
	this.nume = nume;
	this.alcool = alcool;
	this.indulcitor = indulcitor;
    }

    public double getNrCalorii(double mililitri){
	return alcool * mililitri * 5 + indulcitor * mililitri * 15;
    }
    public double getConcetratieAlcool(){
	return alcool;
    }
    public int getCantitateIndulcitor(){
	return indulcitor;
    }
    
    public String toString(){
	return this.nume+", Concetratie alcool: "+ this.getConcetratieAlcool() +", Concetratie alcool pentru 100ml " + this.getNrCalorii(100) + " Cantitate indulcitor: "+ this.getCantitateIndulcitor();
    }
}

class BlendedWhiskey extends Whiskey{
    private String nume;
    private List<Whiskey> lista;

    public BlendedWhiskey(String nume){
	this.nume = nume;
	lista = new ArrayList<>();
    }
    public void AddWhiskey(Whiskey whiskey){
	lista.add(whiskey);
    }
    public double getNrCalorii(double mililitri){
	int i = 0;
	double calorii =0;
	for(Whiskey whiskey: lista){
	    i++;
	    calorii+=whiskey.getNrCalorii(mililitri);
	}
	return calorii/i;
    }
    public double getConcetratieAlcool(){
	int i = 0;
	double alcool =0;
	for(Whiskey whiskey: lista){
	    i++;
	    alcool+=whiskey.getConcetratieAlcool();
	}
	return alcool/i;
    }

     public String toString(){
	 StringBuilder builder = new StringBuilder();
	 builder.append(this.nume+", Concetratie alcool: "+ this.getConcetratieAlcool() +", Concetratie alcool pentru 100ml " + this.getNrCalorii(100));
	 for(Whiskey whiskey: lista){
	     builder.append("\n"+whiskey.toString());
	 }
	 return "\n"+builder;
    }
    
}


class ClientWhiskey{
    public static void main(String[] args){
	ClassicWhiskey cw1 = new ClassicWhiskey("AOLEU", 30);
	JackAndHoney jw1 = new JackAndHoney("DULCEAAATA", 25, 5);

	BlendedWhiskey bw1 = new BlendedWhiskey("shmurdiak");
	bw1.AddWhiskey(cw1);
	bw1.AddWhiskey(jw1);
	System.out.println(bw1.toString());
    }
}
