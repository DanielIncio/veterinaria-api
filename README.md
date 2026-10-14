# Veterinaria API

API REST para gestionar dueños y mascotas de una veterinaria, hecha con Spring Boot y MySQL.

## Estado del proyecto

- ✅ CRUD de dueños y mascotas con relación uno a muchos
- ✅ Validaciones y manejo de errores centralizado
- 🚧 Autenticación con JWT y roles (en desarrollo)
- 🚧 Frontend en Angular (próximamente)

## Tecnologías

- Java 17
- Spring Boot 3.2.4, Spring Web, Spring Data JPA
- MySQL
- Lombok
- Maven
- Postman (pruebas)

## Funcionalidades

- CRUD de dueños (`/duenos`) y de mascotas (`/mascotas`)
- Relación bidireccional: un dueño tiene muchas mascotas y cada mascota conoce a su dueño (`@OneToMany` / `@ManyToOne`)
- Validación de datos de entrada (campos vacíos, edad no negativa, etc.)
- No permite eliminar un dueño que aún tiene mascotas (responde `409 Conflict`)
- Respuestas de error en JSON con mensaje y código de estado

## Cómo ejecutarlo

### Requisitos

- Java 17 o superior
- Maven
- MySQL

### Pasos

1. Clona el repositorio:
   ```bash
   git clone https://github.com/DanielIncio/veterinaria-api.git
   cd veterinaria-api
   ```
2. Crea una base de datos vacía en MySQL (las tablas las crea Hibernate al iniciar):
   ```sql
   CREATE DATABASE veterinaria_db;
   ```
3. Copia el archivo de configuración de ejemplo y completa tus datos:
   ```bash
   cp src/main/resources/application.properties.example src/main/resources/application.properties
   ```
4. Ejecuta el proyecto:
   ```bash
   mvn spring-boot:run
   ```
   La API queda disponible en `http://localhost:8080`.

## Endpoints

### Dueños

| Método | Ruta | Descripción |
|---|---|---|
| GET | `/duenos` | Listar dueños |
| GET | `/duenos/{id}` | Buscar dueño por id |
| POST | `/duenos` | Crear dueño |
| PUT | `/duenos/{id}` | Actualizar dueño |
| DELETE | `/duenos/{id}` | Eliminar dueño (409 si tiene mascotas) |

### Mascotas

| Método | Ruta | Descripción |
|---|---|---|
| GET | `/mascotas` | Listar mascotas |
| GET | `/mascotas/{id}` | Buscar mascota por id |
| POST | `/mascotas?duenoId={id}` | Crear mascota asociada a un dueño |
| PUT | `/mascotas/{id}` | Actualizar mascota |
| DELETE | `/mascotas/{id}` | Eliminar mascota |

### Ejemplo: crear un dueño

```json
POST /duenos
{
  "nombre": "Ana",
  "telefono": "999888777"
}
```

### Ejemplo: crear una mascota para el dueño 1

```json
POST /mascotas?duenoId=1
{
  "nombre": "Firulais",
  "especie": "Perro",
  "raza": "Mestizo",
  "edad": 3
}
```

## Estructura del proyecto

```
config/       → SecurityConfig
controller/   → DuenoController, MascotaController
service/      → DuenoService, MascotaService
repository/   → DuenoRepository, MascotaRepository
model/        → Dueno, Mascota
exception/    → ManejadorErrores
```

## Qué aprendí

- Relaciones entre entidades con JPA (uno a muchos, bidireccional)
- Cómo evitar el bucle infinito de JSON en relaciones bidireccionales (`@JsonIgnoreProperties`)
- Códigos HTTP correctos: 400, 404 y 409
- Uso de Lombok para reducir código repetitivo

## Autor

**Daniel Incio** · [GitHub](https://github.com/DanielIncio)
