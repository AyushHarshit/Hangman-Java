//package game;
import java.util.Random;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
public class GameWindow extends JFrame implements ActionListener
{
	int len,count,rnd,chance,flag;
	String word[]={"JAPAN","QATAR","SYRIA","MONGOLIA","BAHARAIN","INDIA","PAKISTAN","AUSTRALIA","AFGHANISTAN","CHINA"};
	Random rd;
	StringBuffer blanks;
	
	JButton[] button= new JButton[26];
	JLabel lblword,lblmessage;
	JPanel keypad,key1,key2,key3,screen,notify;
	public GameWindow()
	{
		count=0;
		chance=0;
		rd = new Random();
		blanks = new StringBuffer();		
		
		
		
		button[0] = new JButton("A");
		button[1] = new JButton("B");
		button[2] = new JButton("C");
		button[3] = new JButton("D");
		button[4] = new JButton("E");
		button[5] = new JButton("F");
		button[6] = new JButton("G");
		button[7] = new JButton("H");
		button[8] = new JButton("I");
		button[9] = new JButton("J");
		button[10] = new JButton("K");
		button[11] = new JButton("L");
		button[12] = new JButton("M");
		button[13] = new JButton("N");
		button[14] = new JButton("O");
		button[15] = new JButton("P");
		button[16] = new JButton("Q");
		button[17] = new JButton("R");
		button[18] = new JButton("S");
		button[19] = new JButton("T");
		button[20] = new JButton("U");
		button[21] = new JButton("V");
		button[22] = new JButton("W");
		button[23] = new JButton("X");
		button[24] = new JButton("Y");
		button[25] = new JButton("Z");
		
		lblmessage = new JLabel("Guess the country's name");
		lblmessage.setFont(new Font("serif",Font.PLAIN,20));
		
		lblword = new JLabel();
		lblword.setFont(new Font("serif",Font.PLAIN,35));
		
		notify = new JPanel();
		notify.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0,0,0)));
		
		screen = new JPanel();
		screen.setBackground(Color.WHITE);
		screen.setSize(0,200);
		
		
		keypad = new JPanel();
		keypad.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0,0,0)));
		keypad.setBackground(Color.BLACK);
		
		key1 = new JPanel();
		key1.setBackground(Color.BLACK);
		
		key2 = new JPanel();
		key2.setBackground(Color.BLACK);
		
		key3 = new JPanel();
		key3.setBackground(Color.BLACK);
		
	
		
		
		setTitle("Hangman");
		setSize(500,450);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public void addComponent()
	{
		GridBagConstraints gc = new GridBagConstraints();
		
		notify.setLayout(new FlowLayout());
		notify.add(lblmessage);
		
		screen.setLayout(new GridBagLayout());
		screen.add(lblword, gc);
		
		keypad.setLayout(new GridBagLayout());
		
		key1.setLayout(new FlowLayout());
		key1.add(button[16]);
		key1.add(button[22]);
		key1.add(button[4]);
		key1.add(button[17]);
		key1.add(button[19]);
		key1.add(button[24]);
		key1.add(button[20]);
		key1.add(button[8]);
		key1.add(button[14]);
		key1.add(button[15]);
		
		gc.gridx = 0;
		gc.gridy = 0;
		keypad.add(key1,gc);
		
		key2.setLayout(new FlowLayout());
		key2.add(button[0]);
		key2.add(button[18]);
		key2.add(button[3]);
		key2.add(button[5]);
		key2.add(button[6]);
		key2.add(button[7]);
		key2.add(button[9]);
		key2.add(button[10]);
		key2.add(button[11]);
		
		gc.gridx = 0;
		gc.gridy = 1;
		keypad.add(key2,gc);
		
		key3.setLayout(new FlowLayout());
		key3.add(button[25]);
		key3.add(button[23]);
		key3.add(button[2]);
		key3.add(button[21]);
		key3.add(button[1]);
		key3.add(button[13]);
		key3.add(button[12]);
		
		gc.gridx = 0;
		gc.gridy = 2;
		keypad.add(key3,gc);
		
		setLayout(new BorderLayout());
		add(notify, BorderLayout.NORTH);
		add(screen, BorderLayout.CENTER);
		add(keypad, BorderLayout.SOUTH);
		
		for(int w=0 ; w<=25 ; w++)
		{
		button[w].addActionListener(this);
		}
		
	}
	public void startGame()
	{
		addComponent();
		
		rnd= rd.nextInt(10);
		len= word[rnd].length();
		
		for(int j=0;j<len;j++)
		{
			blanks.append("_  ");
		}
		lblword.setText(blanks.toString());
	}
	public void actionPerformed(ActionEvent ae)
	{
		
		
		JButton jb = (JButton) ae.getSource();
		String letter = ae.getActionCommand();
		
		flag = 0;
		
		for(int loop =0 ; loop<len; loop++)
		{
			if(letter.charAt(0) == word[rnd].charAt(loop))
			{
				flag = 1;
				blanks.replace((loop*3),((loop*3)+1),letter);
				count++;
			}
		}
		if(flag==1)
		{
			lblword.setText(blanks.toString());
			jb.setBackground(Color.GREEN);
			jb.setEnabled(false);
		}
		else
		{
			jb.setBackground(Color.RED);
			jb.setEnabled(false);
			chance++;
		}
		if(count==len)
		{
			JOptionPane.showMessageDialog(this,"Congrats You Guessed the Word Correctly" );
			int n = JOptionPane.showConfirmDialog(this,"Do You Want To Play Again?","Restart Game",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
			
			if(n==JOptionPane.YES_OPTION)
			{
				GameWindow obj = new GameWindow();
				obj.startGame();
				this.dispose();
			}
			else
			{
				this.dispose();
				Menu obj = new Menu();
				obj.addComponent();
			}
		}
		if(chance>=5)
		{
			JOptionPane.showMessageDialog(this,"Sorry You Lost Your Chances"+"\nThe Correct Answer is"+word[rnd]);
			int n = JOptionPane.showConfirmDialog(this,"Do You Want To Play Again?","Restart Game",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
			
			if(n==JOptionPane.YES_OPTION)
			{
				GameWindow obj = new GameWindow();
				obj.startGame();
				this.dispose();
			}
			else
			{
				this.dispose();
				Menu obj = new Menu();
				obj.addComponent();
			}
		}
	}
}
































