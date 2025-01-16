# PuntosDeVenta
Puntos de venta, costos y acreditaciones
A tener en cuenta para correr la aplicacion:
*Para desarrollar la app utilice intellij,jdk-17, Springboot y Maven.
*La conexión con la base de datos está realizada en mysql(version 8.0.30) De tener otra versión deberá modificarla en el pom.xml y en aplication.properties. Dejo dos archivos txt de como deberían estar configurados estos dos para que la base de datos se conecte correctamente. De igual manera el usuario y contraseña en aplication.properties deberá personalizarlo.
*Antes de correr la aplicación se debe ejecutar en Mysql Workbench el "scriptBDD" que creará la base de datos, las tablas y los datos necesarios para la prueba en postman.
*Para las pruebas se encuentra el archivo "puntosdeventa.postman_collection" para probar las request en postman donde están todas las urls a probar.
*Hay una clase "PuntosDeVentaApp.java" donde seleccionando run compila el codigo.
