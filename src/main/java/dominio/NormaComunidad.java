package dominio;

import lombok.Getter;

@Getter
public class NormaComunidad {
  private String nombre;

  public NormaComunidad(String nombre) {
    this.nombre = nombre;
  }

}
