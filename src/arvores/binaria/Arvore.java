package arvores.binaria;


public class Arvore implements IArvoreBinaria {
	private No raiz;

	@Override
	public void inserir(int chave) {
		No aux = raiz;
		if(raiz == null){
			No novo = new No(chave);
			raiz = novo;
		}else{
			No pai = raiz;
			while(true){
				pai = aux;
				if(chave < aux.getChave()){
					aux = aux.getFilhoEsquerda();
					if(aux == null){
						No novo = new No(chave);
						pai.setFilhoEsquerda(novo);
						return;
					}
				}else{
					aux = aux.getFilhoDireita();
					if(aux == null){
						No novo = new No(chave);
						pai.setFilhoDireita(novo);
						return;
					}
				}
			}
		}

	}

	@Override
	public No encontrar(int chave) {
		No aux = raiz;
		if(raiz == null){
			return null;
		}
		while(aux.getChave() != chave || aux!=null){
			if(chave<aux.getChave()){
				aux = aux.getFilhoEsquerda();
			}else{
				aux = aux.getFilhoDireita();
			}
		}
		return aux;
	}

	@Override
	public void deletar(int chave) {
		No aux = raiz;
		No pai = null;
		while(aux.getChave()!=chave){
			pai = aux;
			if(aux.getChave()>chave){
				aux = aux.getFilhoEsquerda();
			}else{
				aux = aux.getFilhoDireita();
			}
			if(aux == null){
				return;
			}
		}
		if(aux.getFilhoDireita() == null && aux.getFilhoEsquerda() == null){
			if(aux == raiz){
				raiz = null;
			}else if(aux.equals(pai.getFilhoEsquerda())){
				pai.setFilhoEsquerda(null);
			}else{
				pai.setFilhoDireita(null);
			}
		}else{
			if(aux.getFilhoDireita()==null){
				if(aux == raiz){
					raiz = raiz.getFilhoEsquerda();
				}else if(pai.getFilhoEsquerda().equals(aux)){
					pai.setFilhoEsquerda(aux.getFilhoEsquerda());
				}else{
					pai.setFilhoDireita(aux.getFilhoEsquerda());
				}
			}else if(aux.getFilhoEsquerda() == null){
				if(aux == raiz){
					raiz = raiz.getFilhoDireita();
				}else{
					if(pai.getFilhoEsquerda().equals(aux)){
						pai.setFilhoEsquerda(aux.getFilhoDireita());
					}else{
						pai.setFilhoDireita(aux.getFilhoDireita());
					}
				}
			}else{
				No sucessor = pegaSucessor(aux);
				if(aux==raiz){
					raiz = sucessor;
				}else if(pai.getFilhoEsquerda().equals(aux)){
					pai.setFilhoEsquerda(sucessor);
				}else{
					pai.setFilhoDireita(sucessor);
				}
				sucessor.setFilhoEsquerda(aux.getFilhoEsquerda());
			}
		}

	}

	@Override
	public void imprimir(No no) {
		if(no == null){
			return;
		}else{
			if(no.getFilhoDireita() == null && no.getFilhoEsquerda() == null){
				System.out.println(no.getChave());
				return;
			}
			imprimir(no.getFilhoDireita());
			System.out.println(no.getChave());
			imprimir(no.getFilhoEsquerda());
		}
	}

	public No getRaiz(){
		return raiz;
	}

	private No pegaSucessor(No no){
		No pai = no;
		No sucessor = no;
		No aux = no.getFilhoDireita();
		while(aux != null){
			pai = sucessor;
			sucessor = aux;
			aux = aux.getFilhoEsquerda();
		}
		if(sucessor != no.getFilhoDireita()){
			pai.setFilhoEsquerda(sucessor.getFilhoDireita());
			sucessor.setFilhoDireita(no.getFilhoDireita());
		}
		return sucessor;
	}
}
