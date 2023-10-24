package br.ufrn.imd.modelo;

public class Nodo<T> {
	private T valor;
	private int altura = 1;
	private Nodo<T> esquerdo = null;
	private Nodo<T> direito = null;
	private int nodosEsquerda = 0;
	private int nodosDireita = 0;

	public Nodo(T valor) {
		this.valor = valor;
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

	public int getNodosEsquerda() {
		return nodosEsquerda;
	}

	public void setNodosEsquerda(int nodosEsquerda) {
		this.nodosEsquerda = nodosEsquerda;
	}

	public int getNodosDireita() {
		return nodosDireita;
	}

	public void setNodosDireita(int nodosDireita) {
		this.nodosDireita = nodosDireita;
	}
}
