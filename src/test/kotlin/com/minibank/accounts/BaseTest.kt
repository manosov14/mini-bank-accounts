package com.minibank.accounts

import com.minibank.accounts.dtos.AccountDTO
import com.minibank.accounts.models.Account
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
abstract class BaseTest {

    protected fun makeAccount() : Account {
        val account = Account()
        account.accountName = "Account Name"
        account.term = 91
        account.userId = "2"
        account.currency = "RUB"
        account.percent = 10.0
        account.ownerName = "Owner Name"
        account.accnumber = "10000000000000000005"
        account.ammount = 3500.0
        return account
    }
    protected fun makeAccountDTO() : AccountDTO {
        val accountDTO = AccountDTO()
        accountDTO.accountName = "Account Name"
        accountDTO.term = 91
        accountDTO.userId = "2"
        accountDTO.currency = "RUB"
        accountDTO.percent = 10.0
        accountDTO.ownerName = "Owner Name"
        accountDTO.accnumber = "10000000000000000005"
        accountDTO.ammount = 3500.0
        return accountDTO
    }

}