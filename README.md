# API Recetas - Miguel Urquiza

Una API REST para gestionar recetas e ingredientes, construida con Spring Boot.

## Descripción

Este proyecto es una API RESTful que permite gestionar un catálogo de recetas culinarias junto con sus ingredientes. Incluye funcionalidades CRUD completas para categorías, ingredientes y recetas.

## Tecnologías utilizadas

- **Java 21**
- **Spring Boot 3.5.8**
- **Spring Data JPA** - Para gestionar la base de datos
- **H2 Database** - Base de datos en memoria
- **Lombok** - Para escribir menos código repetitivo
- **SpringDoc OpenAPI** - Documentación automática con Swagger
- **Maven** - Gestor de dependencias

## Estructura del Proyecto

```
ApiRecetasMiguelUrquiza/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/salesianostriana/dam/ApiRecetasMiguelUrquiza/
│   │   │       ├── controllers/      # Endpoints REST
│   │   │       ├── models/          # Entidades JPA
│   │   │       ├── repositories/    # Acceso a datos
│   │   │       ├── services/        # Lógica de negocio
│   │   │       └── dto/            # Data Transfer Objects
│   │   └── resources/
│   │       └── application.properties
│   └── test/
├── pom.xml
└── README.md
```

## Instalación y ejecución

### Prerrequisitos

Asegúrate de tener Java 21 instalado en tu equipo.

### Pasos para ejecutar

1. Clona el repositorio
   ```bash
   git clone <url-del-repo>
   cd ApiRecetasMiguelUrquiza
   ```

2. Ejecuta el proyecto
   ```bash
   # Linux/Mac
   ./mvnw spring-boot:run
   
   # Windows
   mvnw.cmd spring-boot:run
   ```

3. La API estará disponible en `http://localhost:8080`

## Documentación de la API

Una vez que la aplicación esté corriendo, puedes acceder a la documentación interactiva de Swagger en:

```
http://localhost:8080/swagger-ui/index.html
```

Desde ahí puedes ver todos los endpoints disponibles y probarlos directamente.

### Colección de Postman

El proyecto incluye una colección de Postman (`RecetaAPI.postman_collection.json`) con todos los endpoints configurados. Para usarla:

1. Abre Postman
2. Importa el archivo `RecetaAPI.postman_collection.json`
3. Ya puedes hacer peticiones a la API

La colección incluye ejemplos de todas las operaciones para Categorías, Ingredientes y Recetas.

## Base de datos H2

El proyecto usa H2 como base de datos en memoria. Para acceder a la consola:

```
http://localhost:8080/h2-console
```

Configuración de conexión:
- JDBC URL: `jdbc:h2:mem:testdb`
- Username: `sa`
- Password: (dejar en blanco)

## Endpoints

### Categorías
- `GET /categorias` - Obtener todas las categorías
- `GET /categorias/{id}` - Obtener una categoría específica
- `POST /categorias` - Crear nueva categoría
- `PUT /categorias/{id}` - Actualizar categoría
- `DELETE /categorias/{id}` - Eliminar categoría

### Ingredientes
- `GET /ingredientes` - Obtener todos los ingredientes
- `GET /ingredientes/{id}` - Obtener un ingrediente específico
- `POST /ingredientes` - Crear nuevo ingrediente
- `PUT /ingredientes/{id}` - Actualizar ingrediente
- `DELETE /ingredientes/{id}` - Eliminar ingrediente

### Recetas
- `GET /recetas` - Obtener todas las recetas
- `GET /recetas/{id}` - Obtener una receta específica
- `POST /recetas` - Crear nueva receta
- `PUT /recetas/{id}` - Actualizar receta
- `DELETE /recetas/{id}` - Eliminar receta
- `POST /recetas/{id}/ingredientes` - Añadir ingrediente a una receta

## Configuración

Las propiedades de la aplicación están en `src/main/resources/application.properties`:

```properties
# Puerto del servidor
server.port=8080

# Configuración H2
spring.h2.console.enabled=true
spring.datasource.url=jdbc:h2:mem:testdb

# JPA/Hibernate
spring.jpa.show-sql=true
spring.jpa.hibernate.ddl-auto=create-drop
```

## Build para producción

Para generar el JAR ejecutable:

```bash
./mvnw clean package
```

El JAR se generará en `target/ApiRecetasMiguelUrquiza-0.0.1-SNAPSHOT.jar`

## Características principales

- API RESTful completa
- Documentación interactiva con Swagger
- Base de datos H2 en memoria
- Arquitectura en capas (Controller → Service → Repository)
- DTOs para transferencia de datos
- Colección de Postman incluida

## Notas

- El proyecto usa Lombok, asegúrate de tener el plugin instalado en tu IDE
- La base de datos se resetea cada vez que reinicias la aplicación (modo `create-drop`)
- Para cambiar a una base de datos persistente, modifica el `application.properties`

## Autor

Miguel Urquiza

---

Proyecto desarrollado como parte del ciclo de Desarrollo de Aplicaciones Multiplataforma