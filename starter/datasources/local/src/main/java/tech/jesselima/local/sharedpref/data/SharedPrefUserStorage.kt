package tech.jesselima.local.sharedpref.data

import tech.jesselima.local.sharedpref.data.models.UserData

interface SharedPrefUserStorage {
    fun getStoredUser(): UserData?
    fun saveValue(key: String, value: String)
    fun saveValue(key: String, value: Int)
    fun saveValue(key: String, value: Boolean)
    fun saveValue(key: String, value: Float)
    fun saveValue(key: String, value: Long)
    fun getStringValue(key: String): String?
    fun getIntValue(key: String): Int?
    fun getBooleanValue(key: String): Boolean?
    fun getFloatValue(key: String): Float?
    fun getLongValue(key: String): Long?
    fun clearPrefs()
}