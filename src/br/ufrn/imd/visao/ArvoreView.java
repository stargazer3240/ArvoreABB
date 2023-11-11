// SPDX-FileCopyrightText: 2023 Fabrício Moura Jácome
// SPDX-FileCopyrightText: 2023 Ramon Oliveira de Almeida
//
// SPDX-License-Identifier: MIT

package br.ufrn.imd.visao;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import br.ufrn.imd.modelo.ArvoreABB;

public class ArvoreView {

	public static void main(String[] args) {
		ArvoreABB abb = new ArvoreABB();
		abb.inserir(45);
		abb.inserir(10);
		abb.inserir(7);
		abb.inserir(12);
		abb.inserir(90);
		abb.inserir(50);

		/*try {
			File arvoreInput = new File("input" + File.separator + "arvore.txt");
			Scanner scanner = new Scanner(arvoreInput);
			while (scanner.hasNext()) {
				String data = scanner.next();
				abb.inserir(Integer.parseInt(data));
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			System.out.println("Arquivo não encontrado!");
		}*/

//		try {
//			File comandosInput = new File("input" + File.separator + "comandos.txt");
//			Scanner scanner = new Scanner(comandosInput);
//			while (scanner.hasNextLine()) {
//				String data = scanner.nextLine();
//				System.out.println(data);
//			}
//			scanner.close();
//		} catch (FileNotFoundException e) {
//			System.out.println("Arquivo não encontrado!");
//		}

//		System.out.println("\nEnesimo: " + abb.enesimoElemento(3));

		System.out.println("Arvore em pre-ordem:");
		abb.imprimirPreOrdem();
		System.out.println();

		System.out.println("Arvore em ordem simetrica:");
		abb.imprimirOrdemSimetrica();
		System.out.println();

		System.out.println("Arvore em pos-ordem:");
		abb.imprimirPosOrdem();
		System.out.println();
		
//		// delete leaf node
		System.out.println();
		System.out.println("Deletar 12:");
		abb.deletar(12);
		abb.imprimirOrdemSimetrica();
		System.out.println();
		// delete the node with one child
		System.out.println("Deletar 90:");
		abb.deletar(90);
		abb.imprimirOrdemSimetrica();
		System.out.println();
		// delete node with two children
		System.out.println("Deletar 45:");
		abb.deletar(45);
		abb.imprimirOrdemSimetrica();
//		
//
//		
//		System.out.println();
//		System.out.println();
//		System.out.println("Buscar 50: " + abb.buscar(50));
//		System.out.println("Buscar 12: " + abb.buscar(12));
//		

	}
}
