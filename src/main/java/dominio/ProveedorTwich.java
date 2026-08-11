package dominio;

public interface ProveedorTwich {
  void enviarMensaje(Mensaje mensaje);
  void editarMensaje(Mensaje mensaje);
  void eliminarMensaje(Mensaje mensaje);
}
