package dominio;

import lombok.Getter;

@Getter
public class Mensaje {
  private String texto;
  private Usuario propietario;
  private Boolean esVisible;

  public Mensaje(String texto, Usuario usuario) {
    this.texto = texto;
    this.propietario = usuario;
    this.esVisible = true;
  }

  public void ocultar() {
    this.esVisible = false;
  }

  public void desOcultar() {
    this.esVisible = true;
  }

}
