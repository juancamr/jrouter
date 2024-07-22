package com.juancamr.route;

import javax.swing.JPanel;

public class LayoutPanel extends JPanel {

    private JPanel content;

    public LayoutPanel() {
        super();
    }

    public void setContent(JPanel content) {
        this.content = content;
    }

    public JPanel getContent() {
        return content;
    }
}
