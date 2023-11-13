// SPDX-FileCopyrightText: 2023 Fabrício Moura Jácome
// SPDX-FileCopyrightText: 2023 Ramon Oliveira de Almeida
//
// SPDX-License-Identifier: MIT

package br.ufrn.imd.modelo;

import java.util.Stack;

public class ArvoreABB {
	private Nodo raiz;

	public Nodo getRaiz() {
		return raiz;
	}

	public void setRaiz(Nodo raiz) {
		this.raiz = raiz;
	}

	public int getAlturaArvore() {
		return raiz.getAltura();
	}

	public void inserir(int valor) {
		this.raiz = inserir(raiz, valor);
		this.atribuirEsqDir();
		this.atribuirAltura();
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

	public void atribuirEsqDir() {
		calcularEsqDir(raiz, 0);
	}

	private int calcularEsqDir(Nodo n, int i) {
		if (n != null) {
			if (n.getEsq() != null) {
				i = calcularEsqDir(n.getEsq(), i);
				n.setNodosEsq(i);
			}
			i++;
			if (n.getDir() != null) {
				if (n.equals(raiz)) {
					i = calcularEsqDir(n.getDir(), 0);
					n.setNodosDir(i);
				} else {
					i = calcularEsqDir(n.getDir(), i);
					n.setNodosDir(i - n.getNodosEsq() - 1);
				}
			}
		}
		return i;
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
		this.atribuirEsqDir();
		this.atribuirAltura();
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
		return enesimoElemento(raiz, n);
	}

	private int enesimoElemento(Nodo nodo, int n) {
		if (nodo != null) {
			int nodosEsq = nodo.getNodosEsq();
			System.out.println("banana");
			if (n <= nodosEsq) {
				return enesimoElemento(nodo.getEsq(), n);
			} else if (n == nodo.getNodosEsq() + 1) {
				return nodo.getValor();
			} else {
				return enesimoElemento(nodo.getDir(), n - nodosEsq - 1);
			}
		}
		return -1;
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
			int qtdNodos = raiz.getNodosEsq() + raiz.getNodosDir() + 1;
			System.out.println("TESTE          QtdNodos " + qtdNodos + " " + (qtdNodos / 2) + " " + (qtdNodos / 2 + 1)
					+ " " + enesimoElemento(qtdNodos / 2) + " " + enesimoElemento(qtdNodos / 2 + 1));
			if (qtdNodos % 2 == 0) {
				return enesimoElemento(qtdNodos / 2);
			}
			return enesimoElemento(qtdNodos / 2 + 1);
		}
		return 0;
	}

	public double media(int x) {
		Nodo atual = nodoPosicao(x);
		return somaNodos(atual) / 2;
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
		int qtdNodos = raiz.getNodosEsq() + raiz.getNodosDir() + 1;
		int alturaArvore = raiz.getAltura();
		return Math.pow(2, alturaArvore - 1) <= qtdNodos && qtdNodos <= Math.pow(2, alturaArvore) - 1;
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