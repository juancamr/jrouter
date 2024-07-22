package com.juancamr.router;

import java.util.Map;

import javax.swing.JButton;
import javax.swing.JPanel;

public class DialogPanel extends JPanel {

    private JButton buttonAction;
    private GetDataFromDialog onAction;

    public DialogPanel() {
        super();
    }

    public JButton getButtonAction() {
        return buttonAction;
    }

    public GetDataFromDialog getOnAction() {
        return onAction;
    }

    public void setAction(JButton button, GetDataFromDialog customFunction) {
        this.buttonAction = button;
        this.onAction = customFunction;
    }

    @FunctionalInterface
    public static interface GetDataFromDialog {
        Map<String, Object> apply(Map<String, Object> respuesta);
    }
    
    @FunctionalInterface
    public static interface UseDataFromDialog {
       void apply(Map<String, Object> respuesta);
    }
}
