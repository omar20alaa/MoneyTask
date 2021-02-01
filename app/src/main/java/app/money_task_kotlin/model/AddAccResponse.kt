package app.money_task_kotlin.model

import java.io.Serializable

class AddAccResponse {
    /**
     * data : {"account":{"id":"1df1e761-3341-46b6-bab1-8a9cf50e26af","name":"string","type":"checking","on_budget":true,"closed":false,"note":null,"balance":0,"cleared_balance":0,"uncleared_balance":0,"transfer_payee_id":"119889ae-5edf-4c5f-af09-4d706ca93454","deleted":false},"server_knowledge":50}
     */
    var data: DataList? = null

    class DataList : Serializable {
        /**
         * account : {"id":"1df1e761-3341-46b6-bab1-8a9cf50e26af","name":"string","type":"checking","on_budget":true,"closed":false,"note":null,"balance":0,"cleared_balance":0,"uncleared_balance":0,"transfer_payee_id":"119889ae-5edf-4c5f-af09-4d706ca93454","deleted":false}
         * server_knowledge : 50
         */
        var account: AccountList? = null
        var server_knowledge: Int? = null

        class AccountList : Serializable {
            /**
             * id : 1df1e761-3341-46b6-bab1-8a9cf50e26af
             * name : string
             * type : checking
             * on_budget : true
             * closed : false
             * note : null
             * balance : 0
             * cleared_balance : 0
             * uncleared_balance : 0
             * transfer_payee_id : 119889ae-5edf-4c5f-af09-4d706ca93454
             * deleted : false
             */
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