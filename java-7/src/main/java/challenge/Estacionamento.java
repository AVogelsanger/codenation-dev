package challenge;

import java.util.ArrayList;
import java.util.List;

public class Estacionamento {

    private int vagas = 10;
    private int maioridade = 18;
    private int pontuacaoInfracao = 21;

    List<Carro> carros = new ArrayList<>();

    public void estacionar(Carro carro) {

        if (carro.getMotorista() == null
                || carro.getPlaca() == null
                || carro.getCor() == null
                || carro.getMotorista().getPontos() > pontuacaoInfracao
                || carro.getMotorista().getIdade() < maioridade)
            throw new EstacionamentoException("Esse veículo e/ou mot não estão de acordo com nossas"
                    + " diretrizes, proíba sua entrada.");

        if (carros.size() >= vagas) {
            Carro retirado = carros.stream()
                    .filter(auto -> auto.getMotorista().getIdade() < 56)
                    .findFirst()
                    .orElseThrow(() -> new EstacionamentoException("Não há vagas disponíveis, "
                            + "estacionamento lotado!"));
            carros.remove(retirado);
        }
        carros.add(carro);
    }

    public int carrosEstacionados() {
        return carros.size();
    }

    public boolean carroEstacionado(Carro carro) {
        return carros.contains(carro);
    }
}
