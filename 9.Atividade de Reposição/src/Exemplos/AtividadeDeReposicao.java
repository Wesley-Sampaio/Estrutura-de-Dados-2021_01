package Exemplos;

import javax.swing.JOptionPane;

/**
 * Classe que representa um registro de apartamento.
 */
class Apartamento {
    public int numero;
    public String nome;
    public String telefone;
    public boolean[] taxaPaga;

    public Apartamento() {
        this.taxaPaga = new boolean[12];
    }

    public Apartamento(int numero, String nome, String telefone, boolean taxaPaga[]) {
        this.numero = numero;
        this.nome = nome;
        this.telefone = telefone;
        this.taxaPaga = taxaPaga;
    }

    public void imprimeApartamento() {
        //monta uma string para mostrar pgtos por m?s.
        String mesesPagos = "\nJAN  FEV  MAR  ABR  MAI  JUN  JUL  AGO  SET  OUT  NOV  DEZ\n";

        for (int mes = 0; mes < 12; mes++) {
            if (this.taxaPaga[mes]) {
                mesesPagos += "PG   ";
            } else {
                mesesPagos += "NP   ";
            }
        }

        System.out.println("Apartamento: " + numero + ", morador: " + nome + ", telefone: " + telefone + mesesPagos);
    }

    public void imprimeDadosReduzidos() {
        System.out.println("Apartamento: " + numero + ", morador: " + nome);
    }
}

/**
* Essa classe servir? como ?ndice para a ordena??o 
* do array de Apartamentos pelo nome do morador.
*/
class IndiceMorador {
    public String nome;
    public int indice;
    
    public IndiceMorador (String nome, int indice) {
        this.nome = nome;
        this.indice = indice;
    }
}

/**
 * Classe que representa o condom?nio que ? um array de apartamentos.
 */
class Apartamentos {
    private Apartamento[] apartamentos;
    private IndiceMorador [] indiceMoradores;
    private int qtdeAptos;
    private int qtdeMaxima;

    public Apartamentos(int qtdeMaxima) {
        this.apartamentos = new Apartamento [qtdeMaxima];
        this.indiceMoradores = new IndiceMorador [qtdeMaxima];
        this.qtdeAptos = 0;
        this.qtdeMaxima = qtdeMaxima;
    }

    public void insere(int numero, String nome, String telefone, boolean taxaPaga[]) throws Exception {
        if (this.qtdeAptos == this.qtdeMaxima) {
            throw new Exception("Quantidade m?xima de apartamentos atingina. N?o poss?vel inserir novos apartamentos.");
        }

        this.apartamentos[this.qtdeAptos] = new Apartamento(numero, nome, telefone, taxaPaga);
        this.qtdeAptos++;
    }

    public boolean exclui(int numero) {
        int aptoEncontrado = 0;
        boolean achou = false;

        for (int apto = 0; apto < this.qtdeAptos && !achou; apto++) {
            if (this.apartamentos[apto].numero == numero) {
                aptoEncontrado = apto;
                achou = true;
            }
        }

        if (achou) {
            for (int apto = aptoEncontrado; apto < (this.qtdeAptos - 1); apto++) {
                this.apartamentos[apto] = this.apartamentos[apto + 1];
            }
            this.qtdeAptos--;
            //Limpa da mem?ria o ?ltimo apto.
            this.apartamentos[this.qtdeAptos] = new Apartamento();
            return true;
        } else {
            return false;
        }
    }

    /**
     * Busca sequencialmente por apartamento de um determinado n?mero.
     *
     * @param numero N?mero do apartamento.
     * @return Objeto Apartamento encontrado ou null em caso de n?o encontrar.
     */
    public Apartamento buscaLlinear(int numero) {
        for (int apto = 0; apto < this.qtdeAptos; apto++) {
            if (this.apartamentos[apto].numero == numero) {
                return this.apartamentos[apto];
            }
        }

        return null;
    }

    /**
     * Busca binariamente por apartamento de um determinado n?mero.
     *
     * @param numero N?mero do apartamento.
     * @return Objeto Apartamento encontrado ou null em caso de n?o encontrar.
     */
    public Apartamento buscaBinaria(int numero) {
        int inicio = 0;
        int fim = this.apartamentos.length - 1;
        int meio;
        while (inicio <= fim) {
            meio = (inicio + fim) / 2;
            if (this.apartamentos[meio].numero == numero) {
                return this.apartamentos[meio];
            }
            if (numero < this.apartamentos[meio].numero) {
                fim = meio - 1;
            } else {
                inicio = meio + 1;
            }
        }

        return null;
    }
    /**
    * Ordena primeiramete o vetor apartamentos e 
    * depois o vetor de ?ndice de nomes
    **/
    public void ordenaPeloMetodoDaBolha() {
        for (int dir = this.qtdeAptos - 1; dir > 0; dir--) {
            for (int esq = 0; esq < dir; esq++) {
                if (apartamentos[esq].numero > apartamentos[esq + 1].numero) {
                    this.trocaAptos(esq, esq + 1);
                }
            }
        }
        
        //Cria o indice ordenado por nome
        this.ordenaAptosPorNome();
    }
    /**
    * Ordena primeiramete o vetor apartamentos e 
    * depois o vetor de ?ndice de nomes
    **/
    public void ordenaPorSelecao() {
        int indiceDoMenor, interno;

        for (int externo = 0; externo < this.qtdeAptos - 1; externo++) {
            indiceDoMenor = externo;
            for (interno = externo + 1; interno < this.qtdeAptos; interno++) {
                if (apartamentos[interno].numero < apartamentos[indiceDoMenor].numero) {
                    indiceDoMenor = interno;
                }
            }

            if (indiceDoMenor == externo) {
                //troca desnecess?ria...
            } else {
                this.trocaAptos(indiceDoMenor, externo);
            }
        }
        
        //Cria o indice ordenado por nome
        this.ordenaAptosPorNome();
    }

    private void trocaAptos(int i, int ii) {
        Apartamento temp = this.apartamentos[i];
        this.apartamentos[i] = this.apartamentos[ii];
        this.apartamentos[ii] = temp;
    }
    /**
    * Ordena primeiramete o vetor apartamentos e 
    * depois o vetor de ?ndice de nomes
    **/
    public void ordenaPorInsercao() {
        int interno, externo;

        for (externo = 1; externo < this.qtdeAptos; externo++) {
            Apartamento aux = this.apartamentos [externo];
            
            for (interno = externo; interno > 0 && this.apartamentos [interno - 1].numero >= aux.numero; interno--) {
                this.apartamentos [interno] = this.apartamentos [interno - 1];
            }
            
            this.apartamentos [interno] = aux;
        }
        
        //Cria o indice ordenado por nome
        this.ordenaAptosPorNome();
    }
    /**
    * M?todos para ordena??o e busca por nome.
    */
    private void ordenaAptosPorNome () {
        this.geraIndiceDeMoradores();
        this.ordenaNomesPorInsercao();
    }
    /*
    * Gera o vetor indiceMoradores.
    */
    private void geraIndiceDeMoradores () {
        //Deve ser implementado.
    }
    /*
    * Ordena pelo M?todo de Inser??o o vetor indiceMoradores.
    */
    private void ordenaNomesPorInsercao () {
        //Deve ser implementado.
    }
    /**
    * Busca pelo nome no ?ndice de nomes.
    *
    * return Apartamento Se encontrar retorna o objeto apartamentos recuperado do vetor de apartamentos.
    *                                 Se n?o encontrar retorna null.
    */
    public Apartamento buscaBinariaPorNome (String nome) {
		return null;
        //Deve ser implementado.
        //Deve usar o m?todo buscaBinariaRecursivaPorNome (String nome, int ini, int fim).
    }
    /**
    * Busca o nome no ?ndice de nomes. 
    *
    * return int  Se encontrar retorna o ?ndice para o array de apartamentos.
     *                Se n?o encontrar retorna um ?ndice inv?lido (quantidade total de apartamentos).
    */
    @SuppressWarnings("unused")
	private int buscaBinariaRecursivaPorNome (String nome, int ini, int fim) {
		return fim;
        //Deve ser implementado.
        //Este m?todo deve ser recursivo, ou seja, no lugar de um la?o de repeti??o implementar uma fun??o recursiva.
    }
    //Implementei este m?todo para auxiliar o discente nas suas checagens.
    public void imprimeAptosOrdenadosPorNome () {
        for (int i = 0; i < this.qtdeAptos; i++) {
            this.apartamentos [indiceMoradores [i].indice].imprimeDadosReduzidos();
        }
    }
    /**
    Fim dos m?todos para ordena??o e busca por nome.
    */
    public void imprimeApartamentos() {
        for (int apto = 0; apto < this.qtdeAptos; apto++) {
            this.apartamentos[apto].imprimeApartamento();
        }
    }

    public void imprimeDadosReduzidos() {
        for (int apto = 0; apto < this.qtdeAptos; apto++) {
            this.apartamentos[apto].imprimeDadosReduzidos();
        }
    }
}

public class AtividadeDeReposicao {
    private static final int tamMax = 8;

    public static void main(String[] args) {
        Apartamentos aptos = new Apartamentos(tamMax);
        boolean[] taxasPagas = {true, true, true, true, true, true, true, true, true, true, true, false};
        
        char continuaSN = 'n';
        String nome;

        try {
            aptos.insere(102, "Maria Chiquinha", "2", taxasPagas);
            aptos.insere(103, "Z? Roela", "3", taxasPagas);
            aptos.insere(104, "John Doe", "4", taxasPagas);
            aptos.insere(201, "Joane Doe", "5", taxasPagas);
            aptos.insere(203, "Bon Diovi", "7", taxasPagas);
            aptos.insere(202, "Little John Armless", "6", taxasPagas);
            aptos.insere(204, "Vitor Faizano", "8", taxasPagas);
            aptos.insere(101, "Anacleto Cebol?o", "1", taxasPagas);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        System.out.println("Apartamentos fora de ordem!");
        aptos.imprimeApartamentos();

        //aptos.ordenaPeloMetodoDaBolha();
        //aptos.ordenaPorSelecao();
        aptos.ordenaPorInsercao();
        System.out.println("Apartamentos em ordem!");
        aptos.imprimeApartamentos();
        //Esta parte serve para o discente testar se o seu c?digo de ordena??o t? ok.
        System.out.println("Apartamentos por ordem de morador!");
        aptos.imprimeAptosOrdenadosPorNome();
        
        //Pesquisa apto por nome.
        //Eu deixei esta parte totalmente pronta para o discente poder testar o c?digo de busca que implementar.
        do {
            nome = JOptionPane.showInputDialog("Informe o nome para a busca:");
            Apartamento aptoEncontrado = aptos.buscaBinariaPorNome(nome);
            if (aptoEncontrado == null) { //N?o encontrou.
                JOptionPane.showMessageDialog(null, "O apartamento n?o foi encontrado!");
            } else { //Encontrou...
                JOptionPane.showMessageDialog (null, "Apto: " + aptoEncontrado.numero + "\nFone: " + aptoEncontrado.telefone);
            }
            continuaSN = JOptionPane.showInputDialog("Deseja pesquisar outro morador? (s/n)").charAt(0);
        } while (continuaSN == 's');
    }
}
