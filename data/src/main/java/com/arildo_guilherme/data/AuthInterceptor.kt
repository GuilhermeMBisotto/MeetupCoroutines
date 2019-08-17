package com.arildo_guilherme.data

import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException
import java.security.MessageDigest

class AuthInterceptor : Interceptor {

    companion object {
        private const val API_KEY = "apikey"
        private const val TIMESTAMP = "ts"
        private const val HASH = "hash"
        private const val HASH_EXCEPTION = "Falha ao gerar o HASH."
        private const val MD5 = "MD5"
        private const val HEX_JOIN_CONSTANT_FORMAT = "%02x"
    }

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val timestamp = System.currentTimeMillis().toString()
        val request = chain.request()
        val authenticatedRequest = request.url.newBuilder()
            .addQueryParameter(TIMESTAMP, timestamp)
            .addQueryParameter(API_KEY, BuildConfig.API_PUBLIC_KEY)
            .addQueryParameter(
                HASH,
                getHash(timestamp)
            )
            .build()
        return chain.proceed(request.newBuilder().url(authenticatedRequest).build())
    }

    private fun getHash(timestamp: String): String {
        try {
            val value = timestamp + BuildConfig.API_PRIVATE_KEY + BuildConfig.API_PUBLIC_KEY
            return toMD5(value)
        } catch (e: java.lang.Exception) {
            throw Exception(HASH_EXCEPTION)
        }
    }

    private fun toMD5(value: String): String {
        val bytes = MessageDigest.getInstance(MD5).digest(value.toByteArray())
        return bytes.toHex()
    }

    private fun ByteArray.toHex(): String {
        return joinToString("") { HEX_JOIN_CONSTANT_FORMAT.format(it) }
    }
}
