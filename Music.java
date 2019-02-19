package tetris;
import java.awt.event.*;
import javax.swing.*;
import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;

/** Classe que contém a música do menu do jogo */

public class Music implements Runnable {
	
	File musica; // O arquivo (música)
	Clip clip; // Classe que controla a música
	
	public void run () { // Run, para poder tocar a música durante o jogo
	
		try {
			play(); // tenta iniciar a música
		} catch (Exception e) {
			// qualquer erro que acontecer cai aqui
			JOptionPane.showMessageDialog(null, "Erro ao carregar a música!"); 
			}
	
		}
		
		private void play()   {
    	
			try {
    		
				musica = new File("som.wav"); //abre o diretório da música
				AudioInputStream ais = AudioSystem.getAudioInputStream(musica); //transforma a música em um AudioInputStream
        
				clip = AudioSystem.getClip();
				clip.open( ais ); //clip abre a música
				clip.loop(1000); //número de vezes que a música será repetida
				clip.start(); // inicia a música
       
			} catch (Exception e) {
    		//Qualquer excessão será ignorada. 
			}
			
		
		}
		
		public void stop() {
			clip.stop();
		}
		
		public void start() {
			clip.start();
		}
    } //Fim da classe ClipControl

