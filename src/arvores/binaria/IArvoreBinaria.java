package arvores.binaria;

public interface IArvoreBinaria {
	
	public void inserir(int chave);
	public No encontrar(int chave);
	public void deletar(int chave);
	public void imprimir(No raiz);
}
