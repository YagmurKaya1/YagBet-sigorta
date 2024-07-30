package nesnetabanliprogramlama;

public class Pati extends Sigorta {
	 private String turu;

	 // superle verileri tekrar girmeden cektik
	 public Pati(String sirketimiz, int kurulus, String yer, String turu , String isim) {//Contructer
	     super(sirketimiz, kurulus, yer,isim);
	     this.turu = turu;
	 }
	
	  public double getUcret() {
	        return 550.0;
	    }
	}
