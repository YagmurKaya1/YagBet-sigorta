package nesnetabanliprogramlama;

public class Seyahat extends Sigorta {
	 private String tcno;

	 
	 public Seyahat(String sirketimiz, int kurulus, String yer, String tcno,String isim) {//Contructer
	     super(sirketimiz, kurulus, yer,isim);
	     this.tcno = tcno;
	 }
	
	 
	 public double getUcret() {
	        return 275.2;
	    }
	}
