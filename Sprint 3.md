# Sprint 3

## Nivel a alcanzar

En este primer _sprint_ el grupo de trabajo DP2-1920-G3-03 ha decidió optar al nivel de aplicación 2, contando para ello con 19 entidades y 25 historias de usuario. El nivel de sprint al que se opta es el 9, añadiendo para ello el 85% de las historias de usuario, sus pruebas unitarias, la automatización con _Travis CI_, la implementación de una API con la que mostrar oraciones y pruebas parametrizadas. Además de lo realizado en el anterior entregable hemos añadido pruebas _end to end_, integración con la base de datos, pruebas de interfaz de usuario y otras pruebas de integración de controladores.

---

## Justificación de lo realizado

Aquí vemos una tabla con los estados de las historias de usuario y el estado del proyecto, especificando también la historia de usuario realizada por cada pareja:

![](https://github.com/gii-is-DP2/DP2-1920-G3-03/blob/develop/imagesDocumentation/ProductBacklogSprint3.jpg)

Durante este _sprint_ hemos encontrados diferentes problemas a la hora de realizar la implementación de pruebas. 

* El primero de ellos fue el de los datos que persistían, lo que hacía fallar otras pruebas que se realizaban posteriormente, concretamente esto ocurría en las pruebas de servicio con la base de datos de producción, interfaz de usuario y controladores _end to end_, solucionándose con la etiqueta @DirtiesContext(methodMode = MethodMode.AFTER_METHOD).

* La siguiente dificultad con la que nos encontramos fue la diferencia de formato de fechas de _Eclipse_ y la base de datos de producción _MySQL_, arreglándose añadiendo en la configuración del _plugin_ de _maven_ en _pom.xml_ la siguiente línea: <argLine>-Duser.timezone=UTC</argLine>.
* El último problema encontrado fue la generación de _log_ en _Travis_, ya que debido a la cantidad de pruebas y a las etiquetas de @DirtiesContext que generan una gran cantidad de líneas. La solución por la que optamos fue la eliminación de información de _log_ configurándolo en _application.properties_, y además añadiendo un archivo _logback-test.xml_ en src/test/resource.

---

## Retrospectiva

A nivel de grupo, consideramos que este tercer _sprint_ se ha realizado de forma correcta, llegando a un consenso sobre como se implementaría la aplicación web, realizándose las historias de usuario cumpliendo los plazos acordados. Solucionamos los problemas que tuvimos en el entregable anterior haciendo las historias de usuario juntos a sus pruebas a un buen ritmo, incluso teniendo que solucionar los errores encontrados.

Por parte de la pareja 1 (Enrique y Víctor), pensamos que hemos trabajado bien, aunque por las circunstancias habidas ha sido un poco complicada la comunicación y la programación en pareja.

Por parte de la pareja 2 (Carlos y Francisco José), pensamos que hemos mejorado la comunicación respecto al _sprint_ anterior, aunque es verdad que nos ha costado realizar las tareas debido a situación actual por diversos factores como la concentración en el lugar de trabajo, o la perdida de la rutina.

Por parte de la pareja 3 (Álvaro y José Manuel), pensamos que en este _sprint_ hemos trabajando correctamente realizando la implementación de historias de usuario y las pruebas a tiempo, aunque vemos que se ha reducido la comunicación en el presente _sprint_.

Las horas realizadas por cada miembro del equipo de trabajo son guardadas en una hoja excel en el siguiente enlace: https://docs.google.com/spreadsheets/d/1jbJnSilCwHPl17SKoCsF2YmVKAUaUYSlCQ9Jfgw4vy8/edit?usp=sharing

**Horas dedicadas por cada miembro hasta el 11/5/2020**

![](https://github.com/gii-is-DP2/DP2-1920-G3-03/blob/develop/imagesDocumentation/Horas3.jpg)
