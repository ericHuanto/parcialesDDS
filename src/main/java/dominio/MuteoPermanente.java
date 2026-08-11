package dominio;

public class MuteoPermanente implements AccionModeracion {
  private Usuario usuario;
  private NormaComunidad motivo;
  private Transmision transmision;
  private Duracion duracion;

  public MuteoPermanente(Usuario usuario, NormaComunidad motivo, Transmision transmision) {
    this.usuario = usuario;
    this.motivo = motivo;
    this.transmision = transmision;
    this.duracion = new Permanentemente();
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
