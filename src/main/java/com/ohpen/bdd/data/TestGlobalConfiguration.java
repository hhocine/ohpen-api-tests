package com.ohpen.bdd.data;

import org.apache.commons.lang3.StringUtils;

/**
 * Selenium tests global configuration Looks for a property from jahia
 * properties, they should be found in jahia.custom.properties If the property
 * is empty or doesn't exist, defines a default value
 */
public class TestGlobalConfiguration {

    private static String defaultClientUsername = "";

    private static String defaultClientPassword = "";

    private static String defaultClientToken = "";

    /**
     * Gets the property value.
     *
     * @param propertyName the property name
     * @param defaultValue the default value
     * @return the property value
     */
    private static String getPropertyValue(String propertyName, String defaultValue) {
	String value = System.getProperty(propertyName);
	if (StringUtils.isEmpty(value)) {
	    return defaultValue;
	} else {
	    return value;
	}
    }

    /**
     * Gets the property value.
     *
     * @param propertyName the property name
     * @param defaultValue the default value
     * @return the property value
     */
    private static boolean getPropertyValue(String propertyName, boolean defaultValue) {
	String value = System.getProperty(propertyName);
	if (StringUtils.isEmpty(value)) {
	    return defaultValue;
	} else {
	    return Boolean.valueOf(value);
	}
    }

    /**
     * Gets the client username.
     *
     * @return the client username
     */
    public static String getClientUsername() {
	return getPropertyValue("client.username", defaultClientUsername);
    }

    /**
     * Gets the client password.
     *
     * @return the client password
     */
    public static String getClientPassword() {
	return getPropertyValue("client.password", defaultClientPassword);
    }

    /**
     * Gets the client token.
     *
     * @return the client token
     */
    public static String getClientToken() {
	return getPropertyValue("client.token", defaultClientToken);
    }

}
