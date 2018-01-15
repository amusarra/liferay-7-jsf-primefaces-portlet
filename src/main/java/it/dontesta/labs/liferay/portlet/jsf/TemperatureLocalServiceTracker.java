/**
 * 
 */
package it.dontesta.labs.liferay.portlet.jsf;

import org.osgi.framework.BundleContext;
import org.osgi.util.tracker.ServiceTracker;

import it.dontesta.labs.services.temperature.service.TemperatureLocalService;

/**
 * @author amusarra
 *
 */
public class TemperatureLocalServiceTracker extends ServiceTracker<TemperatureLocalService, TemperatureLocalService> {

	public TemperatureLocalServiceTracker(BundleContext bundleContext) {
        super(bundleContext, TemperatureLocalService.class, null);
    }
}