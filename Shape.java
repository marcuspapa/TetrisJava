package tetris;
import java.util.Random;
import java.lang.Math;


public class Shape {

	enum Tetrominoes { NenhumShape, ZShape , SShape, LineShape, TShape, LShape, SquareShape, LEspelhadoShape }; //define os desesnhos como constantes (*enums são bastante utilizadas em jogos)
	
	private Tetrominoes peça;  //criando peça
	private int coords[][];  //coordenadas da peça
	private int[][][] coordsTabela; //tabela para as coordenadas
	
	
	public Shape(){				//metodo construtor que zera as coordenadas e precisa zerar o desenho
		
		coords = new int[4][2]; //Nova coordenada 4x2 = 4 - quadrados dos desenho ; 2 - plano xy
		
		setShape(Tetrominoes.NenhumShape); //zera, pois define sem desenho
		
		
	
	}
	
	public void setShape(Tetrominoes shape) {
		// Mapa de desenhos possíveis para peça, cada linha representa dois desenhos
        // cada 4 colunas em uma linha representam uma peça, cada cédula representa o plano (x,y)
		
		
			
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
        
        
        peça = shape; // Define a peça com o desenho
        
     
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
	    	
	    	return peça; 
	    	}  // Retorna o desenho

	 
	    public int setRandom() //método q gera o desenho aleatório
	    {
	    	
	    	//define um random
	        Random r = new Random();
	        System.out.println("atual" + r);
	        
	        
	        int x = Math.abs(r.nextInt()) % 7 + 1;   // 7 - número de desenhos
	        System.out.println("proxima" + x);
	        
	        Tetrominoes[] values = Tetrominoes.values();   // Cria um quadro com desenhos possiveis
	        
	        System.out.println("atual" + x);
	        
	        setShape(values[x]);  // Seta somente o desenho gerado pelo numero randômico
	        
	        
	        
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
	    	  // Cria um resultado ,  Seta o resultado com a peça ,  Inverte os valores das coordenadas, Retorna o desenho invertido
	        
	    	 Shape result = new Shape();    // objeto criado só pra usar no método \o/
		     result.peça = peça;
	    	
		     /*if (result.peça == Tetrominoes.SquareShape)           *****BUG (Faz o L n girar) -> problema no random
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
	        result.peça = peça;
	        
	        if (result.peça == Tetrominoes.SquareShape)
	            return this;

	        

	        for (int i = 0; i < 4; ++i) {
	            result.setX(i, -y(i));
	            result.setY(i, -x(i));
	        }
	        return result;
	    }
}