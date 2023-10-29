// SPDX-FileCopyrightText: 2023 Fabrício Moura Jácome
// SPDX-FileCopyrightText: 2023 Ramon Oliveira de Almeida
//
// SPDX-License-Identifier: MIT

package br.ufrn.imd.visao;

import br.ufrn.imd.modelo.ArvoreABB;
import br.ufrn.imd.modelo.ArvoreABB.VisitarFlag;
import br.ufrn.imd.modelo.Nodo;

public class ArvoreView {

	public static void main(String[] args) {
		ArvoreABB abb = new ArvoreABB(new Nodo(10));
		abb.getRaiz().setEsq(new Nodo(5));
		abb.getRaiz().setDir(new Nodo(15));
		abb.getRaiz().getEsq().setDir(new Nodo(8));
		abb.getRaiz().getDir().setEsq(new Nodo(14));
		abb.getRaiz().getDir().setDir(new Nodo(18));
		System.out.println("PRE-ORDEM");
		abb.preOrdem(VisitarFlag.IMPRIMIR);
		System.out.println();
		System.out.println("SIMETRICA");
		abb.ordemSimetrica(VisitarFlag.IMPRIMIR);
		System.out.println();
		System.out.println("POS-ORDEM");
		abb.posOrdem(VisitarFlag.IMPRIMIR);
	}
}
