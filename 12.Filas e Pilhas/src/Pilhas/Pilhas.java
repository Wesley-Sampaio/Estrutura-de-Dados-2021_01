package Pilhas;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Classe que representa um link do TAD (Tipo Abstrato de Dado).
 */
class Celula {
    public char caractere;
    public Celula prox;

    public Celula(char caractere) {
        this.caractere = caractere;
        this.prox = null;
    }

    public void imprimeCelula() {
        System.out.println("Caractere: " + caractere);
    }
}

/**
 * Classe que representa a pilha que é uma lista 
 * de células representando o TAD PILHA 
 * (LIFO - Last In First Out).
 * A título de exemplo está sendo implementado o 
 * casamento de delimitadores de blocos de 
 * comandos.
 */
class Pilha {
    private Celula topo;
    @SuppressWarnings("unused")
	private Celula base;

    public Pilha() {
        this.topo = null;
        this.base = null;
    }
    
    public boolean estaVazia () {
        return (this.topo == null);
    }
    /**
    * O empilhamento é realizado sempre inserindo no topo da pilha.
    */
    public void empilha (char caractere) {
        Celula celula = new Celula(caractere);

        if (this.estaVazia()) { //Checa se a pilha está vazia.
            this.topo = this.base = celula; //Primeira e última.
            return; //Sai da rotina.
        }
        
        //Se continuou, a pilha não está vazia. Insere no topo da pilha.
        celula.prox = this.topo;
        this.topo = celula;
    }
    /**
    * O desenpilhamento é realizado sempre retirando do topo da pilha.
    */
    public Celula desempilha() {
        Celula celula = this.topo;
        
        //Se a lista estiver vazia não tem o que retornar.
        if (this.estaVazia()) {
            return null;
        }
        
        //Retira do topo da fila.
        this.topo = this.topo.prox;
        return celula;
    }

    public void imprimeCelulas() {
        for (Celula celula = this.topo; celula != null; celula = celula.prox){
            celula.imprimeCelula();
        }
    }
}
/**
* Classe que realiza a checagem de delimitadores de código.
*/
class ChecadorDeDelimitadores {
    private String input;     //Cadeia de entrada.
    
    public ChecadorDeDelimitadores(String input) {
        this.input = input;
    }
    
    public void checa() {
        Pilha pilha = new Pilha();
        boolean ok = true;
        
        for (int j = 0; j < this.input.length(); j++) {
            char caractere = this.input.charAt(j); //Obtém caractere
            
            switch(caractere) {
                case '{' :
                case '[' :
                case '(' :
                    pilha.empilha(caractere);
                    break;

                case '}' :
                case ']' :
                case ')' :
                    Celula celula = pilha.desempilha();
                    if (celula == null) { //Pilha vazia
                        System.out.println("Erro: " + caractere + " na posição: " + j);
                        ok = false;
                    } else {
                        //Testa se os delimitadores não coincidem.
                        if (caractere == '}' && celula.caractere != '{'
                         || caractere == ']' && celula.caractere != '['
                         || caractere == ')' && celula.caractere != '(') {
                            System.out.println("Erro: " + caractere + " na posição: " + j);
                            ok = false;
                        }
                    }
                    break;
                    
                default :
                    //Não tem que fazer nada se não foi um delimitador de bloco.
            }//Fim do switch
        }//Fim do for
        //Neste ponto todos os caracteres foram processados e a pilha deveria estar vazia.
        if (pilha.estaVazia()) {
            //Ok. Tudo certo.
        } else {
            System.out.println("Está faltando um delimitado de final de bloco.");
            ok = false;
        }
        
        //Para o usuário saber se deu tudo certo:
        if (ok) {
            System.out.println("Os delimitadores estão corretos!");
        }
    }//Fim do método checa()
}//Fim da classe ChecadorDeDelimitadores 
/**
* Classe principal que utilizará da classe 
* ChecadorDeDelimitadores para checagem dos 
* delimitadores para exemplificar o uso de Pilhas.
*/
public class Pilhas {
    public static void main(String[] args) throws IOException {
        String input;
        
        while (true) {
            System.out.print("Entre com a string contendo os delimitadores: ");
            System.out.flush();
            
            input = getString(); //Lê cadeia de caracteres do teclado.
            if (input.equals("")) { //Sai se [Enter]
                break;
            }
            
            ChecadorDeDelimitadores checador = new ChecadorDeDelimitadores(input);
            checador.checa();
        }
    }
    
    public static String getString() throws IOException {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        String s = br.readLine();
        return s;
    }
}