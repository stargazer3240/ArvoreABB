// SPDX-FileCopyrightText: 2023 Fabrício Moura Jácome
// SPDX-FileCopyrightText: 2023 Ramon Oliveira de Almeida
//
// SPDX-License-Identifier: MIT

package br.ufrn.imd.modelo;

import java.util.ArrayDeque;

public class ArvoreABB {
	private Nodo raiz;

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
		System.out.print(n.getValor() + " ");
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
			n.setDir(inserir(n.getDir(), valor));
		return n;
	}

	// Deletar um no da arvore
	public void deletarChave(int chave) {
		raiz = deletarRecursivo(raiz, chave);
	}
	
	// Funcao recursiva utilizada para deletar um no
	public Nodo deletarRecursivo(Nodo raiz, int valor) {
		// Se a arvore estiver vazia
		if(raiz == null) {
			return raiz;
		}
		// Percorrer a arvore
		// Percorrer pela subarvore esquerda
		if(valor < raiz.getValor()) {
			raiz.setEsq(deletarRecursivo(raiz.getEsq(), valor));
		}
		// Percorrer pela subarvore direita
		else if(valor > raiz.getValor()) {
			raiz.setDir(deletarRecursivo(raiz.getDir(), valor));
		}
		else {
			// Se o no possui apenas um filho
			if(raiz.getEsq() == null) {
				return raiz.getDir();
			}
			else if(raiz.getDir() == null) {
				return raiz.getEsq();
			}
			// Se o no possui dois filhos
			// Pegar o sucessor de menor valor da subarvore da direita
			raiz.setValor(menorValor(raiz.getDir()));
			
			// Deletar o sucessor de menor valor
			raiz.setDir(deletarRecursivo(raiz.getDir(), raiz.getValor()));
		}
		return raiz;
	}
	
	public int menorValor(Nodo raiz) {
		// Inicialmente, a menor chave eh a propria raiz
		int menorChave = raiz.getValor();
		// Vamos encontrar e retornar a menorChave
		while(raiz.getEsq() != null) {
			menorChave = raiz.getEsq().getValor();
			raiz = raiz.getEsq();
		}
		return menorChave;
	}

	public boolean buscaArvoreBinaria(int chave) {
		
		Nodo temp = raiz;

		temp = buscar(temp, chave);
		
		if(temp != null) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public Nodo buscar(Nodo raiz, int valor) {

		if(raiz == null || raiz.getValor() == valor) {
			return raiz;
		}
		if(raiz.getValor() > valor) {
			return buscar(raiz.getEsq(), valor);
		}
		return buscar(raiz.getDir(), valor);
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
