/**
 * 
 */
package kth.id2207;

import io.dropwizard.Configuration;

/**
 * @author rafa
 *
 */
public class ClientRequestConfiguration extends Configuration {

	private String data;

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}
	
}
