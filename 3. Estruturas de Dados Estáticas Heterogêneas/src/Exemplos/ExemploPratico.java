package Exemplos;

import javax.swing.JOptionPane;

class RegApto {
    public int numero;
    public String nome ;
    public String telefone;
    public boolean taxaPaga;
}

public class ExemploPratico {
    private static final int qtdeAptos = 40;

    public static void main (String args []) {
        RegApto apartamentos [] = new RegApto [qtdeAptos] ;
        int resp = 0;

    	do {
    	    resp = Integer.parseInt (JOptionPane.showInputDialog ("\tMenu de Op��es \n0 - Sair \n1 - Ler dados \n2 - Imprimir apartamentos com taxa em aberto \n Informe uma op��o: "));
    	    /*
        	System.out.println ("\tMenu de Op��es");
    	    System.out.println ("0 - Sair ");
        	System.out.println ("1 - Ler dados");
        	System.out.println ("2 - Imprimir apartamentos com taxa em aberto");
    	    resp = Integer.parseInt (JOptionPane.showInputDialog (null, "Informe uma op��o: "));
    	    */
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
        char taxaPaga;
    
        for (int apto = 0; apto < qtdeAptos; apto++) {
            apartamentos [apto]               = new RegApto();
    	    apartamentos [apto].numero  = Integer.parseInt (JOptionPane.showInputDialog (null, "Informe o n�mero do " + (apto + 1) + "� apartamento: "));
    	    apartamentos [apto].nome     = JOptionPane.showInputDialog (null, "Informe o nome do morador do apartamento n� " + apartamentos [apto].numero + ": ");
    	    apartamentos [apto].telefone = JOptionPane.showInputDialog (null, "Informe o n�mero do telefone do apartamento n� " + apartamentos [apto].numero + ": ");
    	    taxaPaga                                = JOptionPane.showInputDialog (null, "A taxa de condom�nio do apartamento " + apartamentos [apto].numero + " foi paga? (s/n) :").charAt(0);
    	    	    	    
	        if (taxaPaga == 's') {
	            apartamentos [apto].taxaPaga = true;
    	    } else {
	            apartamentos [apto].taxaPaga = false;
	        }
        }
    }

    private static void imprimeAptosInadimplentes (RegApto apartamentos []) {    
        //System.out.println ("\nInadimplentes\n");
    
        for (int apto = 0; apto < qtdeAptos; apto++) {
            if (apartamentos [apto].taxaPaga) {
                //Adimplente! N�o imprime...
            } else {
                //Inadimplente! Imprime...
    	        //System.out.println ("Apartamento: " + apartamentos [apto].numero + ", morador: " + apartamentos [apto].nome + ", telefone: " + apartamentos [apto].telefone);
    	        JOptionPane.showMessageDialog (null, "Inadimplente\nApartamento: " + apartamentos [apto].numero + ", morador: " + apartamentos [apto].nome + ", telefone: " + apartamentos [apto].telefone);
            }
        }
    }
}