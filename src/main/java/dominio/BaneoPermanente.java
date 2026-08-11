package dominio;

public class BaneoPermanente implements AccionModeracion {
  private Usuario usuario;
  private NormaComunidad motivo;
  private Canal canal;
  private Duracion duracion;

  public BaneoPermanente(Usuario usuario, NormaComunidad normaIncumplida, Canal canal) {
    this.usuario = usuario;
    this.motivo = normaIncumplida;
    this.canal = canal;
    this.duracion = new Permanentemente();
  }

  @Override
  public void ejecutar() {
    canal.banear(usuario, duracion);
  }

  @Override
  public void deshacer() {
    canal.desBanear(usuario, duracion);
  }

}
