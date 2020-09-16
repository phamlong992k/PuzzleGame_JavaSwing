
import javax.swing.JButton;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Asus
 */
public class ClickButton {
    JButton up;
    JButton down;
    JButton left;
    JButton right;
    public ClickButton(){
        this.up = new JButton();
        this.down = new JButton();
        this.left = new JButton();
        this.right = new JButton();
    }
    public ClickButton(JButton up, JButton down, JButton left, JButton right) {
        this.up = up;
        this.down = down;
        this.left = left;
        this.right = right;
    }
    
}
