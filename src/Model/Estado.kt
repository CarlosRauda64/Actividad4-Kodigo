/**
 * Enumeración que define los posibles estados de una tarea.
 * Su uso garantiza la integridad de los datos, ya que el estado de una tarea
 * estará limitado a un conjunto de valores predefinidos.
 *
 * Esta enumeración contribuye al Principio de Responsabilidad Única (SRP),
 * ya que su única función es definir y mantener una colección de estados.
 */
package Model

enum class Estado {
    PENDIENTE,
    EN_PROGRESO,
    COMPLETADO,
    CANCELADO
}