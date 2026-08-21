package dominio;

import servicioexterno.AnalizadorTexto;

public class ModoFamiliar implements Preferencia {
  private AnalizadorTexto analizador;

  public ModoFamiliar(AnalizadorTexto analizador) {
    this.analizador = analizador;
  }

  @Override
  public Boolean esValido(Mensaje mensaje, Idioma idiomaTransmision) {
    return this.analizador.esAptoTodoPublico(mensaje.getTexto());
  }

}
