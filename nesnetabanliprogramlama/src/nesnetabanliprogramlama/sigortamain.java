package nesnetabanliprogramlama;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class sigortamain extends JFrame {

    private JTextField isimField;
    private JTextField soyisimField;
    private JPasswordField sifreField;

    public sigortamain() {
        setTitle("YağBet Sigorta Giriş");
        setSize(2100, 850);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        ImageIcon logoIcon = new ImageIcon("C:\\Users\\hp\\Downloads\\logo5.png"); // Logo yolunu buraya ekleyin
        JLabel logoLabel = new JLabel(logoIcon);

        isimField = new JTextField(15);
        soyisimField = new JTextField(15);
        sifreField = new JPasswordField(15);

        // Logo için konumlandırma
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(logoLabel, gbc);

        // İsim etiketi ve alanı
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.EAST;
        add(new JLabel("İsminizi giriniz:"), gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        add(isimField, gbc);

        // Soyisim etiketi ve alanı
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.EAST;
        add(new JLabel("Soyisminizi giriniz:"), gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        add(soyisimField, gbc);

        // Şifre etiketi ve alanı
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.EAST;
        add(new JLabel("Şifrenizi giriniz:"), gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        add(sifreField, gbc);


        // Giriş butonu
        JButton girisButton = new JButton("Giriş Yap");
        girisButton.addActionListener(new ActionListener() {
            String ilkSifre = ""; // İlk girişte saklanacak şifre

            public void actionPerformed(ActionEvent e) {
                String isim = isimField.getText();
                String soyisim = soyisimField.getText();
                String girilenSifre = new String(sifreField.getPassword());

                // İlk adımda saklanan şifreyi kontrol et
                if (ilkSifre.equals("")) {
                    // Eğer ilk şifre boşsa, kullanıcı ilk kez giriş yapıyor demektir
                    ilkSifre = girilenSifre;
                    JOptionPane.showMessageDialog(sigortamain.this, "Şifreniz kaydedildi. Lütfen tekrar girin.");
                    // Şifre alanlarını temizle
                    sifreField.setText("");
                    return; // Diğer adımları kontrol etmeye gerek yok, çünkü bir sonraki adıma geçilecek
                }

                // İkinci adımda, iki şifreyi kontrol ediyoruz
                if (girilenSifre.equals(ilkSifre)) {
                    // İki şifre eşleşiyorsa giriş işlemlerine devam et
                    JOptionPane.showMessageDialog(sigortamain.this, "Giriş Başarılı\nMERHABA " + isim + " " + soyisim);
                    new SigortaSecimEkrani().setVisible(true);
                    dispose();
                } else {
                    // İki şifre eşleşmiyorsa hata mesajı göster
                    JOptionPane.showMessageDialog(sigortamain.this, "Şifreler uyuşmuyor!", "Hata", JOptionPane.ERROR_MESSAGE);
                    // Şifre alanlarını temizle
                    sifreField.setText("");
                    ilkSifre = ""; // Tekrar giriş yapmak için ilk şifreyi sıfırla
                }
            }
        });

        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.CENTER;
        add(girisButton, gbc);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new sigortamain().setVisible(true);
            }
        });
    }
}

class SigortaSecimEkrani extends JFrame {
    private JTextArea outputArea;
    private JComboBox<String> sigortaSecimBox;
    private double toplamUcret = 0.0;
    private Insurance Insurance;
    private JPanel buttonPanel;
    private JPanel sigortaPanel;

    public SigortaSecimEkrani() {
    	setTitle("YağBet Sigorta");
        setSize(2150, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        
        // Başlık için JLabel oluştur
        JLabel baslikLabel = new JLabel("YAĞBET SİGORTA", SwingConstants.CENTER);
        baslikLabel.setFont(new Font("Arial", Font.BOLD, 30)); // Font ve boyut ayarları
        baslikLabel.setForeground(Color.BLUE); // Mavi renk ayarı
        add(baslikLabel, BorderLayout.NORTH);

        sigortaPanel = new JPanel(new BorderLayout());
        sigortaPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        sigortaPanel.setBackground(new Color(173, 216, 230)); // Bebek mavisi rengi ayarla

        outputArea = new JTextArea();
        outputArea.setEditable(false);
        outputArea.setFont(new Font("Arial", Font.BOLD, 14));
        outputArea.setBackground(new Color(173, 216, 230)); // Bebek mavisi arka plan rengi
        outputArea.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.BLUE, 2),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)));

        JScrollPane scrollPane = new JScrollPane(outputArea);

        String[] sigortaSecenekleri = {"Dask Sigortası", "Pati Sigortası", "Sağlık Sigortası", "Otomobil Sigortası", "Arsa Sigortası", "Seyahat Sigortası", "Trafik Sigortası", "Bitir ve Öde"};
        sigortaSecimBox = new JComboBox<>(sigortaSecenekleri);
        sigortaSecimBox.addActionListener(new SigortaSecimAction());

        sigortaPanel.add(sigortaSecimBox, BorderLayout.NORTH);
        sigortaPanel.add(scrollPane, BorderLayout.CENTER);

        add(sigortaPanel, BorderLayout.CENTER);

        buttonPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        buttonPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        add(buttonPanel, BorderLayout.SOUTH);
        

        updateButtonPanel();
    }

    private class SigortaSecimAction implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            int secim1 = sigortaSecimBox.getSelectedIndex();
            Insurance = null;
            switch (secim1) {
                case 0:
                	Insurance = new DaskSigortasi("YağBet DASK", 2018, "Cihangir Mahallesi no:87468");
                    break;
                case 1:
                    String haytur = JOptionPane.showInputDialog("Lütfen evcil hayvanınızın türünü giriniz:");
                    String hayadi = JOptionPane.showInputDialog("Lütfen evcil hayvanınızın adını giriniz:");
                    Insurance = new PatiSigortasi("YağBet Pati Sigortası", 2020, "Ev", haytur, hayadi);
                    break;
                case 2:
                    String sagisim = JOptionPane.showInputDialog("Lütfen kişi ismini giriniz:");
                    Insurance = new SaglikSigortasi("YağBet Sağlık Sigortası", 1990, "Rumeli Hastanesi Sefaköy", "bireysel", sagisim);
                    break;
                case 3:
                    String otomar = JOptionPane.showInputDialog("Lütfen otomobilin markasını giriniz:");
                    String otoplak = JOptionPane.showInputDialog("Lütfen otomobilin plakasını giriniz:");
                    String otomodel = JOptionPane.showInputDialog("Lütfen otomobilin modelini giriniz (sedan, suv, hatchback, pickup):");
                    try {
                    	Insurance = new OtomobilSigortasi("YağBet Otomobil Sigortası", 2022, "Izmir", otoplak, otomodel, otomar);
                    } catch (IllegalArgumentException ex) {
                        JOptionPane.showMessageDialog(null, ex.getMessage(), "Hata", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    break;
                case 4:
                    String konum = JOptionPane.showInputDialog("Lütfen arsanın konumunu giriniz:");
                    Insurance = new ArsaSigortasi("YağBet Arsa Sigortası", 2016, konum, "A9517563", "arsa");
                    break;
                case 5:
                    String geziciisim = JOptionPane.showInputDialog("Lütfen kişi ismini giriniz:");
                    Insurance = new SeyahatSigortasi("YağBet Seyahat Sigortası", 2024, "YağBet Sigorta", "230118221000", geziciisim);
                    break;
                case 6:
                    String aracIsim = JOptionPane.showInputDialog("Lütfen aracın ismini giriniz:");
                    Insurance = new TrafikSigortasi("Trafik Sigortası", 2023, "YağBet sigorta sirketi", "35 EV 2869", aracIsim);
                    break;
                case 7:
                    outputArea.append("\nÖdeme ekranı");
                    odemeIslemleri();
                    return;
                default:
                    JOptionPane.showMessageDialog(null, "Hatalı giriş yaptınız! 1-7 arası seçim yapın çıkmak için 8'i seçiniz!", "Hata", JOptionPane.ERROR_MESSAGE);
                    return;
            }

            if (Insurance != null) {
                outputArea.append("\n\nSeçilen Sigorta Bilgisi:\n");
                outputArea.append(Insurance.toString());
                toplamUcret += Insurance.getUcret();
            }
            updateButtonPanel();
        }
    }

    private void odemeIslemleri() {
        outputArea.append("\nToplam Ücret: " + toplamUcret);
        String[] odemeSecenekleri = {"Peşin ödeme (card or cash)", "Taksitli ödeme (en fazla 12 ay taksit yapılabilir)"};
        int odemeSecimi = JOptionPane.showOptionDialog(null, "Ödeme seçenekleri:", "Ödeme", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, odemeSecenekleri, odemeSecenekleri[0]);

        if (odemeSecimi == 0) {
            outputArea.append("\nPeşin ödeme seçeneği seçildi.");
            outputArea.append("\nÖdemeniz gereken toplam tutar: " + toplamUcret + " TL'dir.");
        } else if (odemeSecimi == 1) {
            String[] taksitSecenekleri = {"3", "6", "9", "12"};
            String taksitSayisiStr = (String) JOptionPane.showInputDialog(null, "Kaç taksit yapılacağını seçin:", "Taksit Seçenekleri", JOptionPane.QUESTION_MESSAGE, null, taksitSecenekleri, taksitSecenekleri[0]);
            int taksitSayisi = Integer.parseInt(taksitSayisiStr);
            double taksitTutari = toplamUcret / taksitSayisi;
            outputArea.append("\nTaksitli ödeme seçeneği seçildi.");
            outputArea.append("\nSeçilen taksit sayısı: " + taksitSayisi);
            outputArea.append("\nAylık ödemeniz gereken tutar: " + taksitTutari + " TL'dir.");
        }
        outputArea.append("\nÖdeme işlemi tamamlandı. Sigorta süreciniz başarıyla tamamlanmıştır.");
        JOptionPane.showMessageDialog(this, "Ödeme başarıyla tamamlandı.Tekrar işlem için Yeniden giriş yapın.");
        this.dispose();
        new sigortamain().setVisible(true);
    }

    private void updateButtonPanel() {
        // Buton panelini temizle
        buttonPanel.removeAll();

        // Butonları ekle
        String[] buttonLabels = {"Dask Sigortası", "Pati Sigortası", "Sağlık Sigortası", "Otomobil Sigortası", "Arsa Sigortası", "Seyahat Sigortası", "Trafik Sigortası", "Bitir ve Öde"};
        for (String label : buttonLabels) {
            JButton button = new JButton(label);
            button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    // Butona tıklandığında ilgili sigorta seçeneğini seç
                    int index = getIndexForButtonLabel(label);
                    sigortaSecimBox.setSelectedIndex(index);

                }
            });
            buttonPanel.add(button);
        }

        // Buton panelini güncelle
        buttonPanel.revalidate();
        buttonPanel.repaint();

        // Üstteki sigorta seçeneklerini göster
        sigortaPanel.setVisible(true);
    }
    private int getIndexForButtonLabel(String label) {
        switch (label) {
            case "Dask Sigortası":
                return 0;
            case "Pati Sigortası":
                return 1;
            case "Sağlık Sigortası":
                return 2;
            case "Otomobil Sigortası":
                return 3;
            case "Arsa Sigortası":
                return 4;
            case "Seyahat Sigortası":
                return 5;
            case "Trafik Sigortası":
                return 6;
            case "Bitir ve Öde":
                return 7;
            default:
                return -1;
        }
    }

        public static void main(String[] args) {
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    new sigortamain().setVisible(true);
                }
            });
        }
    }
    abstract class Insurance  {
        protected String sigortaTuru;
        protected int sigortaYili;
        protected String sigortaAdresi;
        protected double ucret;

        public Insurance (String sigortaTuru, int sigortaYili, String sigortaAdresi, double ucret) {
            this.sigortaTuru = sigortaTuru;
            this.sigortaYili = sigortaYili;
            this.sigortaAdresi = sigortaAdresi;
            this.ucret = ucret;
        }

        public double getUcret() {
            return ucret;
        }

        @Override
        public String toString() {
            return "Sigorta Türü: " + sigortaTuru + ", Yılı: " + sigortaYili + ", Adresi: " + sigortaAdresi + ", Ücreti: " + ucret;
        }
    }

   

    class DaskSigortasi extends Insurance  {
        private String aracTuru;
        private double ucret;

        public DaskSigortasi(String sigortaTuru, int sigortaYili, String sigortaAdresi) {
            super(sigortaTuru, sigortaYili, sigortaAdresi, 1000.0);

            // Kullanıcıdan araç türünü girmesini isteyin
            JTextField aracTuruField = new JTextField(10);
            JPanel panel = new JPanel();
            panel.add(new JLabel("Taşınmaz Türü:"));
            panel.add(aracTuruField);
            int result = JOptionPane.showConfirmDialog(null, panel, "Tasinmaz Türü Girin", JOptionPane.OK_CANCEL_OPTION);
            if (result == JOptionPane.OK_OPTION) {
                aracTuru = aracTuruField.getText();
            } else {
                throw new RuntimeException("Kullanıcı tasinmaz türünü girmeden işlem iptal edildi.");
            }

            // Kullanıcıdan m^2 bilgisini girmesini isteyin
            JTextField m2Field = new JTextField(10);
            JPanel panel2 = new JPanel();
            panel2.add(new JLabel("m^2:"));
            panel2.add(m2Field);
            int result2 = JOptionPane.showConfirmDialog(null, panel2, "m^2 Girin", JOptionPane.OK_CANCEL_OPTION);
            if (result2 == JOptionPane.OK_OPTION) {
                int d = Integer.parseInt(m2Field.getText());
                int s = d * d * 10;
                this.ucret = s;
            } else {
                throw new RuntimeException("Kullanıcı m^2 bilgisini girmeden işlem iptal edildi.");
            }
        }

        @Override
        public String toString() {
            return super.toString() + ", Tasinmaz Türü: " + aracTuru;
        }
    }

    class PatiSigortasi extends Insurance  {
        private String hayvanTuru;
        private String hayvanAdi;

        public PatiSigortasi(String sigortaTuru, int sigortaYili, String sigortaAdresi, String hayvanTuru, String hayvanAdi) {
            super(sigortaTuru, sigortaYili, sigortaAdresi, 800.0);
            this.hayvanTuru = hayvanTuru;
            this.hayvanAdi = hayvanAdi;
        }

        @Override
        public String toString() {
            return super.toString() + ", Hayvan Türü: " + hayvanTuru + ", Hayvan Adı: " + hayvanAdi;
        }
    }

    class SaglikSigortasi extends Insurance  {
        private String saglikTuru;
        private String kisiIsmi;

        public SaglikSigortasi(String sigortaTuru, int sigortaYili, String sigortaAdresi, String saglikTuru, String kisiIsmi) {
            super(sigortaTuru, sigortaYili, sigortaAdresi, 1200.0);
            this.saglikTuru = saglikTuru;
            this.kisiIsmi = kisiIsmi;
        }

        @Override
        public String toString() {
            return super.toString() + ", Sağlık Türü: " + saglikTuru + ", Kişi İsmi: " + kisiIsmi;
        }
    }

    class OtomobilSigortasi extends Insurance  {
        private String aracPlaka;
        private String aracModel;
        private String aracMarka;

        public OtomobilSigortasi(String sigortaTuru, int sigortaYili, String sigortaAdresi, String aracPlaka, String aracModel, String aracMarka) {
            super(sigortaTuru, sigortaYili, sigortaAdresi, 1500.0);
            this.aracPlaka = aracPlaka;
            this.aracModel = aracModel;
            this.aracMarka = aracMarka;
            validateAracModel(aracModel); // Metodu çağırarak modelin geçerliliğini kontrol ediyoruz.
        }

        private void validateAracModel(String aracModel) {
            String[] validModels = {"sedan", "suv", "hatchback", "pickup"};
            boolean isValid = false;
            for (String model : validModels) {
                if (model.equalsIgnoreCase(aracModel)) {
                    isValid = true;
                    break;
                }
            }
            if (!isValid) {
                throw new IllegalArgumentException("Geçersiz araç modeli. Sadece sedan, suv, hatchback, pickup modelleri kabul edilir.");
            }
        }
        @Override
        public String toString() {
            return super.toString() + ", Araç Plaka: " + aracPlaka + ", Araç Model: " + aracModel + ", Araç Marka: " + aracMarka;
        }
    }
    class ArsaSigortasi extends Insurance  {
        private String arsaKonumu;
        private String arsaNo;

        public ArsaSigortasi(String sigortaTuru, int sigortaYili, String arsaKonumu, String arsaNo, String arsaTuru) {
            super(sigortaTuru, sigortaYili, arsaKonumu, 1800.0);
            this.arsaKonumu = arsaKonumu;
            this.arsaNo = arsaNo;
        }

        @Override
        public String toString() {
            return super.toString() + ", Arsa Konumu: " + arsaKonumu + ", Arsa No: " + arsaNo;
        }
    }

    class SeyahatSigortasi extends Insurance  {
        private String geziciIsmi;

        public SeyahatSigortasi(String sigortaTuru, int sigortaYili, String sigortaAdresi, String sigortaNo, String geziciIsmi) {
            super(sigortaTuru, sigortaYili, sigortaAdresi, 900.0);
            this.geziciIsmi = geziciIsmi;
        }

        @Override
        public String toString() {
            return super.toString() + ", Gezici İsmi: " + geziciIsmi;
        }
    }

    class TrafikSigortasi extends Insurance  {
        private String aracIsmi;
        private String aracPlaka;

        public TrafikSigortasi(String sigortaTuru, int sigortaYili, String sigortaAdresi, String aracPlaka, String aracIsmi) {
            super(sigortaTuru, sigortaYili, sigortaAdresi, 1100.0);
            this.aracPlaka = aracPlaka;
            this.aracIsmi = aracIsmi;
        }

        @Override
        public String toString() {
            return super.toString() + ", Araç Plaka: " + aracPlaka + ", Araç İsmi: " + aracIsmi;
        }
    }