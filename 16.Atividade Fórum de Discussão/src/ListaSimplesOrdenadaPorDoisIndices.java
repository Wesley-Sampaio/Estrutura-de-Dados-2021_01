import javax.swing.JOptionPane;

/**
 * Classe que representa um registro de apartamento.
 */
class Apartamento {
    public int numero;
    public String nome;
    public String telefone;
    public Apartamento proxNumero;
    public Apartamento proxNome;

    public Apartamento() {
        this.proxNumero = null;
        this.proxNome = null;
    }

    public Apartamento(int numero, String nome, String telefone) {
        this.numero = numero;
        this.nome = nome;
        this.telefone = telefone;
        this.proxNumero = null;
        this.proxNome = null;
    }

    public void imprimeApartamento() {
        System.out.println("Apartamento: " + numero + ", morador: " + nome + ", telefone: " + telefone);
    }

    public void imprimeMorador() {
        System.out.println("Morador: " + nome + ", Apartamento: " + numero + ", telefone: " + telefone);
    }

    public void imprimeDadosReduzidos() {
        System.out.println("Apartamento: " + numero + ", morador: " + nome);
    }
}

/**
 * Classe que representa o condomínio que é uma lista de apartamentos.
 */
class Apartamentos {
    private Apartamento primeiroNumero;
    private Apartamento primeiroNome;

    public Apartamentos() {
        this.primeiroNumero = null;
        this.primeiroNome = null;
    }
    
    public boolean estaVazia () {
        return (this.primeiroNumero == null);
    }
    
    private void insereOrdenadoPorNumero(Apartamento apartamento){
        Apartamento aux, anterior;

        //Checa se o novo deve ser o primeiro por ordem de número.
        if (apartamento.numero < this.primeiroNumero.numero) {
            apartamento.proxNumero = this.primeiroNumero;
            this.primeiroNumero = apartamento;
            return;
        }
        
        //Não é o primeiro.
        //Procura em qual posição deve inserir.
        anterior = this.primeiroNumero;
        for (aux = this.primeiroNumero.proxNumero; aux != null && apartamento.numero > aux.numero; aux = aux.proxNumero) {
            anterior = aux;
        }

        //Ajusta os índices para incluir o novo na ordem correta.
        anterior.proxNumero = apartamento;
        apartamento.proxNumero = aux;
    }

    private void insereOrdenadoPorNome(Apartamento apartamento){
        Apartamento aux, anterior;

        //Checa se o novo deve ser o primeiro por ordem de número.
        if (apartamento.nome.compareTo(this.primeiroNome.nome) < 0) {
            apartamento.proxNome = this.primeiroNome;
            this.primeiroNome = apartamento;
            return;
        }
        
        //Não é o primeiro.
        //Procura em qual posição deve inserir.
        anterior = this.primeiroNome;
        for (aux = this.primeiroNome.proxNome; aux != null && apartamento.nome.compareTo(aux.nome) > 0; aux = aux.proxNome) {
            anterior = aux;
        }

        //Ajusta os índices para incluir o novo na ordem correta.
        anterior.proxNome = apartamento;
        apartamento.proxNome = aux;
    }

    public void insereOrdenado (int numero, String nome, String telefone) {
        Apartamento apartamento = new Apartamento(numero, nome, telefone);
        //Apartamento aux, anterior;

        //A lista está vazia?
        if (this.estaVazia()) {
            this.primeiroNumero = apartamento;
            this.primeiroNome = apartamento;
            return;
        }
        
        //A lista não está vazia.
        //Insere ordenando por número do apto.
        this.insereOrdenadoPorNumero(apartamento);
        //Insere ordenando por nome do morador.
        this.insereOrdenadoPorNome(apartamento);
    }
    
    private boolean excluiAptoDaListaDeNumeros (Apartamento apto, Apartamento aptoAnterior) {
        if (aptoAnterior == null) { //O apto encontrado é o primeiro.
            this.primeiroNumero = this.primeiroNumero.proxNumero; //Elimina o primeiro fazendo primeiro apontar p/ o prox. do primeiro (segundo ou null).
            return true; //Encerra a rotina.
        }

        //Se continuou é pq não é o primeiro!
        aptoAnterior.proxNumero = apto.proxNumero; //Elimina o objeto da lista forçando o anterior apontar para o seu próximo.
        return true; //Encerra a rotina;
    }
    
    private boolean excluiAptoDaListaDeNomes (Apartamento apto) {
        Apartamento aptoAnterior = null;
        String nome = apto.nome;

        for (apto = this.primeiroNome; apto != null && !apto.nome.equals(nome); apto = apto.proxNome) {
            aptoAnterior = apto;
        }

        if (apto == null) { //Não encontrou!
            return false; //Encerra a rotina.
        } else { //Encontrou!
            if (aptoAnterior == null) { //O apto encontrado é o primeiro.
                this.primeiroNome = this.primeiroNome.proxNome; //Elimina o primeiro fazendo primeiro apontar p/ o prox. do primeiro (segundo ou null).
                return true; //Encerra a rotina.
            }
            //Se continuou é pq não é o primeiro!
            aptoAnterior.proxNome = apto.proxNome; //Elimina o objeto da lista forçando o anterior apontar para o seu próximo.
            return true; //Encerra a rotina;
        }
    }

    public boolean excluiApartamento(int numero) {
        Apartamento aptoAnterior = null, apto;

        for (apto = this.primeiroNumero; apto != null && apto.numero != numero; apto = apto.proxNumero) {
            aptoAnterior = apto;
        }

        if (apto == null) { //Não encontrou!
            return false; //Encerra a rotina.
        } else { //Encontrou!
            //Exclui das listas.
            if (this.excluiAptoDaListaDeNumeros(apto, aptoAnterior)) {
                return this.excluiAptoDaListaDeNomes(apto);
            } else {
                return false;
            }
        }
    }

    /**
     * Busca sequencialmente por apartamento de um determinado número.
     *
     * @param numero Número do apartamento.
     * @return Objeto Apartamento encontrado ou null em caso de não encontrar.
     */
    public Apartamento buscaLinear(int numero) {
        Apartamento apto;
        for (apto = this.primeiroNumero; apto != null && apto.numero != numero; apto = apto.proxNumero);
        
        return apto;
    }

    public void imprimeApartamentos() {
        for (Apartamento apto = this.primeiroNumero; apto != null; apto = apto.proxNumero){
            apto.imprimeApartamento();
        }
    }

    public void imprimeMoradores() {
        for (Apartamento apto = this.primeiroNome; apto != null; apto = apto.proxNome){
            apto.imprimeMorador();
        }
    }
    
    public void imprimeDadosReduzidos() {
        for (Apartamento apto = this.primeiroNumero; apto != null; apto = apto.proxNumero){
            apto.imprimeDadosReduzidos();
        }
    }

    public void imprimeDadosReduzidosDoMorador() {
        for (Apartamento apto = this.primeiroNome; apto != null; apto = apto.proxNome){
            apto.imprimeDadosReduzidos();
        }
    }
}

public class ListaSimplesOrdenadaPorDoisIndices {
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

        System.out.println("ANTES DAS EXCLUSÕES!");
        System.out.println("\nApartamentos por ordem de número!");
        aptos.imprimeApartamentos();

        System.out.println("\nApartamentos por ordem de morador!\n");
        aptos.imprimeMoradores();
        
        //Pesquisa apto.
        /*
        do {
            numero = Integer.parseInt(JOptionPane.showInputDialog("Informe o número para a busca:"));
            Apartamento aptoEncontrado = aptos.buscaLinear(numero);
            if (aptoEncontrado == null) { //Não encontrou.
                JOptionPane.showMessageDialog(null, "O apartamento não foi encontrado!");
            } else { //Encontrou...
                JOptionPane.showMessageDialog(null, "Apto: " + aptoEncontrado.numero + "\nNome: " + aptoEncontrado.nome + "\nFone: " + aptoEncontrado.telefone);
            }
            continuaSN = JOptionPane.showInputDialog("Deseja pesquisar outro apartamento? (s/n)").charAt(0);
        } while (continuaSN == 's');
        */
        
        aptos.excluiApartamento (101); 
        aptos.excluiApartamento (102); 
        aptos.excluiApartamento (103); 

        System.out.println("\nAPÓS AS EXCLUSÕES!");
        System.out.println("Apartamentos por ordem de número!\n");
        aptos.imprimeApartamentos();

        System.out.println("\nApartamentos por ordem de morador!\n");
        aptos.imprimeMoradores();
    }
}