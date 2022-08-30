// biniakou theofanis icsd13126

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;


public class NewJFrame1 extends javax.swing.JFrame {

    protected File selectedFile;
    protected JFileChooser fileChooser;
    protected Socket s;
    
    public NewJFrame1() {
        super("Choose File to Send to Server");
        initComponents();
        // gia na emfanhstei to frame sto kentro ths othonhs
        setLocationRelativeTo(null);
    }
    
    public void set_sock(Socket s)
    {
        this.s = s;
    }
        @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton1.setText("select file");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("send file");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addComponent(jButton1)
                        .addGap(75, 75, 75)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(181, 181, 181)
                        .addComponent(jButton2)))
                .addContainerGap(125, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addComponent(jButton2)
                .addContainerGap(70, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.fileChooser = new JFileChooser();
                int returnValue = fileChooser.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    selectedFile = fileChooser.getSelectedFile();
                    System.out.println(selectedFile.getName());
                    jLabel1.setText(selectedFile.getName());
                }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        
        BufferedWriter bw = null;
        FileInputStream fis = null;
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        
        try {
            if (selectedFile != null){
            
                String ip,file_name;
            
                Scanner sc = new Scanner(System.in);
                // rwtaw ton xrhsth thn ip kai to onoma tou arxeiou
                System.out.print("write your ip: ");
                ip = sc.nextLine();
                System.out.print("write file's name: ");
                file_name = sc.nextLine();
            
                // dhmiourgw enan bufferedWriter gia na steilw ta 2 parapanw string
                bw = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
                
                // stelnw thn ip
                bw.write(ip);
                bw.newLine();
                bw.flush();
                // stelnw to onoma tou arxeiou
                bw.write(file_name);
                bw.newLine();
                bw.flush();
            
                // mesw autou tou buffer tha steilw ton pinaka me bytes pou antistoixei sto arxeio
                bos = new BufferedOutputStream(s.getOutputStream());
                
                // genika, tha metatrepsw to arxeio se pinana me bytes, kai tha to steilw se auth thn morfh
                
                // dhlwnw enan pinana me byte pou tha exei oses theseis oses kai to megethos tou arxeiou pou tha stalthei
                byte[] file_bytes = new byte[(int) selectedFile.length()];
            
                // fortwnw to arxeio se enan buffer
                bis = new BufferedInputStream(new FileInputStream(selectedFile));
                
                // mesw tou buffer pou uparxei to arxeio, diabazw ton pinaka me bytes
                bis.read(file_bytes);
                // kai mesw tou buffer pou arxikopeiei to stream to stelnw sto server
                bos.write(file_bytes);
                bos.flush();
                
                // "koimizw" ton client gia 10sec wste na prolavei na stalthei olos o pinakas me bytes
                Thread.sleep(10000);
            
            }
            // an patithei to koubi "send" xwris na exei epileksei arxeio, emfanizw katalhllo mhnuma
            else{
                JOptionPane.showMessageDialog(null, "Please Choose a File");
            }
            
        } catch (IOException e) {
            System.out.println("4");
            Logger.getLogger(NewJFrame1.class.getName()).log(Level.SEVERE, null, e);
        } catch (InterruptedException ex) {
            System.out.println("4.1");
            Logger.getLogger(NewJFrame1.class.getName()).log(Level.SEVERE, null, ex);
        } finally { // kai sto telos kleinw oles tis anoixtes sundeseis
            try {
                if (bw != null)
                    bw.close();
                if (fis != null)
                    fis.close();
                if (bos != null)
                    bos.close();
                if(bis != null)
                    bis.close();
                if(this.s != null)
                    s.close();
                
            } catch (IOException e) {
                System.out.println("5");
                Logger.getLogger(main_client.class.getName()).log(Level.SEVERE, null, e);
            } 
        }
    }//GEN-LAST:event_jButton2ActionPerformed

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
            java.util.logging.Logger.getLogger(NewJFrame1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NewJFrame1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NewJFrame1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NewJFrame1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new NewJFrame1().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
