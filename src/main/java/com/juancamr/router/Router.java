package com.test.app.route;

import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import javax.swing.JFrame;
import javax.swing.JPanel;
import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;

import java.awt.BorderLayout;

public class Router {

    private Map<String, JPanel> routes = new HashMap<>();
    private Map<String, LayoutPanel> layouts = new HashMap<>();

    private Map<String, Class<? extends LayoutPanel>> layoutsClasses = new HashMap<>();
    private Map<String, Class<? extends JPanel>> routesClasses = new HashMap<>();
    private Map<String, Class<? extends JPanel>> rutasSinPersistencia = new HashMap<>();

    private Map<String, ArrayList<String>> relaciones = new HashMap<>();

    private JFrame window;
    private static Router router;

    private LayoutPanel currentLayout;

    public static Router getInstance() {
        if (router == null)
            router = new Router();
        return router;
    }

    public void init(int dimensions[], String packageRoute, String packageLayout) {
        int width = dimensions[0];
        int height = dimensions[1];
        window = new JFrame();
        window.setResizable(false);
        window.setSize(width, height);
        window.setLocationRelativeTo(null);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLayout(new BorderLayout());

        Reflections reflections2 = new Reflections(packageLayout, new SubTypesScanner(false));
        Set<Class<? extends LayoutPanel>> subTypes2 = reflections2.getSubTypesOf(LayoutPanel.class);
        if (subTypes2.isEmpty()) {
            System.out.println("No se encontraron layouts");
            return;
        }
        for (Class<? extends LayoutPanel> clazz : subTypes2) {
            String path = clazz.getAnnotation(Layout.class).value();
            layoutsClasses.put(path, clazz);
        }

        Reflections reflections = new Reflections(packageRoute, new SubTypesScanner(false));
        Set<Class<? extends JPanel>> subTypes = reflections.getSubTypesOf(JPanel.class);
        if (subTypes.isEmpty()) {
            System.out.println("No se encontraron rutas");
            return;
        }
        for (Class<? extends JPanel> clazz : subTypes) {
            String path = clazz.getAnnotation(Route.class).value();
            String layout = null;
            if (path.contains(":")) {
                layout = path.split(":")[0];
                path = path.split(":")[1];
            }
            if (path.contains("*")) {
                path = path.replace("*", "");
                rutasSinPersistencia.put(path, clazz);
            } else {
                routesClasses.put(path, clazz);
            }
            ArrayList<String> components = new ArrayList<>();
            components.add(path);
            components.add(layout);
            relaciones.put(path, components);
        }

    }

    public void destroyLayout(String layout) {
        LayoutPanel layoutPanel = layouts.get(layout);
        if (layoutPanel != null) {
            layouts.remove(layout);
        }
    }

    public void go(String route) {
        JPanel[] components = findPanelAndLayout(route);
        JPanel panel = components[0];
        LayoutPanel layout = (LayoutPanel) components[1];

        if (layout == null) {
            window.getContentPane().removeAll();
            window.getContentPane().add(panel, BorderLayout.CENTER);
            window.revalidate();
            window.repaint();
            window.setVisible(true);
            return;
        }

        layout.getContent().removeAll();
        layout.getContent().add(panel, BorderLayout.CENTER);
        layout.revalidate();
        layout.repaint();

        if (currentLayout != layout) {
            window.getContentPane().removeAll();
            window.getContentPane().add(layout, BorderLayout.CENTER);
            window.revalidate();
            window.repaint();
            currentLayout = layout;
        }
        if (!window.isVisible()) {
            window.setVisible(true);
        }
    }

    private JPanel[] findPanelAndLayout(String route) {
        ArrayList<String> rComponents = relaciones.get(route);
        if (rComponents == null) {
            System.out.println("La ruta \"" + route + "\" no existe");
            System.exit(0);
        }

        JPanel panel = routes.get(rComponents.get(0));
        LayoutPanel layout = layouts.get(rComponents.get(1));

        if (panel == null) {
            try {
                if (routesClasses.get(rComponents.get(0)) == null) {
                    panel = rutasSinPersistencia.get(rComponents.get(0)).getConstructor().newInstance();
                } else {
                    panel = routesClasses.get(rComponents.get(0)).getConstructor().newInstance();
                    routes.put(route, panel);
                }
            } catch (Exception error) {
                System.out.println("Error al crear la ruta " + route);
                System.out.println(error);
            }
        }

        if (layout == null) {
            try {
                layout = (LayoutPanel) layoutsClasses.get(rComponents.get(1)).getConstructor().newInstance();
                layouts.put(rComponents.get(1), layout);
            } catch (Exception error) {
                System.out.println(error);
            }
        }

        return new JPanel[] { panel, layout };
    }

    public JFrame getMainWindow() {
        return window;
    }
}
