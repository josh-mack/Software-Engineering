package Estuary;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;

public class DNERR extends JComponent
{
	short level = 1;
	ImageIcon building;
	
	public DNERR()
	{
		
		setLayout(new BorderLayout());
		building = new ImageIcon("imgs/level1.png");
		JLabel label = new JLabel(building);
		label.setBounds(0, 0, building.getIconWidth(), building.getIconHeight());
		setBounds(0,0,building.getIconWidth(), building.getIconHeight());
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setVerticalAlignment(JLabel.CENTER);
		add(label);
		
		JButton upgrade = new JButton("Upgrade");
		upgrade.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				upgrade();
			}
		});
	}
		

	void upgrade()
	{
		switch(level)
		{
		case 2:
			Environment.money -= 200;
			building = new ImageIcon("img/level2");
			break;
		case 3:
			Environment.money -= 500;
			building = new ImageIcon("imgs/level3");
			break;
		default:
			building = new ImageIcon("img.level2");
		}
	}
	
	
}
