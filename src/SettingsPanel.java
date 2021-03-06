import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class SettingsPanel extends JPanel{
	private static final long serialVersionUID = 1L;

	public static boolean RECOG_EVERY_FRAME = true;
	public static boolean LOCK_BOUNDS = false;

	public static int RECOG_THRESH = 25;

	public SettingsPanel()
	{
		super();
		setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
		add(new JLabel("Settings"));

		JCheckBox recog = new JCheckBox("Only trigger recognition manually",!RECOG_EVERY_FRAME);
		recog.setToolTipText("Only trigger recognition manually");
		recog.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				RECOG_EVERY_FRAME = !recog.isSelected();
			}
		});
		add(recog);

		JCheckBox lock = new JCheckBox("Lock Recognition Bounds",LOCK_BOUNDS);
		lock.setToolTipText("Lock Recognition Bounds");
		lock.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				LOCK_BOUNDS = lock.isSelected();
			}
		});
		add(lock);

		JSlider thresh = new JSlider(JSlider.HORIZONTAL, 0, 100, RECOG_THRESH);
		JLabel score = new JLabel("Score Threshold: "+RECOG_THRESH);
		thresh.addChangeListener(new ChangeListener(){
			@Override
			public void stateChanged(ChangeEvent arg0) {
				RECOG_THRESH = thresh.getValue();
				score.setText("Score Threshold: "+RECOG_THRESH);
			}
		});
		add(score);
		add(thresh);

		JButton loadAll = new JButton("Load all sets");
		loadAll.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				RecogApp.select.loadAll();
			}
		});
		add(loadAll);
		
		JButton launchScreenGrab = new JButton("Launch Screen Grab");
		launchScreenGrab.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				ScreenRegionGrab.init();
			}
		});
		add(launchScreenGrab);
		
		JButton launchPopout = new JButton("Launch Card Popout");
		launchPopout.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				new PopoutCardWindow();
			}
		});
		add(launchPopout);
		
		JButton launchSetGen = new JButton("Launch Set Generator");
		launchSetGen.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				new SetGenerator();
			}
		});
		add(launchSetGen);
		
	}
}
