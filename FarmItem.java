public interface FarmItem {
	void add(FarmItem item); // Insert item from the object.
	void remove(FarmItem item); // Remove item from the object.
	double getPrice(); // Return the object's total value.
	String getName(); // Return the object's name.
	void display(String indent); // Print the object, anything inside it, as an indented list.
}
// add and remove belong here because of the fact that containers can implement these methods,
// although individual items do not need to themselves.
