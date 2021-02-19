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
	
	JTextField cajaTemp1,cajaTemp2;
	JComboBox<String> comboTmp1 = new JComboBox<String>();
	JComboBox<String> comboTmp2 = new JComboBox<String>();
	
	
	public EventosComboBox() {
		getContentPane().setLayout(new FlowLayout());
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(500,80);
		setLocationRelativeTo(null);
		setTitle("Eventos ComboBox");
		setVisible(true);
		
		JLabel lblConv = new JLabel("convertir:");
		add(lblConv);
		
		cajaTemp1 = new JTextField(5);
		cajaTemp1.setToolTipText("Presiona ENTER para Convertir");
		cajaTemp1.addActionListener(this);
		cajaTemp1.addKeyListener(new KeyAdapter() {//validacion
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
		
		comboTmp1.addItem("Centigrados");
		comboTmp1.addItem("Fahrenheit");
		comboTmp1.addItem("Rankine");
		comboTmp1.addActionListener(this);
		add(comboTmp1);
		
		JLabel to = new JLabel("A:");
		add(to);
		
		comboTmp2.addItem("Centigrados");
		comboTmp2.addItem("Fahrenheit");
		comboTmp2.addItem("Rankine");
		comboTmp2.addActionListener(this);
		add(comboTmp2);
		
		cajaTemp2 = new JTextField(5);
		cajaTemp2.setEditable(false);
		add(cajaTemp2);
		
	
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==cajaTemp1||e.getSource()==comboTmp1||e.getSource()==comboTmp2) {
			double cnv=Double.parseDouble(cajaTemp1.getText());
			if (comboTmp1.getSelectedItem()=="Centigrados" && comboTmp2.getSelectedItem()=="Fahrenheit") {
				cnv=(cnv*1.8)+32;
			}else if (comboTmp1.getSelectedItem()=="Centigrados" && comboTmp2.getSelectedItem()=="Rankine") {
				cnv=(cnv*1.8)+491.67;
			}else if (comboTmp1.getSelectedItem()=="Fahrenheit" && comboTmp2.getSelectedItem()=="Centigrados") {
				cnv=(cnv-32)*5/9;	
			}else if (comboTmp1.getSelectedItem()=="Fahrenheit" && comboTmp2.getSelectedItem()=="Rankine") {
				cnv=(cnv+459.67);	
			}else if (comboTmp1.getSelectedItem()=="Rankine" && comboTmp2.getSelectedItem()=="Centigrados") {
				cnv=(cnv-491.67)*5/9;	
			}else if (comboTmp1.getSelectedItem()=="Rankine" && comboTmp2.getSelectedItem()=="Fahrenheit") {
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
