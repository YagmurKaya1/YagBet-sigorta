package nesnetabanliprogramlama;

public class Trafik extends Sigorta {
	 private String saseno;

	
	 public Trafik(String sirketimiz, int kurulus, String yer, String saseno,String isim) {//Contructer
	     super(sirketimiz, kurulus, yer,isim);
	     this.saseno = saseno;
	 }

	 public double getUcret() {
	        return 450.5;
	    }
	}
