# YogoGym Application 

## Descripción de la aplicación
La aplicación web a desarrollar tiene como nombre YogoGym. El objetivo fundamental es conseguir modernizar la forma de realizar los entrenamientos y el seguimiento para un local/cadena de esta característica. Con ella se implementarán cuatro roles fundamentales: administrador, monitor de clase, entrenador y cliente. Cada uno de ellos tendrá unas funcionalidades específicas, como por ejemplo los planes de entrenamientos que realizan los entrenadores a sus clientes, pudiendo estos modificarlos para adaptarlos a su necesidad. Para ayudar a administrar el gimnasio, se planea crear gráfica/tableros de control, que ayude a al gerente a saber cuales son las máquinas con más usos, los días en los que más gente acude al entrenamiento, etc. facilitando la gestión del local. También para los clientes se implementará funciones para que, alcanzando unos retos, para los cuales necesitará acreditarlo con una prueba, este reciba algún beneficio.

## Entidades
* Persona
* Usuario
* Administrador
* Entrenador
* Cliente
* Entrenamiento
* Rutina
* Línea de rutina
* Ejercicio
* Equipamiento
* Dieta
* Alimento
* Reto
* Inscripción
* Gremio
* Frase

## Historias de Usuario
### Administrador
---
#### HU1 - Proponer retos semanales
Como administrador quiero añadir retos semanales al gimnasio, que los clientes puedan competir por lograrlos y obtener beneficios y puntos (para su gremio) en el gimnasio si los completan, para motivarlos a realizar ciertos ejercicios y crear un ambiente competitivo.
#### Escenarios:
* **Añadir reto semanal a semana sin retos:** Dado un administrador y una semana [Semana 1 de 2022] sin retos, cuando este intente añadir uno aportando la información necesaria, entonces el reto se creará correctamente.
* **Añadir reto semanal ya existente:** Dado un administrador y una semana con retos [Semana 1 de 2021], cuando este intente añadir uno con el nombre [ChallengeTest] de otro ya existente, entonces se le indicará que esto no es posible debido a que ya existe.
* **Añadir reto semanal a semana con 3 retos:** Dado un administrador y una semana con 3 retos [Semana 2 de 2021], cuando este intente añadir uno, entonces se le indicará que no es posible debido a que se ha llegado al máximo de retos esa semana.
* **Añadir reto semanal en fecha anterior:** Dado un administrador, cuando este intente añadir un reto en una fecha anterior [01/01/2020], entonces se le indicará que no es posible debido a que solo se puede añadir en fechas posteriores.
---
#### HU2 - Dashboard Retos
Como administrador quiero visualizar diagramas que muestren, clasificados por individuales o gremios y semanales o mensuales, el éxito en los retos para poder adecuarlos en temática y dificultad correctamente.
#### Escenarios: 
* **Visualizar diagrama individual de periodo con retos:** Dado un administrador y un periodo con retos individuales registrados, cuando este intente visualizar su diagrama individual, entonces se le mostrarán los nombres de estos y el número y porcentaje de clientes que los han logrado.
* **Visualizar diagrama individual de periodo sin retos:** Dado un administrador y un periodo sin retos, cuando este intente visualizar su diagrama, entonces se le indicará que no hay ninguno y se le ofrecerá añadir uno si el periodo es posterior al actual.
---
#### HU3 - Dashboard Máquinas
Como administrador quiero visualizar diagrama que me muestren cuanto son utilizadas las máquinas de las que dispone el gimnasio para saber cuales requieren un mayor mantenimiento o si es necesario adquirir más de ellas o desecharlas.
#### Escenarios:
* **Visualizar diagrama general de máquinas, sistema con datos de la última semana:** Dado un administrador y un sistema con máquinas registradas, cuando este intente visualizar el diagrama general de máquinas, entonces se le mostrará el nombre de estas, cuantas veces han sido utilizadas en la última semana.
* **Visualizar diagrama general de máquinas, sistema con datos del último mes:** Dado un administrador y un sistema con máquinas registradas, cuando este intente visualizar el diagrama general de máquinas, entonces se le mostrará el nombre de estas, cuantas veces han sido utilizadas en el último mes.
* **Visualizar diagrama general de máquinas, sistema sin datos:** Dado un administrador y un sistema sin máquinas registradas, cuando este intente visualizar el diagrama general de máquinas, entonces se le indicará que no hay ninguna.
---
#### HU4 - Mostrar oraciones motivacionales diariamente
Como administrador quiero que se muestren oraciones motivacionales diariamente para intentar motivar a los clientes y mejorar su estado de ánimo.
#### Escenarios:
* **Mostrar oración diaria, conexión con API estable:** Dado un usuario con un rol cualquiera y una conexión con la API de oraciones estable, cuando se acceda la página principal de la aplicación, entonces se mostrará la oración del día.
* **Mostrar oración diaria, conexión con API no estable:** Dado un usuario con un rol cualquiera y una conexión con la API de oraciones no estable, cuando se acceda la página principal de la aplicación, entonces se mostrará una oración motivacional predefinida.
---
#### HU6 - Dashboard Gimnasio General
Como administrador quiero poder gestionar los datos del gimnasio, así como el número de clientes actuales, el número de clientes por clase, y el número de entrenadores, para poder llevar un control de los clientes y los entrenadores de mi gimnasio.
#### Escenarios:
* **Suficientes datos que mostrar:** Si el sistema posee clientes y profesores se le mostraran los distintos datos que se solicitan, como el número de clientes, el número de clientes por clase, y el número de entrenadores.
* **Sin datos para mostrar:** Si el sistema no posee clientes inscritos o profesores, los valores serán nulos o con valor 0.
---
#### HU7 - Gestión Entrenadores
Como administrador quiero ser capaz de gestionar mis empleados, los cuales son los monitores y los entrenadores, para llevar un control del personal contratado.
#### Escenarios
* **Suficientes datos que mostrar:** Si el sistema posee entrenadores y monitores se le mostraran los distintos datos que posea cada uno, como por ejemplo el nombre, apellidos, dni, correo electrónico, número de teléfono, así como la información de sus contratos.
* **Sin datos para mostrar:** Si el sistema no posee monitores o entrenadores inscritos, los valores serán nulos o con valor 0.
---
### Entrenador
---
#### HU8 - Añadir rutinas a los entrenamientos
Como entrenador quiero realizar rutinas a nuestros clientes para facilitar el desarrollo físico del cliente
#### Escenarios:
* **Añadir una rutina a un usuario, se añade vacía:** Dado un entrenador que añade una rutina a un usuario, cuando se intenta añadir vacía. La operación de añadir rutina no se efectúa resultando en el sistema informando al entrenador que debe como mínimo introducir un ejercicio.
* **Añadir una rutina a un usuario sin rutina:** Dado un entrenador que añade una rutina a un usuario, cuando este usuario no tiene ninguna rutina. La operación de añadir rutina se efectúa correctamente y esta queda disponible tanto por el usuario para poder visualizarla como para el entrenador poder modificarla.
* **Añadir una rutina a un usuario el cual no entrenas:** Dado un entrenador que añade una rutina a un usuario, cuando no entrenas a este usuario. La operación de añadir rutina no se realiza. La vista muestra una excepción al entrenador de que no puede añadir una rutina a un usuario el cual no entrena.
---
#### HU9 - Gestionar las rutinas de los entrenamientos en función de la intensidad, duración y cantidad de ejercicios
Como entrenador quiero llevar el control de las rutinas en función de intensidad, duración y cantidad de ejercicios para poder cambiar la rutina adaptándola de la mejor forma al cliente.
#### Escenarios:
* **Gestionar la rutina de un usuario sin que este tenga rutina:** Dado un entrenador que se dispone a gestionar la rutina de un usuario, cuando ese usuario no posee ninguna rutina. La vista de encargada la gestión de rutinas notificará al entrenador de que el usuario indicado que no posee ninguna rutina y le ofertará la opción de crearla. 
* **Gestionar la rutina de un usuario cuyo usuario no entrenas:** Dado un entrenador que se dispone a gestionar la rutina de un usuario, cuando no eres entrenador de ese usuario. La operación de gestión no se puede efectuar. El sistema limita a solo poder ver la rutina que tiene el usuario deseado, pero no es manipulable por alguien que no sea su entrenador.
* **Gestionar la rutina de un usuario borrando todos los ejercicios:** Dado un entrenador que se dispone a gestionar la rutina, en este caso modificar, de un usuario, borrando todos los ejercicios, cuando el usuario posee una rutina completa. La operación de gestión no se puede efectuar ya que debe existir un mínimo de 3 actividades por rutina.
* **Gestionar la rutina de un usuario:** Dado un entrenador que se dispone a gestionar la rutina de un usuario si incumplir ninguna regla de negocio, cuando este usuario ya posee una. La operación de gestión se efectúa correctamente y esta queda disponible tanto por el usuario para poder visualizar los cambios como para el entrenador poder modificarlos en un futuro.
---
#### HU10 - Añadir dietas alimenticias a los entrenamientos
Como entrenador quiero realizar dietas a los clientes para mejorar su evolución física, haciéndola más eficiente.
#### Escenarios:
* **Añadir una dieta a un cliente que no posee una dieta:** Dado un entrenador que se dispone a añadir una dieta a un usuario con nombre "Dieta 1", descripción "Descripción dieta 1" y tipo "VOLUME" se autogenera la dieta en función de los datos del cliente, se le añade al entrenamiento y se redirige a la vista de detalles del entrenamiento.
* **Añadir una dieta a un cliente que posee una dieta:** Dado un entrenador que se dispone a añadir una dieta a un usuario con nombre "Dieta 1", descripción "Descripción dieta 1" y tipo "VOLUME" se autogenera la dieta en función de los datos del cliente, se sustituye a la que tenía previamente entrenamiento y se redirige a la vista de detalles del entrenamiento.
* **Añadir una dieta a un usuario, sin datos completos:** Dado un entrenador que añade una dieta a un usuario, cuando se intenta añadir sin rellenar todos los datos. La operación de añadir dieta no se efectúa resultando en el sistema informando al entrenador que debe rellenar la información correspondiente a "Name", "Description", "Type".
* **Añadir una dieta a un usuario el cual no entrenas:** Dado un entrenador que intenta añadir una dieta a un usuario, cuando no entrenas a este usuario. La operación de añadir dieta no se realiza. La vista muestra una excepción al entrenador.
* **Añadir una dieta a un entrenamiento que no existe:** Dado un entrenador que intenta añadir una dieta a un entrenamiento que no existe, la operación de añadir dieta no se realiza. La vista muestra una excepción al entrenador.
---
#### HU11 - Gestionar la dieta alimenticia de los entrenamientos en función de kilocalorías, nutrientes esenciales, peso y porcentaje de grasa
Como entrenador quiero llevar el control de las dietas de los clientes en función de cantidad de kilocalorías, nutrientes esenciales, peso y porcentaje de grasa para poder cambiar las cosas necesarias con el fin de mejorar le evolución del cliente
#### Escenarios:
* **Gestionar la dieta de un usuario:** Dado un entrenador que se dispone a actualizar la dieta de un usuario sin incumplir ninguna regla de negocio, cuando este usuario ya posee una. La operación de gestión se efectúa correctamente y esta queda disponible tanto por el usuario para poder visualizar los cambios como para el entrenador poder modificarlos en un futuro.
* **Gestionar la dieta de un usuario, sin datos completos:** Dado un entrenador que añade una dieta a un usuario, cuando se intenta añadir sin rellenar todos los datos la operación de añadir dieta no se efectúa resultando en el sistema informando al entrenador que debe rellenar la información correspondiente a "Name", "Description", "Type", "kcal", "protein", "carb", "fat".
* **Gestionar la dieta de un usuario, con datos erróneos:** Dado un entrenador que añade una dieta a un usuario, cuando se intenta añadir con datos erróneos la operación de añadir dieta no se efectúa resultando en el sistema informando al entrenador que debe cumplir la restricciones correspondientes a "kcal", "protein", "carb", "fat".
* **Gestionar la dieta de un usuario cuyo usuario no entrenas:** Dado un entrenador que intenta actualizar la dieta a un usuario, cuando no entrena a este usuario. La operación de añadir dieta no se realiza. La vista muestra una excepción al entrenador.
* **Gestionar la dieta a un entrenamiento que no existe:** Dado un entrenador que intenta gestionar la dieta a un entrenamiento que no existe, la operación de gestionar dieta no se realiza. La vista muestra una excepción al entrenador.
---
#### HU12 - Añadir un entrenamiento 
Como entrenador quiero añadir entrenamientos a nuestros clientes para poder organizar sus actividades y sus dietas, facilitando su desarrollo físico.
#### Escenarios:
* **Añadir entrenamiento a un usuario, con atributos sin conflictos:** Dado un entrenador que quiere añadir un entrenamiento con nombre "Nuevo Entrenamiento", fecha de inicio igual a la actual, y fecha de fin dentro de 7 días desde la actual; a un usuario, cuando se intenta añadir el entrenamiento, entonces se añade el entrenamiento al cliente y se redirige a la vista de detalles del entrenamiento.
* **Añadir entrenamiento a un cliente, sin introducir atributos:** Dado un entrenador que quiere añadir un entrenamiento sin nombre, ni fecha de inicio ni fecha de fin; a un cliente, cuando se intenta añadir el entrenamiento, entonces no se añade el entrenamiento y se redirige a la vista de creación informando de que debe cumplimentar los campos.
* **Añadir entrenamiento a un cliente, fecha de inicio un día antes de la actual:** Dado un entrenador que quiere añadir un entrenamiento con nombre "Nuevo Entrenamiento", fecha de inicio 1 día antes de la actual, y fecha de fin dentro de 7 días desde la actual; a un cliente, cuando se intenta añadir el entrenamiento, entonces no se añade el entrenamiento al cliente y se redirige a la vista de creación informando de que la fecha de inicio no debe estar en el pasado.
* **Añadir entrenamiento a un cliente, fecha de fin un día antes de la de inicio:** Dado un entrenador que quiere añadir un entrenamiento con nombre "Nuevo Entrenamiento", fecha de inicio igual a la actual, y fecha de fin 1 día antes de la actual; a un cliente, cuando se intenta añadir el entrenamiento, entonces no se añade el entrenamiento al cliente y se redirige a la vista de creación informando de que la fecha de fin debe ser posterior a la de inicio.
* **Añadir entrenamiento a un cliente, fecha de fin igual a la de inicio:** Dado un entrenador que quiere añadir un entrenamiento con nombre "Nuevo Entrenamiento", fecha de inicio igual a la actual, y fecha de fin igual a la actual; a un cliente, cuando se intenta añadir el entrenamiento, entonces no se añade el entrenamiento al cliente y se redirige a la vista de creación informando de que la fecha de fin debe ser posterior a la de inicio.
* **Añadir entrenamiento a un cliente, fecha de inicio coincidiendo con otro entrenamiento:** Dado un cliente con un entrenamiento que comienza en la fecha actual y finaliza dentro de 7 días desde la actual; y un entrenador que quiere añadir un entrenamiento con nombre "Nuevo Entrenamiento", fecha de inicio igual a la actual, y fecha de dentro de 8 días; a ese cliente, cuando se intenta añadir el entrenamiento, entonces no se añade el entrenamiento al cliente y se redirige a la vista de creación informando de que la fecha de inicio no puede coincidir con otro entrenamiento.
* **Añadir entrenamiento a un cliente, fecha de fin coincidiendo con otro entrenamiento:** Dado un cliente con un entrenamiento que comienza dentro de 1 día y finaliza dentro de 7 desde la actual; y un entrenador que quiere añadir un entrenamiento con nombre "Nuevo Entrenamiento", fecha de inicio igual a la actual, y fecha de fin dentro de 7 días desde la actual; a ese cliente, cuando se intenta añadir el entrenamiento, entonces no se añade el entrenamiento al cliente y se redirige a la vista de creación informando de que la fecha de fin no puede coincidir con otro entrenamiento.
* **Añadir entrenamiento a un cliente, entrenamiento incluyendo otro:** Dado un cliente con un entrenamiento que se inicia dentro de 1 día desde la actual y finaliza dentro de 7 días desde la actual; y un entrenador que quiere añadir un entrenamiento con nombre "Nuevo Entrenamiento", fecha de inicio igual a la actual, y fecha de fin dentro de 8 días; a ese cliente, cuando se intenta añadir el entrenamiento, entonces no se añade el entrenamiento al cliente y se redirige a la vista de creación informando de que el entrenamiento no puede incluir a otro.
---
#### HU13 - Gestionar el entrenamiento de nuestros clientes
Como entrenador quiero llevar el control de los entrenamientos de mis clientes conociendo el estado de estos para poder modificar los periodos en los que transcurren y así adaptarlos de la mejor forma a los clientes.
#### Escenarios:
* **Gestionar entrenamientos clientes, entrenador con cliente:** Dado un entrenador que se dispone a gestionar los entrenamientos de sus clientes, cuando accede a la vista de gestión de los entrenamientos, entonces se le listarán sus clientes junto con sus entrenamientos indicando de estos: nombre y su estado, es decir, si está en curso o finalizado. 
* **Gestionar entrenamientos clientes, cliente con entrenamientos:** Dado un entrenador que se dispone a gestionar el entrenamiento de un cliente, cuando accede a la vista de gestión del entrenamiento, entonces se le mostrará la siguiente información del entrenamiento: nombre, fecha inicio, fecha fin, un listado de sus rutinas, su dieta y botones para redireccionar a la edición y borrado de la información general del entrenamiento. 
* **Gestionar entrenamiento en curso, nombre vacío:** Dado un entrenador que se dispone a actualizar el entrenamiento con nombre "Entrenamiento 1", fecha de inicio igual a la actual y fecha de fin dentro de 7 días desde la actual; de un cliente, cuando intenta actualizar el nombre y la fecha de fin a vacío, entonces no se actualizará el entrenamiento y se le devolverá a la vista de actualización informándole de que esos campos no pueden estar vacíos.
* **Gestionar entrenamiento en curso, fecha fin anterior a la de inicio:** Dado un entrenador que se dispone a actualizar el entrenamiento con nombre "Entrenamiento 1", fecha de inicio igual a la actual y fecha de fin dentro de 7 días desde la actual; de un cliente, cuando intenta actualizar la fecha de fin a una un día antes de la actual, entonces no se actualizará el entrenamiento y se le devolverá a la vista de actualización informándole de que la fecha de fin no puede ser anterior a la de inicio.
* **Gestionar entrenamiento en curso, fecha fin coincide con otro entrenamiento:** Dado un cliente con un entrenamiento que se inicia dentro de 8 días desde la actual y finaliza dentro de 15 días desde la actual; y un entrenador que se dispone a actualizar el entrenamiento con nombre "Entrenamiento 1", fecha de inicio igual a la actual y fecha de fin dentro de 7 días desde la actual; de un cliente, cuando intenta actualizar la fecha de fin a una dentro de 8 días desde la actual, entonces no se actualizará el entrenamiento y se le devolverá a la vista de actualización informándole de que la fecha de fin no puede coincidir con otro entrenamiento.
* **Gestionar entrenamiento en curso, entrenamiento incluye otro:** Dado un cliente con un entrenamiento que se inicia dentro de 8 días desde la actual y finaliza dentro de 15 días desde la actual; y un entrenador que se dispone a actualizar el entrenamiento con nombre "Entrenamiento 1", fecha de inicio igual a la actual y fecha de fin dentro de 7 días desde la actual; de un cliente, cuando intenta actualizar la fecha de fin a una dentro de 16 días desde la actual, entonces no se actualizará el entrenamiento y se le devolverá a la vista de actualización informándole de que el entrenamiento no puede incluir otro.
* **Gestionar entrenamiento en curso, fecha fin anterior a la actual:** Dado un entrenador que se dispone a actualizar el entrenamiento con nombre "Entrenamiento 1", fecha de inicio 7 días antes de la actual y fecha de fin dentro de 7 días desde la actual; de un cliente, cuando intenta actualizar la fecha de fin a la de ayer, entonces no se actualizará el entrenamiento y se le devolverá a la vista de actualización informándole de que no se puede finalizar el entrenamiento en el pasado
---
### Usuario
---
#### HU5 - Crear y eliminar gremios
Como cliente quiero poder crear clases y asignarla a monitores, para poder administrar los horarios, que los clientes puedan apuntarse a ellas y los monitores sepan que clases deben impartir.
#### Escenarios:
* **Crear una clase en un horario vacío:** Dado un administrador, este crea una clase asignandole un título, tipo, monitor asignado, horario, la sala y el equipamiento del que dispone.
* **Crear una clase en una sala y horario ya ocupado:** Dado un administrador, el sistema deberá avisarle de que esa sala con ese horario ya está ocupado por otra clase.
* **Crear una clase y asignarla a un monitor ya ocupado:** Dado un administrador, el sistema deberá avisarle de que ese monitor ya está ocupado en ese horario en cualquiera de las salas.
---
#### HU14 - Apuntarme y salirme de gremios
Como cliente quiero poder apuntarme a las clases que me interesen, para poder asistir a ellas y saber el horario en las que se imparten.
#### Escenarios:
* **Apuntarse a una clase con huecos disponibles:** Dado un cliente y una clase sin el aforo completo, el cliente le da a inscribirse, se registra, y aumenta en uno el número de asistentes a esa clase.
* **Apuntarse a una clase sin huecos disponibles:** Dado un cliente y una clase sin huecos disponibles, el cliente le da a inscribirse, y salta un aviso de que el aforo ya está completo.
* **Apuntarse a una clase en el mismo horario que otra:** Dado un cliente y una clase con horario igual a otra que ya esté inscrito, le da a inscribirse y le salta un aviso diciendo que se solapa el horario con otra clase.
---
#### HU15 - Consultar todos los ejercicios disponibles
Como cliente quiero consultar todos los ejercicios disponible para poder coger ideas y realizar mis propias rutinas y entrenamientos.
#### Escenarios:
* **Consultar ejercicios disponibles:** Dado un cliente y un ejercicio publicado o disponible le muestra la información del ejericio.
* **Consultar ejercicios no disponibles:** Dado un cliente y un ejercicio no publicado o no disponible salta un aviso de que el ejercicio no está disponible.
---
#### HU16 - Personalizar dieta de mi entrenamiento
Como cliente quiero poder tener dietas personalizadas según mi tipo de entrenamiento para maximizar el resultado del ejercicio.
#### Escenarios:
* **Cliente con entrenamiento asignado:** Dado un cliente con un entrenamiento asignado se le asignará la dieta que mejor complemente su entrenamiento.
* **Cliente sin entrenamiento asignado:** Dado un cliente sin un entrenamiento asignado no se le permitirá acceder a esta función y se le pedirá que consiga un entrenamiento antes de acceder.
---
#### HU17 - Copiar entrenamiento de otro usuario
Como cliente quiero seguir a otros usuarios para copiar su entrenamiento.
#### Escenarios:
* **Usuarios con perfil público:** Dado un cliente con perfil público se permitirá que otros clientes le sigan.
* **Usuarios con perfil privado:** Dado un cliente con perfil privado no se permitirá que otros clientes le sigan.
---
#### HU18- Dashboard Cliente
Como cliente quiero poder tener un dashboard donde poder ver el Historial de actividades, Calorías quemadas y otros datos relacionados con mi entrenamiento.
#### Escenarios:
* **Suficientes datos que mostrar:** Dado un cliente con actividades realizadas se le mostrarán datos sobre su historial.
* **Sin datos para mostrar:** Dado un cliente sin actividades realizadas se le mostrarán posibles actividades a realizar para empezar a hacer ejercicio.
---
#### HU19 - Personalizar mi entrenamiento
Como cliente quiero poder personalizar mi entrenamiento para actualizarlo o personalizarlo a mi gusto.
#### Escenarios:
* **Con permiso para edición:** Dado un cliente con un entrenamiento que pueda ser editado se le permitirá actualizarlo a su gusto.
* **Sin permiso para edición:** Dado un cliente con un entrenamiento que  no puede ser editado no se le permitirá actualizarlo.
---
#### HU20 - Compartir progreso
Como cliente quiero poder compartir mi progreso con otros usuarios, para poder poder ver su avance y poder compararlos con mis resultados.
#### Escenarios:
* **Compartir progreso sin foto pero con comentario:** Dado un cliente, escribe un mensaje declarando su progreso, le da a publicar y se publica en su perfil, publicandose también en el perfil de los usuarios que tenga agregados como amigos.
* **Compartir progreso con foto pero sin comentario:** Dado un cliente, adjunta una foto, le da a enviar y se publica en su perfil, publicandose también en el perfil de los usuarios que tenga agregados como amigos.
* **Compartir progreso con foto y con comentario:** Dado un cliente, escribe un mensaje declarando su progreso, adjunta una foto, le da a publicar y se publica en su perfil, publicandose también en el perfil de los usuarios que tenga agregados como amigos.
* **Compartir progreso sin foto ni comentario:** Dado un cliente, al darle a publicar, si no ha adjuntado foto ni ha escrito ningún comentario le saltará un aviso diciendo "si desea publicar debe adjuntar una foto o escribir un comentario".
---
#### HU21 - Participar en  los Retos
Como cliente quiero poder participar en los distintos retos propuestos por el gimnasio, para poder ganar puntos para mi gremio y para mi mismo y estar lo mas alto posible en la clasificación.
#### Escenarios
* **Listar los retos en los que aún no me he inscrito:** Dado un cliente, pinchará en la sección "retos", verá un listado con los diferentes retos propuestos y seleccionará el que desee, verá los requisitos que se piden para completarlo y si desea intentarlo pinchará en el botón "inscribirme", añadiendosele dicho reto a la sección "mis retos" pero con un estado "participando" hasta que adjunte una foto demostrando que lo ha completado.
**Listar los retos en los que participa:** Dado un cliente, pinchará en la sección "mis retos", verá un listado con los diferentes retos en los que se ha inscrito.
* **Participar en un reto cuando no hay retos:** Dado un cliente, pinchará en la sección "retos", si dicho usuario ha completado todos los retos, no le saldrá ningún reto en dicha lista.
* **Pedir confirmar un reto que ha completado:** Dado un cliente, pinchará en la sección "mis retos", si dicho usuario ha completado algún reto, pinchará sobre el, rellenara la url de la foto de prueba, y clickara en enviar. El administrador podrá ver esos retos enviados, y decidirá si han sido completados o fallados.
* **Listar los retos en los que participo y la fecha límite del reto pasa antes de que el cliente lo envíe:** Dado un cliente y un reto con fecha de finalización [01/01/2019], sin que haya sido enviado, se pondrá automaticamente en fallado.
---
#### HU22 - Clasificación de los Retos
Como cliente quiero poder ver la clasificación de mis retos, para poder ver en que posición me encuentro y que debo hacer para poder superar y subir al top.
#### Escenarios
* **Ver un la clasificación semanal e histórica con datos:** Dado un cliente, al pulsar el botón de "Clasificación", aparecerá un listado de los retos completados, viendo también una clasificación semanal y global.
* **Ver un la clasificación histórica sin haber completado retos:** Dado un cliente sin retos completados, al pulsar el botón de "Clasificación", aparecerá una clasificación global.
* **Ver un la clasificación de otro cliente:** Dado un cliente, intentar entrar en la clasificación de otro cliente cambiando la url, dando como resultado una redirección a la página de excepción.
---
#### HU23 - Añadir Playlist adaptada a mis entrenamientos
Como cliente quiero poder añadir playlist de spotify aleatorias adaptadas a mis entrenamientos, es decir, adecuadas para el nivel de intensidad especificado, para poder motivarme y llevar un ritmo adecuado.
#### Escenarios
* **Añadir una playlist a un entrenamiento con ejercicios y spotify responde:** Dado un cliente y un entrenamiento, cuando el cliente pulsa sobre crear playlist asociada, spotify devuelve una playlist no vacía.
* **Añadir una playlist a un entrenamiento con ejercicios y spotify no responde:** Dado un cliente y un entrenamiento, cuando el cliente pulsa sobre crear playlist asociada, spotify devuelve un error o una playlist vacía, el sistema debe mostrar un mensaje de error, sugiriendo alternativas o pidiendo disculpas porque spotify no está disponible.
* **Añadir una playlist a un entrenamiento sin ejercicios:** Dado un cliente y un entrenamiento sin ejericios, cuando el cliente pulsa sobre crear playlist asociada, el sistema debe avisarle de que deben haber ejercicos para crear una playlist personalizada.
---
## Planificación y asignación de las historias de usuario
| Id pareja | Integrantes | 
| --- | --- |
| P1 | Enrique y Víctor |
| P2 | Carlos y Francisco José | 
| P3 | Álvaro y José Manuel | 

| Historia de Usuario | Sprint | Pareja asignada | Hecho |
| --- | --- | --- | --- |
| HU12 | 1 | P1 | Sí |
| HU13 | 1 | P1 | --- |
| HU8 | 1 | P1 | Sí | 
| HU9 | 1 | P1 | --- |
| HU10 | 1 | P2 | --- |
| HU11 | 1 | P2 | --- |
| HU14 | 1 | P2 | --- |
| HU15 | 1 | P1 | Sí |
| HU16 | 1 | P2 | --- |
| HU18 | 1 | P3 | --- |
| HU19 | 1 | P1 | --- |
| HU21 | 1 | P3 | --- |
| HU22 | 1 | P3 | --- |
| HU1 | 1 | P3 | --- |
| HU3 | 1 | P3 | --- |
| HU5 | 1 | P2 | --- |
| HU17 | 2 | P3 | --- |
| HU20 | 2 | P2 | --- |
| HU2 | 2 | P3 | --- |
| HU6 | 2 | P1 | --- |
| HU23 | 3 | P1 | --- |
| HU4 | 3 | P2 | --- |
| HU7 | 3 | P3 | --- |

