package com.shinysideup.udatm.lib;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import com.shinysideup.udatm.lib.dao.DeviceOrThingDAO;
import com.shinysideup.udatm.lib.io.IOProtocol;
import com.shinysideup.udatm.lib.pojo.DOTConfigurationContent;
import com.shinysideup.udatm.lib.pojo.DOTModelFirmwareVersionConfigurationContent;
import com.shinysideup.udatm.lib.pojo.DOTModelFirmwareVersionScriptContent;
import com.shinysideup.udatm.lib.pojo.DOTModelFirmwareVersionScriptDetails.ScriptProcessorType;
import com.shinysideup.udatm.lib.util.GroovyScriptProcessor;

/**
 * @author Mike Worley
 */
public class DeviceOrThingScriptAccess {

	public static final String COMMAND_SCRIPT_NAME = "private.commands";
	public static final String COMMAND_NAME_KEY = "commandName";
	public static final String SCRIPT_ACCESS_NAME_KEY = "scriptAccess";

	private final DeviceOrThingDAO datDAO;
	private final List<IOProtocol> ioProtocols;

	public DeviceOrThingScriptAccess(DeviceOrThingDAO datDAO, List<IOProtocol> ioProtocols) {
		this.datDAO = datDAO;
		this.ioProtocols = ioProtocols;
	}

	public String runCommand(String commandName, Map<String, Object> parameters)
			throws NoDataFoundException, DeviceOrThingException {
		if (parameters == null)
			parameters = new Hashtable<String, Object>();
		parameters.put(COMMAND_NAME_KEY, commandName);
		return this.runScript(COMMAND_SCRIPT_NAME, parameters);
	}

	public String runScript(String scriptName, Map<String, Object> parameters)
			throws NoDataFoundException, DeviceOrThingException {
		if (parameters == null)
			parameters = new Hashtable<String, Object>();
		parameters.put(SCRIPT_ACCESS_NAME_KEY, this);
		DOTModelFirmwareVersionScriptContent scriptContent = this.getScript(scriptName);
		if (scriptContent.getDetails().getScriptProcessorType().equals(ScriptProcessorType.Groovy)) {
			try {
				Object result = GroovyScriptProcessor.runGroovyScript(scriptContent.getScript(), parameters);
				if (result != null)
					return result.toString();
				return "";
			} catch (GeneralException e) {
				throw new DeviceOrThingException(e);
			}
		} else {
			throw new NoDataFoundException(scriptContent.getDetails().getScriptProcessorType() + " : is not supported");
		}
	}

	public DOTModelFirmwareVersionConfigurationContent getModelConfig(String configName) throws NoDataFoundException {
		return this.datDAO.getDOTModelFirmwareVersionConfiguration(configName);
	}

	public DOTConfigurationContent getConfig(String configName) throws NoDataFoundException {
		return this.datDAO.getDOTConfiguration(configName);
	}

	public List<IOProtocol> getIOProtocols() {
		return this.ioProtocols;
	}

	private DOTModelFirmwareVersionScriptContent getScript(String scriptName) throws NoDataFoundException {
		return this.datDAO.getDOTModelFirmwareVersionScriptContent(scriptName);
	}
}
