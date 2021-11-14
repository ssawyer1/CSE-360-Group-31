package application;

import javax.swing.*;
import java.awt.*;

        public class NursePortal extends JPanel {
            private JLabel home;
            private JCheckBox jcomp2;
            private JLabel jcomp3;
            private JPasswordField jcomp4;
            private JLabel jcomp5;
            private JPasswordField jcomp6;
            private JLabel jcomp7;
            private JPasswordField jcomp8;
            private JLabel jcomp9;
            private JPasswordField jcomp10;
            private JLabel jcomp11;
            private JPasswordField jcomp12;
            private JLabel jcomp13;
            private JPasswordField jcomp14;
            private JCheckBox jcomp15;
            private JLabel jcomp16;
            private JTextArea jcomp17;

            public NursePortal() {
                //construct components
                home = new JLabel ("Home");
                jcomp2 = new JCheckBox ("New Patient?");
                jcomp3 = new JLabel ("First Name:");
                jcomp4 = new JPasswordField (5);
                jcomp5 = new JLabel ("Last Name:");
                jcomp6 = new JPasswordField (5);
                jcomp7 = new JLabel ("1) Patient Weight:");
                jcomp8 = new JPasswordField (5);
                jcomp9 = new JLabel ("2) Patient Height:");
                jcomp10 = new JPasswordField (5);
                jcomp11 = new JLabel ("3) Patient Blood Pressure:");
                jcomp12 = new JPasswordField (5);
                jcomp13 = new JLabel ("4) Patient Body Temperature:");
                jcomp14 = new JPasswordField (5);
                jcomp15 = new JCheckBox ("Patient is 12 or Older");
                jcomp16 = new JLabel ("Additional Information:");
                jcomp17 = new JTextArea (5, 5);

                //adjust size and set layout
                setPreferredSize (new Dimension (438, 684));
                setLayout (null);

                //add components
                add (home);
                add (jcomp2);
                add (jcomp3);
                add (jcomp4);
                add (jcomp5);
                add (jcomp6);
                add (jcomp7);
                add (jcomp8);
                add (jcomp9);
                add (jcomp10);
                add (jcomp11);
                add (jcomp12);
                add (jcomp13);
                add (jcomp14);
                add (jcomp15);
                add (jcomp16);
                add (jcomp17);

                //set component bounds (only needed by Absolute Positioning)
                home.setBounds (190, 25, 100, 25);
                jcomp2.setBounds (35, 70, 155, 25);
                jcomp3.setBounds (30, 110, 100, 25);
                jcomp4.setBounds (110, 110, 100, 25);
                jcomp5.setBounds (220, 110, 100, 25);
                jcomp6.setBounds (295, 110, 100, 25);
                jcomp7.setBounds (35, 170, 135, 25);
                jcomp8.setBounds (155, 170, 100, 25);
                jcomp9.setBounds (35, 220, 125, 30);
                jcomp10.setBounds (155, 220, 100, 25);
                jcomp11.setBounds (35, 275, 175, 20);
                jcomp12.setBounds (205, 275, 100, 25);
                jcomp13.setBounds (35, 325, 205, 25);
                jcomp14.setBounds (225, 325, 100, 25);
                jcomp15.setBounds (30, 380, 190, 25);
                jcomp16.setBounds (35, 425, 155, 25);
                jcomp17.setBounds (35, 455, 365, 200);
            }


            public static void main (String[] args) {
                JFrame frame = new JFrame ("Nurse Home");
                frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
                frame.getContentPane().add (new NurseHome());
                frame.pack();
                frame.setVisible (true);
            }

            public static class NurseHome extends NursePortal{

            }
        }
