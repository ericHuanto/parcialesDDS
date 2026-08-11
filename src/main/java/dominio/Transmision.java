package dominio;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Transmision {
  private String titulo;
  private List<Categoria> categorias;
  private LocalDateTime fechaInicio;
  private List<Usuario> participantesActuales;
  private List<Mensaje> mensajes;
  private Integer maxParticipantes;
  private Canal canal;
  private List<UsuarioCastigado> muteados;
  private List<Preferencia> preferenciasMensaje;
  private Idioma idioma;
  private PlataFormaMensajeria plataforma;

  public Transmision(String titulo, List<Categoria> categorias, Canal canal, Idioma idioma, PlataFormaMensajeria plataforma) {
    this.titulo = titulo;
    this.categorias = categorias;
    this.fechaInicio = LocalDateTime.now();
    this.participantesActuales = new ArrayList<>();
    this.mensajes = new ArrayList<>();
    this.maxParticipantes = 0;
    this.canal = canal;
    this.muteados = new ArrayList<>();
    this.preferenciasMensaje = new ArrayList<>();
    this.idioma = idioma;
    this.plataforma = plataforma;
  }

  public void recibirParticipante(Usuario usuario) {
    if (seEncuentraComoEspectador(usuario))
      throw new RuntimeException("ya te encuentras en la transmisión");

    this.participantesActuales.add(usuario);

    if (this.participantesActuales.size() >= this.maxParticipantes) {
      this.maxParticipantes = this.participantesActuales.size();
    }
  }

  public void sacarParticipante(Usuario usuario) {
    this.participantesActuales.remove(usuario);
  }

  public void recibirMensaje(Mensaje mensaje) {
    if (!seEncuentraComoEspectador(mensaje.getPropietario()))
      throw new RuntimeException("debes unirte como espectador para poder mandar mensaje");
    if (this.canal.estaBaneado(mensaje.getPropietario()) || this.estaMuteado(mensaje.getPropietario()))
      throw new RuntimeException("no puedes enviar mensajes, estas sancionado");
    if (!cumpleTodasLasPreferencias(mensaje))
      throw new RuntimeException("no se puede enviar el mensaje, No cumple con las preferencias de la transmisión");

    this.mensajes.add(mensaje);
    this.plataforma.enviarMensaje(mensaje);
  }

  public List<Mensaje> verChat() {
    return this.mensajes.stream().filter(m -> m.getEsVisible()).toList();
  }

  public void finalizar() {
    this.participantesActuales.forEach(p -> sacarParticipante(p));
  }

  public void mutear(Usuario usuario, Duracion duracion) {
    UsuarioCastigado castigado = this.muteados.stream().filter(m -> m.getUsuario().esIgual(usuario)).findFirst().orElse(null);
    if (castigado == null) {
      castigado = new UsuarioCastigado(usuario);
      this.muteados.add(castigado);
    }
    castigado.getDuracionesCastigo().add(duracion);
  }

  public void desMutear(Usuario usuario, Duracion duracion) {
    UsuarioCastigado castigado = this.muteados.stream().filter(m -> m.getUsuario().esIgual(usuario)).findFirst().orElseThrow(() -> new RuntimeException("No puedo deshacer un usuario que no esta muteado"));
    castigado.getDuracionesCastigo().remove(duracion);
    if (castigado.getDuracionesCastigo().isEmpty()) {
      this.muteados.remove(castigado);
    }
  }

  public Boolean estaMuteado(Usuario usuario) {
    UsuarioCastigado castigado = this.muteados.stream().filter(m -> m.getUsuario().esIgual(usuario)).findFirst().orElse(null);
    if (castigado == null) { return false; }
    return castigado.sigueCastigado();
  }

  public void editarMensaje(Mensaje mensaje) {
    this.plataforma.editarMensaje(mensaje);
  }

  public void eliminarMensaje(Mensaje mensaje) {
    mensaje.ocultar();
    this.plataforma.eliminarMensaje(mensaje);
  }

  public void restaurarMensaje(Mensaje mensaje) {
    mensaje.desOcultar();
  }

  public void agregarPreferencia(Preferencia preferencia) {
    this.preferenciasMensaje.add(preferencia);
  }

  public Boolean esHoraDeFinalizarla() {
    return LocalDateTime.now().isAfter(fechaInicio.plusHours(2));
  }

  //================== FUNCIONES AUXILIARES ====================
  private Boolean seEncuentraComoEspectador(Usuario usuario) {
    return this.participantesActuales.stream().anyMatch(u -> u.esIgual(usuario));
  }

  private Boolean cumpleTodasLasPreferencias(Mensaje mensaje) {
    return this.preferenciasMensaje.stream().allMatch(p -> p.esValido(mensaje, this.idioma));
  }

}
