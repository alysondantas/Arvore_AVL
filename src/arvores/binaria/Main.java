package arvores.binaria;
import arvores.binaria.Arvore;
import arvores.binaria.No;

public class Main {

	public static void main(String[] args) {
		Arvore arv = new Arvore();
		arv.inserir(5);
		arv.inserir(3);
		arv.inserir(2);
		arv.inserir(6);
		No no = arv.getRaiz();
		arv.imprimir(no);
		arv.deletar(6);
		arv.imprimir(no);
	}

}
