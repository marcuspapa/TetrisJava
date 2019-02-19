package tetris;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.FileInputStream;
import java.io.IOException;

import javax.swing.*;

import sun.audio.*;

import java.io.*;
import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.*;

import java.lang.*;
import java.util.*;

public class Tetris extends JFrame implements WindowListener { //extends JPANEL , criar frame dps
	
	JLabel statusbar;
	JLabel score;
	JLabel level;
	JLabel luis;
	JButton statistics;
	
	String thaleco ="Super ARCADE Master Flipper Sis Tetris, versão 4.2.1";
	String instrucoes = "Para girar uma peça, utilize as teclas das setas para cima ou para baixo. Para mover uma peça no plano horizontal utilize as teclas das setas para a direita e para a esquerda";
	String instrucoes2 = "Para pausar o jogo utilize a tecla 'P'. Para acelerar a peça 'S' e para descer a peça direto utilize a tecla 'Espaço' ";
	
	JPanel contentPane;
	JPanel toolBar;
	JPanel workArea;
	
	JPanel gamePanel;
	JButton botaoNovo;
	JButton botaoSair;
	
	
	JButton newgame;

	JMenuBar menuBar;
	JMenu menuArquivo;
	JMenu menuAjuda;
	JMenu menuRecordes;
	JMenuItem menuNovo;
	JMenuItem menuSair;
	JMenuItem menuSobre;
	JMenuItem menuInstrucoes;
	Board board;
	int controleMusica=0;	
	
	JMenuItem som;
	
	//JPanel jp;
	//JPanel painelEsquerdo;
	
	//JMenuBar menuBar;
	
	
	public Tetris(){
		
		super("Super ARCADE Master Flipper Sis Tetris");
		addWindowListener(this);
		
		
		statusbar = new JLabel("NÚMERO DE LINHAS REMOVIDAS: 0 ");
		score = new JLabel("SCORE: 0");
		luis = new JLabel();
		
		ImageIcon ImagemFundo = new ImageIcon("core.png");
		 
		luis.setIcon(ImagemFundo); //aparecem
        luis.setBounds(0, 0, 800, 680);
         
        
		
		
		
		
		
		/*statistics = new JButton("Estatísticas");
		statistics.setPreferredSize(new Dimension(40,60));
		add(statistics, BorderLayout.EAST);

		statistics.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				JOptionPane.showMessageDialog(statistics, null);
			}
		});*/
			
		
		
		
		
		//JPanel all = new JPanel(new BorderLayout()); 
	//all.add(getLeftPanel(), BorderLayout.WEST);      

		//level = new JLabel("LEVEL: 0");
		
		
		/*JPanel r = new JPanel();
		BoxLayout rL = new BoxLayout(r,BoxLayout.Y_AXIS);
		r.setLayout(rL);
		Dimension ra = new Dimension(10, 0); 
		jp = new JPanel();
		jp.setLayout(new BoxLayout(jp, BoxLayout.LINE_AXIS));
		
		jp.add(Box.createRigidArea(ra)); 
		jp.add(new JLabel("SCORE:")); 
		score = new JLabel("0");
		*/
		
		
		contentPane = (JPanel) this.getContentPane();
		
		contentPane.setLayout(new BorderLayout(10, 10));
		
		contentPane.setBackground(Color.GRAY);
		
		contentPane.add(luis);
		
		JButton newgame = new JButton("NEW GAME");
		
		//newgame.setSize(5,5);
		//contentPane.add(newgame, Box.createRigidArea(new Dimension(0, 10)));
		
		
		toolBar = new JPanel(new FlowLayout());
		
		menuBar = new JMenuBar();

		
		menuArquivo = new JMenu("Arquivo");
		menuRecordes = new JMenu("Recordes");
		menuAjuda = new JMenu("Ajuda");
		menuNovo = new JMenuItem("Novo jogo");
		menuSair = new JMenuItem("Fechar jogo");
		menuSobre = new JMenuItem("Sobre");
		
		Music music = new Music();
		
		
		menuInstrucoes = new JMenuItem("Instruções");
		this.setJMenuBar(menuBar);
		menuBar.add(menuArquivo);
		menuBar.add(menuRecordes);
		menuBar.add(menuAjuda);
		menuArquivo.add(menuNovo);
		menuArquivo.add(menuSair);
		menuAjuda.add(menuSobre);
		menuAjuda.add(menuInstrucoes);
		
         
		som = new JMenuItem("SOM");
		menuArquivo.add(som);
		
       /*JPanel all = new JPanel(new BorderLayout());
        all.add(painelEsquerdo, BorderLayout.WEST);
     	all.add(getPainelDireito(), BorderLayout.EAST);
	
		
     	private JPanel getPainelDireito(){
     		JPanel r = new JPanel();
     		score = new JLabel("SCORE");
     		r.add(score);
     		return r;
     	}
     	
     	
     	*/
     	
     	
		 board = new Board(Tetris.this);
		 music.run();
     	
				
     	
        
		//add(level, BorderLayout.EAST);
		//add(jp, BorderLayout.EAST);
        
		 
		
	
		
        
		
		
	//	music();
		
		
		/*som.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
			som.addActionListener(song.music());
			
			}});
		*/
		
		
		som.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
			
			

                    if (controleMusica == 0) {
                    music.stop();
                    controleMusica = 1;
                    System.out.println(controleMusica);
                    }

                    else {
                    music.run();
                    controleMusica = 0;
                    System.out.println(controleMusica);
                    }

            }
				
				
				
				
				
				//music.stop();
			//NoMusic();
			
				
			
		});
		
	menuNovo.addActionListener(new ActionListener(){
			
		public void actionPerformed(ActionEvent e){
				
				
				JOptionPane.showMessageDialog(getComponent(0),"Boa sorte!", "VAI COMEÇAR", 0 , new ImageIcon("core.png"));
				
				//Board board = new Board(Tetris.this);
			
				//	limpaStatusBar();
				
			
				add(score, BorderLayout.EAST);
				add(board, BorderLayout.CENTER);
				add(statusbar, BorderLayout.SOUTH);
				luis.setBounds(0,0,0,0);
				
				board.start();
				
				
				
				//Musica musica = new Musica();
				//Thread threadMusic = new Thread(musica);
		     	
				//threadMusic.start();
		     	//music();
				
				setSize(300,600);
				setTitle("Tetris JAVA");
				
				
			}
		});
		
		menuSobre.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent e){
			ImageIcon icon = new ImageIcon("tetrisicon.png");
			JOptionPane.showMessageDialog(getComponent(0), thaleco , "SOBRE", 1 , icon);
		}
		});
		
		menuInstrucoes.addActionListener( new ActionListener() {
			
			public void actionPerformed(ActionEvent e){
			JOptionPane.showMessageDialog(getComponent(0),instrucoes,"Instruções",1,new ImageIcon("tetrisicon.png"));
			JOptionPane.showMessageDialog(getComponent(0),instrucoes2, "Instruções",1,new ImageIcon("tetrisicon.png"));
			}
		});
		
		menuSair.addActionListener(new ActionListener() {  
		    public void actionPerformed(ActionEvent e){  
		        System.exit(0);  
		    }  
		
		
		});

		//statusbar = new JLabel(" 0 ");
		//contentPane.add(statusbar, BorderLayout.SOUTH);
	}
	
	public JLabel getStatusBar(){
		return statusbar;
		
	}
	
	public JLabel limpaStatusBar(){
		return statusbar = new JLabel("NÚMERO DE LINHAS REMOVIDAS: 0 "); 
	}
	
	public JLabel getScore(){
		return score ;
		
	}

	   
        

		/*private JPanel getLeftPanel(){
	
	JPanel r = new JPanel();
	statistics = new JButton("Estatísticas");
	statistics.setPreferredSize(new Dimension(40,60));
	r.add(statistics, BorderLayout.WEST);

	statistics.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e){
			JOptionPane.showMessageDialog(statistics, null);
		}
		
	
		return r;
		
	});*/
	
	//public class Musica implements Runnable{
	//public void run(){
		public void music(){
		
		AudioPlayer MGP = AudioPlayer.player;
		AudioStream BGM = null;
	     try {  
	            BGM = new AudioStream(new FileInputStream("som.wav"));  
	            
	            
	            } catch(IOException error) {  
	                System.out.println("Error!!!");  
	            }  
	      
	     		
	            MGP.start(BGM);
	            
	            
	            
	            
	            
	        }  
	//}
		public void NoMusic(){
			
			
		            
		            AudioPlayer.player.stop();
		            
		            
		            
		        } 

		  
	public static void main(String[] args){
		
		
		//Tetris game = new Tetris();
		
				Tetris frame = new Tetris();
				frame.setSize(800, 400);
				frame.setVisible(true);
				frame.setLocation(300,100);
				frame.setResizable(false);
				
				//game.setLocationRelativeTo(null);
				//game.setVisible(true);
			
				
				
		
		}

public void windowActivated(WindowEvent arg0) {
	
	
}




public void windowClosed(WindowEvent arg0) {
	System.exit(0);
	
}




public void windowClosing(WindowEvent arg0) {
	System.exit(0);
	
}




@Override
public void windowDeactivated(WindowEvent arg0) {
	// TODO Auto-generated method stub
	
}




@Override
public void windowDeiconified(WindowEvent arg0) {
	// TODO Auto-generated method stub
	
}




@Override
public void windowIconified(WindowEvent arg0) {
	// TODO Auto-generated method stub
	
}




@Override
public void windowOpened(WindowEvent arg0) {
	// TODO Auto-generated method stub
	
}
}





