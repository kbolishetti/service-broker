package com.techolution.servicebroker.utility.config;


import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.cloud.servicebroker.model.Catalog;
import org.springframework.cloud.servicebroker.model.DashboardClient;
import org.springframework.cloud.servicebroker.model.Plan;
import org.springframework.cloud.servicebroker.model.ServiceDefinition;
import org.springframework.cloud.servicebroker.service.CatalogService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CatalogConfig implements CatalogService{
	
	
	@Bean
	public Catalog catalog() {
		return new Catalog(Collections.singletonList(
				new ServiceDefinition(
						"techolution-utility-service-broker",
						"techolution-utility",
						"A simple techolution-utility service broker implementation",
						true,
						false,
						Collections.singletonList(
								new Plan("mongo-plan",
										"default",
										"This is a default mongo plan.  All services are created equally.",
										getPlanMetadata())),
						Arrays.asList("techolution-utility", "document"),
						getServiceDefinitionMetadata(),
						null,
						null)));
	}
	
/* Used by Pivotal CF console */

	private Map<String, Object> getServiceDefinitionMetadata() {
		Map<String, Object> sdMetadata = new HashMap<>();
		sdMetadata.put("displayName", "techolution-utility");
		sdMetadata.put("imageUrl", "http://www.techolution.com/wp-content/uploads/2016/07/techolution-logo-web.png");
		sdMetadata.put("longDescription", "techolution-utility Service");
		sdMetadata.put("providerDisplayName", "Pivotal");
		sdMetadata.put("documentationUrl", "https://github.com/spring-cloud-samples/cloudfoundry-techolution-utility-service-broker");
		sdMetadata.put("supportUrl", "https://github.com/spring-cloud-samples/cloudfoundry-techolution-utility-service-broker");
		return sdMetadata;
	}
	
	private Map<String,Object> getPlanMetadata() {
		Map<String,Object> planMetadata = new HashMap<>();
		planMetadata.put("costs", getCosts());
		planMetadata.put("bullets", getBullets());
		return planMetadata;
	}

	private List<Map<String,Object>> getCosts() {
		Map<String,Object> costsMap = new HashMap<>();
		
		Map<String,Object> amount = new HashMap<>();
		amount.put("usd", 0.0);
	
		costsMap.put("amount", amount);
		costsMap.put("unit", "MONTHLY");
		
		return Collections.singletonList(costsMap);
	}
	
	private List<String> getBullets() {
		return Arrays.asList("Shared techolution-utility server", 
				"100 MB Storage (not enforced)", 
				"40 concurrent connections (not enforced)");
	}

	@Override
	public Catalog getCatalog() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ServiceDefinition getServiceDefinition(String serviceId) {
		// TODO Auto-generated method stub
		return null;
	}
	
}