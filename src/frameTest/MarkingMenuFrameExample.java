package frameTest;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import markingmenu.MarkingMenu;

/**
 *
 * @author nathan
 */
public class MarkingMenuFrameExample extends javax.swing.JFrame {

    private static final int DELTA_MOVE_POSITION = 10;

    private MarkingMenu markingMenu;

    public MarkingMenuFrameExample() {
        initComponents();
        setResizable(false);

        markingMenu = new MarkingMenu();


        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (markingMenu.isShowing()) {
                    markingMenu.initState();
                }
        }});

        initPositionLabelListener();
        initSizeLabelListener();
        initTextLabelListener();
        initColorLabelListener();
    }

    private void positionLabelMousePressed(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON3) {
            List<String> actions = new ArrayList();
            actions.add("A droite");
            actions.add("A gauche");
            actions.add("En bas");
            actions.add("En haut");

            markingMenu.setListActions(actions, e.getXOnScreen(), e.getYOnScreen());

            markingMenu.addMarkingMenuItemClick((int position) -> {
                int x = testLabel.getLocation().x;
                int y = testLabel.getLocation().y;

                switch (position) {
                    case 1:
                        testLabel.setLocation(x + DELTA_MOVE_POSITION, y);
                        break;
                    case 2:
                        testLabel.setLocation(x - DELTA_MOVE_POSITION, y);
                        break;
                    case 3:
                        testLabel.setLocation(x, y + DELTA_MOVE_POSITION);
                        break;
                    case 4:
                        testLabel.setLocation(x, y - DELTA_MOVE_POSITION);
                        break;
                }
            });
            markingMenu.mouseMarkingMenuPressed(e);
        }
    }

    private void textLabelMousePresseed(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON3) {
            List<String> actions = new ArrayList();
            actions.add("Franças");
            actions.add("Norvégien");
            actions.add("Bulgare");
            actions.add("Tibétain");

            markingMenu.setListActions(actions, e.getXOnScreen(), e.getYOnScreen());

            markingMenu.addMarkingMenuItemClick((int position) -> {
                switch (position) {
                    case 1:
                        testLabel.setText("Bonjour");
                        break;
                    case 2:
                        testLabel.setText("God dag");
                        break;
                    case 3:
                        testLabel.setText("добър ден");
                        break;
                    case 4:
                        testLabel.setText("Tashi delek");
                        break;
                }
            });
        }
    }

    private void colorLabelMousePressed(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON3) {
            List<String> actions = new ArrayList();
            actions.add("Rouge");
            actions.add("Vert");
            actions.add("Bleu");
            actions.add("Jaune");
            actions.add("Rose");
            actions.add("Noir");
            actions.add("Blanc");
            actions.add("Gris");

            markingMenu.setListActions(actions, e.getXOnScreen(), e.getYOnScreen());

            markingMenu.addMarkingMenuItemClick((int position) -> {
                switch (position) {
                    case 1:
                        testLabel.setForeground(Color.red);
                        break;
                    case 2:
                        testLabel.setForeground(Color.green);
                        break;
                    case 3:
                        testLabel.setForeground(Color.blue);
                        break;
                    case 4:
                        testLabel.setForeground(Color.yellow);
                        break;
                    case 5:
                        testLabel.setForeground(Color.pink);
                        break;
                    case 6:
                        testLabel.setForeground(Color.black);
                        break;
                    case 7:
                        testLabel.setForeground(Color.white);
                        break;
                    case 8:
                        testLabel.setForeground(Color.gray);
                        break;
                }
            });
        }
    }

    private void sizeLabelMousePressed(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON3) {
            List<String> actions = new ArrayList();
            actions.add("+2");
            actions.add("-2");

            markingMenu.setListActions(actions, e.getXOnScreen(), e.getYOnScreen());

            markingMenu.addMarkingMenuItemClick((int position) -> {
                Font labelFont = testLabel.getFont();
                String labelText = testLabel.getText();

                int stringWidth = testLabel.getFontMetrics(labelFont).stringWidth(labelText);
                int componentWidth = testLabel.getWidth();

                // Find out how much the font can grow in width.
                double widthRatio = (double) componentWidth / (double) stringWidth;

                int newFontSize = (int) (labelFont.getSize() * widthRatio);
                int componentHeight = testLabel.getHeight();

                // Pick a new font size so it will not be larger than the height of label.
                int fontSizeToUse = Math.min(newFontSize, componentHeight);

                // Set the label's font size to the newly determined size.
                switch (position) {
                    case 1:
                        testLabel.setFont(new Font(labelFont.getName(), Font.PLAIN, fontSizeToUse + 2));
                        break;
                    case 2:
                        testLabel.setFont(new Font(labelFont.getName(), Font.PLAIN, fontSizeToUse - 2));
                        break;
                }
            });
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        sizeLabel = new javax.swing.JLabel();
        colorLabel = new javax.swing.JLabel();
        positionLabel = new javax.swing.JLabel();
        textLabel = new javax.swing.JLabel();
        testLabel = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        sizeLabel.setFont(new java.awt.Font("Segoe UI Light", 0, 24)); // NOI18N
        sizeLabel.setText("Taille");

        colorLabel.setFont(new java.awt.Font("Segoe UI Light", 0, 24)); // NOI18N
        colorLabel.setText("Couleur");

        positionLabel.setFont(new java.awt.Font("Segoe UI Light", 0, 24)); // NOI18N
        positionLabel.setText("Position");

        textLabel.setFont(new java.awt.Font("Segoe UI Light", 0, 24)); // NOI18N
        textLabel.setText("Texte");

        testLabel.setFont(new java.awt.Font("Segoe UI Light", 0, 24)); // NOI18N
        testLabel.setText("Label de test");

        jLabel1.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
        jLabel1.setText("Veuillez faire un clic droit sur un des labels suivants : Taille, Couleur, Position, Texte");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(69, 69, 69)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addContainerGap(66, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(sizeLabel)
                            .addComponent(positionLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(colorLabel)
                            .addComponent(textLabel))
                        .addGap(93, 93, 93))))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(298, 298, 298)
                .addComponent(testLabel)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel1)
                .addGap(79, 79, 79)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(sizeLabel)
                    .addComponent(colorLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 112, Short.MAX_VALUE)
                .addComponent(testLabel)
                .addGap(126, 126, 126)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(positionLabel)
                    .addComponent(textLabel))
                .addGap(91, 91, 91))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(MarkingMenuFrameExample.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MarkingMenuFrameExample.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MarkingMenuFrameExample.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MarkingMenuFrameExample.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MarkingMenuFrameExample().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel colorLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel positionLabel;
    private javax.swing.JLabel sizeLabel;
    private javax.swing.JLabel testLabel;
    private javax.swing.JLabel textLabel;
    // End of variables declaration//GEN-END:variables

    private void initPositionLabelListener() {
        positionLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                positionLabelMousePressed(e);
                markingMenu.mouseMarkingMenuPressed(e);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                markingMenu.mouseMarkingMenuReleased(e, 4);
            }
        });

        positionLabel.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                markingMenu.mouseMarkingMenuDragged(e);
            }
        });
    }

    private void initSizeLabelListener() {
        sizeLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                sizeLabelMousePressed(e);
                markingMenu.mouseMarkingMenuPressed(e);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                markingMenu.mouseMarkingMenuReleased(e, 2);
            }

        });

        sizeLabel.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                markingMenu.mouseMarkingMenuDragged(e);
            }
        });
    }

    private void initColorLabelListener() {
        colorLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                colorLabelMousePressed(e);
                markingMenu.mouseMarkingMenuPressed(e);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                markingMenu.mouseMarkingMenuReleased(e, 8);
            }
        });
        
        colorLabel.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                markingMenu.mouseMarkingMenuDragged(e);
            }
        });
    }

    private void initTextLabelListener() {
        textLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                textLabelMousePresseed(e);
                markingMenu.mouseMarkingMenuPressed(e);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                markingMenu.mouseMarkingMenuReleased(e, 4);
            }
        });
        
        textLabel.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                markingMenu.mouseMarkingMenuDragged(e);
            }
        });
    }
}
