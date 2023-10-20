package br.ufrn.imd.modelo;

import java.util.Stack;

public class ArvoreABB<T> {
	private Nodo<T> raiz;

	public ArvoreABB(Nodo<T> n) {
		this.raiz = n;
		raiz.setAltura(1);
	}

	public Nodo<T> getRaiz() {
		return raiz;
	}

	public void setRaiz(Nodo<T> raiz) {
		this.raiz = raiz;
	}

	public void preOrdem(Nodo<T> n) {
		imprimirValor(n);
		if (n.getEsquerdo() != null) {
			preOrdem(n.getEsquerdo());
		}
		if (n.getDireito() != null) {
			preOrdem(n.getDireito());
		}
	}

	public void preOrdemRaiz() {
		preOrdem(raiz);
	}

	public void ordemSimetrica(Nodo<T> n) {
		if (n.getEsquerdo() != null) {
			ordemSimetrica(n.getEsquerdo());
		}
		imprimirValor(n);
		if (n.getDireito() != null) {
			ordemSimetrica(n.getDireito());
		}
	}

	public void ordemSimetricaRaiz() {
		ordemSimetrica(raiz);
	}

	public void posOrdem(Nodo<T> n) {
		if (n.getEsquerdo() != null) {
			posOrdem(n.getEsquerdo());
		}
		imprimirValor(n);
		if (n.getDireito() != null) {
			posOrdem(n.getDireito());
		}
	}

	private void calcularAltura(Nodo<T> n) {
		Nodo<T> nodoEsq = n.getEsquerdo();
		int alturaEsq = nodoEsq == null ? 0 : nodoEsq.getAltura();
		Nodo<T> nodoDir = n.getDireito();
		int alturaDir = nodoDir == null ? 0 : nodoDir.getAltura();
		n.setAltura(Math.max(alturaEsq, alturaDir) + 1);
	}

	public void imprimirValor(Nodo<T> n) {
		System.out.print(n.getValor() + " ");
	}

	public void posOrdemAltura(Nodo<T> n) {
		if (n.getEsquerdo() != null) {
			posOrdemAltura(n.getEsquerdo());
		}
		calcularAltura(n);
		if (n.getDireito() != null) {
			posOrdemAltura(n.getDireito());
		}
	}

	public void posOrdemRaiz() {
		posOrdem(raiz);
	}

	public void preOrdemIterativa(Nodo<T> n) {
		Stack<Nodo<T>> pilha = new Stack<>();
		pilha.push(n);
		while (!pilha.empty()) {
			Nodo<T> atual = pilha.pop();
			imprimirValor(atual);
			if (atual.getDireito() != null) {
				pilha.push(atual.getDireito());
			}
			if (atual.getEsquerdo() != null) {
				pilha.push(atual.getEsquerdo());
			}
		}
	}

	public void preOrdemIteRaiz() {
		preOrdemIterativa(raiz);
	}

	public void posOrdemIterativa(Nodo<T> n) {
		Stack<Nodo<T>> pilha = new Stack<>();
		Stack<Nodo<T>> pilha2 = new Stack<>();
		while (!pilha.empty()) {
			Nodo<T> atual = pilha.pop();
			pilha2.push(atual);
			if (atual.getEsquerdo() != null) {
				pilha.push(atual.getEsquerdo());
			}
			if (atual.getDireito() != null) {
				pilha.push(atual.getDireito());
			}
		}
		while (!pilha2.empty()) {
			imprimirValor(pilha2.pop());
		}
	}

	public void posOrdemIteRaiz() {
		posOrdemIterativa(raiz);
	}
}
