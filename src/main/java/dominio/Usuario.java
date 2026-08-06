package dominio;

public class Usuario {
  private String nombre;
  private Canal canal;

  public Usuario(String nombre) {
    this.nombre = nombre;
  }

  public String getNombre() {
    return this.nombre;
  }

  public void crearCanal(Canal canal) {
    this.canal = canal;
  }

  public Boolean esIgual(Usuario otro) {
    return this.nombre.equals(otro.getNombre());
            //&& this.canal.esIgual(otro.getCanal);
  }

}
