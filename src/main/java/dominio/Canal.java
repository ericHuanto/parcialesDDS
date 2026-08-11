package dominio;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;

@Getter
public class Canal {
  private String nombre;
  private Usuario dueño;
  private List<Usuario> suscriptores;
  private Transmision transmisionEnCurso;
  private List<Transmision> historialTransmisiones;
  private List<MuestraApoyo> donacionesRecibidas;

  public Canal(String nombre, Usuario usuario) {
    this.nombre = nombre;
    this.dueño = usuario;
    this.suscriptores = new ArrayList<>();
    this.transmisionEnCurso = null;
    this.historialTransmisiones = new ArrayList<>();
    this.donacionesRecibidas = new ArrayList<>();
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
