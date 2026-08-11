package dominio;

public interface PlataFormaMensajeria {
  void enviarMensaje(Mensaje mensaje);
  void editarMensaje(Mensaje mensaje);
  void eliminarMensaje(Mensaje mensaje);
}
