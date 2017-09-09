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
 * @author a1600222
 */
public class Play extends Thread {

    ArrayList<File> caminho = new ArrayList<>();
    static String tipo;
    PlaySinc playsic;
    boolean sinc;

    public Play(ArrayList<File> caminho, String tipo, PlaySinc playsinc, boolean sinc) {
        this.caminho = caminho;
        this.tipo = tipo;
        this.playsic = playsinc;
        this.sinc = sinc;
    }

    @Override
    public void run() {
        System.out.println(this.sinc);
        if (this.sinc == true) {
            this.playsic.playSinc(this.caminho, this.tipo);
        } else {
            this.playhora();
        }

    }

    public void playhora() {
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

    }
}

/*

    Desenvolver uma aplicação que simule um Rádio Relógio, com as seguintes especificações:

ok    - Utilizar interface gráfica do Java;

ok    - Deverá apresentar um display com a hora atual, de preferência sincronizada com a hora do computador que estiver rodando a aplicação;

ok    - Deverá ser mostrada a hora no formato: HH:MM:SS;

ok    - Deverá conter um tabela que permita ao usuário selecionar uma lista de músicas que ele deseje ouvir;

    - Essa tabela deverá permitir incluir e excluir arquivos MP3 para serem tocados;

    - Ao selecionar uma música na tabela e ao pressionar o botão play, a música deverá ser tocada e ao seu término, caso exista, deverá iniciar a próxima música escolhida, da sequência, e assim sucessivamente até a última música da lista;

    - A qualquer momento o usuário poderá incluir ou excluir música, inclusive se o aplicativo estiver tocando alguma;

ok    - A interface deverá apresentar um botão para para a música que estiver tocando;

ok    - A cada hora cheia, respeitando o término da música que estiver tocando, o rádio deverá informar a hora atual. Por exemplo: Se for 21h e ainda estiver tocando uma música, a aplicação deverá esperar a música terminar para informar a hora. Se a música terminar às 21:02h, esta é a hora a ser informada;

ok    - Deverá haver um botão para que a hora atual seja informada, por cima da música que estiver tocando.

ok    - Deverá ser possível a programação de N horários em que o rádio relógio irá "Despertar"

 */
