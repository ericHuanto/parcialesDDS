package dominio;

public class Habilidad {
  private String titulo;
  private String codigo;
  private String descripcion;

  public Habilidad(String titulo, String descripcion) {
    this.titulo = titulo;
    this.codigo = normalizar(titulo);
    this.descripcion = descripcion;
  }

  public Boolean esIgual(Habilidad otra) {
    return this.codigo.equals(otra.codigo);
  }

  //==================== FUNCIONES AUXILIARES ===========================
  private String normalizar(String titulo) {
    return titulo==null ?
        "" : titulo.trim()
        .toLowerCase()
        .replace('á', 'a')
        .replace('é', 'e')
        .replace('í', 'i')
        .replace('ó', 'o')
        .replace('ú', 'u')
        .replace('ü', 'u')
        .replaceAll("\\s+", "_")
        .replaceAll("[^a-z0-9_]", "");
  }

}
