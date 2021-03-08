/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prolabproje1;

public class Kenar {

    public String Komsuismi;
    public Sehir baslangicsehir;
    public Sehir gidileceksehir;
    public int uzaklik;

    public Kenar(Sehir baslangicsehir, String Komsuismi, int uzaklik) {
        this.baslangicsehir = baslangicsehir;
        this.uzaklik = uzaklik;
        this.Komsuismi = Komsuismi;
    }

}
