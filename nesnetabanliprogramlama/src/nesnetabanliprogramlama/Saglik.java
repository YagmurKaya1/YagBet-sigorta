package nesnetabanliprogramlama;

public class Saglik extends Sigorta {
	 private String bireysel;

	 // bilgi cekimi yaptıgımız metot
	 public Saglik(String sirketimiz, int kurulus, String yer, String bireysel,String isim) {//Contructer
	     super(sirketimiz, kurulus, yer,isim);
	     this.bireysel = bireysel;
	 }
	
	 public double getUcret() {
	        return 980.0;
	    }
	}