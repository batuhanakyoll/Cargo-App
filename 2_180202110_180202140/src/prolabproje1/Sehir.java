/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prolabproje1;

import java.util.ArrayList;

/**
 *
 * @author aybuk
 */
public class Sehir implements Comparable<Sehir> {

    public String Sehirismi;
    int uzaklik = Integer.MAX_VALUE;
    boolean ugranildi_mi;
    Sehir oncekisehir;
    public ArrayList<Kenar> komsular81 = new ArrayList<>();

    public Sehir(String Sehirismi) {
        this.Sehirismi = Sehirismi;
    }

    @Override
    public int compareTo(Sehir otherVertex) {
        return Double.compare(this.uzaklik, otherVertex.uzaklik);
    }

}
