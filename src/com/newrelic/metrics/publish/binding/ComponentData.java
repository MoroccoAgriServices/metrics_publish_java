package com.newrelic.metrics.publish.binding;

import java.util.HashMap;

public class ComponentData {
	public String name;
	public String guid;
	
	public String toString() {
		return "ComponentData(" + name + ":" + guid + ")";
	}
	
	/* package */ ComponentData() {
		super();
	}

	/* package */ HashMap<String,Object> serialize(Request request) {
		HashMap<String, Object> output = new HashMap<String, Object>();
		output.put("name", name);
		output.put("guid", guid);	
		output.put("duration", request.getDuration());	
		
		HashMap<String, Object> metricsOutput = new HashMap<String, Object>();
		output.put("metrics", metricsOutput);
		
		for (MetricData metric : request.getMetrics(this)) {
			metric.serialize(metricsOutput);
		}
		
		return output;
	}	
}
