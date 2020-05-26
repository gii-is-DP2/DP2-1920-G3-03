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
* Foro
* Mensaje

#### Entidades de soporte:
* Autoridades
* Entidad Base
* Tablero de control de Administrador
* Tablero de control de Cliente

## Historias de Usuario

### Administrador

---

#### HU1 - Proponer retos semanales

Como administrador quiero añadir retos semanales al gimnasio, que los clientes puedan competir por lograrlos y obtener beneficios y puntos (para su gremio) en el gimnasio si los completan, para motivarlos a realizar ciertos ejercicios y crear un ambiente competitivo.

#### Escenarios:

* **Añadir reto semanal a semana sin retos:** Dado un administrador [admin1] y una semana [01/01/2030] sin retos, cuando este intente añadir un challenge aportando la información necesaria [name="Challenge Name", description="Challenge Desc", initialDate="01/01/2030", endDate="05/01/2030", points=10,reps=12,reward="Reward",weight=20. , exercise=Exercise1], entonces el reto se creará correctamente.
* **Añadir reto semanal ya existente:** Dado un administrador[admin1], creará un reto igual que el anterior  pero con nombre= "Challenge Same Name"]. Cuando este intente crear uno con el mismo nombre e initialDate que el anterior, entonces se le indicará que esto no es posible debido a que ya existe.
* **Añadir reto semanal a semana con 3 retos:** Dado un administrador[admin1]  y una semana con 2 retos con initialDate=[01/03/2030], cuando este intente añadir dos con los mismos datos que en el primer caso pero con el mismo initialDate de esta semana y endDate=[05/03/2030] y nombres "Challenge 3" y "Challenge 4", entonces el primero se creará, pero el segundo indicará que hay un máximo de 3 test por semana.
* **Añadir reto semanal en fecha anterior:** Dado un administrador[admin1], cuando este intente añadir un reto con los mismos datos que el primero pero con initialDate [01/01/2010], entonces se le indicará que no es posible debido a que solo se puede añadir en fechas posteriores.
* **Añadir reto con errores en el formulario:** Dado un administrador[admin1], cuando este intente añadir un reto con los siguientes errores: Campos vacíos, fecha inicial posterior a la final o puntos o repeticiones negativos, se deberán indicar los errores.
* **Todos los escenarios anteriores también ocurrirán cuando se actualice un reto:** Solo se pueden actualizar los retos que no tienen inscripciones
* **Eliminar un reto:** Dado un administrador[admin1] y un reto sin inscripciones, este clickará sobre él y podrá eliminarlo.

---

#### HU2 - Dashboard Retos

Como administrador quiero visualizar el cliente y el gremio con más puntos de cada mes, para poder saber quienes son los ganadores y otorgarles sus respectivos premios, y diagramas con el porcentaje de clientes y gremios que han completado los retos de un mes para poder adecuarlos en temática y dificultad correctamente.

#### Escenarios: 

* **Visualizar datosindividual en periodo con retos:** Dado un administrador [admin1] y el mes [01/2020], cuando este intente visualizar los datos, entonces se le mostrarán que [Julio Enrique Guerrero, 10 puntos] y [Gym for Dummies, 10 puntos] han ganado y que el reto 1 lo han completado [9,09%] de los clientes y un [33,3%].
* **Visualizar datos en periodo sin retos:** Dado un administrador [admin1] y el mes [02/2021], cuando este intente visualizar los datos, se le mostrará que no hay retos que finalicen ese mes y se le propondrá crear uno.
* **Visualizar datos en periodo sin retos completados:** Dado un administrador [admin1] y el mes [10/2020], cuando este intente visualizar los datos, se le mostrará que no hay retos completados ese mes.
---

#### HU3 - Dashboard Máquinas

Como administrador quiero visualizar diagrama que me muestren cuanto son utilizadas las máquinas de las que dispone el gimnasio para saber cuales requieren un mayor mantenimiento o si es necesario adquirir más de ellas o desecharlas.

#### Escenarios:

* **Visualizar diagrama general de máquinas, sistema con datos de la última semana:** Dado un administrador con usuario 'admin1' y un sistema con máquinas registradas y usadas en la última semana, cuando este intente visualizar el diagrama general de máquinas, entonces se le mostrará el nombre de estas, cuantas veces han sido utilizadas en la última semana.
* **Visualizar diagrama general de máquinas, sistema con datos del último mes:** Dado un administrador con usuario 'admin1' y un sistema con máquinas registradas y usadas en el último mes, cuando este intente visualizar el diagrama general de máquinas, entonces se le mostrará el nombre de estas, cuantas veces han sido utilizadas en el último mes.
* **Visualizar diagrama general de máquinas, sistema sin datos:** Dado un administrador con usuario 'admin1' y un sistema sin entrenamientos registrados, cuando este intente visualizar el diagrama general de máquinas, entonces se le indicará que no hay ninguna.

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

### Entrenador

---

#### HU8 - Añadir rutinas a los entrenamientos

Como entrenador quiero realizar rutinas a nuestros clientes para facilitar el desarrollo físico del cliente

#### Escenarios:

* **Añadir una rutina a un cliente, con atributos sin conflictos:** Dado un entrenador que quiere añadir una rutina a uno de los clientes que entrena, este introduce los datos requeridos que son un nombre para la rutina, una descripción general y repeticiones por semana, cuando se intenta añadir la rutina, la operación se efectúa correctamente y redirige al entrenador a una vista de rutinas desde donde puede ver la que ha creado.
* **Añadir una rutina a un cliente, sin atributos:** Dado un entrenador que quiere añadir una rutina a uno de los clientes que entrena, cuando intenta añadir dicha rutina sin añadir los parámetros requeridos (nombre, descripción y repeticiones por semana) la operación no se efectúa y redirige al entrenador a la misma vista de creación informándole de los errores, que en este caso es que los parámetros requeridos están vacíos.
* **Añadir una rutina a un cliente, con repeticiones por semana superior mayor que 20:** Dado un entrenador que quiere añadir una rutina a uno de los clientes que entrena, cuando intenta asignarle un número de repeticiones superior a 20, la vista le redirecciona a la misma vista de creación indicándole el error y atributo que provoca el conflicto.
* **Añadir una rutina a un cliente, con repeticiones por semana inferior a 1:** Dado un entrenador que quiere añadir una rutina a uno de los clientes que entrena, cuando intenta asignarle un número de repeticiones inferior a 1, la vista le redirecciona a la misma vista de creación indicándole el error y atributo que provoca el conflicto.
* **Añadir una rutina a un cliente, cuando el entrenamiento al que pertenece esa rutina ha finalizado:** Dado un entrenador que intenta añadir una rutina, cuando un entrenamiento ha finalizado, la vista no le deja hacer redirección a los controles de gestión de rutas (añadir, editar y borrar). En el caso que intente acceder desde la URL, se le redirige a la vista de error.
* **Añadir una rutina a un cliente que no entrena:** Dado un entrenador que quiere añadir una rutina a un cliente que no entrena, desde las opciones mostradas, este no puede acceder a otros clientes salvo los suyos. En el caso que intente a través de URL, se le redirecciona a la vista de error.

---

#### HU9 - Gestionar las rutinas de los entrenamientos 

Como entrenador quiero llevar el control de las rutinas para poder cambiar la rutina adaptándola de la mejor forma al cliente y corregir errores.

#### Escenarios:

(En cuanto a operaciones de gestión, nos referimos a POST: Editar y borrar)

* **Gestionar la rutina de un cliente con atributos sin conflictos:** Dado un entrenador que se dispone a gestionar la rutina de un cliente, cuando ese cliente no posee ninguna rutina. La vista de encargada la gestión de rutinas notificará al entrenador de que el cliente indicado que no posee ninguna rutina y le ofertará la opción de crearla. 

* **Gestionar la rutina de un cliente cuyo cliente no entrenas:** Dado un entrenador que se dispone a gestionar la rutina de un cliente, cuando no eres entrenador de ese cliente. La operación de gestión no se puede efectuar. El sistema limita a solo poder ver la de los clientes que entrena por ello solo puede manipular

* **Gestionar una rutina a un cliente, cuando el entrenamiento al que pertenece esa rutina ha finalizado:** Dado un entrenador que intenta gestionar una rutina, cuando un entrenamiento ha finalizado, la vista no le deja hacer redirección a los controles de gestión de rutas (añadir, editar y borrar). En el caso que intente acceder desde la URL, se le redirige a la vista de error.

* **Gestionar una rutina a un cliente con atributos con conflictos:** Dado un entrenador que intenta gestionar una rutina, en el caso de editar, cuando intenta editar la rutina y:

  - Deja los atributos nombre, descripción o repeticiones por semana vacíos
  - Repeticiones por semana tiene un valor inferior a 1

  La vista redirecciona al entrenador a la misma vista de editar indicándole el error/es y atributo/os que provocan el conflicto.

---

#### HU10 - Añadir dietas alimenticias a los entrenamientos

Como entrenador quiero realizar dietas a los clientes para mejorar su evolución física, haciéndola más eficiente.

#### Escenarios:
* **Añadir una dieta a un cliente que no posee una dieta:** Dado un entrenador que se dispone a añadir una dieta a un usuario con nombre "Dieta 1", descripción "Descripción dieta 1" y tipo "VOLUME" se autogenera la dieta en función de los datos del cliente, se le añade al entrenamiento y se redirige a la vista de detalles del entrenamiento.
* **Añadir una dieta a un cliente que posee una dieta:** Dado un entrenador que se dispone a añadir una dieta a un usuario con nombre "Dieta 1", descripción "Descripción dieta 1" y tipo "VOLUME" se autogenera la dieta en función de los datos del cliente, se sustituye a la que tenía previamente entrenamiento y se redirige a la vista de detalles del entrenamiento.
* **Añadir una dieta a un usuario, sin datos completos:** Dado un entrenador que añade una dieta a un usuario, cuando se intenta añadir sin rellenar todos los datos(Name: "Dieta 1", Description: null, Type: VOLUME). La operación de añadir dieta no se efectúa resultando en el sistema informando al entrenador que debe rellenar la información correspondiente a "Name", "Description", "Type".
* **Añadir una dieta a un usuario el cual no entrenas:** Dado un entrenador que intenta añadir una dieta a un usuario, cuando no entrenas a este usuario. La operación de añadir dieta no se realiza. La vista muestra una excepción al entrenador.
* **Añadir una dieta a un entrenamiento que no existe:** Dado un entrenador que intenta añadir una dieta a un entrenamiento que no existe, la operación de añadir dieta no se realiza. La vista muestra una excepción al entrenador.


---

#### HU11 - Gestionar la dieta alimenticia de los entrenamientos en función de kilocalorías, nutrientes esenciales, peso y porcentaje de grasa

Como entrenador quiero llevar el control de las dietas de los clientes en función de cantidad de kilocalorías, nutrientes esenciales, peso y porcentaje de grasa para poder cambiar las cosas necesarias con el fin de mejorar le evolución del cliente

#### Escenarios:
* **Gestionar la dieta de un usuario:** Dado un entrenador que se dispone a actualizar la dieta de un usuario sin incumplir ninguna regla de negocio (Name: "Dieta 1", Description: "Descripción dieta 1", Type: VOLUME, kcal: 1, carb: 1,protein: 1, fat: 1), cuando este usuario ya posee una. La operación de gestión se efectúa correctamente y esta queda disponible tanto por el usuario para poder visualizar los cambios como para el entrenador poder modificarlos en un futuro.
* **Gestionar la dieta de un usuario, sin datos completos:** Dado un entrenador que añade una dieta a un usuario, cuando se intenta añadir sin rellenar todos los datos (Name: "Dieta 1", Description: "Descripción dieta 1", Type: VOLUME, kcal: 1, carb: 1,protein: 1, fat: null) la operación de añadir dieta no se efectúa resultando en el sistema informando al entrenador que debe rellenar la información correspondiente a "Name", "Description", "Type", "kcal", "protein", "carb", "fat".
* **Gestionar la dieta de un usuario, con datos erróneos:** Dado un entrenador que añade una dieta a un usuario, cuando se intenta añadir con datos erróneos (Name: "Dieta 1", Description: "Descripción dieta 1", Type: VOLUME, kcal: 1, carb: 1,protein: 1, fat: -1) la operación de añadir dieta no se efectúa resultando en el sistema informando al entrenador que debe cumplir la restricciones correspondientes a "kcal", "protein", "carb", "fat".
* **Gestionar la dieta de un usuario cuyo usuario no entrenas:** Dado un entrenador que intenta actualizar la dieta a un usuario , cuando no entrena a este usuario. La operación de añadir dieta no se realiza. La vista muestra una excepción al entrenador.
* **Gestionar la dieta a un entrenamiento que no existe:** Dado un entrenador que intenta gestionar la dieta a un entrenamiento que no existe, la operación de gestionar dieta no se realiza. La vista muestra una excepción al entrenador.

---

#### HU12 - Añadir un entrenamiento 

Como entrenador quiero añadir entrenamientos a mis clientes para poder organizar sus actividades y sus dietas, facilitando su desarrollo físico.

#### Escenarios:

###### En todos los escenarios el permiso de edición no es compartido con el cliente.

* **Añadir entrenamiento a un cliente que él no entrena:** Dado un entrenador con nombre de usuario "trainer1", que quiere añadir un entrenamiento con nombre "Nuevo Entrenamiento", fecha de inicio igual a la actual, y fecha de fin dentro de 7 días desde la actual; a un cliente que él no entrena con nombre de usuario "client3", cuando intenta acceder a la vista de creación a través de la URL, entonces se le redireccionará a la vista de error.
* **Añadir entrenamiento a uno de sus clientes, con atributos sin errores:** Dado un entrenador con nombre de usuario "trainer1", que quiere añadir un entrenamiento con nombre "Nuevo Entrenamiento", fecha de inicio igual a la actual, y fecha de fin dentro de 7 días desde la actual; a uno de sus clientes con nombre de usuario "client1", cuando se intenta añadir el entrenamiento, entonces se añade el entrenamiento al cliente y se redirige a la vista de detalles del entrenamiento.
* **Añadir entrenamiento a uno de sus clientes, sin introducir atributos:** Dado un entrenador con nombre de usuario "trainer1", que quiere añadir un entrenamiento sin nombre, ni fecha de inicio, ni fecha de fin; a uno de sus clientes con nombre de usuario "client1", cuando se intenta añadir el entrenamiento, entonces no se añade el entrenamiento y se redirige a la vista de creación informando de que debe cumplimentar los campos.
* **Añadir entrenamiento a uno de sus clientes, fecha de inicio un día antes de la actual:** Dado un entrenador con nombre de usuario "trainer1", que quiere añadir un entrenamiento con nombre "Nuevo Entrenamiento", fecha de inicio 1 día antes de la actual, y fecha de fin dentro de 7 días desde la actual; a uno de sus clientes con nombre de usuario "client1", cuando se intenta añadir el entrenamiento, entonces no se añade el entrenamiento al cliente y se redirige a la vista de creación informando de que la fecha de inicio no debe estar en el pasado.
* **Añadir entrenamiento a uno de sus clientes, fecha de fin un día antes de la de inicio:** Dado un entrenador con nombre de usuario "trainer1", que quiere añadir un entrenamiento con nombre "Nuevo Entrenamiento", fecha de inicio igual a la actual, y fecha de fin 1 día antes de la actual; a uno de sus clientes con nombre de usuario "client1", cuando se intenta añadir el entrenamiento, entonces no se añade el entrenamiento al cliente y se redirige a la vista de creación informando de que la fecha de fin debe ser posterior a la de inicio.
* **Añadir entrenamiento a uno de sus clientes, fecha de fin igual a la de inicio:** Dado un entrenador con nombre de usuario "trainer1", que quiere añadir un entrenamiento con nombre "Nuevo Entrenamiento", fecha de inicio igual a la actual, y fecha de fin igual a la actual; a uno de sus clientes con nombre de usuario "client1", cuando se intenta añadir el entrenamiento, entonces no se añade el entrenamiento al cliente y se redirige a la vista de creación informando de que la fecha de fin debe ser posterior a la de inicio.
* **Añadir entrenamiento a uno de sus clientes, entrenamiento de más de 90 días:** Dado un entrenador con nombre de usuario "trainer1", que quiere añadir un entrenamiento con nombre "Nuevo Entrenamiento", fecha de inicio igual a la actual, y fecha de fin dentro de 91 desde la actual; a uno de sus clientes con nombre de usuario "client1", cuando se intenta añadir el entrenamiento, entonces no se añade el entrenamiento al cliente y se redirige a la vista de creación informando de que el entrenamiento no puede durar más de 90 días.
* **Añadir entrenamiento a uno de sus clientes, fecha de inicio coincidiendo con otro entrenamiento:** Dado un cliente con nombre de usuario "client1", con un entrenamiento que comienza en la fecha actual y finaliza dentro de 7 días desde la actual; y su entrenador con nombre de usuario "trainer1" que quiere añadir un entrenamiento con nombre "Nuevo Entrenamiento", fecha de inicio igual a la actual, y fecha de dentro de 8 días; a ese cliente, cuando se intenta añadir el entrenamiento, entonces no se añade el entrenamiento al cliente y se redirige a la vista de creación informando de que la fecha de inicio no puede coincidir con otro entrenamiento.
* **Añadir entrenamiento a uno de sus clientes, fecha de fin coincidiendo con otro entrenamiento:** Dado un cliente con nombre de usuario "client1", con un entrenamiento que comienza dentro de 1 día y finaliza dentro de 7 desde la actual; y su entrenador con nombre de usuario "trainer1" que quiere añadir un entrenamiento con nombre "Nuevo Entrenamiento", fecha de inicio igual a la actual, y fecha de fin dentro de 7 días desde la actual; a ese cliente, cuando se intenta añadir el entrenamiento, entonces no se añade el entrenamiento al cliente y se redirige a la vista de creación informando de que la fecha de fin no puede coincidir con otro entrenamiento.
* **Añadir entrenamiento a uno de sus clientes, entrenamiento incluyendo otro:** Dado un cliente con nombre de usuario "client1", con un entrenamiento que se inicia dentro de 1 día desde la actual y finaliza dentro de 7 días desde la actual; y su entrenador con nombre de usuario "trainer1" que quiere añadir un entrenamiento con nombre "Nuevo Entrenamiento", fecha de inicio igual a la actual, y fecha de fin dentro de 8 días; a ese cliente, cuando se intenta añadir el entrenamiento, entonces no se añade el entrenamiento al cliente y se redirige a la vista de creación informando de que el entrenamiento no puede incluir a otro.

---

#### HU13 - Gestionar el entrenamiento de nuestros clientes

Como entrenador quiero llevar el control de los entrenamientos de mis clientes conociendo el estado de estos para poder modificar los periodos en los que transcurren y así adaptarlos de la mejor forma a los clientes.

#### Escenarios:

* **Listar entrenamientos de sus clientes:** Dado un entrenador con nombre de usuario "trainer1", que se dispone a gestionar los entrenamientos de sus clientes, cuando accede a la vista de gestión de entrenamientos, entonces se le listarán sus clientes junto con sus entrenamientos indicando de estos: nombre y su estado, es decir, si está en curso o finalizado.
* **Listar entrenamientos de clientes que no entrena:** Dado un entrenador con nombre de usuario "trainer1", que se dispone a gestionar los entrenamientos de clientes que no entrena, cuando intenta acceder a la vista de gestión de entrenamientos de otro entrenador con nombre de usuario "trainer2" a través de la URL, entonces se le redireccionará a la vista de error.
* **Ver detalles del entrenamiento de uno de sus clientes:** Dado un entrenador con nombre de usuario "trainer1", que se dispone a gestionar el entrenamiento de uno de sus clientes con nombre de usuario "client1" del que tiene permisos de edición y es autor, cuando accede a la vista de gestión del entrenamiento, entonces se le mostrará la siguiente información del entrenamiento: nombre, fecha inicio, fecha fin, permiso de edición, un listado de sus rutinas, su dieta, y enlaces para la edición y borrado del entrenamiento.
* **Ver detalles del entrenamiento de un cliente que no entrena:** Dado un entrenador con nombre de usuario "trainer1", que se dispone a gestionar el entrenamiento de un cliente que no entrena con nombre de usuario "client3", cuando accede a la vista de gestión del entrenamiento a través de la URL, entonces se le redireccionará a la vista de error.
* **Actualizar entrenamiento en curso de uno de sus clientes, con permisos de edición:** Dado un entrenador con nombre de usuario "trainer1", que se dispone a actualizar el entrenamiento con nombre "Entrenamiento1", fecha de inicio igual a la actual menos 7 días y fecha de fin igual a la actual más 7 días; de uno de sus clientes con nombre de usuario "client6" del que tiene permisos de edición, cuando intenta actualizar el nombre a “Entrenamiento 1 Actualizado” y la fecha de fin a la actual, entonces se actualizará el entrenamiento y se le redireccionará a la vista de detalles de este.
* **Actualizar entrenamiento de uno de sus clientes, sin permisos de edición:** Dado un entrenador con nombre de usuario "trainer1", que se dispone a actualizar el entrenamiento con nombre “Entrenamiento2” de uno de sus clientes con nombre de usuario "client5" del que no tiene permisos de edición, cuando intenta acceder a través de la URL, entonces se le redireccionará a la vista de error.
* **Actualizar entrenamiento en curso, nombre y fecha de fin vacíos:** Dado un entrenador con nombre de usuario "trainer1", que se dispone a actualizar el entrenamiento con nombre "Entrenamiento1", fecha de inicio igual a la actual menos 7 días y fecha de fin igual a la actual más 7 días; de uno de sus clientes con nombre de usuario "client6" del que tiene permisos de edición, cuando intenta actualizar el nombre y la fecha de fin a vacío, entonces no se actualizará el entrenamiento y se le devolverá a la vista de actualización informándole de que esos campos no pueden estar vacíos.
* **Actualizar entrenamiento en curso, fecha de fin en el pasado:** Dado un entrenador con nombre de usuario "trainer1", que se dispone a actualizar el entrenamiento con nombre "Entrenamiento1", fecha de inicio igual a la actual menos 7 días y fecha de fin igual a la actual más 7 días; de uno de sus clientes con nombre de usuario "client6" del que tiene permisos de edición, cuando intenta actualizar la fecha de fin a la de ayer, entonces no se actualizará el entrenamiento y se le devolverá a la vista de actualización informándole de que la fecha de fin no puede actualizarse a una en el pasado.
* **Actualizar entrenamiento en curso, duración superior a 90 días:** Dado un entrenador con nombre de usuario "trainer1", que se dispone a actualizar el entrenamiento con nombre "Entrenamiento1", fecha de inicio igual a la actual menos 7 días y fecha de fin igual a la actual más 7 días; de uno de sus clientes con nombre de usuario "client6" del que tiene permisos de edición, cuando intenta actualizar la fecha de fin a una dentro de 84 días, entonces no se actualizará el entrenamiento y se le devolverá a la vista de actualización informándole de que el entrenamiento no puede superar los 90 días de duración.
* **Gestionar entrenamiento en curso, fecha fin coincide con otro entrenamiento:** Dado un cliente con nombre de usuario "client6", con un entrenamiento que se inicia dentro de 8 días desde la fecha actual y finaliza dentro de 15 días desde la fecha actual; y un entrenador con nombre de usuario "trainer1" que se dispone a actualizar el entrenamiento con nombre "Entrenamiento 1", fecha de inicio igual a la actual menos 7 días y fecha de fin igual a la actual más 7 días; de ese cliente, cuando intenta actualizar la fecha de fin a una dentro de 8 días desde la actual, entonces no se actualizará el entrenamiento y se le devolverá a la vista de actualización informándole de que la fecha de fin no puede coincidir con otro entrenamiento.
* **Gestionar entrenamiento en curso, entrenamiento incluye otro:** Dado un cliente con nombre de usuario "client6", con un entrenamiento que se inicia dentro de 8 días desde la fecha actual y finaliza dentro de 15 días desde la fecha actual; y un entrenador con nombre de usuario "trainer1" que se dispone a actualizar el entrenamiento con nombre "Entrenamiento 1", fecha de inicio igual a la actual menos 7 días y fecha de fin igual a la actual más 7 días; de ese cliente, cuando intenta actualizar la fecha de fin a una dentro de 16 días desde la actual, entonces no se actualizará el entrenamiento y se le devolverá a la vista de actualización informándole de que el entrenamiento no puede incluir otro.
* **Eliminar entrenamiento de uno de sus clientes, siendo autor:** Dado un entrenador con nombre de usuario "trainer1", que se dispone a eliminar el entrenamiento de uno de sus clientes con nombre de usuario "client1" del que es autor, cuando acceder al enlace de eliminación, entonces se eliminará el entrenamiento y se le redireccionará al listado informándole de que se realizó la operación con éxito.
* **Eliminar entrenamiento de uno de sus clientes, con permisos de edición, pero sin ser autor:** Dado un entrenador con nombre de usuario "trainer1", que se dispone a eliminar el entrenamiento de uno de sus clientes con nombre de usuario "client6" del que tiene permisos de edición, pero del que no es autor, cuando intenta acceder a través de la URL, entonces se le redireccionará a la vista de error.

---

#### HU17 - Copiar entrenamiento de otro cliente

Como entrenador quiero poder copiar los entrenamientos de otros clientes con perfil público para ahorrar tiempo a la hora de hacer entrenamientos similares.

#### Escenarios:

* **Mostrar entrenamientos a copiar a un entrenamiento vacío:** Dado un entrenador con usuario 'trainer1', y un cliente con id '1' que cuente con un entrenamiento que no contenga rutinas ni dietas, mostrarle un listado de entrenamientos de otros clientes con perfil público, y que tengan rutinas, dietas o ambas, para poder copiarla.
* **Mostrar entrenamientos a copiar a un entrenamiento no vacío:** Dado un entrenador con usuario 'trainer1', y un cliente con id '1' que cuente con un entrenamiento que contenga rutinas, dietas o ambas, redirigir a la vista de error.

---

#### HU24 - Añadir línea de rutinas a los entrenamientos

Como entrenador quiero añadir líneas de rutinas a nuestros clientes para facilitar el seguimiento de la rutina

#### Escenarios:

* **Añadir una línea de rutina a un cliente, con atributos sin conflictos:** Dado un entrenador que quiere añadir una rutina a uno de los clientes que entrena, este introduce los datos, la operación se efectúa correctamente y redirige al entrenador a una vista de rutinas desde donde puede ver la que ha creado.
* **Añadir una rutina a un cliente, sin atributos:** Dado un entrenador que quiere añadir una línea de rutina a uno de los clientes que entrena, cuando intenta añadir dicha rutina sin añadir los parámetros requeridos (tiempo o repetición, series, peso y ejercicio) la operación no se efectúa y redirige al entrenador a la misma vista de creación informándole de los errores, que en este caso es que los parámetros requeridos están vacíos.
* **Añadir una rutina a un cliente, con conflictos:** Dado un entrenador que quiere añadir una línea de rutina a uno de los clientes que entrena, cuando intenta efectuar la operación con alguno de los siguientes conflictos:
  - Tiempo y repeticiones tienen valores válidos. Solo puedes realizar el ejercicio que especifica la línea de rutina por repetición o tiempo, no ambos.
  - Tiempo o repeticiones con valor 0 o negativo. Debe especificar alguno de estos con un valor válido (mayor o igual que 1).
  - Series de la línea de rutina debe ser superior a 1.
  - Peso debe ser 0 o un número positivo
  - Ejercicio no nulo

En cuanto alguno de estos conflictos se cumplan, se redirige al entrenador a la vista de añadir vista indicándole los errores y atributos en conflicto.

* **Añadir una línea de rutina a un cliente, cuando el entrenamiento al que pertenece esa rutina ha finalizado:** Dado un entrenador que intenta añadir una rutina, cuando un entrenamiento ha finalizado, la vista no le deja hacer redirección a los controles de gestión de rutas (añadir, editar y borrar). En el caso que intente acceder desde la URL, se le redirige a la vista de error.
* **Añadir una línea de rutina a un cliente que no entrena:** Dado un entrenador que quiere añadir una rutina a un cliente que no entrena, desde las opciones mostradas, este no puede acceder a otros clientes salvo los suyos. En el caso que intente a través de URL, se le redirecciona a la vista de error.

---

#### HU25 - Gestionar las líneas de rutinas de los entrenamientos

Como entrenador quiero llevar el control de las líneas de rutinas para poder cambiar la rutina adaptándola de la mejor forma al cliente y corregir errores.

#### Escenarios:

* **Gestionar la línea de rutina de un cliente con atributos sin conflictos:** Dado un entrenador que se dispone a gestionar la línea de rutina de un cliente cuando necesita realizar algún tipo de modificación. La vista de encargada la gestión de rutinas notificará al entrenador de que el cliente indicado que no posee ninguna rutina y le ofertará la opción de crearla. 
* **Gestionar la línea de rutina de un cliente con atributos sin conflictos:** Dado un entrenador que se dispone a gestionar la rutina de un cliente, en el caso de editar, cuando efectúa la operación con los siguientes conflictos:
  - Tiempo y repeticiones tienen valores válidos. Solo puedes realizar el ejercicio que especifica la línea de rutina por repetición o tiempo, no ambos.
  - Tiempo o repeticiones con valor 0 o negativo. Debe especificar alguno de estos con un valor válido (mayor o igual que 1).
  - Series de la línea de rutina debe ser superior a 1.
  - Peso debe ser 0 o un número positivo
  - Ejercicio no nulo

En caso de que salte alguna de estas restricciones, la vista redirige al entrenador a la misma vista de actualizar indicándole el error y atributo que provoca el conflicto.

* **Gestionar una línea de rutina a un cliente, cuando el entrenamiento al que pertenece esa línea de rutina ha finalizado:** Dado un entrenador que intenta gestionar una línea de rutina, cuando un entrenamiento ha finalizado, la vista no le deja hacer redirección a los controles de gestión de rutas (añadir, editar y borrar). En el caso que intente acceder desde la URL, se le redirige a la vista de error.
* **Gestionar una línea de rutina a un cliente que no entrena:** Dado un entrenador que quiere añadir una línea de rutina a un cliente que no entrena, desde las opciones mostradas, este no puede acceder a otros clientes salvo los suyos. En el caso que intente a través de URL, se le redirecciona a la vista de error.

---

### Cliente

---

#### HU5 - Crear y eliminar gremios

Como cliente quiero poder crear gremios, para que en un futuro puedan inscribirse otros clientes e ir adquiriendo puntos para recibir futuras recompensas, también quiero poder actualizar y eliminar aquel gremio que he creado.

#### Escenarios:

* **Crear un gremio:** Dado un cliente, quiero poder crear un gremio, para ello en la vista donde podemos observar todos los gremios existentes, aparecerá un botón para poder crear tú propio gremio, para ello debemos introducir un nombre, una descripción y una url, la cuál será la imagen de nuestro gremio. El creador se selecciona automáticamente, pasando a ser el nombre de dicho cliente que está creando dicho gremio.
* **Crear un gremio sin introducir atributos:** Dado un cliente, al intentar crear un gremio, si este no introduce valores en los campos nombre, descripción y logo, saltará una excepción en aquellos campos que se encuentren vacíos y deberá cumplimentarlos.
* **Crear un gremio introduciendo el mismo nombre que otro gremio:** Dado un cliente, al intentar crear un gremio, si introduce un nombre que posee otro gremio le saltará una excepción, la cuál le informará de que ya existe otro gremio con dicho nombre.
* **Crear un gremio siendo un cliente con otro gremio ya creado por él mismo:** Dado un cliente, al intentar crear un gremio si este ya ha creado un gremio, saltará una excepción en el campo de creador, informando de que dicho cliente ya posee un gremio creado por él, ya que un cliente solo puede tener un gremio.
* **Crear un gremio con una mal url:** Dado un cliente, al intentar crear un gremio, si este introduce en el campo de logo una url que no comience por "https://" lanzará una excepción.
* **Actualizar un gremio:** Dado un cliente, al introducirse en aquel gremio creado por el mismo, aparecerá un botón de editar, en el cuál podrá cambiar el nombre, la descripción o el logo.
  Actualizar posee exactamente las mismas restricciones que crear, excepto la del mismo creador.
* **Eliminar un gremio:** Dado un cliente, al intentar crear su gremio, en la vista de la información de dicho gremio aparecerá un botón de eliminar, al pulsarlo se eliminará dicho gremio.
* **Eliminar un gremio sin ser el creador:** Dado un cliente, al intentar eliminar un gremio que no ha sido creado por él, le lanzará a una página de error, y dicha acción no se llevará a cabo.

---

#### HU14 - Apuntarme y salirme de gremios

Como cliente quiero poder unirme a gremios para poder formar parte de una comunidad y participar para poder recibir premios, y poder salirme de aquel gremio al que me haya unido.

#### Escenarios:

* **Unirse a un gremio:** Como cliente, al intentar unirme a un gremio, primero veremos la lista con todos los gremios creados, acto seguido al seleccionar la que más nos interese podremos ver toda la información de la misma, y al pulsar el botón de unirnos, pasaremos a formar parte de dicho gremio.
* **Abandonar un gremio:** Como cliente, al salirme de un gremio, primero iremos a la vista de nuestro gremio dándonos toda la información de la misma, y pulsaremos sobre el botón de abandonar este gremio, de tal forma que ya no formaremos parte de dicho gremio y nos podremos unir a otro diferente si lo deseamos.
* **Abandonar un gremio al que no perteneces:** Como cliente, al intentar abandonar un gremio al que no pertenezco nos llevará a una página de excepción, no realizándose ninguna acción.

---

#### HU15 - Consultar todos los ejercicios disponibles

Como cliente quiero consultar todos los ejercicios disponible para poder coger ideas y realizar mis propias rutinas y entrenamientos.

#### Escenarios:

* **Consultar ejercicios disponibles:** Dado un cliente y un ejercicio publicado o disponible le muestra la información del ejercicio.
* **Consultar ejercicios no disponibles:** Dado un cliente y un ejercicio no publicado o no disponible salta un aviso de que el ejercicio no está disponible.

---

#### HU16 - Personalizar dieta de mi entrenamiento

Como cliente quiero poder tener dietas personalizadas según mi tipo de entrenamiento para maximizar el resultado del ejercicio.

#### Escenarios:

* **Añadir una dieta a un cliente con un entrenamiento con ejercicios:** Dado un cliente que se dispone a añadir una dieta con nombre "Dieta 1", descripción "Descripción dieta 1" y tipo "AUTO_ASSIGN" se autogenera la dieta en función de los datos del cliente y seleccionando el tipo de dieta que mejor funcione con la intensidad del entrenamiento asignado, se le añade al entrenamiento y se redirige a la vista de dietas.

* **Añadir una dieta a un cliente con un entrenamiento con ejercicios:** Dado un cliente que se dispone a añadir una dieta con nombre "Dieta 1", descripción "Descripción dieta 1" y tipo "AUTO_ASSIGN" a un entrenamiento "Entrenamiento 1" que no tiene ejercicios, se autogenera la dieta en función de los datos del cliente y seleccionando el tipo de dieta "MAINTENANCE", se le añade al entrenamiento y se redirige a la vista de dietas.

* **Cliente con entrenamiento finalizado:** Dado un cliente con entrenamiento de nombre "Entrenamiento 1" y con fecha de finalización anterior a la actual no se le mostrará el enlace de añadir dieta a dicho entrenamiento.

* **Cliente con entrenamiento asignado:** Dado un cliente con entrenamiento de nombre "Entrenamiento 1" y con fecha de finalización posterior a la actual se le mostrará el enlace de añadir dieta a dicho entrenamiento.

---

#### HU18- Dashboard Cliente

Como cliente quiero poder tener un dashboard donde poder ver el Historial de actividades, Calorías quemadas y otros datos relacionados con mi entrenamiento.

#### Escenarios:

* **Ver dashboard con datos:** Dado un cliente con usuario 'client1', y un sistema con ejercicios registrados para este cliente, al pulsar el botón de "Dashboard", aparecerá las kcal gastadas este mes y un histórico, además de dos gráficas (mensual e histórico).
* **Ver dashboard sin datos:** Dado un cliente con usuario 'client3', y un sistema sin entrenamientos registrados para este cliente al pulsar el botón de "Dashboard", aparecerá un mensaje de que no hay datos de ejercicios registrados.
* **Ver dashboard de otro cliente:** Dado un cliente con usuario 'client3', cambiar la url para ver su dashboard para ver el de 'client1', se redirigirá a una vista de excepción.
* **Ver dashboard sin datos de este mes:** Dado un cliente con usuario 'client2', y un sistema sin ejercicios registrados este mes para este cliente, al pulsar el botón de "Dashboard", aparecerá un mensaje de que no hay datos de ejercicios registrados este mes y justo abajo las gráficas y kcal del histórico.
* **Ver dashboard sin partes del cuerpo de este mes:** Dado un cliente con usuario 'client4', y un sistema sin partes del cuerpo registradas este mes para este cliente, al pulsar el botón de "Dashboard", aparecerá un mensaje de que no hay datos de partes del cuerpo registradas este mes y justo abajo el resto de gráficos de este mes, y las gráficas y kcal del histórico.
* **Ver dashboard sin tipos de repetición de este mes:** Dado un cliente con usuario 'client4', y un sistema sin tipos de repetición registrados este mes para este cliente, al pulsar el botón de "Dashboard", aparecerá un mensaje de que no hay datos de tipos de repetición registrados este mes y justo abajo el resto de gráficos de este mes, y las gráficas y kcal del histórico.
* **Ver dashboard sin kcal de este mes:** Dado un cliente con usuario 'client4', y un sistema sin kcal registradas este mes para este cliente, al pulsar el botón de "Dashboard", aparecerá un mensaje de que no hay datos de kcal registradas este mes y justo abajo el resto de gráficos de este mes, y las gráficas y kcal del histórico.
* **Ver dashboard sin partes del cuerpo:** Dado un cliente con usuario 'client4', y un sistema sin partes del cuerpo registradas para este cliente, al pulsar el botón de "Dashboard", aparecerá un mensaje de que no hay datos de partes del cuerpo registradas y justo abajo el resto de gráficos histórico.
* **Ver dashboard sin tipos de repetición:** Dado un cliente con usuario 'client4', y un sistema sin tipos de repetición registrados para este cliente, al pulsar el botón de "Dashboard", aparecerá un mensaje de que no hay datos de tipos de repetición registrados y justo abajo el resto de gráficos histórico.
* **Ver dashboard sin kcal:** Dado un cliente con usuario 'client4', y un sistema sin kcal registradas para este cliente, al pulsar el botón de "Dashboard", aparecerá un mensaje de que no hay datos de kcal registradas y justo abajo el resto de gráficos histórico.
* **Ver dashboard sin nombres partes del cuerpo de este mes:** Dado un cliente con usuario 'client4', y un sistema sin nombres partes del cuerpo registradas este mes para este cliente, al pulsar el botón de "Dashboard", aparecerá un mensaje de que no hay datos de partes del cuerpo registradas este mes y justo abajo el resto de gráficos de este mes, y las gráficas y kcal del histórico.
* **Ver dashboard sin cuenta partes del cuerpo de este mes:** Dado un cliente con usuario 'client4', y un sistema sin cuenta partes del cuerpo registradas este mes para este cliente, al pulsar el botón de "Dashboard", aparecerá un mensaje de que no hay datos de partes del cuerpo registradas este mes y justo abajo el resto de gráficos de este mes, y las gráficas y kcal del histórico.
* **Ver dashboard sin nombre tipos de repetición de este mes:** Dado un cliente con usuario 'client4', y un sistema sin nombre tipos de repetición registrados este mes para este cliente, al pulsar el botón de "Dashboard", aparecerá un mensaje de que no hay datos de tipos de repetición registrados este mes y justo abajo el resto de gráficos de este mes, y las gráficas y kcal del histórico.
* **Ver dashboard sin cuenta tipos de repetición de este mes:** Dado un cliente con usuario 'client4', y un sistema sin cuenta tipos de repetición registrados este mes para este cliente, al pulsar el botón de "Dashboard", aparecerá un mensaje de que no hay datos de tipos de repetición registrados este mes y justo abajo el resto de gráficos de este mes, y las gráficas y kcal del histórico.
* **Ver dashboard con datos (varios):** Dado un cliente con usuario 'client1', y un sistema con ejercicios registrados para este cliente, al pulsar el botón de "Dashboard", aparecerá las kcal gastadas este mes y un histórico, además de dos gráficas (mensual e histórico).

---

#### HU19 - Personalizar mi entrenamiento

Como cliente quiero poder personalizar mi entrenamiento para actualizarlo o personalizarlo a mi gusto.

#### Escenarios:

* **Con permiso para edición:** Dado un cliente con un entrenamiento que pueda ser editado se le permitirá actualizarlo a su gusto.
* **Sin permiso para edición:** Dado un cliente con un entrenamiento que  no puede ser editado no se le permitirá actualizarlo.

---

#### HU20 - Comunicarme con mi gremio

Como cliente quiero poder comunicarme con los miembros de mi gremio a través de un foro, para poder compartir nuestros avances y opiniones sobre el gremio y el gimnasio.

#### Escenarios:

* **Publicar en el foro de su gremio un mensaje, con contenido:** Dado un cliente con nombre de usuario "client1", que quiere publicar un mensaje en el foro de su gremio con contenido "Mensaje de prueba", cuando se intenta publicar el mensaje, entonces se añade el mensaje a dicho foro y se redirige de nuevo a este.
* **Publicar en el foro de su gremio un mensaje, sin contenido:** Dado un cliente con nombre de usuario "client1", que quiere publicar un mensaje en el foro de su gremio con contenido vacío, cuando se intenta publicar el mensaje, entonces este no se añade a dicho foro y se redirige de nuevo a este indicando que el mensaje no es válido.
* **Responder a un mensaje del foro de su gremio, con contenido:** Dado un cliente con nombre de usuario "client5", que quiere responder a un mensaje del foro de su gremio que tiene como contenido "Mensaje de prueba" con una respuesta con contenido "Respuesta de prueba", cuando se intenta publicar la respuesta, entonces se añade la respuesta al mensaje y a dicho foro, y se redirige de nuevo a este.
* **Responder a un mensaje del foro de su gremio, sin contenido:** Dado un cliente con nombre de usuario "client5", que quiere responder a un mensaje del foro de su gremio que tiene como contenido "Mensaje de prueba" con una respuesta vacía, cuando se intenta publicar la respuesta, entonces no se añade la respuesta al mensaje ni a dicho foro, y se redirige de nuevo a este indicando que el mensaje no es válido.

---

#### HU21 - Participar en los Retos

Como cliente quiero poder participar en los distintos retos propuestos por el gimnasio, para poder ganar puntos para mi gremio y para mi mismo y estar lo mas alto posible en la clasificación.

#### Escenarios

* **Listar los retos en los que no me he inscrito** Dado un cliente[client1], pinchará en la sección "new challenges", verá un listado con los diferentes retos propuestos, en este caso el "Challenge4", ya que aún no participa en el.
* **Listar los retos en los que me he inscrito** Dado un cliente[client1], pinchará en la sección "my challenges", verá un listado con los diferentes retos en los que está inscrito, por ejemplo el "Challenge1", con estado "FAILED".
* **Inscribirme en un reto** Dado un cliente[client2], pinchará en la sección "new challenges", verá un listado con los diferentes retos en los que está inscrito, por ejemplo el "Challenge4", clickará sobre él, verá los requisitos que se piden, y podrá inscribirse en el. Una vez hecho esto, ese reto aparecerá en "my challenges" con estado "PARTICIPATING".
* **Completar un reto** Dado un cliente[client1], pinchará en "my challenges", después en el reto[Challenge5] con estado "PARTICIPATING", y pondrá una url donde demuestre que lo ha completado, y clickará submit, pasando al estado "submitted". El admin[admin1] irá a "submitted challenges", clickará sobre ese reto, y pondrá el estado "COMPLETED". Entonces en "my challenges" de [client1] el [Challenge5] aparecerá como completado.
* **Completar un reto poniendo una url no valida** Dado un cliente[client1], pinchará en "my challenges", después en el reto[Challenge2] con estado "PARTICIPATING", y pondrá una url no válida, por lo que el sistema no debe dejarle enviarlo.
---

#### HU22 - Clasificación de los Retos

Como cliente quiero poder ver la clasificación de mis retos, para poder ver en que posición me encuentro y que debo hacer para poder superar y subir al top.

#### Escenarios

* **Ver un la clasificación semanal e histórica con datos:** Dado un cliente con usuario 'client1', y un sistema con retos completados la última semana, al pulsar el botón de "Clasificación", aparecerá un listado de los retos completados, viendo también una clasificación semanal y global.
* **Ver un la clasificación semanal e histórica sin haber completado retos:** Dado un cliente con usuario 'client2', y un sistema sin retos completados para este cliente, al pulsar el botón de "Clasificación", aparecerá una clasificación semanal y global.
* **Ver un la clasificación de otro cliente:** Dado un cliente con usuario 'client2', intentar entrar en la clasificación del cliente con usuario 'client1' cambiando la url, dando como resultado una redirección a la página de excepción.
* **Ver un la clasificación sin retos:** Dado un cliente con usuario 'client1' y un sistema sin retos completados para ningún cliente, al pulsar el botón de "Clasificación", aparecerá un mensaje de que no hay retos completados.
* **Ver un la clasificación histórica con datos:** Dado un cliente con usuario 'client1', y un sistema sin retos completados la última semana, al pulsar el botón de "Clasificación", aparecerá un listado de los retos completados, viendo también una clasificación global.
* **Ver un la clasificación histórica con datos de otros:** Dado un cliente con usuario 'client2', y un sistema sin retos completados la última semana, y sin retos completados por este cliente, al pulsar el botón de "Clasificación", aparecerá una clasificación global.
* **Ver un la clasificación histórica con datos de otros y sin retos completados:** Dado un cliente con usuario 'client1', y sin retos completados por este cliente, pero si con puntos, al pulsar el botón de "Clasificación", aparecerá una clasificación semanal y global.
* **Ver un la clasificación histórica con datos de otros y sin puntos:** Dado un cliente con usuario 'client1', y sin puntos por este cliente, pero si con retos completados, al pulsar el botón de "Clasificación", aparecerá una clasificación semanal y global.
* **Ver un la clasificación sin usuarios con retos completados:** Dado un cliente con usuario 'client1', con retos completados, y un sistema en el que hay retos completados pero no se devuelven el nombre de los usuarios, al pulsar el botón de "Clasificación", aparecerá un listado de los retos completados.
* **Ver un la clasificación sin puntos:** Dado un cliente con usuario 'client1', con retos completados, y un sistema en el que hay retos completados pero no se devuelven los puntos, al pulsar el botón de "Clasificación", aparecerá un listado de los retos completados.
---

#### HU23 - Añadir Playlist adaptada a mis entrenamientos

Como cliente quiero poder añadir playlist de spotify aleatorias adaptadas a mis entrenamientos, es decir, adecuadas para el nivel de intensidad especificado, para poder motivarme y llevar un ritmo adecuado.

#### Escenarios

* **Añadir una playlist a un entrenamiento con ejercicios y spotify responde:** Dado un cliente y un entrenamiento, cuando el cliente pulsa sobre crear playlist asociada, spotify devuelve una playlist no vacía.
* **Añadir una playlist a un entrenamiento con ejercicios y spotify no responde:** Dado un cliente y un entrenamiento, cuando el cliente pulsa sobre crear playlist asociada, spotify devuelve un error o una playlist vacía, el sistema debe mostrar un mensaje de error, sugiriendo alternativas o pidiendo disculpas porque spotify no está disponible.
* **Añadir una playlist a un entrenamiento sin ejercicios:** Dado un cliente y un entrenamiento sin ejercicios, cuando el cliente pulsa sobre crear playlist asociada, el sistema debe avisarle de que deben haber ejercicos para crear una playlist personalizada.

---

## Planificación y asignación de las historias de usuario

| Id pareja | Integrantes             |
| --------- | ----------------------- |
| P1        | Enrique y Víctor        |
| P2        | Carlos y Francisco José |
| P3        | Álvaro y José Manuel    |

| Historia de Usuario | Sprint | Pareja asignada | Estado       |
| ------------------- | ------ | --------------- | ------------ |
| HU1                 | 1      | P3              | Finalizada   |
| HU3                 | 1      | P3              | Finalizada   |
| HU4                 | 1      | P2              | Finalizada   |
| HU5                 | 1      | P2              | Finalizada   |
| HU8                 | 1      | P1              | Finalizada   |
| HU9                 | 1      | P1              | Finalizada   |
| HU10                | 1      | P2              | Finalizada   |
| HU11                | 1      | P2              | Finalizada   |
| HU12                | 1      | P1              | Finalizada   |
| HU13                | 1      | P1              | Finalizada   |
| HU14                | 1      | P2              | Finalizada   |
| HU15                | 1      | P1              | Finalizada   |
| HU18                | 1      | P3              | Finalizada   |
| HU21                | 1      | P3              | Finalizada   |
| HU22                | 1      | P3              | Finalizada   |
| HU24                | 1      | P1              | Finalizada   |
| HU25                | 1      | P1              | Finalizada   |
| HU2                 | 2      | P3              | Finalizada   |
| HU6                 | 2      | P2              | Finalizada   |
| HU16                | 2      | P2              | Finalizada   |
| HU17                | 2      | P3              | Finalizada   |
| HU19                | 2      | P1              | Finalizada   |
| HU20                | 3      | P1              | Finalizada   |
| HU23                | 3      | P2              | Finalizada   |
