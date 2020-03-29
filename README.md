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
#### Entidades de soporte:
* Autoridades
* Entidad Base
* Tablero de control de Administrador
* Tablero de control de Cliente

## Historias de Usuario

### **Solo se encuentran actualizadas las historias de usuario finalizadas en el [Product Backlog](https://docs.google.com/spreadsheets/d/1-mP7vgruDpIj8CKCvBVDqFyz_7dwrxKCtpqvso2JObE/edit#gid=0). El resto requieren de modificación.**

### Administrador

---

#### HU1 - Proponer retos semanales

Como administrador quiero añadir retos semanales al gimnasio, que los clientes puedan competir por lograrlos y obtener beneficios y puntos (para su gremio) en el gimnasio si los completan, para motivarlos a realizar ciertos ejercicios y crear un ambiente competitivo.

#### Escenarios:

* **Añadir reto semanal a semana sin retos:** Dado un administrador [admin1] y una semana [1/1/2040] sin retos, cuando este intente añadir un challenge aportando la información necesaria [name="ChallengeTest", description="Test", initialDate="1/1/2040", endDate="5/1/2040", points=10,reps=10,reward="Test",weight=10. , exercise=Exercise1], entonces el reto se creará correctamente.
* **Añadir reto semanal ya existente:** Dado un administrador[admin1]  y un reto c1[igual que el anterior pero con name= "Same Name" e initialDate=1/1/2030] cuando este intente añadir uno con el nombre ["Same Name"]  en el mismo initialDate que el anterior, entonces se le indicará que esto no es posible debido a que ya existe.
* **Añadir reto semanal a semana con 3 retos:** Dado un administrador[admin1]  y 3 retos con los mismos datos que el anterior pero con initialDate=[1/1/2050], cuando este intente añadir uno con el mismo initialDate, entonces se le indicará que no es posible debido a que se ha llegado al máximo de retos esa semana.
* **Añadir reto semanal en fecha anterior:** Dado un administrador[admin1] , cuando este intente añadir un reto con los mismos datos que el anterior pero con fecha [01/02/2010], entonces se le indicará que no es posible debido a que solo se puede añadir en fechas posteriores.

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

* **Visualizar diagrama general de máquinas, sistema con datos de la última semana:** Dado un administrador con usuario 'admin1' y un sistema con máquinas registradas y usadas en la última semana, cuando este intente visualizar el diagrama general de máquinas, entonces se le mostrará el nombre de estas, cuantas veces han sido utilizadas en la última semana.
* **Visualizar diagrama general de máquinas, sistema con datos del último mes:** Dado un administrador con usuario 'admin1' y un sistema con máquinas registradas y usadas en el último mes, cuando este intente visualizar el diagrama general de máquinas, entonces se le mostrará el nombre de estas, cuantas veces han sido utilizadas en el último mes.
* **Visualizar diagrama general de máquinas, sistema sin datos:** Dado un administrador con usuario 'admin1' y un sistema sin entrenamientos registrados, cuando este intente visualizar el diagrama general de máquinas, entonces se le indicará que no hay ninguna.
* **Visualizar diagrama general de máquinas, sistema sin equipamiento:** Dado un administrador con usuario 'admin1' y un sistema sin máquinas registradas, cuando este intente visualizar el diagrama general de máquinas, entonces se le indicará que no hay ninguna.
* **Visualizar diagrama general de máquinas, sistema sin ejercicio:** Dado un administrador con usuario 'admin1' y un sistema sin ejercicio, cuando este intente visualizar el diagrama general de máquinas, entonces se le indicará que no hay ninguna.
* **Visualizar diagrama general de máquinas, sistema sin línea de rutina:** Dado un administrador con usuario 'admin1' y un sistema sin líneas de rutinas, cuando este intente visualizar el diagrama general de máquinas, entonces se le indicará que no hay ninguna.
* **Visualizar diagrama general de máquinas, sistema sin rutinas:** Dado un administrador con usuario 'admin1' y un sistema sin rutinas, cuando este intente visualizar el diagrama general de máquinas, entonces se le indicará que no hay ninguna.
* **Visualizar diagrama general de máquinas, sistema con fechas de hace de dos meses:** Dado un administrador con usuario 'admin1' y un sistema con entrenamientos de hace dos meses, cuando este intente visualizar el diagrama general de máquinas, entonces se le indicará que no hay ninguna.
* **Visualizar diagrama general de máquinas, sistema con fechas de dos días después:** Dado un administrador con usuario 'admin1' y un sistema con entrenamientos de dos días después, cuando este intente visualizar el diagrama general de máquinas, entonces se le indicará que no hay ninguna.

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
<<<<<<< HEAD
* **Gestionar la rutina de un usuario sin que este tenga rutina:** Dado un entrenador que se dispone a gestionar la rutina de un usuario, cuando ese usuario no posee ninguna rutina. La vista de encargada la gestión de rutinas notificará al entrenador de que el usuario indicado que no posee ninguna rutina y le ofertará la opción de crearla. 
* **Gestionar la rutina de un usuario cuyo usuario no entrenas:** Dado un entrenador que se dispone a gestionar la rutina de un usuario, cuando no eres entrenador de ese usuario. La operación de gestión no se puede efectuar. El sistema limita a solo poder ver la rutina que tiene el usuario deseado, pero no es manipulable por alguien que no sea su entrenador.
* **Gestionar la rutina de un usuario borrando todos los ejercicios:** Dado un entrenador que se dispone a gestionar la rutina, en este caso modificar, de un usuario, borrando todos los ejercicios, cuando el usuario posee una rutina completa. La operación de gestión no se puede efectuar ya que debe existir un mínimo de 3 actividades por rutina.
* **Gestionar la rutina de un usuario:** Dado un entrenador que se dispone a gestionar la rutina de un usuario si incumplir ninguna regla de negocio, cuando este usuario ya posee una. La operación de gestión se efectúa correctamente y esta queda disponible tanto por el usuario para poder visualizar los cambios como para el entrenador poder modificarlos en un futuro.
* **Gestionar la rutina del entrenamiento finalizado de un usuario:** Dado un entrenador qie se dispone a gestionar la rutina de un usuario cuyo entrenamiento ha finalizado, la operación no se efectua ya que al finalizar (fecha final del entrenamiento anterior a la actual) un entrenamiento este entra en un estado inmutable. 
=======

(En cuanto a operaciones de gestión, nos referimos a POST: Editar y borrar)

* **Gestionar la rutina de un cliente con atributos sin conflictos:** Dado un entrenador que se dispone a gestionar la rutina de un cliente, cuando ese cliente no posee ninguna rutina. La vista de encargada la gestión de rutinas notificará al entrenador de que el cliente indicado que no posee ninguna rutina y le ofertará la opción de crearla. 

* **Gestionar la rutina de un cliente cuyo cliente no entrenas:** Dado un entrenador que se dispone a gestionar la rutina de un cliente, cuando no eres entrenador de ese cliente. La operación de gestión no se puede efectuar. El sistema limita a solo poder ver la de los clientes que entrena por ello solo puede manipular

* **Gestionar una rutina a un cliente, cuando el entrenamiento al que pertenece esa rutina ha finalizado:** Dado un entrenador que intenta gestionar una rutina, cuando un entrenamiento ha finalizado, la vista no le deja hacer redirección a los controles de gestión de rutas (añadir, editar y borrar). En el caso que intente acceder desde la URL, se le redirige a la vista de error.

* **Gestionar una rutina a un cliente con atributos con conflictos:** Dado un entrenador que intenta gestionar una rutina, en el caso de editar, cuando intenta editar la rutina y:

  - Deja los atributos nombre, descripción o repeticiones por semana vacíos
  - Repeticiones por semana tiene un valor inferior a 1

  La vista redirecciona al entrenador a la misma vista de editar indicándole el error/es y atributo/os que provocan el conflicto.

>>>>>>> release/1.0.0
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

* **Añadir entrenamiento a un cliente que él no entrena:** Dado un entrenador con nombre de usuario "trainer1", que quiere añadir un entrenamiento con nombre "Nuevo Entrenamiento", fecha de inicio igual a la actual, y fecha de fin dentro de 7 días desde la actual; a un cliente que él no entrena con nombre de usuario "client2", cuando intenta acceder a la vista de creación a través de la URL, entonces se le redireccionará a la vista de error.
* **Añadir entrenamiento a uno de sus clientes, con atributos sin errores:** Dado un entrenador con nombre de usuario "trainer1", que quiere añadir un entrenamiento con nombre "Nuevo Entrenamiento", fecha de inicio igual a la actual, y fecha de fin dentro de 7 días desde la actual; a uno de sus clientes con nombre de usuario "client1", cuando se intenta añadir el entrenamiento, entonces se añade el entrenamiento al cliente y se redirige a la vista de detalles del entrenamiento.
* **Añadir entrenamiento a uno de sus clientes, sin introducir atributos:** Dado un entrenador con nombre de usuario "trainer1", que quiere añadir un entrenamiento sin nombre, ni fecha de inicio, ni fecha de fin; a uno de sus clientes con nombre de usuario "client1", cuando se intenta añadir el entrenamiento, entonces no se añade el entrenamiento y se redirige a la vista de creación informando de que debe cumplimentar los campos.
* **Añadir entrenamiento a uno de sus clientes, fecha de inicio un día antes de la actual:** Dado un entrenador con nombre de usuario "trainer1", que quiere añadir un entrenamiento con nombre "Nuevo Entrenamiento", fecha de inicio 1 día antes de la actual, y fecha de fin dentro de 7 días desde la actual; a uno de sus clientes con nombre de usuario "client1", cuando se intenta añadir el entrenamiento, entonces no se añade el entrenamiento al cliente y se redirige a la vista de creación informando de que la fecha de inicio no debe estar en el pasado.
* **Añadir entrenamiento a uno de sus clientes, fecha de fin un día antes de la de inicio:** Dado un entrenador con nombre de usuario "trainer1", que quiere añadir un entrenamiento con nombre "Nuevo Entrenamiento", fecha de inicio igual a la actual, y fecha de fin 1 día antes de la actual; a uno de sus clientes con nombre de usuario "client1", cuando se intenta añadir el entrenamiento, entonces no se añade el entrenamiento al cliente y se redirige a la vista de creación informando de que la fecha de fin debe ser posterior a la de inicio.
* **Añadir entrenamiento a uno de sus clientes, fecha de fin igual a la de inicio:** Dado un entrenador con nombre de usuario "trainer1", que quiere añadir un entrenamiento con nombre "Nuevo Entrenamiento", fecha de inicio igual a la actual, y fecha de fin igual a la actual; a uno de sus clientes con nombre de usuario "client1", cuando se intenta añadir el entrenamiento, entonces no se añade el entrenamiento al cliente y se redirige a la vista de creación informando de que la fecha de fin debe ser posterior a la de inicio.
* **Añadir entrenamiento a uno de sus clientes, fecha de inicio coincidiendo con otro entrenamiento:** Dado un cliente con nombre de usuario "client1", con un entrenamiento que comienza en la fecha actual y finaliza dentro de 7 días desde la actual; y su entrenador con nombre de usuario "trainer1" que quiere añadir un entrenamiento con nombre "Nuevo Entrenamiento", fecha de inicio igual a la actual, y fecha de dentro de 8 días; a ese cliente, cuando se intenta añadir el entrenamiento, entonces no se añade el entrenamiento al cliente y se redirige a la vista de creación informando de que la fecha de inicio no puede coincidir con otro entrenamiento.
* **Añadir entrenamiento a uno de sus clientes, fecha de fin coincidiendo con otro entrenamiento:** Dado un cliente con nombre de usuario "client1", con un entrenamiento que comienza dentro de 1 día y finaliza dentro de 7 desde la actual; y su entrenador con nombre de usuario "trainer1" que quiere añadir un entrenamiento con nombre "Nuevo Entrenamiento", fecha de inicio igual a la actual, y fecha de fin dentro de 7 días desde la actual; a ese cliente, cuando se intenta añadir el entrenamiento, entonces no se añade el entrenamiento al cliente y se redirige a la vista de creación informando de que la fecha de fin no puede coincidir con otro entrenamiento.
* **Añadir entrenamiento a uno de sus clientes, entrenamiento incluyendo otro:** Dado un cliente con nombre de usuario "client1", con un entrenamiento que se inicia dentro de 1 día desde la actual y finaliza dentro de 7 días desde la actual; y su entrenador con nombre de usuario "trainer1" que quiere añadir un entrenamiento con nombre "Nuevo Entrenamiento", fecha de inicio igual a la actual, y fecha de fin dentro de 8 días; a ese cliente, cuando se intenta añadir el entrenamiento, entonces no se añade el entrenamiento al cliente y se redirige a la vista de creación informando de que el entrenamiento no puede incluir a otro.

---

#### HU13 - Gestionar el entrenamiento de nuestros clientes

Como entrenador quiero llevar el control de los entrenamientos de mis clientes conociendo el estado de estos para poder modificar los periodos en los que transcurren y así adaptarlos de la mejor forma a los clientes.

#### Escenarios:

* **Gestionar entrenamientos de sus clientes:** Dado un entrenador con nombre de usuario "trainer1", que se dispone a gestionar los entrenamientos de sus clientes, cuando accede a la vista de gestión de entrenamientos, entonces se le listarán sus clientes junto con sus entrenamientos indicando de estos: nombre y su estado, es decir, si está en curso o finalizado.
* **Gestionar entrenamientos de clientes que no entrena:** Dado un entrenador con nombre de usuario "trainer1", que se dispone a gestionar los entrenamientos de clientes que no entrena, cuando intenta acceder a la vista de gestión de entrenamientos de otro entrenador con nombre de usuario "trainer2" a través de la URL, entonces se le redireccionará a la vista de error.
* **Gestionar entrenamiento de uno de sus clientes:** Dado un entrenador con nombre de usuario "trainer1", que se dispone a gestionar el entrenamiento de uno de sus clientes con nombre de usuario "client1", cuando accede a la vista de gestión del entrenamiento, entonces se le mostrará la siguiente información del entrenamiento: nombre, fecha inicio, fecha fin, un listado de sus rutinas, su dieta y enlaces para redireccionar a la edición y borrado de la información general del entrenamiento. 
* **Gestionar entrenamiento de un cliente que no entrena:** Dado un entrenador con nombre de usuario "trainer1", que se dispone a gestionar el entrenamiento de un cliente que no entrena con nombre de usuario "client2", cuando accede a la vista de gestión del entrenamiento a través de la URL, entonces se le redireccionará a la vista de error.
* **Gestionar entrenamiento en curso, nombre vacío:** Dado un entrenador con nombre de usuario "trainer1", que se dispone a actualizar el entrenamiento con nombre "Entrenamiento 1", fecha de inicio igual a la actual y fecha de fin dentro de 7 días desde la actual; de uno de sus clientes con nombre de usuario "client1", cuando intenta actualizar el nombre y la fecha de fin a vacío, entonces no se actualizará el entrenamiento y se le devolverá a la vista de actualización informándole de que esos campos no pueden estar vacíos.
* **Gestionar entrenamiento en curso, fecha fin anterior a la de inicio:** Dado un entrenador con nombre de usuario "trainer1", que se dispone a actualizar el entrenamiento con nombre "Entrenamiento 1", fecha de inicio igual a la actual y fecha de fin dentro de 7 días desde la actual; de uno de sus clientes con nombre de usuario "client1", cuando intenta actualizar la fecha de fin a la de ayer, entonces no se actualizará el entrenamiento y se le devolverá a la vista de actualización informándole de que la fecha de fin no puede ser anterior a la de inicio.
* **Gestionar entrenamiento en curso, fecha fin coincide con otro entrenamiento:** Dado un cliente con nombre de usuario "client1", con un entrenamiento que se inicia dentro de 8 días desde la actual y finaliza dentro de 15 días desde la actual; y un entrenador con nombre de usuario "trainer1" que se dispone a actualizar el entrenamiento con nombre "Entrenamiento 1", fecha de inicio igual a la actual y fecha de fin dentro de 7 días desde la actual; de ese cliente, cuando intenta actualizar la fecha de fin a una dentro de 8 días desde la actual, entonces no se actualizará el entrenamiento y se le devolverá a la vista de actualización informándole de que la fecha de fin no puede coincidir con otro entrenamiento.
* **Gestionar entrenamiento en curso, entrenamiento incluye otro:** Dado un cliente con nombre de usuario "client1", con un entrenamiento que se inicia dentro de 8 días desde la actual y finaliza dentro de 15 días desde la actual; y un entrenador con nombre de usuario "trainer1" que se dispone a actualizar el entrenamiento con nombre "Entrenamiento 1", fecha de inicio igual a la actual y fecha de fin dentro de 7 días desde la actual; de ese cliente, cuando intenta actualizar la fecha de fin a una dentro de 16 días desde la actual, entonces no se actualizará el entrenamiento y se le devolverá a la vista de actualización informándole de que el entrenamiento no puede incluir otro.
* **Gestionar entrenamiento en curso, fecha fin anterior a la actual:** Dado un entrenador con nombre de usuario "trainer1", que se dispone a actualizar el entrenamiento con nombre "Entrenamiento 1", fecha de inicio 7 días antes de la actual y fecha de fin dentro de 7 días desde la actual; de uno de sus clientes con nombre de usuario "client1", cuando intenta actualizar la fecha de fin a la de ayer, entonces no se actualizará el entrenamiento y se le devolverá a la vista de actualización informándole de que no se puede finalizar el entrenamiento en el pasado

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
  Actualizar posee exactamente las mismas restricciones que crear.
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

* **Cliente con entrenamiento asignado:** Dado un cliente con un entrenamiento asignado se le asignará la dieta que mejor complemente su entrenamiento.
* **Cliente sin entrenamiento asignado:** Dado un cliente sin un entrenamiento asignado no se le permitirá acceder a esta función y se le pedirá que consiga un entrenamiento antes de acceder.

---

#### HU17 - Copiar entrenamiento de otro cliente

Como cliente quiero seguir a otros clientes para copiar su entrenamiento.

#### Escenarios:

* **Clientes con perfil público:** Dado un cliente con perfil público se permitirá que otros clientes le sigan.
* **Clientes con perfil privado:** Dado un cliente con perfil privado no se permitirá que otros clientes le sigan.

---

#### HU18- Dashboard Cliente

Como cliente quiero poder tener un dashboard donde poder ver el Historial de actividades, Calorías quemadas y otros datos relacionados con mi entrenamiento.

#### Escenarios:

* **Ver dashboard con datos:** Dado un cliente con usuario 'client1', y un sistema con ejercicios registrados para este cliente, al pulsar el botón de "Dashboard", aparecerá las kcal gastadas este mes y un histórico, además de dos gráficas (mensual e histórico).
* **Ver dashboard sin datos:** Dado un cliente con usuario 'client3', y un sistema sin entrenamientos registrados para este cliente al pulsar el botón de "Dashboard", aparecerá un mensaje de que no hay datos de ejercicios registrados.
* **Ver dashboard de otro cliente:** Dado un cliente con usuario 'client3', cambiar la url para ver su dashboard para ver el de 'client1', se redirigirá a una vista de excepción.
* **Ver dashboard sin datos de este mes:** Dado un cliente con usuario 'client2', y un sistema sin ejercicios registrados este mes para este cliente, al pulsar el botón de "Dashboard", aparecerá un mensaje de que no hay datos de ejercicios registrados este mes y justo abajo las gráficas y kcal del histórico.
* **Ver dashboard sin ejercicios:** Dado un cliente con usuario 'client2' y un sistema en el que no tenga ejercicios registrados para este cliente, al pulsar el botón de "Dashboard", aparecerá un mensaje de que no hay datos de ejercicios registrados.
* **Ver dashboard sin líneas de rutinas:** Dado un cliente con usuario 'client2' y un sistema en el que no tenga líneas de rutina registradas para este cliente, al pulsar el botón de "Dashboard", aparecerá un mensaje de que no hay datos de ejercicios registrados.
* **Ver dashboard sin rutinas:** Dado un cliente con usuario 'client2' y un sistema en el que no tenga rutinas registradas para este cliente, al pulsar el botón de "Dashboard", aparecerá un mensaje de que no hay datos de ejercicios registrados.

---

#### HU19 - Personalizar mi entrenamiento

Como cliente quiero poder personalizar mi entrenamiento para actualizarlo o personalizarlo a mi gusto.

#### Escenarios:

* **Con permiso para edición:** Dado un cliente con un entrenamiento que pueda ser editado se le permitirá actualizarlo a su gusto.
* **Sin permiso para edición:** Dado un cliente con un entrenamiento que  no puede ser editado no se le permitirá actualizarlo.

---

#### HU20 - Compartir progreso

Como cliente quiero poder compartir mi progreso con otros clientes, para poder poder ver su avance y poder compararlos con mis resultados.

#### Escenarios:

* **Compartir progreso sin foto pero con comentario:** Dado un cliente, escribe un mensaje declarando su progreso, le da a publicar y se publica en su perfil, publicándose también en el perfil de los clientes que tenga agregados como amigos.
* **Compartir progreso con foto pero sin comentario:** Dado un cliente, adjunta una foto, le da a enviar y se publica en su perfil, publicándose también en el perfil de los clientes que tenga agregados como amigos.
* **Compartir progreso con foto y con comentario:** Dado un cliente, escribe un mensaje declarando su progreso, adjunta una foto, le da a publicar y se publica en su perfil, publicándose también en el perfil de los clientes que tenga agregados como amigos.
* **Compartir progreso sin foto ni comentario:** Dado un cliente, al darle a publicar, si no ha adjuntado foto ni ha escrito ningún comentario le saltará un aviso diciendo "si desea publicar debe adjuntar una foto o escribir un comentario".

---

#### HU21 - Participar en  los Retos

Como cliente quiero poder participar en los distintos retos propuestos por el gimnasio, para poder ganar puntos para mi gremio y para mi mismo y estar lo mas alto posible en la clasificación.

#### Escenarios

* **Listar los retos en los que aún no me he inscrito:** Dado un cliente[client1], pinchará en la sección "new challenges", verá un listado con los diferentes retos propuestos y seleccionará el que desee, por ejemplo, el challenge descrito en la HU01, verá los requisitos que se piden para completarlo y si desea intentarlo pinchará en el botón "inscribe me", añadiéndosele dicho reto a la sección "my challenges" pero con un estado "participating" hasta que adjunte una foto[url] demostrando que lo ha completado y pasará al estado "submitted".
  **Listar los retos en los que participa:** Dado un cliente[client1],  pinchará en la sección "my challenges", verá un listado con los diferentes retos en los que se ha inscrito.
* **Participar en un reto cuando no hay retos:** Dado un cliente[client1], , pinchará en la sección "new challenges", si dicho cliente se ha inscrito en todos los retos disponibles, no le saldrá ningún reto en dicha lista.
* **Pedir confirmar un reto que ha completado:** Dado un cliente[client1], , pinchará en la sección "my challenges", si dicho cliente ha completado algún reto, pinchará sobre el[Challenge descrito anteriormente], rellenará la url de la foto de prueba[https://test.com], y clicará en enviar. El administrador[admin1] podrá ver esos retos enviados, y decidirá si han sido completados o fallados en base a la url proporcionada.
* **Listar los retos en los que participo y la fecha límite del reto pasa antes de que el cliente lo envíe:** Dado un cliente[client1],  y un reto con fecha de finalización [12/12/2018], sin que haya sido enviado, se pondrá automáticamente en fallado.

---

#### HU22 - Clasificación de los Retos

Como cliente quiero poder ver la clasificación de mis retos, para poder ver en que posición me encuentro y que debo hacer para poder superar y subir al top.

#### Escenarios

* **Ver un la clasificación semanal e histórica con datos:** Dado un cliente con usuario 'client3', y un sistema con retos completados la última semana, al pulsar el botón de "Clasificación", aparecerá un listado de los retos completados, viendo también una clasificación semanal y global.
* **Ver un la clasificación histórica sin haber completado retos:** Dado un cliente con usuario 'client1', y un sistema sin retos completados para este cliente, al pulsar el botón de "Clasificación", aparecerá una clasificación global.
* **Ver un la clasificación de otro cliente:** Dado un cliente con usuario 'client1', intentar entrar en la clasificación del cliente con usuario 'client3' cambiando la url, dando como resultado una redirección a la página de excepción.
* **Ver un la clasificación sin retos:** Dado un cliente con usuario 'client3' y un sistema sin retos completados para ningún cliente, al pulsar el botón de "Clasificación", aparecerá un mensaje de que no hay retos completados.

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
| HU4                 | 1      | P2              | Sin Comenzar |
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
| HU2                 | 2      | P3              | Sin Comenzar |
| HU6                 | 2      | P2              | Sin Comenzar |
| HU16                | 2      | P3              | Sin Comenzar |
| HU17                | 2      | P2              | Sin Comenzar |
| HU19                | 2      | P1              | Sin Comenzar |
| HU7                 | 3      | P3              | Sin Comenzar |
| HU20                | 3      | P2              | Sin Comenzar |
| HU23                | 3      | P1              | Sin Comenzar |
