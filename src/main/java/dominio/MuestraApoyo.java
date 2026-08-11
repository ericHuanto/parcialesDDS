package dominio;

public class MuestraApoyo {
  private Integer monto;
  private Usuario donador;

  public MuestraApoyo(int monto, Usuario donador) {
    if (monto <= 0 || monto > 10)
      throw new RuntimeException("Monto Incorrecto");
    this.monto = monto;
    this.donador = donador;
  }

  public MuestraApoyo(int monto) {
    this(monto, null);
  }

}
