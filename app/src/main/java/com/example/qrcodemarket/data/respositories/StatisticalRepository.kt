package com.example.qrcodemarket.data.respositories

import com.example.qrcodemarket.data.network.SyntheticApi
import com.example.qrcodemarket.data.network.response.SafeApiRequest

class StatisticalRepository(
    private val api:SyntheticApi
):SafeApiRequest() {
    suspend fun getStatistical() = apiRequest { api.Statistical() }
}