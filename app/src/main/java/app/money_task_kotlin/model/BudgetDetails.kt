package app.money_task_kotlin.model

import java.io.Serializable
import java.util.*

class BudgetDetails {
    var data: DataList? = null

    class DataList : Serializable {
        var budget: BudgetList? = null
        var server_knowledge: Int? = null

        class BudgetList : Serializable {
            var id: String? = null
            var name: String? = null
            var last_modified_on: String? = null
            var date_format: DateFormatList? = null
            var currency_format: CurrencyFormatList? = null
            var first_month: String? = null
            var last_month: String? = null
            private var accounts: ArrayList<AccountsList>? = null
            var payees: ArrayList<PayeesList>? = null
            private var payee_locations: ArrayList<*>? = null
            private var category_groups: ArrayList<CategoryGroupsList>? = null
            private var categories: ArrayList<CategoriesList>? = null
            private var months: ArrayList<MonthsList>? = null
            private var transactions: ArrayList<TransactionsList>? = null
            private var subtransactions: ArrayList<*>? = null
            private var scheduled_transactions: ArrayList<*>? = null
            private var scheduled_subtransactions: ArrayList<*>? = null
            fun getAccounts(): List<AccountsList>? {
                return accounts
            }

            fun setAccounts(accounts: ArrayList<AccountsList>?) {
                this.accounts = accounts
            }

            fun getPayee_locations(): List<*>? {
                return payee_locations
            }

            fun setPayee_locations(payee_locations: ArrayList<*>?) {
                this.payee_locations = payee_locations
            }

            fun getCategory_groups(): List<CategoryGroupsList>? {
                return category_groups
            }

            fun setCategory_groups(category_groups: ArrayList<CategoryGroupsList>?) {
                this.category_groups = category_groups
            }

            fun getCategories(): List<CategoriesList>? {
                return categories
            }

            fun setCategories(categories: ArrayList<CategoriesList>?) {
                this.categories = categories
            }

            fun getMonths(): List<MonthsList>? {
                return months
            }

            fun setMonths(months: ArrayList<MonthsList>?) {
                this.months = months
            }

            fun getTransactions(): List<TransactionsList>? {
                return transactions
            }

            fun setTransactions(transactions: ArrayList<TransactionsList>?) {
                this.transactions = transactions
            }

            fun getSubtransactions(): List<*>? {
                return subtransactions
            }

            fun setSubtransactions(subtransactions: ArrayList<*>?) {
                this.subtransactions = subtransactions
            }

            fun getScheduled_transactions(): List<*>? {
                return scheduled_transactions
            }

            fun setScheduled_transactions(scheduled_transactions: ArrayList<*>?) {
                this.scheduled_transactions = scheduled_transactions
            }

            fun getScheduled_subtransactions(): List<*>? {
                return scheduled_subtransactions
            }

            fun setScheduled_subtransactions(scheduled_subtransactions: ArrayList<*>?) {
                this.scheduled_subtransactions = scheduled_subtransactions
            }

            class DateFormatList : Serializable {
                var format: String? = null
            }

            class CurrencyFormatList : Serializable {
                var iso_code: String? = null
                var example_format: String? = null
                var decimal_digits: Int? = null
                var decimal_separator: String? = null
                var isSymbol_first: Boolean? = null
                var group_separator: String? = null
                var currency_symbol: String? = null
                var isDisplay_symbol: Boolean? = null
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

            class PayeesList : Serializable {
                var id: String? = null
                var name: String? = null
                var transfer_account_id: Any? = null
                var isDeleted: Boolean? = null
            }

            class CategoryGroupsList : Serializable {
                var id: String? = null
                var name: String? = null
                var isHidden: Boolean? = null
                var isDeleted: Boolean? = null
            }

            class CategoriesList : Serializable {
                var id: String? = null
                var category_group_id: String? = null
                var name: String? = null
                var isHidden: Boolean? = null
                var original_category_group_id: Any? = null
                var note: Any? = null
                var budgeted: Int? = null
                var activity: Int? = null
                var balance: Int? = null
                var goal_type: Any? = null
                var goal_creation_month: Any? = null
                var goal_target: Int? = null
                var goal_target_month: Any? = null
                var goal_percentage_complete: Any? = null
                var isDeleted: Boolean? = null
            }

            class MonthsList : Serializable {
                var month: String? = null
                var note: Any? = null
                var income: Int? = null
                var budgeted: Int? = null
                var activity: Int? = null
                var to_be_budgeted: Int? = null
                var age_of_money: Any? = null
                var isDeleted: Boolean? = null
                var categories: List<CategoriesListX>? = null

                class CategoriesListX : Serializable {
                    var id: String? = null
                    var category_group_id: String? = null
                    var name: String? = null
                    var isHidden: Boolean? = null
                    var original_category_group_id: Any? = null
                    var note: Any? = null
                    var budgeted: Int? = null
                    var activity: Int? = null
                    var balance: Int? = null
                    var goal_type: Any? = null
                    var goal_creation_month: Any? = null
                    var goal_target: Int? = null
                    var goal_target_month: Any? = null
                    var goal_percentage_complete: Any? = null
                    var isDeleted: Boolean? = null
                }
            }

            class TransactionsList : Serializable {
                var id: String? = null
                var date: String? = null
                var amount: Int? = null
                var memo: Any? = null
                var cleared: String? = null
                var isApproved: Boolean? = null
                var flag_color: Any? = null
                var account_id: String? = null
                var payee_id: String? = null
                var category_id: String? = null
                var transfer_account_id: Any? = null
                var transfer_transaction_id: Any? = null
                var matched_transaction_id: Any? = null
                var import_id: Any? = null
                var isDeleted: Boolean? = null
            }
        }
    }
}