package com.ksoe.energynet.util.AnswerFileDodatokOk;

import java.io.Serializable;
import java.util.Date;
import java.util.Vector;

public class AnswerFileDodatokOkList implements Serializable {
	public Vector list;
	public String year;
	public String monthStart;
	public String monthFinal;

	public final Vector getList() {
		return list;
	}

	public String getMonthFinal() {
		return monthFinal;
	}

	public void setMonthFinal(String monthFinal) {
		this.monthFinal = monthFinal;
	}

	public String getMonthStart() {
		return monthStart;
	}

	public void setMonthStart(String monthStart) {
		this.monthStart = monthStart;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	
	
}
