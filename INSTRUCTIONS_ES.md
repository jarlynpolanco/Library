# Library API


## Pasos a seguir para crear ambiente

- Utilizar el motor de base de datos MySQL.

- Ejecutar el script que se encuentra en la carpeta raiz del proyecto llamado: DbScript.sql en el mismo se encuentran las consultas necesarias para crear la base de datos, las tablas y las relaciones entre las diferentes tablas.

- Ejecutar el script que se encuentra en la carpeta raiz del proyecto llamado: SeedData.sql el mismo contiene la data de prueba con los diferentes autores, libros y paginas.

- Abrir el proyecto con NetBeans, ejecutar el comando mvn clean install para que se descarguen las dependencias de maven especificadas en el archivo pom.xml

- Ejecutar el proyecto haciendo Run Project o ubicandose en el paquete com.gbh.library.application usando la clase principal Startup.java y haciendo un Run File.

- Probar la aplicación segun los requerimientos indicados utilizando las rutas: http://localhost:8090/author/1/books y http://localhost:8090/book/1/pages/3/html



