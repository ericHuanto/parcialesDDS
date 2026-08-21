
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import dominio.AccionModeracion;
import dominio.BaneoPermanente;
import dominio.Canal;
import dominio.Categoria;
import dominio.EliminacionMensaje;
import dominio.Idioma;
import dominio.Mensaje;
import dominio.ModoEmote;
import dominio.NormaComunidad;
import dominio.PlataFormaMensajeria;
import dominio.PlataformaTwich;
import dominio.ProveedorTwich;
import dominio.Transmision;
import dominio.Usuario;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import servicioexterno.AnalizadorTexto;

public class parcialTest {
  private Usuario eric;
  private Canal canalEric;
  private Categoria rpg;
  private Categoria retro;
  private NormaComunidad incitacionViolencia;
  private NormaComunidad contenidoSexual;
  private Usuario feli;
  private Usuario david;
  private Idioma español;
  private AnalizadorTexto analizador;
  private ProveedorTwich proveedorTwichMockeado;

  @BeforeEach
  public void configuracionInicial() {
    eric = new Usuario("Eric Huanto");
    canalEric = new Canal("Gaming de disparos", eric);
    rpg = new Categoria("RPG");
    retro = new Categoria("Retro");
    incitacionViolencia = new NormaComunidad("Incitación a la violencia");
    contenidoSexual = new NormaComunidad("Contenido sexual explícito");
    feli = new Usuario("Feli");
    david = new Usuario("David");
    español = new Idioma("Español");
    analizador = mock(AnalizadorTexto.class);
    proveedorTwichMockeado = mock(ProveedorTwich.class);
  }

  @Test
  public void primerRequerimiento() {
    Mensaje mensajeFeli= new Mensaje("....", feli);
    Mensaje mensajeDavid = new Mensaje("....", david);
    Transmision transmisionEric = new Transmision("Counter Strike 2", List.of(rpg, retro), canalEric, español, mock(PlataFormaMensajeria.class));

    canalEric.iniciarTransmision(transmisionEric);
    transmisionEric.recibirParticipante(feli);
    transmisionEric.recibirParticipante(david);
    transmisionEric.recibirMensaje(mensajeFeli);
    transmisionEric.recibirMensaje(mensajeDavid);
    assertEquals(2, transmisionEric.verChat().size());
    assertFalse(canalEric.estaBaneado(feli));
    //primera prueba para ver que se recibieron los mensajes

    AccionModeracion eliminarMensajeFeli = new EliminacionMensaje(mensajeFeli, contenidoSexual, transmisionEric);
    AccionModeracion eliminarMensajeDavid = new EliminacionMensaje(mensajeDavid, incitacionViolencia, transmisionEric);
    AccionModeracion banearAFeli = new BaneoPermanente(feli, contenidoSexual, canalEric);
    canalEric.aplicarModeracion(eliminarMensajeFeli, banearAFeli);
    canalEric.aplicarModeracion(eliminarMensajeDavid);
    assertEquals(0, transmisionEric.verChat().size());
    assertTrue(canalEric.estaBaneado(feli));
    //segunda prueba para ver que se ejecutaron las acciones

    canalEric.cancelarModeracion(eliminarMensajeFeli);
    canalEric.cancelarModeracion(eliminarMensajeDavid);
    canalEric.cancelarModeracion(banearAFeli);

    assertEquals(2, transmisionEric.verChat().size());
    assertFalse(canalEric.estaBaneado(feli));
    //tercera prueba para ver si volvimos a lo mismo que la primera prueba
  }

  @Test
  public void segundoRequerimiento() {
    Mensaje mensajeCaritas = new Mensaje("😀😀", feli);
    Transmision transmisionEric = new Transmision("Counter Strike 2", List.of(rpg, retro), canalEric, español, mock(PlataFormaMensajeria.class));

    when(analizador.soloContieneEmojis("😀😀")).thenReturn(true);

    canalEric.iniciarTransmision(transmisionEric);
    transmisionEric.agregarPreferencia(new ModoEmote(analizador));
    transmisionEric.recibirParticipante(feli);
    transmisionEric.recibirMensaje(mensajeCaritas);

    assertEquals(1, transmisionEric.verChat().size());
    //primera prueba para ver que se recibió el mensaje

    Mensaje mensajeFeli= new Mensaje("....", feli);

    assertThrows(RuntimeException.class, () -> {transmisionEric.recibirMensaje(mensajeFeli);});
    //segunda prueba para verificar que no se puede recibir mensajes normales
  }

  @Test
  public void tercerRequerimiento() {
    Mensaje mensajeFeli= new Mensaje("....", feli);
    PlataformaTwich twich = new PlataformaTwich(proveedorTwichMockeado);
    Transmision transmisionEric = new Transmision("Counter Strike 2", List.of(rpg, retro), canalEric, español, twich);

    canalEric.iniciarTransmision(transmisionEric);
    transmisionEric.recibirParticipante(feli);
    transmisionEric.recibirMensaje(mensajeFeli);

    verify(proveedorTwichMockeado).enviarMensaje(mensajeFeli);
    //Verificamos que se use el metodo de envío de mensaje
  }

  @Test
  public void cuartoRequerimiento() {
    //Para cumplir con este requerimiento es necesario del sistema operativo
    //vamos a tener que configurar el crontab de linux o el programador de tareas de windows
    //ya sea que usemos linux o windows vamos a necesitar tener un main.
    //Ver TerminadorTransmisiones en la carpeta cron
  }

}
