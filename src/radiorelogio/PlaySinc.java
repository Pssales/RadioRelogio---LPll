/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package radiorelogio;

import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javazoom.jl.player.Player;

/**
 *
 * @author Camila
 */
public class PlaySinc {

    static File caminho;
    static String tipo;
    public boolean emOperacao;

    public synchronized void playSinc(File caminho, String tipo) {
        this.caminho = caminho;
        this.tipo = tipo;
        while (this.emOperacao == true) {  // Aguarda a liberação da conta para operação
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }
        this.emOperacao = true;

        if (this.tipo == "hora") {
            Player player;
            FileInputStream musica;
            try {//fala hora:
                musica = new FileInputStream("src/horas/HRS" + new SimpleDateFormat("HH").format(Calendar.getInstance().getTime()) + ".mp3");
                player = new Player(musica);
                player.play();
                //fala minutos:
                musica = new FileInputStream("src/horas/MIN" + new SimpleDateFormat("mm").format(Calendar.getInstance().getTime()) + ".mp3");
                player = new Player(musica);
                player.play();
            } catch (Exception e) {
                System.out.println(e);
            }
        } else {
            File file = new File("src/musica/");
            File afile[] = file.listFiles();

            Player player;
            FileInputStream musica;
            try {
                musica = new FileInputStream(this.caminho);
                player = new Player(musica);

                player.play();
                System.out.println("Tocando agora " + afile[1].getName());
            } catch (Exception e) {
                System.out.println(e);
            }
        }

        this.emOperacao = false;
        notifyAll(); //notifica os objetos que estão esperando

    }

    public void playhora(File caminho, String tipo) {
        Player player;
        FileInputStream musica;
        try {//fala hora:
            musica = new FileInputStream("src/horas/HRS" + new SimpleDateFormat("HH").format(Calendar.getInstance().getTime()) + ".mp3");
            player = new Player(musica);
            player.play();
            //fala minutos:
            musica = new FileInputStream("src/horas/MIN" + new SimpleDateFormat("mm").format(Calendar.getInstance().getTime()) + ".mp3");
            player = new Player(musica);
            player.play();
        } catch (Exception e) {
            System.out.println(e);
        }

    }
}
