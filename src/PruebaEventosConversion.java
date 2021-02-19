import java.awt.FlowLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

class EventosComboBox extends JFrame implements ActionListener{

	JButton btnConv;
	JTextField cajaTemp1,cajaTemp2;
	JComboBox<String> comboTmp1 = new JComboBox<String>();
	JComboBox<String> comboTmp2 = new JComboBox<String>();
	
	
	public EventosComboBox() {
		getContentPane().setLayout(new FlowLayout());
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(400,80);
		setLocationRelativeTo(null);
		setTitle("Eventos ComboBox");
		setVisible(true);
		
		cajaTemp1 = new JTextField(5);
		cajaTemp1.setToolTipText("Presiona ENTER para Convertir");
		cajaTemp1.addActionListener(this);
		cajaTemp1.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent ke) {
				String value = cajaTemp1.getText();
				int code=ke.getKeyCode();
				if ((ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9')	||	(!value.contains(".")&&ke.getKeyChar()=='.') || (code==KeyEvent.VK_BACK_SPACE)) {
					cajaTemp1.setEditable(true);
				}else{
					cajaTemp1.setEditable(false);
				}
			}
		});
		add(cajaTemp1);
		
		comboTmp1.addItem("C");
		comboTmp1.addItem("F");
		comboTmp1.addItem("R");
		add(comboTmp1);
		
		btnConv = new JButton("convertir>>>>");
		btnConv.addActionListener(this);
		add(btnConv);
		
		cajaTemp2 = new JTextField(5);
		cajaTemp2.setEditable(false);
		add(cajaTemp2);
		
		comboTmp2.addItem("C");
		comboTmp2.addItem("F");
		comboTmp2.addItem("R");
		add(comboTmp2);
		
	
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==btnConv || e.getSource()==cajaTemp1) {
			double cnv=Double.parseDouble(cajaTemp1.getText());
			if (comboTmp1.getSelectedItem()=="C" && comboTmp2.getSelectedItem()=="F") {
				cnv=(cnv*1.8)+32;
			}else if (comboTmp1.getSelectedItem()=="C" && comboTmp2.getSelectedItem()=="R") {
				cnv=(cnv*1.8)+491.67;
			}else if (comboTmp1.getSelectedItem()=="F" && comboTmp2.getSelectedItem()=="C") {
				cnv=(cnv-32)*5/9;	
			}else if (comboTmp1.getSelectedItem()=="F" && comboTmp2.getSelectedItem()=="R") {
				cnv=(cnv+459.67);	
			}else if (comboTmp1.getSelectedItem()=="R" && comboTmp2.getSelectedItem()=="C") {
				cnv=(cnv-491.67)*5/9;	
			}else if (comboTmp1.getSelectedItem()=="R" && comboTmp2.getSelectedItem()=="F") {
				cnv=(cnv-459.67);	
			}
			cnv = Math.round(cnv * 100.0) / 100.0;
			cajaTemp2.setText(String.valueOf(cnv));
	
			
		}
		
	}
	
}
public class PruebaEventosConversion {

	public static void main(String[] args) {
		
		SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new EventosComboBox();
            }
        });

	}

}
