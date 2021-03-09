package model.interfaces;

import java.io.IOException;
import java.util.List;

import model.CRBDataModel.CRBLine;

/**Data file builder that for takes in data and 
 * @author klee8
 *
 * @param <T> - The data type to be imported & exported
 */
public interface IDataFormatter <T> {

	/** Interpret the designated string as a object of the template type and add them to the list of data
	 * @param input - String representation of a single object
	 * @throws IOException
	 */
	public void appendFromString (String input) throws IOException;
	
	
	/** Interprete the designed list of strings as objects of the template type and add them to the list of data
	 * @param input - A list of String representations of the object
	 * @throws IOException
	 */
	public void appendFromString (List <String> input) throws IOException;
	
	/** Intake the designated object and add it to the list of data
	 * @param input
	 */
	public void appendFromDataLine (T input);
	
	/** Intake the designated list of objects and add it to the list of data
	 * @param inputs
	 */
	public void appendFromDataLine (List <T> inputs);
	
	/**Output data for system use in a list format
	 * @return
	 */
	public List<T> outputData ();
	
	/**Clear the Formatter of all added data
	 * 
	 */
	public void clear();

	
}
