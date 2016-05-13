package arvores.binaria;

public class No {
	private No filhoEsquerda;
	private No filhoDireita;
	private int chave;
	
	public No(int chave){
		this.setChave(chave);
	}

	public No getFilhoEsquerda() {
		return filhoEsquerda;
	}

	public void setFilhoEsquerda(No filhoEsquerda) {
		this.filhoEsquerda = filhoEsquerda;
	}

	public int getChave() {
		return chave;
	}

	public void setChave(int chave) {
		this.chave = chave;
	}

	public No getFilhoDireita() {
		return filhoDireita;
	}

	public void setFilhoDireita(No filhoDireita) {
		this.filhoDireita = filhoDireita;
	}
}
