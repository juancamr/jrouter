package com.juancamr.router;

import javax.swing.JPanel;

public class LayoutPanel extends JPanel {

    private JPanel children;

    public LayoutPanel() {
        super();
    }

    public void setChildren(JPanel content) {
        this.children = content;
    }

    public JPanel getChildren() {
        return children;
    }
}
