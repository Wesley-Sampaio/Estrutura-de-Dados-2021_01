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
        //monta uma string para mostrar pgtos por mÃªs.
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
 * Classe que representa o condomÃ­nio que Ã© um array de apartamentos.
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
     * Busca por apartamento de um determinado nÃºmero.
     *
     * @param numero NÃºmero do apartamento.
     * @return Objeto Apartamento encontrado ou null em caso de nÃ£o encontrar.
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
            throw new Exception("Quantidade mÃ¡xima de apartamentos atingina. NÃ£o possÃ­vel inserir novos apartamentos.");
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
            //Limpa da memÃ³ria o Ãºltimo apto.
            this.apartamentos[this.qtdeAptos] = new Apartamento();
            return true;
        } else {
            return false;
        }
    }

    public void ordenaPeloMetodoCompareTo() {
    	int cicloExterno = 1, cicloInterno;

		// Vou imprimir antes da troca e a cada interação para mostrar a evolução.
		System.out.println("Antes de iniciar a ordenação!");

		for (int i = 0; i < apartamentos.length; ++i) {
			System.out.println("Passou pelo laço mais externo. Iteração nº " + cicloExterno++ + "\n");
			cicloInterno = 1;

			for (int j = i + 1; j < apartamentos.length; ++j) {
				System.out.println("\nInício do " + cicloInterno + "º ciclo interno.");

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
				// Imprimido a cada iteração para mostrar a evolução.
				System.out.println("Final do " + cicloInterno++ + "º ciclo interno: \n");
				this.imprimeDadosReduzidos();
			}
		}

		System.out.println("Encerrou a ordenação!");
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
			// aptos.insere(103, "Zé Roela", "3", taxasPagas);
			// aptos.insere(104, "John Doe", "4", taxasPagas);
			aptos.insere(201, "Joane Doe", "5", taxasPagas);
			// aptos.insere(203, "Bon Diovi", "7", taxasPagas);
			aptos.insere(202, "Little John Armless", "6", taxasPagas);
			aptos.insere(101, "Anacleto Cebolão", "1", taxasPagas);
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
		 * (aptoEncontrado == null) { //Não encontrou.
		 * JOptionPane.showMessageDialog(null, "O apartamento 201 não foi encontrado!");
		 * } else { //Encontrou... aptoEncontrado.imprimeApartamento(); }
		 * 
		 * JOptionPane.showMessageDialog(null, "Excluindo aptos 201 e 204.");
		 * aptos.exclui(201); aptos.exclui(204);
		 * 
		 * System.out.println("\nNova configuração dos apartamentos.");
		 * aptos.imprimeApartamentos();
		 */
	}
}