package com.minibank.accounts.repositories

import com.minibank.accounts.models.Account
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<Account, Int> {
    fun findByEmail(email: String): Account? {

        return TODO("Provide the return value")
    }
}