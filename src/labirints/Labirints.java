package labirints;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.*;

public class Labirints extends javax.swing.JFrame {
    int m,n; //labirinta rindu un kolonnu skaits
    int[] path;
    public Labirints() {
        initComponents();
    }
    private boolean[] apmeklets;
    private ArrayList<Integer>[] labirints;

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lasa = new javax.swing.JButton();
        zime = new javax.swing.JButton();
        panelis = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lasa.setText("Uzzīmēt labirintu");
        lasa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lasaActionPerformed(evt);
            }
        });

        zime.setText("Uzzīmēt ceļu");
        zime.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                zimeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelisLayout = new javax.swing.GroupLayout(panelis);
        panelis.setLayout(panelisLayout);
        panelisLayout.setHorizontalGroup(
            panelisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 625, Short.MAX_VALUE)
        );
        panelisLayout.setVerticalGroup(
            panelisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 267, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(776, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lasa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(zime, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(15, 15, 15))
            .addGroup(layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(panelis, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lasa)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(zime)
                .addGap(18, 18, 18)
                .addComponent(panelis, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(28, Short.MAX_VALUE))
        );

        fromText = new JLabel("From:");
        toText = new JLabel("To:");
        fromText.setBounds(730, 80, 40, 30);
        toText.setBounds(830, 80, 30, 30);

        this.add(fromText);
        this.add(toText);

        dzest = new JButton("Dzest celu");
        dzest.setBounds(780, 120, 120, 25);

        dzest.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dzestActionPerformed(evt);
            }
        });

        this.add(dzest);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void lasaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lasaActionPerformed
        try { 
            BufferedReader bufRdr  = new BufferedReader(new FileReader("labirints.txt")); 
            String failaRinda = bufRdr.readLine(); //nolasa no faila vienu teksta rindu
            String[] tmp=failaRinda.split(" ");    //sadala to atsevišķos gabaliņos, kurus saliek masīvā tmp
            m=Integer.parseInt(tmp[0]);            //iegūst rindu skaitu
            n=Integer.parseInt(tmp[1]);            //iegūst kolonnu skaitu
            System.out.println(m+" "+n);           //izvada rindu un kolonnu skaitu testa nolūkiem
            Graphics2D g=(Graphics2D)panelis.getGraphics();
            g.setStroke(new BasicStroke(5));

            javax.swing.SpinnerModel model1 = new SpinnerNumberModel(1, 1, n*m, 1);
            javax.swing.SpinnerModel model2 = new SpinnerNumberModel(1, 1, n*m, 1);
            from = new JSpinner();
            to = new JSpinner();
            from.setModel(model1);
            from.setBounds(770, 80, 50, 30);
            to.setModel(model2);
            to.setBounds(865, 80, 50, 30);

            this.add(from);
            this.add(to);


// jāraksta cikls, kas uzzīmēs m reiz n labirinta rūtiņas
            int y = 0;
            for (int i = 0; i <= m; i++) {
                g.drawLine(10,y + 10, 20*n + 10, y + 10);
                y += 20;
            }
            int x = 0;
            for (int j = 0; j <= n; j++) {
                g.drawLine(x + 10,10, x + 10, 20*m + 10);
                x += 20;
            }
// te jāraksta cikls, kas lasa katru no m reiz n faila rindām un izmanto datus, 
// lai dzēstu sienas starp kaimiņu rūtiņām. Dzēšana notiek, pārvelkot līniju fona krāsā
            //g.drawLine(20*(n-1),0, 20*n, 0);
            labirints = new ArrayList[m * n + 1];
            for (int i = 1; i < labirints.length; i++) {
                failaRinda = bufRdr.readLine();
                tmp = failaRinda.split(" ");
                labirints[i] = new ArrayList<Integer>();
                for (int j = 1; j < tmp.length; j++) {
                    labirints[i].add(Integer.parseInt(tmp[j]));
                }
            }


            g.setColor(panelis.getBackground());
            for (int i = 1; i < labirints.length; i++) {
                Iterator it = labirints[i].iterator();
                while (it.hasNext()){
                    int b = (int) it.next();
                    int r1 = (i - 1)/n;
                    int k1 = (i - 1)%n;
                    int r2 = (b - 1)/n;
                    int k2 = (b - 1)%n;
                    if (r1 == r2) {
                        int x1;
                        if (k1 < k2) {
                            x1 = (k1 + 1) * 20;
                            int y1 = r1 * 20;
                            int y2 = y1 + 20;
                            g.drawLine(x1 + 10, y1 + 15, x1 + 10, y2 + 5);
                        }
                    } else if (k1 == k2) {
                        int y1;
                        if (r1 < r2) {
                            y1 = (r1 + 1) * 20;
                            int x1 = k1 * 20;
                            int x2 = x1 + 20;
                            g.drawLine(x1 + 15, y1 + 10, x2 + 5, y1 + 10);
                        }
                    }
                }
            }
        } catch (IOException e) { 
            System.err.println(e); 
            System.exit(1); 
        }
        
    }//GEN-LAST:event_lasaActionPerformed

    private void BFS(int v, int fin) {
        Queue<Integer> que = new LinkedList<>();
        que.add(v);
        apmeklets[v] = true;
        while (!que.isEmpty()) {
            int p = que.remove();
            for (int i = 0; i < labirints[p].size(); i++) {
                int u = labirints[p].get(i);
                if (!apmeklets[u]) {
                    path[u] = p;
                    if (u == fin) {
                        return;
                    }
                    que.add(u);
                    apmeklets[u] = true;
                }
            }
        }
    }

    private void zimeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_zimeActionPerformed
        path = new int[m*n + 1];
        apmeklets = new boolean[n*m + 1];
        for (int i = 1; i <= n*m; i++) {
            apmeklets[i] = false;
        }
        int f, t;
        f = (int) from.getValue();
        t = (int) to.getValue();
        path[f] = -1;
        zimetCelu(f, t, true);

        }//GEN-LAST:event_zimeActionPerformed
    public void zimetCelu(int no,int uz, boolean color){
        mekletCelu(no,uz);
        Graphics g=panelis.getGraphics();
        if (color) {
            g.setColor(Color.red);
        } else {
            g.setColor(panelis.getBackground());
        }
        int x0 = ((uz - 1)%n + 1)*20;
        int y0 = ((uz - 1)/n + 1)*20;
        for (int i = path[uz]; i != -1; ) {
            int x1 = ((i - 1)%n + 1)*20;
            int y1 = ((i - 1)/n + 1)*20;
            g.drawLine(x0,y0, x1, y1);
            i = path[i];
            x0 = x1;
            y0 = y1;
        }
    }
    public void mekletCelu(int no, int uz){
        BFS(no, uz);
    }

    public void dzestActionPerformed(java.awt.event.ActionEvent evt) {
        int f, t;
        f = (int) from.getValue();
        t = (int) to.getValue();
        path[f] = -1;
        zimetCelu(f, t, false);

    }
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
            java.util.logging.Logger.getLogger(Labirints.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Labirints.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Labirints.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Labirints.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Labirints().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton lasa;
    private javax.swing.JPanel panelis;
    private javax.swing.JButton zime;
    private javax.swing.JSpinner from;
    private javax.swing.JLabel fromText;
    private javax.swing.JSpinner to;
    private javax.swing.JLabel toText;
    private javax.swing.JButton dzest;
    // End of variables declaration//GEN-END:variables
}
