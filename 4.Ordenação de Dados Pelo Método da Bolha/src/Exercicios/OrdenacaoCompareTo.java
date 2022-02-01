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
        //JOptionPane.showMessageDialog(null, "Apartamento: " + numero + ", morador: " + nome + ", telefone: " + telefone + mesesPagos);
    }

    public void imprimeDadosReduzidos() {
        System.out.println("Apartamento: " + numero + ", morador: " + nome);
    }
}

/**
 * Classe que representa o condomínio que é um array de apartamentos.
 */
class Apartamentos {

    private Apartamento[] apartamentos;
    private int qtdeAptos;
    private int qtdeMaxima;

    public Apartamentos(int qtdeMaxima) {
        this.apartamentos = new Apartamento[qtdeMaxima];
        this.qtdeAptos = 0;
        this.qtdeMaxima = qtdeMaxima;
    }

    /**
     * Busca por apartamento de um determinado número.
     *
     * @param numero Número do apartamento.
     * @return Objeto Apartamento encontrado ou null em caso de não encontrar.
     */
    public Apartamento busca(int numero) {
        for (int apto = 0; apto < this.qtdeAptos; apto++) {
            if (this.apartamentos[apto].numero == numero) {
                return this.apartamentos[apto];
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

    public void ordenaPeloMetodoCompareTo() {
    	int cicloExterno = 1, cicloInterno;

		// Vou imprimir antes da troca e a cada intera��o para mostrar a evolu��o.
		System.out.println("Antes de iniciar a ordena��o!");

		for (int i = 0; i < apartamentos.length; ++i) {
			System.out.println("Passou pelo la�o mais externo. Itera��o n� " + cicloExterno++ + "\n");
			cicloInterno = 1;

			for (int j = i + 1; j < apartamentos.length; ++j) {
				System.out.println("\nIn�cio do " + cicloInterno + "� ciclo interno.");

				if (apartamentos[i].nome.compareTo(apartamentos[j].nome) > 0) {
					System.out.println("\nAntes da troca.");
					System.out.println("Apto 1: ");
					this.apartamentos[i].imprimeDadosReduzidos();
					System.out.println("Apto 2: ");
					this.apartamentos[i + 1].imprimeDadosReduzidos();
					//
					this.trocaAptos(i, j);
					//
					System.out.println("\nDepois da troca.");
					System.out.println("Apto 1: ");
					this.apartamentos[i].imprimeDadosReduzidos();
					System.out.println("Apto 2: ");
					this.apartamentos[i + 1].imprimeDadosReduzidos();
				}
				// Imprimido a cada itera��o para mostrar a evolu��o.
				System.out.println("Final do " + cicloInterno++ + "� ciclo interno: \n");
				this.imprimeDadosReduzidos();
			}
		}

		System.out.println("Encerrou a ordena��o!");
	}

	private void trocaAptos(int i, int j) {
		String temp = apartamentos[i].nome;
		apartamentos[i].nome = apartamentos[j].nome;
		apartamentos[j].nome = temp;
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

public class OrdenacaoCompareTo {
	private static final int tamMax = 4;

	public static void main(String[] args) {
		Apartamentos aptos = new Apartamentos(tamMax);
		boolean[] taxasPagas = { true, true, true, true, true, true, true, true, true, true, true, false };

		try {
			aptos.insere(102, "Maria Chiquinha", "2", taxasPagas);
			// aptos.insere(103, "Z� Roela", "3", taxasPagas);
			// aptos.insere(104, "John Doe", "4", taxasPagas);
			aptos.insere(201, "Joane Doe", "5", taxasPagas);
			// aptos.insere(203, "Bon Diovi", "7", taxasPagas);
			aptos.insere(202, "Little John Armless", "6", taxasPagas);
			aptos.insere(101, "Anacleto Cebol�o", "1", taxasPagas);
			// aptos.insere(204, "Vitor Faizano", "8", taxasPagas);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}

		System.out.println("Apartamentos fora de ordem!");
		aptos.imprimeApartamentos();
		
		aptos.ordenaPeloMetodoCompareTo();
		System.out.println("\nApartamentos em ordem!\n");
		aptos.imprimeApartamentos();

		/*
		 * //Pesquisa apto. Apartamento aptoEncontrado = aptos.busca(201); if
		 * (aptoEncontrado == null) { //N�o encontrou.
		 * JOptionPane.showMessageDialog(null, "O apartamento 201 n�o foi encontrado!");
		 * } else { //Encontrou... aptoEncontrado.imprimeApartamento(); }
		 * 
		 * JOptionPane.showMessageDialog(null, "Excluindo aptos 201 e 204.");
		 * aptos.exclui(201); aptos.exclui(204);
		 * 
		 * System.out.println("\nNova configura��o dos apartamentos.");
		 * aptos.imprimeApartamentos();
		 */
	}
}