package dominio;

import lombok.Getter;

@Getter
//como conocemos a danny le preguntamos como es la clase Idioma
public class Idioma {
  private String nombre;

  public Idioma(String nombre) {
    this.nombre = nombre;
  }

  public Boolean esIgual(Idioma otro) {
    return this.nombre.equalsIgnoreCase(otro.getNombre());
  }

}
