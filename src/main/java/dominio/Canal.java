package dominio;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;

@Getter
public class Canal {
  private String nombre;
  private Usuario dueñoCanal;
  private List<Usuario> suscriptores;
  private Transmision transmisionEnCurso;
  private List<Transmision> historialTransmisiones;
  private List<MuestraApoyo> donacionesRecibidas;
  private List<AccionModeracion> accionesRealizadas;
  private List<UsuarioCastigado> baneados;

  public Canal(String nombre, Usuario dueñoCanal) {
    this.nombre = nombre;
    this.dueñoCanal = dueñoCanal;
    this.suscriptores = new ArrayList<>();
    this.transmisionEnCurso = null;
    this.historialTransmisiones = new ArrayList<>();
    this.donacionesRecibidas = new ArrayList<>();
    this.baneados = new ArrayList<>();
    this.accionesRealizadas = new ArrayList<>();
  }

  public void agregarSuscriptor(Usuario usuario) {
    this.suscriptores.add(usuario);
  }

  public void iniciarTransmision(Transmision transmision) {
    if (estaEnLive())
      throw new RuntimeException("No puedes iniciar Live, porque ya tiene una transmisión en curso");

    this.transmisionEnCurso = transmision;
  }

  public void finalizarTransmision() {
    this.transmisionEnCurso.finalizar();
    this.historialTransmisiones.add(this.transmisionEnCurso);
    this.transmisionEnCurso = null;
  }

  public Boolean estaEnLive() {
    return this.transmisionEnCurso != null;
  }

  public void recibirDonacion(MuestraApoyo muestraApoyo) {
    this.donacionesRecibidas.add(muestraApoyo);
  }

  public void aplicarModeracion(AccionModeracion accion) {
    accion.ejecutar();
    this.accionesRealizadas.add(accion);
  }

  public void aplicarModeracion(AccionModeracion... acciones) {
    for (AccionModeracion accion : acciones) {
      aplicarModeracion(accion);
    }
  }

  public void cancelarModeracion(AccionModeracion accion) {
    //this.accionesRealizadas.remove(accion);
    accion.deshacer();
  }

  public void banear(Usuario usuario, Duracion duracion) {
    UsuarioCastigado castigado = this.baneados.stream().filter(b -> b.getUsuario().esIgual(usuario)).findFirst().orElse(null);
    if (castigado == null) {
      castigado = new UsuarioCastigado(usuario);
      this.baneados.add(castigado);
    }
    castigado.getDuracionesCastigo().add(duracion);
  }

  public void desBanear(Usuario usuario, Duracion duracion) {
    UsuarioCastigado castigado = this.baneados.stream().filter(b -> b.getUsuario().esIgual(usuario)).findFirst().orElseThrow(() -> new RuntimeException("No puedo deshacer un usuario que no esta baneado"));
    castigado.getDuracionesCastigo().remove(duracion);
    if (castigado.getDuracionesCastigo().isEmpty()) {
      this.baneados.remove(castigado);
    }
  }

  public Boolean estaBaneado(Usuario usuario) {
    UsuarioCastigado castigado = this.baneados.stream().filter(b -> b.getUsuario().esIgual(usuario)).findFirst().orElse(null);
    if (castigado == null) { return false; }
    return castigado.sigueCastigado();
  }

}
