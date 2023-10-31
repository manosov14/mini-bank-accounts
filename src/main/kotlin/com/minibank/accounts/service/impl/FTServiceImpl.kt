package com.minibank.accounts.service.impl


import com.minibank.accounts.config.FTProperties
import com.minibank.accounts.config.FeatureToggle
import com.minibank.accounts.service.FTService
import org.springframework.stereotype.Service

@Service
class FTServiceImpl(val ftProps: FTProperties) : FTService {

    override fun isEnabled(ftName: String): Boolean {
        return ftProps.features.get(ftName)?.enabled ?: false
    }

    override fun getAll(): Map<String, FeatureToggle> {
        return ftProps.features
    }
}