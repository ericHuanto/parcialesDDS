package dominio;

import java.util.ArrayList;
import java.util.List;

public class Canal {
  private String nombre;
  private List<Usuario> suscriptores;
  private Transmision transmisionEnCurso;
  private List<Transmision> historialTransmisiones;
  private List<MuestraApoyo> donacionesRecibidas; // Valores anónimos de 1 a 10

  public Canal(String nombre) {
    this.nombre = nombre;
    this.suscriptores = new ArrayList<>();
    this.transmisionEnCurso = null;
    this.historialTransmisiones = new ArrayList<>();
    this.donacionesRecibidas = new ArrayList<>();
  }

  public String getNombre() {
    return this.nombre;
  }

  public Transmision getTransmisionEnCurso() {
    return this.transmisionEnCurso;
  }

  public void agregarSuscriptor(Usuario usuario) {
    this.suscriptores.add(usuario);
  }

  public void iniciarTransmision(Transmision transmision) {
    this.transmisionEnCurso = transmision;
  }

  public void finalizarTransmision() {
    this.transmisionEnCurso.finalizar();
    this.historialTransmisiones.add(this.transmisionEnCurso);
    this.transmisionEnCurso = null;
  }

  public void recibirDonacion(MuestraApoyo muestraApoyo) {
    this.donacionesRecibidas.add(muestraApoyo);
  }

}
