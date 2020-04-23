package br.com.codenation.calculadora;


public class CalculadoraSalario {

	public static double inss = 0.0;
	public static double irrf = 0.0;

	public static long calcularSalarioLiquido(double salarioBase) {
		//Use o Math.round apenas no final do método para arredondar o valor final.
		//Documentação do método: https://docs.oracle.com/javase/8/docs/api/java/lang/Math.html#round-double-

		if (salarioBase < 1039.00)
			salarioBase = 0.0;
		else
			salarioBase = salarioBase - calcularInss(salarioBase);
			salarioBase = salarioBase - calcularIrrf(salarioBase);

		return Math.round(salarioBase);
	}


	//Exemplo de método que pode ser criado para separar melhor as responsábilidades de seu algorítmo
	private static double calcularInss(double salarioBase) {
		if (salarioBase < 1500.01)
			inss = 0.08;
		else if (salarioBase < 4000.01)
			inss = 0.09;
		else
			inss = 0.11;
		return salarioBase * inss;
	}

	private static double calcularIrrf(double salarioBase) {
		if (salarioBase < 3000.01)
			irrf = 0.0;
		else if (salarioBase < 6000.01)
			irrf = 0.075;
		else
			irrf = 0.15;
		return salarioBase * irrf;
	}

}
/*Dúvidas ou Problemas?
Manda e-mail para o meajuda@codenation.dev que iremos te ajudar! 
*/