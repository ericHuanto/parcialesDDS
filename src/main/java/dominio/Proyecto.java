package dominio;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;

@Getter
public class Proyecto {
  private String titulo;
  private String descripcion;
  private List<Habilidad> habilidadesNecesarias;
  private NivelCompromiso nivelCompromiso;
  private Remuneracion remuneracion;
  private List<Colaboradora> colaboradores;

  public Proyecto(String titulo, String descripcion, List<Habilidad> habilidadesNecesarias,
                  NivelCompromiso compromiso, Remuneracion remuneracion) {
    this.titulo = titulo;
    this.descripcion = descripcion;
    this.habilidadesNecesarias = new ArrayList<>(habilidadesNecesarias);
    this.nivelCompromiso = compromiso;
    this.remuneracion = remuneracion;
    this.colaboradores = new ArrayList<>();
  }

  public void registrarColaboradora(Colaboradora colaboradora) {
    if (!colaboradora.puedeAnotarseA(this))
      throw new RuntimeException("Colaboradora no puede anotarse al proyecto");

    this.colaboradores.add(colaboradora);
  }

}
