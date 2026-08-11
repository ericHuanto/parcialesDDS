package dominio;

public class EliminacionMensaje implements AccionModeracion {
  private Mensaje mensaje;
  private NormaComunidad motivo;
  private Transmision transmision;

  public EliminacionMensaje(Mensaje mensaje, NormaComunidad normaIncumplida, Transmision transmision) {
    this.mensaje = mensaje;
    this.motivo = normaIncumplida;
    this.transmision = transmision;
  }

  @Override
  public void ejecutar() {
    this.transmision.eliminarMensaje(this.mensaje);
  }

  @Override
  public void deshacer() {
    this.transmision.restaurarMensaje(this.mensaje);
  }

}
