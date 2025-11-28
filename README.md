# API Recetas - Miguel Urquiza

API REST para gestionar recetas e ingredientes, construida con Spring Boot.

## Descripción

Este proyecto es una API RESTful que permite gestionar un catálogo de recetas culinarias junto con sus ingredientes. Incluye funcionalidades CRUD completas para categorías, ingredientes y recetas, además de una relación Muchos a Muchos entre recetas e ingredientes que permite almacenar cantidades específicas.

## Tecnologías utilizadas

- **Java 21**
- **Spring Boot 3.5.8**
- **Spring Data JPA**
- **H2 Database**
- **Lombok**
- **SpringDoc OpenAPI**
- **Maven**

## Modelo de datos

### Entidades principales

**Categoria**
- id, nombre (único), descripcion
- Una categoría tiene muchas recetas

**Receta**
- id, nombre (único), tiempoPreparacionMin, dificultad (FACIL/MEDIA/DIFICIL)
- Pertenece a una categoría
- Tiene muchos ingredientes (M:M)

**Ingrediente**
- id, nombre (único)
- Se usa en muchas recetas (M:M)

**IngredienteReceta** (Tabla intermedia)
- Almacena la relación entre recetas e ingredientes
- Incluye cantidad y unidad de medida (GRAMOS, KILOS, MILILITROS, etc.)

## Instalación y ejecución

### Requisitos previos

- JDK 21 instalado

### Pasos

1. Clonar el repositorio
   ```bash
   git clone <url-del-repo>
   cd ApiRecetasMiguelUrquiza
   ```

2. Ejecutar el proyecto
   ```bash
   # Linux/Mac
   ./mvnw spring-boot:run
   
   # Windows
   mvnw.cmd spring-boot:run
   ```

3. La API estará disponible en `http://localhost:8080`

## Documentación de la API

### Swagger UI

Accede a la documentación interactiva en:
```
http://localhost:8080/swagger-ui/index.html
```

### Colección de Postman

El proyecto incluye una colección de Postman (`RecetaAPI.postman_collection.json`) con todos los endpoints configurados. Para usarla:

1. Abre Postman
2. Importa el archivo `RecetaAPI.postman_collection.json`
3. Ya puedes hacer peticiones a la API

## Endpoints principales

### Categorías
- `GET /categorias` - Listar todas
- `GET /categorias/{id}` - Obtener por ID
- `POST /categorias` - Crear nueva
- `PUT /categorias/{id}` - Actualizar
- `DELETE /categorias/{id}` - Eliminar

### Ingredientes
- `GET /ingredientes` - Listar todos
- `GET /ingredientes/{id}` - Obtener por ID
- `POST /ingredientes` - Crear nuevo
- `PUT /ingredientes/{id}` - Actualizar
- `DELETE /ingredientes/{id}` - Eliminar

### Recetas
- `GET /recetas` - Listar todas
- `GET /recetas/{id}` - Obtener por ID (incluye ingredientes con cantidades)
- `POST /recetas` - Crear nueva (requiere idCategoria)
- `PUT /recetas/{id}` - Actualizar
- `DELETE /recetas/{id}` - Eliminar
- `POST /recetas/{id}/ingredientes` - Añadir ingrediente con cantidad

**Ejemplo para añadir ingrediente a receta:**
```json
{
  "idIngrediente": 1,
  "cantidad": 500,
  "unidad": "GRAMOS"
}
```

## Gestión de errores

La API maneja las siguientes excepciones con códigos HTTP apropiados:

- **404 Not Found** - Entidad no encontrada (categoría, receta o ingrediente)
- **409 Conflict** - Nombre duplicado o ingrediente ya añadido a la receta
- **400 Bad Request** - Tiempo de preparación inválido (≤ 0)

Todos los errores devuelven una respuesta en formato ProblemDetail (RFC 7807).

## Base de datos H2

Accede a la consola H2 en: `http://localhost:8080/h2-console`

**Configuración:**
- JDBC URL: `jdbc:h2:mem:testdb`
- Username: `sa`
- Password: (vacío)

La base de datos se resetea al reiniciar la aplicación (modo `create-drop`).

## Estructura del proyecto

```
src/main/java/com/salesianostriana/dam/ApiRecetasMiguelUrquiza/
├── controllers/      # Endpoints REST
├── dto/             # Data Transfer Objects
├── error/           # Gestión de excepciones
├── models/          # Entidades JPA
├── repositories/    # Acceso a datos
└── services/        # Lógica de negocio
```

## Características

- CRUD completo para todas las entidades
- Relación M:M con atributos entre recetas e ingredientes
- Validación de datos y manejo de errores
- Documentación interactiva con Swagger
- Arquitectura en capas con DTOs
- Colección de Postman incluida

## Notas

- El proyecto usa Lombok, necesitas tener el plugin instalado en tu IDE
- La base de datos es en memoria y se pierde al detener la aplicación
- Los nombres de categorías, recetas e ingredientes son únicos

## Autor

Miguel Urquiza García

---
## Información Curso

- Proyecto Desarrollado en el curso de Desarrollo de Aplicaciones Multiplataforma
- Salesianos San Pedro - Triana, Sevilla.
- Curso 2025-2026