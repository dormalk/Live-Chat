package ac.il.Shenkar;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultCaret;

public class ClientGUI implements StringConsumer,ActionListener {

	private StringConsumer consumer=null;
	private StringProducer producer=null;
	private static JTextArea ta=null;
	private static JTextField tf=null;
	private static JTextField statusline=null;
	private static JButton btn1=null;
	private static JButton btn2=null;
	private static JPanel panel=null;
	private static JTextArea cl=null;
	public static void main(String args[]){
			ClientGUI cg=new ClientGUI();
			cg.go();
	
	}
	
	public void go(){
		JFrame frame=new JFrame();
		panel=new JPanel();
		JPanel panel1=new JPanel();
		JPanel panel2=new JPanel();

		btn1=new JButton("Connect");
		btn2=new JButton("Send");
		tf=new JTextField("Enter Nickname");
		ta=new JTextArea();
		cl=new JTextArea();
		statusline=new JTextField();
		JScrollPane scrollPane=new JScrollPane(ta,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		//Frame Enable closing operation
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowClosing(WindowEvent e)
            {
            	if (consumer!=null){
            		consumer.consume("-");
            	}
            	e.getWindow().dispose();
            }
        });
		//Frame Disable resize operation
		frame.setResizable(false);
		frame.setLayout(new FlowLayout());
		//Frame Set blue background
		frame.getContentPane().setBackground(new Color(208,59,59));
		//Frame Set size
		frame.setSize(750,750);
		//TextArea panel set blue background
		panel1.setBackground(new Color(117,117,117));
		//TextArea panel set layout
		panel1.setLayout(new FlowLayout());
		//TextArea panel set size
		panel1.setPreferredSize(new Dimension(730,615));
		panel2.setPreferredSize(new Dimension(730,35));
		//TextLine panel set layout
		panel.setLayout(new BorderLayout());
		//TextLine panel set size
		panel.setPreferredSize(new Dimension(730,35));
		//TextArea set size
		scrollPane.setPreferredSize(new Dimension(565,605));
		//TextArea set border
		ta.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.white));
		//TextArea disable edit
		ta.setEditable(false);
		statusline.setPreferredSize(new Dimension(732,37));
		statusline.setEditable(false);
		statusline.setBackground(new Color(208,59,59));
		statusline.setFont(new Font("SansSerif",Font.TRUETYPE_FONT, 18));
		statusline.setForeground(Color.WHITE);
		panel2.setBackground(new Color(208,59,59));
		cl.setPreferredSize(new Dimension(150,605));
		//TextArea set border
		cl.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.white));
		//TextArea disable edit
		cl.setEditable(false);
		//TextLine set font
		tf.setFont(new Font("SansSerif",Font.TRUETYPE_FONT, 18));
		//TextLine set border
		tf.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.white));

		tf.addFocusListener(new FocusListener() {

		    @Override
		    public void focusGained(FocusEvent e) {
		        tf.setText(null); // Empty the text field when it receives focus
		    }

			@Override
			public void focusLost(FocusEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		//TextArea set font
		ta.setFont(new Font("Serif",Font.ITALIC, 18));
		//Add connect button to bottom panel
		panel.add(btn1, BorderLayout.WEST);
		//Add TextLine to bottom panel
		panel.add(tf);
		//Add TextArea to top panel
		panel2.add(statusline);
		panel1.add(scrollPane);
		panel1.add(cl);
		frame.add(panel2,BorderLayout.NORTH);
		//Add TextArea panel to frame to the top
		frame.add(panel1,BorderLayout.NORTH);
		//Add TextLine panel to frame to the bottom
		frame.add(panel,BorderLayout.SOUTH);
		btn1.setActionCommand("Connect");
		btn1.addActionListener(this);
		btn2.setActionCommand("Send");
		btn2.addActionListener(this);
		
		frame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String Command=e.getActionCommand();
		if(Command.equals("Connect")){
			statusline.setText("Connecting...");
			try{
				ConnectionProxy proxy=new ConnectionProxy(new Socket("127.0.0.1",2032));
				consumer=proxy;
				producer=proxy;
				producer.addConsumer(this);
				proxy.start();
				statusline.setText("Connect");
				consumer.consume(tf.getText());
				tf.setText("");
				panel.remove(btn1);
				panel.add(btn2,BorderLayout.WEST);
			}
			catch(IOException a){
				statusline.setText(a.getMessage());
			}		
		}
		if(Command.equals("Send")){
			if (!tf.getText().equals("")){
				consumer.consume(tf.getText());
			}
			tf.setText("");			
		}
	}
	@Override
	public synchronized void consume(String str) {
		// TODO Auto-generated method stub
		if (str.charAt(0)=='#'){
			cl.setText("");
			cl.append(str.substring(1, str.length()-1)+"\n");
		}
		else{
			ta.append(str+"\n");
		}
		ta.setCaretPosition(ta.getDocument().getLength());

	}

	@Override
	public String infrom() {
		// TODO Auto-generated method stub
		return null;
	}
}

