package com.nickstamp.kit.core.analytics.model

abstract class AnalyticsEvent {

    abstract val category: String
    val metadata = AnalyticsMetadata()

    companion object Companion {
        const val ACTION = "action"
        const val LABEL = "label"
        private const val MAX_VALUE_LENGTH = 100
    }

    class AnalyticsMetadata {
        val analyticsData = hashMapOf<String, Any?>()

        fun putString(key: String, value: String?) {
            analyticsData[key] = value?.take(MAX_VALUE_LENGTH)
        }

        fun putInt(key: String, value: Int?) {
            analyticsData[key] = value
        }

        fun putMap(key: String, value: Map<String, Any?>) {
            val filteredMap = value.mapValues {
                if (it.value is String) {
                    (it.value as String).take(MAX_VALUE_LENGTH)
                } else {
                    it.value
                }
            }
            analyticsData[key] = filteredMap
        }

        override fun toString(): String {
            return analyticsData.toString()
        }
    }

    override fun toString(): String {
        return "AnalyticsEvent(category='$category', metadata=$metadata)"
    }

}