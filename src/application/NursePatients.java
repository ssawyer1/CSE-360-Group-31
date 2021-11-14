package application;


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

    public class NursePatients extends JPanel {
        private JLabel jcomp1;
        private JComboBox jcomp2;
        private JMenuBar jcomp3;
        private JMenuBar jcomp4;
        private JTextArea Prescriptions;

        public NursePatients() {
            //construct preComponents
            String[] jcomp2Items = {"Sally Joe/Doctor Page", "John Doe/Doctor Jane", "Caleb Foe/Doctor Page"};
            JMenu messageMenu = new JMenu ("Message");
            JMenuItem option_1Item = new JMenuItem ("Option 1");
            messageMenu.add (option_1Item);
            JMenuItem option_2Item = new JMenuItem ("Option 2");
            messageMenu.add (option_2Item);
            JMenu prescriptionsMenu = new JMenu ("Prescriptions");
            JMenuItem prescriptionsItem = new JMenuItem ("Prescriptions");
            prescriptionsMenu.add (prescriptionsItem);
            JMenu patient_historyMenu = new JMenu ("Patient History");
            JMenuItem option_1Item1 = new JMenuItem ("Option 1");
            patient_historyMenu.add (option_1Item1);
            JMenuItem option_2Item2 = new JMenuItem ("Option 2");
            patient_historyMenu.add (option_2Item2);
            JMenu messagesMenu = new JMenu ("Messages");
            option_1Item = new JMenuItem("Option 1");
            messagesMenu.add (option_1Item);
             option_2Item = new JMenuItem ("Option 2");
            messagesMenu.add (option_2Item);
            JMenu homeMenu = new JMenu ("Home");
             option_1Item1 = new JMenuItem ("Option 1");
            homeMenu.add (option_1Item1);
             option_2Item2 = new JMenuItem ("Option 2");
            homeMenu.add (option_2Item2);
            JMenu patientsMenu = new JMenu ("Patients");
            JMenuItem option_1Item3 = new JMenuItem ("Option 1");
            patientsMenu.add (option_1Item3);
            JMenuItem option_2Item4 = new JMenuItem ("Option 2");
            patientsMenu.add (option_2Item4);

            //construct components
            jcomp1 = new JLabel ("Patient Name:");
            jcomp2 = new JComboBox (jcomp2Items);
            jcomp3 = new JMenuBar();
            jcomp3.add (messageMenu);
            jcomp3.add (prescriptionsMenu);
            jcomp3.add (patient_historyMenu);
            jcomp4 = new JMenuBar();
            jcomp4.add (messagesMenu);
            jcomp4.add (homeMenu);
            jcomp4.add (patientsMenu);
            Prescriptions = new JTextArea (5, 5);

            //adjust size and set layout
            setPreferredSize (new Dimension (438, 684));
            setLayout (null);

            //add components
            add (jcomp1);
            add (jcomp2);
            add (jcomp3);
            add (jcomp4);
            add (Prescriptions);

            //set component bounds (only needed by Absolute Positioning)
            jcomp1.setBounds (45, 105, 100, 25);
            jcomp2.setBounds (40, 135, 205, 25);
            jcomp3.setBounds (40, 180, 295, 25);
            jcomp4.setBounds (45, 60, 200, 25);
            Prescriptions.setBounds (40, 205, 295, 270);
        }


        public static void main (String[] args) {
            JFrame frame = new JFrame ("Nurse Patient Screen");
            frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
            frame.getContentPane().add (new NursePatients());
            frame.pack();
            frame.setVisible (true);
        }
    }
