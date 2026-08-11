package persistencia;

import dominio.Categoria;
import java.util.ArrayList;
import java.util.List;

// Esto lo usan los admins.
public class CategoriaRepository {
  private final static CategoriaRepository INSTANCE = new CategoriaRepository();
  private final List<Categoria> categorias;

  private CategoriaRepository() {
    this.categorias = new ArrayList<>();
  }

  public static CategoriaRepository getInstance() {
    return INSTANCE;
  }

  public void agregar(Categoria categoria) {
    this.categorias.add(categoria);
  }

  public Categoria buscarPorNombre(String nombre) {
    return this.categorias.stream().filter(c -> c.getNombre().equals(nombre)).findFirst().orElseThrow();
  }

}
