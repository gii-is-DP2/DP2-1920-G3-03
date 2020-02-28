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
### Monitor de clases
#### Conocer el nº de asistentes a mis clases
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
### Entrenador (TODO)
#### Nombre
Descripción
#### Escenarios:
* 
---
### Cliente (TODO)
#### Nombre
Descripción
#### Escenarios:
* 
---

## Planificación y asignación de las historias de usuario (TODO)
| Historia de Usuario | Sprint | Pareja asignada |
| --- | --- | --- |
|   |   |   | 
