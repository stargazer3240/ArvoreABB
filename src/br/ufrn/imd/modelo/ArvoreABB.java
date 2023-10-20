package br.ufrn.imd.modelo;

import java.util.ArrayDeque;

public class ArvoreABB<T> {
	private Nodo<T> raiz;

	public ArvoreABB(Nodo<T> n) {
		this.raiz = n;
	}

	public Nodo<T> getRaiz() {
		return raiz;
	}

	public void setRaiz(Nodo<T> raiz) {
		this.raiz = raiz;
	}

	private enum VisitarFlag {
		IMPRIMIR, ALTURA, VALOR
	}

	private void visitar(Nodo<T> n, VisitarFlag f) {
		switch (f) {
		case IMPRIMIR:
			imprimirValor(n);
			break;
		case ALTURA:
			calcularAltura(n);
			break;
		case VALOR:
			break;
		}
	}

	public void preOrdemRaiz() {
		preOrdem(raiz, VisitarFlag.IMPRIMIR);
	}

	public void preOrdem(Nodo<T> n, VisitarFlag f) {
		if (n != null) {
			visitar(n, f);
			if (n.getEsquerdo() != null) {
				preOrdem(n.getEsquerdo(), f);
			}
			if (n.getDireito() != null) {
				preOrdem(n.getDireito(), f);
			}
		}
	}

	public void preOrdemIteRaiz() {
		preOrdemIterativa(raiz, VisitarFlag.IMPRIMIR);
	}

	public void preOrdemIterativa(Nodo<T> n, VisitarFlag f) {
		if (n != null) {
			ArrayDeque<Nodo<T>> pilha = new ArrayDeque<>();
			pilha.push(n);
			while (!pilha.isEmpty()) {
				Nodo<T> atual = pilha.pop();
				visitar(atual, f);
				Nodo<T> atualDir = atual.getDireito();
				if (atualDir != null) {
					pilha.push(atualDir);
				}
				Nodo<T> atualEsq = atual.getEsquerdo();
				if (atualEsq != null) {
					pilha.push(atualEsq);
				}
			}
		}
	}

	public void ordemSimetricaRaiz() {
		ordemSimetrica(raiz, VisitarFlag.IMPRIMIR);
	}

	public void ordemSimetrica(Nodo<T> n, VisitarFlag f) {
		if (n != null) {
			if (n.getEsquerdo() != null) {
				ordemSimetrica(n.getEsquerdo(), f);
			}
			visitar(n, f);
			if (n.getDireito() != null) {
				ordemSimetrica(n.getDireito(), f);
			}
		}
	}

	public void posOrdemRaiz() {
		posOrdem(raiz, VisitarFlag.IMPRIMIR);
	}

	public void posOrdem(Nodo<T> n, VisitarFlag f) {
		if (n != null) {
			if (n.getEsquerdo() != null) {
				posOrdem(n.getEsquerdo(), f);
			}
			if (n.getDireito() != null) {
				posOrdem(n.getDireito(), f);
			}
			visitar(n, f);
		}
	}

	public void posOrdemIteRaiz() {
		posOrdemIterativa(raiz, VisitarFlag.IMPRIMIR);
	}

	public void posOrdemIterativa(Nodo<T> n, VisitarFlag f) {
		if (n != null) {
			ArrayDeque<Nodo<T>> pilha = new ArrayDeque<>();
			ArrayDeque<Nodo<T>> pilha2 = new ArrayDeque<>();
			pilha.push(n);
			while (!pilha.isEmpty()) {
				Nodo<T> atual = pilha.pop();
				Nodo<T> atualEsq = atual.getEsquerdo();
				pilha2.push(atual);
				if (atualEsq != null) {
					pilha.push(atualEsq);
				}
				Nodo<T> atualDir = atual.getDireito();
				if (atualDir != null) {
					pilha.push(atualDir);
				}
			}
			while (!pilha2.isEmpty()) {
				visitar(pilha2.pop(), f);
			}
		}
	}

	public void imprimirValor(Nodo<T> n) {
		System.out.print(n.getValor() + " ");
	}

	public int getAlturaArvore() {
		return raiz.getAltura();
	}

	public void atribuirAltura() {
		posOrdem(raiz, VisitarFlag.ALTURA);
	}

	private void calcularAltura(Nodo<T> n) {
		if (n != null) {
			Nodo<T> nodoEsq = n.getEsquerdo();
			int alturaEsq = nodoEsq == null ? 0 : nodoEsq.getAltura();
			Nodo<T> nodoDir = n.getDireito();
			int alturaDir = nodoDir == null ? 0 : nodoDir.getAltura();
			n.setAltura(Math.max(alturaEsq, alturaDir) + 1);
		}
	}

	public void emNivelRaiz() {
		emNivel(raiz, VisitarFlag.IMPRIMIR);
	}

	public void emNivel(Nodo<T> n, VisitarFlag f) {
		if (n != null) {
			ArrayDeque<Nodo<T>> fila = new ArrayDeque<>();
			fila.add(n);
			while (!fila.isEmpty()) {
				Nodo<T> atual = fila.poll();
				visitar(atual, f);
				Nodo<T> atualEsq = atual.getEsquerdo();
				if (atualEsq != null) {
					fila.add(atualEsq);
				}
				Nodo<T> atualDir = atual.getDireito();
				if (atualDir != null) {
					fila.add(atualDir);
				}
			}
		}
	}
}
