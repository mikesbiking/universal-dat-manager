package com.shinysideup.udatm.lib.ui;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Properties;

import com.shinysideup.udatm.lib.io.FileCaptureIOProtocolDescriptor;
import com.shinysideup.udatm.lib.io.HTTPIOProtocolDescriptor;
import com.shinysideup.udatm.lib.io.IOProtocolDescriptor;
import com.shinysideup.udatm.lib.pojo.DOTType;
import com.shinysideup.udatm.lib.util.JsonUtil;
import com.shinysideup.udatm.lib.util.UDATMHelper;

public class UDATMTool {

	public static void main(String[] args) throws Exception {
		FileCaptureIOProtocolDescriptor iopd = new FileCaptureIOProtocolDescriptor();
		iopd.setFilePath("c:\\temp\\udatm\\test.out");
		iopd.setAppend(true);
		// IOProtocol iop = new FileCaptureIOProtocol(iopd);
		// iop.write("hello");

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
		
		
		System.out.println(UDATMHelper.getProperties());



		

		
	}

}
