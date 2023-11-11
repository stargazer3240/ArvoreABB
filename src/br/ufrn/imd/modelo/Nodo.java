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
	private int qtdNodosEsq;
	private int qtdNodosDir;

	public Nodo(int valor) {
		this.valor = valor;
		this.altura = 1;
		esq = null;
		dir = null;
		qtdNodosEsq = 0;
		qtdNodosDir = 0;
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
		return qtdNodosEsq;
	}

	public void setNodosEsq(int qtdNodosEsq) {
		this.qtdNodosEsq = qtdNodosEsq;
	}

	public int getNodosDir() {
		return qtdNodosDir;
	}

	public void setNodosDir(int qtdNodosDir) {
		this.qtdNodosDir = qtdNodosDir;
	}
}
