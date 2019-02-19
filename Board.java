package tetris;

import sun.audio.*;

import java.awt.BorderLayout;
import java.io.FileInputStream;
import java.io.IOException;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.*;

import tetris.Shape.Tetrominoes; // Importação dos desenhos para a peça da classe Desenho


	public class Board extends JPanel implements ActionListener {
		 // Tamanho do Quadro a ser gerado
	    
		final int BoardLargura = 10;
		final int BoardAltura = 22;

Timer tempo; //timer

//estados do jogo

boolean quedaFim = false; // peça caiu
boolean iniciado = false; //qnd está jogando
boolean Pausado = false; //qnd está pausado

int numLinesRemoved = 0; // linhas removidas

//x e y atual das peças

int atualX = 0;
int atualY = 0;

JLabel statusbar;
JLabel score; 
JLabel level;



private JPanel hiScorePanel;



Shape atualPeça; //peça atual
Tetrominoes[] board; //tipos de quadros



public Board (Tetris parent) {  // (Tetris parent)
	 
	//Método construtor da classe, recebendo JFrame principal como parâmetro
	
	
	setFocusable(true);
	
	
    atualPeça = new Shape();
   
    tempo = new Timer(400, this);
    tempo.start();
    
    

   //statusbar =  parent.getStatusBar();
   // score = parent.getScore();
   statusbar =  parent.getStatusBar();
   statusbar.setForeground(Color.RED);
   score = parent.getScore();
   score.setForeground(Color.BLUE);
   //parent.music();
   board = new Tetrominoes[BoardLargura * BoardAltura];
  
   
   parent.addKeyListener(new TAdapter());
   clearBoard();  

 
}



public void actionPerformed(ActionEvent e) { // Define o padrão da queda da peça no jogo , se ela acabou de cair então define que ela ainda não acabou de cair e cria um novo desenho, se não, ele desce uma linha a peça
    if (quedaFim) {
        
    	quedaFim = false;
        novaPedra();
    
    } 
    
    else {
        descerUmaLinha();
    }
}

//Define o tamanho de cada quadrado em Largura x Altura
int quadradoLargura() { return (int) getSize().getWidth() / BoardLargura; }
int quadradoAltura() { return (int) getSize().getHeight() / BoardAltura; }

// Define em que posição (x,y) a peça será desenhada
		Tetrominoes shapeAt(int x, int y) { 
	
			
	return board[( y * BoardLargura) + x ]; 
	
}

private void clearBoard()
{
    for (int i = 0; i < BoardAltura * BoardLargura; ++i)
        
    	board[i] = Tetrominoes.NenhumShape;
}

public void start()
{
    if (Pausado)
        return;

    iniciado = true;
    quedaFim = false;
    
    numLinesRemoved = 0;
    clearBoard();

    novaPedra();
    tempo.start();
}

private void pause()
{
    if (!iniciado)
        return;

    Pausado = !Pausado;
    if (Pausado) {
        tempo.stop();
        
  statusbar.setText("paused");
    } else {
        tempo.start();
        statusbar.setText(String.valueOf(numLinesRemoved));
    }
    repaint();
}



public void paint(Graphics g)        //desenha 'board'
{ 
    super.paint(g);

    Dimension size = getSize(); //tamanho do JPanel
    
    int limiteBoard = (int) size.getHeight() - BoardAltura * quadradoAltura();  //limite do JPanel


    for (int i = 0; i < BoardAltura; ++i) {
        for (int j = 0; j < BoardLargura; ++j) {
            Tetrominoes shape = shapeAt(j, BoardAltura - i - 1);
            if (shape != Tetrominoes.NenhumShape)
                drawSquare(g, 0 + j * quadradoLargura(),
                           limiteBoard + i * quadradoAltura(), shape);
        }
    }

    if (atualPeça.retornaShape() != Tetrominoes.NenhumShape) { // Para cada quadro, define a posição em (x,y) e desenha na tela
        for (int k = 0; k < 4; ++k) {
            int x = atualX + atualPeça.x(k);
            int y = atualY - atualPeça.y(k);
            
            drawSquare(g, 0 + x * quadradoLargura(),
                       limiteBoard + (BoardAltura - y - 1) * quadradoAltura(),
                       atualPeça.retornaShape());
        }
    }
}

private void dropDown()
{
    int newY = atualY;
    while (newY > 0) {
    	
        if (!tryMove(atualPeça, atualX, newY - 1))
            break;
        --newY;
    }
    pieceDropped();
}

private void descerUmaLinha()         //Acelera peça (descendo uma linha) qnd pressiona S
{
    if (!tryMove(atualPeça, atualX, atualY - 1))
        pieceDropped();
}




private void pieceDropped()
{
    for (int i = 0; i < 4; ++i) {
        int x = atualX + atualPeça.x(i);
        	int y = atualY - atualPeça.y(i);
        
        	board[(y * BoardLargura) + x] = atualPeça.retornaShape();
    }

    removeLinhaCheia();

    if (!quedaFim)
        novaPedra();
}

private void novaPedra()
{
	atualPeça.setRandom();
    
	
	
	atualX = BoardLargura / 2 + 1;
    atualY = BoardAltura - 1 + atualPeça.minimoY();

    if (!tryMove(atualPeça, atualX, atualY)) {
    	
    	atualPeça.setShape(Tetrominoes.NenhumShape);
       
   
    	tempo.stop();
    	
    	
        iniciado = false;
        
        //statusbar.setText("game over");
        
        
        
        JOptionPane.showMessageDialog(null,"GAME OVER , TRY AGAIN", "FIM DE JOGO",0,new ImageIcon("icontetris.png"));
        
        statusbar.setText("NÚMERO DE LINHAS REMOVIDAS: 0");
        score.setText("SCORE:");
        
        
    }
}

	private boolean tryMove(Shape newPiece, int newX, int newY)
{
    for (int i = 0; i < 4; ++i) {
        int x = newX + newPiece.x(i);
        int y = newY - newPiece.y(i);
        if (x < 0 || x >= BoardLargura || y < 0 || y >= BoardAltura)
            return false;
        if (shapeAt(x, y) != Tetrominoes.NenhumShape)
            return false;
    }

    atualPeça = newPiece;
    atualX = newX;
    atualY = newY;
    
    repaint();
    
    return true;
}

private void removeLinhaCheia()
{
    int linhasCheias = 0;

    for (int i = BoardAltura - 1; i >= 0; --i) {
        boolean lineIsFull = true;

        for (int j = 0; j < BoardLargura; ++j) {
            if (shapeAt (j, i) == Tetrominoes.NenhumShape) {
                lineIsFull = false;
                break;
            }
        }

        if (lineIsFull) {
            ++linhasCheias;
            
           
            for (int k = i; k < BoardAltura - 1; ++k) {
                for (int j = 0; j < BoardLargura; ++j)
                     board[(k * BoardLargura) + j] = shapeAt(j, k + 1);
            }
        }
    }

    if (linhasCheias > 0) {                        //começa a contar quantas linhas foram removidas
        numLinesRemoved += linhasCheias;
        
        statusbar.setText(String.valueOf(numLinesRemoved ));
        
        score(numLinesRemoved);
       
        /*int s = numLinesRemoved * 10;
       score.setText(String.valueOf(s));
        */
        
        quedaFim = true;
        atualPeça.setShape(Tetrominoes.NenhumShape);
        repaint();
    }
 }


public void score(int numLines){
	
	int pontos = numLinesRemoved * 10;
	score.setText(String.valueOf(pontos));
	
	if(pontos > 50){ acelera(pontos);}													//**Criar dificuldades
		/*Timer time2 = new Timer(800,this);
		time2.start();
	}

	if(pontos > 50){
		Timer time3 = new Timer(1000,this);
		time3.start();
		
	}*/
	
	
}


public void acelera(int pontos){
	
	if(pontos > 50){													//**Criar dificuldades
		Timer time2 = new Timer(800,this);
		time2.start();
		if (!tryMove(atualPeça, atualX, atualY)) { time2.stop();}
	}

	if(pontos > 150){
		Timer time3 = new Timer(1000,this);
		time3.start();
		if (!tryMove(atualPeça, atualX, atualY)) { time3.stop();}
	}
	
	if(pontos > 200){  
		Timer time4 = new Timer(1000,this);
		time4.start();
		if (!tryMove(atualPeça, atualX, atualY)) { time4.stop();}
	}
	
}




public void level(int pontos){
	
	int nivel = 0;
	for(int k = 0; k <= pontos ; k++){
		++nivel;
	}
	level.setText(String.valueOf(nivel));
	
	
	
}














private void drawSquare(Graphics g, int x, int y, Tetrominoes shape)
{
    Color colors[] = { 
    	new Color(0, 0, 0), new Color(153, 204, 50), 
        new Color(0, 0, 156), new Color(107, 35, 42), 
        new Color(255, 127, 0), new Color(255, 0, 0), 
        new Color(0, 255, 0), new Color(218, 170, 0)
    };


    Color color = colors[shape.ordinal()];

    g.setColor(color);
    g.fillRect(x + 1, y + 2, quadradoLargura() - 2, quadradoAltura() - 2);

    g.setColor(color.darker());
    
    g.drawLine(x, y + quadradoAltura() - 1, x, y);
    g.drawLine(x, y, x + quadradoLargura() - 1, y);

    g.setColor(color.darker());
    g.drawLine(x + 1, y + quadradoAltura() - 1,
                     x + quadradoLargura() - 1, y + quadradoAltura() - 1);
    g.drawLine(x + quadradoLargura() - 1, y + quadradoAltura() - 1,
                     x + quadradoLargura() - 1, y + 1);
}






class TAdapter extends KeyAdapter {
     public void keyPressed(KeyEvent e) {

         if (!iniciado || atualPeça.retornaShape() == Tetrominoes.NenhumShape) {  
             return;
         }

         int keycode = e.getKeyCode();

         if (keycode == 'p' || keycode == 'P') {
             pause();
             return;
         }

         if (Pausado)
             return;

         switch (keycode) {
         case KeyEvent.VK_LEFT:
             tryMove(atualPeça, atualX - 1, atualY);
             break;
         case KeyEvent.VK_RIGHT:
             tryMove(atualPeça, atualX + 1, atualY);
             break;
         case KeyEvent.VK_DOWN:
             tryMove(atualPeça.giraDireita(), atualX, atualY);
             break;
         case KeyEvent.VK_UP:
             tryMove(atualPeça.giraEsquerda(), atualX, atualY);
             break;
         case KeyEvent.VK_SPACE:
             dropDown();
             break;
         case 's':
             descerUmaLinha();
             break;
         case 'S':
             descerUmaLinha();
             break;
         }

     }
 }

	
/*private void showHiScore() {
setHiScorePanel();

JOptionPane.showMessageDialog(this,hiScorePanel,"HI SCORE", JOptionPane.PLAIN_MESSAGE);

hiScorePanel = null; }
private void setHiScorePanel() {
hiScorePanel = new JPanel(new BorderLayout());
Tetris tg = new Tetris();

String[] colNames = {"Place", "Points", "Lines", "Name"};
String[][] data = new String[tg.hiScore.length+1][colNames.length];
data[0] = colNames;
for (int i = 0; i < tg.hiScore.length; i++) {
data[i+1] = new String[colNames.length];
data[i+1][0] = (i+1)+".";
data[i+1][1] = (""+tg.hiScore[i].score); data[i+1][2] = (""+tg.hiScore[i].lines); data[i+1][3] = (""+tg.hiScore[i].name);
}

JTable table = new JTable(data, colNames); table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF); table.setBackground(new Color(230,255,255)); table.setEnabled(false);

hiScorePanel.add(table,BorderLayout.CENTER);}

*/
	
	
	
	}
