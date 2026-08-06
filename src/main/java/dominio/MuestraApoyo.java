package dominio;

public class MuestraApoyo {
  private Integer monto;
  private Usuario donador;

  public MuestraApoyo(int monto, Usuario donador) {
    this.monto = monto;
    this.donador = donador;
  }

  public MuestraApoyo(int monto) {
    this.monto = monto;
    this.donador = null;
  }

}
