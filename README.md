# Gestión de Equipos Informáticos del Colegio

## Introducción
Este proyecto consiste en la implementación de una API que permite gestionar los equipos informáticos del colegio, así como las incidencias de los mismos. Ofrece una funcionalidad rápida y versátil, pudiendo fácilmente gestionar las incidencias, para que estas puedan ser resueltas lo antes posible.

## Tecnologías
Para la implementación de este proyecto se utilizarán Spring Boot.

## Funcionalidades
###En Postamn al hacer login de administrador y usuario en el resultado del token cada vez que hagas un endpoint tienes que copiar el token y pegarlo en el header de la petecion en el Bearer.

### Registro y Login
Cualquier usuario podrá registrarse en la app, aunque para poder utilizarla definitivamente un usuario con rol de administrador tendrá que validarlo. Debe haber una petición de usuarios no validados. La seguridad que se utilizará será JWT.

Las peticiones son las siguientes:
- POST /auth/register: registro de cualquier usuario
- POST /auth/login: login de cualquier usuario
- PUT /users/{userId}/validate: validación del usuario cuyo id es userId
- GET /users/no-validated: listado de usuarios pendientes de validar

### Gestión del Inventario
Cualquier usuario podrá listar el inventario de equipos del centro. Para ello podrá obtener un listado de todos los equipos, así como otro listado de los diferentes tipos de inventariables, y las diferentes ubicaciones.

Los usuarios administradores podrán también dar de alta nuevos equipos, modificar los datos del mismo, así como eliminar ese equipo.

Las peticiones son:
- GET /inventariable: todos los equipos, impresoras, ...
- GET /inventariable/{{inventariableId}}: obtener un inventariable
- GET /inventariable/tipos: los diferentes tipos de equipos
- GET /inventariable/ubicaciones: las diferentes ubicaciones
- POST /inventariable: crear un nuevo inventariable (solo administradores)
- PUT /inventariable/{{inventariableId}}: editar un inventariable (solo administradores)
- DELETE /inventariable/{{inventariableId}}: borrar un inventariable (solo administradores)

### Gestión de Tickets
Cualquier usuario podrá dar de alta un nuevo ticket. No es obligatorio seleccionar el dispositivo sobre el cual se pone (puede ser una incidencia general, como que no hay internet en toda el aula 7). Solamente los usuarios que hayan creado un ticket (o cualquier administrador) lo podrán editar o borrar.

Tan solo los usuarios administradores o técnicos podrán asignar las incidencias a un usuario técnico (administrador). Cuando una incidencia se asigna a un técnico, se guarda el técnico y la fecha de la misma.

Las peticiones son:
- POST /ticket: creación de un nuevo ticket
- PUT /ticket/{{ticketId}}: edición de un ticket (solamente puede editar el usuario que lo creó, o un administrador)
- DELETE /ticket/{{ticketId}}: borrado de un ticket (solamente puede borrar el usuario que lo creó, o un administrador)
- GET /ticket: obtener todos los tickets (solo administradores)
- GET /ticket/inventariable/{{ticket}} (solo administradores)
- PUT /ticket/{{ticketId}}/estado: actualizar estado de un ticket (solo administradores)
- PUT /ticket/{{ticketId}}/asignar: asignar un ticket a un técnico (solo administradores)
- GET /ticket/asignados/me: obtener los tickets asignados a mi como técnico (solo administradores)

