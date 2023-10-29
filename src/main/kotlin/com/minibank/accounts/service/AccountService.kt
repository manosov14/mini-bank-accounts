package com.minibank.accounts.service

import com.example.minibank.dtos.exeptions.Message
import com.minibank.accounts.dtos.AccountDTO
import com.minibank.accounts.models.Account
import com.minibank.accounts.repositories.AccountRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service


@Service

class AccountService(
    val accountRepository: AccountRepository
) {

    fun create(account: Account): Account? {
        return this.accountRepository.save(account)
    }

    fun deleteAccount(id: Int) {
        try {
            this.accountRepository.findByIdOrNull(id)
            ResponseEntity.ok(this.accountRepository.deleteById(id))
        } catch (e: Exception) {
             ResponseEntity.badRequest().body(Message ("Account with id $id not found"))
        }
    }

    fun updateAccount(id: Int, body: AccountDTO) {
        val existingAccount =
            accountRepository.findByIdOrNull(id) ?: throw RuntimeException("User with id $id not found")
        existingAccount.term = body.term
        existingAccount.percent = body.percent
        existingAccount.ammount = body.ammount
        existingAccount.currency = body.currency
        existingAccount.accnumber = body.accnumber
        existingAccount.ownerName = body.ownerName
        existingAccount.accountName = body.accountName

        try {
            accountRepository.findByIdOrNull(id)
            accountRepository.save(existingAccount)
        } catch (e: Exception) {
            ResponseEntity.badRequest().body(Message("Счет не найден"))
        }
    }
}