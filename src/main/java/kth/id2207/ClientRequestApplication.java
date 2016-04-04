/**
 * 
 */
package kth.id2207;

import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import kth.id2207.data.ClientRequestDAO;
import kth.id2207.service.ClientRequestService;

/**
 * @author pradeeppeiris
 *
 */
public class ClientRequestApplication extends Application<ClientRequestConfiguration> {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		new ClientRequestApplication().run(args);

	}
	
	public void initialize(Bootstrap<ClientRequestConfiguration> bootstrap) {
		bootstrap.addBundle(new AssetsBundle());
//		bootstrap.addBundle(new AssetsBundle("/assets/", "/"));
	}
	
	public void run(ClientRequestConfiguration configuration, Environment environment) throws Exception {
		ClientRequestDAO requestDAO = new ClientRequestDAO(configuration.getData());
		final ClientRequestService requestReousrce = new ClientRequestService(requestDAO);
		environment.jersey().register(requestReousrce);
	}

}
