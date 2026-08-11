package dominio;

import lombok.Getter;

@Getter
public class Usuario {
  private String nombre;

  public Usuario(String nombre) {
    this.nombre = nombre;
  }

  public Boolean esIgual(Usuario otro) {
    return this.nombre.equals(otro.getNombre());
  }

}
