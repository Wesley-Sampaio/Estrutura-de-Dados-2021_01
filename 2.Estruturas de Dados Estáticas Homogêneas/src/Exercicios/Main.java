package Exercicios;
/******************************************************************************

Welcome to GDB Online.
GDB online is an online compiler and debugger tool for C, C++, Python, Java, PHP, Ruby, Perl,
C#, VB, Swift, Pascal, Fortran, Haskell, Objective-C, Assembly, HTML, CSS, JS, SQLite, Prolog.
Code, Compile, Run and Debug online from anywhere in world.

*******************************************************************************/
import java.util.Scanner;

public class Main {
    
public static void main (String[]args) {

    try (Scanner ler = new Scanner (System.in)) {
		float soma, mediaTurma, notasSuperioresMedia;
		float notas[][] = new float[5][30];
		int i, j;
		int discQSuperamMediaEmDuasOuMais[] = new int[30];

		for (j = 0; j <= 29; j++) {
		    discQSuperamMediaEmDuasOuMais[j] = 0;
		}

		for (i = 0; i <= 4; i++) {
		    soma = 0;
		    for (j = 0; j <= 29; j++) {
		        System.out.print ("informe a nota da " + (i + 1) + "ª disciplina do " + (j + 1) + "º: aluno: ");
		        notas[i][j] = ler.nextInt ();
		        soma = soma + notas[i][j];
		    }

		    mediaTurma = soma / 30;
		    notasSuperioresMedia = 0;
		    
		    System.out.println(mediaTurma + " Media");

		    for (j = 0; j <= 29; j++) {
		        if (notas[i][j] > mediaTurma) {
			        notasSuperioresMedia = notasSuperioresMedia + 1;
			        discQSuperamMediaEmDuasOuMais[j] = discQSuperamMediaEmDuasOuMais[j] + 1;
		        }
		    }
		    System.out.println("");
		    System.out.println(notasSuperioresMedia + " notas são superiores à média da turma.\n");
		}
		
		for (j = 0; j <= 29; j++) {
		    if (discQSuperamMediaEmDuasOuMais[j] > 1){
		        System.out.println("\nO discente número "+ (j + 1) +" ficou acima da média em " + discQSuperamMediaEmDuasOuMais [j] + " disciplinas.");
		    }
		    
		}
	}  
}
}
