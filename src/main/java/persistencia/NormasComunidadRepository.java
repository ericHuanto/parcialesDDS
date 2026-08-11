package persistencia;

import dominio.NormaComunidad;
import java.util.ArrayList;
import java.util.List;

// Esto lo usan los admins.
public class NormasComunidadRepository {
  private final static NormasComunidadRepository INSTANCE = new NormasComunidadRepository();
  private final List<NormaComunidad> normas;

  private NormasComunidadRepository() {
    this.normas = new ArrayList<>();
  }

  public static NormasComunidadRepository getInstance() {
    return INSTANCE;
  }

  public void agregar(NormaComunidad normaComunidad) {
    this.normas.add(normaComunidad);
  }

  public NormaComunidad buscarPorNombre(String nombre) {
    return this.normas.stream().filter(c -> c.getNombre().equals(nombre)).findFirst().orElseThrow();
  }

}
