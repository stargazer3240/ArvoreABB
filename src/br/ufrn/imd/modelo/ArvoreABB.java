// SPDX-FileCopyrightText: 2023 Fabrício Moura Jácome
// SPDX-FileCopyrightText: 2023 Ramon Oliveira de Almeida
//
// SPDX-License-Identifier: MIT

package br.ufrn.imd.modelo;

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

	public void enesimoElemento(int n) {
		int enesimo = enesimoElemento(raiz, n);
		if (enesimo != -1) {
			System.out.println(enesimo);
		}
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

	public void imprimirPreOrdem() {
		imprimirPreOrdemRec(raiz);
	}
	public void imprimirPreOrdemRec(Nodo nodo) {
		if (nodo != null) {
			System.out.print(nodo.getValor() + " ");
			imprimirPreOrdemRec(nodo.getEsq());
			imprimirPreOrdemRec(nodo.getDir());
		}
	}
	public void imprimirOrdemSimetrica(){
		imprimirOrdemSimetricaRec(raiz);
	}
	private void imprimirOrdemSimetricaRec(Nodo nodo)
    {
		if(nodo != null){
			imprimirOrdemSimetricaRec(nodo.getEsq());
			System.out.print(nodo.getValor() + " ");
			imprimirOrdemSimetricaRec(nodo.getDir());
		}
	}
	public void imprimirPosOrdem() {
		imprimirPosOrdemRec(raiz);
	}
	public void imprimirPosOrdemRec(Nodo nodo) {
		if (nodo != null) {
			imprimirPosOrdemRec(nodo.getEsq());
			imprimirPosOrdemRec(nodo.getDir());
			System.out.print(nodo.getValor() + " ");
		}
	}
}