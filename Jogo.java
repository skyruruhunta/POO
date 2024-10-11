package Aula4;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Jogo extends JPanel implements ActionListener {

    private final int LARGURA = 400;
    private final int ALTURA = 400;

    private int posX = 0;

    Jogo(){
        setPreferredSize(new Dimension(LARGURA, ALTURA));
        Timer temporizador = new Timer(150, this);
    }

    private void atualizar(){
        this.posX++;
    }
    

    @Override
    public void paint (Graphics g){
        super.paint(g);

        g.setColor(Color.BLUE);
        g.drawRect(this.posX, 50, 50, 50);
    }

    @Override
    public void actionPerformed(ActionEvent e) {        
        
    }
}
