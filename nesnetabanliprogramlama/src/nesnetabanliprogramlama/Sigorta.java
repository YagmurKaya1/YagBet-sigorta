package nesnetabanliprogramlama;

public class Sigorta {   

//güvenlik ve degistirilmeme için private kullanildi
private String sirketimiz;
private int kurulus;
private String yer;
private String isim;
//ana metodlarimiz da burada bilgi cektigimiz yer mainde
//Constructor oluşturuldu.
public Sigorta(String sirketimiz, int kurulus, String yer,String isim) {
    this.sirketimiz = sirketimiz;
    this.kurulus = kurulus;
    this.yer = yer;
    this.isim=isim;
}
//getter setter metodları da bunlar
public String getsirketimiz() {
    return sirketimiz;
}

public void setsirketimiz(String sirketimiz) {
    this.sirketimiz = sirketimiz;
}

public int getkurulus() {
    return kurulus;
}

public void setkurulus(int kurulus) {
    this.kurulus = kurulus;
}

public String getyer() {
    return yer;
}

public void setyer(String yer) {
    this.yer = yer;
}
public String getisim() {
	return isim;
}
public void setisim(String isim) {
	this.isim= isim;
}
//bu kisim metod ve ekrana yazdirma
public void ekranayazdir() {
    System.out.println("Şirketin İsmi: " + sirketimiz);
    System.out.println("Kurulus Yili: " + kurulus);
    System.out.println("Sirketin yeri: " + yer);
    System.out.println("İsim: " + isim);
   
}
public double getUcret() {
    return 0.0;
}
}