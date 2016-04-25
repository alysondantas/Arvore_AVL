/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arvores.avl;

/**
 *
 * @author Ricardo
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
	
	public int calcularAlturaEsq(No no, int esq){
		if (no != null) {
			esq = esq + 1;
			esq = calcularAlturaEsq(no.getFilhoDaEsquerda(), esq);
			return esq;
		}
		return -1;
	}
	
	public int calcularAlturaDir(No no, int dir){
		if (no != null) {
			dir = dir + 1;
			dir =calcularAlturaDir(no.getFilhoDaDireita(), dir);
			return dir;
		}
		return -1;
	}
	
	public void ajustarBalanco(No no){
		int dir = calcularAlturaDir(no, 1);
		if(dir<0){
			dir = 0;
		}
		int esq = calcularAlturaEsq(no, 1);
		if(esq<0){
			esq = 0;
		}
		int fator = dir - esq;
		no.setFator(fator);
		System.out.println("Ajustou o fator do "+ no.getChave());
		System.err.println("novo fator " + no.getFator());
		if(no.getFator() == 2){
			System.out.println("Entrou no caso 2");
			caso2(no);
		}else if(no.getFator() == -2){
			System.out.println("Entrou no caso 1");
			caso1(no);
		}
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
			no = rotacaoEsq(no);
		} else {
			no = rotacaoDirEsq(no);
		}
//		no.setFator(0);
		inseriu = false;
	}

	public void caso1(No no) {
		No aux = no.getFilhoDaEsquerda();
		if (aux.getFator() == -1) {
			no = rotacaoDir(no);
		} else {
			no = rotacaoEsqDir(no);
		}
//		no.setFator(0);
		inseriu = false;

	}

	public No rotacaoDirEsq(No no){
		System.out.println("Rotacao dupla dir esq");
		no.setFilhoDaDireita(rotacaoDir(no.getFilhoDaDireita()));
		no = rotacaoEsq(no);
		ajustarBalanco(no);
		return no;
	}

	public No rotacaoEsqDir(No no){
		System.out.println("Rotacao dupla esq dir");
		no.setFilhoDaEsquerda(rotacaoEsq(no.getFilhoDaEsquerda()));
		no = rotacaoDir(no);
		ajustarBalanco(no);
		return no;
	}

	public No rotacaoDir(No no){
		System.out.println("Rotacao a direita");
		No pai = no.getPai();
		No aux = no.getFilhoDaEsquerda();
		no.setFilhoDaEsquerda(aux.getFilhoDaDireita());
		if(aux.getFilhoDaEsquerda() != null){
			No aux2 = aux.getFilhoDaEsquerda();
			aux2.setPai(no);
		}
		if(no!=raiz){
			System.out.println("comparou o pai...");
			if(pai.getFilhoDaEsquerda().equals(no)){
				System.out.println("encontrou o pai e trocou");
				pai.setFilhoDaEsquerda(aux);
			}
			else if(pai.getFilhoDaDireita().equals(no)){
				System.out.println("Erro?");
				pai.setFilhoDaDireita(aux);
			}
		}
		aux.setPai(no.getPai());
		aux.setFilhoDaDireita(no);
		no.setPai(aux);
		ajustarBalanco(no);
//		no.setFator(0);
		no = aux;
		ajustarBalanco(no);
		return no;
	}

	public No rotacaoEsq(No no){
		System.out.println("Rotacao a esquerda");
		No pai = no.getPai();
		No aux = no.getFilhoDaDireita();
		no.setFilhoDaDireita(aux.getFilhoDaEsquerda());
		if(aux.getFilhoDaDireita() != null){
			No aux2 = aux.getFilhoDaDireita();
			aux2.setPai(no);
		}
		aux.setPai(no.getPai());
		if(no!=raiz){
			System.out.println("comparou o pai... a esquerda");
			if(pai.getFilhoDaEsquerda().equals(no)){
				System.out.println("Erro?");
				pai.setFilhoDaEsquerda(aux);
			} else if(pai.getFilhoDaDireita().equals(no)){
				System.out.println("encontrou o pai e trocou como filho da direita");
				pai.setFilhoDaDireita(aux);
			}
		}
		aux.setFilhoDaEsquerda(no);
		no.setPai(aux);
		ajustarBalanco(no);
//		no.setFator(0);
		no = aux;
		ajustarBalanco(no);
		return no;
	}

}
