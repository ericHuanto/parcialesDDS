package dominio;

import serviciosexternos.AnalizadorTexto;

public class ModoIdioma implements Preferencia {
  private AnalizadorTexto analizador;

  public ModoIdioma(AnalizadorTexto analizador) {
    this.analizador = analizador;
  }

  @Override
  public Boolean esValido(Mensaje mensaje, Idioma idiomaTransmision) {
    Idioma idiomaMensaje = this.analizador.determinarIdioma(mensaje.getTexto());
    return idiomaMensaje.esIgual(idiomaTransmision);
  }

}
