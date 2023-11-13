// SPDX-FileCopyrightText: 2023 Fabrício Moura Jácome
// SPDX-FileCopyrightText: 2023 Ramon Oliveira de Almeida
//
// SPDX-License-Identifier: MIT

package br.ufrn.imd.modelo;

import java.util.Stack;

public class ArvoreABB {
	private Nodo raiz;
	private int tamanho;

	public Nodo getRaiz() {
		return raiz;
	}

	public void setRaiz(Nodo raiz) {
		this.raiz = raiz;
	}

	public void inserir(int valor) {
		this.raiz = inserir(raiz, valor);
		this.atribuirAltura();
		tamanho++;
	}

	private Nodo inserir(Nodo n, int valor) {
		if (n == null) {
			n = new Nodo(valor);
			return n;
		}
		if (valor < n.getValor()) {
			n.setEsq(inserir(n.getEsq(), valor));
		}
		if (valor > n.getValor()) {
			n.setDir(inserir(n.getDir(), valor));
		}
		return n;
	}

	public void atribuirAltura() {
		posOrdemAltura(raiz);
	}

	private void posOrdemAltura(Nodo n) {
		if (n != null) {
			Nodo nEsq = n.getEsq();
			if (nEsq != null) {
				posOrdemAltura(nEsq);
			}
			Nodo nDir = n.getDir();
			if (nDir != null) {
				posOrdemAltura(nDir);
			}
			calcularAltura(n);
		}
	}

	private void calcularAltura(Nodo n) {
		if (n != null) {
			Nodo nodoEsq = n.getEsq();
			int alturaEsq = nodoEsq == null ? 0 : nodoEsq.getAltura();
			Nodo nodoDir = n.getDir();
			int alturaDir = nodoDir == null ? 0 : nodoDir.getAltura();
			n.setAltura(Math.max(alturaEsq, alturaDir) + 1);
		}
	}

	public boolean buscar(int valor) {
		return buscar(raiz, valor) != null;
	}

	private Nodo buscar(Nodo n, int valor) {
		if (n == null || n.getValor() == valor) {
			return n;
		}
		if (n.getValor() > valor) {
			return buscar(n.getEsq(), valor);
		}
		return buscar(n.getDir(), valor);
	}

	public void deletar(int valor) {
		raiz = deletar(raiz, valor);
		this.atribuirAltura();
		tamanho--;
	}

	private Nodo deletar(Nodo n, int valor) {
		if (n == null) {
			return n;
		}
		if (valor < n.getValor()) {
			n.setEsq(deletar(n.getEsq(), valor));
		} else if (valor > n.getValor()) {
			n.setDir(deletar(n.getDir(), valor));
		} else {
			if (n.getEsq() == null) {
				return n.getDir();
			} else if (n.getDir() == null) {
				return n.getEsq();
			}
			n.setValor(menorValor(n.getDir()));
			n.setDir(deletar(n.getDir(), n.getValor()));
		}
		return n;
	}

	private int menorValor(Nodo n) {
		int menorChave = n.getValor();
		while (n.getEsq() != null) {
			menorChave = n.getEsq().getValor();
			n = n.getEsq();
		}
		return menorChave;
	}

	public int enesimoElemento(int n) {
		if (n <= tamanho) {
			Stack<Nodo> pilha = new Stack<Nodo>();
			Nodo atual = raiz;

			int i = 1;
			while (atual != null || !pilha.isEmpty()) {
				while (atual != null) {
					pilha.push(atual);
					atual = atual.getEsq();
				}
				atual = pilha.pop();
				if (i == n) {
					return atual.getValor();
				}
				atual = atual.getDir();
				i++;
			}
		}
		return 0;
	}

	public int posicao(int x) {
		Stack<Nodo> pilha = new Stack<Nodo>();
		Nodo atual = raiz;

		int posicao = 0;
		while (atual != null || !pilha.isEmpty()) {
			while (atual != null) {
				pilha.push(atual);
				atual = atual.getEsq();
			}
			atual = pilha.pop();
			posicao++;
			if (atual.getValor() == x) {
				return posicao;
			}
			atual = atual.getDir();
		}
		return 0;
	}

	public int mediana() {
		if (raiz != null) {
			if (tamanho % 2 == 0) {
				return enesimoElemento(tamanho / 2);
			}
			return enesimoElemento(tamanho / 2 + 1);
		}
		return 0;
	}

	public double media(int x) {
		Nodo atual = nodoPosicao(x);
		return somaNodos(atual) / tamanho;
	}

	// Retorna o nodo na posição x.
	private Nodo nodoPosicao(int x) {
		Stack<Nodo> pilha = new Stack<Nodo>();
		Nodo atual = raiz;

		while (atual != null || !pilha.isEmpty()) {
			while (atual != null) {
				pilha.push(atual);
				atual = atual.getEsq();
			}
			atual = pilha.pop();
			if (atual.getValor() == x) {
				return atual;
			}
			atual = atual.getDir();
		}

		return null;
	}

	// Soma os valores de todos os nodos a partir do nodo input.
	private double somaNodos(Nodo raiz) {
		Stack<Nodo> pilha = new Stack<Nodo>();
		Nodo atual = raiz;

		int soma = 0;
		while (atual != null || !pilha.isEmpty()) {
			while (atual != null) {
				pilha.push(atual);
				atual = atual.getEsq();
			}
			atual = pilha.pop();
			soma += atual.getValor();
			atual = atual.getDir();
		}

		return soma;
	}

	public boolean ehCheia() {
		return ehCompleta() && ehEstritaBinaria();
	}

	public boolean ehCompleta() {
		int alturaArvore = raiz.getAltura();
		return Math.pow(2, alturaArvore - 1) <= tamanho && tamanho <= Math.pow(2, alturaArvore) - 1;
	}

	private boolean ehEstritaBinaria() {
		Stack<Nodo> pilha = new Stack<Nodo>();
		Nodo atual = raiz;

		while (atual != null || !pilha.isEmpty()) {
			while (atual != null) {
				pilha.push(atual);
				atual = atual.getEsq();
			}
			atual = pilha.pop();
			if (atual.getAltura() != 1 && (atual.getEsq() == null || atual.getDir() == null)) {
				return false;
			}
			atual = atual.getDir();
		}

		return true;
	}

	public String preOrdemString() {
		return preOrdemString(raiz);
	}

	private String preOrdemString(Nodo n) {
		String s = "";
		Stack<Nodo> pilha = new Stack<Nodo>();
		pilha.push(raiz);
		while (!pilha.isEmpty()) {
			Nodo atual = pilha.pop();
			s += atual.getValor() + " ";
			if (atual.getDir() != null) {
				pilha.push(atual.getDir());
			}
			if (atual.getEsq() != null) {
				pilha.push(atual.getEsq());
			}
		}
		return s;
	}

	public void imprimeArvore(int flag) {
		if (flag == 1) {
			imprimeArvoreFormato1(raiz, 0, raiz.getAltura());
		} else if (flag == 2) {
			imprimeArvoreFormato2(raiz);
			System.out.println();
		} else {
			System.out.println("Formato " + flag + " não suportado.");
		}
	}

	private void imprimeArvoreFormato1(Nodo n, int nivel, int hifens) {
		if (n != null) {
			for (int i = 0; i < nivel; i++) {
				System.out.print("    ");
			}
			System.out.println(String.format("%3d", n.getValor()) + formadorHifens(1) + formadorHifens(hifens));
			imprimeArvoreFormato1(n.getEsq(), nivel + 1, hifens - 1);
			imprimeArvoreFormato1(n.getDir(), nivel + 1, hifens - 1);

		}
	}

	private String formadorHifens(int n) {
		String s = "";
		for (int i = 0; i < n; i++) {
			s += "----";
		}
		return s;
	}

	private void imprimeArvoreFormato2(Nodo n) {
		if (n != null) {
			if (n == this.raiz) {
				System.out.print("(" + n.getValor());
			} else {
				System.out.print(" (" + n.getValor());
			}
			imprimeArvoreFormato2(n.getEsq());
			imprimeArvoreFormato2(n.getDir());
			System.out.print(")");
		}
	}
}