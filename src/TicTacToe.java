import java.awt.font.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TicTacToe {

    private static int[][] winCombinations = new int[][] {
            {0, 1, 2}, {3, 4, 5}, {6, 7, 8}, //gagnes horizontal
            {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, //gagnes vertical
            {0, 4, 8}, {2, 4, 6}			 //gagnes diagonal
    };

    private static JButton buttons[] = new JButton[9]; //créer 9 boutons



    public static void main (String[] args)
    {
        gamePanel(); //Lancer le jeu
    }

    private static void gamePanel(){
        JFrame frame = new JFrame ("Tic Tac Toe");
        frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);


        JPanel panel = new JPanel(); //créer un panel avec une boîte comme une planche de tic tac toe
        panel.setLayout (new GridLayout (3, 3));
        panel.setBorder (BorderFactory.createLineBorder (Color.gray, 5));
        panel.setBackground (Color.white);

        for(int i=0; i<=8; i++){ //placer le bouton sur le tableau
            buttons[i] = new MyButton();
            panel.add(buttons[i]);
        }

        frame.getContentPane().add (panel);
        frame.pack();
        frame.setVisible(true);
        frame.setSize(500, 500);// définir la taille du frame et laisser le jeu commencer
    }

    public static int xOrO=0; // utilisé pour compter
    public static int[] var = {0,1}; //utilisé pour varier le joueur qui débute

    private static class MyButton extends JButton
            implements ActionListener {//création de sa propre classe de boutons

        int again=1000;
        boolean win=false; // il n'y a pas de gagne
        String letter; // x or o
        public MyButton() {	// création d'un tableau vierge
            super();
            letter=" ";
            setFont(new Font("Dialog", 1, 60));
            setText(letter);
            addActionListener(this);
        }
        public void actionPerformed(ActionEvent e) { // placing x or o's
            if((xOrO%2)==var[0] && getText().equals(" ") && win==false){
                letter="X";
                xOrO=xOrO+1;
                System.out.println(letter + "\n"+xOrO);
            } else if((xOrO%2)==var[1] && getText().equals(" ") && win==false) {
                letter="O";
                xOrO=xOrO+1;
                System.out.println(letter + "\n"+xOrO);
            } // si l'utilisateur clique sur un bouton déjà joué, rien ne se passera

            setText(letter); // placez le x ou le o


            for(int i=0; i<=7; i++){ // vérifier les combinaisons gagnantes
                if( buttons[winCombinations[i][0]].getText().equals(buttons[winCombinations[i][1]].getText()) &&
                        buttons[winCombinations[i][1]].getText().equals(buttons[winCombinations[i][2]].getText()) &&
                        buttons[winCombinations[i][0]].getText() != " "){//the winning is true
                    win = true;
                }
            }

            if(win == true){ // si le jeu se termine, faites savoir à l'utilisateur qui gagne et donnez la possibilité de jouer à nouveau
                again=JOptionPane.showConfirmDialog(null, letter + " gagne le match!  Voulez-vous rejouer?",letter + " gagne!",JOptionPane.YES_NO_OPTION);

            } else if(xOrO == 9 && win == false){//match nul, annoncer et demander si l'utilisateur souhaite rejouer
                again=JOptionPane.showConfirmDialog(null, "Le match était nul!  Voulez-vous rejouer?","Match nul!",JOptionPane.YES_NO_OPTION);
                win=true;
            }

            if(again==JOptionPane.YES_OPTION && win==true){ // si l'utilisateur veut rejouer effacer tout le bouton et recommencer avel le changement de rôle
                clearButtons();
                win=false;
                if(var[0]==0 && var[1]==1) {
                    var[0]=1;
                    var[1]=0;
                }else{
                    var[0]=0;
                    var[1]=1;
                }
            }
            else if(again==JOptionPane.NO_OPTION){
                System.exit(0); // quitter le jeu si l'utilisateur ne veut pas rejouer
            }
        }

    }

    public static void clearButtons(){

        for(int i=0; i<=8; i++){// effacer tous les boutons
            buttons[i].setText(" ");
        }
        xOrO=0; // réinitialiser le compteur

    }

}
