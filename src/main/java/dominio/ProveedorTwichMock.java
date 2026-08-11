package dominio;

public class ProveedorTwichMock implements ProveedorTwich {

  public ProveedorTwichMock() { }

  @Override
  public void enviarMensaje(Mensaje mensaje) {
    System.out.println("se envio a twich " + mensaje.getTexto());
  }

  @Override
  public void editarMensaje(Mensaje mensaje) {
    System.out.println("se va a editar de twich " + mensaje.getTexto());
  }

  @Override
  public void eliminarMensaje(Mensaje mensaje) {
    System.out.println("se va a eliminar de twich " + mensaje.getTexto());
  }
}
