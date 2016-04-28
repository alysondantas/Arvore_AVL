package arvores.avl;

/**
 *
 * @author Alyson Dantas e Bruno Menezes
 */
public class No {

    private No filhoDaEsquerda;
    private No filhoDaDireita;
    private No pai;
    private int fator;
    private int chave;

    public No getFilhoDaEsquerda() {
        return filhoDaEsquerda;
    }

    public void setFilhoDaEsquerda(No filhoDaEsquerda) {
        this.filhoDaEsquerda = filhoDaEsquerda;
    }

    public No getFilhoDaDireita() {
        return filhoDaDireita;
    }

    public void setFilhoDaDireita(No filhoDaDireita) {
        this.filhoDaDireita = filhoDaDireita;
    }

    public int getChave() {
        return chave;
    }

    public void setChave(int chave) {
        this.chave = chave;
    }

    public int getFator() {
        return fator;
    }

    public void setFator(int fator) {
        this.fator = fator;
    }

	public No getPai() {
		return pai;
	}

	public void setPai(No pai) {
		this.pai = pai;
	}

}
