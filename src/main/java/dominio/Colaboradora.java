package dominio;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;

@Getter
public class Colaboradora {
  private String nombre;
  private String apellido;
  private String correo;
  private List<Habilidad> habilidades;

  public Colaboradora(String nombre, String apellido, String correo, List<Habilidad> habilidades) {
    this.nombre = nombre;
    this.apellido = apellido;
    this.correo = correo;
    this.habilidades = new ArrayList<>(habilidades);
  }

  public boolean puedeAnotarseA(Proyecto proyecto) {
    return this.habilidades.stream().anyMatch(myHabilidad -> seEncuentra(myHabilidad, proyecto.getHabilidadesNecesarias()));
  }

  public boolean esIgual(Colaboradora otra) {
    return this.nombre.equals(otra.getNombre())
        && this.apellido.equals(otra.getApellido())
        && this.correo.equals(otra.getCorreo())
        && this.habilidades.size() == otra.getHabilidades().size();
  }

  //================= FUNCIONES AUXILIARES ====================
  private Boolean seEncuentra(Habilidad miHabilidada, List<Habilidad> habilidadesProyecto) {
    return habilidadesProyecto.stream().anyMatch(hp -> hp.esIgual(miHabilidada));
    //si configuramos el hashcode y el equals, esto podría ser: return habilidadesProyecto.contains(miHabilidad)
  }

}
