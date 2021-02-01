package app.money_task_kotlin.model

import java.io.Serializable
import java.util.*

class Budget {
    var data: DataList? = null

    class DataList : Serializable {
        var default_budget: Any? = null
        var budgets: ArrayList<BudgetsList>? = null

        class BudgetsList : Serializable {
            var id: String? = null
            var name: String? = null
            var last_modified_on: String? = null
            var first_month: String? = null
            var last_month: String? = null
            var date_format: DateFormatList? = null
            var currency_format: CurrencyFormatList? = null

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
        }
    }
}