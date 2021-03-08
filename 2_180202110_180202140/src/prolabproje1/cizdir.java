/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prolabproje1;

import java.awt.HeadlessException;
import javax.swing.JFrame;

/**
 *
 * @author aybuk
 */
public class cizdir extends JFrame {

    public cizdir() throws HeadlessException {
         haritaEkrani ekran =new haritaEkrani();    
        this.setVisible(true);
        this.setResizable(true);
        this.setSize(1193,570);
        this.add(ekran);
    }

        
    
    
}
