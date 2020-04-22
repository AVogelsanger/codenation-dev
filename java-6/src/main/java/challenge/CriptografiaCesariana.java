package challenge;

public class CriptografiaCesariana implements Criptografia {

    @Override
    public String criptografar(String texto) {

        if (texto.isEmpty()) {
            throw new IllegalArgumentException();
        }

        char[] c = texto.toCharArray();
        String encrypt = "";

        for (char letras : c) {
            if (Character.isLetter(letras)) {
                char somaCarac = (char) (letras + 3);
                encrypt += somaCarac;
            } else {
                encrypt += letras;
            }
        }
        return encrypt.toLowerCase();
    }

    @Override
    public String descriptografar(String texto) {

        if (texto.isEmpty()) {
            throw new IllegalArgumentException();
        }

        char[] c = texto.toCharArray();
        String decrypt = "";

        for (char letras : c) {
            if (Character.isLetter(letras)) {
                char somaCarac = (char) (letras - 3);
                decrypt += somaCarac;
            } else {
                decrypt += letras;
            }
        }
        return decrypt.toLowerCase();
    }
}
