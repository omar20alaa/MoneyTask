package app.money_task_kotlin.model

import java.io.Serializable

class AddNewAccount {
    var account: AccountList? = null

    class AccountList : Serializable {
        var name: String? = null
        var type: String? = null
        var balance: String? = null
    }
}