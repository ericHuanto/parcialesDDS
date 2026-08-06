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
  private LocalDateTime fechaFin;

  public Transmision(String titulo, List<Categoria> categorias) {
    this.titulo = titulo;
    this.categorias = categorias;
    this.fechaInicio = LocalDateTime.now();
    this.participantesActuales = new ArrayList<>();
    this.mensajes = new ArrayList<>();
    this.maxParticipantes = 0;
    this.fechaFin = null;
  }

  public void recibirParticipante(Usuario usuario) {
    if (seEncuentraComoEspectador(usuario)) {
      throw new RuntimeException("ya te encuentras en la transmision");
    }

    this.participantesActuales.add(usuario);

    if (this.participantesActuales.size() >= this.maxParticipantes) {
      this.maxParticipantes = this.participantesActuales.size();
    }
  }

  public void sacarParticipante(Usuario usuario) {
    this.participantesActuales.remove(usuario);
  }

  public void recibirMensaje(Mensaje mensaje) {
    this.mensajes.add(mensaje);
  }

  public List<Mensaje> verChat() {
    return this.mensajes;
  }

  public void finalizar() {
    this.fechaFin = LocalDateTime.now();
    this.participantesActuales.clear();
  }
  //================== FUNCIONES AUXILIARES ====================
  private boolean seEncuentraComoEspectador(Usuario usuario) {
    return this.participantesActuales.stream().anyMatch(u -> u.esIgual(usuario));
  }
}
