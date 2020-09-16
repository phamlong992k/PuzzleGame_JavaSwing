
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Asus
 */
public class GamePanel extends javax.swing.JPanel {

    /**
     * Creates new form GamePanel
     */
    //boolean swapped=false;
    
    ClickButton relateButton;
    boolean swapped = false;
    EmptyButton emptyButton;
    int selectedIndexComboBox = 0;
    int Height;
    int Width;
    int MAX = 9;
    int SIZE = 3;
    int moveCount = 0;
    Vector<JButton> btnList;
    //JButton btn[];
    final JLabel nameGame= new JLabel("---PUZZLE GAME---");
    JPanel center;
    JPanel bottom;
    JPanel top;
    
    JLabel lblmoveCount, lblSec;
    JComboBox<String> cmb;
    JButton btnNew;
    boolean onGame = false;
    int time = 0;
    String messageNewGame = "New Game";
    Vector<Integer> listToWin = new Vector<>();
    Vector<Integer> countButton;
    //JButton btnNewGame;
    
    public GamePanel() {
        initComponents();
        
        loadControl();
    }
    
    private void loadControl() {
        this.removeAll();
        this.validate();
        this.repaint();
        top= new JPanel(new FlowLayout(FlowLayout.CENTER));
        
        center = new JPanel(new GridLayout(SIZE, SIZE));
    
        bottom = new JPanel(new FlowLayout(FlowLayout.CENTER));
        this.setLayout(new BorderLayout());
        btnList = new Vector();
        countButton = new Vector();
        for (int so = 0; so < MAX; so++) {
            if (so > 0) {
                listToWin.add(so);
            }

            countButton.add(so);// tao mang so
        }
        do{
            Collections.shuffle(countButton);
            for (int i = 0; i < MAX; i++) {
                JButton button = new JButton();
                if (countButton.get(i) == 0) {
                    button.setText("");
                } else {
                    button.setText("" + countButton.get(i));
                }
                center.add(button);
                btnList.add(button);
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (onGame == true) {
                            checkSwwapp2(e);
                           //checkSwapp(e);
                        }
                        else{
                            JOptionPane.showMessageDialog(null,"Click New Game to play");
                        }
                                
                    }
                });
            }
            Collections.sort(listToWin);
            listToWin.add(0);
        }while(btnList.equals(listToWin));    
        setEnableGame();
        //
        lblmoveCount = new JLabel("Move Count: " + moveCount);
        time = 0;
        lblSec = new JLabel("Elapsed: " + time + " sec");
        //
        cmb = new JComboBox();
        setComboBox(cmb);
        getSelectComboBox(cmb);
        cmb.setSelectedIndex(selectedIndexComboBox);
        //setTime();
        //
        btnNew = new JButton(messageNewGame);
        newGame();
        //
        bottom.add(lblmoveCount);
        bottom.add(lblSec);
        bottom.add(cmb);
        bottom.add(btnNew);
        
        top.add(nameGame);
        this.add(top,BorderLayout.NORTH);
        this.add(center,BorderLayout.CENTER);
        this.add(bottom,BorderLayout.SOUTH);
    }
    private void setEnableGame(){
        Component[] components = center.getComponents();
        for (Component cpn : components) {
            if (cpn instanceof JButton) {
                JButton btn=new JButton();
                btn=((JButton) cpn);
                btn.setEnabled(onGame);
            }
        }
    }
    private void getButton(ActionEvent e) {
        JButton newButton = (JButton) e.getSource();
        Width = newButton.getWidth();
        Height = newButton.getHeight();    
    }
    private void findEmptyButton(){
        Component[] components = center.getComponents();
        for (Component cpn : components) {
            if (cpn instanceof JButton) {
                JButton btn=new JButton();
                btn=((JButton) cpn);
                if (btn.getText() == "") {
                    emptyButton= new EmptyButton();
                   //System.out.println(btn.getX());
                   emptyButton.setX(btn.getX());
                   emptyButton.setY(btn.getY());
                   emptyButton.setButton(btn);
                }
            }
        }
    }
    private void checkSwwapp2(ActionEvent e){
        JButton newButton = ((JButton) e.getSource());
        getButton(e);
        JButton up= new JButton("-1");
        JButton down= new JButton("-1");
        JButton left= new JButton("-1");
        JButton right= new JButton("-1");
        if(center.getComponentAt(newButton.getX(),newButton.getY()+Height)instanceof JButton){
            up=(JButton) center.getComponentAt(newButton.getX(),newButton.getY()+Height);
        }
        if(center.getComponentAt(newButton.getX(),newButton.getY()-Height)instanceof JButton){
            down=(JButton)center.getComponentAt(newButton.getX(),newButton.getY()-Height);
        }
        if(center.getComponentAt(newButton.getX()-Width,newButton.getY())instanceof JButton){
            left=(JButton)center.getComponentAt(newButton.getX()-Width,newButton.getY());
        }
        if(center.getComponentAt(newButton.getX()+Width,newButton.getY())instanceof JButton){
            right=(JButton)center.getComponentAt(newButton.getX()+Width,newButton.getY());
        }
        
        
        
        if(up.getText().equals("")){
            setMove2(e,up);
        }
        else if(down.getText().equals("")){
            setMove2(e,down);
        }
        else if(right.getText().equals("")){
            setMove2(e,right);
        }
        else if(left.getText().equals("")){
            setMove2(e,left);
        }
        
    }
    private void checkSwapp(ActionEvent e) {
           
            
            findEmptyButton();
            getButton(e);
            JButton newButton = ((JButton) e.getSource());
            if (newButton.getY() >= 0 && newButton.getX() >= 0) {
                if (emptyButton.getY() == newButton.getY()) {
                    int xLast = emptyButton.getX();
                    int xNew = newButton.getX();
                    if (xLast == xNew - Width) {
                        setMove(e);
                    }
                    if (xLast == xNew + Width) {
                        setMove(e);
                    }
                } else if (emptyButton.getX() == newButton.getX()) {
                    int yLast = emptyButton.getY();
                    int yNew = newButton.getY();
                    if (yLast == yNew - Height) {
                        setMove(e);
                    }
                    if (yLast == yNew + Height) {
                        setMove(e);
                    }
                }
            }
        
        
        //lastButton = null;
        
    }

    private void setMove(ActionEvent e) {
        swapp(e);
        moveCount += 1;
        lblmoveCount.setText("Move Count: " + moveCount);
        checkWin();

    }
     private void setMove2(ActionEvent e,JButton empty) {
        swapp2(e,empty);
        moveCount += 1;
        lblmoveCount.setText("Move Count: " + moveCount);
        checkWin();

    }
    private void swapp2(ActionEvent e,JButton empty){
        String tmp = "";
        tmp = empty.getText();
        //System.out.println(lastButton);
        empty.setText(((JButton) e.getSource()).getText());
        ((JButton) e.getSource()).setText(tmp);
        //emptyButton = null;
        swapped = true;
    }
    private void swapp(ActionEvent e) {
        
        String tmp = "";
        tmp = emptyButton.getButton().getText();
        //System.out.println(lastButton);
        emptyButton.getButton().setText(((JButton) e.getSource()).getText());
        ((JButton) e.getSource()).setText(tmp);
        emptyButton = null;
        swapped = true;
    }

    private void checkWin() {
        Vector<Integer> result = new Vector<>();
        Component[] components = center.getComponents();
        for (Component cpn : components) {
            if (cpn instanceof JButton) {
                JButton btn=new JButton();
                btn=((JButton) cpn);
                if (btn.getText() == "") {
                    result.add(0);
                } else {
                    result.add(Integer.valueOf(((JButton) cpn).getText()));
                }
            }
        }
        if (result.equals(listToWin)) {
            onGame = false;
            JOptionPane.showMessageDialog(this, "WINNNNNN"+"\n"+"Time: "+time+"\n"+"Move Count: "+moveCount);
            setNew();
        }
    }

    private void setComboBox(JComboBox cmb) {
        String[] arr = {"3x3", "4x4", "5x5", "6x6", "7x7", "8x8", "9x9"};
        Vector items = new Vector<>(Arrays.asList(arr));
        DefaultComboBoxModel model = new DefaultComboBoxModel(items);
        cmb.setModel(model);
    }

    private void getSelectComboBox(JComboBox cmb) {
        cmb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int pos = cmb.getSelectedIndex();
                switch (pos) {
                    case 0: {
                        setSizeMaxIndex(pos);
                        break;
                    }
                    case 1: {
                        setSizeMaxIndex(pos);
                        break;
                    }
                    case 2: {
                        setSizeMaxIndex(pos);
                        break;
                    }
                    case 3: {
                        setSizeMaxIndex(pos);
                        break;
                    }
                    case 4: {
                        setSizeMaxIndex(pos);
                        break;
                    }
                    case 5: {
                        setSizeMaxIndex(pos);
                        break;
                    }
                    case 6: {
                        setSizeMaxIndex(pos);
                        break;
                    }
                    
                }
            }
        });
        top.repaint();
    }
    private void setSizeMaxIndex(int pos){
        
        SIZE=pos+3;
        MAX=SIZE*SIZE;
        selectedIndexComboBox=pos;
        
    }
    private Thread setTime() {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                while (onGame == true) {
                    try {
                        time += 1;
                        lblSec.setText("Elapsed: " + time + " sec");
                        Thread.sleep(1000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        return t;
    }

    private void newGame() {
        btnNew.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (onGame == false) {
                    messageNewGame = "Stop";
                    onGame = true;
                    time = 0;
                    moveCount = 0;
                    listToWin.clear();
                   
                    loadControl();
                    cmb.setEnabled(false);
                    setTime().start();

                    //((MainFrame)getParent()).pack();
                } else if (onGame == true) {
                    
                    onGame=false;
                    JOptionPane.showMessageDialog(null,"You Lose !"+"\n"+"Time: "+time+"\n"+"Move Count: "+moveCount);
                    setNew();
                    //setTime().interrupt();
                }
            }
        });
    }

    private void setNew() {
        messageNewGame="NewGame";
        cmb.setEnabled(true);
        listToWin.clear();
        moveCount=0;
        loadControl();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
