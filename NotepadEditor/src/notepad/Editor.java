package notepad;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.print.PageFormat;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import static java.awt.event.InputEvent.CTRL_DOWN_MASK;

import java.awt.Color;
import java.awt.Font;
import java.awt.GraphicsEnvironment;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.UIManager;
public class Editor implements ActionListener{

	JFrame jf , jfr , jfr1;
	JMenuBar jmb;
	JMenu file , edit , format , help;
	JMenuItem neww , open , save , saveas , pagesetup , print , exit , cut  , copy , paste , replace , datetime , font  , fontcolour , textareacolour;
	JTextArea jt;
	JScrollPane js;
	Image icon;
	JFileChooser fc;
	File f;
	PrinterJob pj;
	JButton jb1 , jb2 , jb3;
	JTextField jt1 , jt2;
	JLabel jl1 , jl2;
	JComboBox jcb2_Fontsize , jcb_Font , jcb1_Fontstyle;
	JCheckBoxMenuItem wordwrap;
	
	public Editor()
	{
		
		try
		{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		
		jf = new JFrame("Untitled-Notepad");
		Image icon = Toolkit.getDefaultToolkit().getImage("D:\\Java Projects dev\\notepad.png");
		jf.setIconImage(icon);
		jf.setSize(500 , 500);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		jmb = new JMenuBar();
		jf.setJMenuBar(jmb);
		
		file = new JMenu("File");
		jmb.add(file);
		edit = new JMenu("Edit");
		jmb.add(edit);
		format = new JMenu("Format");
		jmb.add(format);
		help = new JMenu("Help");
		jmb.add(help);
		
		neww = new JMenuItem("New");
		neww.addActionListener(this);
		file.add(neww);
		neww.setAccelerator(KeyStroke.getKeyStroke('N', CTRL_DOWN_MASK));
		open = new JMenuItem("Open");
		file.add(open);
		open.addActionListener(this);
		open.setAccelerator(KeyStroke.getKeyStroke('O', CTRL_DOWN_MASK));
		save = new JMenuItem("Save");
		file.add(save);
		save.addActionListener(this);
		save.setAccelerator(KeyStroke.getKeyStroke('S', CTRL_DOWN_MASK));
		saveas = new JMenuItem("Save as");
		saveas.setAccelerator(KeyStroke.getKeyStroke('S' , InputEvent.SHIFT_DOWN_MASK));
		saveas.addActionListener(this);
		file.add(saveas);
		file.addSeparator();
		pagesetup = new JMenuItem("Page Setup");
		pagesetup.setAccelerator(KeyStroke.getKeyStroke('P' , InputEvent.SHIFT_DOWN_MASK));
		pagesetup.addActionListener(this);
		file.add(pagesetup);
		print = new JMenuItem("Print");
		print.setAccelerator(KeyStroke.getKeyStroke('P', CTRL_DOWN_MASK));
		print.addActionListener(this);
		file.add(print);
		file.addSeparator();
		exit = new JMenuItem("Exit");
		exit.setAccelerator(KeyStroke.getKeyStroke('E' , CTRL_DOWN_MASK));
		exit.addActionListener(this);
		file.add(exit);
		
		cut = new JMenuItem("Cut");
		edit.add(cut);
		cut.addActionListener(this);
		cut.setAccelerator(KeyStroke.getKeyStroke('X', CTRL_DOWN_MASK));
		copy = new JMenuItem("Copy");
		copy.setAccelerator(KeyStroke.getKeyStroke('C', CTRL_DOWN_MASK));
		copy.addActionListener(this);
		edit.add(copy);
		paste = new JMenuItem("Paste");
		paste.setAccelerator(KeyStroke.getKeyStroke('V', CTRL_DOWN_MASK));
		paste.addActionListener(this);
		edit.add(paste);
		edit.addSeparator();
		replace = new JMenuItem("Replace");
		replace.setAccelerator(KeyStroke.getKeyStroke('H', CTRL_DOWN_MASK));
		replace.addActionListener(this);
		edit.add(replace);
		edit.addSeparator();
		datetime = new JMenuItem("Time/Date");
		datetime.addActionListener(this);
		edit.add(datetime);
		
		wordwrap = new JCheckBoxMenuItem("Word Wrap");
		wordwrap.addActionListener(this);
		format.add(wordwrap);
		format.addSeparator();
		font = new JMenuItem("Font");
		font.addActionListener(this);
		format.add(font);
		fontcolour = new JMenuItem("Font color");
		fontcolour.addActionListener(this);
		format.add(fontcolour);
		textareacolour = new JMenuItem("Text area color");
		textareacolour.addActionListener(this);
		format.add(textareacolour);
		
		jt = new JTextArea();
		js = new JScrollPane(jt);
		js.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		js.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		jf.add(js);
		
		jf.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
	
		if(e.getSource() == exit)
		{
			System.exit(0);
		}
		if(e.getSource() == neww)
		{
			neww();
		}
		if(e.getSource() == saveas)
		{
			saveas();
		}
		if(e.getSource() == pagesetup)
		{
			pagesetup();
		}
		if(e.getSource() == print)
		{
			print();
		}
		if(e.getSource() == open)
		{
            try 
            {
				open();
			} 
            catch (Exception e1) 
            {
				e1.printStackTrace();
			}
		}
		if(e.getSource() == cut)
		{
			jt.cut();
		}
		if(e.getSource() == copy)
		{
			jt.copy();
		}
		if(e.getSource() == paste)
		{
			jt.paste();
		}
		if(e.getSource() == save)
		{
			try 
			{
				save();
			}
			catch (IOException e1) 
			{
				e1.printStackTrace();
			}
		}
		if(e.getSource() == replace)
		{
			replaceframe();
		}
		if(e.getSource() == jb1)
		{
			replace();
		}
		if(e.getSource() == datetime)
		{
			datetime();
		}
		if(e.getSource() == fontcolour)
		{
			fontcolour();
		}
		if(e.getSource() == textareacolour)
		{
			textareacolour();		
	    }	
		if(e.getSource() == font)
		{
			font();
		}
		if(e.getSource() == jb3)
		{
			OkButton();
		}
		if(e.getSource() == wordwrap)
		{
			boolean b = wordwrap.getState();
			jt.setLineWrap(b);
		}
	}
	//SAVEAS METHOD---------------------------------------------
	public void saveas()
	{
		fc = new JFileChooser();
		int i = fc.showSaveDialog(jf);
		f = fc.getSelectedFile();
		if(i == 0)
		{
			try
			{
				String text = jt.getText();
				byte[] b = text.getBytes();
				FileOutputStream fos = new FileOutputStream(f);
				fos.write(b);
				fos.close();
				jf.setTitle(f.getName());
			}
			catch(Exception e)
			{
				System.out.println(e);
			}
		}
		else
		{
			JOptionPane.showMessageDialog(jf, "Provide the file name" , "File not saved" , JOptionPane.WARNING_MESSAGE);
		}
	}
	
	//OPEN METHOD---------------------------------------------
	public void open() 
	{
		fc = new JFileChooser();
		int i = fc.showOpenDialog(jf);
		File f = fc.getSelectedFile();
		if(i == 0)
		{
			FileInputStream fis=null;
			try
			{
				jf.setTitle("");
				fis = new FileInputStream(f);
				int ii;
				while((ii=fis.read())!=-1)
				{
					jt.append(String.valueOf((char)ii));;
				}
			}
			catch(Exception e)
			{
				System.out.println(e);
			}
			finally
			{
				try
				{
				    fis.close();
				}
				catch(Exception e)
				{
					System.out.println(e);
				}
				jf.setTitle(f.getName());
			}
		}
		
	}
	//SAVE METHOD---------------------------------------------
	public void save() throws IOException
	{
		if(jf.getTitle().equals("Untitled-Notepad"))
		{
			saveas();
		}
		else
		{
			try
			{
				String text = jt.getText();
				byte[] b = text.getBytes();
				f = fc.getSelectedFile();
				FileOutputStream fos = new FileOutputStream(f);
				fos.write(b);
				fos.close();
				jf.setTitle(f.getName());
			}
			catch(Exception e)
			{
				System.out.println(e);
			}
		}
	}
	
	//NEW METHOD---------------------------------------------
	public void neww()
	{
		String text = jt.getText();
		if(!text.equals(""))
		{
			int i = JOptionPane.showConfirmDialog(jf, "Do you want to save this file?");
			if(i == 0)
			{
				try 
				{
					saveas();
					if(!jf.getTitle().equals("Untitled-Notepad"))
					{
						jf.setTitle("Untitled-Notepad");
						jt.setText("");
					}
				}
				catch (Exception e1) 
				{
					e1.printStackTrace();
				}
				//File f = fc.getSelectedFile();
				jf.setTitle(f.getName());
			}
			else if(i == 1)
			{
		  	     jt.setText("");
			}
			else
			{
				System.exit(0);
			}
		}
	}
	
	//PAGE SETUP METHOD---------------------------------------------
	public void pagesetup()
	{
		pj = PrinterJob.getPrinterJob();
		@SuppressWarnings("unused")
		PageFormat pf = pj.pageDialog(pj.defaultPage());
	}
	
	//PRINT METHOD--------------------------------------------------
	public void print()
	{
		 pj = PrinterJob.getPrinterJob();
		 if (pj.printDialog()) 
		 {
		     try 
		     {
		    	 pj.print();
		     }
		     catch (PrinterException exc)
		     {
		          System.out.println(exc);
		     }
		  }   
	}
	
	//REPLACE FRAME METHOD-------------------------------------------------
	public void replaceframe()
	{
		jfr = new JFrame("Replace");
		jfr.setSize(500 , 300);
		jfr.setLayout(null);
		
		jl1 = new JLabel("Find What :  ");
		jl1.setBounds(30 , 50 , 80 , 40);
		jfr.add(jl1);
		jt1 = new JTextField();
		jt1.setBounds(150 , 50 , 200 , 30);
		jfr.add(jt1);
		
		jl2 = new JLabel("Replace With :  ");
		jl2.setBounds(30 , 100 , 90 , 40);
		jfr.add(jl2);
		jt2 = new JTextField();
		jt2.setBounds(150 , 100 , 200 , 30);
		jfr.add(jt2);
		
		jb1 = new JButton("Replace");
		jb1.addActionListener(this);
		jb1.setBounds(200 , 200 , 80 , 30);
		jfr.add(jb1);
		
		jfr.setVisible(true);
	}
	//REPLACE METHOD--------------------------------------------------
	public void replace()
	{
		String find_what = jt1.getText();
		String replace_with = jt2.getText();
		String text = jt.getText();
		String new_text = text.replace(find_what , replace_with);
		jt.setText(new_text);
		jfr.setVisible(false);
	}
	//DATE TIME METHOD------------------------------------------------
	public void datetime()
	{
		LocalDateTime ldt = LocalDateTime.now();
	    String str = ldt.toString();
	    jt.append(str);
	}
	//FONT COLOR METHOD------------------------------------------------
	public void fontcolour()
	{
		Color c = JColorChooser.showDialog(jf , "Choose Font Color" , Color.black);
		jt.setForeground(c);;
	}
	//TEXT AREA COLOR METHOD------------------------------------------------
	public void textareacolour()
	{
		Color c = JColorChooser.showDialog(jf, "Choose Textarea Color", Color.white);
		jt.setBackground(c);
	}
	//FONT COLOR METHOD------------------------------------------------
	public void font()
	{
		jfr1 = new JFrame("Font");
	    jfr1.setSize(600 , 400);
		jfr1.setLayout(null);
		Image icon = Toolkit.getDefaultToolkit().getImage("D:\\Java Projects dev\\notepad.png");
		jfr1.setIconImage(icon);
		
		
		String[] str1 = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
		jcb_Font = new JComboBox(str1);
		jcb_Font.setBounds(50 , 50 , 150 , 30);
		jfr1.add(jcb_Font);
		
		String[] str2 = {"Plain", "Bold" , "Italic"};
		jcb1_Fontstyle = new JComboBox(str2);
		jcb1_Fontstyle.setBounds(250 , 50 , 150 , 30);
		jfr1.add(jcb1_Fontstyle);
		
		Integer[] str3 = {10 , 15 , 20 , 25 , 30 , 35 , 40 , 45 , 50 , 55 , 60 , 65 , 70};
		jcb2_Fontsize = new JComboBox(str3);
		jcb2_Fontsize.setBounds(450 , 50 , 100 , 30);
		jfr1.add(jcb2_Fontsize);
		
		jb3 = new JButton("OK");
		jb3.setBounds(250 , 200 , 100 , 40);
		jb3.addActionListener(this);
		jfr1.add(jb3);
	
		jfr1.setVisible(true);
		
	}
	
	public void OkButton()
	{
		String str = (String)jcb_Font.getSelectedItem();
		String str2 = (String)jcb1_Fontstyle.getSelectedItem();
		Integer str3 = (Integer)jcb2_Fontsize.getSelectedItem();
		
		int str21 = 0;
		if(str2.equals("PLAIN"))
		{
			str21 = 0;
		}
		if(str2.equals("BOLD"))
		{
			str21 = 1;
		}
		if(str2.equals("ITALIC"))
		{
			str21 = 2;
		}
		
		Font f = new Font(str , str21 , str3);
		jt.setFont(f);
		
		jfr1.setVisible(false);
	}
}

