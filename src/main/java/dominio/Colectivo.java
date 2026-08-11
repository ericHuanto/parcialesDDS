package dominio;

import java.util.ArrayList;
import java.util.List;

public class Colectivo {
  private String nombre;
  private String descripcion;
  private String ubicacion;
  private TipoColectivo tipo;
  private List<Proyecto> proyectos;

  public Colectivo(String nombre, String descripcion, String ubicacion, TipoColectivo tipoColectivo) {
    this.nombre = nombre;
    this.descripcion = descripcion;
    this.ubicacion = ubicacion;
    this.tipo = tipoColectivo;
    this.proyectos = new ArrayList<>();
  }

  public void agregarProyecto(Proyecto proyecto) {
    this.proyectos.add(proyecto);
  }

  public boolean puedeVerDatosDe(Colaboradora colaboradora) {
    return this.proyectos.stream().flatMap(p -> p.getColaboradores().stream())
        .anyMatch(c -> c.esIgual(colaboradora));
  }

}
