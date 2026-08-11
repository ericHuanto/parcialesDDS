package dominio;

import java.time.LocalDateTime;

public class Temporalmente implements Duracion {
  private LocalDateTime fechaFin;

  public Temporalmente(LocalDateTime fechaFin) {
    this.fechaFin = fechaFin;
  }

  @Override
  public Boolean sigueVigente() {
    LocalDateTime ahora = LocalDateTime.now();
    return ahora.isBefore(fechaFin);
  }

}
