package com.minibank.accounts.dtos

import io.swagger.v3.oas.annotations.media.Schema

class AccountDTO {

    @Schema(description = "Идентификатор счета")
    val id: Int = 0

    @Schema(description = "Идентификатор владельца счета")
    val ownerName: String = ""

    @Schema(description = "Номер счета")
    val acnumber: Int = 0

    @Schema(description = "Наименование счета")
    val accountName: String = ""

    @Schema(description = "Валюта")
    val ammount: Double = 0.00

    @Schema(description = "Процент")
    val percent: Double = 0.00

    @Schema(description = "Срок")
    val term: Int = 0

    @Schema(description = "Валюта")
    val currency: String = ""
}