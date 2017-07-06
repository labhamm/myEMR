package com.hackathon.vault.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

import com.hackathon.vault.services.VaultService;
import com.hackathon.vault.services.impl.CloudService;
import com.hackathon.vault.services.impl.LocalService;

/**
 * Binds the Vault service based on defined condition.
 * 
 * <blockquote>
 * 
 * <pre>
 * Based on {@code Conditional} attribue it binds the {@code VaultService}, only one bean should be instantiated for {@link VaultService}}.
 * </pre>
 * 
 * </blockquote> <blockquote>
 * 
 * <pre>
 * either {@link CloudService} or {@link LocalService}.
 * </pre>
 * 
 * </blockquote>
 *
 * @author vimal.singh@philips.com
 */
@Configuration
public class VaultServiceConfig {

	/**
	 * Binds Cloud service
	 *
	 * @return the cloud service
	 */
	@Bean(name = "vaultService")
	@Conditional(CloudServiceCondition.class)
	public VaultService getCloudService() {
		return new CloudService();
	}

	/**
	 * Binds local service.
	 *
	 * @return the local service
	 */
	@Bean(name = "vaultService")
	@Conditional(LocalServiceCondition.class)
	public VaultService getLocalService() {
		return new LocalService();
	}

}
