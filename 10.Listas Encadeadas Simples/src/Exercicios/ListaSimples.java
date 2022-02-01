package Exercicios;

import javax.swing.JOptionPane;

/**
 * Classe que representa um registro de apartamento.
 */
class Apartamento {
	public int numero;
	public String nome;
	public String telefone;
	public Apartamento prox;

	public Apartamento() {
		this.prox = null;
	}

	public Apartamento(int numero, String nome, String telefone) {
		this.numero = numero;
		this.nome = nome;
		this.telefone = telefone;
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
 * Classe que representa o condomínio que é uma lista de apartamentos.
 */
class Apartamentos {
	private Apartamento primeiro;

	public Apartamentos() {
		this.primeiro = null;
	}

	public boolean estaVazia() {
		return (this.primeiro == null);
	}

	public void insereOrdenado(int numero, String nome, String telefone) {
		Apartamento apartamento = new Apartamento(numero, nome, telefone);
		Apartamento aux, anterior;

		if (this.primeiro == null) {
			this.primeiro = apartamento;
			return;
		}

		if (apartamento.numero < this.primeiro.numero) {
			apartamento.prox = this.primeiro;
			this.primeiro = apartamento;
			return;
		}

		anterior = this.primeiro;
		for (aux = this.primeiro.prox; aux != null && apartamento.numero > aux.numero; aux = aux.prox) {
			anterior = aux;
		}

		anterior.prox = apartamento;
		apartamento.prox = aux;
	}

	public boolean excluiApartamento(int numero) {
		Apartamento apto, aptoAnt = null;

		for (apto = this.primeiro; apto.numero != numero; apto = apto.prox) {
			if (apto != null) {
				aptoAnt = apto;
			}
		}
		
		if (aptoAnt == null) {
		    this.primeiro = this.primeiro.prox;
		    return true; 
		}
		aptoAnt.prox = apto.prox; 
		return true;
	}

	/**
	 * Busca sequencialmente por apartamento de um determinado número.
	 *
	 * @param numero Número do apartamento.
	 * @return Objeto Apartamento encontrado ou null em caso de não encontrar.
	 */
	public Apartamento buscaLinear(int numero) {
		Apartamento apto;

		for (apto = this.primeiro; apto != null; apto = apto.prox) {
			if (apto.numero == numero) {
				return apto;
			}
		}

		return null;

//      Apartamento apto;
//    	apto = this.primeiro;
//    	
//    	while ((apto != null) && (apto.numero != numero)) {
//    		apto = apto.prox;
//    	}
//        return apto;
	}

	public void imprimeApartamentos() {
		for (Apartamento apto = this.primeiro; apto != null; apto = apto.prox) {
			apto.imprimeApartamento();
		}
	}

	public void imprimeDadosReduzidos() {
		for (Apartamento apto = this.primeiro; apto != null; apto = apto.prox) {
			apto.imprimeDadosReduzidos();
		}
	}
}

public class ListaSimples {
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

		System.out.println("Apartamentos em ordem!");
		aptos.imprimeApartamentos();

		// Pesquisa apto.
		do {
			numero = Integer.parseInt(JOptionPane.showInputDialog("Informe o número para a busca:"));
			Apartamento aptoEncontrado = aptos.buscaLinear(numero);
			if (aptoEncontrado == null) { // Não encontrou.
				JOptionPane.showMessageDialog(null, "O apartamento não foi encontrado!");
			} else { // Encontrou...
				JOptionPane.showMessageDialog(null, "Apto: " + aptoEncontrado.numero + "\nNome: " + aptoEncontrado.nome
						+ "\nFone: " + aptoEncontrado.telefone);
			}
			continuaSN = JOptionPane.showInputDialog("Deseja pesquisar outro apartamento? (s/n)").charAt(0);
		} while (continuaSN == 's');

		aptos.excluiApartamento(102);

		System.out.println("Apartamentos em ordem!");
		aptos.imprimeApartamentos();
	}
}
