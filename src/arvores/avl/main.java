package arvores.avl;

public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Arvore arv = new Arvore();
		
		//rotação a esquerda
		arv.inserir(8);
		arv.inserir(2);
		arv.inserir(15);
		arv.inserir(9);
		arv.inserir(20);
		arv.inserir(17);
		
		//rotacao a direita
//		arv.inserir(8);
//		arv.inserir(10);
//		arv.inserir(4);
//		arv.inserir(2);
//		arv.inserir(6);
//		arv.inserir(3);
		arv.imprimeEmOrdem();
	}

}
