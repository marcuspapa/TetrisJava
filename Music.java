package tetris;
import java.awt.event.*;
import javax.swing.*;
import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;

/** Classe que cont�m a m�sica do menu do jogo */

public class Music implements Runnable {
	
	File musica; // O arquivo (m�sica)
	Clip clip; // Classe que controla a m�sica
	
	public void run () { // Run, para poder tocar a m�sica durante o jogo
	
		try {
			play(); // tenta iniciar a m�sica
		} catch (Exception e) {
			// qualquer erro que acontecer cai aqui
			JOptionPane.showMessageDialog(null, "Erro ao carregar a m�sica!"); 
			}
	
		}
		
		private void play()   {
    	
			try {
    		
				musica = new File("som.wav"); //abre o diret�rio da m�sica
				AudioInputStream ais = AudioSystem.getAudioInputStream(musica); //transforma a m�sica em um AudioInputStream
        
				clip = AudioSystem.getClip();
				clip.open( ais ); //clip abre a m�sica
				clip.loop(1000); //n�mero de vezes que a m�sica ser� repetida
				clip.start(); // inicia a m�sica
       
			} catch (Exception e) {
    		//Qualquer excess�o ser� ignorada. 
			}
			
		
		}
		
		public void stop() {
			clip.stop();
		}
		
		public void start() {
			clip.start();
		}
    } //Fim da classe ClipControl

