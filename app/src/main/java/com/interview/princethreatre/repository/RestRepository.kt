package com.interview.princethreatre.repository

interface RestRepository {
    fun get(domain: String, api: String)
    fun post(domain: String, api: String, data: Any)
    fun put(domain: String, api: String, data: Any)
    fun delete(domain: String, api: String)
}