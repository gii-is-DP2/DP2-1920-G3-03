# YogoGym Application 

## Descripción de la aplicación
La aplicación web a desarrollar tiene como nombre YogoGym. El objetivo fundamental es conseguir modernizar la forma de realizar los entrenamientos y el seguimiento para un local/cadena de esta característica. Con ella se implementarán cuatro roles fundamentales: administrador, monitor de clase, entrenador y cliente. Cada uno de ellos tendrá unas funcionalidades específicas, como por ejemplo los planes de entrenamientos que realizan los entrenadores a sus clientes, pudiendo estos modificarlos para adaptarlos a su necesidad. Para ayudar a administrar el gimnasio, se planea crear gráfica/tableros de control, que ayude a al gerente a saber cuales son las máquinas con más usos, los días en los que más gente acude al entrenamiento, etc. facilitando la gestión del local. También para los clientes se implementará funciones para que, alcanzando unos retos, para los cuales necesitará acreditarlo con una prueba, este reciba algún beneficio.

## Entidades
* Ejercicio
* Oraciones
* Rutinas
* Entrenamiento
* Maquinas
* Actividades
* Dieta
* Productos
* Foros
* Clasificación
* Retos
* Mensajes


## Historias de Usuario
### Administrador
---
#### Proponer retos semanales y mensuales 
Como administrador quiero añadir retos semanales y mensuales al gimnasio para clientes individuales y gremios 
para que estos puedan competir por lograrlos y obtengan beneficios en el gimnasio si lo hacen.
#### Escenarios:
* **Añadir reto semanal/mensual a semana/mes sin retos:** Dado un administrador y una semana/mes sin retos, cuando este intente añadir uno aportando la información necesaria, entonces el reto se añadirá a la semana/mes.
* **Añadir reto semanal/mensual ya existente:** Dado un administrador y una semana/mes con retos pero no completa, cuando este intente añadir uno con la información de otro ya existente, entonces se le indicará que esto no es posible debido a que ya existe.
* **Añadir reto semanal/mensual a semana/mes con 3 retos:** Dado un administrador y una semana/mes con 3 retos, cuando este intente añadir uno, entonces se le indicará que no es posible debido a que se ha llegado al máximo de retos.
* **Añadir reto semanal/mensual a semana/mes actual o anterior:** Dado un administrador, cuando este intente añadir un reto a una semana/mes actual o anterior, entonces se le indicará que no es posible debido a que solo se puede añadir a semanas/meses posteriores.
---
#### Control de los retos
Como administrador quiero visualizar diagramas que muestren, clasificados por individuales o gremios y semanales o mensuales, el éxito en los retos para poder adecuarlos en temática y dificultad correctamente.
#### Escenarios: 
* **Visualizar diagrama individual de periodo con retos:** Dado un administrador y un periodo con retos individuales registrados, cuando este intente visualizar su diagrama individual, entonces se le mostrarán los nombres de estos y el número y porcentaje de clientes que los han logrado.
* **Visualizar diagrama gremial de periodo con retos:** Dado un administrador y un periodo con retos gremiales registrados, cuando este intente visualizar su diagrama, entonces se le mostrarán los nombres de estos, el número y porcentaje de gremios que los han logrado y los gremios específicos que lo lograron.
* **Visualizar diagrama individual/gremial de periodo sin retos:** Dado un administrador y un periodo sin retos, cuando este intente visualizar su diagrama, entonces se le indicará que no hay ninguno y se le ofrecerá añadir uno si el periodo es posterior al actual.
---
#### Control de los gremios
Como administrador quiero visualizar diagramas que muestren el número de integrantes de cada gremio así como la evolución de este número en el tiempo para poder conocer las tendencias y adecuar los servicios del gimnasio a estas o buscar maneras de fomentar los gremios menos populares.
#### Escenarios: 
* **Visualizar diagrama general de gremios, sistema con datos:** Dado un administrador y un sistema con gremios registrados, cuando este intente visualizar el diagrama general de gremios, entonces se le mostrará el nombre de estos, cuantos integrantes tiene, el porcentaje que representa de clientes en gremios y la posibilidad de ver su evolución en el tiempo.
* **Visualizar diagrama general de gremios, sistema sin datos:** Dado un administrador y un sistema sin gremios registrados, cuando este intente visualizar el diagrama general de gremios, entonces se le indicará que no hay ninguno y se le ofrecerá la posibilidad de añadir uno.
* **Visualizar diagrama específico de gremio:** Dado un administrador y un gremio registrado, cuando este intente visualizar su diagrama específico, entonces se le mostrará un línea temporal que muestre la evolución del número de integrantes mensualmente.
---
#### Control de las máquinas
Como administrador quiero visualizar diagrama que me muestren cuanto son utilizadas las máquinas de las que dispone el gimnasio para saber cuales requieren un mayor mantenimiento o si es necesario adquirir más de ellas o desecharlas.
#### Escenarios:
* **Visualizar diagrama general de máquinas, sistema con datos:** Dado un administrador y un sistema con máquinas registradas, cuando este intente visualizar el diagrama general de máquinas, entonces se le mostrará el nombre de estas, cuantas veces han sido utilizadas y la posibilidad de ver su evolución en el tiempo.
* **Visualizar diagrama general de máquinas, sistema sin datos:** Dado un administrador y un sistema sin máquinas registradas, cuando este intente visualizar el diagrama general de máquinas, entonces se le indicará que no hay ninguna y se le ofrecerá la posibilidad de añadir una.
* **Visualizar diagrama específico de máquina:** Dado un administrador y una máquina registrada, cuando este intente visualizar su diagrama específico, entonces se le mostrará una línea temporal que muestre la evolución del número de usos mensualmente.
---
#### Mostrar oraciones motivacionales diariamente
Como administrador quiero que se muestren oraciones motivacionales diariamente para intentar motivar a los clientes y mejorar su estado de ánimo.
#### Escenarios:
* **Mostrar oración diaria, conexión con API estable:** Dado un usuario con un rol cualquiera y una conexión con la API de oraciones estable, cuando se acceda la página principal de la aplicación, entonces se mostrará la oración del día.
* **Mostrar oración diaria, conexión con API no estable:** Dado un usuario con un rol cualquiera y una conexión con la API de oraciones no estable, cuando se acceda la página principal de la aplicación, entonces se mostrará una oración motivacional predefinida.
---
#### Crear y asignar clases
Como administrador quiero poder crear clases y asignarla a monitores, para poder administrar los horarios, que los clientes puedan apuntarse a ellas y los monitores sepan que clases deben impartir.
#### Escenarios:
* **Crear una clase en un horario vacío:** Dado un administrador, este crea una clase asignandole un título, tipo, monitor asignado, horario, la sala y el equipamiento del que dispone.
* **Crear una clase en una sala y horario ya ocupado:** Dado un administrador, el sistema deberá avisarle de que esa sala con ese horario ya está ocupado por otra clase.
* **Crear una clase y asignarla a un monitor ya ocupado:** Dado un administrador, el sistema deberá avisarle de que ese monitor ya está ocupado en ese horario en cualquiera de las salas.

---
### Monitor de clases
---
#### Ver horario clases que imparto
Como monitor quiero poder visualizar el horario de las clases que imparto, para poder saber en que sala y a que hora debo estar para impartirla.
#### Escenarios:
* **Mostrar el horario con clases existentes:** Dado un monitor y clases asignadas a él, mostrarle el horario de las clases que imparte, visualizando: cada clase con su día y hora y el aula a usar.
* **Mostrar el horario sin clases existentes:** Dado un monitor sin clases asignadas, mostrarle un aviso de que no dispone de horario porque no tiene clases asignadas.
---
#### Ver equipo disponible en mi clase
Como monitor quiero ver el equipo disponible en cada aula, para poder saber de que dispondré en cada clase y poder adecuarla a ello.
#### Escenarios:
* **Mostrar el equipo disponible en un aula con equipo:** Dado un monitor y una clase asignada a él, mostrar el equipo del que dispone el aula.
* **Mostrar el equipo disponible en un aula sin equipo:** Dado un monitor y una clase asignada a él, avisar de que el áula no dispone de equipo.
* **Unir esta con mostrar nº de asistentes**
---
#### Mostrar el nº de asistentes a mis clases
Como monitor quiero que se me muestren el número de clientes que se han apuntado a cada una de mis clases para poder decidir no ir a la clase si el número es 0, o adaptarla al total de participantes.
#### Escenarios:
* **Mostrar el nº de asistentes con asistentes apuntados:** Dado un monitor con clases registradas, cuando acceda a cada una de las clases, se mostrará el nº de asistentes y el aforo máximo.
* **Mostrar el nº de asistentes sin asistentes apuntados:** Dado un monitor con clases registradas, se mostrará con un aviso aquellas clases sin alumnos asistentes.
---
#### Limitar nº de asistentes a mis clases
Como monitor quiero poder limitar el número de asistentes a cada una de las clases que impartiré, para que estas se desarrollen sin problema y con un aforo adecuado al tipo de clase y sala.
#### Escenarios:
* **Limitar a mas de uno el nº máximo de asistentes:** Dado un monitor con clases registradas, establece un número mayor que 0 de límite y este se registra correctamente.
* **Limitar a 0 el nº máximo de asistentes:** Dado un monitor con clases registradas, se le indicará que el aforo máximo debe ser mayor a 0 para que puedan asistir clientes.
---
### Entrenador
---
#### Añadir rutinas a nuestros clientes
Como entrenador quiero realizar rutinas a nuestros clientes para facilitar el desarrollo físico del cliente
#### Escenarios:
* **Añadir rutina a un usuario, ya existe una rutina:** Dado un usuario que ya posee una rutina, cuando como entrenador intento asignarle una rutina. La operación de añadir la rutina no se efectúa resultando en el sistema informando al entrenador que el usuario ya posee una rutina.
* **Añadir una rutina a un usuario, se añade vacía:** Dado un entrenador que añade una rutina a un usuario, cuando se intenta añadir vacía. La operación de añadir rutina no se efectúa resultando en el sistema informando al entrenador que debe como mínimo introducir un ejercicio.
* **Añadir una rutina a un usuario sin rutina:** Dado un entrenador que añade una rutina a un usuario, cuando este usuario no tiene ninguna rutina. La operación de añadir rutina se efectúa correctamente y esta queda disponible tanto por el usuario para poder visualizarla como para el entrenador poder modificarla.
* **Añadir una rutina a un usuario el cual no entrenas:** Dado un entrenador que añade una rutina a un usuario, cuando no entrenas a este usuario. La operación de añadir rutina no se realiza. La vista muestra una excepción al entrenador de que no puede añadir una rutina a un usuario el cual no entrena.
#### Gestionar la rutina de nuestros clientes en función de la intensidad, duración y cantidad de ejercicios
Como entrenador quiero llevar el control de las rutinas en función de intensidad, duración y cantidad de ejercicios para poder cambiar la rutina adaptándola de la mejor forma al cliente.
#### Escenarios:
* **Gestionar la rutina de un usuario sin que este tenga rutina:** Dado un entrenador que se dispone a gestionar la rutina de un usuario, cuando ese usuario no posee ninguna rutina. La vista de encargada la gestión de rutinas notificará al entrenador de que el usuario indicado que no posee ninguna rutina y le ofertará la opción de crearla. 
* **Gestionar la rutina de un usuario cuyo usuario no entrenas:** Dado un entrenador que se dispone a gestionar la rutina de un usuario, cuando no eres entrenador de ese usuario. La operación de gestión no se puede efectuar. El sistema limita a solo poder ver la rutina que tiene el usuario deseado, pero no es manipulable por alguien que no sea su entrenador.
* **Gestionar la rutina de un usuario borrando todos los ejercicios:** Dado un entrenador que se dispone a gestionar la rutina, en este caso modificar, de un usuario, borrando todos los ejercicios, cuando el usuario posee una rutina completa. La operación de gestión no se puede efectuar ya que debe existir un mínimo de 3 actividades por rutina.
* **Gestionar la rutina de un usuario:** Dado un entrenador que se dispone a gestionar la rutina de un usuario si incumplir ninguna regla de negocio, cuando este usuario ya posee una. La operación de gestión se efectúa correctamente y esta queda disponible tanto por el usuario para poder visualizar los cambios como para el entrenador poder modificarlos en un futuro.
#### Responder a las dudas presentadas por los clientes
Como entrenador quiero responder a las dudas de los clientes para ayudarlo con todo lo relacionado con su entrenamiento y pueda aprender.
#### Escenarios:
* **Responder una duda de un cliente relacionada con dieta:** Dado un entrenador que se dispone a responder la duda de un cliente, cuando ese cliente tiene un duda relacionada con la dieta. El entrenador le responde a través del chat designado respondiendo a la duda especificada aportando el material necesario.
* **Responder una duda de un cliente relacionada con rutinas:** Dado un entrenador que se dispone a responder la duda de un cliente, cuando ese cliente tiene un duda relacionada con las rutinas. El entrenador le responde a través del chat designado respondiendo a la duda especificada aportando el material necesario.
* **Responder una duda de un cliente que no esta relacionada con preguntas del ámbito de un gimnasio:** Dado un entrenador que se dispone a responder la duda de un cliente, cuando ese cliente no posee una duda relacionada con el ámbito del gimnasio. El entrenador tiene la opción de finalizar la conversación incluso de reportar al cliente. El sistema guardará esa notificación y a cierto número de reportes el cliente no podrá preguntar por  un determinado tiempo.
#### Añadir dietas alimenticias a nuestros clientes
Como entrenador quiero realizar dietas a los clientes para mejorar su evolución física, haciéndola más eficiente.
#### Escenarios:
* **Añadir una dieta a un cliente que no posee una dieta:** Dado un entrenador que se dispone a añadir la dieta a un usuario, cuando ese usuario no posee ninguna dieta. La operación de añadir dieta se efectúa correctamente y esta queda disponible tanto por el usuario para poder visualizarla como para el entrenador poder modificarla.
* **Añadir dieta a un usuario, ya existe una dieta:** Dado un usuario que ya posee una dieta, cuando como entrenador intento asignarle una dieta. La operación de añadir la dieta no se efectúa resultando en el sistema informando al entrenador que el usuario ya posee una dieta.
* **Añadir una dieta a un usuario, se añade vacía:** Dado un entrenador que añade una dieta a un usuario, cuando se intenta añadir vacía. La operación de añadir dieta no se efectúa resultando en el sistema informando al entrenador que debe como mínimo introducir 3 comidas, una para el desayuno, otra para la comida y otro para la cena.
* **Añadir una dieta a un usuario el cual no entrenas:** Dado un entrenador que añade una dieta a un usuario, cuando no entrenas a este usuario. La operación de añadir dieta no se realiza. La vista muestra una excepción al entrenador de que no puede añadir una dieta a un usuario el cual no entrena.
#### Gestionar la dieta alimenticia de nuestros clientes en función de kilocalorías, nutrientes esenciales, peso y porcentaje de grasa
Como entrenador quiero llevar el control de las dietas de los clientes en función de cantidad de kilocalorías, nutrientes esenciales, peso y porcentaje de grasa para poder cambiar las cosas necesarias con el fin de mejorar le evolución del cliente
#### Escenarios:
* **Gestionar la dieta de un usuario sin que este tenga rutina:** Dado un entrenador que se dispone a gestionar la dieta de un usuario, cuando ese usuario no posee ninguna dieta. La vista de encargada la gestión de dietas notificará al entrenador de que el usuario indicado que no posee ninguna dieta y le ofertará la opción de crearla. 
* **Gestionar la dieta de un usuario cuyo usuario no entrenas:** Dado un entrenador que se dispone a gestionar la dieta de un usuario, cuando no eres entrenador de ese usuario. La operación de gestión no se puede efectuar. El sistema limita a solo poder ver la dieta que tiene el usuario deseado, pero no es manipulable por alguien que no sea su entrenador.
* **Gestionar la dieta de un usuario borrando todos los ejercicios:** Dado un entrenador que se dispone a gestionar la dieta, en este caso modificar, de un usuario, borrando todas las comidas, cuando el usuario posee una dieta completa. La operación de gestión no se puede efectuar ya que debe existir un mínimo de 3 comidas por dieta.
* **Gestionar la dieta de un usuario:** Dado un entrenador que se dispone a gestionar la dieta de un usuario sin incumplir ninguna regla de negocio, cuando este usuario ya posee una. La operación de gestión se efectúa correctamente y esta queda disponible tanto por el usuario para poder visualizar los cambios como para el entrenador poder modificarlos en un futuro.

#### Lo hizo Xisko ####
#### Mostrar clientes referidos
Como monitor quiero poder ver los clientes que están relacionados conmigo para poder llevar un mejor seguimiento de mis referidos.
#### Escenarios:
* **Mostrar clientes cuando no tienes ninguno referido:** Dado un entrenador sin clientes referidos muestra los clientes sin entrenadores para ayudar a captar nuevos clientes.
* **Mostrar clientes cuando tienes referidos:** Dado un entrenador con clientes referidos muestra los clientes y los enlaces a su perfil.
---
#### Realizar rutinas a los clientes
Como monitor quiero poder quiero poder realizar rutinas a los clientes para poder ayudarles durante su entrenamiento.
#### Escenarios:
* **Realizar rutinas a los clientes:** Dado un entrenador sin clientes referidos muestra los clientes sin entrenadores para ayudar a captar nuevos clientes.
* **Realizar rutinas a cliente referido:** Dado un entrenador con un cliente referido se le crea una rutina desde 0 o se le asocia una ya existente a dicho cliente.
* **Realizar rutinas a cliente no referido:** Dado un entrenador con un cliente no referido, se le crea una rutina desde 0 o se le asocia una ya existente a dicho cliente y ese cliente pasa a ser un cliente asociado al entrenador.
---
#### Responder las dudas de los clientes
Como monitor quiero poder responder las dudas de los clientes para ayudarles a realizar correctamente su entrenamiento, ejercicios, dietas, etc.
#### Escenarios:
* **Responder con lenguaje adecuado:** Dado un entrenador y un mensaje para su cliente comprueba que el mensaje no contenga lenguaje soez para evitar la falta de profesionalidad e impide su envío hasta su correción.
* **Responder con lenguaje adecuado:** Dado un entrenador y un mensaje para su cliente comprueba que el mensaje no contenga lenguaje soez para evitar la falta de profesionalidad y procede a su envío.
---

#### Realizar las dietas de los clientes
Como monitor quiero poder realizar las dietas de los clientes para ayudarles a complementar su entrenamiento.
#### Escenarios:
* **Usuario sin dieta asignada:** Dado un entrenador y cliente asociado sin dieta asignada se le crea o asigna una dieta previamente creada.
* **Usuario sin dieta asignada:** Dado un entrenador y cliente asociado con dieta asignada no se le puede asociar otra dieta hasta que no se acabe o elimine la anterior.
---
#### Vender productor relacionados con la dieta de los clientes
Como monitor quiero poder conseguir dinero referido a la venta de productos relacionados con la dieta del cliente para conseguir ingresos extras.
#### Escenarios:
* **Promocionar productos relacionados:** Dado un entrenador y cliente asociado con dieta asignada se permite asociar links y publicidad relacionada con los productos de su dieta.
* **Promocionar productos no relacionados:** Dado un entrenador y cliente asociado con dieta asignada no se permite asociar links y publicidad relacionada con productos que no estén relacionados con su dieta.

#### Acaba lo de xisko
---
### Cliente (TODO)
---
#### Apuntarme a Clase
Como cliente quiero poder apuntarme a las clases que me interesen, para poder asistir a ellas y saber el horario en las que se imparten.
#### Escenarios:
* **Apuntarse a una clase con huecos disponibles:** Dado un cliente y una clase sin el aforo completo, el cliente le da a inscribirse, se registra, y aumenta en uno el número de asistentes a esa clase.
* **Apuntarse a una clase sin huecos disponibles:** Dado un cliente y una clase sin huecos disponibles, el cliente le da a inscribirse, y salta un aviso de que el aforo ya está completo.
* **Apuntarse a una clase en el mismo horario que otra:** Dado un cliente y una clase con horario igual a otra que ya esté inscrito, le da a inscribirse y le salta un aviso diciendo que se solapa el horario con otra clase.
---
#### Consultar todos los ejercicios disponibles
Como cliente quiero consultar todos los ejercicios disponible para poder coger ideas y realizar mis propias rutinas y entrenamientos.
#### Escenarios:
* **Consultar ejercicios disponibles:** Dado un cliente y un ejercicio publicado o disponible le muestra la información del ejericio.
* **Consultar ejercicios no disponibles:** Dado un cliente y un ejercicio no publicado o no disponible salta un aviso de que el ejercicio no está disponible.
---
#### Consultar cualquier tipo de duda relacionada con mi entrenamiento
Como cliente quiero coonsultar cualquier tipo de duda relacionada con mi entrenamiento para poder realizarlo correctamente.
#### Escenarios:
* **Dudas relacionadas:** Dado un cliente y un mensaje que incluya una duda que se considere relacionada con su entrenamiento o algún ejercicio se le permitirá publicarla para conseguir ayuda de alguien.
* **Dudas no relacionadas:** Dado un cliente y un mensaje que incluya una duda que no se considere relacionada con su entrenamiento o algún ejercicio se eliminará el mensaje y se le informará sobre las razones. 

---
#### Acceder a la tienda de productos para poder realizar de forma eficiente mi dieta
Como cliente quiero acceder a la tienda de productos para poder realizar de forma eficiente mi dieta.
#### Escenarios:
* **Cliente con dieta asignada:** Dado un cliente con una dieta asignada se le permitirá acceder a esta función.
* **Cliente sin dieta asignada:** Dado un cliente sin una dieta asignada no se le permitirá acceder a esta función y se le pedirá que consiga una dieta antes de acceder.

---
#### Dietas personalizadas según mi tipo de entrenamiento
Como cliente quiero poder tener dietas personalizadas según mi tipo de entrenamiento para maximizar el resultado del ejercicio.
#### Escenarios:
* **Cliente con entrenamiento asignado:** Dado un cliente con un entrenamiento asignado se le asignará la dieta que mejor complemente su entrenamiento.
* **Cliente sin entrenamiento asignado:** Dado un cliente sin un entrenamiento asignado no se le permitirá acceder a esta función y se le pedirá que consiga un entrenamiento antes de acceder.

---
#### Seguir a otros usuarios para copiar su entrenamiento
Como cliente quiero seguir a otros usuarios para copiar su entrenamiento.
#### Escenarios:
* **Usuarios con perfil público:** Dado un cliente con perfil público se permitirá que otros clientes le sigan.
* **Usuarios con perfil privado:** Dado un cliente con perfil privado no se permitirá que otros clientes le sigan.

---
---
#### Dashboard Cliente
Como cliente quiero poder tener un dashboard donde poder ver el Historial de actividades, Calorías quemadas y otros datos relacionados con mi entrenamiento.
#### Escenarios:
* **Suficientes datos que mostrar:** Dado un cliente con actividades realizadas se le mostrarán datos sobre su historial.
* **Sin datos para mostrar:** Dado un cliente sin actividades realizadas se le mostrarán posibles actividades a realizar para empezar a hacer ejercicio.
---
---
#### Personalizar mi entrenamiento: Crud ejercicio, entrenamiento
Como cliente quiero poder personalizar mi entrenamiento para actualizarlo o personalizarlo a mi gusto.
#### Escenarios:
* **Con permiso para edición:** Dado un cliente con un entrenamiento que pueda ser editado se le permitirá actualizarlo a su gusto.
* **Sin permiso para edición:** Dado un cliente con un entrenamiento que  no puede ser editado no se le permitirá actualizarlo.

---
## Planificación y asignación de las historias de usuario (TODO)
| Historia de Usuario | Sprint | Pareja asignada |
| --- | --- | --- |
|   |   |   | 
