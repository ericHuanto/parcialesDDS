import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import dominio.Colaboradora;
import dominio.Colectivo;
import dominio.DePago;
import dominio.Gratuito;
import dominio.Habilidad;
import dominio.Proyecto;
import dominio.TipoColectivo;
import java.util.List;
import org.junit.jupiter.api.Test;

public class ConsignaTest {

  @Test
  public void primerRequerimiento() {
    Colectivo fondoPreservacion = new Colectivo("Fondo de Preservación", "Fundación dedicada a la compra y preservación de libros antiguos", "Argentina", TipoColectivo.FUNDACION);
    Colaboradora danny = new Colaboradora("danico", "apellido", "correo@gmail.com", List.of());
    Habilidad java = new Habilidad("Desarrollo Web Java", "Lorem Ipsum");
    Proyecto bibliotecaDigital = new Proyecto("Biblioteca digital", "Lorem Ipsum", List.of(java), null, new DePago(500));
  }

  @Test
  public void segundoRequerimiento() {
    Habilidad android = new Habilidad("Desarrollo Móvil Android", "Lorem Ipsum");
    Habilidad ios = new Habilidad("Desarrollo Móvil iOS", "Lorem Ipsum");
    Proyecto seguimientoVolumenes = new Proyecto("Plataforma de seguimiento de volúmenes", "Lorem Ipsum", List.of(android, ios), null, new Gratuito());
    Colaboradora danny = new Colaboradora("danico", "apellido", "correo@gmail.com", List.of(ios));

    assertTrue(danny.puedeAnotarseA(seguimientoVolumenes));
  }

  @Test
  public void tercerRequerimiento() {
    Habilidad java = new Habilidad("Desarrollo Web Java", "Lorem Ipsum");
    Proyecto bibliotecaDigital = new Proyecto("Biblioteca digital", "Lorem Ipsum", List.of(java), null, new DePago(500));
    Colaboradora danny = new Colaboradora("danico", "apellido", "correo@gmail.com", List.of(java));

    bibliotecaDigital.registrarColaboradora(danny);

    assertEquals(danny, bibliotecaDigital.getColaboradores().get(0));
  }

  @Test
  public void cuartoRequerimiento() {
    Colectivo fondoPreservacion = new Colectivo("Fondo de Preservación", "Fundación dedicada a la compra y preservación de libros antiguos", "Argentina", TipoColectivo.FUNDACION);
    Habilidad java = new Habilidad("Desarrollo Web Java", "Lorem Ipsum");
    Proyecto bibliotecaDigital = new Proyecto("Biblioteca digital", "Lorem Ipsum", List.of(java), null, new DePago(500));
    Colaboradora danny = new Colaboradora("danico", "apellido", "correo@gmail.com", List.of(java));

    fondoPreservacion.agregarProyecto(bibliotecaDigital);
    bibliotecaDigital.registrarColaboradora(danny);

    assertTrue(fondoPreservacion.puedeVerDatosDe(danny));
  }


}