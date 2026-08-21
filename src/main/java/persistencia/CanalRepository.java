package persistencia;

import dominio.Canal;
import dominio.Transmision;
import java.util.ArrayList;
import java.util.List;

public class CanalRepository {
  private final static CanalRepository INSTANCE = new CanalRepository();
  private final List<Canal> canales;

  private CanalRepository() {
    this.canales = new ArrayList<>();
  }

  public static CanalRepository getInstancia() {
    return INSTANCE;
  }
  // Esta función se usa después de crear un canal para el usuario, en el controller o si somos más puristas en el service.
  public void agregar(Canal canal) {
    this.canales.add(canal);
  }

  public Canal buscarPorNombre(String nombre) {
    return canales.stream().filter(c -> c.getNombre().equals(nombre)).findFirst().orElseThrow();
  }

  public List<Canal> buscarTodos() {
    return this.canales;
  }

  public List<Transmision> buscarTodasTransmisionesEnCurs() {
    return this.canales.stream()
        .filter(c -> c.estaEnLive())
        .map(c -> c.getTransmisionEnCurso())
        .toList();
  }

  public List<Canal> buscarCanalesConTransmisionesAFinalizar() {
    List<Canal> canalesEnLive = this.canales.stream().filter(c -> c.estaEnLive()).toList();
    return canalesEnLive.stream().filter(c -> c.getTransmisionEnCurso().esHoraDeFinalizarla()).toList();
  }

}
