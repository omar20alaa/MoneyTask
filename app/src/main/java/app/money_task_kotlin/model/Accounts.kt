package app.money_task_kotlin.model

import java.io.Serializable
import java.util.ArrayList

class Accounts {
    var data: DataList? = null

    class DataList : Serializable {
        var server_knowledge: Int? = null
        private var accounts: ArrayList<AccountsList>? = null
        fun getAccounts(): List<AccountsList>? {
            return accounts
        }

        fun setAccounts(accounts: ArrayList<AccountsList>?) {
            this.accounts = accounts
        }

        class AccountsList : Serializable {
            var id: String? = null
            var name: String? = null
            var type: String? = null
            var isOn_budget: Boolean? = null
            var isClosed: Boolean? = null
            var note: Any? = null
            var balance: Int? = null
            var cleared_balance: Int? = null
            var uncleared_balance: Int? = null
            var transfer_payee_id: String? = null
            var isDeleted: Boolean? = null
        }
    }
}