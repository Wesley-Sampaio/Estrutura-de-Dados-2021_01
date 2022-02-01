package Filas;

/**
 * Classe que representa um link do TAD (Tipo Abstrato de Dado).
 */
class Celula {
    public int senha;
    public Celula prox;

    public Celula(int senha) {
        this.senha = senha;
        this.prox = null;
    }

    public void imprimeCelula() {
        System.out.println("Senha: " + senha);
    }
}

/**
 * Classe que representa a fila que é uma lista de células representando o TAD FILA (FIFO - First In First Out).
 */
class Fila {
    private Celula inicio;
    private Celula fim;

    public Fila() {
        this.inicio = null;
        this.fim = null;
    }
    
    public boolean estaVazia () {
        return (this.inicio == null);
    }
    /**
    * O enfileiramento é feito sempre inserindo no final da fila.
    */
    public void enfileira (int senha) {
        Celula celula = new Celula(senha);

        if (this.estaVazia()) { //Checa se a fila está vazia.
            this.inicio = this.fim = celula; //Primeira e última.
            return; //Sai da rotina.
        }
        
        //Se continuou, a fila não está vazia. Insere no final da fila.
        this.fim.prox = celula;
        this.fim = celula;
    }
    /**
    * O desenfileiramento é realizado sempre retirando do início da fila.
    */
    public Celula desenfileira() {
        Celula celula = this.inicio;
        
        //Se a lista estiver vazia não tem o que retornar.
        if (this.estaVazia()) {
            return null;
        }
        
        //Retira o primeiro da fila.
        this.inicio = this.inicio.prox;
        return celula;
    }

    public void imprimeCelulas() {
        for (Celula celula = this.inicio; celula != null; celula = celula.prox){
            celula.imprimeCelula();
        }
    }
}

public class Filas {
    public static void main(String[] args) {
        Celula celula;
        Fila fila = new Fila();
        
        @SuppressWarnings("unused")
		char continuaSN = 'n';
        int senha = 1;

        fila.enfileira(senha++);
        fila.enfileira(senha++);
        fila.enfileira(senha++);
        fila.enfileira(senha++);
        fila.enfileira(senha++);
        fila.enfileira(senha++);
        fila.enfileira(senha++);
        fila.enfileira(senha++);

        System.out.println("Fila antes de desenfileirar!");
        fila.imprimeCelulas();
        
        celula = fila.desenfileira();
        System.out.print("Próximo da fila! ");
        celula.imprimeCelula();
        
        celula = fila.desenfileira();
        System.out.print("Próximo da fila! ");
        celula.imprimeCelula();

        System.out.println("Fila após dois desenfileiramento!");
        fila.imprimeCelulas();
    }
}
