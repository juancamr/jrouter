package com.juancamr.route;

import javax.swing.JButton;
import javax.swing.JPanel;

public class DialogPanel extends JPanel {

    private JButton buttonAction;
    private FunctionCustomFuture onAction;

    public DialogPanel() {
        super();
    }

    public JButton getButtonAction() {
        return buttonAction;
    }

    public void setButtonAction(JButton action) {
        this.buttonAction = action;
    }

    public FunctionCustomFuture getOnAction() {
        return onAction;
    }

    public void setOnAction(FunctionCustomFuture customFunction) {
        this.onAction = customFunction;
    }
}
