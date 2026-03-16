package com.github.klee0kai.cloud.core.data.platform

import com.russhwolf.settings.PreferencesSettings
import com.russhwolf.settings.Settings
import java.util.*
import java.util.prefs.Preferences
import javax.crypto.Cipher
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec

class JvmEncryptor(secretKey: String) {
    private val keySpec = SecretKeySpec(secretKey.padEnd(32, '0').take(32).toByteArray(), "AES")
    private val charset = Charsets.UTF_8

    fun encrypt(value: String): String {
        val cipher = Cipher.getInstance("AES/CBC/PKCS5Padding")
        cipher.init(Cipher.ENCRYPT_MODE, keySpec)
        val iv = cipher.iv
        val encrypted = cipher.doFinal(value.toByteArray(charset))
        // Сохраняем IV вместе с данными, чтобы расшифровать потом
        return Base64.getEncoder().encodeToString(iv + encrypted)
    }

    fun decrypt(encryptedValue: String): String {
        return try {
            val decoded = Base64.getDecoder().decode(encryptedValue)
            val iv = decoded.sliceArray(0 until 16)
            val data = decoded.sliceArray(16 until decoded.size)

            val cipher = Cipher.getInstance("AES/CBC/PKCS5Padding")
            cipher.init(Cipher.DECRYPT_MODE, keySpec, IvParameterSpec(iv))
            String(cipher.doFinal(data), charset)
        } catch (e: Exception) {
            "" // Или выбросить ошибку, если данные повреждены
        }
    }
}

class EncryptedJvmSettings(
    private val delegate: Settings,
    private val encryptor: JvmEncryptor
) : Settings by delegate {

    override fun putString(key: String, value: String) {
        delegate.putString(key, encryptor.encrypt(value))
    }

    override fun getString(key: String, defaultValue: String): String {
        val encrypted = delegate.getString(key, "")
        return if (encrypted.isEmpty()) defaultValue else encryptor.decrypt(encrypted)
    }

}

actual fun initSettingsEngine(

): Settings {
    val masterKey = "kfajadljf32424fsdf"
    val preferences = Preferences.userRoot().node("secure_settings")
    val baseSettings = PreferencesSettings(preferences)
    return EncryptedJvmSettings(baseSettings, JvmEncryptor(masterKey))
}