package com.minibank.accounts.dtos

import io.swagger.v3.oas.annotations.media.Schema

class AccountDTO {

    @Schema(description = "Идентификатор клиента")
    var userId: String? = ""

    @Schema(description = "Идентификатор владельца счета")
    var ownerName: String? = ""

    @Schema(description = "Номер счета")
    var accnumber: String? = ""

    @Schema(description = "Наименование счета")
    var accountName: String? = ""

    @Schema(description = "Валюта")
    var ammount: Double? = 0.00

    @Schema(description = "Процент")
    var percent: Double? = 0.00

    @Schema(description = "Срок")
    var term: Int? = 0

    @Schema(description = "Валюта")
    var currency: String? = ""
}