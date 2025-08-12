/**
 * Interfaz que define el comportamiento básico que cualquier entidad de usuario
 * debe tener. Esto permite que diferentes tipos de usuarios (por ejemplo, con
 * diferentes permisos) puedan ser tratados de manera uniforme.
 *
 * Esta interfaz es un ejemplo de cómo se aplica el Principio de Segregación de Interfaces (ISP),
 * ya que solo incluye los métodos mínimos necesarios para que un usuario
 * sea funcional en el sistema. También es clave para el Principio de Sustitución de Liskov (LSP),
 * permitiendo que las clases que la implementan, como {@link Model.User}, puedan ser
 * utilizadas en cualquier lugar donde se espere un IUser, sin cambiar el
 * comportamiento del sistema.
 */
package Model;

public interface IUser {
    String getName();
    String toString();
}
