/**
 * Interfaz genérica que define el contrato para la capa de persistencia.
 * Proporciona operaciones CRUD (Crear, Leer, Actualizar, Eliminar) básicas
 * para cualquier tipo de entidad.
 *
 * Esta interfaz es la base del Principio de Inversión de Dependencias (DIP),
 * ya que las clases de alto nivel (como los Managers) dependerán de esta
 * abstracción y no de las implementaciones concretas de almacenamiento.
 * Esto también facilita la aplicación del Principio Abierto/Cerrado (OCP),
 * permitiendo que se agreguen nuevas implementaciones de almacenamiento
 * (por ejemplo, una base de datos) sin modificar el código de los Managers.
 */
package Storage;

import java.util.List;

public interface IStorage<Item> {
    void add(Item item);
    List<Item> getAll();
    void delete(Item item);
    Item getById(int id);
}
