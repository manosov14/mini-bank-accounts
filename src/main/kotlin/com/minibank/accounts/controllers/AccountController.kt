package com.minibank.accounts.controllers

import com.minibank.accounts.dtos.AccountDTO
import com.minibank.accounts.models.Account
import io.swagger.v3.oas.annotations.Operation
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("api")
@ConditionalOnProperty(prefix = "feature.toggles", name = ["account"], havingValue = "true")

class AccountController {
    @PostMapping("account")
    fun createAccount(@RequestBody body: AccountDTO): Int {
        val account = Account()


        @Operation(description = "Идентификатор")
        account.id = body.id

        @Operation(description = "Владелец счета")
        account.ownerName = body.ownerName

        @Operation(description = "Номер счета")
        account.acnumber = body.acnumber

        @Operation(description = "Наименование счета")
        account.accountName = body.accountName

        @Operation(description = "Сумма средств")
        account.ammount = body.ammount

        @Operation(description = "Процент")
        account.percent = body.percent

        @Operation(description = "Срок")
        account.term = body.term

        @Operation(description = "Валюта")
        account.currency = body.currency

        return 0
    }
}