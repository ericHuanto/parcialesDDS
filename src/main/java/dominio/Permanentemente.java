package dominio;

public class Permanentemente implements Duracion {

  public Permanentemente() { }

  @Override
  public Boolean sigueVigente() {
    return true;
  }

}
