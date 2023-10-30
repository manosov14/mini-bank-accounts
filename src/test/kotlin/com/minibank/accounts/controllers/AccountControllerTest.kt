package com.minibank.accounts.controllers

import com.minibank.accounts.BaseTest
import com.minibank.accounts.service.AccountService
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.whenever
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

import org.mockito.InjectMocks
import org.mockito.Mock

class AccountControllerTest : BaseTest() {

    @InjectMocks
    private lateinit var subj: AccountController

    @Mock
    private lateinit var accService: AccountService


    @Test
    fun createAccount_ok() {
        whenever(accService.create(any())).thenReturn(makeAccount())
        val responseEntity = subj.createAccount(makeAccountDTO())
        Assertions.assertEquals(201, responseEntity.statusCode.value())
    }

    @Test
    fun createAccount_wrongAccNumLenght() {
        val accountDTO = makeAccountDTO()
        accountDTO.accnumber = "1000"
        val responseEntity = subj.createAccount(accountDTO)
        Assertions.assertEquals(400, responseEntity.statusCode.value())
    }


    @Test
    fun getAccount_ok() {
        whenever(accService.findById(any())).thenReturn(makeAccountDTO())
        val responseEntity = subj.getAccount(id = 1)
        Assertions.assertEquals(200, responseEntity.statusCode.value())
    }

    @Test
    fun getAccount_notFound() {
        whenever(accService.findById(any())).thenReturn(null)
        val responseEntity = subj.getAccount(id = 1)
        Assertions.assertEquals(404, responseEntity.statusCode.value())
    }

    @Test
    fun getAccounts() {
        whenever(accService.findAll()).thenReturn(listOf(makeAccountDTO(), makeAccountDTO()))
        val responseEntity = subj.getAccounts()
        Assertions.assertEquals(200, responseEntity.statusCode.value())
        Assertions.assertEquals(2, responseEntity.body?.size)
    }
}