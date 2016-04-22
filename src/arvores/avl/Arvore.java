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
    static boolean inseriu = false;


    public void inserir(int chave) {
    	inseriu = false;
        if (raiz == null) {
            No no = new No();
            no.setFator(0);
            no.setChave(chave);
            System.out.println("Inserindo: " + chave);
            raiz = no;
        } else {
            inserir(raiz, chave);
        }
    }

    private void inserir(No no, int chave) {
        if (chave < no.getChave()) {
            if (no.getFilhoDaEsquerda() != null) {
                inserir(no.getFilhoDaEsquerda(), chave);
                if (inseriu) {
                	no.setFator(calcularFatBal(no));
//                    ajustarBalanceamentoDireita(no);
                }
            } else {
                System.out.println("Inserindo: " + chave);
                No newNo = new No();
                newNo.setChave(chave);
                newNo.setFator(0);
                no.setFilhoDaEsquerda(newNo);
                no.setFator(calcularFatBal(no));
//                ajustarBalanceamentoDireita(no);//ajusta balanceamento do no para esquerda
                inseriu = true;
            }
            return;
        }
        if (chave > no.getChave()) {
            if (no.getFilhoDaDireita() != null) {
                inserir(no.getFilhoDaDireita(), chave);
                if (inseriu) {
                	no.setFator(calcularFatBal(no));
//                    ajustarBalanceamentoEsquerda(no);
                }
            } else {
                System.out.println("Inserindo: " + chave);
                No newNo = new No();
                newNo.setChave(chave);
                newNo.setFator(0);
                no.setFilhoDaDireita(newNo);
                no.setFator(calcularFatBal(no));
//                ajustarBalanceamentoEsquerda(no);//ajusta balanceamento do no para direita
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
        	no = rotacaoDirEsq(no);
        }
        no.setFator(0);
        inseriu = false;
    }

    public void caso1(No no) {
        No aux = no.getFilhoDaEsquerda();
        if (aux.getFator() == -1) { //rotação simples a direita
        	no = rotacaoDir(no);
        } else {
        	no = rotacaoEsqDir(no);
        }
        no.setFator(0);
        inseriu = false;

    }
    
    public No rotacaoDirEsq(No no){
    	no.setFilhoDaDireita(rotacaoDir(no.getFilhoDaDireita()));
    	no = rotacaoEsq(no);
    	return no;
    }
    
    public No rotacaoEsqDir(No no){
    	no.setFilhoDaEsquerda(rotacaoEsq(no.getFilhoDaEsquerda()));
    	no = rotacaoDir(no);
		return no;
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
    
    public No rotDir(No no) {
    	No q, temp;
    	q = no.getFilhoDaEsquerda();
    	temp = q.getFilhoDaDireita();
    	q.setFilhoDaDireita(no);
    	no.setFilhoDaEsquerda(temp);
    	no = q;
    	return no;
    }
    
    public No rotEsq(No no) {
    	No q, temp;
    	q = no.getFilhoDaDireita();
    	temp = q.getFilhoDaEsquerda();
    	q.setFilhoDaEsquerda(no);
    	no.setFilhoDaEsquerda(temp);
    	no = q;
    	return no;
    }
    
    public int calcularFatBal(No no) {
    	int alturaEsquerda = calcularAltura(0, no);
    	int alturaDireita = calcularAltura(1, no);
    	int fatBal = alturaEsquerda - alturaDireita;
    	if (fatBal == 2) {
    		caso2(no);
    		inseriu = false;
    	} else if (fatBal == -2) {
    		caso1(no);
    		inseriu = false;
    	}
    	return fatBal;
    }
    
    public int calcularAltura(int lado, No noOriginal) {
    	No no = noOriginal;
    	boolean primeiro = false;
    	int altura = -1;
    	
    	if(lado == 0) {
    		while(no != null) {
    			if(primeiro == false) {
    				altura ++;
    				no = no.getFilhoDaEsquerda();
    				primeiro = true;
    			}
    			else {
    				if(no.getFilhoDaEsquerda() == null && no.getFilhoDaDireita() != null) {
    					altura ++;
    					altura += calcularAltura(1, no);
    					no = no.getFilhoDaEsquerda();
    				}
    				else {
    					altura ++;
    					no = no.getFilhoDaEsquerda();
    				}
    			}
    		}
    	}
    	
    	else {
    		while(no != null) {
    			if (primeiro == false) {
    				altura++;
					no = no.getFilhoDaDireita();
					primeiro = true;
    			}
    			else {
    				if(no.getFilhoDaDireita() == null && no.getFilhoDaEsquerda() != null) {
    					altura += calcularAltura(0, no);
    					no = no.getFilhoDaDireita();
    				}
    				else {
    					altura ++;
    					no = no.getFilhoDaDireita();
    				}
    			}
    		}
    	}
    	
    	return altura;
    }

}
