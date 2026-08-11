package dominio;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;

@Getter
public class UsuarioCastigado {
  private Usuario usuario;
  private List<Duracion> duracionesCastigo;

  public UsuarioCastigado(Usuario usuario) {
    this.usuario = usuario;
    this.duracionesCastigo = new ArrayList<>();
  }

  public Boolean sigueCastigado() {
    return this.duracionesCastigo.stream().anyMatch(d -> d.sigueVigente());
  }

}
