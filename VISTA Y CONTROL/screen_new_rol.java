package app_movil_recictrash;

import java.awt.Color;
import java.sql.Connection;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class screen_new_rol extends javax.swing.JFrame {
    
    private Usuario usuario;
    
    public screen_new_rol(Usuario usuario){
        this.usuario = usuario;
        initComponents();
    }

    public screen_new_rol() {
        initComponents();
    }
    
    

   @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new PanelCircular();
        jLabel4 = new javax.swing.JLabel();
        jPanel4 = new PanelCircular();
        jLabel5 = new javax.swing.JLabel();
        jPanel5 = new PanelCircular();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(229, 255, 235));
        jPanel1.setPreferredSize(new java.awt.Dimension(360, 640));

        jPanel2.setBackground(new java.awt.Color(33, 143, 104));
        jPanel2.setPreferredSize(new java.awt.Dimension(360, 426));

        jPanel3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel3MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanel3MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPanel3MouseExited(evt);
            }
        });

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/9531246 (2).png"))); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jLabel4)
                .addContainerGap(31, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel4)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel4.setPreferredSize(new java.awt.Dimension(160, 146));
        jPanel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel4MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanel4MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPanel4MouseExited(evt);
            }
        });

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/imagen_2025-10-23_195403529-removebg-preview (1).png"))); // NOI18N

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jLabel5)
                .addContainerGap(31, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel5)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        jPanel5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel5.setPreferredSize(new java.awt.Dimension(160, 146));
        jPanel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel5MouseClicked(evt);
            }
        });

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/2416666.png"))); // NOI18N

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jLabel6)
                .addContainerGap(31, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel6)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(142, 209, 170));
        jLabel7.setText("Ciudadano");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(142, 209, 170));
        jLabel8.setText("Reciclador");

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(142, 209, 170));
        jLabel9.setText("Administrador");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel8)
                .addGap(59, 59, 59))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(96, 96, 96)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(124, 124, 124)
                        .addComponent(jLabel9)))
                .addContainerGap(16, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8))
                .addGap(29, 29, 29)
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel9)
                .addGap(37, 37, 37))
        );

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Gemini_Generated_Image_u6vuiwu6vuiwu6vu-Photoroom (3).png"))); // NOI18N

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(33, 143, 104));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("RecicTrash");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(33, 143, 104));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Elija su rol ");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(117, 117, 117)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jPanel3MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel3MouseEntered
          jPanel3.setBackground(new Color(172,229,200));
    }//GEN-LAST:event_jPanel3MouseEntered

    private void jPanel3MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel3MouseExited
          jPanel3.setBackground(new Color(142,209,170));
    }//GEN-LAST:event_jPanel3MouseExited

    private void jPanel4MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel4MouseEntered
           jPanel4.setBackground(new Color(172,229,200));
    }//GEN-LAST:event_jPanel4MouseEntered

    private void jPanel4MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel4MouseExited
        jPanel4.setBackground(new Color(142,209,170));
    }//GEN-LAST:event_jPanel4MouseExited

    private void jPanel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel3MouseClicked
        usuario.setRol("Ciudadano");
        
        if(usuario == null){
           JOptionPane.showMessageDialog(this, "Error: datos del usario no estan disponibles.","Error",JOptionPane.ERROR_MESSAGE);
           return;
        }
        if(usuario.getApellidos() == null || usuario.getApellidos().trim().isEmpty()
            || usuario.getNombres() == null || usuario.getNombres().trim().isEmpty()
                || usuario.getCorreo() == null || usuario.getCorreo().trim().isEmpty()
                || usuario.getContrasena() == null || usuario.getContrasena().trim().isEmpty()
                || usuario.getTelefono() == null || usuario.getTelefono().trim().isEmpty()){
            
            JOptionPane.showMessageDialog(this, "Faltan datos: complete todos los campos", "Faltan datos", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        int opcion = JOptionPane.showConfirmDialog(this, "¿Confirma registrarse como ciudadano?","Confirmar registro",JOptionPane.YES_NO_OPTION);
        if(opcion != JOptionPane.YES_NO_OPTION){
            return;
        }
        
        jPanel3.setEnabled(false);
        jPanel4.setEnabled(false);
        jPanel5.setEnabled(false);
        
        cConnection_1 db = new cConnection_1();
        Connection con = null;
        CallableStatement cs = null;
        ResultSet rs = null;
        
        try{
            con = db.obtenerConexion();
            if(con == null){
                JOptionPane.showMessageDialog(this,"No se pudo conectar a la base de datos","Error BD",JOptionPane.ERROR_MESSAGE);
                jPanel3.setEnabled(true);
                jPanel4.setEnabled(true);
                jPanel5.setEnabled(true);
                return;
            }
            
            String nombreCompleto = usuario.getNombres() + " " + usuario.getApellidos();
            String sql = "{call dbo.sp_registrar_usuario(?,?,?,?,?,?)}";
            cs = con.prepareCall(sql);
            
            cs.setString(1,usuario.getCorreo());
            cs.setString(2,nombreCompleto);
            cs.setString(3,usuario.getContrasena());
            cs.setString(4,usuario.getRol());
            cs.setString(5,usuario.getTelefono());
            cs.setInt(6,1);
            
            boolean hadResult = cs.execute();
            int nuevoId = 0;
            
            if(hadResult){
                rs = cs.getResultSet();
                if(rs != null && rs.next()){
                    Object val = rs.getObject(1);
                    nuevoId = val == null ? 0 : ((Number) val).intValue();
                }
            }
            else{
                int updateCount = cs.getUpdateCount();
                if(updateCount > 0) {
                    nuevoId = -1;
                }
            }
            
            if(nuevoId > 0){
                JOptionPane.showMessageDialog(this, "Registro exitoso. ID: " + nuevoId, "'Éxito",JOptionPane.INFORMATION_MESSAGE);
                this.dispose();
            }
            else if (nuevoId == -1){
                JOptionPane.showMessageDialog(this,"Registro exitoso(sinID devuelto).","Éxito",JOptionPane.INFORMATION_MESSAGE);
                this.dispose();
            }
            else{
                JOptionPane.showMessageDialog(this,"No se pudo registrar el usuario.Verifique e intente de nuevo","Error",JOptionPane.ERROR_MESSAGE);
                jPanel3.setEnabled(true);
                jPanel4.setEnabled(true);
                jPanel5.setEnabled(true);
            }
        }
        catch(SQLException ex){
            JOptionPane.showMessageDialog(this, "Error en la base de datos : " + ex.getMessage(), "Error BD", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
             jPanel3.setEnabled(true);
             jPanel4.setEnabled(true);
             jPanel5.setEnabled(true);
        }
        catch (Exception ex){
            JOptionPane.showMessageDialog(this, "Error inesperado : " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
            jPanel3.setEnabled(true);
            jPanel4.setEnabled(true);
            jPanel5.setEnabled(true);
        }
        finally{
            try{if (rs != null) rs.close();} catch (SQLException e) { e.printStackTrace();}
            try{if (cs != null) cs.close();} catch (SQLException e) { e.printStackTrace();}
            
            db.closeConnection();
        }
    }//GEN-LAST:event_jPanel3MouseClicked

    private void jPanel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel4MouseClicked
        usuario.setRol("Reciclador");
        if(usuario == null){
           JOptionPane.showMessageDialog(this, "Error: datos del usario no estan disponibles.","Error",JOptionPane.ERROR_MESSAGE);
           return;
        }
        if(usuario.getApellidos() == null || usuario.getApellidos().trim().isEmpty()
            || usuario.getNombres() == null || usuario.getNombres().trim().isEmpty()
                || usuario.getCorreo() == null || usuario.getCorreo().trim().isEmpty()
                || usuario.getContrasena() == null || usuario.getContrasena().trim().isEmpty()
                || usuario.getTelefono() == null || usuario.getTelefono().trim().isEmpty()){
            
            JOptionPane.showMessageDialog(this, "Faltan datos: complete todos los campos", "Faltan datos", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        int opcion = JOptionPane.showConfirmDialog(this, "¿Confirma registrarse como reciclador?","Confirmar registro",JOptionPane.YES_NO_OPTION);
        if(opcion != JOptionPane.YES_NO_OPTION){
            return;
        }
        
        jPanel3.setEnabled(false);
        jPanel4.setEnabled(false);
        jPanel5.setEnabled(false);
        
        cConnection_1 db = new cConnection_1();
        Connection con = null;
        CallableStatement cs = null;
        ResultSet rs = null;
        
        try{
            con = db.obtenerConexion();
            if(con == null){
                JOptionPane.showMessageDialog(this,"No se pudo conectar a la base de datos","Error BD",JOptionPane.ERROR_MESSAGE);
                jPanel3.setEnabled(true);
                jPanel4.setEnabled(true);
                jPanel5.setEnabled(true);
                return;
            }
            
           String nombreCompleto = usuario.getNombres() + " " + usuario.getApellidos();
            String sql = "{call dbo.sp_registrar_usuario(?,?,?,?,?,?)}";
            cs = con.prepareCall(sql);
            
            cs.setString(1,usuario.getCorreo());
            cs.setString(2,nombreCompleto);
            cs.setString(3,usuario.getContrasena());
            cs.setString(4,usuario.getRol());
            cs.setString(5,usuario.getTelefono());
            cs.setInt(6,1);
            
            boolean hadResult = cs.execute();
            int nuevoId = 0;
            
            if(hadResult){
                rs = cs.getResultSet();
                if(rs != null && rs.next()){
                    Object val = rs.getObject(1);
                    nuevoId = val == null ? 0 : ((Number) val).intValue();
                }
            }
            else{
                int updateCount = cs.getUpdateCount();
                if(updateCount > 0) {
                    nuevoId = -1;
                }
            }
            
            if(nuevoId > 0){
                JOptionPane.showMessageDialog(this, "Registro exitoso. ID: " + nuevoId, "'Éxito",JOptionPane.INFORMATION_MESSAGE);
                this.dispose();
            }
            else if (nuevoId == -1){
                JOptionPane.showMessageDialog(this,"Registro exitoso(sinID devuelto).","Éxito",JOptionPane.INFORMATION_MESSAGE);
                this.dispose();
            }
            else{
                JOptionPane.showMessageDialog(this,"No se pudo registrar el usuario.Verifique e intente de nuevo","Error",JOptionPane.ERROR_MESSAGE);
                jPanel3.setEnabled(true);
                jPanel4.setEnabled(true);
                jPanel5.setEnabled(true);
            }
        }
        catch(SQLException ex){
            JOptionPane.showMessageDialog(this, "Error en la base de datos : " + ex.getMessage(), "Error BD", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
             jPanel3.setEnabled(true);
             jPanel4.setEnabled(true);
             jPanel5.setEnabled(true);
        }
        catch (Exception ex){
            JOptionPane.showMessageDialog(this, "Error inesperado : " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
            jPanel3.setEnabled(true);
            jPanel4.setEnabled(true);
            jPanel5.setEnabled(true);
        }
        finally{
            try{if (rs != null) rs.close();} catch (SQLException e) { e.printStackTrace();}
            try{if (cs != null) cs.close();} catch (SQLException e) { e.printStackTrace();}
            
            db.closeConnection();
        }
    }//GEN-LAST:event_jPanel4MouseClicked

    private void jPanel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel5MouseClicked
        usuario.setRol("Administrador");
        if(usuario == null){
           JOptionPane.showMessageDialog(this, "Error: datos del usario no estan disponibles.","Error",JOptionPane.ERROR_MESSAGE);
           return;
        }
        if(usuario.getApellidos() == null || usuario.getApellidos().trim().isEmpty()
            || usuario.getNombres() == null || usuario.getNombres().trim().isEmpty()
                || usuario.getCorreo() == null || usuario.getCorreo().trim().isEmpty()
                || usuario.getContrasena() == null || usuario.getContrasena().trim().isEmpty()
                || usuario.getTelefono() == null || usuario.getTelefono().trim().isEmpty()){
            
            JOptionPane.showMessageDialog(this, "Faltan datos: complete todos los campos", "Faltan datos", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        int opcion = JOptionPane.showConfirmDialog(this, "¿Confirma registrarse como administrador?","Confirmar registro",JOptionPane.YES_NO_OPTION);
        if(opcion != JOptionPane.YES_NO_OPTION){
            return;
        }
        
        jPanel3.setEnabled(false);
        jPanel4.setEnabled(false);
        jPanel5.setEnabled(false);
        
        cConnection_1 db = new cConnection_1();
        Connection con = null;
        CallableStatement cs = null;
        ResultSet rs = null;
        
        try{
            con = db.obtenerConexion();
            if(con == null){
                JOptionPane.showMessageDialog(this,"No se pudo conectar a la base de datos","Error BD",JOptionPane.ERROR_MESSAGE);
                jPanel3.setEnabled(true);
                jPanel4.setEnabled(true);
                jPanel5.setEnabled(true);
                return;
            }
            
            String nombreCompleto = usuario.getNombres() + " " + usuario.getApellidos();
            String sql = "{call dbo.sp_registrar_usuario(?,?,?,?,?,?)}";
            cs = con.prepareCall(sql);
            
            cs.setString(1,usuario.getCorreo());
            cs.setString(2,nombreCompleto);
            cs.setString(3,usuario.getContrasena());
            cs.setString(4,usuario.getRol());
            cs.setString(5,usuario.getTelefono());
            cs.setInt(6,1);
            
            boolean hadResult = cs.execute();
            int nuevoId = 0;
            
            if(hadResult){
                rs = cs.getResultSet();
                if(rs != null && rs.next()){
                    Object val = rs.getObject(1);
                    nuevoId = val == null ? 0 : ((Number) val).intValue();
                }
            }
            else{
                int updateCount = cs.getUpdateCount();
                if(updateCount > 0) {
                    nuevoId = -1;
                }
            }
            
            if(nuevoId > 0){
                JOptionPane.showMessageDialog(this, "Registro exitoso. ID: " + nuevoId, "'Éxito",JOptionPane.INFORMATION_MESSAGE);
                this.dispose();
            }
            else if (nuevoId == -1){
                JOptionPane.showMessageDialog(this,"Registro exitoso(sinID devuelto).","Éxito",JOptionPane.INFORMATION_MESSAGE);
                this.dispose();
            }
            else{
                JOptionPane.showMessageDialog(this,"No se pudo registrar el usuario.Verifique e intente de nuevo","Error",JOptionPane.ERROR_MESSAGE);
                jPanel3.setEnabled(true);
                jPanel4.setEnabled(true);
                jPanel5.setEnabled(true);
            }
        }
        catch(SQLException ex){
            JOptionPane.showMessageDialog(this, "Error en la base de datos : " + ex.getMessage(), "Error BD", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
             jPanel3.setEnabled(true);
             jPanel4.setEnabled(true);
             jPanel5.setEnabled(true);
        }
        catch (Exception ex){
            JOptionPane.showMessageDialog(this, "Error inesperado : " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
            jPanel3.setEnabled(true);
            jPanel4.setEnabled(true);
            jPanel5.setEnabled(true);
        }
        finally{
            try{if (rs != null) rs.close();} catch (SQLException e) { e.printStackTrace();}
            try{if (cs != null) cs.close();} catch (SQLException e) { e.printStackTrace();}
            
            db.closeConnection();
        }
    }//GEN-LAST:event_jPanel5MouseClicked

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
            java.util.logging.Logger.getLogger(screen_new_rol.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(screen_new_rol.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(screen_new_rol.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(screen_new_rol.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new screen_new_rol().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    // End of variables declaration//GEN-END:variables
}
