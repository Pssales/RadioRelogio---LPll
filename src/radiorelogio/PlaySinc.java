/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package radiorelogio;

import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import javazoom.jl.player.Player;

/**
 *
 * @author Camila
 */
public class PlaySinc {

    public boolean emOperacao;

    public synchronized void playSinc(String music, ArrayList<File> caminho, String tipo) {
        while (this.emOperacao == true) {  // Aguarda a liberação da conta para operação
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }
        this.emOperacao = true;

        if (tipo == "hora") {
            Player player;
            FileInputStream musica;
            try {
                musica = new FileInputStream("src/horas/HRS" + new SimpleDateFormat("HH").format(Calendar.getInstance().getTime()) + ".mp3");
                player = new Player(musica);
                player.play();

                musica = new FileInputStream("src/horas/MIN" + new SimpleDateFormat("mm").format(Calendar.getInstance().getTime()) + ".mp3");
                player = new Player(musica);
                player.play();
            } catch (Exception e) {
                System.out.println(e);
            }
        } else {
            
            File selecionada = new File(music);
            Player player;
            boolean achou = false;
            FileInputStream musica;
            try {
                for (int i = 0; i < caminho.size(); i++) {
                    if (caminho.get(i).equals(selecionada) || achou==true) {
                        achou=true;
                        System.out.println(caminho.get(i));
                        musica = new FileInputStream(caminho.get(i));
                        player = new Player(musica);
                        player.play();
                    }
                }

            } catch (Exception e) {
                System.out.println(e);
            }
        }

        this.emOperacao = false;

        notifyAll(); //notifica os objetos que estão esperando

    }
}
