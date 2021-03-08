/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prolabproje1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author aybuk
 */
class DijkstraShortestPath {

    public void computeShortestPaths(Sehir sourceVertex) {

        for (int i = 0; i < Prolabproje1.sehirler81.size(); i++) {
            Prolabproje1.sehirler81.get(i).ugranildi_mi = false;
            Prolabproje1.sehirler81.get(i).uzaklik = Integer.MAX_VALUE;
            Prolabproje1.sehirler81.get(i).oncekisehir = null;
        }
        sourceVertex.uzaklik = 0;
        PriorityQueue<Sehir> priorityQueue = new PriorityQueue<>();
        priorityQueue.add(sourceVertex);
        sourceVertex.ugranildi_mi = true;

        while (!priorityQueue.isEmpty()) {
            // Getting the minimum distance vertex from priority queue
            Sehir actualVertex = priorityQueue.poll();

            for (Kenar edge : actualVertex.komsular81) {

                Sehir v = edge.gidileceksehir;
                if (!v.ugranildi_mi) {
                    int newDistance = actualVertex.uzaklik + edge.uzaklik;
                    if (newDistance < v.uzaklik) {
                        priorityQueue.remove(v);
                        v.uzaklik = newDistance;
                        v.oncekisehir = actualVertex;
                        priorityQueue.add(v);
                    }
                }
            }
            actualVertex.ugranildi_mi = true;
        }

    }

    public List<Sehir> getShortestPathTo(Sehir targetVertex) {
        List<Sehir> path = new ArrayList<>();

        for (Sehir vertex = targetVertex; vertex != null; vertex = vertex.oncekisehir) {
            path.add(vertex);
        }

        Collections.reverse(path);
        return path;
    }

}

public class Prolabproje1 {

    public static int faktoriyel(int sayi) {//bu fonk dfdeğişecek
        int fakt = 1;
        for (int i = 1; i <= sayi; i++) {
            fakt *= i;
        }
        return fakt;
    }

    static int gidileceksehirsayi;

    public static ArrayList<String> rotaa = new ArrayList<>();
    static ArrayList<Sehir> sehrimintadi = new ArrayList<>();
    public static ArrayList<Sehir> sehirler81 = new ArrayList<>();

    static ArrayList<kordinatsehir> ksehirler = new ArrayList<>();

    public static void main(String[] args) throws FileNotFoundException, InterruptedException {

        File dosya2 = new File("prolab.txt");// File sınıfından yeni bir dosya oluşturuluyor. 
        Scanner dosya = null;

        try {
            if (!dosya2.exists()) {  // Dosya var mı yok mu kontrol ediliyor.
                System.out.println("dosya yok");
                dosya2.createNewFile(); // Eğer dosya yok ise oluşturuluyor.
            } else {
                dosya = new Scanner(dosya2); // Scanner sınıfı ile dosyamıza eriştik ve içerisindeki verileri okuma işlemini sc referansı ile yapacağız. Burada sc özel bir mana teşkil etmiyor isterseniz başka bir isimlendirme de yapabilirsiniz
                int sayi = 0;
                while (dosya.hasNextLine()) {   // hasNext() metodu ise dosyamızın son satırına kadar okuma işlemini gerçekleştirir.
                    String cumle = dosya.nextLine();  // Scanner sınıfının nesnesi ile her satırda ne kadar uzunlukta cümle varsa bunun tespiti yapılıyor.
                    String[] s = cumle.split(",");// split() metodu ile virgül göre cümleyi bölme işlemi yapılıyor. 
                    int i;
                    sehirler81.add(new Sehir(s[0]));

                    int cift = 1;
                    while (cift < s.length) {
//                        System.out.println(s[i]);
//                     String isim=s[i].trim();
//                     System.out.println(isim);
//                     String [] a=isim.split(",");
                        String komsuisimleri = s[cift];
                        int uzaklik = Integer.parseInt(s[cift + 1]);

                        sehirler81.get(sayi).komsular81.add(new Kenar(sehirler81.get(sayi), komsuisimleri, uzaklik));
                        cift += 2;
                    }

                    sayi++;

                }
                dosya.close();
                // Dosyamızı okuduktan sonra kapatma işlemini yapıyoruz!!
            }

        } catch (IOException e) {

        }

        for (int i = 0; i < sehirler81.size(); i++) {
            for (int j = 0; j < sehirler81.get(i).komsular81.size(); j++) {
                for (int k = 0; k < sehirler81.size(); k++) {
                    if (sehirler81.get(k).Sehirismi.equals(sehirler81.get(i).komsular81.get(j).Komsuismi) == true) {

                        sehirler81.get(i).komsular81.get(j).gidileceksehir = sehirler81.get(k);
                        break;
                    }

                }

            }

        }

        DijkstraShortestPath shortestPath = new DijkstraShortestPath();
        shortestPath.computeShortestPaths(sehirler81.get(40));

        /*        for (Sehir i : shortestPath.getShortestPathTo(sehirler81.get(53))) {//oraya gidene kadarki pathi yazdırır
            System.out.println(i.Sehirismi);
        }
        System.out.println(sehirler81.get(25).uzaklik);*/
        sehrimintadi.add(sehirler81.get(40));
        ArrayList<String> gdsRota = new ArrayList<>(); // gdsRota=> gidilecek sehirlerin rotası. Bu yapı gidilecekSehirlerin faktoriyeli kadar 4lü yapı tutar.
        ArrayList<Integer> farkliRandom = new ArrayList<>(); // farkliRandom, oluşturduğumuz 4 lü sayıyı (gidilecekSehirlerin indexi olan sayılar) tutar.  
        Random random = new Random();
        sorgupenceresi frame = new sorgupenceresi();
        frame.setVisible(true);
        TimeUnit.SECONDS.sleep(5);  //kulalnıcıdan kaç şehire gidileceğini seçmesi için 5 sn bekletiyoruz.      
        int sehirSayisi = gidileceksehirsayi;
        int sehirsayisiFaktoriyel = faktoriyel(gidileceksehirsayi);
        while (gdsRota.size() != sehirsayisiFaktoriyel) {
            while (farkliRandom.size() != sehirSayisi) {
                int rnd = random.nextInt(sehirSayisi) + 1; // başlangıç noktasının  (0. indexi yani Kocaeliyi) indexini almaması için yaptık. 
                // System.out.println(rnd);
                if (farkliRandom.contains(rnd) == false) {
                    farkliRandom.add(rnd);
                }
            } //1 2 3 5 4=>farklirandom
            String tmpRota = "";
            tmpRota += 0;
            for (int i = 0; i < farkliRandom.size(); i++) {
                tmpRota += farkliRandom.get(i);
            }
            tmpRota += 0;
            // tmprota="12354"
            if (gdsRota.contains(tmpRota) == false) {
                gdsRota.add(tmpRota);
                //   System.out.println(tmpRota);
            }

            farkliRandom.clear();
        }
        frame.setVisible(false);
        sehirpenceresi frame2 = new sehirpenceresi();
        frame2.setVisible(true);
        TimeUnit.SECONDS.sleep(10);  //kullanıcıdan kaç şehire gidileceğini seçmesi için 5 sn bekletiyoruz.      
        sehrimintadi.add(sehirler81.get(40));
        //System.out.println("\n"+sehrimintadi.get(0).Sehirismi+sehrimintadi.get(1).Sehirismi+sehrimintadi.get(2).Sehirismi+sehrimintadi.get(3).Sehirismi+sehrimintadi.get(4).Sehirismi+"\n");

        ArrayList<Integer> uzaklikDizisi = new ArrayList<>();
        for (int i = 0; i < gdsRota.size(); i++) {
            Integer toplam = 0;
            for (int j = 0; j < sehirSayisi + 1; j++) {
                shortestPath.computeShortestPaths(sehrimintadi.get(gdsRota.get(i).charAt(j) - 48));//ascii tabloda 0 => 48 e denk geliyor o yüzden çıakrdıkk.
                toplam += sehrimintadi.get(gdsRota.get(i).charAt(j + 1) - 48).uzaklik;
            }
            uzaklikDizisi.add(toplam);
        }
        //        System.out.println(uzaklikDizisi);
        ArrayList<Integer> tmp = new ArrayList<>();
        for (int i = 0; i < uzaklikDizisi.size(); i++) {
            tmp.add(i);
        }

        //        System.out.println(tmp);
        ArrayList<Integer> uzaklikdizisitmp = (ArrayList<Integer>) uzaklikDizisi.clone();
        for (int i = 0; i < tmp.size() - 1; i++) {
            for (int j = i + 1; j < tmp.size(); j++) {
                if (uzaklikdizisitmp.get(i) >= uzaklikdizisitmp.get(j)) {
                    int tmpuzaklik = uzaklikdizisitmp.get(i);
                    int tmpsayi = tmp.get(i);
                    uzaklikdizisitmp.set(i, uzaklikdizisitmp.get(j));
                    uzaklikdizisitmp.set(j, tmpuzaklik);
                    tmp.set(i, tmp.get(j));
                    tmp.set(j, tmpsayi);
                }
            }
        }
        // System.out.println(uzaklikdizisitmp);

        System.out.println("EN KISA ROTA");
        for (int i = 0; i < gidileceksehirsayi + 1; i++) {
            shortestPath.computeShortestPaths(sehrimintadi.get(gdsRota.get(tmp.get(0)).charAt(i) - 48));
            for (Sehir j : shortestPath.getShortestPathTo(sehrimintadi.get(gdsRota.get(tmp.get(0)).charAt(i + 1) - 48))) {
                System.out.print(j.Sehirismi + ",");
                rotaa.add(j.Sehirismi);
            }

        }
        new cizdir();
        Thread.sleep(2000);
        rotaa.clear();
        System.out.println("\n");
        System.out.println("EN KISA 2.ROTA:");
        for (int i = 0; i < gidileceksehirsayi + 1; i++) {
            shortestPath.computeShortestPaths(sehrimintadi.get(gdsRota.get(tmp.get(1)).charAt(i) - 48));
            for (Sehir j : shortestPath.getShortestPathTo(sehrimintadi.get(gdsRota.get(tmp.get(1)).charAt(i + 1) - 48))) {
                System.out.print(j.Sehirismi + ",");
                rotaa.add(j.Sehirismi);
            }

        }
        new cizdir();
        Thread.sleep(2000);
        rotaa.clear();
        System.out.println("\n");
        System.out.println("EN KISA 3.ROTA:");
        for (int i = 0; i < gidileceksehirsayi + 1; i++) {
            shortestPath.computeShortestPaths(sehrimintadi.get(gdsRota.get(tmp.get(2)).charAt(i) - 48));
            for (Sehir j : shortestPath.getShortestPathTo(sehrimintadi.get(gdsRota.get(tmp.get(2)).charAt(i + 1) - 48))) {
                System.out.print(j.Sehirismi + ",");
                rotaa.add(j.Sehirismi);
            }

        }
        new cizdir();
        Thread.sleep(2000);
        rotaa.clear();
        System.out.println("\n");
        System.out.println("EN KISA 4.ROTA:");
        for (int i = 0; i < gidileceksehirsayi + 1; i++) {
            shortestPath.computeShortestPaths(sehrimintadi.get(gdsRota.get(tmp.get(3)).charAt(i) - 48));
            for (Sehir j : shortestPath.getShortestPathTo(sehrimintadi.get(gdsRota.get(tmp.get(3)).charAt(i + 1) - 48))) {
                System.out.print(j.Sehirismi + ",");
                rotaa.add(j.Sehirismi);
            }

        }
        new cizdir();
        Thread.sleep(2000);
        rotaa.clear();
        System.out.println("\n");
        System.out.println("EN KISA 5.ROTA:");
        for (int i = 0; i < gidileceksehirsayi + 1; i++) {
            shortestPath.computeShortestPaths(sehrimintadi.get(gdsRota.get(tmp.get(4)).charAt(i) - 48));
            for (Sehir j : shortestPath.getShortestPathTo(sehrimintadi.get(gdsRota.get(tmp.get(4)).charAt(i + 1) - 48))) {
                System.out.print(j.Sehirismi + ",");
                rotaa.add(j.Sehirismi);
            }

        } 
        System.out.println("\n");
        System.out.println(uzaklikdizisitmp);

        new cizdir();
        Thread.sleep(2000);
        rotaa.clear();
        frame2.setVisible(false);
        //    cizdirme frame3 = new cizdirme();
        //frame3.setVisible(true);
        //System.out.println("\n");

        // System.out.println(uzaklikdizisitmp);
        // System.exit(0);
    }
}
