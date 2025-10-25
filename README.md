#  Ejercicio: Refactorización de la clase **God**

##  Descripción

En este ejercicio se presenta una clase denominada **God**, que concentra múltiples responsabilidades dentro de una sola clase.  
El objetivo es analizar sus problemas de diseño y refactorizarla aplicando los **principios SOLID**, **patrones de diseño** y **buenas prácticas de clean code**, aprovechando las características modernas de **Java (Streams, Lambdas, Interfaces Funcionales, etc.)**.

---

## Problemas detectados: violaciones a los principios SOLID

### 1. Principio de Responsabilidad Única (SRP)
La clase `God` se encarga de:
- Autenticación de usuarios  
- Procesamiento de archivos  
- Operaciones con base de datos  
- Envío de correos electrónicos  

Estas son **responsabilidades distintas**, que deberían estar distribuidas en **clases independientes**, cada una encargada de un único motivo de cambio.

---

### 2. Principio de Abierto/Cerrado (OCP)
El método `processFile` utiliza una sentencia `switch`.  
Si se desea añadir soporte para un nuevo tipo de archivo (por ejemplo, **CSV**), sería necesario **modificar** la clase `God`, violando el principio.  
✅ Solución sugerida: usar **polimorfismo** o el **patrón Strategy** para extender el comportamiento sin alterar el código existente.

---

### 3. Principio de Sustitución de Liskov (LSP)
Si se crea una subclase de `God`, podría **alterar el comportamiento** de métodos como `login`, rompiendo la funcionalidad que depende del comportamiento original.  
✅ Solución sugerida: definir **interfaces claras y cohesivas** para que las subclases puedan respetar los contratos establecidos.

---

### 4. Principio de Segregación de Interfaces (ISP)
La interfaz `GodInterface` es una interfaz **“gorda”**.  
Una clase que sólo necesite procesar archivos estaría **obligada a implementar métodos innecesarios** (`login`, `logout`, `sendEmail`, etc.).  
✅ Solución sugerida: dividir la interfaz en **interfaces específicas por tipo de funcionalidad**, por ejemplo:
- `AuthenticationService`
- `FileProcessor`
- `EmailService`
- `DatabaseRepository`

---

### 5. Principio de Inversión de Dependencias (DIP)
La clase `God` depende directamente de una clase concreta `DatabaseConnection`.  
Debería depender de una **abstracción** (una interfaz), permitiendo cambiar la implementación de base de datos sin modificar el código.  
✅ Solución sugerida: inyectar dependencias a través de interfaces o frameworks como **Spring**.

---

##  Tareas de refactorización

1. Separar las responsabilidades en distintas clases e interfaces.  
2. Reemplazar estructuras rígidas (como `switch`) por **patrones de diseño** como:
   - Strategy
   - Factory Method
   - Dependency Injection  
3. Aplicar **interfaces funcionales** y **lambdas** donde sea posible (por ejemplo, en el procesamiento de archivos).  
4. Usar **Streams API** para manipular colecciones de datos.  
5. Escribir pruebas unitarias para cada nueva clase o servicio creado.  
6. Documentar las clases refactorizadas con **JavaDoc**.



---

## Ejercicio adicional

1. Implementá una versión inicial de `FileProcessor` que use **lambdas** para mapear extensiones de archivo a comportamientos concretos.  
2. Reemplazá el acceso directo a la base de datos por una interfaz `DatabaseRepository`.  
3. Utilizá **inyección de dependencias** (manual o con Spring) para instanciar los servicios.  
4. Agregá logs y excepciones personalizadas.  
5. Aplicá **tests unitarios** usando JUnit o AssertJ.

---

## Objetivo final

Refactorizar la clase “God” en un conjunto de clases **modulares, extensibles y mantenibles**, respetando los principios SOLID y el espíritu de **Clean Code**.

---

##  Recursos recomendados

- Libro: *Clean Code* – Robert C. Martin  
- Libro: *Design Patterns* – Gamma, Helm, Johnson, Vlissides  
- Documentación oficial de [Java Streams](https://docs.oracle.com/javase/8/docs/api/java/util/stream/package-summary.html)  
- [Guía de principios SOLID en Java](https://www.baeldung.com/solid-principles)


## Cambios realizados

### 1. Patrón Strategy
   Este es el patrón más evidente en el rediseño del procesamiento de archivos.
* Propósito: Permite definir una familia de algoritmos (estrategias), encapsular cada uno en su propia clase y hacerlos intercambiables.
* Dónde lo usamos:
   * La interfaz FileProcessor es el contrato común de la Estrategia.
   * Las clases TxtFile, XmlFile, JsonFile, y DocFile son las Estrategias Concretas. Cada una implementa el mismo método processFile de una manera diferente.
   * La clase ProcessFileService actúa como el Contexto que utiliza una de estas estrategias para realizar su trabajo, sin saber los detalles de cuál está usando.

### 2. Patrón Factory (Fábrica)
   Lo utilizamos para decidir qué estrategia de procesamiento de archivos crear en tiempo de ejecución.
* Propósito: Encapsula la lógica de creación de objetos. El cliente pide un objeto a la fábrica en lugar de crearlo directamente, lo que desacopla al cliente de las clases concretas.
* Dónde lo usamos:
  ### 3. Inyección de Dependencias (Principio y Patrón)
   Este es un principio clave para lograr la Inversión de Dependencias (la 'D' de SOLID). Lo aplicamos de forma manual.
* Propósito: Un objeto no debe crear sus propias dependencias, sino que se le deben "inyectar" desde fuera.
* Dónde lo usamos:
   * La clase DatabaseRepositoryImpl no crea su propia conexión a la base de datos (no hace new DatabaseConnection()).
   * En su lugar, recibe una dependencia de tipo DbConnection a través de su constructor: public DatabaseRepositoryImpl(DbConnection dbConnection).
   * La clase Main actúa como el Inyector, creando todas las dependencias y pasándolas a los objetos que las necesitan.

### 4. Patrón Repository
   Se utilizó para abstraer la lógica de acceso a datos.
* Propósito: Mediar entre la lógica de negocio y la capa de acceso a datos, proporcionando una interfaz similar a una colección para consultar y persistir datos.
* Dónde lo usamos:
   * La interfaz DatabaseRepository define el contrato del repositorio (getUserData, setUserData).
### 5. Patrón Facade (Fachada) - Aplicado como Capa de Servicio
   Cada uno de nuestros servicios (AuthenticationService, EmailService, etc.) actúa como una fachada para su respectiva área de funcionalidad.
* Propósito: Proporciona una interfaz unificada y simplificada a un subsistema más complejo.
* Dónde lo usamos:
   * La clase Main no necesita conocer los detalles internos de cómo se procesa un archivo (estrategias, fábrica, etc.). Simplemente llama a fileService.processFile(). El ProcessFileService actúa como una
     fachada que simplifica el uso de ese subsistema.