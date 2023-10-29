package com.minibank.accounts

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration
import org.springframework.boot.runApplication

@SpringBootApplication(exclude = [SecurityAutoConfiguration::class])
class MiniBankAccountsApplication

fun main(args: Array<String>) {
	runApplication<MiniBankAccountsApplication>(*args)
}
