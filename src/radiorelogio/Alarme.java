/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package radiorelogio;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import static radiorelogio.Tela.ps;

/**
 *
 * @author Camila
 */
public class Alarme extends Thread{
    ArrayList<String> alarmes = new ArrayList<>();

    public Alarme(ArrayList<String> hora) {
        this.alarmes= hora;
    }
    @Override
    public void run(){
        while(true){
            for (int i = 0; i < alarmes.size(); i++) {
                if(new SimpleDateFormat("HH:mm").format(Calendar.getInstance().getTime()).equals(this.alarmes.get(i))){
                    Play p = new Play();
                    p.start();
                    try {
                        p.sleep(100000);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Alarme.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
    }
}
