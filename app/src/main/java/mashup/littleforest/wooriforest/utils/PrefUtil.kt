package mashup.littleforest.wooriforest.utils

import android.content.Context
import android.content.SharedPreferences

object PrefUtil {

    private const val PREF_NAME = "Pref"

    private lateinit var pref: SharedPreferences

    /**
     * Bearer {token} 값으로 저장합니다.
     */
    const val PREF_ACCESS_TOKEN = "access_token"
    //const val PREF_REFRESH_TOKEN = "refresh_token"

    //home 화면을 한번이라도 봤을 경우
    const val PREF_USER_MAIN = "main"

    const val PREF_SHOP_ITEM_IMAGE = "shop_item_image"
    const val PREF_USER_NAME = "user_name"

    fun init(context: Context) {
        pref = context.applicationContext.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    }

    fun get(key: String, defValue: String): String {
        return pref.getString(key, defValue) ?: defValue
    }

    fun get(key: String, defValue: Int): Int {
        return pref.getInt(key, defValue)
    }

    fun get(key: String, defValue: Long): Long {
        return pref.getLong(key, defValue)
    }

    fun get(key: String, defValue: Boolean): Boolean {
        return pref.getBoolean(key, defValue)
    }

    fun put(key: String, value: String) {
        val editor = pref.edit()
        editor.putString(key, value)
        editor.apply()
    }

    fun put(key: String, value: Int) {
        val editor = pref.edit()
        editor.putInt(key, value)
        editor.apply()
    }

    fun put(key: String, value: Long) {
        val editor = pref.edit()
        editor.putLong(key, value)
        editor.apply()
    }

    fun put(key: String, value: Boolean) {
        val editor = pref.edit()
        editor.putBoolean(key, value)
        editor.apply()
    }

    fun remove(key: String) {
        val editor = pref.edit()
        editor.remove(key)
        editor.apply()
    }

    fun clear() {
        val editor = pref.edit()
        editor.clear()
        editor.apply()
    }
}