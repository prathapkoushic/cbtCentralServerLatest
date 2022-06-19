package com.ttipl.service;

import java.io.StringReader;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class StickerGenerationService {
	public String genterateStickers(List<Object[]> stickerData, String xslPath, String reportsPath, String centercode) {
		StringBuilder xml = new StringBuilder();
		String reportFormat = "pdf";
		int i = 0;
		for (Object[] data : stickerData) {
			i++;
			if (i == 1) {
				xml.append("<row>");
			}

			xml.append("<rollno" + i + ">" + data[0] + "</rollno" + i + ">");
			xml.append("<password" + i + ">" + data[1] + "</password" + i + ">");
			if (i == 3) {
				xml.append("</row>");
				i = 0;
			}
		}
		if (i == 2 || i == 1) {
			xml.append("</row>");
		}
		String c = "", reportName = "";
		c = "<root><result>";
		xml.append("</result>");
		xml.append("</root>");
		reportName = centercode + "_RollnoSticker";
		String x = c + xml;
		System.out.println(x);
		String outPut = "";
		try {
			System.out.println("X=" + x);
			StringReader rd = new StringReader(x);
			if (reportFormat.length() >= 0) {
				outPut = new XMLTransformer().GetTransformedString(rd, reportFormat, "RollnoStickerA4", xslPath,
						reportsPath, "", reportName);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return outPut;
	}

}
