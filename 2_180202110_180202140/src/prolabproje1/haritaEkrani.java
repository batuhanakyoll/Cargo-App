/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prolabproje1;

import java.awt.Color;
import java.awt.Graphics;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author aybuk
 */
public class haritaEkrani extends JPanel {
    ArrayList<String> rotaa2 = prolabproje1.Prolabproje1.rotaa;
    static ArrayList<kordinatsehir> ksehirler = new ArrayList<>();
    File sehirkordinatları = new File("sehirkordinatlari.txt");// File sınıfından yeni bir dosya oluşturuluyor. 
    Scanner dosya1 = null;
     ImageIcon resim = new ImageIcon("harita.png"); 

    @Override
    public void paint(Graphics g) {        
        super.paint(g);
        ImageIcon i = new ImageIcon("harita.png");
        g.drawImage(i.getImage(), 0, 0, 1015, 479, null);        
        int x1=0,y1=0,x2=0,y2=0;
        for (int ia = 0; ia < rotaa2.size()-1; ia++) {
            g.setColor(Color.red);
            for(int j=0; j<ksehirler.size();j++){
                if(ksehirler.get(j).ksehirismi.equals(rotaa2.get(ia))){
                    x1=ksehirler.get(j).x;
                    y1=ksehirler.get(j).y;
                    break;
                }
            }
            for(int j=0; j<ksehirler.size();j++){
                if(ksehirler.get(j).ksehirismi.equals(rotaa2.get(ia+1))){
                    x2=ksehirler.get(j).x;
                    y2=ksehirler.get(j).y;
                    break;
                }
            }         
            g.drawLine(x1, y1, x2, y2);

        }
    }

    public haritaEkrani() {       
        
        dosyaoku();
    }
    public void dosyaoku() {

        File sehirkordinatlar = new File("sehirkordinatlari.txt");// File sınıfından yeni bir dosya oluşturuluyor. 
        Scanner dosya3 = null;
        try {
            if (!sehirkordinatlar.exists()) {  // Dosya var mı yok mu kontrol ediliyor.
                System.out.println("dosya yok");
                sehirkordinatlar.createNewFile(); // Eğer dosya yok ise oluşturuluyor.
            } else {
                dosya3 = new Scanner(sehirkordinatlar); // Scanner sınıfı ile dosyamıza eriştik ve içerisindeki verileri okuma işlemini sc referansı ile yapacağız. Burada sc özel bir mana teşkil etmiyor isterseniz başka bir isimlendirme de yapabilirsiniz
                int sayi = 0;
                while (dosya3.hasNextLine()) {   // hasNext() metodu ise dosyamızın son satırına kadar okuma işlemini gerçekleştirir.
                    String cumle = dosya3.nextLine();  // Scanner sınıfının nesnesi ile her satırda ne kadar uzunlukta cümle varsa bunun tespiti yapılıyor.
                    String[] a = cumle.split(",");// split() metodu ile virgül göre cümleyi bölme işlemi yapılıyor. 
                    int i;
                    ksehirler.add(new kordinatsehir(a[0]));

                    int cift = 1;
                    while (cift < a.length) {

                        int xler = Integer.parseInt(a[cift]);
                        int yler = Integer.parseInt(a[cift + 1]);

                        ksehirler.get(sayi).x=xler;
                        ksehirler.get(sayi).y=yler;
                        cift += 2;
                    }

                    sayi++;

                }
                dosya3.close();  // Dosyamızı okuduktan sonra kapatma işlemini yapıyoruz!!
            }

        } catch (IOException e) {
        }

    }
     
}
