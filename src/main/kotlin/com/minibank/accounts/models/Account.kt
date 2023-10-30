package com.minibank.accounts.models

import javax.persistence.*



@Entity
@Table(name = "accounts")
class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Int = 0

    @Column
    var userId: String? = ""

    @Column
    var ownerName: String? = ""

    @Column
    var accnumber: String? = ""

    @Column
    var accountName: String? = ""

    @Column
    var ammount: Double? = 0.00

    @Column
    var percent: Double? = 0.00

    @Column
    var term: Int? = 0

    @Column
    var currency: String? = ""

}