package com.minibank.accounts.repositories

import com.minibank.accounts.models.Account
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.transaction.annotation.Transactional

interface AccountRepository : JpaRepository<Account, Int> {

    @Transactional
    fun findByAccnumber(accnumber: String): String? {
        return "Счет успешно создан"
    }
}