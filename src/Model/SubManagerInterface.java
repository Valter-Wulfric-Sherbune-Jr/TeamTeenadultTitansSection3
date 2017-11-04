package Model;

import java.util.HashMap;

public interface SubManagerInterface {
	
	/* Import all the information of one of the subfolder 
	 * hierarchy to the main game folder and create individual object
	 * for all item in the subfolder by looping makeListObject
	 * */
	public void makeList(String gameFolder);
	
	/* Depending on the subfolder name, it create all object of it 
	 * by parsing the data from the text file in that subfolder 
	 * */
	public void makeListObject(String filePath);
	
	/* Retrieve the object and executing toString by accessing the
	 * hashmap of the object list
	 * */
	public void loadListId(String Id);
	
	/*Return the HashMap with all the object added to the list
	 * Key: ID, Value: SubFolderObject
	 * */
	public HashMap<String, ?> getList();
}
