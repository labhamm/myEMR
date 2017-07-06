package com.hackathon.vault.config;

import org.apache.commons.lang.StringUtils;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

import com.hackathon.vault.services.impl.CloudService;

/**
 * {@code CloudServiceCondition} could be used to instantiate the
 * {@link CloudService}.
 * 
 * <p>
 * Conditions are checked immediately before the bean-definition is due to be
 * registered and are free to veto registration based on any criteria that can
 * be determined at that point.
 * 
 * <blockquote>
 * 
 * <pre>
 * if property vault.type set to cloud then conditions matches to true and {@link CloudService} will be instantiated.
 * </pre>
 * 
 * </blockquote>
 * 
 * @author vimal.singh@philips.com
 * @see Condition
 * @see CloudService
 * 
 */
public class CloudServiceCondition implements Condition {

	/**
	 * Determine if the condition matches.
	 * 
	 * @param context
	 *            the condition context
	 * @param metadata
	 *            metadata of the
	 *            {@link org.springframework.core.type.AnnotationMetadata class}
	 *            or {@link org.springframework.core.type.MethodMetadata method}
	 *            being checked.
	 * @return {@code true} if the condition matches and the component can be
	 *         registered or {@code false} to veto registration.
	 */
	@Override
	public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
		if (null != context) {
			Environment environment = context.getEnvironment();
			if (null != environment) {
				String vaultType = environment.getProperty("vault.type");
				if (StringUtils.isNotEmpty(vaultType)) {
					return "cloud".equalsIgnoreCase(vaultType);
				}
			}
		}
		return false;
	}
}
