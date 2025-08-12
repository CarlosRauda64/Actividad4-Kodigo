# Actividad4-Kodigo
Implementación de una aplicación básica con clases que representen un sistema de gestión de tareas (Task Management System).
# Sistema de Gestión de Tareas - Aplicación de Principios SOLID

## 📝 Resumen del Proyecto

Este proyecto es una aplicación de consola en Java para la gestión de tareas. El objetivo principal es demostrar la implementación práctica de los cinco principios SOLID (Responsabilidad Única, Abierto/Cerrado, Sustitución de Liskov, Segregación de Interfaces e Inversión de Dependencias) en una arquitectura modular y escalable.

El sistema permite:
* Crear, editar y eliminar usuarios.
* Crear, editar y eliminar proyectos.
* Crear, editar y eliminar tareas.
* Asignar y desasignar usuarios a tareas y proyectos.
* Gestionar la eliminación de entidades con un servicio que asegura la integridad de los datos (eliminación en cascada).

## 🚀 Arquitectura del Proyecto

El proyecto está organizado en paquetes lógicos que reflejan una arquitectura limpia:

* **`Model`**: Contiene las clases que representan las entidades del negocio (tareas, proyectos, usuarios).
* **`Storage`**: Encapsula la lógica de persistencia de datos (en este caso, en memoria).
* **`Management`**: Contiene la lógica de negocio y las reglas de la aplicación.
* **`Service`**: Aloja servicios que coordinan operaciones complejas entre los Managers.

## 💡 Principios SOLID Aplicados

### 1. Principio de Responsabilidad Única (SRP)
* **En el código**: Cada clase, como `User`, `UserStorage` o `UserManager`, tiene una única razón para cambiar.
    * **`Model.User`**: Solo se encarga de los datos de un usuario.
    * **`Storage.UserStorage`**: Solo gestiona la persistencia de los usuarios.
    * **`Management.UserManager`**: Solo contiene la lógica de negocio para los usuarios.
* **Beneficio**: El sistema es más fácil de mantener y probar, ya que los cambios en una funcionalidad no afectan a otras responsabilidades de la clase.

### 2. Principio Abierto/Cerrado (OCP)
* **En el código**: Las clases están abiertas para la extensión pero cerradas para la modificación.
    * **Ejemplo**: La interfaz `IStorage` permite que los `Managers` trabajen con cualquier implementación de almacenamiento. Si se desea cambiar la persistencia de memoria a una base de datos, se puede crear una nueva clase (ej. `DatabaseStorage`) que implemente `IStorage` sin necesidad de modificar el código de los `Managers`.
* **Beneficio**: La arquitectura es flexible y escalable, permitiendo añadir nuevas funcionalidades sin alterar el código existente.

### 3. Principio de Sustitución de Liskov (LSP)
* **En el código**: Los objetos de una superclase pueden ser reemplazados por objetos de una subclase sin afectar la funcionalidad.
    * **Ejemplo**: La interfaz `IUser` permite que los `Managers` traten a cualquier objeto que la implemente (como la clase `User`) de manera uniforme. El sistema funcionará correctamente sin importar la implementación de `IUser` que se utilice.
* **Beneficio**: El código es más robusto y polimórfico, facilitando la creación de nuevas entidades que se adhieran a un contrato común.

### 4. Principio de Segregación de Interfaces (ISP)
* **En el código**: Se han creado interfaces específicas para cada cliente, en lugar de una única interfaz monolítica.
    * **Ejemplo**: La interfaz `IStorage` define solo los métodos CRUD básicos, mientras que la interfaz `IUser` define solo los métodos para acceder al nombre y la representación en cadena.
* **Beneficio**: Las interfaces son más fáciles de entender y las clases no se ven obligadas a implementar métodos que no necesitan, lo que reduce el acoplamiento.

### 5. Principio de Inversión de Dependencias (DIP)
* **En el código**: Los módulos de alto nivel (`Managers`) no dependen de los módulos de bajo nivel (`Storage`), sino de abstracciones.
    * **Ejemplo**: Los constructores de `UserManager`, `TaskManager` y `ProjectManager` reciben una interfaz `IStorage` a través de inyección de dependencias. Esto rompe la dependencia directa y permite un desacoplamiento total de la lógica de negocio y la capa de datos.
* **Beneficio**: El código es más flexible, testable y modular.

## ⚙️ Configuración y Uso

### Requisitos
* Java Development Kit (JDK) 8 o superior.
* Un IDE compatible con Java (IntelliJ IDEA, Eclipse, etc.).
* El proyecto usa Kotlin para la clase `Estado`, por lo que el IDE debe tener soporte para ambos lenguajes.

### Pasos para Ejecutar
1.  **Clonar el repositorio**: `git clone [URL_DEL_REPOSITORIO]`
2.  **Abrir en el IDE**: Importar el proyecto en tu IDE preferido.
3.  **Ejecutar la clase principal**: Ejecutar el método `main` de la clase principal para iniciar la interfaz de consola.

* * *
***Nota***: *Esta documentación es un resumen de las decisiones de diseño. Para una explicación detallada de cada clase y método, consulte los comentarios Javadoc en el código fuente.*
