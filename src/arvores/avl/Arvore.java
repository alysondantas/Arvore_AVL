package arvores.avl;

/**
 *
 * @author Alyson Dantas e Bruno Menezes
 */
public class Arvore {

	private No raiz;

	public void inserir(int chave) {
		if (raiz == null) {
			No no = new No();
			no.setChave(chave);
			no.setFator(0);
			System.out.println("Inserindo: " + chave);
			no.setPai(null);
			raiz = no;
		} else {
			inserir(raiz, chave);
		}
	}
	static boolean inseriu = false;

	private void inserir(No no, int chave) {
		if (chave < no.getChave()) {
			if (no.getFilhoDaEsquerda() != null) {
				inserir(no.getFilhoDaEsquerda(), chave);
				if (inseriu) {
					ajustarBalanco(no);
				}
			} else {
				System.out.println("Inserindo: " + chave);
				No newNo = new No();
				newNo.setChave(chave);
				newNo.setFator(0);
				no.setFilhoDaEsquerda(newNo);
				newNo.setPai(no);
				ajustarBalanco(no);
				inseriu = true;
			}
			return;
		}
		if (chave > no.getChave()) {
			if (no.getFilhoDaDireita() != null) {
				inserir(no.getFilhoDaDireita(), chave);
				if (inseriu) {
					ajustarBalanco(no);
				}
			} else {
				System.out.println("Inserindo: " + chave);
				No newNo = new No();
				newNo.setChave(chave);
				newNo.setFator(0);
				no.setFilhoDaDireita(newNo);
				newNo.setPai(no);
				ajustarBalanco(no);
				inseriu = true;
			}
			return;
		}

	}

	public void imprimeEmOrdem() {
		System.out.println("IMPRIMINDO ÁRVORE");
		System.out.println("A raiz é " + raiz.getChave());
		System.out.println("Filho da esquerda da raiz " + raiz.getFilhoDaEsquerda().getChave());
		imprimeEmOrdem(raiz);
	}

	private void imprimeEmOrdem(No raiz) {
		if (raiz != null) {
			imprimeEmOrdem(raiz.getFilhoDaEsquerda());
			System.out.println(raiz.getChave() + " - Fator: " + raiz.getFator());
			imprimeEmOrdem(raiz.getFilhoDaDireita());
		}
	}

	public int obterFator(No raiz){
		if(raiz == null){
			return -1;
		}else if(raiz.getFilhoDaDireita() == null && raiz.getFilhoDaEsquerda() == null){
			return 0;
		}else if(raiz.getFilhoDaDireita() == null){
			return obterFator(raiz.getFilhoDaEsquerda())+1;
		}else if(raiz.getFilhoDaEsquerda() == null){
			return obterFator(raiz.getFilhoDaDireita())+1;
		}else{
			return Integer.max(obterFator(raiz.getFilhoDaDireita()), obterFator(raiz.getFilhoDaEsquerda()))+1;
		}
	}


	public void ajustarBalanco(No no){
		int dir = obterFator(no.getFilhoDaDireita());
		int esq = obterFator(no.getFilhoDaEsquerda());

		int fator = dir - esq;
		no.setFator(fator);

		if(no.getFator() == 2){
			System.out.println("Entrou no caso 2");
			caso2(no);
			ajustarBalanco(no);
		}else if(no.getFator() == -2){
			System.out.println("Entrou no caso 1");
			caso1(no);
			ajustarBalanco(no);
		}
		if(no.getPai()!= null){
			ajustarBalanco(no.getPai());
		} 
//		else{
//			raiz = no;
//		}

	}

	//	private void ajustarBalanceamentoEsquerda(No no) {
	//		switch (no.getFator()) {
	//		case 1: //Mais alto a direita
	//			no.setFator(0);
	//			break;
	//		case 0:
	//			no.setFator(-1);
	//			break;
	//		case -1:
	//			no.setFator(-2);
	//			caso1(no);
	//			inseriu = false;
	//			break;
	//		}
	//	}
	//
	//	private void ajustarBalanceamentoDireita(No no) {
	//		switch (no.getFator()) {
	//		case -1:
	//			no.setFator(0);
	//			break;
	//		case 0:
	//			no.setFator(1);
	//			break;
	//		case 1:
	//			no.setFator(2);
	//			caso2(no);
	//			inseriu = false;
	//			break;
	//		}
	//	}




	public void caso2(No no) {
		No aux = no.getFilhoDaDireita();
		if (aux.getFator() == 1) {
			rotacaoEsq(no);
		} else {
			if(aux.getFator() == -1){
				rotacaoDirEsq(no);
			}
		}
		//no.setFator(0);
		inseriu = false;
	}

//	public void caso2(No no) {
//		No aux = no.getFilhoDaDireita();
//		if (aux.getFator() == 1) {
//			rotacaoEsq(no);
//		} else {
//			if(no.getFator() == -1){
//				rotacaoDirEsq(no);
//			}
//		}
//		//		no.setFator(0);
//		inseriu = false;
//	}

	public void caso1(No no) {
		No aux = no.getFilhoDaEsquerda();
		if (aux.getFator() == -1) {
			rotacaoDir(no);
		} else {
			if(aux.getFator()==1){
				rotacaoEsqDir(no);
			}
		}
		//no.setFator(0);
		inseriu = false;
	}
	
	//	public void caso1(No no) {
	//		No aux = no.getFilhoDaEsquerda();
	//		if (aux.getFator() == -1) {
	//			rotacaoDir(no);
	//		} else {
	//			if(no.getFator() == 1){
	//				rotacaoEsqDir(no);
	//			}
	//		}
	//		//		no.setFator(0);
	//		inseriu = false;
	//
	//	}
	
	public No rotacaoDirEsq(No no){
		System.out.println("Rotacao dupla dir esq");
		no.setFilhoDaDireita(rotacaoDir(no.getFilhoDaDireita()));
		no = rotacaoEsq(no);
		ajustarBalanco(no.getFilhoDaDireita());
		return no;
	}

	public No rotacaoEsqDir(No no){
		System.out.println("Rotacao dupla esq dir");
		no.setFilhoDaEsquerda(rotacaoEsq(no.getFilhoDaEsquerda()));
		no = rotacaoDir(no);
		ajustarBalanco(no.getFilhoDaEsquerda());
		return no;
	}

	public No rotacaoDir(No no){
		boolean braiz = false;
		System.out.println("Rotacao a direita");
		No pai = no.getPai();
		No aux = no.getFilhoDaEsquerda();
		no.setFilhoDaEsquerda(aux.getFilhoDaDireita());
		if(no.getFilhoDaEsquerda() != null){
			No aux2 = no.getFilhoDaEsquerda();
			aux2.setPai(no);
		}
		if(no != raiz){
			System.out.println("comparou o pai...");
			if(pai.getFilhoDaEsquerda()!=null){
				if(pai.getFilhoDaEsquerda().equals(no)){
					System.out.println("encontrou o pai e trocou");
					pai.setFilhoDaEsquerda(aux);
				}
			}else if(pai.getFilhoDaDireita()!=null){
				if(pai.getFilhoDaDireita().equals(no)){
					System.out.println("Erro?");
					pai.setFilhoDaDireita(aux);
				}
			}
		} else {
			System.out.println("É a raiz!");
			braiz = true;
		}
		aux.setPai(no.getPai());
		aux.setFilhoDaDireita(no);
		no.setPai(aux);
		no = aux;
		if(braiz == true){
			raiz = aux;
			raiz.setPai(null);
		}
		return no;
	}

	public No rotacaoEsq(No no){
		boolean braiz = false;
		System.out.println("Rotacao a esquerda");
		No pai = no.getPai();
		No aux = no.getFilhoDaDireita();
		no.setFilhoDaDireita(aux.getFilhoDaEsquerda());
		if(no.getFilhoDaDireita() != null){
			No aux2 = no.getFilhoDaDireita();
			aux2.setPai(no);
		}
		if(no != raiz){
			System.out.println("comparou o pai... a esquerda");
			if(pai.getFilhoDaDireita()!=null){
				if(pai.getFilhoDaDireita().equals(no)){
					System.out.println("encontrou o pai e trocou como filho da direita");
					pai.setFilhoDaDireita(aux);
				}
			}else if(pai.getFilhoDaEsquerda()!=null){
				if(pai.getFilhoDaEsquerda().equals(no)){
					System.out.println("Erro?");
					pai.setFilhoDaEsquerda(aux);
				}
			}

		} else{
			System.out.println("É a raiz!");
			braiz = true;
		}

		aux.setPai(no.getPai());
		aux.setFilhoDaEsquerda(no);
		no.setPai(aux);
		no = aux;
		if(braiz == true){
			raiz = aux;
			raiz.setPai(null);
		}
		return no;
	}

}
