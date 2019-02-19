package tetris;
import java.util.Random;
import java.lang.Math;


public class Shape {

	enum Tetrominoes { NenhumShape, ZShape , SShape, LineShape, TShape, LShape, SquareShape, LEspelhadoShape }; //define os desesnhos como constantes (*enums s�o bastante utilizadas em jogos)
	
	private Tetrominoes pe�a;  //criando pe�a
	private int coords[][];  //coordenadas da pe�a
	private int[][][] coordsTabela; //tabela para as coordenadas
	
	
	public Shape(){				//metodo construtor que zera as coordenadas e precisa zerar o desenho
		
		coords = new int[4][2]; //Nova coordenada 4x2 = 4 - quadrados dos desenho ; 2 - plano xy
		
		setShape(Tetrominoes.NenhumShape); //zera, pois define sem desenho
		
		
	
	}
	
	public void setShape(Tetrominoes shape) {
		// Mapa de desenhos poss�veis para pe�a, cada linha representa dois desenhos
        // cada 4 colunas em uma linha representam uma pe�a, cada c�dula representa o plano (x,y)
		
		
			
        coordsTabela = new int[][][] {
           { { 0, 0 },   { 0, 0 },   { 0, 0 },   { 0, 0 } }, { { 0, -1 },  { 0, 0 },   { -1, 0 },  { -1, 1 } },
           { { 0, -1 },  { 0, 0 },   { 1, 0 },   { 1, 1 } }, { { 0, -1 },  { 0, 0 },   { 0, 1 },   { 0, 2 } },
           { { -1, 0 },  { 0, 0 },   { 1, 0 },   { 0, 1 } }, { { 0, 0 },   { 1, 0 },   { 0, 1 },   { 1, 1 } },
           { { -1, -1 }, { 0, -1 },  { 0, 0 },   { 0, 1 } }, { { 1, -1 },  { 0, -1 },  { 0, 0 },   { 0, 1 } }
           
       };
        
        // Seta as coordenadas do desenho
        for (int i = 0; i < 4 ; i++) {
            for (int j = 0; j < 2; ++j) {
                coords[i][j] = coordsTabela[shape.ordinal()][i][j];
            }
        }
        
        
        pe�a = shape; // Define a pe�a com o desenho
        
     
	}
	  // Manipula e retorna o (x,y) do desenho
	 	private void setX(int index, int x) { 
	 		
	 		coords[index][0] = x; 
	 		}
	    
	 	private void setY(int index, int y) { 
	 		coords[index][1] = y; 
	 		}
	    
	    
	    
	    
	    public int x(int index) { return coords[index][0]; }
	    public int y(int index) { return coords[index][1]; }
	    
	    public Tetrominoes retornaShape()  {
	    	
	    	return pe�a; 
	    	}  // Retorna o desenho

	 
	    public int setRandom() //m�todo q gera o desenho aleat�rio
	    {
	    	
	    	//define um random
	        Random r = new Random();
	        System.out.println("atual" + r);
	        
	        
	        int x = Math.abs(r.nextInt()) % 7 + 1;   // 7 - n�mero de desenhos
	        System.out.println("proxima" + x);
	        
	        Tetrominoes[] values = Tetrominoes.values();   // Cria um quadro com desenhos possiveis
	        
	        System.out.println("atual" + x);
	        
	        setShape(values[x]);  // Seta somente o desenho gerado pelo numero rand�mico
	        
	        
	        
	       return x;
	       
	    
	    }
	       
	    
	   
	    
	    
	       
	    
	    // Retorna a coordenada minima de x
	    public int minimoX()
	    {
	      int m = coords[0][0];
	      for (int i=0; i < 4; i++) {
	          m = Math.min(m, coords[i][0]);
	      }
	      return m;
	    }

	    // Retorna a coordenada minima de y
	    public int minimoY() 
	    {
	      int m = coords[0][1];
	      
	      for (int i=0; i < 4; i++) {
	          m = Math.min(m, coords[i][1]);
	      }
	      return m;
	    }
	   
	    // Gira o desenho para a esquerda
	    public Shape giraEsquerda() 
	    {
	    	  // Cria um resultado ,  Seta o resultado com a pe�a ,  Inverte os valores das coordenadas, Retorna o desenho invertido
	        
	    	 Shape result = new Shape();    // objeto criado s� pra usar no m�todo \o/
		     result.pe�a = pe�a;
	    	
		     /*if (result.pe�a == Tetrominoes.SquareShape)           *****BUG (Faz o L n girar) -> problema no random
	            return this;  //se for quadrada n gira*/

	      
	 
	        for (int i = 0; i < 4; ++i) {
	            result.setX(i, y(i));
	            result.setY(i, -x(i));
	            
	        }
	        
	        return result;
	        
	    }

	    public Shape giraDireita()
	    {
	    	Shape result = new Shape();
	        result.pe�a = pe�a;
	        
	        if (result.pe�a == Tetrominoes.SquareShape)
	            return this;

	        

	        for (int i = 0; i < 4; ++i) {
	            result.setX(i, -y(i));
	            result.setY(i, -x(i));
	        }
	        return result;
	    }
}