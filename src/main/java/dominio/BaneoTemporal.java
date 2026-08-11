package dominio;

import java.time.LocalDateTime;

public class BaneoTemporal implements AccionModeracion {
  private Usuario usuario;
  private Integer minutos;
  private NormaComunidad motivo;
  private Canal canal;
  private Duracion duracion;

  public BaneoTemporal(Canal canal, Usuario usuario, NormaComunidad normaIncumplida, Integer minutos) {
    this.canal = canal;
    this.usuario = usuario;
    this.motivo = normaIncumplida;
    this.minutos = minutos;
    this.duracion = new Temporalmente(LocalDateTime.now().plusMinutes(minutos));
  }

  @Override
  public void ejecutar() {
    this.canal.banear(usuario, duracion);
  }

  @Override
  public void deshacer() {
    this.canal.desBanear(usuario, duracion);
  }

}
