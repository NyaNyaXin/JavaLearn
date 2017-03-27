package com.cx.spring.beans.collections;

import java.util.Properties;

public class DataSource {
	private Properties properties;

	/**
	 * @return the properties
	 */
	public Properties getProperties() {
		return properties;
	}

	/**
	 * @param properties the properties to set
	 */
	public void setProperties(Properties properties) {
		this.properties = properties;
	}

	
	@Override
	public String toString() {
		return "DataSource [properties=" + properties + "]";
	}
	
}
