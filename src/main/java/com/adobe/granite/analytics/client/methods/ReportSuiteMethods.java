package com.adobe.granite.analytics.client.methods;

import static com.adobe.granite.analytics.client.JsonUtil.a;
import static com.adobe.granite.analytics.client.JsonUtil.o;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

import com.adobe.granite.analytics.client.AnalyticsClient;
import com.adobe.granite.analytics.client.domain.CompanyReportSuites;
import com.adobe.granite.analytics.client.domain.Evar;
import com.adobe.granite.analytics.client.domain.Event;
import com.adobe.granite.analytics.client.domain.Prop;
import com.adobe.granite.analytics.client.domain.ReportSuiteEvars;
import com.adobe.granite.analytics.client.domain.ReportSuiteEvents;
import com.adobe.granite.analytics.client.domain.ReportSuiteProps;
import com.google.gson.reflect.TypeToken;
import static com.adobe.granite.analytics.client.JsonUtil.GSON;

public class ReportSuiteMethods {

	private final AnalyticsClient client;

	public ReportSuiteMethods(AnalyticsClient client) {
		this.client = client;
	}

	public CompanyReportSuites getReportSuites() throws IOException {
		return client.callMethod("Company.GetReportSuites", o("types", a("standard")), CompanyReportSuites.class);
	}

	public List<ReportSuiteEvars> getEvars(String... rsid) throws IOException {
		final Type type = new TypeToken<List<ReportSuiteEvars>>() {}.getType();
		return client.callMethod("ReportSuite.GetEvars", o("rsid_list", rsid), type);
	}

	public List<ReportSuiteProps> getProps(String... rsid) throws IOException {
		final Type type = new TypeToken<List<ReportSuiteProps>>() {}.getType();
		return client.callMethod("ReportSuite.GetProps", o("rsid_list", rsid), type);
	}
	
	public List<ReportSuiteEvents> getEvents(String... rsid) throws IOException {
		final Type type = new TypeToken<List<ReportSuiteEvents>>() {}.getType();
		return client.callMethod("ReportSuite.GetEvents", o("rsid_list", rsid), type);
	}
	
	public Boolean setEvars(List<Evar> varList, List<String> reportSuiteList) throws IOException {
		String jsonStr=GSON.toJson(o("evars",varList,"rsid_list", reportSuiteList));
		return Boolean.parseBoolean(client.callMethod("ReportSuite.SaveEvars", jsonStr));
	}
	
	public Boolean setProps(List<Prop> varList, List<String> reportSuiteList) throws IOException {
		String jsonStr=GSON.toJson(o("props",varList,"rsid_list", reportSuiteList));
		return Boolean.parseBoolean(client.callMethod("ReportSuite.SaveProps", jsonStr));
	}
	public Boolean setEvents(List<Event> varList, List<String> reportSuiteList) throws IOException {
		String jsonStr=GSON.toJson(o("events",varList,"rsid_list", reportSuiteList));
		return Boolean.parseBoolean(client.callMethod("ReportSuite.SaveEvents", jsonStr));
	}
}
