package nesnetabanliprogramlama;
import java.util.Scanner;
public class Arsa extends Sigorta {
	 private String arsano;
	 private double ucret;
	 
	 Scanner proje =new Scanner(System.in);
	 
	 public Arsa(String sirketimiz, int kurulus, String yer, String arsano,String isim) {//Constructer
	     super(sirketimiz, kurulus, yer,isim);
	     this.arsano = arsano;
	     System.out.println("m degerini giriniz");
	     int m=proje.nextInt();
		 double v=m*m*10.5;
		 this.ucret = v;
		 System.out.println("m^2 basina düsen ücret: "+v);
		
		
		 
	 }
	
	 
	 public double getUcret() {
	        return ucret;
	    }
	 
	}
