package com.juancamr.jrouter;

import javax.swing.JPanel;

public class LayoutPanel extends JPanel {

    private JPanel children;

    public LayoutPanel() {
        super();
    }

    public void setChildren(JPanel children) {
        this.children = children;
    }

    public JPanel getChildren() {
        return children;
    }
}
