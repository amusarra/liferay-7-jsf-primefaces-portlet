/**
 * 
 */
package it.dontesta.labs.liferay.portlet.jsf;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;

import com.liferay.faces.util.logging.Logger;
import com.liferay.faces.util.logging.LoggerFactory;
import com.liferay.portal.kernel.service.UserLocalService;

import it.dontesta.labs.services.temperature.model.Temperature;
import it.dontesta.labs.services.temperature.service.TemperatureLocalService;

/**
 * @author amusarra
 *
 */

@ManagedBean (name ="registeredUser")
@RequestScoped
public class ExampleAccessToOSGiService {

	@PostConstruct
	public void postConstruct() {
		try {
			Bundle bundle = FrameworkUtil.getBundle(this.getClass());
			BundleContext bundleContext = bundle.getBundleContext();
			
			userLocalServiceTracker = new UserLocalServiceTracker(bundleContext);
			userLocalServiceTracker.open();

			if (!userLocalServiceTracker.isEmpty()) {
				UserLocalService userLocalService = userLocalServiceTracker.getService();
				countRegisteredUser = userLocalService.getUsersCount();
				
				_log.info("Liferay total users: " + countRegisteredUser);
			}
			else {
				_log.error("User service is temporarily unavailable");
			}

			temperatureLocalServiceTracker = new TemperatureLocalServiceTracker(bundleContext);
			temperatureLocalServiceTracker.open();

			if (!temperatureLocalServiceTracker.isEmpty()) {
				TemperatureLocalService temperatureLocalService = temperatureLocalServiceTracker.getService();
				
				Temperature temperature = temperatureLocalService.getEntry(1987l);
				deviceTemperatureValue = temperature.getValue();
				deviceId = temperature.getDeviceId();
				
				_log.info("Temperature for DeviceId " 
						+ temperature.getDeviceId() 
						+ " is " 
						+ temperature.getValue()
						+ "°");
			}
			else {
				_log.error("Temperature service is temporarily unavailable");
			}

		}
		catch (Exception e) {
			_log.error(e.getMessage(), e);
		}
	}
	
	
	@PreDestroy
	public void preDestroy() {
		userLocalServiceTracker.close();
		temperatureLocalServiceTracker.close();
	}


	public int getCountRegisteredUser() {
		return countRegisteredUser;
	}

	public void setCountRegisteredUser(int countRegisteredUser) {
		this.countRegisteredUser = countRegisteredUser;
	}

	public int getDeviceTemperatureValue() {
		return deviceTemperatureValue;
	}

	public void setDeviceTemperatureValue(int deviceTemperatureValue) {
		this.deviceTemperatureValue = deviceTemperatureValue;
	}
	
	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	private int countRegisteredUser;
	private int deviceTemperatureValue;
	private String deviceId;
	private UserLocalServiceTracker userLocalServiceTracker;
	private TemperatureLocalServiceTracker temperatureLocalServiceTracker;
	private static final Logger _log = LoggerFactory.getLogger(ExampleAccessToOSGiService.class);
}
