package com.shinysideup.udatm.lib;

import java.util.Hashtable;
import java.util.Map;

import com.shinysideup.udatm.lib.dao.DeviceOrThingDAO;
import com.shinysideup.udatm.lib.dao.DevicesAndThingsDAO;

/**
 * @author Mike Worley
 */
public class DeviceOrThingCollection {

	private Map<Long, DeviceOrThing> dots = new Hashtable<>();

	private DevicesAndThingsDAO datDAO;

	private boolean shutdown = false;

	public DeviceOrThingCollection(DevicesAndThingsDAO datDAO) {
		this.datDAO = datDAO;
	}

	public DeviceOrThing getDeviceOrThing(Long dotId) throws NoDataFoundException, DeviceOrThingException {
		synchronized (dots) {
			if (shutdown)
				throw new NoDataFoundException("Shutting down");
			if (dots.containsKey(dotId))
				return dots.get(dotId);
			DeviceOrThing dot = new DeviceOrThing(new DeviceOrThingDAO(this.datDAO, dotId));
			dots.put(dotId, dot);
			return dot;
		}
	}

	public void removeDeviceOrThing(Long dotId) {
		synchronized (dots) {
			if (!shutdown) {
				if (dots.containsKey(dotId)) {
					dots.remove(dotId).shutdown();
				}
			}
		}
	}

	public void shutdown() {
		synchronized (dots) {
			if (!shutdown) {
				shutdown = true;
				for (Long dotId : dots.keySet()) {
					removeDeviceOrThing(dotId);
				}
			}
		}
	}

}
