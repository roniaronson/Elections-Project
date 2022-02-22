package elections.objects;

import java.util.Vector;

public class Set<T extends Citizen> {
	private Vector <T> items;
	
	public Set() {
	items = new Vector<T>();
	}
	
	public boolean add(T object) {
		for (int i = 0; i < items.size(); i++) {
			if(items.elementAt(i).equals(object))
				return false;
		}
		items.add(object);
		return true;
	}
	
	public boolean remove(T object) {
		for (int i = 0; i < items.size(); i++) {
			if(items.elementAt(i).equals(object)) {
				items.remove(i);
				return true;
			}
		}
				return false;
	}
	
	public int size() {
		return items.size();
	}
	
	public T elementAt(int index) {
		return items.elementAt(index);
	}
	
	public T findById(int id) {
		for (int i = 0; i < items.size(); i++) {
			if (items.elementAt(i).getId() == id && items.elementAt(i)!=null)
				return items.elementAt(i);
		}
		return null;
	}
	
	@Override
	public String toString() {
		StringBuffer str= new StringBuffer("");
		for (int i = 0; i < items.size(); i++) {
			str.append(items.elementAt(i).toString()+"\n");
		}
		return str.toString();
	}
}
