package com.sanches.alertamed.clock.api.security.util;

import java.util.Random;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class GeradorSenha {

	public static void main(String[] args) {
		int n = 6;
		for(int i=0; i < n; i++) {
			System.out.println("== "+i+" ==");
			for(int j=(i-1)+1; j < n; j++) {
				if(i != j)
					System.out.println(i + " - " + j);
			}
			System.out.println();
		}
		
	}
	
	public static void gerarSenha() {
		char senha[] = new char[12];

		for (int j = 0; j < 5; j++) {
			for (int i = 0; i < senha.length; i++) {
				senha[i] = aleatoriar(33, 125);
			}
			System.out.println(senha);
		}
		
		System.out.println();
		for (int i = 0; i < 5; i++) {
			System.out.println(encodar(String.valueOf(senha[i])));
		}
	}

	public static String encodar(String valor) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder.encode(valor);
	}

	public static char aleatoriar(int minimo, int maximo) {
		Random random = new Random();
		int valor = (random.nextInt((maximo - minimo) + 1) + minimo);

		while (true) {
			if (valor != 39 && valor != 94 && valor != 96) {
				return (char) valor;
			} else {
				valor = (random.nextInt((maximo - minimo) + 1) + minimo);
			}
		}
	}

}
