# API de Clientes

Esta es una API simple para administrar clientes. Proporciona las operaciones básicas de CRUD (Crear, Leer, Actualizar y Eliminar) para gestionar clientes.

## Recursos Disponibles

- `/clientes`: Endpoint para obtener la lista de todos los clientes.
- `/clientes/{id}`: Endpoint para obtener un cliente por su ID.
- `/clientes`: Endpoint para crear un nuevo cliente.
- `/clientes/{id}`: Endpoint para actualizar un cliente existente (PUT).
- `/clientes/{id}`: Endpoint para actualizar parcialmente un cliente existente (PATCH).
- `/clientes/{id}`: Endpoint para eliminar un cliente por su ID (DELETE).

## Formato de Datos

La API utiliza JSON para el intercambio de datos. Este es un ejemplo de un objeto de cliente:

```json
{
  "id": 1,
  "nombre": "Nombre del Cliente",
  "email": "cliente@email.com"
}
```

Ejemplo en Java GET Clientes

```
OkHttpClient client = new OkHttpClient().newBuilder()
  .build();
MediaType mediaType = MediaType.parse("text/plain");
RequestBody body = RequestBody.create(mediaType, "");
Request request = new Request.Builder()
  .url("http://127.0.0.1:8080/clientes")
  .method("GET", body)
  .build();
Response response = client.newCall(request).execute();
```
