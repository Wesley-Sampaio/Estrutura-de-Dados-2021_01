package Resolu��o;

import javax.swing.JOptionPane;
public class Exemplo1 {
    private static final int tam = 30;
    
    public static void main (String args []) {
	    float soma, media, notasSuperioresMedia;
    	float notas [] = new float [tam];
	    int indice;
    	    
        soma = 0;
        for (indice = 0; indice < tam; indice++) {
			notas [indice] = Float.parseFloat (JOptionPane.showInputDialog ("Informe a " + (indice + 1) + "ª nota: "));
		    soma = soma + notas [indice];
    	}
    
	    media = soma / tam;
    
		notasSuperioresMedia = 0;
    	
    	for (indice = 0; indice < tam; indice++) {
        	if (notas [indice] > media) {
            	notasSuperioresMedia++;
        	}
    	}	
    
		JOptionPane.showMessageDialog (null, notasSuperioresMedia + " notas são superiores à média da turma.");
	}
}