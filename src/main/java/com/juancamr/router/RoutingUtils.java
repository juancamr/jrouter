package com.juancamr.route;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import javax.swing.JDialog;
import javax.swing.JFrame;


public class RoutingUtils {


    public static Map<String, Object> openDialog(JFrame parent, DialogPanel dialogPanel) {
        try {
            CompletableFuture<Map<String, Object>> future = new CompletableFuture<>();
            JDialog dialog = new JDialog(parent, "Modal", true);
            dialogPanel.getButtonAction().addActionListener(e -> {
                Map<String, Object> respuesta = new HashMap<>();
                respuesta = dialogPanel.getOnAction().apply(respuesta);
                future.complete(respuesta);
                dialog.dispose();
            });

            dialog.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    if (!future.isDone()) {
                        future.complete(null);
                    }
                }
            });

            dialog.add(dialogPanel);
            dialog.pack();
            dialog.setLocationRelativeTo(null);
            dialog.setVisible(true);
            return future.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Map<String, Object> openDialog(DialogPanel dialogPanel) {
        try {
            CompletableFuture<Map<String, Object>> future = new CompletableFuture<>();
            JDialog dialog = new JDialog(Router.getInstance().getMainWindow(), "Modal", true);
            dialogPanel.getButtonAction().addActionListener(e -> {
                Map<String, Object> respuesta = new HashMap<>();
                respuesta = dialogPanel.getOnAction().apply(respuesta);
                future.complete(respuesta);
                dialog.dispose();
            });

            dialog.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    if (!future.isDone()) {
                        future.complete(null);
                    }
                }
            });

            dialog.add(dialogPanel);
            dialog.pack();
            dialog.setLocationRelativeTo(null);
            dialog.setVisible(true);
            return future.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            return null;
        }
    }
}
