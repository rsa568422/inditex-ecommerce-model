##### RESUMEN #####

Se implementa la solución a la prueba técnica en el componente inditex-ecommerce-model, se realiza una implementación
de la persistencia sobre CSV en inditex-ecommerce-persistence y una implementación de un microservicio con api REST
en springboot en el componente inditex-ecommerce-api.

Se aplican los principios SOLID, el clean code y los patrones de diseño ahí donde se detecta la posibilidad de hacerlo.
Siguiendo las recomendaciones del libro "Arquitectura Limpia" de Robert C. Martin, se trata de hacer un componente de
modelo de negocio lo más independiente posible de los detalles de implementación, primando el principio de inversión de
dependencia para hacer que sea el componente de modelo quien indique a los demás que va a necesitar para operar.

##### inditex-ecommerce-model #####

Se extrae la lógica de modelo de negocio al módulo inditex-ecommerce-model, realizando una implementación
del algoritmo de visibilidad independiente a los detalles de implementación. Se agregan dependencias a los
artefactos commons-lang3 y lombok como herramientas básicas para la implementación, al ofrecer utilidades
y etiquetas para reducir la cantidad de código a implementar. Se emplean las herramientas JUnit y Mockito
para el testing. Se generan tres clases de entidad junto con sus repositorios:
 - Product (ProductRepository)
 - Size (SizeRepository)
 - Stock (StockRepository)

Se considera el algoritmo de visibilidad como un algoritmo de complejidad O(n^2) al iterar sobre cada producto y
sobre cada talla de cada producto. No considero que el stock afecte a la complejidad del algoritmo, ya que su relación
con las tallas es de uno a uno. Se podría mejorar el rendimiento del algoritmo con un atributo isVisible ya establecido
en los productos registrados que recuperemos del repositorio, lo que reduciría la complejidad a O(n), pero tendríamos
que mantener coherente ese valor actualizándolo en la persistencia cada vez que agregase, eliminase o modificase el
stock de una talla. En un principio parece más acertado mantener la complejidad del algoritmo en O(n^2) para evitar
los problemas que puede suponer mantener la persistencia coherente, pero entiendo que en un escenario real se espera
una gran demanda en la ejecución del algoritmo, por lo que puede que sea un requisito reducir la complejidad al mínimo.

Versión de Java: 11
Cobertura del código con las pruebas: 91%

Product
-------
Se hace que la clase Product implemente la interfaz Comparable para poder ordenar de forma sencilla los productos
según sequence. Se emplean Long para los atributos id y sequence, y Set<Size> para sizes. Se elige el tipo Set para
las tallas, asumiendo que las tallas de un producto no estarán repetidas, por lo se considera oportuno utilizar
conjuntos y no listas. Esta clase será la responsable de implementar el algoritmo de visibilidad, ya que se espera
conocer si un elemento Product es visible.

ProductRepository
-----------------
Se genera la interfaz ProductRepository con los siguientes métodos:
 - findAll: método mínimo a implementar para las clases herederas. Se espera que se devuelva el conjunto de productos
            registrados en el sistema, por lo que al igual que con las tallas, se decide emplear Set<Product> para el
            resultado.
 - filterVisiblesAndOrderBySequence: método estático que se ofrece como herramienta para filtrar un conjunto de
                                     productos dado y devolver el subconjunto de productos visibles ordenados por
                                     sequence.
 - findVisiblesOrderBySequence: método default que hace uso de findAll y filterVisiblesAndOrderBySequence para filtrar
                                los productos y devolver el conjunto de productos visibles ordenados por sequence.

Size
----
Se emplea Long para los atributos id y productId y boolean para los atributos backSoon y special. Para el stock se
emplea el tipo Optional<Stock>, ya que no siempre la talla tendrá un stock asociado. Se agrega el método haveStock
para simplificar la implementación del algoritmo de visibilidad.

SizeRepository
--------------
Se agrega una interfaz SizeRepository que es opcional, ya que no siempre necesitaremos implementarla para poder
trabajar con el modelo. En implementaciones de ProductRepository basadas en el ORM hibernate por ejemplo, podríamos
obtener las tallas del producto automáticamente mediante la etiqueta @OneToMany, pero en caso de no disponer de una
herramienta con esta característica para la persistencia, necesitaríamos un método que nos permita recuperar el
conjunto de tallas asociadas a un producto.
 - findByProductId: método acordado para obtener las tallas de un determinado producto. Dado el id del producto, se
                    dará como resultado el Set<Size> correspondiente.

Stock
-----
Se emplea Long para los atributos sizeId y quantity. Se entiende que se trata de una entidad débil dependiente de Size.

StockRepository
---------------
Como sucedía con SizeRepository, StockRepository será necesario en implementaciones en las que no podamos recuperar
automáticamente el stock asociado a una talla.
 - findBySizeId: método acordado para obtener el stock asociado a una talla. Dado el id de la talla, se dará como
                 resultado un Optional<Stock> que contendrá el stock correspondiente en caso de existir y
                 Optional.empty() en caso contrario.

##### inditex-ecommerce-persistence #####

Se genera un módulo de persistencia inditex-ecommerce-persistence con una implementación de las clases Repository de
inditex-ecommerce-model. Dicha implementación se realiza sobre los propios ficheros CSV aportados en la documentación
de la prueba, implementando un ORM propio simulando hibernate. Dicho ORM es solo un ejercicio para demostrar mis
conocimientos sobre la reflexión de Java y el uso de anotaciones, pero he llevado a cabo una implementación similar en
un proyecto real sobre jdbc, dados los requisitos del proyecto que imposibilitaban el uso de hibernate u otros ORMs
comerciales. Se hace uso de lombok, commons-lang3, JUnit y Mockito como en inditex-ecommerce-model, agregando una nueva
dependencia a gson para simplificar la generación de nuevas instancias de los objetos.

Para evitar dependencias en la medida de lo posible, se agregan clases DTO propias para representar las clases de modelo
junto con una herramienta Parser que se encarga de trasformar un DTO en su correspondiente modelo.

Versión de Java: 11
Cobertura del código con las pruebas: 89%

CsvRepository
-------------
Se agrega la clase abstracta CsvRepository que será la responsable de trabajar con los objetos DTO. Recupera la
información del fichero CSV correspondiente y la carga en un Set<E> con el conjunto de elementos de modelo
correspondientes. Como posible mejora, aplicaría un patrón singleton para mantener una única instancia en memoria de
dicho conjunto de elementos y cambiaría la estructura contenedora de Set<E> a Map<Long, E> para poder disponer de una
implementación trivial de un posible método findById.

##### inditex-ecommerce-api #####

Se agrega el módulo inditex-ecommerce-ipa para la implementación de una api REST con springboot. Se hace uso de lombok
y commons-lang3 como en los demás módulos, agregando una base de datos en memoria con h2, el testing con
spring-boot-starter-test, una capa de persistencia alternativa con spring-boot-starter-data-jpa y se agrega swagger
al proyecto para poder probar el endpoint manualmente mediante springfox-boot-starter. Se realizan pruebas unitarias
y de integración en los distintos componentes del módulo.

Para la persistencia implementada en este módulo con h2 y JpaRepository, se agregan clases DTO propias con un método
toModel que las transforma en clases de entidad equivalentes. Se aplica un patrón de diseño adapter en la clase
ProductH2RepositoryAdapter para implementar la interfaz ProductRepository empleando la interfaz ProductH2Repository.

Se agrega la clase de configuración RepositoryConfig en la que podemos establecer la persistencia a utilizar. En la
versión entregada se trabaja con la persistencia de JpaRepository con H2, ya que queda anotado el método getH2Repository
con @Primary, pero podemos cambiar a la persistencia en CSV implementada en inditex-ecommerce-persistence simplemente
moviendo esa etiqueta al método getCsvRepository.

Versión de Java: 11
Cobertura del código con las pruebas: 98%
