package View;

	import java.awt.Color;
	import java.awt.event.ActionEvent;
	import java.awt.event.KeyEvent;

	import javax.swing.AbstractAction;
	import javax.swing.ImageIcon;
	import javax.swing.JColorChooser;
	import javax.swing.JPanel;
	import javax.swing.KeyStroke;

	public class ColorSwatch extends AbstractAction {
		
		private Color myColor;
		private static final long serialVersionUID = 1L;
		
		private final JPanel myPanel;

		public ColorSwatch(final String theText, final JPanel thePanel) {
			super(theText);

			myPanel = thePanel;

			putValue(MNEMONIC_KEY, KeyEvent.VK_C);
			putValue(ACCELERATOR_KEY, 
					KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.ALT_MASK));


			final String imgLocation = "images/color.gif";

			putValue(SMALL_ICON, new ImageIcon(imgLocation, theText));
		}

		@Override
		public void actionPerformed(ActionEvent theEvent) {
			final Color result = JColorChooser.showDialog(null, "A Color Chooser", null);
	        if (result != null) {
	            myPanel.setBackground(result);
	            this.myColor = result;
	        }
		}
		
		public Color getColor() {
			return this.myColor;
		}
}
