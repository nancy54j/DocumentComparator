package documentCompare;

import jdk.nashorn.internal.scripts.JO;
import oracle.jrockit.jfr.JFR;

import javax.print.Doc;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class GUI extends JFrame {

    private JLabel instruction;
    private JLabel instruction2;

    private JButton buttonCalc;
    private JLabel percentSim;

    private JTextField text;
    private JTextField text2;

    public GUI(){
        createView();
        setTitle("DOCUMENT COMPARATOR");
        
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(new Dimension(500,300));
    }

    private void createView(){

        JPanel panel = new JPanel();

        //instructions
        instruction = new JLabel();
        instruction.setText("Enter path name of 2 files you would like to compare...  ");

        instruction2 = new JLabel();
        instruction2.setText("\n ex: C:\\Users\\Desktop\\File.txt\n");

        //elements
        buttonCalc = new JButton("Calculate Similarity");
        buttonCalc.setHorizontalAlignment(JLabel.CENTER);

        text = new JTextField(30);
        text.setHorizontalAlignment(JLabel.CENTER);

        text2 = new JTextField(30);
        text2.setHorizontalAlignment(JLabel.CENTER);

        percentSim = new JLabel();

        buttonCalc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){

                int similarity = -1;
                try {
                    similarity = SetSimilarity();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }

                String textInput = "Invalid URL";
                if(similarity != -1){
                    textInput = "Documents are "+similarity+"% similar";
                }
                percentSim.setText(textInput);

            }
        });


        GridBagConstraints c = new GridBagConstraints();

        c.gridx = 0;//set the x location of the grid for the next component
        c.gridy = 0;//set the y location of the grid for the next component
        panel.add(instruction,c);

        c.gridy = 1;//change the y location
        c.anchor=GridBagConstraints.WEST;//left align components after this point
        panel.add(instruction2,c);

        c.gridy = 2;//change the y location
        panel.add(text,c);

        c.gridy = 3;//change the y location
        panel.add(text2,c);

        c.gridy = 4;//change the y location
        panel.add(buttonCalc,c);

        c.gridy = 5;//change the y location
        panel.add(percentSim,c);

        this.getContentPane().add(panel);

        this.pack();
    }

    //returns cosine similarity of the function
   public int SetSimilarity() throws IOException{

       Document doc1 = new Document(text.getText());
       Document doc2 = new Document(text2.getText());

        return doc1.cosineSimilarity(doc2);
    }

    public static void main(String[] arg) {

        new GUI().setVisible(true);
    }
}

