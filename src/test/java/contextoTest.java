import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import dominio.Canal;
import dominio.Categoria;
import dominio.Mensaje;
import dominio.MuestraApoyo;
import dominio.Transmision;
import dominio.Usuario;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import persistencia.CanalRepository;

public class contextoTest {
  private Categoria rpg;
  private Categoria retro;
  private CanalRepository repoCanales;
  private Usuario felix;
  private Mensaje primerMensajeFelix;
  private Mensaje segundoMensajeFelix;

  @BeforeEach
  void configuracionInicial() {
    rpg = new Categoria("RPG");
    retro = new Categoria("Retro");
    repoCanales = CanalRepository.getInstancia();
    felix = new Usuario("Felix");
    primerMensajeFelix= new Mensaje("....", felix);
    segundoMensajeFelix = new Mensaje("....", felix);
  }

  @Test
  public void primerRequerimiento() {
    Usuario eric = new Usuario("Eric Huanto");
    Canal canalEric = new Canal("Gaming de disparos", eric);

    assertEquals(eric, canalEric.getDueño());
    //Verificamos que eric sea el dueño del canal
  }

  @Test
  public void segundoRequerimiento() {
    Usuario eric = new Usuario("Eric Huanto");
    Canal canalEric = new Canal("Gaming de disparos", eric);
    Transmision transmision = new Transmision("Counter Strike 2", List.of(rpg, retro));

    canalEric.iniciarTransmision(transmision);
    assertTrue(canalEric.estaEnLive());
    //primera prueba para ver si está en live

    canalEric.finalizarTransmision();
    assertFalse(canalEric.estaEnLive());
    //segunda prueba para ver si cerro su live
  }

  @Test
  public void tercerRequerimientos() {
    Usuario eric = new Usuario("Eric Huanto");
    Canal canalEric = new Canal("Gaming de disparos", eric);
    Transmision transmision = new Transmision("Counter Strike 2", List.of(rpg, retro));
    Transmision transmision2 = new Transmision("Popply play time cap 5", List.of(retro));

    canalEric.iniciarTransmision(transmision);
    canalEric.finalizarTransmision();
    canalEric.iniciarTransmision(transmision2);
    canalEric.finalizarTransmision();

    assertEquals(List.of(transmision, transmision2), canalEric.getHistorialTransmisiones());
    //primera prueba para ver el historial de transmisiones

    List<Canal> canalesCreados = repoCanales.buscarTodos();
    assertEquals(List.of(), canalesCreados);
    //segunda prueba para listar los canales
    //Nota: ahora está vacío y eso está bien, porque quien se encarga de guardar los canales en el repo es controller, y como no tenemos ningún controller, está bien que devuelva vacío

    List<Transmision> transmisionesEnCurso = repoCanales.buscarTodasTransmisionesEnCurs();
    assertEquals(List.of(), transmisionesEnCurso);
    //tercera prueba para listar las transmisiones en curso
    //Nota: análogo al de arriba no contamos con un controller que registre en el repo los canales
    //Observación: se podría haber usado un repo de transmisiones para esto
  }

  @Test
  public void cuartoRequerimiento() {
    Usuario eric = new Usuario("Eric Huanto");
    Canal canalEric = new Canal("Gaming de disparos", eric);

    canalEric.agregarSuscriptor(felix);
    assertEquals(List.of(felix), canalEric.getSuscriptores());
    //primera prueba para registrar un suscriptor

    canalEric.recibirDonacion(new MuestraApoyo(8));
    assertEquals(1, canalEric.getDonacionesRecibidas().size());
    //segunda prueba para registrar una muestra de apoyo anónima
  }

  @Test
  public void quintoRequerimiento() {
    Usuario eric = new Usuario("Eric Huanto");
    Canal canalEric = new Canal("Gaming de disparos", eric);
    Transmision transmision = new Transmision("Counter Strike 2", List.of(rpg, retro));

    canalEric.iniciarTransmision(transmision);
    transmision.recibirParticipante(felix);

    assertEquals(List.of(felix), transmision.getParticipantesActuales());
    //verificamos que aparezca como participante
  }

  @Test
  public void sextoRequermiento() {
    Usuario eric = new Usuario("Eric Huanto");
    Canal canalEric = new Canal("Gaming de disparos", eric);
    Transmision transmision = new Transmision("Counter Strike 2", List.of(rpg, retro));

    canalEric.iniciarTransmision(transmision);
    transmision.recibirMensaje(primerMensajeFelix);
    transmision.recibirMensaje(segundoMensajeFelix);

    assertEquals(List.of(primerMensajeFelix, segundoMensajeFelix), transmision.verChat());
    //verificamos que la transmision reciba los mensajes
  }

}
