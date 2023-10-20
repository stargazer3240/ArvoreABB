package br.ufrn.imd.visao;

import br.ufrn.imd.modelo.ArvoreABB;
import br.ufrn.imd.modelo.Nodo;

public class ArvoreView {

	public static void main(String[] args) {
		ArvoreABB<Integer> abb = new ArvoreABB<>(new Nodo<>(10));
		abb.getRaiz().setEsquerdo(new Nodo<>(5));
		abb.getRaiz().setDireito(new Nodo<>(15));
		abb.getRaiz().getEsquerdo().setDireito(new Nodo<>(8));
		abb.getRaiz().getDireito().setEsquerdo(new Nodo<>(14));
		abb.getRaiz().getDireito().setDireito(new Nodo<>(18));
		System.out.println("PRE-ORDEM");
		abb.preOrdemRaiz();
		System.out.println();
		System.out.println("SIMETRICA");
		abb.ordemSimetricaRaiz();
		System.out.println();
		System.out.println("POS-ORDEM");
		abb.posOrdemRaiz();
		System.out.println();
		System.out.println("PRE-ORDEM-ITERATIVA");
		abb.preOrdemIteRaiz();
		System.out.println();
		abb.atribuirAltura();
		System.out.println("Altura da Árvore: " + abb.getAlturaArvore());
		System.out.println("EM NÍVEL");
		abb.emNivelRaiz();
	}
}
