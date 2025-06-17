package com.example.nit3213app.data.model

/**
 * Dynamic data class representing an entity from the dashboard API
 * This class can adapt to any JSON structure by storing data as key-value pairs
 * 
 * @param data Map containing all the key-value pairs from the JSON response
 */
data class Entity(
    val data: Map<String, Any>
) {
    /**
     * Get a value by key with optional default
     */
    fun getValue(key: String, default: String = "N/A"): String {
        return when (val value = data[key]) {
            is String -> value
            is Number -> value.toString()
            is Boolean -> value.toString()
            null -> default
            else -> value.toString()
        }
    }
    
    /**
     * Get all keys in the entity data
     */
    fun getKeys(): List<String> = data.keys.toList()
    
    /**
     * Get the description field (commonly used for details)
     * Try common description field names
     */
    fun getDescription(): String {
        return getValue("description") 
            .takeIf { it != "N/A" } 
            ?: getValue("desc")
            .takeIf { it != "N/A" }
            ?: "No description available"
    }
    
    /**
     * Get a display name for the entity (first non-empty value)
     */
    fun getDisplayName(): String {
        val keys = getKeys()
        return if (keys.isNotEmpty()) {
            getValue(keys.first())
        } else {
            "Unknown Entity"
        }
    }
} 