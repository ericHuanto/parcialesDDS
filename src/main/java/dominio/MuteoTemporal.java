package dominio;


import java.time.LocalDateTime;

public class MuteoTemporal implements AccionModeracion {
  private Usuario usuario;
  private Integer minutos;
  private NormaComunidad motivo;
  private Transmision transmision;
  private Duracion duracion;

  public MuteoTemporal(Usuario usuario, Integer minutos, NormaComunidad motivo, Transmision transmision) {
    this.usuario = usuario;
    this.minutos = minutos;
    this.motivo = motivo;
    this.transmision = transmision;
    this.duracion = new Temporalmente(LocalDateTime.now().plusMinutes(this.minutos));
  }

  @Override
  public void ejecutar() {
    this.transmision.mutear(usuario, duracion);
  }

  @Override
  public void deshacer() {
    this.transmision.desMutear(usuario, duracion);
  }

}
