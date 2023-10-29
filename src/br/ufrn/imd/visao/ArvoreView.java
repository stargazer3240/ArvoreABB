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
		ArvoreABB abb = new ArvoreABB();
		abb.inserir(45);
		abb.inserir(10);
		abb.inserir(7);
		abb.inserir(12);
		abb.inserir(90);
		abb.inserir(50);

		/*System.out.println("PRE-ORDEM");
		abb.preOrdem(VisitarFlag.IMPRIMIR);*/
		System.out.println();
		System.out.println("SIMETRICA");
		abb.ordemSimetrica(VisitarFlag.IMPRIMIR);
		System.out.println();
		/*System.out.println("POS-ORDEM");
		abb.posOrdem(VisitarFlag.IMPRIMIR);*/

		//delete leaf node  
        System.out.println("\nThe BST after Delete 12(leaf node):"); 
        abb.deletarChave(12); 
        abb.ordemSimetrica(VisitarFlag.IMPRIMIR);
        //delete the node with one child
        System.out.println("\nThe BST after Delete 90 (node with 1 child):"); 
        abb.deletarChave(90); 
        abb.ordemSimetrica(VisitarFlag.IMPRIMIR);
                 
        //delete node with two children  
        System.out.println("\nThe BST after Delete 45 (Node with two children):"); 
        abb.deletarChave(45); 
        abb.ordemSimetrica(VisitarFlag.IMPRIMIR);


		boolean ret_val = abb.buscaArvoreBinaria(50);
        System.out.println("\nKey 50 found in BST:" + ret_val );
        ret_val = abb.buscaArvoreBinaria(12);
        System.out.println("\nKey 12 found in BST:" + ret_val );
	}
}
