import java.util.List;
import javafx.scene.layout.Pane;

public interface FarmItem {
	void add(FarmItem item); // Insert item from the object.
	void remove(FarmItem item); // Remove item from the object.
	double getPrice(); // Return the object's total value.
	String getName(); // Return the object's name.
	void display(String indent); // Print the object, anything inside it, as an indented list.
	double getX(); // top left corner of the pane
	double getY(); // top left corner of the pane
	double getWidth(); //size in pixels
	double getHeight(); // size in pixels 
	void draw(Pane pane); // add this objects shapes and those of everything inside of it
	void collect(List<FarmItem> out); // add this object, thjem everything inside to out
}
// add and remove belong here because of the fact that containers can implement these methods,
// although individual items do not need to themselves.
