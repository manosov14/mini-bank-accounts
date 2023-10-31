package com.minibank.accounts.service

import com.minibank.accounts.config.FeatureToggle

interface FTService {
    fun isEnabled(ftName: String): Boolean

    fun getAll(): Map<String, FeatureToggle>
}