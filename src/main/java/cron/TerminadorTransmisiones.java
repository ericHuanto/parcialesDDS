package cron;

import dominio.Canal;
import java.util.List;
import persistencia.CanalRepository;

public class TerminadorTransmisiones {

  public static void main(String[] args) {
    CanalRepository repoCanales = CanalRepository.getInstancia();
    List<Canal> canalesEnVivo = repoCanales.buscarCanalesConTransmisionesAFinalizar();

    canalesEnVivo.forEach(c -> c.finalizarTransmision());
  }
  //Nota: nose si es mejor tener doble referencia, o tener un repo más.
  //por el momento hay una doble referencia entre canal y transmisión, se podría evitas con un repo de transmisiones.
}
