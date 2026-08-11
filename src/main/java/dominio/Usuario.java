package dominio;

import lombok.Getter;

@Getter
public class Usuario {
  private String nombre;

  public Usuario(String nombre) {
    this.nombre = nombre;
  }

  public String getNombre() {
    return this.nombre;
  }

  public Boolean esIgual(Usuario otro) {
    return this.nombre.equals(otro.getNombre());
  }

}
