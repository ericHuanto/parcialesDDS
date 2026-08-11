package dominio;

public class PlataformaTwich implements PlataFormaMensajeria {
  private ProveedorTwich proveedor;

  public PlataformaTwich(ProveedorTwich proveedor) {
    this.proveedor = proveedor;
  }

  @Override
  public void enviarMensaje(Mensaje mensaje) {
    this.proveedor.enviarMensaje(mensaje);
  }

  @Override
  public void editarMensaje(Mensaje mensaje) {
    this.proveedor.editarMensaje(mensaje);
  }

  @Override
  public void eliminarMensaje(Mensaje mensaje) {
    this.proveedor.eliminarMensaje(mensaje);
  }

}
