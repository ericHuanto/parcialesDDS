package dominio;

import serviciosexternos.AnalizadorTexto;

public class ModoEmote implements Preferencia {
  private AnalizadorTexto analizador;

  public ModoEmote(AnalizadorTexto analizador) {
    this.analizador = analizador;
  }

  @Override
  public Boolean esValido(Mensaje mensaje, Idioma idiomaTransmision) {
    return this.analizador.soloContieneEmojis(mensaje.getTexto());
  }

}
