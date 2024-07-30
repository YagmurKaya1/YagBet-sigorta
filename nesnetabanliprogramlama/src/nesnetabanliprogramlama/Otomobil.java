package nesnetabanliprogramlama;

import java.util.Arrays;
import java.util.List;

public class Otomobil extends Sigorta {
    private String aracplaka;
    private String model;
    private double ucret;
    private static final List<String> gecerliModeller = Arrays.asList("sedan", "suv", "hatchback", "pickup");

    public Otomobil(String sirketimiz, int kurulus, String yer, String aracplaka, String model, String isim) {
        super(sirketimiz, kurulus, yer, isim);
        if (!gecerliModeller.contains(model.toLowerCase())) {
            throw new IllegalArgumentException("Geçersiz model: " + model);
        }
        this.aracplaka = aracplaka;
        this.model = model;
        this.ucret = hesaplaUcret(model);
    }

    // Araba modeline göre fiyat hesaplama
    private double hesaplaUcret(String model) {
        switch (model.toLowerCase()) {
            case "sedan":
                return 1000.0;
            case "suv":
                return 1500.0;
            case "hatchback":
                return 1200.0;
            case "pickup":
                return 1800.0;
            default:
                return 750.0; // Default ücret
        }
    }

    public double getUcret() {
        return ucret;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        if (!gecerliModeller.contains(model.toLowerCase())) {
            throw new IllegalArgumentException("Geçersiz model: " + model);
        }
        this.model = model;
        this.ucret = hesaplaUcret(model); // Model değiştiğinde ücreti yeniden hesapla
    }
}