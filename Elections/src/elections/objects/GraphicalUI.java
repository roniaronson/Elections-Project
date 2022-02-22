package elections.objects;

import javax.swing.JOptionPane;

public class GraphicalUI implements Messageable {

	@Override
	public void showMessage(String message) {
		JOptionPane.showMessageDialog(null, message);
	}

	@Override
	public String getString(String message) {
		return JOptionPane.showInputDialog(message);
	}

	@Override
	public int getInt(String message) {
		return Integer.parseInt(JOptionPane.showInputDialog(message));
	}

	@Override
	public boolean getBoolean(String message) {
		return Boolean.parseBoolean(JOptionPane.showInputDialog(message));
	}
}
