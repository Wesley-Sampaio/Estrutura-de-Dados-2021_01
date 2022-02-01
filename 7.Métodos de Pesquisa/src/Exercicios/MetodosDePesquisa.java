package Exercicios;
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
        //monta uma string para mostrar pgtos por mês.
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
        System.out.println("Apartamento: " + numero  + ", morador: " + nome + ", telefone: " + telefone);
    }
}

/**
 * Classe que representa o condomínio que é um array de apartamentos.
 */
class Apartamentos {

    private Apartamento[] apartamentos;
    private int qtdeAptos;
    private int qtdeMaxima;
	public String buscaBinaria;
	
    public Apartamentos(int qtdeMaxima) {
        this.apartamentos = new Apartamento[qtdeMaxima];
        this.qtdeAptos = 0;
        this.qtdeMaxima = qtdeMaxima;
    }

    /**
     * Busca sequencialmente por apartamento de um determinado número.
     *
     * @param numero Número do apartamento.
     * @return Objeto Apartamento encontrado ou null em caso de não encontrar.
     */
    public Apartamento buscaLlinear(String nome) {
        for (int apto = 0; apto < this.qtdeAptos; apto++) {
            if (this.apartamentos[apto].nome == nome) {
                return this.apartamentos[apto];
            }
        }

        return null;
    }

    /**
     * Busca binariamente por apartamento de um determinado número.
     *
     * @param numero Número do apartamento.
     * @return Objeto Apartamento encontrado ou null em caso de não encontrar.
     */
    public Apartamento buscaBinaria(String nome) {
        int inicio = 0;
        int fim = this.apartamentos.length - 1;
        int meio;
        while (inicio <= fim) {
            meio = (inicio + fim) / 2;
            if (this.apartamentos[meio].nome == nome) {
                return this.apartamentos[meio];
            }
            if (nome.compareTo (this.apartamentos[meio].nome) >= 0) {
                fim = meio - 1;
            } else {
                inicio = meio + 1;
            }
        }

        return null;
    }

    public void insere(int numero, String nome, String telefone, boolean taxaPaga[]) throws Exception {
        if (this.qtdeAptos == this.qtdeMaxima) {
            throw new Exception("Quantidade máxima de apartamentos atingina. Não possível inserir novos apartamentos.");
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
            //Limpa da memória o último apto.
            this.apartamentos[this.qtdeAptos] = new Apartamento();
            return true;
        } else {
            return false;
        }
    }

    public void ordenaPeloMetodoDaBolha() {
        for (int dir = this.qtdeAptos - 1; dir > 0; dir--) {
            for (int esq = 0; esq < dir; esq++) {
                if (apartamentos[esq].numero > apartamentos[esq + 1].numero) {
                    this.trocaAptos(esq, esq + 1);
                }
            }
        }
    }

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
                //troca desnecessária...
            } else {
                this.trocaAptos(indiceDoMenor, externo);
            }
        }
    }

    private void trocaAptos(int i, int ii) {
        Apartamento temp = this.apartamentos[i];
        this.apartamentos[i] = this.apartamentos[ii];
        this.apartamentos[ii] = temp;
    }

    public void ordenaPorInsercao() {
        int interno, externo;

        for (externo = 1; externo < this.qtdeAptos; externo++) {
            Apartamento aux = this.apartamentos [externo];
            
            for (interno = externo; interno > 0 && this.apartamentos [interno - 1].numero >= aux.numero; interno--) {
                this.apartamentos [interno] = this.apartamentos [interno - 1];
            }
            
            this.apartamentos [interno] = aux;
        }
    }

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

public class MetodosDePesquisa {
    private static final int tamMax = 8;

    public static void main(String[] args) {
        Apartamentos aptos = new Apartamentos(tamMax);
        boolean[] taxasPagas = {true, true, true, true, true, true, true, true, true, true, true, false};

        try {
            aptos.insere(102, "Maria Chiquinha", "2", taxasPagas);
            aptos.insere(103, "Zé Roela", "3", taxasPagas);
            aptos.insere(104, "John Doe", "4", taxasPagas);
            aptos.insere(201, "Joane Doe", "5", taxasPagas);
            aptos.insere(203, "Bon Diovi", "7", taxasPagas);
            aptos.insere(202, "Little John Armless", "6", taxasPagas);
            aptos.insere(204, "Vitor Faizano", "8", taxasPagas);
            aptos.insere(101, "Anacleto Cebolão", "1", taxasPagas);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        //System.out.println("Apartamentos fora de ordem!");
        //aptos.imprimeApartamentos();

        //aptos.ordenaPeloMetodoDaBolha();
        //aptos.ordenaPorSelecao();
        //aptos.ordenaPorInsercao();
        //System.out.println("Apartamentos em ordem!");
        //aptos.imprimeApartamentos();
        
        //Pesquisa apto.
                    	
	        int resp = 0;
	        
	        do {
	    	    resp = Integer.parseInt (JOptionPane.showInputDialog ("\tMenu de Opções \n0 - Sair  \n1 - busca Binaria\n Informe uma opção: "));

		        switch (resp) {
	    	       	    	            
		            case 1:
		            	String busca = JOptionPane.showInputDialog("Informe o nome para a busca: ");
		            	Apartamento aptoEncontrado = aptos.buscaBinaria(busca.intern());
		    	        if (aptoEncontrado == null) { //Não encontrou.
		    	            JOptionPane.showMessageDialog(null, "O apartamento não foi encontrado!");
		    	        } else { //Encontrou...
		    	            System.out.println ("Encontrou o apartamento!");
		    	            aptoEncontrado.imprimeDadosReduzidos();
		    	        }
	    	            break;
		        }
	    	} while (resp != 0);

        /*
        JOptionPane.showMessageDialog(null, "Excluindo aptos 201 e 204.");
        aptos.exclui(201);
        aptos.exclui(204);

        System.out.println("\nNova configuração dos apartamentos.");
        aptos.imprimeApartamentos();
        */
    }
}