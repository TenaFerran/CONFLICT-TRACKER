# Conflict Tracker – Desplegament Fullstack

Aplicación desarrollada para la asignatura de Fullstack, orientada a la gestión de conflictos, utilizando Vue 3 en el frontend y Spring Boot en el backend.

El proyecto se ha desplegado en una arquitectura distribuida en la nube, utilizando Vercel para el frontend, Railway para el backend y Supabase como base de datos.

## URLs de la aplicación

Frontend (Vercel):  
https://vue-conflict-tracker.vercel.app

Backend (Railway):  
https://conflict-tracker-production-3d6d.up.railway.app


## Arquitectura de la aplicación

La aplicación sigue una arquitectura de tres capas desacopladas:

```
Frontend (Vue 3 - Vercel)
        │
        │ HTTP REST API
        ↓
Backend (Spring Boot - Railway)
        │
        │ JDBC
        ↓
Base de Datos (PostgreSQL - Supabase)
```


## Tecnologías utilizadas

Frontend: Vue 3 + Vite + Pinia  
Backend: Spring Boot  
Base de datos: PostgreSQL  

Hosting:
- Frontend: Vercel  
- Backend: Railway  
- Base de datos: Supabase  


## Variables de entorno

### Frontend (Vercel)

Variable utilizada:

```
VITE_API_URL=https://conflict-tracker-production-3d6d.up.railway.app
```

Uso en el código (store de conflictos):

```js
const BASE_URL = `${import.meta.env.VITE_API_URL}/api/v1/conflicts`
```

Importante:
- Las variables deben comenzar por `VITE_`
- Es necesario hacer redeploy tras cualquier modificación


### Backend (Railway)

Variables utilizadas para la conexión a la base de datos:

- DB_URL  
- DB_USERNAME  
- DB_PASSWORD  

Ejemplo de configuración en `application.yml`:

```yml
spring:
  datasource:
    url: ${DB_URL}
    username: ${DB_USERNAME}
    password: ${DB_PASSWORD}
```


## Configuración de variables de entorno

### En Vercel

1. Acceder a Settings → Environment Variables  
2. Añadir la variable:  
   - VITE_API_URL = https://conflict-tracker-production-3d6d.up.railway.app  
3. Realizar un redeploy del proyecto  


### En Railway

1. Acceder a la sección Variables  
2. Añadir:
   - DB_URL  
   - DB_USERNAME  
   - DB_PASSWORD  
3. El despliegue se actualiza automáticamente  


## Problemas encontrados y soluciones

### 1. Error de conexión con PostgreSQL

Error:
```
Driver org.postgresql.Driver claims to not accept jdbcUrl
```

Causa:  
Se estaba utilizando un formato incorrecto de conexión:

```
postgresql://...
```

Solución:  
Utilizar el formato JDBC correcto:

```
jdbc:postgresql://...
```


### 2. Error de conexión con Supabase

Error:
```
UnknownHostException
```

Causa:  
Uso de un endpoint incorrecto

Solución:  
Utilizar el endpoint de tipo pooler proporcionado por Supabase


### 3. Error de versión de Java en Railway

Error:
```
release version 25 not supported
```

Causa:  
Railway no soporta Java 25

Solución:  
- Usar Java 21  
- Eliminar el perfil `-Pproduction`  
- Configurar el build command como:

```
./mvnw clean package -DskipTests
```


### 4. Error de rutas en Vercel (SPA Routing)

Error:
```
404 al refrescar rutas
```

Causa:  
Uso de Vue Router en modo history

Solución:  
Crear el archivo `vercel.json`:

```json
{
  "rewrites": [
    { "source": "/(.*)", "destination": "/index.html" }
  ]
}
```


### 5. Error "Unexpected token '<'"

Error:
```
is not valid JSON
```

Causa:  
El frontend recibía HTML en lugar de JSON

Motivo:
- URL de la API incorrecta  
- Variable mal definida (`API_URL` en lugar de `VITE_API_URL`)

Solución:

```js
import.meta.env.VITE_API_URL
```


### 6. Error CORS

Error:
```
No 'Access-Control-Allow-Origin'
```

Causa:  
El dominio del frontend no estaba permitido en el backend

Solución:

```java
@CrossOrigin(origins = "https://vue-conflict-tracker.vercel.app")
```


### 7. Error 404 en la API

Error:
```
/conflicts → 404
```

Causa:  
Ruta incorrecta en el frontend

Solución:  
El backend utiliza:

```
/api/v1/conflicts
```

Se actualizó el frontend para usar esa ruta correctamente.


### 8. Error 500 al crear un conflicto

Causa:  
Se introducía una fecha no válida (no se permitía la fecha actual)

Solución:  
Utilizar una fecha válida posterior

Mejora detectada:  
Sería recomendable devolver un error 400 (Bad Request) en lugar de 500.


## Limpieza de datos y control de errores

Para evitar que errores de la API afecten al estado de la aplicación:

- Se comprueba `response.ok` antes de procesar la respuesta  
- No se guardan datos en Pinia si la respuesta es incorrecta  
- Se gestionan los errores mediante mensajes controlados  

Ejemplo:

```js
if (!response.ok) {
  throw new Error("Error en la API")
}
```


## Conclusión

Se ha conseguido desplegar correctamente una aplicación fullstack en la nube, separando frontend, backend y base de datos en servicios independientes.

En el proceso han ido habiendo errores ya que era la primera vez que desplegaba los servicios y no estaba familiarizado con el proceso y las webs, pero poco a poco se han corregido los errores y ahora funciona correctamente
