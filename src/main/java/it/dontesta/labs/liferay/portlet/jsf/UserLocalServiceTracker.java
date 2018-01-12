/**
 * 
 */
package it.dontesta.labs.liferay.portlet.jsf;

import org.osgi.framework.BundleContext;
import org.osgi.util.tracker.ServiceTracker;

import com.liferay.portal.kernel.service.UserLocalService;

/**
 * @author amusarra
 *
 */
public class UserLocalServiceTracker extends ServiceTracker<UserLocalService, UserLocalService> {

	public UserLocalServiceTracker(BundleContext bundleContext) {
        super(bundleContext, UserLocalService.class, null);
    }
}
