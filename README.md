# JRouter

Este proyecto en Java está diseñado para facilitar la creación de rutas de navegación en aplicaciones de escritorio desarrolladas con Java Swing. Ofrece una manera eficiente y flexible de manejar la navegación entre diferentes vistas o paneles dentro de una interfaz gráfica.

## Funcionalidad

Definición de Rutas: Permite definir rutas de navegación entre diferentes JPanel o vistas en una aplicación Swing.
Creación de Layouts: Permite crear layouts o paneles padre para diferentes vistas.
Navegación Dinámica: Facilita la transición entre vistas sin necesidad de gestionar manualmente la visibilidad de los paneles.
Configuración Sencilla: Ofrece una configuración fácil y rápida para integrar en aplicaciones Swing existentes.

## Instalación

Agrega el repositorio de JitPack y la dependencia a tu archivo pom.xml:

```xml
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>

<dependencies>
    <!-- tus dependencias aquí -->

    <!-- jrouter -->
	<dependency>
	    <groupId>com.github.juancamr</groupId>
	    <artifactId>jrouter</artifactId>
	    <version>1.0</version>
	</dependency>
</dependencies>
```

## Uso

Definición del tamaño de ventana: Indica el ancho y alto de la ventana principal.
Definición de paquetes: Indica el paquete a analizar para encontrar las rutas (obligatorio) y layouts (opcional).
Configura las Rutas: Utiliza las anotaciones @Route para definir las rutas entre tus paneles.
Configura las Layouts: Utiliza las anotaciones @Layout para definir los layouts o paneles padre para tus vistas.
Navega a una vista: Utiliza el método go() para navegar a una vista.

## Ejemplo de Código

### Setup

Definición de valores de configuración:

```java
int dimensiones[] = {1000, 600}; // las dimensiones de tu preferencia
String packageRoute =  "com.tuprograma.app.view.routes"; // obligatorio
String packageLayout = "com.tuprograma.app.view.layouts"; // opcional
```

Inicialización del router:

```java
Router.getInstance().init(dimensiones, packageRoute); // sin layouts
// o
Router.getInstance().init(dimensiones, packageRoute, packageLayout); // con layouts
```

### Rutas

Para definir una ruta, utiliza la anotación @Route en la clase de tu panel.
Considera que el constructor de tu panel no reciba parámetros.

```java
@Route("home")
public class HomePanel extends JPanel {
    // tu código aquí
}
```

### Layouts

Para definir un layout o panel padre, utiliza la anotación @Layout y hereda de la clase LayoutPanel.
Luego, utiliza el método setChildren() para establecer el panel hijo (paneles que cambiaran).
Considera que el constructor de tu panel no reciba parámetros.

```java
@Layout("main")
public class MainLayout extends LayoutPanel {

    public MainLayout() {
        initComponents();
        setChildren()
    }
}
```

## Dependencias

Este proyecto utiliza la biblioteca Reflections, que está licenciada bajo la [Apache License 2.0](https://www.apache.org/licenses/LICENSE-2.0).

## Contribuciones

¡Las contribuciones son bienvenidas! Si encuentras algún problema o tienes una mejora para el proyecto, no dudes en abrir un issue o enviar un pull request.
Licencia

Este proyecto está bajo la Licencia MIT. Consulta el archivo LICENSE para más detalles.
