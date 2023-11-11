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

		try {
			File arvoreInput = new File("input" + File.separator + "arvore.txt");
			Scanner scanner = new Scanner(arvoreInput);
			while (scanner.hasNext()) {
				String data = scanner.next();
				abb.inserir(Integer.parseInt(data));
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			System.out.println("Arquivo não encontrado!");
		}

		try {
			File comandosInput = new File("input" + File.separator + "comandos.txt");
			Scanner scanner = new Scanner(comandosInput);
			while (scanner.hasNextLine()) {
				String data = scanner.nextLine();
				System.out.println(data);
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			System.out.println("Arquivo não encontrado!");
		}
	}
}
