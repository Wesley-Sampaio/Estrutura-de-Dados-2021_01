import java.io.*;
import java.util.*;               // para Stack class
////////////////////////////////////////////////////////////////
class No {
    public int iDado;             // item de dado chave
    public double dDado;     // item de dado
    public No filhoEsq;        // nó filho à esquerda
    public No filhoDir;         // nó filho à direita

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
    private No root;             // nó raiz

    public Arvore() { 
        root = null; 
    }    

    public No find (int chave) {  //Busca nó pela chave. Assume árvore não vazia.
        No corrente = root;               //Inicia pela raiz.
        
        while (corrente.iDado != chave)  {   //Enquanto não encontrar...
            if (chave < corrente.iDado) {        //Vai para a esquerda?
                corrente = corrente.filhoEsq;
            } else {                                 //Ou para a direita?
                corrente = corrente.filhoDir;
            }
            
            if (corrente == null) {        //Se não possue filho...
                return null;                 //Então não encontrou...
            }
        }
        
        return corrente;                    //Encontrou
    }  
    //Método de insersão de nó.
    public void insere(int id, double dd) {
        No novoNo = new No();
        novoNo.iDado = id;   
        novoNo.dDado = dd;
        
        if (root==null) {   //Árvore vazia?
            root = novoNo;
            return;
        }
        
        No corrente = root;       //Inicia pela raíz.
        No noPai;
        while (true) {               //A saída está dentro do enquanto...
            noPai = corrente;
            if (id < corrente.iDado)  { //Para a esquerda?
                corrente = corrente.filhoEsq;
                if(corrente == null)  {   //Se chegou no final, insere à esquerda.
                    noPai.filhoEsq = novoNo;
                    return;
                }
            }  else {                         //Ou para a direita?
                corrente = corrente.filhoDir;
                if(corrente == null)  {     //Se chegou no final, insere à direita.
                    noPai.filhoDir = novoNo;
                    return;
                }
           }  //Encerra if externo.
        }  //Encera while.
    }  //Encerra insere().

    public boolean deleta (int chave) { //Exclui nó.
        No corrente = root;
        No paiDoNoASerExcluido = root;
        boolean ehFilhoEsq = true;

        while(corrente.iDado != chave) { //Procura o nó
            paiDoNoASerExcluido = corrente;
            if(chave < corrente.iDado)  { //Vai para a esquerda?
                ehFilhoEsq = true;
                corrente = corrente.filhoEsq;
            } else {                                 //Ou para a direita?
                ehFilhoEsq = false;
                corrente = corrente.filhoDir;
            }
            if(corrente == null) { //Fim da linha! Não encontrou
                return false;         
            }
        }
        
        //Encontrou nó para exclusão.

        //Se for um nó folha, simplesmente exclui-o.
        if (corrente.filhoEsq==null && corrente.filhoDir==null) {
            if (corrente == root) {           //Se for o nó raiz...
                root = null;                       //A árvore fica vazia.
            } else if (ehFilhoEsq) {        //Se o nó é um filho à esquerda...
                paiDoNoASerExcluido.filhoEsq = null;     //Desconecta-o
            } else {                                //Se o nó é um filho à direita...
                paiDoNoASerExcluido.filhoDir = null;       //Desconecta-o
            }
        } else if (corrente.filhoDir == null) { //Se o nó não tem filho à direita, usa a árvore à esquerda para substituí-lo.
            if (corrente == root) {   //O nó é raiz?
                root = corrente.filhoEsq;
            } else if (ehFilhoEsq) { //O nó é filho à esquerda?
                paiDoNoASerExcluido.filhoEsq = corrente.filhoEsq;
            } else {                         //Ou o nó é filho à direita?
                paiDoNoASerExcluido.filhoDir = corrente.filhoEsq;
            }
        } else if (corrente.filhoEsq == null) { //Se o nó não tem filho à esquerda, usa a árvore à direita para substituí-lo.
            if(corrente == root) {   //O nó é raiz?
                root = corrente.filhoDir;
            } else if(ehFilhoEsq) { //O nó é filho à esquerda?
                paiDoNoASerExcluido.filhoEsq = corrente.filhoDir;
            } else {                        //Ou o nó é filho à direita?
                paiDoNoASerExcluido.filhoDir = corrente.filhoDir;
            }
        } else  { //Se tem dois filhos tem que substituir por nó em ordem...
            substituiNo(corrente, paiDoNoASerExcluido, ehFilhoEsq);
        }  //Encerra o if.

        return true;
    }
    /**
    * Busca o nó que deve substituir o nó a ser 
    * excluído. Retorna o nó com maior valor depois 
    * do nó a ser excluído. A busca começa pelo 
    * filho à direita do nó a ser excluído e vai até o
    * último descendente à esquerda desse.
    */ 
    private void substituiNo(No noASerExcluido, No paiDoNoASerExcluido, boolean ehFilhoEsq) {
        No paiDoSubstituto = noASerExcluido; //É preciso começar do nó a ser excluído.
        No substituto = noASerExcluido; //Começa a partir do nó a ser excluído.
         //O corrente aponta sempre para o nó sucessor do que será o substituto para procurar o final da lista.
        No corrente = noASerExcluido.filhoDir;
        
        while(corrente != null)  { //Vai até o encontrar o último.
            paiDoSubstituto = substituto;
            substituto = corrente;
            corrente = corrente.filhoEsq; 
        }
        
        if (substituto == noASerExcluido.filhoDir) {
            //Se o substituto for o nó imediatamente à direita do nó a ser excluído basta seguir com a troca abaixo.
        } else {
            //Se o substituto não for o filho à direita (ele será o último à esquerda),
            //o seu pai o substitui pelo seu filho à direita e ele recebe o filho à 
            //direita do nó a ser excluído.
            paiDoSubstituto.filhoEsq = substituto.filhoDir;
            substituto.filhoDir = noASerExcluido.filhoDir;
        }
  
        //Conecta nó pai do corente ao seu substituto em seu lugar
        if (noASerExcluido == root) { //O nó a ser excluído é o nó raiz?
            root = substituto;
        } else if (ehFilhoEsq) { //O nó a ser excluído é filho à esquerda?
            paiDoNoASerExcluido.filhoEsq = substituto;
        } else {                        //Ou à direita?
            paiDoNoASerExcluido.filhoDir = substituto;
        }

        //Conecta o nó substituto como filho à esquerda do nó a ser excluído. 
        //Lembrando que o nó substituto nunca terá filho a sua esquerda.
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
            System.out.println("Escolha a primeira letra da opção desejada: ");
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
                    System.out.print("Informe o número a ser buscado: ");
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
                    System.out.print("Opção inválida!\n");
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