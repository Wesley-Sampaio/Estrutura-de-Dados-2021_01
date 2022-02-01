package Resolu��o;

import javax.swing.JOptionPane;
public class Exemplo2 {
    private static final int discentes = 30;
    private static final int disciplinas = 5;
    
    public static void main (String args []) {
	    float soma, mediaTurma;
	    int notasSuperioresMedia;
    	float notas [][] = new float [disciplinas][discentes];
	    int i, j;
        int discQSuperamMediaEmDuasOuMais [] = new int [discentes]; //qtde de vezes por discente.

        //Deve-se iniciar a matriz discQSuperamMediaEmDuasOuMais com zero.
        for (j = 0; j < discentes; j++) { //Loop dos discentes
            discQSuperamMediaEmDuasOuMais [j] = 0;
        }
    	    
        for (i = 0; i < disciplinas; i++) { //Loop das disciplinas
            soma = 0;
            for (j = 0; j < discentes; j++) {
			    notas [i][j] = Float.parseFloat (JOptionPane.showInputDialog ("Informe a nota da " + (i + 1) + "ª disciplina do " + (j + 1) + "º aluno: "));
    		    soma = soma + notas [i][j];
        	}
    
	        mediaTurma = soma / discentes;
    		notasSuperioresMedia = 0;
    	
            for (j = 0; j < discentes; j++) { //Loop dos discentes
            	if (notas [i][j] > mediaTurma) {
                	notasSuperioresMedia++;
                    discQSuperamMediaEmDuasOuMais [j]++;
            	}
        	}
    
		    JOptionPane.showMessageDialog (null, notasSuperioresMedia + " notas são superiores à média da turma.");
		}

        //Imprime os discentes com notas acima da média da turma em mais de uma disciplina.
        for (j = 0; j < discentes; j++) { //Loop dos discentes que superaram a média.
            if (discQSuperamMediaEmDuasOuMais [j] > 1) {//Superou a média em + de 1 disciplina?
                JOptionPane.showMessageDialog (null, "O discente número " + (j + 1) + " ficou acima da média em " + discQSuperamMediaEmDuasOuMais [j] + " disciplinas.");
            }
        }
    }
}