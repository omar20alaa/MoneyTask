package app.money_task_kotlin.view.fragment

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import app.money_task_kotlin.R
import app.money_task_kotlin.databinding.FragmentAddAccountBinding
import app.money_task_kotlin.global.GlobalFunctions.setTextView
import app.money_task_kotlin.global.GlobalFunctions.showErrorToast
import app.money_task_kotlin.global.GlobalFunctions.showLog
import app.money_task_kotlin.global.GlobalFunctions.showSuccessToast
import app.money_task_kotlin.global.GlobalFunctions.showWarningToast
import app.money_task_kotlin.model.AddAccResponse
import app.money_task_kotlin.model.AddNewAccount
import app.money_task_kotlin.view.activity.MainActivity
import app.money_task_kotlin.view_model.AddNewAccountViewModel

class AddAccountFragment : Fragment() {

    // todo vars
    var binding: FragmentAddAccountBinding? = null
    var budget_id: String? = ""
    var addNewAccountViewModel: AddNewAccountViewModel? = null
    var typeValue: String? = ""
    var type = arrayOf<String?>(
        "checking", "savings", "creditCard", "cash", "lineOfCredit", "otherAsset",
        "otherLiability", "mortgage", "carLoan", "studentLoan", "personalLoan", "consumerLoan",
        "medicalDebt", "otherDebt"
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddAccountBinding.inflate(inflater, container, false)
        val view: View = binding!!.root
        showLog("AddAccountFragment Called --> ")
        initAct()
        initVisibility()
        receivedData()
        initViewModel()
        initSpinner()
        return view
    }

    private fun initSpinner() {
        binding!!.etType.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(adapterView: AdapterView<*>?, view: View, i: Int, l: Long) {
                typeValue = type[i]
            }

            override fun onNothingSelected(adapterView: AdapterView<*>?) {}
        }
        val aa: ArrayAdapter<*> =
            ArrayAdapter<Any?>(activity!!, android.R.layout.simple_spinner_item, type)
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding!!.etType.adapter = aa
    }

    private fun initViewModel() {
        addNewAccountViewModel = ViewModelProvider(activity!!).get(
            AddNewAccountViewModel::class.java
        )
        initClick()
    }

    private fun initClick() {
        binding!!.btnAdd.setOnClickListener {
            if (isAllValid) {
                addAccRequest()
            }
        }
    }

    private val isAllValid: Boolean
        private get() {
            if (TextUtils.isEmpty(binding!!.etName.text.toString())) {
                showWarningToast(activity, activity!!.getString(R.string.enter_name))
                return false
            } else if (TextUtils.isEmpty(binding!!.etBalance.text.toString())) {
                showWarningToast(activity, activity!!.getString(R.string.enter_balance))
                return false
            }
            return true
        }

    private fun addAccRequest() {
        binding!!.loadingLayout.loading.visibility = View.VISIBLE
        val account = AddNewAccount()
        val accountList = AddNewAccount.AccountList()
        accountList.name = binding!!.etName.text.toString().trim { it <= ' ' }
        accountList.type = typeValue
        accountList.balance = binding!!.etBalance.text.toString().trim { it <= ' ' }
        account.account = accountList
        addNewAccountViewModel!!.addNewAccount(budget_id, account)
            .observe(activity!!, Observer { responseResult: AddAccResponse? ->
                if (responseResult != null) if (responseResult.data != null) {
                    binding!!.loadingLayout.loading.visibility = View.GONE
                    showSuccessToast(activity, activity!!.getString(R.string.created))
                    activity!!.supportFragmentManager.popBackStack()
                } else {
                    showErrorToast(activity, activity!!.getString(R.string.try_again))
                }
            })
    } // addAccRequest

    // todo come from
    private fun receivedData() {
        if (arguments != null) {
            budget_id = arguments!!.getString("budget_id")
            showLog("budget_id --> $budget_id")
        }
    }

    private fun initVisibility() {
        MainActivity.binding!!.toolbar.toolbar.visibility = View.VISIBLE
        MainActivity.binding!!.toolbar.imgBack.visibility = View.GONE
        setTextView(MainActivity.binding!!.toolbar.tvTitle, activity!!.getString(R.string.add_acc))
    }

    private fun initAct() {
        var activity : FragmentActivity? = null
        if (activity == null) activity = getActivity()
    }
}