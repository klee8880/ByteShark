package leek.byteShark.model.interfaces;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

/**Data file builder that for takes in data and 
 * @author klee8
 *
 * @param <T> - The data type to be imported & exported
 */
public interface IDataFormatter <T> {

	/** Interpret the designated string as a object of the template type and add them to the list of data
	 * @param input - String representation of a single object
	 * @throws IllegalArgumentException
	 */
	public void appendFromString (String input) throws IllegalArgumentException;
	
	
	/** Interprete the designed list of strings as objects of the template type and add them to the list of data
	 * @param input - A list of String representations of the object
	 * @throws IllegalArgumentException
	 */
	public void appendFromString (List <String> input) throws IllegalArgumentException;
	
	/** Intake the designated object and add it to the list of data
	 * @param input
	 * @throws IOException 
	 */
	public void appendFromDataLine (T input) throws IllegalArgumentException;
	
	/** Intake the designated list of objects and add it to the list of data
	 * @param inputs
	 * @throws IllegalArgumentException 
	 */
	public void appendFromDataLine (List <T> inputs) throws IllegalArgumentException;

	/**Output data for system use in a list format
	 * @return
	 */
	public List<T> outputData ();
	
	/**Clear the Formatter of all added data
	 * 
	 */
	public void clear();


	public Iterator<T> iterator();

	
}
