package leek.byteShark.view;

import java.util.List;

import leek.byteShark.model.CRBDataModel.CRBData;
import lombok.Data;

/**Wrapper class around a set of BRC data used for proper HTML POST interaction
 * @author klee8
 *
 */
@Data
public class BRCWrapper {
	List<CRBData> data;
	
	public BRCWrapper(List<CRBData> data) {
		super();
		this.data = data;
	}
	
}
