package FilasOuPilhasGenerico;
import java.io.File;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
* Esta classe é responsável por permitir escolher um arquivo.
*/
class EscolherArquivo {
    public static String caminho () {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        
        int resultado = fileChooser.showOpenDialog(null);
        
        if (resultado == JFileChooser.CANCEL_OPTION) {
            return null;
        }
        
        File arquivo = fileChooser.getSelectedFile();
        
        if (arquivo == null || arquivo.getName().equals("")) {
            JOptionPane.showMessageDialog(null, "Nome de arquivo inválido!");
        } else {
            return arquivo.getPath();
        }
        
        return null;
    }
}

/**
 * Classe que representa um link do TAD (Tipo Abstrato de Dado).
 */
class Celula {
    public int numero;
    public Celula ant;
    public Celula prox;

    public Celula(int numero) {
        this.numero = numero;
        this.prox = null;
    }

    public void imprimeCelula() {
        System.out.println("Número: " + numero);
    }
}

/**
 * Classe que representa a lista genérica que serve tanto para Filas quanto Filas.
 */
class FilaOuPilha {
    private Celula primeiroInserido;
    private Celula ultimoInserido;

    public FilaOuPilha() {
        this.primeiroInserido = null;
        this.ultimoInserido = null;
    }
    
    public boolean estaVazia () {
        return (this.primeiroInserido == null);
    }
    
    @SuppressWarnings("unused")
	private void insereNoInicio (Celula celula) {
        if (this.estaVazia()) { //Checa se a lista está vazia.
            this.primeiroInserido = this.ultimoInserido = celula; //Primeira e última.
            return; //Sai da rotina.
        }
        
        //Se continuou, a lista não está vazia. Insere no início da lista.
        celula.prox = this.primeiroInserido; //Ajusta  ponteiro do próximo da nova primeira célula.
        this.primeiroInserido.ant = celula; //Ajusta ponteiro do anterior da antiga primeira célula.
        this.primeiroInserido = celula;
    }
    
    private void insereNoFinal (Celula celula) {
        if (this.estaVazia()) { //Checa se a lista está vazia.
            this.primeiroInserido = this.ultimoInserido = celula; //Primeira e última.
            return; //Sai da rotina.
        }
        
        //Se continuou, a lista não está vazia. Insere no final da lista.
        this.ultimoInserido.prox = celula; //Ajusta ponteiro próximo do que era a última.
        celula.ant = this.ultimoInserido; //Ajusta ponteiro anterior.
        this.ultimoInserido = celula;
    }
    
    private Celula retiraDoInicio () {
        if (this.estaVazia()) { //Checa se a lista está vazia.
            return null; //Sai da rotina.
        }
        
        //Se continuou, a lista não está vazia. Retira do início da lista.
        Celula celula = this.primeiroInserido;
        this.primeiroInserido = this.primeiroInserido.prox; //Ajusta o ponteiro para o novo primeiro.
        this.primeiroInserido.ant = null; //Ajusta ponteiro do anterior do novo primeiro.
        //Retorna antiga primeira célula.
        return celula;
    }
    
    private Celula retiraDoFinal () {
        if (this.estaVazia()) { //Checa se a lista está vazia.
            return null; //Sai da rotina.
        }
        
        //Se continuou, a lista não está vazia. Retira do final da lista.
        Celula celula = this.ultimoInserido; //Antigo último.
        this.ultimoInserido = this.ultimoInserido.ant; //Ajusta ponteigo da para a nova última célula.
        this.ultimoInserido.prox = null; //Ajusta o ponteiro para o póximo da nova última célula.
        //Retorna antiga primeira célula.
        return celula;
    }
    /**
    * O enfileiramento é feito sempre inserindo do final da lista.
    */
    public void enfileira (int numero) {
        Celula celula = new Celula(numero);
        
        this.insereNoFinal(celula);
    }
    /**
    * O desenfileiramento é realizado sempre retirando do início da lista.
    */
    public Celula desenfileira() {
        Celula celula = this.retiraDoInicio();
        
        return celula;
    }
    /**
    * O empilhamento é realizado sempre inserindo no final da lista.
    */
    public void empilha (int numero) {
        Celula celula = new Celula(numero);
        this.insereNoFinal(celula);
    }
    /**
    * O desenpilhamento é realizado sempre retirando do final da lista.
    */
    public Celula desempilha() {
        Celula celula = this.retiraDoFinal();

        return celula;
    }
    /**
    * A impressão da fila é realizada do início para o final da lista.
    */
    public void imprimeFila() {
        for (Celula celula = this.primeiroInserido; celula != null; celula = celula.prox){
            celula.imprimeCelula();
        }
    }
    /**
    * A impressão da pilha é realizada do final para o início da lista.
    */
    public void imprimePilha() {
        for (Celula celula = this.ultimoInserido; celula != null; celula = celula.ant){
            celula.imprimeCelula();
        }
    }
}
/**
* Classe principal que exemplificará o uso de Listas para Filas e Pilhas.
*/
public class FilasOuPilhasGenerico {
    public static void main(String[] args) throws IOException {
        FilaOuPilha filaOuPilha = new FilaOuPilha();
        
        System.out.println ("Teste de Fila!");
        filaOuPilha.enfileira(1);
        filaOuPilha.enfileira(2);
        filaOuPilha.enfileira(3);
        filaOuPilha.enfileira(4);
        filaOuPilha.enfileira(5);
        System.out.println ("Antes de desenfileirar!");
        filaOuPilha.imprimeFila();
        filaOuPilha.desenfileira();
        filaOuPilha.desenfileira();
        System.out.println ("Depois de desenfileirar!");
        filaOuPilha.imprimeFila();
        
        //Limpa a lista.
        filaOuPilha = new FilaOuPilha();
        
        System.out.println ("Teste de Pilha!");
        filaOuPilha.empilha(1);
        filaOuPilha.empilha(2);
        filaOuPilha.empilha(3);
        filaOuPilha.empilha(4);
        filaOuPilha.empilha(5);
        System.out.println ("Antes de desempilhar!");
        filaOuPilha.imprimePilha();
        filaOuPilha.desempilha();
        filaOuPilha.desempilha();
        System.out.println ("Depois de desempilhar!");
        filaOuPilha.imprimePilha();
    }
}