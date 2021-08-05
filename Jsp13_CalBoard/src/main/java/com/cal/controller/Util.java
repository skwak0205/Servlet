package com.cal.controller;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

import com.cal.dto.CalDto;

public class Util {

	private String todates;

	public String getTodates() {
		return todates;
	}

	public void setTodates(String mdate) {
		// yyyy-MM-dd hh:mm:00
		String d = mdate.substring(0, 4) + "-" + mdate.substring(4, 6) + "-" + mdate.substring(6, 8) + " "
				+ mdate.substring(8, 10) + ":" + mdate.substring(10) + ":00";

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy년MM월dd일 HH시mm분");
		Timestamp tm = Timestamp.valueOf(d);
		todates = sdf.format(tm);
	}

	public static String fontColor(int date, int dayOfWeek) {
		String color = "";

		if ((dayOfWeek - 1 + date) % 7 == 0) {
			color = "blue";
		} else if ((dayOfWeek - 1 + date) % 7 == 1) {
			color = "red";
		} else {
			color = "black";
		}

		return color;
	}

	public static String isTwo(String msg) {
		return (msg.length() < 2) ? "0" + msg : msg;
	}

	public static String getCalView(int i, List<CalDto> list) {
		String d = isTwo(i + "");
		String res = "";

		for (CalDto dto : list) {
			if (dto.getMdate().substring(6, 8).equals(d)) {
				res += "<p>" + ((dto.getTitle().length() > 6) ? dto.getTitle().substring(0, 6) + "..." : dto.getTitle())
						+ "</p>";
			}
		}

		return res;
	}
}
