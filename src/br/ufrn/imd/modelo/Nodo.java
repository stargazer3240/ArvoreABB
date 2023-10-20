package br.ufrn.imd.modelo;

public class Nodo<T> {
	private T valor;
	private int altura = 1;
	private Nodo<T> esquerdo = null;
	private Nodo<T> direito = null;

	public Nodo(T valor) {
		this.valor = valor;
	}

	public int getAltura() {
		return altura;
	}

	public void setAltura(int altura) {
		this.altura = altura;
	}

	public T getValor() {
		return valor;
	}

	public void setValor(T valor) {
		this.valor = valor;
	}

	public Nodo<T> getEsquerdo() {
		return esquerdo;
	}

	public void setEsquerdo(Nodo<T> esquerdo) {
		this.esquerdo = esquerdo;
	}

	public Nodo<T> getDireito() {
		return direito;
	}

	public void setDireito(Nodo<T> direito) {
		this.direito = direito;
	}
}
