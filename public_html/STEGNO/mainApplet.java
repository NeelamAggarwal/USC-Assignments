package tryingproject;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.image.*;
import javax.swing.*;
import java.awt.*;

public class mainApplet extends javax.swing.JApplet {

    Image img, fimg, picture, mimg;
    PlugInFilter pif;
    int pixels[];
    int mpixels[] = new int[1000 * 1000];
    int iw, ih, w, h;
    int R[] = new int[1000 * 1000];
    int G[] = new int[1000 * 1000];
    int B[] = new int[1000 * 1000];
    int hist[] = new int[256];
    int hist_m[] = new int[256];
    int HistR[] = new int[256];
    int HistG[] = new int[256];
    int HistB[] = new int[256];
    int max_hist, max_hist_m, maxR, maxG, maxB;
    float mean_bef, var_bef, mean_aft, var_aft, MeanR, MeanB, MeanG, VarR, VarG, VarB;
    int rr1[] = new int[1000 * 1000];
    int gg1[] = new int[1000 * 1000];
    int bb1[] = new int[1000 * 1000];
    int endptr[] = new int[8];
    int remainder[] = new int[8];
    int rr[] = new int[1000 * 1000];
    int gg[] = new int[1000 * 1000];
    int ac[] = new int[1000 * 1000];
    int bb[] = new int[1000 * 1000];
    int len;
    String ns;
    int digit;
    int convertednumber1, convertednumber2, convertednumber3;

    public void init() {
        try {
            java.awt.EventQueue.invokeAndWait(new Runnable() {

                public void run() {
                    initComponents();
                }
            });
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        MenuBarFrame = new javax.swing.JInternalFrame();
        displayPanel = new javax.swing.JPanel();
        LoadImagePanel = new javax.swing.JPanel();
        jLabelImage1 = new javax.swing.JLabel();
        jTextFieldAddress1 = new javax.swing.JTextField();
        jLabelPath1 = new javax.swing.JLabel();
        OperationsPanel = new javax.swing.JPanel();
        jLabelImage = new javax.swing.JLabel();
        jLabelPath = new javax.swing.JLabel();
        jTextFieldAddress = new javax.swing.JTextField();
        jLabelMod_Img = new javax.swing.JLabel();
        EncodePanel = new javax.swing.JPanel();
        jLabelEncodeImage = new javax.swing.JLabel();
        jLabelHistRed = new javax.swing.JLabel();
        jLabelHistGreen = new javax.swing.JLabel();
        jLabelHistBlue = new javax.swing.JLabel();
        jLabelMeanRed = new javax.swing.JLabel();
        jLabelMeanGreen = new javax.swing.JLabel();
        jLabelMeanBlue = new javax.swing.JLabel();
        jLabelVarRed = new javax.swing.JLabel();
        jLabelVarGreen = new javax.swing.JLabel();
        jLabelVarBlue = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jButtonEncode = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        EncodeButtonClickPanel = new javax.swing.JPanel();
        jLabelBefore = new javax.swing.JLabel();
        jLabelAfter = new javax.swing.JLabel();
        jLabelImageBefore = new javax.swing.JLabel();
        jLabelImageAfter = new javax.swing.JLabel();
        jLabelHistBefore = new javax.swing.JLabel();
        jLabelHistAfter = new javax.swing.JLabel();
        jLabelMeanBefore = new javax.swing.JLabel();
        jLabelVarianceBefore = new javax.swing.JLabel();
        jLabelMeanAfter = new javax.swing.JLabel();
        jLabelVarianceAfter = new javax.swing.JLabel();
        DecodePanel = new javax.swing.JPanel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenuFile = new javax.swing.JMenu();
        jMenuItemLoad = new javax.swing.JMenuItem();
        jMenuItemExit = new javax.swing.JMenuItem();
        jMenuOperations = new javax.swing.JMenu();
        jMenuItemReset = new javax.swing.JMenuItem();
        jMenuItemBlur = new javax.swing.JMenuItem();
        jMenuItemContrast = new javax.swing.JMenuItem();
        jMenuItemGrayScale = new javax.swing.JMenuItem();
        jMenuItemInvert = new javax.swing.JMenuItem();
        jMenuItemSharpen = new javax.swing.JMenuItem();
        jMenuEncode = new javax.swing.JMenu();
        jMenuDecode = new javax.swing.JMenu();

        addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                formMouseMoved(evt);
            }
        });

        MenuBarFrame.setVisible(true);

        displayPanel.setLayout(new java.awt.CardLayout());

        LoadImagePanel.setLayout(null);

        jLabelImage1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelImage1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 255)), "Image", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Comic Sans MS", 3, 18), new java.awt.Color(0, 0, 255))); // NOI18N
        jLabelImage1.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jLabelImage1MouseMoved(evt);
            }
        });
        LoadImagePanel.add(jLabelImage1);
        jLabelImage1.setBounds(150, 10, 670, 530);

        jTextFieldAddress1.setFont(new java.awt.Font("Edwardian Script ITC", 0, 24));
        jTextFieldAddress1.setText("Address");
        jTextFieldAddress1.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 255)));
        LoadImagePanel.add(jTextFieldAddress1);
        jTextFieldAddress1.setBounds(210, 540, 590, 31);

        jLabelPath1.setFont(new java.awt.Font("Comic Sans MS", 1, 12));
        jLabelPath1.setText("Path");
        jLabelPath1.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 255)));
        LoadImagePanel.add(jLabelPath1);
        jLabelPath1.setBounds(160, 540, 40, 30);

        displayPanel.add(LoadImagePanel, "fourthcard");

        OperationsPanel.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                OperationsPanelMouseMoved(evt);
            }
        });

        jLabelImage.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 255)), "Original Image", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Comic Sans MS", 3, 18), new java.awt.Color(0, 0, 255))); // NOI18N
        jLabelImage.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jLabelImageMouseMoved(evt);
            }
        });

        jLabelPath.setFont(new java.awt.Font("Comic Sans MS", 1, 12));
        jLabelPath.setText("Path");
        jLabelPath.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 255)));

        jTextFieldAddress.setFont(new java.awt.Font("Edwardian Script ITC", 0, 24));
        jTextFieldAddress.setText("Address");
        jTextFieldAddress.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 255)));

        jLabelMod_Img.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 255)), "Modified Image", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Comic Sans MS", 3, 18), new java.awt.Color(0, 0, 255))); // NOI18N

        javax.swing.GroupLayout OperationsPanelLayout = new javax.swing.GroupLayout(OperationsPanel);
        OperationsPanel.setLayout(OperationsPanelLayout);
        OperationsPanelLayout.setHorizontalGroup(
            OperationsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(OperationsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(OperationsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(OperationsPanelLayout.createSequentialGroup()
                        .addComponent(jLabelPath, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldAddress, javax.swing.GroupLayout.DEFAULT_SIZE, 609, Short.MAX_VALUE))
                    .addGroup(OperationsPanelLayout.createSequentialGroup()
                        .addComponent(jLabelImage, javax.swing.GroupLayout.PREFERRED_SIZE, 461, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelMod_Img, javax.swing.GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE)))
                .addContainerGap())
        );
        OperationsPanelLayout.setVerticalGroup(
            OperationsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(OperationsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(OperationsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabelMod_Img, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelImage, javax.swing.GroupLayout.DEFAULT_SIZE, 431, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(OperationsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabelPath, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextFieldAddress, javax.swing.GroupLayout.PREFERRED_SIZE, 30, Short.MAX_VALUE))
                .addGap(39, 39, 39))
        );

        displayPanel.add(OperationsPanel, "firstcard");

        jLabelEncodeImage.setBorder(javax.swing.BorderFactory.createTitledBorder("EncodeImage"));

        jLabelHistRed.setBorder(javax.swing.BorderFactory.createTitledBorder("HistRed"));
        jLabelHistRed.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelHistRedMouseClicked(evt);
            }
        });

        jLabelHistGreen.setBorder(javax.swing.BorderFactory.createTitledBorder("HistGreen"));
        jLabelHistGreen.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelHistGreenMouseClicked(evt);
            }
        });

        jLabelHistBlue.setBorder(javax.swing.BorderFactory.createTitledBorder("HistBlue"));
        jLabelHistBlue.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelHistBlueMouseClicked(evt);
            }
        });

        jLabelMeanRed.setBorder(javax.swing.BorderFactory.createTitledBorder("MeanRed"));

        jLabelMeanGreen.setBorder(javax.swing.BorderFactory.createTitledBorder("MeanGreen"));

        jLabelMeanBlue.setBorder(javax.swing.BorderFactory.createTitledBorder("MeanBlue"));

        jLabelVarRed.setBorder(javax.swing.BorderFactory.createTitledBorder("Variance_Red"));

        jLabelVarGreen.setBorder(javax.swing.BorderFactory.createTitledBorder("Variance_Green"));

        jLabelVarBlue.setBorder(javax.swing.BorderFactory.createTitledBorder("Variance_Blue"));

        jLabel1.setFont(new java.awt.Font("Comic Sans MS", 1, 11));
        jLabel1.setText("String to be encoded:");

        jButtonEncode.setFont(new java.awt.Font("Comic Sans MS", 1, 11));
        jButtonEncode.setText("Encode");
        jButtonEncode.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 255)));
        jButtonEncode.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButtonEncodeMouseClicked(evt);
            }
        });
        jButtonEncode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEncodeActionPerformed(evt);
            }
        });

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        javax.swing.GroupLayout EncodePanelLayout = new javax.swing.GroupLayout(EncodePanel);
        EncodePanel.setLayout(EncodePanelLayout);
        EncodePanelLayout.setHorizontalGroup(
            EncodePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(EncodePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(EncodePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(EncodePanelLayout.createSequentialGroup()
                        .addGroup(EncodePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(EncodePanelLayout.createSequentialGroup()
                                .addGroup(EncodePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabelMeanBlue, javax.swing.GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE)
                                    .addComponent(jLabelMeanRed, javax.swing.GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE)
                                    .addComponent(jLabelMeanGreen, javax.swing.GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(EncodePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabelVarBlue, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                                    .addComponent(jLabelVarGreen, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                                    .addComponent(jLabelVarRed, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE))
                                .addGap(18, 18, 18))
                            .addGroup(EncodePanelLayout.createSequentialGroup()
                                .addComponent(jLabelEncodeImage, javax.swing.GroupLayout.DEFAULT_SIZE, 301, Short.MAX_VALUE)
                                .addGap(18, 18, 18)))
                        .addGroup(EncodePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabelHistBlue, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 336, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(EncodePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabelHistGreen, javax.swing.GroupLayout.PREFERRED_SIZE, 336, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabelHistRed, javax.swing.GroupLayout.PREFERRED_SIZE, 336, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(EncodePanelLayout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 443, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonEncode, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)))
                .addContainerGap())
        );
        EncodePanelLayout.setVerticalGroup(
            EncodePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(EncodePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(EncodePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(EncodePanelLayout.createSequentialGroup()
                        .addComponent(jLabelHistRed, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelHistGreen, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelHistBlue, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(EncodePanelLayout.createSequentialGroup()
                        .addComponent(jLabelEncodeImage, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(EncodePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelMeanRed, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelVarRed, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(EncodePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelVarGreen)
                            .addComponent(jLabelMeanGreen, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(EncodePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelMeanBlue)
                            .addComponent(jLabelVarBlue, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGroup(EncodePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(EncodePanelLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(EncodePanelLayout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(jButtonEncode, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(EncodePanelLayout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        displayPanel.add(EncodePanel, "thirdcard");

        jLabelBefore.setFont(new java.awt.Font("Comic Sans MS", 1, 11));
        jLabelBefore.setText("IMAGE BEFORE MODIFICATION");
        jLabelBefore.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 255)));

        jLabelAfter.setFont(new java.awt.Font("Comic Sans MS", 1, 11));
        jLabelAfter.setText("IMAGE AFTER MODIFICATION");
        jLabelAfter.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 255)));

        jLabelImageBefore.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 255)), "Image Before", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(0, 0, 255))); // NOI18N
        jLabelImageBefore.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelImageBeforeMouseClicked(evt);
            }
        });

        jLabelImageAfter.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 255)), "Image After", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(0, 0, 255))); // NOI18N
        jLabelImageAfter.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelImageAfterMouseClicked(evt);
            }
        });

        jLabelHistBefore.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 255)), "Histogram Before", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(0, 0, 255))); // NOI18N
        jLabelHistBefore.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelHistBeforeMouseClicked(evt);
            }
        });

        jLabelHistAfter.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelHistAfter.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 255)), "Histogram After", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(0, 0, 255))); // NOI18N
        jLabelHistAfter.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelHistAfterMouseClicked(evt);
            }
        });

        jLabelMeanBefore.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 255)), "Mean Before", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(0, 0, 255))); // NOI18N

        jLabelVarianceBefore.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 255)), "Variance Before", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(0, 0, 255))); // NOI18N

        jLabelMeanAfter.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 255)), "Mean After", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(0, 0, 255))); // NOI18N

        jLabelVarianceAfter.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 255)), "Variance After", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(0, 0, 255))); // NOI18N

        javax.swing.GroupLayout EncodeButtonClickPanelLayout = new javax.swing.GroupLayout(EncodeButtonClickPanel);
        EncodeButtonClickPanel.setLayout(EncodeButtonClickPanelLayout);
        EncodeButtonClickPanelLayout.setHorizontalGroup(
            EncodeButtonClickPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(EncodeButtonClickPanelLayout.createSequentialGroup()
                .addGroup(EncodeButtonClickPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(EncodeButtonClickPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabelMeanBefore, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabelVarianceBefore, javax.swing.GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE)
                        .addGap(10, 10, 10))
                    .addGroup(EncodeButtonClickPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabelHistBefore, javax.swing.GroupLayout.DEFAULT_SIZE, 327, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, EncodeButtonClickPanelLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(EncodeButtonClickPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabelImageBefore, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 323, Short.MAX_VALUE)
                            .addComponent(jLabelBefore, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 323, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addGroup(EncodeButtonClickPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabelImageAfter, javax.swing.GroupLayout.DEFAULT_SIZE, 322, Short.MAX_VALUE)
                    .addComponent(jLabelHistAfter, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 322, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, EncodeButtonClickPanelLayout.createSequentialGroup()
                        .addComponent(jLabelMeanAfter, javax.swing.GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelVarianceAfter, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabelAfter, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 322, Short.MAX_VALUE))
                .addContainerGap())
        );
        EncodeButtonClickPanelLayout.setVerticalGroup(
            EncodeButtonClickPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(EncodeButtonClickPanelLayout.createSequentialGroup()
                .addGroup(EncodeButtonClickPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelBefore, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelAfter, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(EncodeButtonClickPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabelImageAfter, javax.swing.GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE)
                    .addComponent(jLabelImageBefore, javax.swing.GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(EncodeButtonClickPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabelHistAfter, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelHistBefore, javax.swing.GroupLayout.DEFAULT_SIZE, 237, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(EncodeButtonClickPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelMeanBefore, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelVarianceAfter, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelMeanAfter, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelVarianceBefore, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        displayPanel.add(EncodeButtonClickPanel, "secondcard");

        javax.swing.GroupLayout DecodePanelLayout = new javax.swing.GroupLayout(DecodePanel);
        DecodePanel.setLayout(DecodePanelLayout);
        DecodePanelLayout.setHorizontalGroup(
            DecodePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 675, Short.MAX_VALUE)
        );
        DecodePanelLayout.setVerticalGroup(
            DecodePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 522, Short.MAX_VALUE)
        );

        displayPanel.add(DecodePanel, "fifthcard");

        jMenuBar1.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 255)));

        jMenuFile.setText("File");
        jMenuFile.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenuFileMouseClicked(evt);
            }
        });

        jMenuItemLoad.setText("Load");
        jMenuItemLoad.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenuItemLoadMouseClicked(evt);
            }
        });
        jMenuItemLoad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemLoadActionPerformed(evt);
            }
        });
        jMenuFile.add(jMenuItemLoad);

        jMenuItemExit.setText("Exit");
        jMenuFile.add(jMenuItemExit);

        jMenuBar1.add(jMenuFile);

        jMenuOperations.setText("Image operations");
        jMenuOperations.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenuOperationsMouseClicked(evt);
            }
        });

        jMenuItemReset.setText("Reset");
        jMenuItemReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemResetActionPerformed(evt);
            }
        });
        jMenuOperations.add(jMenuItemReset);

        jMenuItemBlur.setText("Blur");
        jMenuItemBlur.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemBlurActionPerformed(evt);
            }
        });
        jMenuOperations.add(jMenuItemBlur);

        jMenuItemContrast.setText("Contrast");
        jMenuItemContrast.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemContrastActionPerformed(evt);
            }
        });
        jMenuOperations.add(jMenuItemContrast);

        jMenuItemGrayScale.setText("GrayScale");
        jMenuItemGrayScale.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemGrayScaleActionPerformed(evt);
            }
        });
        jMenuOperations.add(jMenuItemGrayScale);

        jMenuItemInvert.setText("Invert");
        jMenuItemInvert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemInvertActionPerformed(evt);
            }
        });
        jMenuOperations.add(jMenuItemInvert);

        jMenuItemSharpen.setText("Sharpen");
        jMenuItemSharpen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemSharpenActionPerformed(evt);
            }
        });
        jMenuOperations.add(jMenuItemSharpen);

        jMenuBar1.add(jMenuOperations);

        jMenuEncode.setText("Encode");
        jMenuEncode.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenuEncodeMouseClicked(evt);
            }
        });
        jMenuBar1.add(jMenuEncode);

        jMenuDecode.setText("Decode");
        jMenuDecode.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenuDecodeMouseClicked(evt);
            }
        });
        jMenuDecode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuDecodeActionPerformed(evt);
            }
        });
        jMenuBar1.add(jMenuDecode);

        MenuBarFrame.setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout MenuBarFrameLayout = new javax.swing.GroupLayout(MenuBarFrame.getContentPane());
        MenuBarFrame.getContentPane().setLayout(MenuBarFrameLayout);
        MenuBarFrameLayout.setHorizontalGroup(
            MenuBarFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MenuBarFrameLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(displayPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 675, Short.MAX_VALUE)
                .addContainerGap())
        );
        MenuBarFrameLayout.setVerticalGroup(
            MenuBarFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MenuBarFrameLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(displayPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 522, Short.MAX_VALUE)
                .addContainerGap())
        );

        getContentPane().add(MenuBarFrame, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    public void start() {

        Dimension d = getSize();
        w = d.width;
        h = d.height;

        int p[] = new int[1000 * 1000];

        try {
            getAppletContext().showStatus("Image now loading...");
            MediaTracker tracker = new MediaTracker(this);

            try {
                picture = ImageIO.read(new File("C:\\Users\\user\\Desktop\\Untitled.png")); //C:\\Documents and Settings\\NiKiL\\Desktop\\ncolaba.jpg"));
            } catch (IOException ex) {
            }
            tracker.addImage(picture, 0);
            tracker.waitForID(0);
            iw = picture.getWidth(null);
            ih = picture.getHeight(null);
            pixels = new int[iw * ih];
            PixelGrabber pg = new PixelGrabber(picture, 0, 0, iw, ih, pixels, 0, iw);
            pg.grabPixels();
        } catch (InterruptedException e) {
        }

        //calculate RGB
        for (int i = 0; i < iw * ih; i++) {
            p[i] = pixels[i];
            R[i] = 0xff & (p[i] >> 16);
            G[i] = 0xff & (p[i] >> 8);
            B[i] = 0xff & (p[i]);
            rr1[i] = R[i];
            gg1[i] = G[i];
            bb1[i] = B[i];
            int y = (int) (.33 * R[i] + .56 * G[i] + .11 * B[i]);
            hist[y]++;
            mean_bef += y;
            MeanR += R[i];
            MeanB += B[i];
            MeanG += G[i];
            HistR[R[i]]++;
            HistG[G[i]]++;
            HistB[B[i]]++;
        }

        // mean
        mean_bef = mean_bef / (iw * ih);
        MeanR = MeanR / (iw * ih);
        MeanG = MeanG / (iw * ih);
        MeanB = MeanB / (iw * ih);


        // Variance
        for (int i = 0; i < iw * ih; i++) {
            R[i] = 0xff & (p[i] >> 16);
            G[i] = 0xff & (p[i] >> 8);
            B[i] = 0xff & (p[i]);
            int y = (int) (.33 * R[i] + .56 * G[i] + .11 * B[i]);
            var_bef += (y - mean_bef) * (y - mean_bef);
            VarR += (R[i] - MeanR) * (R[i] - MeanR);
            VarG += (G[i] - MeanG) * (G[i] - MeanG);
            VarB += (B[i] - MeanB) * (B[i] - MeanB);
        }
        var_bef = var_bef / (iw * ih);
        VarR = VarR / (iw * ih);
        VarG = VarG / (iw * ih);
        VarB = VarB / (iw * ih);

        // maximum of histogram
        for (int i = 0; i < 256; i++) {
            if (hist[i] > max_hist) {
                max_hist = hist[i];
            }
        }
        for (int i = 0; i < 256; i++) {
            if (HistR[i] > maxR) {
                maxR = HistR[i];
            }
        }
        for (int i = 0; i < 256; i++) {
            if (HistG[i] > maxG) {
                maxG = HistG[i];
            }
        }
        for (int i = 0; i < 256; i++) {
            if (HistB[i] > maxB) {
                maxB = HistB[i];
            }
        }
        showStatus("    ");

    }
    private void jMenuFileMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuFileMouseClicked
        CardLayout card = (CardLayout) (displayPanel.getLayout());
        card.show(displayPanel, "fourthcard");
    }//GEN-LAST:event_jMenuFileMouseClicked

    private void jMenuOperationsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuOperationsMouseClicked
        CardLayout card = (CardLayout) (displayPanel.getLayout());
        card.show(displayPanel, "firstcard");
    }//GEN-LAST:event_jMenuOperationsMouseClicked

    private void jMenuEncodeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuEncodeMouseClicked
        CardLayout card = (CardLayout) (displayPanel.getLayout());
        card.show(displayPanel, "thirdcard");

        this.jLabelEncodeImage.setIcon(new ImageIcon(picture));
        this.jLabelMeanRed.setText("" + MeanR);
        this.jLabelVarRed.setText("" + VarR);
        this.jLabelMeanGreen.setText("" + MeanG);
        this.jLabelVarGreen.setText("" + VarG);
        this.jLabelMeanBlue.setText("" + MeanB);
        this.jLabelVarBlue.setText("" + VarB);

    }//GEN-LAST:event_jMenuEncodeMouseClicked

    private void jLabelImageMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelImageMouseMoved

        int xpos = evt.getX();
        int ypos = evt.getY();

        showStatus("THE RGB VALUE OF IMAGE IS: RED:" + R[xpos * ypos]
                + "  GREEN:" + G[xpos * ypos] + "  BLUE:" + B[xpos * ypos]
                + "  AT POSITION X:" + xpos + " , Y:" + ypos);
    }//GEN-LAST:event_jLabelImageMouseMoved

    private void OperationsPanelMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_OperationsPanelMouseMoved
        showStatus(" ");
    }//GEN-LAST:event_OperationsPanelMouseMoved

    private void jMenuItemLoadMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItemLoadMouseClicked
    }//GEN-LAST:event_jMenuItemLoadMouseClicked

    private void jMenuItemLoadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemLoadActionPerformed
        JFileChooser fc = new JFileChooser();
        fc.showOpenDialog(null);
        this.jTextFieldAddress1.setText(fc.getSelectedFile().getPath());
        img = new ImageIcon(this.jTextFieldAddress1.getText()).getImage();
        this.jLabelImage1.setIcon(new ImageIcon(img));
    }//GEN-LAST:event_jMenuItemLoadActionPerformed

    private void jMenuItemBlurActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemBlurActionPerformed
        try {
            pif = (PlugInFilter) Class.forName("tryingproject.Blur").newInstance();
            fimg = pif.filter(this, img);
            this.jLabelImage.setIcon(new ImageIcon(img));
            this.jLabelMod_Img.setIcon(new ImageIcon(fimg));
            String address = this.jTextFieldAddress1.getText();
            jTextFieldAddress.setText(address);

        } catch (ClassNotFoundException e) {
            showStatus("Blur not found");
        } catch (InstantiationException e) {
            showStatus("could't new: Blur");
        } catch (IllegalAccessException e) {
            showStatus("no access: Blur");
        }
    }//GEN-LAST:event_jMenuItemBlurActionPerformed

    private void jMenuItemResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemResetActionPerformed
        this.jLabelImage.setIcon(new ImageIcon(this.jTextFieldAddress.getText()));
    }//GEN-LAST:event_jMenuItemResetActionPerformed

    private void jLabelImageBeforeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelImageBeforeMouseClicked
        this.jLabelImageBefore.setIcon(new ImageIcon(picture));
        this.jLabelMeanBefore.setText("" + mean_bef);
        this.jLabelVarianceBefore.setText("" + var_bef);

    }//GEN-LAST:event_jLabelImageBeforeMouseClicked

    private void jLabelHistBeforeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelHistBeforeMouseClicked

        Graphics g1 = jLabelHistBefore.getGraphics();
        int p, q;
        p = jLabelHistBefore.getWidth();
        q = jLabelHistBefore.getHeight();
        int x = (p - 256) / 2;
        int lasty = q - q * hist[0] / max_hist;
        for (int i = 0; i < 256; i++, x++) {
            int y = q - q * hist[i] / max_hist;
            g1.setColor(new Color(i, i, i));
            g1.fillRect(x, y, 1, q);
            g1.setColor(Color.red);
            g1.drawLine(x - 2, lasty, x, y);
            lasty = y;
        }
    }//GEN-LAST:event_jLabelHistBeforeMouseClicked

    private void jMenuItemContrastActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemContrastActionPerformed
        try {
            pif = (PlugInFilter) Class.forName("tryingproject.Contrast").newInstance();
            fimg = pif.filter(this, img);
            this.jLabelMod_Img.setIcon(new ImageIcon(fimg));
            this.jLabelImage.setIcon(new ImageIcon(img));

        } catch (ClassNotFoundException e) {
            showStatus("Contrast not found");
        } catch (InstantiationException e) {
            showStatus("could't new: Contrast");
        } catch (IllegalAccessException e) {
            showStatus("no access: Contrast");
        }
    }//GEN-LAST:event_jMenuItemContrastActionPerformed

    private void jMenuItemGrayScaleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemGrayScaleActionPerformed
        try {
            pif = (PlugInFilter) Class.forName("tryingproject.GrayScale").newInstance();
            fimg = pif.filter(this, img);
            this.jLabelMod_Img.setIcon(new ImageIcon(fimg));
            this.jLabelImage.setIcon(new ImageIcon(img));

        } catch (ClassNotFoundException e) {
            showStatus("Grayscale not found");
        } catch (InstantiationException e) {
            showStatus("could't new: Grayscale");
        } catch (IllegalAccessException e) {
            showStatus("no access: Grayscale");
        }
    }//GEN-LAST:event_jMenuItemGrayScaleActionPerformed

    private void jMenuItemInvertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemInvertActionPerformed
        try {
            pif = (PlugInFilter) Class.forName("tryingproject.Invert").newInstance();
            fimg = pif.filter(this, img);
            this.jLabelMod_Img.setIcon(new ImageIcon(fimg));
            this.jLabelImage.setIcon(new ImageIcon(img));

        } catch (ClassNotFoundException e) {
            showStatus("Invert not found");
        } catch (InstantiationException e) {
            showStatus("could't new: Invert");
        } catch (IllegalAccessException e) {
            showStatus("no access: Invert");
        }
    }//GEN-LAST:event_jMenuItemInvertActionPerformed

    private void jMenuItemSharpenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemSharpenActionPerformed
        try {
            pif = (PlugInFilter) Class.forName("tryingproject.Sharpen").newInstance();
            fimg = pif.filter(this, img);
            this.jLabelMod_Img.setIcon(new ImageIcon(fimg));
            this.jLabelImage.setIcon(new ImageIcon(img));

        } catch (ClassNotFoundException e) {
            showStatus("Sharpen not found");
        } catch (InstantiationException e) {
            showStatus("could't new: Sharpen");
        } catch (IllegalAccessException e) {
            showStatus("no access: Sharpen");
        }
    }//GEN-LAST:event_jMenuItemSharpenActionPerformed

    private void jMenuDecodeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuDecodeMouseClicked
CardLayout card=(CardLayout) displayPanel.getLayout();
card.show(displayPanel,"fifthcard");


    }//GEN-LAST:event_jMenuDecodeMouseClicked

    private void jLabelHistRedMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelHistRedMouseClicked

        Graphics g1 = jLabelHistRed.getGraphics();
        int p, q;
        p = jLabelHistRed.getWidth();
        q = jLabelHistRed.getHeight();
        int x = (p - 256) / 2;
        int lasty = q - q * HistR[0] / maxR;
        for (int i = 0; i < 256; i++, x++) {
            int y = q - q * HistR[i] / maxR;
            g1.setColor(new Color(i, i, i));
            g1.fillRect(x, y, 1, q);
            g1.setColor(Color.RED);
            g1.drawLine(x - 2, lasty, x, y);
            lasty = y;
        }


    }//GEN-LAST:event_jLabelHistRedMouseClicked

    private void jLabelHistGreenMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelHistGreenMouseClicked

        Graphics g1 = jLabelHistGreen.getGraphics();
        int p, q;
        p = jLabelHistGreen.getWidth();
        q = jLabelHistGreen.getHeight();
        int x = (p - 256) / 2;
        int lasty = q - q * HistG[0] / maxG;
        for (int i = 0; i < 256; i++, x++) {
            int y = q - q * HistG[i] / maxG;
            g1.setColor(new Color(i, i, i));
            g1.fillRect(x, y, 1, q);
            g1.setColor(Color.RED);
            g1.drawLine(x - 2, lasty, x, y);
            lasty = y;
        }


    }//GEN-LAST:event_jLabelHistGreenMouseClicked

    private void jLabelHistBlueMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelHistBlueMouseClicked

        Graphics g1 = jLabelHistBlue.getGraphics();
        int p, q;
        p = jLabelHistBlue.getWidth();
        q = jLabelHistBlue.getHeight();
        int x = (p - 256) / 2;
        int lasty = q - q * HistB[0] / maxB;
        for (int i = 0; i < 256; i++, x++) {
            int y = q - q * HistB[i] / maxB;
            g1.setColor(new Color(i, i, i));
            g1.fillRect(x, y, 1, q);
            g1.setColor(Color.RED);
            g1.drawLine(x - 2, lasty, x, y);
            lasty = y;
        }


    }//GEN-LAST:event_jLabelHistBlueMouseClicked

    private void jMenuDecodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuDecodeActionPerformed
    }//GEN-LAST:event_jMenuDecodeActionPerformed

    private void jButtonEncodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEncodeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonEncodeActionPerformed

    private void jLabelImage1MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelImage1MouseMoved
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabelImage1MouseMoved

    private void formMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseMoved
    }//GEN-LAST:event_formMouseMoved

    private void jButtonEncodeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonEncodeMouseClicked

        ns = jTextArea1.getText();
        len = ns.length();
        for (int i = 0; i < len; i++) {
            int val = (int) ns.charAt(i);
            System.out.println("ascii is " + val);
            byte4(val, i);
            System.out.print("\n");
            byte1(R[i], i);
            System.out.print("\n");
            byte2(G[i], i);
            byte3(B[i], i);
        }


        JFrame frame = new JFrame("Message Box");
        frame.setBounds(400, 300, 200, 100);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JOptionPane.showMessageDialog(frame, "Message has been encoded");

        CardLayout card = (CardLayout) (displayPanel.getLayout());
        card.show(displayPanel, "secondcard");


    }

    public void byte1(int r1, int f) {
        int i = 0;

        for (int ii = 0; ii <= 7; ii++) {
            remainder[ii] = 0;
        }

        while (r1 > 0) {
            remainder[i] = r1 % 2;
            r1 = r1 / 2;
            i++;
        }

        for (int y = 0, z = 7; (y < 8) && (z >= 0); y++, z--) {
            rr[z] = remainder[y];
        }

        for (int p2 = 0; p2 < 8; p2++) {
            System.out.print(rr[p2]);
        }

        for (int u = 0; u < 3; u++) {
            rr[5 + u] = ac[u];
        }

        System.out.print("the modified bits are");
        for (int p2 = 0; p2 <= 7; p2++) {
            System.out.print(rr[p2]);
        }
        System.out.println("the actual number are" + R[f]);
        int gt1 = binary1();
        System.out.println("the modified number are" + gt1);
        rr1[f] = gt1;

    }

    public void byte2(int r1, int f) {
        int i = 0;
        for (int ii = 0; ii <= 7; ii++) {
            remainder[ii] = 0;
        }
        while (r1 > 0) {
            remainder[i] = r1 % 2;
            r1 = r1 / 2;
            i++;
        }

        for (int y = 0, z = 7; (y < 8) && (z >= 0); y++, z--) {
            gg[z] = remainder[y];
        }

        for (int p2 = 0; p2 < 8; p2++) {
            System.out.print(gg[p2]);
        }
        for (int u = 0; u < 4; u++) {
            gg[5 + u] = ac[u + 3];
        }
        System.out.print("the modified bits are");
        for (int p2 = 0; p2 <= 7; p2++) {
            System.out.print(gg[p2]);
        }
        System.out.println("the actual number are" + G[f]);
        int gt1 = binary2();
        System.out.println("the modified number are" + gt1);
        gg1[f] = gt1;
//e1++;

    }

    public void byte3(int r1, int f) {
        int i = 0, e = 0;
        for (int ii = 0; ii <= 7; ii++) {
            remainder[ii] = 0;
        }
        while (r1 > 0) {
            remainder[i] = r1 % 2;
            r1 = r1 / 2;
            i++;
        }

        for (int y = 0, z = 7; (y < 8) && (z >= 0); y++, z--) {
            bb[z] = remainder[y];
        }

        for (int p2 = 0; p2 < 8; p2++) {
            System.out.print(bb[p2]);
        }

        for (int u = 0; u < 4; u++) {
            bb[5 + u] = ac[u + 5];
        }
        System.out.print("the modified bits are");
        for (int p2 = 0; p2 <= 7; p2++) {
            System.out.print(bb[p2]);
        }
        System.out.println("the actual number are" + B[f]);
        int gt1 = binary3();
        System.out.println("the modified number are" + gt1);
        bb1[f] = gt1;
//e2++;
    }

    public void byte4(int r1, int f) {
        int i = 0;

        while (r1 > 0) {
            remainder[i] = r1 % 2;
            r1 = r1 / 2;
            i++;
        }

        for (int y = 0, z = 7; (y < 8) && (z >= 0); y++, z--) {
            ac[z] = remainder[y];
        }

        for (int p2 = 0; p2 < 8; p2++) {
            System.out.print(ac[p2]);
        }

    }

    public int binary1() {


        for (int j = 0; j <= 7; j++) {
            endptr[j] = rr[j];
        }

        int integerpart1 = 0;
        int intmultiplier1 = 1;
        int h = 7;
        while (h >= 0) {
            digit = endptr[h];
            integerpart1 += intmultiplier1 * digit;
            intmultiplier1 *= 2;
            h--;
        }
        convertednumber1 = integerpart1;
        return convertednumber1;
    }

    public int binary2() {


        for (int j = 0; j <= 7; j++) {
            endptr[j] = gg[j];
        }


        int integerpart2 = 0;
        int intmultiplier2 = 1;
        int h = 7;
        while (h >= 0) {
            digit = endptr[h];
            integerpart2 += intmultiplier2 * digit;
            intmultiplier2 *= 2;
            h--;
        }
        convertednumber2 = integerpart2;
        return convertednumber2;
    }

    public int binary3() {


        for (int j = 0; j <= 7; j++) {
            endptr[j] = bb[j];
        }

        int integerpart3 = 0;
        int intmultiplier3 = 1;

        int h = 7;
        while (h >= 0) {
            digit = endptr[h];
            integerpart3 += intmultiplier3 * digit;
            intmultiplier3 *= 2;
            h--;
        }

        convertednumber3 = integerpart3;
        return convertednumber3;




    }//GEN-LAST:event_jButtonEncodeMouseClicked

    private void jLabelImageAfterMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelImageAfterMouseClicked


        for (int i = 0; i < iw * ih; i++) {
            mpixels[i] = ((255 << 24) | (rr1[i] << 16) | (gg1[i] << 8) | bb1[i]);
        }
        mimg = createImage(new MemoryImageSource(iw, ih, mpixels, 0, iw));
        this.jLabelImageAfter.setIcon(new ImageIcon(mimg));


    }//GEN-LAST:event_jLabelImageAfterMouseClicked

    private void jLabelHistAfterMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelHistAfterMouseClicked

        for (int i = 0; i < iw * ih; i++) {
            int y_m = (int) (.33 * rr1[i] + .56 * gg1[i] + .11 * bb1[i]);
            hist_m[y_m]++;
            mean_aft += y_m;
        }

        for (int i = 0; i < 256; i++) {
            if (hist_m[i] > max_hist_m) {
                max_hist_m = hist_m[i];
            }
        }

        mean_aft = mean_aft / (iw * ih);

        for (int i = 0; i < iw * ih; i++) {
            int y_m = (int) (.33 * rr1[i] + .56 * gg1[i] + .11 * bb1[i]);
            var_aft += (y_m - mean_aft) * (y_m - mean_aft);
    }//GEN-LAST:event_jLabelHistAfterMouseClicked
        var_aft = var_aft / (iw * ih);
        this.jLabelMeanAfter.setText("" + mean_aft);
        this.jLabelVarianceAfter.setText("" + var_aft);

        Graphics g1 = jLabelHistAfter.getGraphics();
        int p, q;
        p = jLabelHistAfter.getWidth();
        q = jLabelHistAfter.getHeight();
        int x = (p - 256) / 2;
        int lasty = q - q * hist_m[0] / max_hist_m;
        for (int i = 0; i < 256; i++, x++) {
            int y = q - q * hist_m[i] / max_hist_m;
            g1.setColor(new Color(i, i, i));
            g1.fillRect(x, y, 1, q);
            g1.setColor(Color.RED);
            g1.drawLine(x - 2, lasty, x, y);
            lasty = y;
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel DecodePanel;
    private javax.swing.JPanel EncodeButtonClickPanel;
    private javax.swing.JPanel EncodePanel;
    private javax.swing.JPanel LoadImagePanel;
    private javax.swing.JInternalFrame MenuBarFrame;
    private javax.swing.JPanel OperationsPanel;
    private javax.swing.JPanel displayPanel;
    private javax.swing.JButton jButtonEncode;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabelAfter;
    private javax.swing.JLabel jLabelBefore;
    private javax.swing.JLabel jLabelEncodeImage;
    private javax.swing.JLabel jLabelHistAfter;
    private javax.swing.JLabel jLabelHistBefore;
    private javax.swing.JLabel jLabelHistBlue;
    private javax.swing.JLabel jLabelHistGreen;
    private javax.swing.JLabel jLabelHistRed;
    private javax.swing.JLabel jLabelImage;
    private javax.swing.JLabel jLabelImage1;
    private javax.swing.JLabel jLabelImageAfter;
    private javax.swing.JLabel jLabelImageBefore;
    private javax.swing.JLabel jLabelMeanAfter;
    private javax.swing.JLabel jLabelMeanBefore;
    private javax.swing.JLabel jLabelMeanBlue;
    private javax.swing.JLabel jLabelMeanGreen;
    private javax.swing.JLabel jLabelMeanRed;
    private javax.swing.JLabel jLabelMod_Img;
    private javax.swing.JLabel jLabelPath;
    private javax.swing.JLabel jLabelPath1;
    private javax.swing.JLabel jLabelVarBlue;
    private javax.swing.JLabel jLabelVarGreen;
    private javax.swing.JLabel jLabelVarRed;
    private javax.swing.JLabel jLabelVarianceAfter;
    private javax.swing.JLabel jLabelVarianceBefore;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenu jMenuDecode;
    private javax.swing.JMenu jMenuEncode;
    private javax.swing.JMenu jMenuFile;
    private javax.swing.JMenuItem jMenuItemBlur;
    private javax.swing.JMenuItem jMenuItemContrast;
    private javax.swing.JMenuItem jMenuItemExit;
    private javax.swing.JMenuItem jMenuItemGrayScale;
    private javax.swing.JMenuItem jMenuItemInvert;
    private javax.swing.JMenuItem jMenuItemLoad;
    private javax.swing.JMenuItem jMenuItemReset;
    private javax.swing.JMenuItem jMenuItemSharpen;
    private javax.swing.JMenu jMenuOperations;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextFieldAddress;
    private javax.swing.JTextField jTextFieldAddress1;
    // End of variables declaration//GEN-END:variables
}
