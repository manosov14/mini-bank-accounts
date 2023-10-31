package com.minibank.accounts.controllers

import com.example.minibank.dtos.exeptions.Message
import com.minibank.accounts.dtos.AccountDTO
import com.minibank.accounts.models.Account
import com.minibank.accounts.service.AccountService
import com.minibank.accounts.service.FTService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/v1/accounts")
class AccountController(
    val accountService: AccountService,
    val ftService: FTService
) {

    private val accountCanDelete: String = "accountCanDelete"
    private val accountCanUpdate: String = "accountCanUpdate"
    private val accountCanGet: String = "accountCanGet"
    private val accountsCanGet: String = "accountsCanGet"
    private val accountCanCreate: String = "accountCanCreate"

    @Tag(
        name = "Счета",
        description = "Все методы для работы со счетом Пользователя",
    )

    @PostMapping()
    @Operation(summary = "Создание счета")
    fun createAccount(@RequestBody body: AccountDTO): ResponseEntity<Any> {

        if (!ftService.isEnabled(accountCanCreate)) {
            println("feature $accountCanCreate is off")
            return ResponseEntity.ok().body(Message("FT $accountCanCreate off"))
        }
        println("feature $accountCanCreate is on")

        val account = Account()

        // Длина счета
        val accLenghtIsValid = body.accnumber!!.length >= 20

        @Operation(description = "Владелец счета") // TODO на вход принимать jwt и информацию о userId и владельце получать оттуда
        account.ownerName = body.ownerName

        @Operation(description = "Номер счета")
        account.accnumber = body.accnumber

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

        // Проверка на длину в 20 символов
        if (accLenghtIsValid) {
            return ResponseEntity.status(HttpStatus.CREATED).body(this.accountService.create(account))
        } else {
            return ResponseEntity.badRequest().body(Message("Номер счета должен иметь 20 символов"))
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Закрытие (удаление) счета") // TODO проверять не только по id счета, но и по userId из JWT
    fun deleteAccount(@PathVariable id: Int): Any {
        if (!ftService.isEnabled(accountCanDelete)) {
            println("feature $accountCanDelete is off")
            return ResponseEntity.ok().body(Message("FT $accountCanDelete off"))
        }

        println("feature $accountCanDelete is on")
        return accountService.deleteAccount(id)
    }

    @PutMapping("/{id}")
    @Operation(summary = "Обновление данных по счету") // TODO проверять не только по id счета, но и по userId из JWT
    fun updateAccount(@PathVariable id: Int, @RequestBody body: AccountDTO): ResponseEntity<Any> {

        if (!ftService.isEnabled(accountCanUpdate)) {
            println(Message("feature $accountCanUpdate is off"))
            return ResponseEntity.ok().body(Message("FT $accountCanUpdate off"))
        }
        println("feature $accountCanUpdate is on")


        val accnumValidation = body.accnumber!!.length >= 20

        // Проверка на длину в 20 символов
        if (accnumValidation) {
            accountService.updateAccount(id, body)
            return ResponseEntity.ok().build()
        } else {
            return ResponseEntity.badRequest().body(Message("Номер счета должен иметь 20 символов"))
        }
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получение информации по конкретному счету") // TODO проверять не только по id счета, но и по userId из JWT
    fun getAccount(@PathVariable id: Int): ResponseEntity<Any>  {

        if (!ftService.isEnabled(accountCanGet)) {
            println("feature $accountCanGet is off")
            return ResponseEntity.ok().body(Message("FT $accountCanGet off"))
        }
        println("feature $accountCanGet is on")

        val account = accountService.findById(id)
        if (account != null) {
            return ResponseEntity.ok(account)
        } else {
            return ResponseEntity.notFound().build()
        }
    }

    @GetMapping()
    @Operation(summary = "Получение списка всех счетов с детальной информацией ") // TODO возвращать все счета userId из JWT
    fun getAccounts(): ResponseEntity<List<AccountDTO>> {

        if (!ftService.isEnabled(accountsCanGet)) {
            println("feature $accountsCanGet is off")
        }
        println("feature $accountsCanGet is on")

        return ResponseEntity.ok(accountService.findAll())
    }
}
