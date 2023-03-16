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

	public DeviceOrThing getDeviceOrThing(Long dotId) throws NoDataFoundException {
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

	public void updateDeviceOrThing(Long dotId) {
		synchronized (dots) {
			if (!shutdown) {
				if (dots.containsKey(dotId)) {
					dots.get(dotId).update();
				}
			}
		}
	}

	public void removeDeviceOrThing(Long dotId) {
		synchronized (dots) {
			if (!shutdown) {
				if (dots.containsKey(dotId)) {
					dots.remove(dotId).disconnectConnections();
				}
			}
		}
	}

	public void disconnectDOTConnections() {
		synchronized (dots) {
			if (!shutdown) {
				for (Long key : dots.keySet()) {
					dots.get(key).disconnectConnections();
				}
			}
		}
	}

	public void shutdown() {
		synchronized (dots) {
			if (!shutdown) {
				for (Long key : dots.keySet()) {
					dots.get(key).shutdown();
				}
				dots.clear();
				shutdown = true;
			}
		}
	}

}
