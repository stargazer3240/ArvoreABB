// SPDX-FileCopyrightText: 2023 Fabrício Moura Jácome
// SPDX-FileCopyrightText: 2023 Ramon Oliveira de Almeida
//
// SPDX-License-Identifier: MIT

package br.ufrn.imd.modelo;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Stack;

public class ArvoreABB {
	private Nodo raiz;

	private int tamanho(Nodo nodo) {
        if (nodo == null) {
            return 0;
        }
        return nodo.getTamanho();
    }

	public Nodo getRaiz() {
		return raiz;
	}

	public void setRaiz(Nodo raiz) {
		this.raiz = raiz;
	}

	public enum VisitarFlag {
		IMPRIMIR, ALTURA, VALOR
	}

	private void visitar(Nodo n, VisitarFlag f) {
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

	public void preOrdem(VisitarFlag f) {
		preOrdem(raiz, f);
	}

	public void preOrdem(Nodo n, VisitarFlag f) {
		if (n != null) {
			visitar(n, f);
			Nodo nEsq = n.getEsq();
			if (nEsq != null) {
				preOrdem(nEsq, f);
			}
			Nodo nDir = n.getDir();
			if (nDir != null) {
				preOrdem(nDir, f);
			}
		}
	}

	public void ordemSimetrica(VisitarFlag f) {
		ordemSimetrica(raiz, f);
	}

	public void ordemSimetrica(Nodo n, VisitarFlag f) {
		if (n != null) {
			if (n.getEsq() != null) {
				ordemSimetrica(n.getEsq(), f);
			}
			visitar(n, f);
			if (n.getDir() != null) {
				ordemSimetrica(n.getDir(), f);
			}
		}
	}

	public void posOrdem(VisitarFlag f) {
		posOrdem(raiz, f);
	}

	public void posOrdem(Nodo n, VisitarFlag f) {
		if (n != null) {
			Nodo nEsq = n.getEsq();
			if (nEsq != null) {
				posOrdem(nEsq, f);
			}
			Nodo nDir = n.getDir();
			if (nDir != null) {
				posOrdem(nDir, f);
			}
			visitar(n, f);
		}
	}

	public void imprimirValor(Nodo n) {
		System.out.print(n.getValor() + "  |" + n.getAltura() + "| ");
	}

	public int getAlturaArvore() {
		return raiz.getAltura();
	}

	public void atribuirAltura() {
		posOrdem(raiz, VisitarFlag.ALTURA);
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

	public void emNivel(VisitarFlag f) {
		emNivel(raiz, f);
	}

	public void emNivel(Nodo n, VisitarFlag f) {
		if (n != null) {
			ArrayDeque<Nodo> fila = new ArrayDeque<>();
			fila.add(n);
			while (!fila.isEmpty()) {
				Nodo atual = fila.poll();
				visitar(atual, f);
				Nodo atualEsq = atual.getEsq();
				if (atualEsq != null) {
					fila.add(atualEsq);
				}
				Nodo atualDir = atual.getDir();
				if (atualDir != null) {
					fila.add(atualDir);
				}
			}
		}
	}

	public void inserir(int valor) {
		this.raiz = inserir(raiz, valor);
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
		if (valor > n.getValor())
		{
			n.setDir(inserir(n.getDir(), valor));
		}

		int tam = 1 + tamanho(n.getEsq()) + tamanho(n.getDir());
		n.setTamanho(tam); 
		return n;
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

	public boolean buscar(int chave) {
		return buscar(raiz, chave) != null;
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

	public int enesimoElemento(int n) {
        return enesimoElemento(raiz, n);
    }

    private int enesimoElemento(Nodo nodo, int n) {
        int leftSize = tamanho(nodo.getEsq());
        if (n <= leftSize) {
            return enesimoElemento(nodo.getEsq(), n);
        } else if (n == leftSize + 1) {
            return nodo.getValor();
        } else {
            return enesimoElemento(nodo.getDir(), n - leftSize - 1);
        }
    }

//	public void preOrdemIteRaiz() {
//		preOrdemIterativa(raiz, VisitarFlag.IMPRIMIR);
//	}
//
//	public void preOrdemIterativa(Nodo<T> n, VisitarFlag f) {
//		if (n != null) {
//			ArrayDeque<Nodo<T>> pilha = new ArrayDeque<>();
//			pilha.push(n);
//			while (!pilha.isEmpty()) {
//				Nodo<T> atual = pilha.pop();
//				visitar(atual, f);
//				Nodo<T> atualDir = atual.getDireito();
//				if (atualDir != null) {
//					pilha.push(atualDir);
//				}
//				Nodo<T> atualEsq = atual.getEsquerdo();
//				if (atualEsq != null) {
//					pilha.push(atualEsq);
//				}
//			}
//		}
//	}
//
//
//	public void posOrdemIteRaiz() {
//		posOrdemIterativa(raiz, VisitarFlag.IMPRIMIR);
//	}
//
//	public void posOrdemIterativa(Nodo<T> n, VisitarFlag f) {
//		if (n != null) {
//			ArrayDeque<Nodo<T>> pilha = new ArrayDeque<>();
//			ArrayDeque<Nodo<T>> pilha2 = new ArrayDeque<>();
//			pilha.push(n);
//			while (!pilha.isEmpty()) {
//				Nodo<T> atual = pilha.pop();
//				pilha2.push(atual);
//				Nodo<T> atualEsq = atual.getEsquerdo();
//				if (atualEsq != null) {
//					pilha.push(atualEsq);
//				}
//				Nodo<T> atualDir = atual.getDireito();
//				if (atualDir != null) {
//					pilha.push(atualDir);
//				}
//			}
//			while (!pilha2.isEmpty()) {
//				visitar(pilha2.pop(), f);
//			}
//		}
//	}
}
