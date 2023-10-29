// SPDX-FileCopyrightText: 2023 Fabrício Moura Jácome
// SPDX-FileCopyrightText: 2023 Ramon Oliveira de Almeida
//
// SPDX-License-Identifier: MIT

package br.ufrn.imd.modelo;

public class Nodo<T> {
	private T valor;
	private int altura;
	private Nodo<T> esq;
	private Nodo<T> dir;
	private int nodosEsq;
	private int nodosDir;

	public Nodo(T valor) {
		this.valor = valor;
		this.altura = 1;
		Nodo<T> esq = null;
		Nodo<T> dir = null;
		nodosEsq = 0;
		nodosDir = 0;
	}

	public T getValor() {
		return valor;
	}

	public void setValor(T valor) {
		this.valor = valor;
	}

	public int getAltura() {
		return altura;
	}

	public void setAltura(int altura) {
		this.altura = altura;
	}

	public Nodo<T> getEsq() {
		return esq;
	}

	public void setEsq(Nodo<T> esquerdo) {
		this.esq = esquerdo;
	}

	public Nodo<T> getDir() {
		return dir;
	}

	public void setDir(Nodo<T> direito) {
		this.dir = direito;
	}

	public int getNodosEsq() {
		return nodosEsq;
	}

	public void setNodosEsq(int nodosEsquerda) {
		this.nodosEsq = nodosEsquerda;
	}

	public int getNodosDir() {
		return nodosDir;
	}

	public void setNodosDir(int nodosDireita) {
		this.nodosDir = nodosDireita;
	}
}
