/**
 * 
 */
package it.dontesta.labs.liferay.portlet.jsf;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;

import com.liferay.faces.util.logging.Logger;
import com.liferay.faces.util.logging.LoggerFactory;

import it.dontesta.labs.services.temperature.model.Temperature;
import it.dontesta.labs.services.temperature.service.TemperatureLocalService;

/**
 * @author amusarra
 *
 */
@ManagedBean
@ViewScoped
public class DataGridView implements Serializable {

	@PostConstruct
	public void postConstruct() {
		try {
			Bundle bundle = FrameworkUtil.getBundle(this.getClass());
			BundleContext bundleContext = bundle.getBundleContext();
			temperatureLocalServiceTracker = new TemperatureLocalServiceTracker(bundleContext);
			temperatureLocalServiceTracker.open();

			if (!temperatureLocalServiceTracker.isEmpty()) {
				TemperatureLocalService temperatureLocalService = temperatureLocalServiceTracker.getService();
				
				temperatures = temperatureLocalService.getTemperatures(-1, -1);

				_log.info("Temperature registered count " 
						+ temperatures.size());
			}
			else {
				_log.error("Temperature service is temporarily unavailable");
			}

		}
		catch (Exception e) {
			_log.error(e.getMessage(), e);
		}	
	}
	
	public List<Temperature> getTemperatures() {
        return temperatures;
    }
	
	public Temperature getSelectedTemperature() {
        return selectedTemperature;
    }
 
    public void setSelectedTemperature(Temperature selectedTemperature) {
        this.selectedTemperature = selectedTemperature;
    }
    
	private List<Temperature> temperatures;
	private Temperature selectedTemperature;

	private TemperatureLocalServiceTracker temperatureLocalServiceTracker;
	private static final Logger _log = LoggerFactory.getLogger(DataGridView.class);
	private static final long serialVersionUID = -5212057358494157536L;

}
