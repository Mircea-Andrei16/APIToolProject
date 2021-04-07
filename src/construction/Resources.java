package construction;
// is a resource class for storing the label names in a hash map
import java.util.HashMap;
import java.util.Map;

public class Resources {

	private Map<String,String> resourcesLabel;

	public Resources() {
     
		resourcesLabel = new HashMap<>();
		
        resourcesLabel.put("openApiLabel", "OpenAPI URL: ");
		
		resourcesLabel.put("servicesLabel", "Services: ");
	
		resourcesLabel.put("portsLabel", "Ports: ");
		
		resourcesLabel.put("operationsLabel", "Operations: ");
		
		resourcesLabel.put("outputFileLabel", "Output File: ");
		
	}
	
	public String getLabel(String operationKey) {
		
		return resourcesLabel.get(operationKey);
		
	}
	
	
	
	
	
}
