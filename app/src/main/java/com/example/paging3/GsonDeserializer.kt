package com.example.paging3

import com.github.kittinunf.fuel.core.ResponseDeserializable
import com.google.gson.Gson

internal class GsonDeserializer<T : Any>(private val clazz: Class<T>) : ResponseDeserializable<T> {
    override fun deserialize(content: String): T = Gson().fromJson(content, clazz)
}