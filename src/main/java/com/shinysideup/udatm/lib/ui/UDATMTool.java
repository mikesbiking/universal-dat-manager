package com.shinysideup.udatm.lib.ui;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Hashtable;

import com.shinysideup.udatm.lib.Result;
import com.shinysideup.udatm.lib.io.FileCaptureIOProtocolDescriptor;
import com.shinysideup.udatm.lib.io.HTTPIOProtocolDescriptor;
import com.shinysideup.udatm.lib.io.IOProtocol;
import com.shinysideup.udatm.lib.io.IOProtocolDescriptor;
import com.shinysideup.udatm.lib.pojo.DOTModelFirmwareVersionScriptDetails;
import com.shinysideup.udatm.lib.pojo.DOTModelFirmwareVersionScriptDetails.ScriptProcessorType;
import com.shinysideup.udatm.lib.pojo.DOTType;
import com.shinysideup.udatm.lib.util.DATUtil;
import com.shinysideup.udatm.lib.util.GroovyScriptProcessor;
import com.shinysideup.udatm.lib.util.JsonUtil;

public class UDATMTool {

	/**
	 * NOTE: This will be the main UI class. Currently using for trying things
	 */
	public static void main(String[] args) throws Exception {
		// testSerialization();
		// testGroovyScript();
		// testIOPRelection();
		// testScriptSerialization();
		// testResult();
		testDATUtil();

	}

	public static void testDATUtil() {
		for (int i = 0; i < 100; i++) {
			System.out.println(DATUtil.generateUUID());
			System.out.println(DATUtil.getRandomLongValue());
		}
	}

	public static void testResult() {
		Result result = new Result();
		result.setResult("1");
		result.setResult("2");
		System.out.println(result.getResult());
		result.setResult(null);
		result.setResult("1");
		result.setResult("2");
		result.setResult("3");
		result.setResult("4");
		System.out.println(result.getResult());

	}

	public static void testScriptSerialization() throws Exception {

		DOTModelFirmwareVersionScriptDetails scriptDetails = new DOTModelFirmwareVersionScriptDetails();
		scriptDetails.setScriptProcessorType(ScriptProcessorType.Groovy);
		String json = JsonUtil.serialize(scriptDetails);
		System.out.println(json);
		scriptDetails = JsonUtil.deserialize(json, DOTModelFirmwareVersionScriptDetails.class);
		System.out.println(scriptDetails.getScriptProcessorType());
	}

	public static void testSerialization() throws Exception {
		FileCaptureIOProtocolDescriptor iopd = new FileCaptureIOProtocolDescriptor();
		iopd.setFilePath("c:\\temp\\udatm\\test.out");
		iopd.setAppend(true); //

		HTTPIOProtocolDescriptor httpiopd = new HTTPIOProtocolDescriptor();
		httpiopd.setConnectionAddress("sdfsafgsd");

		ArrayList<IOProtocolDescriptor> iopdlist = new ArrayList<>();
		iopdlist.add(iopd);
		iopdlist.add(httpiopd);
		String json = JsonUtil.serialize(iopdlist);
		System.out.println(json);

		iopdlist = JsonUtil.deserializeArrayList(json, IOProtocolDescriptor.class);

		System.out.println(iopdlist.get(0).getClass());
		System.out.println(iopdlist.get(1).getClass());

		DOTType type = new DOTType();
		json = JsonUtil.serialize(type);
		System.out.println(json);

	}

	public static void testGroovyScript() throws Exception {

		ArrayList<String> alparam = new ArrayList<>();
		alparam.add("initial item");

		Hashtable<String, Object> map = new Hashtable<>();
		map.put("hello", "Hello World from object");
		map.put("alparam", alparam);
		Object resobj = GroovyScriptProcessor.runGroovyScript("return " + "hello.toString();", map);
		System.out.println(resobj);
		System.out.println(GroovyScriptProcessor.runGroovyScript("return " + "\"hello\";", map));
		System.out.println(alparam);
		System.out.println(GroovyScriptProcessor.runGroovyScript("alparam.add(\"second item\");", map));
		System.out.println(alparam);

	}

	public static void testIOPRelection() throws Exception {
		FileCaptureIOProtocolDescriptor iopd = new FileCaptureIOProtocolDescriptor();
		iopd.setFilePath("c:\\temp\\udatm\\test.out");
		iopd.setAppend(true); //

		try {
			Class<?> clazz = Class.forName(iopd.getIOProtocolClass());
			Constructor<?> constructor = clazz.getConstructor(iopd.getClass());
			Object[] params = { iopd };
			IOProtocol iop = (IOProtocol) constructor.newInstance(params);
			System.out.println("iop : " + iop.getIOProtocolDescriptor());
		} catch (Exception e) {
			System.out.println("Failed to instantiate " + iopd.getType() + " : " + e.getLocalizedMessage());
		}
	}
}
