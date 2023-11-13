package br.ufrn.imd.controle;

import java.util.Collections;
import java.util.HashSet;

import br.ufrn.imd.modelo.ArvoreABB;

public class LeitorComandos {
	private HashSet<String> comandos;
	private ArvoreABB abb;

	public LeitorComandos(ArvoreABB abb) {
		comandos = new HashSet<>();
		Collections.addAll(comandos, "ENESIMO", "POSICAO", "MEDIANA", "CHEIA", "COMPLETA", "IMPRIMA", "REMOVA", "MEDIA",
				"BUSCAR", "INSIRA", "PREORDEM");
		this.abb = abb;
	}

	public void lerComando(String input) {
		String[] strArray = input.split(" ");
		String cmd = strArray[0];
		String arg = "";
		if (strArray.length > 1) {
			arg = strArray[1];
		}
		executarComando(cmd, arg);
	}

	private void executarComando(String cmd, String arg) {
		if (comandos.contains(cmd)) {
			if (cmd.equals("ENESIMO")) {
				enesimoElemento(arg);
			} else if (cmd.equals("POSICAO")) {
				posicao(arg);
			} else if (cmd.equals("MEDIANA")) {
				mediana();
			} else if (cmd.equals("CHEIA")) {
				ehCheia();
			} else if (cmd.equals("COMPLETA")) {
				ehCompleta();
			} else if (cmd.equals("IMPRIMA")) {
				imprima(arg);
			} else if (cmd.equals("REMOVA")) {
				remova(arg);
			} else if (cmd.equals("MEDIA")) {
				media(arg);
			} else if (cmd.equals("INSIRA")) {
				insira(arg);
			} else if (cmd.equals("BUSCAR")) {
				buscar(arg);
			} else if (cmd.equals("PREORDEM")) {
				preOrdem();
			}
		} else {
			System.out.println("Comando " + cmd + " inválido!");
		}
	}

	private void enesimoElemento(String arg) {
		int pos = Integer.parseInt(arg);
		System.out.println("O valor do nó na posição " + pos + " é: " + abb.enesimoElemento(pos));
	}

	private void posicao(String arg) {
		int val = Integer.parseInt(arg);
		System.out.println("Posição do nó cujo valor é " + val + ": " + abb.posicao(val));
	}

	private void mediana() {
		System.out.println("A mediana da árvore é: " + abb.mediana());
	}

	private void ehCheia() {
		if (abb.ehCheia()) {
			System.out.println("A árvore é cheia.");
		} else {
			System.out.println("A árvore não é cheia.");
		}
	}

	private void ehCompleta() {
		if (abb.ehCompleta()) {
			System.out.println("A árvore é completa.");
		} else {
			System.out.println("A árvore não é completa.");
		}
	}

	private void imprima(String arg) {
		int formato = Integer.parseInt(arg);
		System.out.println("Imprimindo árvore no formato " + formato + ":");
		abb.imprimeArvore(formato);
	}

	private void remova(String arg) {
		int val = Integer.parseInt(arg);
		System.out.println("Removendo o nó cujo valor é " + val + ".");
		abb.deletar(val);
	}

	private void media(String arg) {
		int val = Integer.parseInt(arg);
		System.out.print("Imprimindo a média da subárvore cujo nó raiz tem valor " + val + ": " + abb.media(val));
	}

	private void insira(String arg) {
		int val = Integer.parseInt(arg);
		System.out.println("Inserindo valor " + val + " na árvore.");
		abb.inserir(val);
	}

	private void buscar(String arg) {
		int val = Integer.parseInt(arg);
		System.out.print("\nO nó com valor " + val);
		if (abb.buscar(val)) {
			System.out.print(" foi encontrado.\n");
		} else {
			System.out.print(" não foi encontrado.\n");
		}
	}

	private void preOrdem() {
		System.out.println("Percurso pré ordem: " + abb.preOrdemString());
	}
}
