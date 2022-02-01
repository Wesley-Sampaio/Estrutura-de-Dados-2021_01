package Resolu��o;

import javax.swing.JOptionPane;

/**
Classe que representa um registro de pessoas.
*/
class RegApto {
    public int numero;
    public String nome ;
    public String telefone;
    public boolean [] taxaPaga;
    
    public RegApto () {
        this.taxaPaga = new boolean [12];
    }
}

public class Exercicio {
    private static final int qtdeAptos = 4;

    public static void main (String args []) {
        RegApto apartamentos [] = new RegApto [qtdeAptos] ;
	    int resp = 0;

    	do {
    	    resp = Integer.parseInt (JOptionPane.showInputDialog ("\tMenu de Op��es \n0 - Sair \n1 - Ler dados \n2 - Imprimir apartamentos com taxa em aberto \n Informe uma op��o: "));

	        switch (resp) {
    	        case 1:
	                leDadosDosApartamentos (apartamentos);
	                break;
	            case 2:
	                imprimeAptosInadimplentes (apartamentos);
    	            break;
	        }
    	} while (resp != 0);
    }
    
    private static void leDadosDosApartamentos (RegApto apartamentos []) {
        for (int apto = 0; apto < qtdeAptos; apto++) {
            apartamentos [apto]               = new RegApto();
    	    apartamentos [apto].numero  = Integer.parseInt (JOptionPane.showInputDialog (null, "Informe o n�mero do " + (apto + 1) + "� apartamento: "));
    	    apartamentos [apto].nome     = JOptionPane.showInputDialog (null, "Informe o nome do morador do apartamento n� " + apartamentos [apto].numero + ": ");
    	    apartamentos [apto].telefone = JOptionPane.showInputDialog (null, "Informe o n�mero do telefone do apartamento n� " + apartamentos [apto].numero + ": ");

    	    lePgtos (apartamentos, apto);
        }
    }
    
    private static void lePgtos (RegApto apartamentos [], int apto) {
        char taxaPaga;
        for (int mes = 0; mes < 12; mes++) {            
    	    taxaPaga                                = JOptionPane.showInputDialog (null, "A taxa de condom�nio do apartamento " + apartamentos [apto].numero + " no m�s " + (mes + 1) + " foi paga? (s/n) :").charAt(0);
    	    	    	    
	        if (taxaPaga == 's') {
	            apartamentos [apto].taxaPaga [mes] = true;
    	    } else {
	            apartamentos [apto].taxaPaga [mes] = false;
	        }
        }
    }

    private static void imprimeAptosInadimplentes (RegApto apartamentos []) {    
        boolean todosOsMesesOk;
        
        for (int apto = 0; apto < qtdeAptos; apto++) {
            todosOsMesesOk = true;
            for (int mes = 0; mes < 12; mes++) {
                if (apartamentos [apto].taxaPaga [mes]) {
                    //Adimplente neste m�s...
                } else {
                    //Inadimplente neste m�s...
                    todosOsMesesOk = false;
                    //break;
                }
            }
            if (todosOsMesesOk) {
                //Adimplente! N�o imprime...
            } else {
                //Inadimplente! Imprime...
    	        JOptionPane.showMessageDialog (null, "Inadimplente\nApartamento: " + apartamentos [apto].numero + ", morador: " + apartamentos [apto].nome + ", telefone: " + apartamentos [apto].telefone);        
    	    }
        }
    }
}