// SPDX-FileCopyrightText: 2023 Fabrício Moura Jácome
// SPDX-FileCopyrightText: 2023 Ramon Oliveira de Almeida
//
// SPDX-License-Identifier: MIT

package br.ufrn.imd.modelo;

public class Nodo {
	private int valor;
	private int altura;
	private Nodo esq;
	private Nodo dir;
	private int nodosEsq;
	private int nodosDir;

	public Nodo(int valor) {
		this.valor = valor;
		this.altura = 1;
		Nodo esq = null;
		Nodo dir = null;
		nodosEsq = 0;
		nodosDir = 0;
	}

	public int getValor() {
		return valor;
	}

	public void setValor(int valor) {
		this.valor = valor;
	}

	public int getAltura() {
		return altura;
	}

	public void setAltura(int altura) {
		this.altura = altura;
	}

	public Nodo getEsq() {
		return esq;
	}

	public void setEsq(Nodo esq) {
		this.esq = esq;
	}

	public Nodo getDir() {
		return dir;
	}

	public void setDir(Nodo dir) {
		this.dir = dir;
	}

	public int getNodosEsq() {
		return nodosEsq;
	}

	public void setNodosEsq(int nodosEsq) {
		this.nodosEsq = nodosEsq;
	}

	public int getNodosDir() {
		return nodosDir;
	}

	public void setNodosDir(int nodosDir) {
		this.nodosDir = nodosDir;
	}

}
