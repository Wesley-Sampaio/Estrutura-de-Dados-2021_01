package FilasOuPilhasGenerico;
import java.io.File;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
* Esta classe � respons�vel por permitir escolher um arquivo.
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
            JOptionPane.showMessageDialog(null, "Nome de arquivo inv�lido!");
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
        System.out.println("N�mero: " + numero);
    }
}

/**
 * Classe que representa a lista gen�rica que serve tanto para Filas quanto Filas.
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
        if (this.estaVazia()) { //Checa se a lista est� vazia.
            this.primeiroInserido = this.ultimoInserido = celula; //Primeira e �ltima.
            return; //Sai da rotina.
        }
        
        //Se continuou, a lista n�o est� vazia. Insere no in�cio da lista.
        celula.prox = this.primeiroInserido; //Ajusta  ponteiro do pr�ximo da nova primeira c�lula.
        this.primeiroInserido.ant = celula; //Ajusta ponteiro do anterior da antiga primeira c�lula.
        this.primeiroInserido = celula;
    }
    
    private void insereNoFinal (Celula celula) {
        if (this.estaVazia()) { //Checa se a lista est� vazia.
            this.primeiroInserido = this.ultimoInserido = celula; //Primeira e �ltima.
            return; //Sai da rotina.
        }
        
        //Se continuou, a lista n�o est� vazia. Insere no final da lista.
        this.ultimoInserido.prox = celula; //Ajusta ponteiro pr�ximo do que era a �ltima.
        celula.ant = this.ultimoInserido; //Ajusta ponteiro anterior.
        this.ultimoInserido = celula;
    }
    
    private Celula retiraDoInicio () {
        if (this.estaVazia()) { //Checa se a lista est� vazia.
            return null; //Sai da rotina.
        }
        
        //Se continuou, a lista n�o est� vazia. Retira do in�cio da lista.
        Celula celula = this.primeiroInserido;
        this.primeiroInserido = this.primeiroInserido.prox; //Ajusta o ponteiro para o novo primeiro.
        this.primeiroInserido.ant = null; //Ajusta ponteiro do anterior do novo primeiro.
        //Retorna antiga primeira c�lula.
        return celula;
    }
    
    private Celula retiraDoFinal () {
        if (this.estaVazia()) { //Checa se a lista est� vazia.
            return null; //Sai da rotina.
        }
        
        //Se continuou, a lista n�o est� vazia. Retira do final da lista.
        Celula celula = this.ultimoInserido; //Antigo �ltimo.
        this.ultimoInserido = this.ultimoInserido.ant; //Ajusta ponteigo da para a nova �ltima c�lula.
        this.ultimoInserido.prox = null; //Ajusta o ponteiro para o p�ximo da nova �ltima c�lula.
        //Retorna antiga primeira c�lula.
        return celula;
    }
    /**
    * O enfileiramento � feito sempre inserindo do final da lista.
    */
    public void enfileira (int numero) {
        Celula celula = new Celula(numero);
        
        this.insereNoFinal(celula);
    }
    /**
    * O desenfileiramento � realizado sempre retirando do in�cio da lista.
    */
    public Celula desenfileira() {
        Celula celula = this.retiraDoInicio();
        
        return celula;
    }
    /**
    * O empilhamento � realizado sempre inserindo no final da lista.
    */
    public void empilha (int numero) {
        Celula celula = new Celula(numero);
        this.insereNoFinal(celula);
    }
    /**
    * O desenpilhamento � realizado sempre retirando do final da lista.
    */
    public Celula desempilha() {
        Celula celula = this.retiraDoFinal();

        return celula;
    }
    /**
    * A impress�o da fila � realizada do in�cio para o final da lista.
    */
    public void imprimeFila() {
        for (Celula celula = this.primeiroInserido; celula != null; celula = celula.prox){
            celula.imprimeCelula();
        }
    }
    /**
    * A impress�o da pilha � realizada do final para o in�cio da lista.
    */
    public void imprimePilha() {
        for (Celula celula = this.ultimoInserido; celula != null; celula = celula.ant){
            celula.imprimeCelula();
        }
    }
}
/**
* Classe principal que exemplificar� o uso de Listas para Filas e Pilhas.
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