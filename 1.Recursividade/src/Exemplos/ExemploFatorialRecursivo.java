package Exemplos;

import javax.swing.JOptionPane;
public class ExemploFatorialRecursivo {
    public static void main (String args []) {
	    int fat, n;
	
		n = Integer.parseInt(JOptionPane.showInputDialog("Informe o número a ser calculado: "));
		fat = fatorialRecursivo (n);
		
		JOptionPane.showMessageDialog (null, "O fatorial de " + n + " é: " + fat);
	}
	static int fatorialRecursivo (int n) {
	    int fat;
	    
	    if (n == 0) {
	        fat = 1;
	    }  else {
	        fat = n * fatorialRecursivo (n - 1);
	    } 
	    
	    return fat;
	}
}