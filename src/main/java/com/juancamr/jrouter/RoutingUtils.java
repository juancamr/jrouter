package com.juancamr.jrouter;

import java.awt.BorderLayout;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

public class RoutingUtils {
    
    public static void openDialog(Class<? extends DialogPanel> dialogClass,
            DialogPanel.UseDataFromDialog fn) {
        JFrame parent = null;
            JDialog dialog = new JDialog(parent, "Modal", true);
            try {
                DialogPanel dialogPanel = dialogClass.getConstructor().newInstance();
                dialogPanel.getButtonAction().addActionListener(e -> {
                    Map<String, Object> respuesta = new HashMap<>();
                    respuesta = dialogPanel.getOnAction().apply(respuesta);
                    dialog.dispose();
                    if (respuesta != null) {
                        fn.apply(respuesta);
                    }
                });

                dialog.add(dialogPanel);
                dialog.pack();
                dialog.setLocationRelativeTo(null);
                dialog.setVisible(true);
            } catch (Exception e) {
                System.out.println(e);
            }
    }

    public static void whileLoading(Runnable function) {
        JFrame frame = null;
        JDialog loadingDialog = new JDialog(frame, "Cargando", true);
        JLabel loadingLabel = new JLabel("Cargando, por favor espere...");
        loadingLabel.setHorizontalAlignment(SwingConstants.CENTER);
        loadingDialog.getContentPane().add(loadingLabel, BorderLayout.CENTER);
        loadingDialog.setSize(200, 100);
        loadingDialog.setLocationRelativeTo(frame);

        new Thread(() -> {
            SwingUtilities.invokeLater(() -> loadingDialog.setVisible(true));
            function.run();
            SwingUtilities.invokeLater(loadingDialog::dispose);
        }).start();
    }

}
