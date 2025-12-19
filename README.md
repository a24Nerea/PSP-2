# Proyecto 2 Broker de bolsa
# Índice
- [Descripción](#descripción)
- [Creación de agentes](#crear-agentes)
- [Operacion de Compra y Venta](#operación-compra-venta)



# Descripción
El siguiente proyecto simula una compra y venta de acciones de determinados agentes. En una de las ventanas de la aplicación se puede introducir nuevos agentes, con id, nombre y un saldo inicial. En otra de ellas se pueden realizar dichas operaciones seleccionando el agente, y la compra o venta que quieras realizar, así como, introducir el precio y la cantidad. Además, contiene una gráfica que se va cambiando en tiempo real según las operaciones que se realicen

## Crear Agentes
En la ventana principal tenemos que hacer click en el menú que pone "Broker", allí nos saldrán varios menús donde nos aparecerá las diferentes ventanas que contiene la aplicación. En este caso le daremos a la opción de Gestionar Agentes

![Peticion1](/imagenesReadme/gestionAgentesPestaña.bmp)

Una vez hagamos click nos aparecerá como la imagen que dejo a continuación. Aquí debemos introducir los datos correctamente, si nos equivocamos y dejamos algún campo vacío, duplicamos algún nombre o ID la aplicación nos avisará. Además, en la ventana aparecerá una lista de los agentes que añadas o los que añadiste en un pasado.

## Operación compra-venta

Para poder abrir la ventana de las operaciones, volvemos a la ventana principal y le damos al menú de broker, como indico anteriormente, y seleccionamos la opción de operaciones:

![Peticion1](/imagenesReadme/gestionOperaciones.bmp)

Una vez dentro de la vista, seleccionamos, gracias al comoBox, el agente en cuestion:

![Peticion1](/imagenesReadme/elegirAgenteComboBox.bmp)

Introducimos los datos necesarios, y una vez le demos al botón Guardar, si la operación ha sido realizada con éxito, nos mostrará un mensaje con información sobre la operación. Ejemplo de operación "Compra":

![Peticion1](/imagenesReadme/ordenCompraRegistrada.bmp)

Por último, dejo una imagen de una operación "Venta" y al lado se puede comprobar como la gráfica varía según el precio que le indicamos:

![Peticion1](/imagenesReadme/ordenVentaRegistrada.bmp)
