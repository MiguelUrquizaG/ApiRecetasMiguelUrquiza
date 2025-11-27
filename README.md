#  API Recetas - Miguel Urquiza

> Una API REST para gestionar recetas e ingredientes, construida con Spring Boot y actitud.

##  Descripción

Este proyecto es una API RESTful que permite gestionar un catálogo completo de recetas culinarias junto con sus ingredientes. Ideal para aplicaciones de cocina, gestores de menús o cualquier proyecto que necesite organizar recetas de forma eficiente.

##  Tech Stack

- **Java 21** - Porque vivimos en el presente
- **Spring Boot 3.5.8** - El framework que hace la vida más fácil
- **Spring Data JPA** - Para no escribir SQL a mano
- **H2 Database** - Base de datos en memoria (perfecta para desarrollo)
- **Lombok** - Adiós boilerplate code
- **SpringDoc OpenAPI** - Documentación automática con Swagger UI
- **Maven** - Gestor de dependencias

##  Estructura del Proyecto

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

##  Quick Start

### Prerrequisitos

- Java 21 instalado
- Maven 3.9+ (o usa el wrapper incluido)

### Instalación y Ejecución

1. **Clona el repositorio**
   ```bash
   git clone <url-del-repo>
   cd ApiRecetasMiguelUrquiza
   ```

2. **Ejecuta con Maven Wrapper (recomendado)**
   ```bash
   # Linux/Mac
   ./mvnw spring-boot:run
   
   # Windows
   mvnw.cmd spring-boot:run
   ```

3. **O compila y ejecuta el JAR**
   ```bash
   ./mvnw clean package
   java -jar target/ApiRecetasMiguelUrquiza-0.0.1-SNAPSHOT.jar
   ```

4. **¡Listo!** La API estará corriendo en `http://localhost:8080`

##  Documentación API

Una vez que la aplicación esté corriendo, puedes acceder a la documentación interactiva de Swagger:

```
http://localhost:8080/swagger-ui/index.html
```

Ahí podrás ver todos los endpoints disponibles y probarlos directamente desde el navegador.

##  Base de Datos

El proyecto usa **H2** como base de datos en memoria. Para acceder a la consola H2:

```
http://localhost:8080/h2-console
```

**Configuración de conexión:**
- JDBC URL: `jdbc:h2:mem:testdb`
- Username: `sa`
- Password: _(dejar en blanco)_

##  Endpoints Principales

### Recetas
- `GET /api/recetas` - Obtener todas las recetas
- `GET /api/recetas/{id}` - Obtener una receta específica
- `POST /api/recetas` - Crear nueva receta
- `PUT /api/recetas/{id}` - Actualizar receta
- `DELETE /api/recetas/{id}` - Eliminar receta

### Ingredientes
- `GET /api/ingredientes` - Obtener todos los ingredientes
- `GET /api/ingredientes/{id}` - Obtener un ingrediente específico
- `POST /api/ingredientes` - Crear nuevo ingrediente
- `PUT /api/ingredientes/{id}` - Actualizar ingrediente
- `DELETE /api/ingredientes/{id}` - Eliminar ingrediente

##  Configuración

Las propiedades de la aplicación se encuentran en `src/main/resources/application.properties`:

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

##  Testing

Ejecuta los tests con:

```bash
./mvnw test
```

##  Build para Producción

Para generar el JAR ejecutable:

```bash
./mvnw clean package -DskipTests
```

El JAR se generará en `target/ApiRecetasMiguelUrquiza-0.0.1-SNAPSHOT.jar`

##  Características Clave

- ✅ API RESTful completamente funcional
- ✅ Documentación automática con OpenAPI/Swagger
- ✅ Validación de datos
- ✅ Manejo de excepciones personalizado
- ✅ Arquitectura en capas (Controller → Service → Repository)
- ✅ DTOs para transferencia de datos
- ✅ Base de datos H2 para desarrollo rápido

##  Notas de Desarrollo

- El proyecto usa Lombok para reducir boilerplate, asegúrate de tener el plugin instalado en tu IDE
- La base de datos se resetea cada vez que reinicias la aplicación (modo `create-drop`)
- Para cambiar a una base de datos persistente, modifica el `application.properties`

##  Autor

**Miguel Urquiza**