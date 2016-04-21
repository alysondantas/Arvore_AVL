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
            System.out.println("Inserindo: " + chave);
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
                    ajustarBalanceamentoEsquerda(no);
                }
            } else {
                System.out.println("Inserindo: " + chave);
                No newNo = new No();
                newNo.setChave(chave);
                newNo.setFator(0);
                no.setFilhoDaEsquerda(newNo);
                inseriu = true;
            }
            return;
        }
        if (chave > no.getChave()) {
            if (no.getFilhoDaDireita() != null) {
                inserir(no.getFilhoDaDireita(), chave);
                if (inseriu) {
                    ajustarBalanceamentoDireita(no);
                }
            } else {
                System.out.println("Inserindo: " + chave);
                No newNo = new No();
                newNo.setChave(chave);
                newNo.setFator(0);
                no.setFilhoDaDireita(newNo);
                inseriu = true;
            }
            return;
        }

    }

    public void imprimeEmOrdem() {
        System.out.println("IMPRIMINDO Ã?RVORE");
        imprimeEmOrdem(raiz);
    }

    private void imprimeEmOrdem(No raiz) {
        if (raiz != null) {
            imprimeEmOrdem(raiz.getFilhoDaEsquerda());
            System.out.println(raiz.getChave() + " - Fator: " + raiz.getFator());
            imprimeEmOrdem(raiz.getFilhoDaDireita());
        }
    }

    private void ajustarBalanceamentoEsquerda(No no) {
        switch (no.getFator()) {
            case 1: //Mais alto a direita
                no.setFator(0);
                break;
            case 0:
                no.setFator(-1);
                break;
            case -1:
                no.setFator(-2);
                caso1(no);
                inseriu = false;
                break;
        }
    }

    private void ajustarBalanceamentoDireita(No no) {
        switch (no.getFator()) {
            case -1:
                no.setFator(0);
                break;
            case 0:
                no.setFator(1);
                break;
            case 1:
                no.setFator(2);
                caso2(no);
                inseriu = false;
                break;
        }
    }

    public void caso2(No no) {
        No aux = no.getFilhoDaDireita();
        if (aux.getFator() == 1) {
        	no = rotacaoEsq(no);
        } else {

        }
        no.setFator(0);
        inseriu = false;
    }

    public void caso1(No no) {
        No aux = no.getFilhoDaEsquerda();
        if (aux.getFator() == -1) { //rotação simples a direita
        	no = rotacaoDir(no);
        } else {

        }
        no.setFator(0);
        inseriu = false;

    }
    
    public No rotacaoDir(No no){
    	No aux = no.getFilhoDaEsquerda();
    	no.setFilhoDaEsquerda(aux.getFilhoDaDireita());
    	aux.setFilhoDaDireita(no);
    	no = aux;
    	return no;
    }
    
    public No rotacaoEsq(No no){
    	No aux = no.getFilhoDaDireita();
    	no.setFilhoDaDireita(aux.getFilhoDaEsquerda());
    	aux.setFilhoDaEsquerda(no);
    	no = aux;
    	return no;
    }

}
