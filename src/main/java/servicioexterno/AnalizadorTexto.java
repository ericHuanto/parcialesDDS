package servicioexterno;

import dominio.Idioma;

public interface AnalizadorTexto {
  Idioma determinarIdioma(String texto);
  Boolean esAptoTodoPublico(String texto);
  Boolean soloContieneEmojis(String texto);
}
