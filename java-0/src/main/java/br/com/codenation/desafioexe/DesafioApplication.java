package br.com.codenation.desafioexe;

import java.util.ArrayList;
import java.util.List;

public class DesafioApplication {

	public static Integer fibonacci(int n) {
		if (n == 0 || n == 1)
			return n;
		return fibonacci(n-1) + fibonacci(n-2);
	}

	public static List<Integer> fibonacci() {
		List<Integer> fib = new ArrayList<Integer>();
		for (int i = 0; i < 15; i++) {
			fib.add(fibonacci(i));
		}
		return fib;
	}

	public static Boolean isFibonacci(Integer a) {
		return fibonacci().contains(a);
	}

}