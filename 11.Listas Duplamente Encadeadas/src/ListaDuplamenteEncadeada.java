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
 * Classe que representa o condom�nio que � uma lista de apartamentos duplamente encadeada.
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

        if (this.estaVazia()) { //Checa se a lista est� vazia.
            this.primeiro = this.ultimo = apartamento; //Primeiro e �ltimo.
            return; //Sai da rotina.
        }
        
        //Se continuou, a lista n�o est� vazia.
        if (apartamento.numero < this.primeiro.numero) { //O novo apto. � o primeiro?
            apartamento.prox = this.primeiro;
            this.primeiro.ant = apartamento;
            this.primeiro = apartamento;
            return; //Sai da rotina.
        }
        
        //Se continuou � porque n�o � o �nico e nem o primeiro da lista.
        anterior = this.primeiro;
        for (aux = this.primeiro.prox; aux != null && apartamento.numero > aux.numero; aux = aux.prox) {
            anterior = aux;
        }
                
        //Encontrou a posi��o para inserir o novo apartamento.
        anterior.prox = apartamento;
        apartamento.ant = anterior;
        apartamento.prox = aux;
        
        //Verifica seu o novo apto. � o �ltimo da lista.
        if (apartamento.prox == null) {
            this.ultimo = apartamento; //S� � o �ltimo ajusta a refer�ncia ao �ltimo.
        } else {
            //Se n�o for o �ltimo aux deve apontar o novo apto como anterior.
            aux.ant = apartamento;
        }
    }

    public boolean excluiApartamento(int numero) {
        Apartamento aptoAnterior = null, apto;
        
        //Se a lista estiver vazia n�o tem o que procurar.
        if (this.estaVazia()) {
            return false;
        }

        //Busca o apto...
        for (apto = this.primeiro; apto != null && apto.numero != numero; apto = apto.prox) {
            aptoAnterior = apto;
        }

        if (apto == null) { //N�o encontrou!
            return false;
        } 
        
        //Encontrou!
        if (aptoAnterior == null) { //O apto encontrado � o primeiro da lista.
            this.primeiro = apto.prox; //Elimina o primeiro fazendo primeiro apontar p/ o pr�ximo apto..
            //Checa se o novo primeiro ficou nulo (se a lista estivesse comente com um apto.).
            if (this.primeiro == null) {
                //Se primeiro for nulo a lista ficou vazia e o �ltimo deve ser ajutado tamb�m.
                this.ultimo = this.primeiro;
            } else {
                //Se primeiro n�o ficou nulo, ent�o existe pelo meno um na lista ainda e o ponteiro de anterior desse primeiro deve apontar para nulo.
                this.primeiro.ant = null;
            }
            return true; //Encerra a rotina com sucesso.
        }
        
        //Se continuou � pq n�o � o primeiro!
        aptoAnterior.prox = apto.prox; //Elimina o objeto da lista for�ando o anterior a apontar para o seu pr�ximo. Mas falta ajustar o subsequente.
        //Verifica seu o apto. encontrado � o �ltimo da lista.
        if (apto.prox == null) {
            this.ultimo = aptoAnterior; //Se � o �ltimo ajusta a refer�ncia do �ltimo para o anterior.
        }  else {
            //N�o � o �ltimo.
            //� necess�rio que o sucessor do encontrado aponte para o seu antecessor para completar sua exclus�o.
            apto.prox.ant = aptoAnterior;
        }
        
        return true; //Encerra a rotina;
    }

    /**
     * Busca sequencialmente por apartamento de um determinado n�mero.
     *
     * @param numero N�mero do apartamento.
     * @return Objeto Apartamento encontrado ou null em caso de n�o encontrar.
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
            aptos.insereOrdenado(103, "Z� Roela", "3");
            aptos.insereOrdenado(104, "John Doe", "4");
            aptos.insereOrdenado(201, "Joane Doe", "5");
            aptos.insereOrdenado(203, "Bon Diovi", "7");
            aptos.insereOrdenado(202, "Little John Armless", "6");
            aptos.insereOrdenado(204, "Vitor Faizano", "8");
            aptos.insereOrdenado(101, "Anacleto Cebol�o", "1");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        System.out.println("Apartamentos em ordem ascendente!");
        aptos.imprimeApartamentos();

        System.out.println("Apartamentos em ordem descendente!");
        aptos.imprimeApartamentosEmOrdemDescendente();
        
        //Pesquisa apto.
/*        do {
            numero = Integer.parseInt(JOptionPane.showInputDialog("Informe o n�mero para a busca: "));
            Apartamento aptoEncontrado = aptos.buscaLinear(numero);
            if (aptoEncontrado == null) { //N�o encontrou.
                JOptionPane.showMessageDialog(null, "O apartamento n�o foi encontrado!");
            } else { //Encontrou...
                JOptionPane.showMessageDialog(null, "Apto: " + aptoEncontrado.numero + "\nNome: " + aptoEncontrado.nome + "\nFone: " + aptoEncontrado.telefone);
            }
            continuaSN = JOptionPane.showInputDialog("Deseja pesquisar outro apartamento? (s/n)").charAt(0);
        } while (continuaSN == 's');*/
        
        aptos.excluiApartamento (102);
        aptos.excluiApartamento (104);
        aptos.excluiApartamento (101);
        aptos.excluiApartamento (204);

        System.out.println("Apartamentos em ordem ascendente ap�s as exclus�es!");
        aptos.imprimeApartamentos();

        System.out.println("Apartamentos em ordem descendente ap�s a exclus�es!");
        aptos.imprimeApartamentosEmOrdemDescendente();
    }
}
