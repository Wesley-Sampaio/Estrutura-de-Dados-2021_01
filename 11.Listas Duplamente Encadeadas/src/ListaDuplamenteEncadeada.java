import javax.swing.JOptionPane;

/**
 * Classe que representa um registro de apartamento.
 */
class Apartamento {
    public int numero;
    public String nome;
    public String telefone;
    public Apartamento ant;
    public Apartamento prox;

    public Apartamento() {
        this.ant = null;
        this.prox = null;
    }

    public Apartamento(int numero, String nome, String telefone) {
        this.numero = numero;
        this.nome = nome;
        this.telefone = telefone;
        this.ant = null;
        this.prox = null;
    }

    public void imprimeApartamento() {
        System.out.println("Apartamento: " + numero + ", morador: " + nome + ", telefone: " + telefone);
    }

    public void imprimeDadosReduzidos() {
        System.out.println("Apartamento: " + numero + ", morador: " + nome);
    }
}

/**
 * Classe que representa o condomínio que é uma lista de apartamentos duplamente encadeada.
 */
class Apartamentos {
    private Apartamento primeiro;
    private Apartamento ultimo;

    public Apartamentos() {
        this.primeiro = null;
        this.ultimo = null;
    }
    
    public boolean estaVazia () {
        return (this.primeiro == null);
    }

    public void insereOrdenado (int numero, String nome, String telefone) {
        Apartamento apartamento = new Apartamento(numero, nome, telefone);
        Apartamento aux, anterior;

        if (this.estaVazia()) { //Checa se a lista está vazia.
            this.primeiro = this.ultimo = apartamento; //Primeiro e último.
            return; //Sai da rotina.
        }
        
        //Se continuou, a lista não está vazia.
        if (apartamento.numero < this.primeiro.numero) { //O novo apto. é o primeiro?
            apartamento.prox = this.primeiro;
            this.primeiro.ant = apartamento;
            this.primeiro = apartamento;
            return; //Sai da rotina.
        }
        
        //Se continuou é porque não é o único e nem o primeiro da lista.
        anterior = this.primeiro;
        for (aux = this.primeiro.prox; aux != null && apartamento.numero > aux.numero; aux = aux.prox) {
            anterior = aux;
        }
                
        //Encontrou a posição para inserir o novo apartamento.
        anterior.prox = apartamento;
        apartamento.ant = anterior;
        apartamento.prox = aux;
        
        //Verifica seu o novo apto. é o último da lista.
        if (apartamento.prox == null) {
            this.ultimo = apartamento; //Sé é o último ajusta a referência ao último.
        } else {
            //Se não for o último aux deve apontar o novo apto como anterior.
            aux.ant = apartamento;
        }
    }

    public boolean excluiApartamento(int numero) {
        Apartamento aptoAnterior = null, apto;
        
        //Se a lista estiver vazia não tem o que procurar.
        if (this.estaVazia()) {
            return false;
        }

        //Busca o apto...
        for (apto = this.primeiro; apto != null && apto.numero != numero; apto = apto.prox) {
            aptoAnterior = apto;
        }

        if (apto == null) { //Não encontrou!
            return false;
        } 
        
        //Encontrou!
        if (aptoAnterior == null) { //O apto encontrado é o primeiro da lista.
            this.primeiro = apto.prox; //Elimina o primeiro fazendo primeiro apontar p/ o próximo apto..
            //Checa se o novo primeiro ficou nulo (se a lista estivesse comente com um apto.).
            if (this.primeiro == null) {
                //Se primeiro for nulo a lista ficou vazia e o último deve ser ajutado também.
                this.ultimo = this.primeiro;
            } else {
                //Se primeiro não ficou nulo, então existe pelo meno um na lista ainda e o ponteiro de anterior desse primeiro deve apontar para nulo.
                this.primeiro.ant = null;
            }
            return true; //Encerra a rotina com sucesso.
        }
        
        //Se continuou é pq não é o primeiro!
        aptoAnterior.prox = apto.prox; //Elimina o objeto da lista forçando o anterior a apontar para o seu próximo. Mas falta ajustar o subsequente.
        //Verifica seu o apto. encontrado é o último da lista.
        if (apto.prox == null) {
            this.ultimo = aptoAnterior; //Se é o último ajusta a referência do último para o anterior.
        }  else {
            //Não é o último.
            //É necessário que o sucessor do encontrado aponte para o seu antecessor para completar sua exclusão.
            apto.prox.ant = aptoAnterior;
        }
        
        return true; //Encerra a rotina;
    }

    /**
     * Busca sequencialmente por apartamento de um determinado número.
     *
     * @param numero Número do apartamento.
     * @return Objeto Apartamento encontrado ou null em caso de não encontrar.
     */
    public Apartamento buscaLinear(int numero) {
        Apartamento apto;
        for (apto = this.primeiro; apto != null && apto.numero != numero; apto = apto.prox);
        
        return apto;
    }

    public void imprimeApartamentos() {
        for (Apartamento apto = this.primeiro; apto != null; apto = apto.prox){
            apto.imprimeApartamento();
        }
    }

    public void imprimeApartamentosEmOrdemDescendente() {
        for (Apartamento apto = this.ultimo; apto != null; apto = apto.ant){
            apto.imprimeApartamento();
        }
    }

    public void imprimeDadosReduzidos() {
        for (Apartamento apto = this.primeiro; apto != null; apto = apto.prox){
            apto.imprimeDadosReduzidos();
        }
    }
}

public class ListaDuplamenteEncadeada {
    @SuppressWarnings("unused")
	private static final int tamMax = 8;

    @SuppressWarnings("unused")
	public static void main(String[] args) {
        Apartamentos aptos = new Apartamentos();
        
        char continuaSN = 'n';
        int numero;

        try {
            aptos.insereOrdenado(102, "Maria Chiquinha", "2");
            aptos.insereOrdenado(103, "Zé Roela", "3");
            aptos.insereOrdenado(104, "John Doe", "4");
            aptos.insereOrdenado(201, "Joane Doe", "5");
            aptos.insereOrdenado(203, "Bon Diovi", "7");
            aptos.insereOrdenado(202, "Little John Armless", "6");
            aptos.insereOrdenado(204, "Vitor Faizano", "8");
            aptos.insereOrdenado(101, "Anacleto Cebolão", "1");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        System.out.println("Apartamentos em ordem ascendente!");
        aptos.imprimeApartamentos();

        System.out.println("Apartamentos em ordem descendente!");
        aptos.imprimeApartamentosEmOrdemDescendente();
        
        //Pesquisa apto.
/*        do {
            numero = Integer.parseInt(JOptionPane.showInputDialog("Informe o número para a busca: "));
            Apartamento aptoEncontrado = aptos.buscaLinear(numero);
            if (aptoEncontrado == null) { //Não encontrou.
                JOptionPane.showMessageDialog(null, "O apartamento não foi encontrado!");
            } else { //Encontrou...
                JOptionPane.showMessageDialog(null, "Apto: " + aptoEncontrado.numero + "\nNome: " + aptoEncontrado.nome + "\nFone: " + aptoEncontrado.telefone);
            }
            continuaSN = JOptionPane.showInputDialog("Deseja pesquisar outro apartamento? (s/n)").charAt(0);
        } while (continuaSN == 's');*/
        
        aptos.excluiApartamento (102);
        aptos.excluiApartamento (104);
        aptos.excluiApartamento (101);
        aptos.excluiApartamento (204);

        System.out.println("Apartamentos em ordem ascendente após as exclusões!");
        aptos.imprimeApartamentos();

        System.out.println("Apartamentos em ordem descendente após a exclusões!");
        aptos.imprimeApartamentosEmOrdemDescendente();
    }
}
