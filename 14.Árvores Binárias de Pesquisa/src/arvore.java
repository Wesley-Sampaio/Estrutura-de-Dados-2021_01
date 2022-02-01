import java.io.*;
import java.util.*;               // para Stack class
////////////////////////////////////////////////////////////////
class No {
    public int iDado;             // item de dado chave
    public double dDado;     // item de dado
    public No filhoEsq;        // n� filho � esquerda
    public No filhoDir;         // n� filho � direita

    public void displayNo()  {     // autodisplay
        System.out.print('{');
        System.out.print(iDado);
        System.out.print(", ");
        System.out.print(dDado);
        System.out.print("} ");
    }
}
////////////////////////////////////////////////////////////////
class Arvore {
    private No root;             // n� raiz

    public Arvore() { 
        root = null; 
    }    

    public No find (int chave) {  //Busca n� pela chave. Assume �rvore n�o vazia.
        No corrente = root;               //Inicia pela raiz.
        
        while (corrente.iDado != chave)  {   //Enquanto n�o encontrar...
            if (chave < corrente.iDado) {        //Vai para a esquerda?
                corrente = corrente.filhoEsq;
            } else {                                 //Ou para a direita?
                corrente = corrente.filhoDir;
            }
            
            if (corrente == null) {        //Se n�o possue filho...
                return null;                 //Ent�o n�o encontrou...
            }
        }
        
        return corrente;                    //Encontrou
    }  
    //M�todo de insers�o de n�.
    public void insere(int id, double dd) {
        No novoNo = new No();
        novoNo.iDado = id;   
        novoNo.dDado = dd;
        
        if (root==null) {   //�rvore vazia?
            root = novoNo;
            return;
        }
        
        No corrente = root;       //Inicia pela ra�z.
        No noPai;
        while (true) {               //A sa�da est� dentro do enquanto...
            noPai = corrente;
            if (id < corrente.iDado)  { //Para a esquerda?
                corrente = corrente.filhoEsq;
                if(corrente == null)  {   //Se chegou no final, insere � esquerda.
                    noPai.filhoEsq = novoNo;
                    return;
                }
            }  else {                         //Ou para a direita?
                corrente = corrente.filhoDir;
                if(corrente == null)  {     //Se chegou no final, insere � direita.
                    noPai.filhoDir = novoNo;
                    return;
                }
           }  //Encerra if externo.
        }  //Encera while.
    }  //Encerra insere().

    public boolean deleta (int chave) { //Exclui n�.
        No corrente = root;
        No paiDoNoASerExcluido = root;
        boolean ehFilhoEsq = true;

        while(corrente.iDado != chave) { //Procura o n�
            paiDoNoASerExcluido = corrente;
            if(chave < corrente.iDado)  { //Vai para a esquerda?
                ehFilhoEsq = true;
                corrente = corrente.filhoEsq;
            } else {                                 //Ou para a direita?
                ehFilhoEsq = false;
                corrente = corrente.filhoDir;
            }
            if(corrente == null) { //Fim da linha! N�o encontrou
                return false;         
            }
        }
        
        //Encontrou n� para exclus�o.

        //Se for um n� folha, simplesmente exclui-o.
        if (corrente.filhoEsq==null && corrente.filhoDir==null) {
            if (corrente == root) {           //Se for o n� raiz...
                root = null;                       //A �rvore fica vazia.
            } else if (ehFilhoEsq) {        //Se o n� � um filho � esquerda...
                paiDoNoASerExcluido.filhoEsq = null;     //Desconecta-o
            } else {                                //Se o n� � um filho � direita...
                paiDoNoASerExcluido.filhoDir = null;       //Desconecta-o
            }
        } else if (corrente.filhoDir == null) { //Se o n� n�o tem filho � direita, usa a �rvore � esquerda para substitu�-lo.
            if (corrente == root) {   //O n� � raiz?
                root = corrente.filhoEsq;
            } else if (ehFilhoEsq) { //O n� � filho � esquerda?
                paiDoNoASerExcluido.filhoEsq = corrente.filhoEsq;
            } else {                         //Ou o n� � filho � direita?
                paiDoNoASerExcluido.filhoDir = corrente.filhoEsq;
            }
        } else if (corrente.filhoEsq == null) { //Se o n� n�o tem filho � esquerda, usa a �rvore � direita para substitu�-lo.
            if(corrente == root) {   //O n� � raiz?
                root = corrente.filhoDir;
            } else if(ehFilhoEsq) { //O n� � filho � esquerda?
                paiDoNoASerExcluido.filhoEsq = corrente.filhoDir;
            } else {                        //Ou o n� � filho � direita?
                paiDoNoASerExcluido.filhoDir = corrente.filhoDir;
            }
        } else  { //Se tem dois filhos tem que substituir por n� em ordem...
            substituiNo(corrente, paiDoNoASerExcluido, ehFilhoEsq);
        }  //Encerra o if.

        return true;
    }
    /**
    * Busca o n� que deve substituir o n� a ser 
    * exclu�do. Retorna o n� com maior valor depois 
    * do n� a ser exclu�do. A busca come�a pelo 
    * filho � direita do n� a ser exclu�do e vai at� o
    * �ltimo descendente � esquerda desse.
    */ 
    private void substituiNo(No noASerExcluido, No paiDoNoASerExcluido, boolean ehFilhoEsq) {
        No paiDoSubstituto = noASerExcluido; //� preciso come�ar do n� a ser exclu�do.
        No substituto = noASerExcluido; //Come�a a partir do n� a ser exclu�do.
         //O corrente aponta sempre para o n� sucessor do que ser� o substituto para procurar o final da lista.
        No corrente = noASerExcluido.filhoDir;
        
        while(corrente != null)  { //Vai at� o encontrar o �ltimo.
            paiDoSubstituto = substituto;
            substituto = corrente;
            corrente = corrente.filhoEsq; 
        }
        
        if (substituto == noASerExcluido.filhoDir) {
            //Se o substituto for o n� imediatamente � direita do n� a ser exclu�do basta seguir com a troca abaixo.
        } else {
            //Se o substituto n�o for o filho � direita (ele ser� o �ltimo � esquerda),
            //o seu pai o substitui pelo seu filho � direita e ele recebe o filho � 
            //direita do n� a ser exclu�do.
            paiDoSubstituto.filhoEsq = substituto.filhoDir;
            substituto.filhoDir = noASerExcluido.filhoDir;
        }
  
        //Conecta n� pai do corente ao seu substituto em seu lugar
        if (noASerExcluido == root) { //O n� a ser exclu�do � o n� raiz?
            root = substituto;
        } else if (ehFilhoEsq) { //O n� a ser exclu�do � filho � esquerda?
            paiDoNoASerExcluido.filhoEsq = substituto;
        } else {                        //Ou � direita?
            paiDoNoASerExcluido.filhoDir = substituto;
        }

        //Conecta o n� substituto como filho � esquerda do n� a ser exclu�do. 
        //Lembrando que o n� substituto nunca ter� filho a sua esquerda.
        substituto.filhoEsq = noASerExcluido.filhoEsq;        
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
	public void imprimeArvore() {
        Stack globalStack = new Stack();
        globalStack.push(root);
        
        int nBlanks = 32;
        boolean isRowEmpty = false;
        
        System.out.println("......................................................");
        while (!isRowEmpty) {
            Stack localStack = new Stack();
            isRowEmpty = true;

            for (int j=0; j < nBlanks; j++) {
                System.out.print(' ');
            }

            while (!globalStack.isEmpty()) {
                No temp = (No) globalStack.pop();
                
                if (temp != null) {
                    System.out.print(temp.iDado);
                    localStack.push(temp.filhoEsq);
                    localStack.push(temp.filhoDir);

                    if (temp.filhoEsq != null || temp.filhoDir != null) {
                        isRowEmpty = false;
                    }
                } else {
                    System.out.print("--");
                    localStack.push(null);
                    localStack.push(null);
                }
                
                for (int j = 0; j < nBlanks * 2 - 2; j++) {
                    System.out.print(' ');
                }
            }  //Fim do while

            System.out.println();
            nBlanks /= 2;
                                
            while(!localStack.isEmpty()) {
                globalStack.push( localStack.pop() );
            }
        }  //Fim do while
            
        System.out.println("......................................................");
    }  //Fim de imprimeArvore()
// -------------------------------------------------------------
}  //final da classe Arvore

class AppArvore {
    public static void main(String[] args) throws IOException {
        int value;
        Arvore aArvore = new Arvore();

        aArvore.insere(50, 1.5);
        aArvore.insere(40, 1.2);
        aArvore.insere(60, 1.7);
        aArvore.insere(45, 1.5);
        aArvore.insere(30, 1.2);
        aArvore.insere(10, 1.7);
        aArvore.insere(55, 1.5);
        aArvore.insere(50, 1.2);
        aArvore.insere(57, 1.7);
        aArvore.insere(70, 1.5);

        while(true) {
            System.out.println("Escolha a primeira letra da op��o desejada: ");
            System.out.print("Mostra, Insere, Procura, Exclui, Sai. ");
         
            int choice = getChar();
         
            switch (choice) {
                case 'M':
                case 'm':
                   aArvore.imprimeArvore();
                   break;
               
                case 'I':
                case 'i':
                    System.out.print("Digite um interio para inserir: ");
                    value = getInt();
                    aArvore.insere(value, value + 0.9);
                    break;
               
                case 'P':
                case 'p':
                    System.out.print("Informe o n�mero a ser buscado: ");
                    value = getInt();
                   No found = aArvore.find(value);
                   if (found == null) {
                        System.out.print("Could not find ");
                        System.out.print(value + '\n');
                    } else {
                        System.out.print("Encontrado: ");
                        found.displayNo();
                        System.out.print("\n");
                    }
                   break;
                   
                case 'E':
                case 'e':
                    System.out.print("Enter value to delete: ");
                    value = getInt();
                    boolean didDelete = aArvore.deleta(value);
                    if(didDelete) {
                        System.out.print("Deleted " + value + '\n');
                    } else {
                        System.out.print("Could not delete ");
                        System.out.print(value + '\n');
                    }
                    break;
                    
                case 'S':
                case 's':
                    return;
                    
                default:
                    System.out.print("Op��o inv�lida!\n");
            }  // end switch
        }  // end while
    }  // end main()
// -------------------------------------------------------------
    private static String getString() throws IOException {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        String s = br.readLine();
        return s;
    }
// -------------------------------------------------------------
    private static char getChar() throws IOException {
        String s = getString();
        return s.charAt(0);
    }
//-------------------------------------------------------------
    private static int getInt() throws IOException {
        String s = getString();
        return Integer.parseInt(s);
    }
// -------------------------------------------------------------
   }  //Final da classe AppArvore