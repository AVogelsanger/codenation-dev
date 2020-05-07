package br.com.codenation;
import java.util.Arrays;

public class StatisticUtil {

	/**
	 * Método que apresenta a média
	 * @param elements
	 * @return media
	 */
	public static int average(int[] elements) {
		int soma = 0;
		for (int i = 0; i < elements.length; i++) {
			soma += elements[i];
		}
		return soma / elements.length;
	}

	/**
	 * Método que retorna a moda
	 * @param elements
	 * @return moda
	 */
	public static int mode(int[] elements) {
		int moda = elements[0];
		int soma = 0;

		for (int i = 0; i < elements.length; i++) {
			int cont = 0;
			for (int j = 0; j < elements.length; j++) {
				if (elements[j] == elements[i]) {
					cont++;
				}
			}
			if (cont > soma) {
				moda = elements[i];
				soma = cont;
			}
		}
		return moda;
	}

	/**
	 * Método que retorna a mediana
	 * @param elements
	 * @return mediana
	 */
	public static int median(int[] elements) {
		Arrays.sort(elements);
		int mediana = 0;
		int div = elements.length / 2;

		if (elements.length % 2 == 0) {
			mediana = (elements[div] + elements[div - 1]) / 2;
		} else {
			mediana = elements[div];
		}
		return mediana;
	}
}