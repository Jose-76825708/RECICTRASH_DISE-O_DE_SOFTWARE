package app_movil_recictrash;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class PanelCircular extends JPanel {

    private Color colorNormal = new Color(142, 209, 170);
    private Color colorHover = new Color(162, 229, 190);
    private Color colorActual = colorNormal;

    public PanelCircular() {
        setOpaque(false);

        // Detectar cuando el mouse entra o sale del panel
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                colorActual = colorHover;
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                colorActual = colorNormal;
                repaint();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setColor(colorActual);
        g2.fillOval(0, 0, getWidth(), getHeight());
    }
}
