package nesnetabanliprogramlama;

import java.util.Scanner;

public class Dask extends Sigorta{
    private String policeno;
    private double ucret;
     
    Scanner proje1 =new Scanner(System.in);
    public Dask(String sirketimiz, int kurulus, String yer, String policeno ,String isim) {//Constructer
        super(sirketimiz, kurulus, yer, isim);
        this.policeno = policeno;
        System.out.println("Lütfen m^2 giriniz:");
        int d=proje1.nextInt();
		 int s=d*d*10;
		 this.ucret = s;
        
	 }

 public double getUcret() {
     return 1500.0;
 }
}