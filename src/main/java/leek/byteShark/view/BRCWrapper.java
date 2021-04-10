package leek.byteShark.view;

import java.util.List;

import leek.byteShark.model.CRBDataModel.CRBData;

/**Wrapper class around a set of BRC data used for proper HTML POST interaction
 * @author klee8
 *
 */
public class BRCWrapper {
	List<CRBData> data;
	
	String response = "";
	
	public BRCWrapper() {
		super();
		this.data = null;
	}
	
	public BRCWrapper(List<CRBData> data) {
		super();
		this.data = data;
	}

	public List<CRBData> getData() {
		return data;
	}

	public void setData(List<CRBData> data) {
		this.data = data;
	}

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}
	
}
