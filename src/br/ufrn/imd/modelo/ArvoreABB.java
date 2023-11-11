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

	public void imprimir(Nodo n) {
		System.out.println(n.getValor() + " ");
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
		percursoPosOrdem(raiz);
	}

	public void percursoPosOrdem(Nodo n) {
		if (n != null) {
			Nodo nEsq = n.getEsq();
			if (nEsq != null) {
				percursoPosOrdem(nEsq);
			}
			Nodo nDir = n.getDir();
			if (nDir != null) {
				percursoPosOrdem(nDir);
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

	public Nodo buscar(Nodo n, int valor) {

		if (n == null || n.getValor() == valor) {
			return n;
		}
		if (n.getValor() > valor) {
			return buscar(n.getEsq(), valor);
		}
		return buscar(n.getDir(), valor);
	}

	// Deletar um no da arvore
	public void deletar(int chave) {
		raiz = deletar(raiz, chave);
	}

	// Funcao recursiva utilizada para deletar um no
	public Nodo deletar(Nodo n, int valor) {
		if (n == null) {
			return n;
		}
		// Percorrer a arvore
		// Percorrer pela subarvore esquerda
		if (valor < n.getValor()) {
			n.setEsq(deletar(n.getEsq(), valor));
		}
		// Percorrer pela subarvore direita
		else if (valor > n.getValor()) {
			n.setDir(deletar(n.getDir(), valor));
		} else {
			// Se o no possui apenas um filho
			if (n.getEsq() == null) {
				return n.getDir();
			} else if (n.getDir() == null) {
				return n.getEsq();
			}
			// Se o no possui dois filhos
			// Pegar o sucessor de menor valor da subarvore da direita
			n.setValor(menorValor(n.getDir()));

			// Deletar o sucessor de menor valor
			n.setDir(deletar(n.getDir(), n.getValor()));
		}
		return n;
	}

	public int menorValor(Nodo n) {
		// Inicialmente, a menor chave eh a propria raiz
		int menorChave = n.getValor();
		// Vamos encontrar e retornar a menorChave
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

	public Nodo nodoPosicao(int x) {
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

	public int mediana() {
		if (raiz != null) {
			int qtdNodos = raiz.getNodosEsq() + raiz.getNodosDir() + 1;
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

	public double somaNodos(Nodo raiz) {
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
			imprimeArvoreFormato1(raiz, 0);
		} else if (flag == 2) {
			imprimeArvoreFormato2(raiz);
		} else {
			System.out.println("Formato não suportado.");
		}
	}

	private void imprimeArvoreFormato1(Nodo n, int nivel) {
		if (n != null) {
			imprimeArvoreFormato1(n.getDir(), nivel + 1);

			for (int i = 0; i < nivel; i++) {
				System.out.print("\t");
			}

			System.out.println(n.getValor() + "-------------");

			imprimeArvoreFormato1(n.getEsq(), nivel + 1);
		}
	}

	private void imprimeArvoreFormato2(Nodo n) {
		if (n != null) {
			System.out.print("(" + n.getValor());
			imprimeArvoreFormato2(n.getEsq());
			imprimeArvoreFormato2(n.getDir());
			System.out.print(")");
		}
	}

	public void imprimirPreOrdem() {
		System.out.println("Arvore em pre-ordem:");
		imprimirPreOrdemRec(raiz);
		System.out.println();
	}

	public void imprimirPreOrdemRec(Nodo nodo) {
		if (nodo != null) {
			System.out.print(nodo.getValor() + " ");
			imprimirPreOrdemRec(nodo.getEsq());
			imprimirPreOrdemRec(nodo.getDir());
		}
	}

	public void imprimirOrdemSimetrica() {
		System.out.println("Arvore em ordem simetrica:");
		imprimirOrdemSimetricaRec(raiz);
		System.out.println();
	}

	private void imprimirOrdemSimetricaRec(Nodo nodo) {
		if (nodo != null) {
			imprimirOrdemSimetricaRec(nodo.getEsq());
			System.out.print(nodo.getValor() + " ");
			imprimirOrdemSimetricaRec(nodo.getDir());
		}
	}

	public void imprimirPosOrdem() {
		System.out.println("Arvore em pos-ordem:");
		imprimirPosOrdemRec(raiz);
		System.out.println();
	}

	public void imprimirPosOrdemRec(Nodo nodo) {
		if (nodo != null) {
			imprimirPosOrdemRec(nodo.getEsq());
			imprimirPosOrdemRec(nodo.getDir());
			System.out.print(nodo.getValor() + " ");
		}
	}
}