package Aula4;

import javax.swing.JFrame;

public class Main {

    public static void main(String[] args) {
        
        JFrame janela = new JFrame("App");

        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //janela.setSize(400, 400);

        Jogo jogo = new Jogo();
        janela.add (jogo);
        janela.pack();

        janela.setVisible(true);
    }
    
}
