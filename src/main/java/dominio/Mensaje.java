package dominio;

public class Mensaje {
  private String texto;
  private Usuario propietario;

  public Mensaje(String texto) {
    this.texto = texto;
  }

  public Mensaje(String texto, Usuario usuario) {
    this.texto = texto;
    this.propietario = usuario;
  }

}
