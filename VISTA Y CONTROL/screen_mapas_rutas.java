package app_movil_recictrash;

import java.sql.*; 
import java.util.ArrayList; 
import java.util.List; 
import javafx.application.Platform; 
import javafx.scene.web.WebEngine;
import java.awt.Dimension;
import javafx.embed.swing.JFXPanel;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.web.WebView;
import javafx.scene.web.WebEngine;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

public class screen_mapas_rutas extends javax.swing.JFrame {

    private JFXPanel jfxPanel;
    String categoria;
    private WebEngine webEngine;
    class Pin {
    double lat;
    double lng;
    String info;
    Pin(double lat, double lng, String info){
        this.lat = lat;
        this.lng = lng;
        this.info = info;
        }
    }
    private void cargarPins(WebEngine webEngine){
    List<Pin> pins = new ArrayList<>();

    try (Connection con = new cConnection_1().obtenerConexion();
         PreparedStatement ps = con.prepareStatement("EXEC dbo.ObtenerReportesUbicacion");
         ResultSet rs = ps.executeQuery()) {

        while(rs.next()){
            pins.add(new Pin(
                rs.getDouble("latitud"),
                rs.getDouble("longitud"),
                rs.getString("descripcion")
            ));
        }

    } catch(SQLException e) {
        e.printStackTrace();
    }

    // Convertir lista de pines a array JS
    StringBuilder jsArray = new StringBuilder("[");
    for(Pin p : pins){
        jsArray.append("{lat:").append(p.lat)
               .append(",lng:").append(p.lng)
               .append(",info:'").append(p.info.replace("'", "\\'")).append("'},");
    }
    if(jsArray.length() > 1) jsArray.setLength(jsArray.length() - 1); // quitar última coma
    jsArray.append("]");

    // Llamar a la función JS en Leaflet
    Platform.runLater(() -> {
        webEngine.executeScript("addMarkersFromJava(" + jsArray.toString() + ");");
    });
}

    
    public screen_mapas_rutas() {
         initComponents();

        // FORZAR tamaño fijo del jPanelMapa (348 x 350)
        Dimension mapaSize = new Dimension(348, 350);
        jPanelMapa.setPreferredSize(mapaSize);
        jPanelMapa.setMinimumSize(mapaSize);
        jPanelMapa.setMaximumSize(mapaSize);
        jPanelMapa.setSize(mapaSize);

        // Usamos layout absoluto en jPanelMapa para controlar exactamente el JFXPanel
        jPanelMapa.setLayout(null);

        // Crear JFXPanel y colocarlo con bounds fijos (0,0,width,height)
        jfxPanel = new JFXPanel();
        jfxPanel.setBounds(0, 0, mapaSize.width, mapaSize.height);
        jfxPanel.setPreferredSize(mapaSize);
        jfxPanel.setSize(mapaSize);

        // Remover cualquier JFXPanel anterior si existiera
        jPanelMapa.removeAll();
        jPanelMapa.add(jfxPanel);
        jPanelMapa.revalidate();
        jPanelMapa.repaint();

        // Evitar que el JFrame se auto redimensione de manera impredecible
        this.setResizable(false);

        // Inicializar JavaFX
        Platform.runLater(() -> initFX(jfxPanel));

        // Tras pack() fijamos los tamaños otra vez (evita que GroupLayout los cambie)
        pack();
        SwingUtilities.invokeLater(() -> {
            jPanelMapa.setSize(mapaSize);
            jPanelMapa.setPreferredSize(mapaSize);
            jfxPanel.setBounds(0, 0, mapaSize.width, mapaSize.height);
            jPanelMapa.revalidate();
            jPanelMapa.repaint();
        });

        
    }

    private void initFX(JFXPanel panel) {
    WebView webView = new WebView();
    webEngine = webView.getEngine();

    webEngine.setUserAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 "
            + "(KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36");

    try {
        java.net.URL url = getClass().getResource("/app_movil_recictrash/mapa.html");
        System.out.println("mapa.html resource url = " + url);
        if (url != null) {
            webEngine.load(url.toExternalForm());
        } else {
            webEngine.loadContent("<html><body><h3>ERROR: mapa.html no encontrado en recursos</h3></body></html>");
        }

        // Inyectar puente JS -> Java (window.app.log / window.app.err)
        webEngine.getLoadWorker().stateProperty().addListener((obs, oldS, newS) -> {
            System.out.println("Load state: " + newS);
            if (newS == javafx.concurrent.Worker.State.SUCCEEDED) {
                try {
                    netscape.javascript.JSObject window = (netscape.javascript.JSObject) webEngine.executeScript("window");
                    Object app = new Object() {
                        public void log(String msg) { System.out.println("[WEB-LOG] " + msg); }
                        public void err(String msg) { System.err.println("[WEB-ERR] " + msg); }
                    };
                    window.setMember("app", app);
                    System.out.println("JS bridge injected (window.app).");
                } catch (Exception ex) {
                    System.out.println("Error injecting JS bridge: " + ex);
                    ex.printStackTrace(System.out);
                }
            }
        });

    } catch (Exception ex) {
        ex.printStackTrace();
        webEngine.loadContent("<html><body><h1>Error interno</h1><pre>" + ex + "</pre></body></html>");
    }

    panel.setScene(new Scene(webView));
    webEngine.getLoadWorker().stateProperty().addListener((obs, oldS, newS) -> {
    if(newS == javafx.concurrent.Worker.State.SUCCEEDED){
        try {
            netscape.javascript.JSObject window = (netscape.javascript.JSObject) webEngine.executeScript("window");
            Object app = new Object() {
                public void log(String msg) { System.out.println("[WEB-LOG] " + msg); }
                public void err(String msg) { System.err.println("[WEB-ERR] " + msg); }
            };
            window.setMember("app", app);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        // ✅ Aquí cargamos los pines desde la DB usando tu clase de conexión
        cargarPins(webEngine);
    }
});
}
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jPanelMapa = new javax.swing.JPanel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(229, 255, 235));
        jPanel1.setPreferredSize(new java.awt.Dimension(374, 772));

        jPanel2.setBackground(new java.awt.Color(33, 143, 104));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("MAPAS Y RUTAS");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(125, 125, 125)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(33, 143, 104));

        jPanelMapa.setMaximumSize(new java.awt.Dimension(348, 350));
        jPanelMapa.setMinimumSize(new java.awt.Dimension(348, 350));
        jPanelMapa.setPreferredSize(new java.awt.Dimension(350, 348));

        javax.swing.GroupLayout jPanelMapaLayout = new javax.swing.GroupLayout(jPanelMapa);
        jPanelMapa.setLayout(jPanelMapaLayout);
        jPanelMapaLayout.setHorizontalGroup(
            jPanelMapaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 350, Short.MAX_VALUE)
        );
        jPanelMapaLayout.setVerticalGroup(
            jPanelMapaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelMapa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelMapa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(25, Short.MAX_VALUE))
        );

        jComboBox1.setBackground(new java.awt.Color(33, 143, 104));
        jComboBox1.setForeground(new java.awt.Color(255, 255, 255));
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecciona", "Orgánico", "Plástico", "Vidrio", "Papel", "Metales", "Otros" }));

        jLabel5.setBackground(new java.awt.Color(255, 255, 255));
        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Selecciona el tipo de residuo:");

        jPanel4.setBackground(new java.awt.Color(33, 143, 104));

        jLabel4.setBackground(new java.awt.Color(255, 255, 255));
        jLabel4.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("BUSCAR PUNTOS");
        jLabel4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel4MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE)
        );

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Nombre", "Ubicacion", "Tipo "
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jPanel5.setBackground(new java.awt.Color(33, 143, 104));

        jLabel6.setBackground(new java.awt.Color(255, 255, 255));
        jLabel6.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("VOLVER");
        jLabel6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel6MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 108, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(124, 124, 124)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(36, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 702, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseClicked
          int Opc = jComboBox1.getSelectedIndex();
         String categoria = null;
         switch(Opc){
             case 1 -> categoria = "Orgánico";
             case 2 -> categoria = "Plástico";
             case 3 -> categoria = "Vidrio";
             case 4 -> categoria = "Papel";
             case 5 -> categoria = "Metales";
             case 6 -> categoria = "Otros";
         }

         // Conectamos a la DB y ejecutamos el SP
         List<Pin> pins = new ArrayList<>();
         DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
         model.setRowCount(0); // Limpiar tabla antes de llenar

         try (Connection con = new cConnection_1().obtenerConexion();
              CallableStatement cs = con.prepareCall("{call dbo.ObtenerReportesUbicacionFiltro(?)}")) {

             cs.setString(1, categoria);

             try (ResultSet rs = cs.executeQuery()) {
                 while(rs.next()){
                     // Agregamos pines para el mapa
                     pins.add(new Pin(
                         rs.getDouble("latitud"),
                         rs.getDouble("longitud"),
                         rs.getString("descripcion")
                     ));

                     // Concatenamos latitud y longitud para la columna "Ubicación"
                     String ubicacion = rs.getDouble("latitud") + ", " + rs.getDouble("longitud");

                     // Agregar fila a jTable1
                     model.addRow(new Object[] {
                         rs.getString("descripcion"),
                         ubicacion,
                         rs.getString("tipo_residuo_texto")
                     });
                 }
             }

         } catch(SQLException e) {
             e.printStackTrace();
         }

         // Convertir lista de pines a array JS para el mapa
         StringBuilder jsArray = new StringBuilder("[");
         for(Pin p : pins){
             jsArray.append("{lat:").append(p.lat)
                    .append(",lng:").append(p.lng)
                    .append(",info:'").append(p.info.replace("'", "\\'")).append("'},");
         }
         if(jsArray.length() > 1) jsArray.setLength(jsArray.length() - 1);
         jsArray.append("]");

         // Actualizar mapa en JavaFX
         Platform.runLater(() -> {
             webEngine.executeScript("addMarkersFromJava(" + jsArray.toString() + ");");
         });
    }//GEN-LAST:event_jLabel4MouseClicked

    private void jLabel6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MouseClicked
        this.dispose();
    }//GEN-LAST:event_jLabel6MouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(screen_mapas_rutas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(screen_mapas_rutas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(screen_mapas_rutas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(screen_mapas_rutas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new screen_mapas_rutas().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanelMapa;
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
