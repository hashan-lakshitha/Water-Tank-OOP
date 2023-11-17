
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;



interface WaterLevelObserver1{

    public void update(int waterLevel);
}
class ControlRoom{
    private int waterLevel;
    private ArrayList<WaterLevelObserver1> observerList=new ArrayList<>();
    public void addWaterLevelObserver(WaterLevelObserver1 ob){
        observerList.add(ob);

    }
    public void notification(){
        for (WaterLevelObserver1 ob:observerList){
            ob.update(waterLevel);
        }
    }

    public void setWaterLevel(int waterLevel){
        if (this.waterLevel!=waterLevel);
        this.waterLevel=waterLevel;
        notification();
    }
}

class WaterTank extends JFrame{
    private JSlider waterLevelSlider;

    private ControlRoom controlRoom;
    WaterTank (ControlRoom controlRoom){
        this.controlRoom=controlRoom;

        setSize(400,900);
        setTitle("Water Tank");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());
        setBackground(Color.yellow);

        waterLevelSlider=new JSlider(JSlider.VERTICAL,0,100,0);
        waterLevelSlider.setFont(new Font ("",1,15));
        waterLevelSlider.setPreferredSize(new Dimension(300,800));
        waterLevelSlider.setPaintTicks(true);
        waterLevelSlider.setMajorTickSpacing(5);
        waterLevelSlider.setMinorTickSpacing(5);
        waterLevelSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent ce) {
                controlRoom.setWaterLevel(waterLevelSlider.getValue());
            }
        });
        waterLevelSlider.setPaintLabels(true);
        add(waterLevelSlider);
        setVisible(true);
    }

}
class AlarmWindow extends JFrame implements WaterLevelObserver1{

    private JLabel label;

    AlarmWindow(){
        setSize(400,300);

        setTitle("Alarm");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new FlowLayout());
        label=new JLabel("ALARM OFF", SwingConstants.CENTER);
        label.setPreferredSize(new Dimension(400,400));
        label.setFont(new Font ("",1,30));
        add(label);
        setVisible(true);
    }
    @Override
    public void update(int waterLevel) {
        label.setText(waterLevel>=50?"ALARM ON":"ALARM OFF");

        if (waterLevel>=50){
            label.setFont(new Font ("",1,50));
            label.setForeground(Color.red);
        }else{
            label.setFont(new Font ("",1,30));
        }
    }
}


class DisplayWindow extends JFrame implements WaterLevelObserver1{
    private JLabel displaywaterLevellabel;

    DisplayWindow(){
        setSize(400,300);
        setTitle("Display");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new FlowLayout());
        displaywaterLevellabel=new JLabel("0",SwingConstants.CENTER);
        displaywaterLevellabel.setPreferredSize(new Dimension(400,400));
        displaywaterLevellabel.setFont(new Font ("",1,30));
        add(displaywaterLevellabel);
        setVisible(true);
    }
    @Override
    public void update(int waterLevel) {
        displaywaterLevellabel.setText(""+waterLevel);
        if (waterLevel>=50){
            displaywaterLevellabel.setFont(new Font ("",1,50));
            displaywaterLevellabel.setForeground(Color.red);
        }else{
            displaywaterLevellabel.setFont(new Font ("",1,30));
        }
    }
}

class SplitterWindow extends JFrame implements WaterLevelObserver1{
    private JLabel splitterLevellabel;

    SplitterWindow(){
        setSize(400,300);
        setTitle("Splitter");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new FlowLayout());
        splitterLevellabel=new JLabel(" SPLITTER OFF",SwingConstants.CENTER);
        splitterLevellabel.setFont(new Font ("",1,30));
        splitterLevellabel.setPreferredSize(new Dimension(400,400));
        add(splitterLevellabel);
        setVisible(true);
    }
    @Override
    public void update(int waterLevel) {
        splitterLevellabel.setText(waterLevel>=50?"SPLITTER ON":"SPLITTER OFF");
        if (waterLevel>=50){
            splitterLevellabel.setFont(new Font ("",1,50));
            splitterLevellabel.setForeground(Color.red);
        }else {
            splitterLevellabel.setFont(new Font ("",1,30));

        }

    }
}

class Demo {
    public static void main(String [] arg){
        ControlRoom controlRoom=new ControlRoom();
        controlRoom.addWaterLevelObserver(new  AlarmWindow());
        controlRoom.addWaterLevelObserver(new  SplitterWindow());
        controlRoom.addWaterLevelObserver(new  DisplayWindow());

        new WaterTank(controlRoom).setVisible(true);
    }
}
