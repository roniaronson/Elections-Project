package elections.objects;

public interface Messageable {
	void showMessage(String msg);

	String getString(String msg);

	int getInt(String msg);

	boolean getBoolean(String msg);
}
