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

  public void agregar(Canal canal) { //Esta función se usa después de crear un canal para el usuario, en el controller o si somos más puristas en el service.
    this.canales.add(canal);
  }

  public Canal buscarPorNombre(String nombre) {
    return canales.stream().filter(c -> c.getNombre().equals(nombre)).toList().get(0);
  }

  public List<Canal> buscarTodos() {
    return this.canales;
  }

  public List<Transmision> buscarTodasTransmisionesEnCurs() {
    return this.canales.stream().filter(c -> c.estaEnLive())
        .map(c -> c.getTransmisionEnCurso())
        .toList();
  }

}
