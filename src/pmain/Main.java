package pmain;
//VFinal 4
import Dessin.pOptionDessinMap;
import Sauvegarde.pSimulationDepuisFichier;
import com.raven.component.MenuLayout;
import com.raven.event.EventMenuSelected;
import com.raven.form.MainForm;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLayeredPane;
import javax.swing.SwingUtilities;
import net.miginfocom.swing.MigLayout;
import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTarget;
import org.jdesktop.animation.timing.TimingTargetAdapter;
import pAccueil.pAPropos;
import pAccueil.pAccueil;
import pGeneration.GenerationProcedurale;
import pGoogleMaps.pOptionGoogle;

public class Main extends javax.swing.JFrame {

    private final MigLayout layout;
    private final MainForm main;
    private final MenuLayout menu;
    private final Animator animator;
    private pOptionGoogle og;
    private pOptionDessinMap dm;
    private pSimulationDepuisFichier sf;
    private GenerationProcedurale gp;

    public Main() {
        initComponents();
        layout = new MigLayout("fill", "0[fill]0", "0[fill]0");
        main = new MainForm();
        menu = new MenuLayout();
        menu.getMenu().initMoving(Main.this);
        main.initMoving(Main.this);
        mainPanel.setLayer(menu, JLayeredPane.POPUP_LAYER);
        mainPanel.setLayout(layout);
        mainPanel.add(main);
        mainPanel.add(menu, "pos -215 0 100% 100%", 0);
        
        // ANIMATION DU MENU \\
        TimingTarget target = new TimingTargetAdapter() {
            @Override
            public void timingEvent(float fraction) {
                float x = (fraction * 215);
                float alpha;
                if (menu.isShow()) {
                    x = -x;
                    alpha = 0.5f - (fraction / 3);
                } else {
                    x -= 215;
                    alpha = fraction / 3;
                }
                layout.setComponentConstraints(menu, "pos " + (int) x + " 0 100% 100%");
                if (alpha < 0) {
                    alpha = 0;
                }
                menu.setAlpha(alpha);
                mainPanel.revalidate();
            }

            @Override
            public void end() {
                menu.setShow(!menu.isShow());
                if (!menu.isShow()) {
                    menu.setVisible(false);
                }
            }
        };
        animator = new Animator(200, target);
        
        menu.addMouseListener(new MouseAdapter() {
            /*
                Permet de rentrer dans le menu avec l'animation
            */
            @Override
            public void mousePressed(MouseEvent me) {
                if (SwingUtilities.isLeftMouseButton(me)) {
                    if (!animator.isRunning()) {
                        if (menu.isShow()) {
                            animator.start();
                        }
                    }
                }
            }
        });
        
        // ACTIONS BOUTONS \\
        
          // GESTION DU MENU BURGER \\
        main.addEventMenu(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (!animator.isRunning()) {
                    if (!menu.isShow()) {
                        menu.setVisible(true);
                        animator.start();
                    }
                }
            }
        });
        menu.getMenu().addEventMenuSelected(new EventMenuSelected() {
            @Override
            public void selected(int index) {
                if (index == 0) {
                    gp = new GenerationProcedurale();
                    main.show(gp);
                    animator.start();
                } else if (index == 1) {
                    sf = new pSimulationDepuisFichier(); 
                    main.show(sf);
                    animator.start();
                } else if (index == 2) {
                    dm = new pOptionDessinMap();
                    main.show(dm);
                    animator.start();
                } else if (index == 3) {
                    og = new pOptionGoogle();
                    main.show(og);
                    animator.start();
                }
            }
        });
          // GESTION DES AUTRES BOUTON \\
        main.addEventHome(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                main.show(new pAccueil());
            }
        });
        main.addEventAbout(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                main.show(new pAPropos());
            }
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel = new javax.swing.JLayeredPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Projet Simulation Feu de Foret - Maxime INNOCENTI & Antoine HENRIET");
        setMaximumSize(new java.awt.Dimension(1300, 740));
        setMinimumSize(new java.awt.Dimension(1300, 740));
        setUndecorated(true);
        setResizable(false);

        mainPanel.setBackground(new java.awt.Color(250, 250, 250));
        mainPanel.setMaximumSize(new java.awt.Dimension(1300, 740));
        mainPanel.setMinimumSize(new java.awt.Dimension(1300, 740));
        mainPanel.setOpaque(true);

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1300, Short.MAX_VALUE)
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 740, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Main().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLayeredPane mainPanel;
    // End of variables declaration//GEN-END:variables
}
