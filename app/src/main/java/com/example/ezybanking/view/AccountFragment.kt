package com.example.ezybanking.view
import android.app.AlertDialog
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.util.Log
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import android.widget.Toast
import com.example.ezybanking.R
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ezybanking.model.Transaction
import com.example.ezybanking.viewmodel.TransactionViewModel
import kotlinx.android.synthetic.main.fragment_account.*
import kotlinx.android.synthetic.main.fragment_account.view.*
import kotlinx.android.synthetic.main.fragment_transactions.view.*
import kotlinx.android.synthetic.main.layout_transaction.*
import kotlinx.android.synthetic.main.layout_transaction.view.*
import kotlin.properties.Delegates


class AccountFragment  : Fragment(), View.OnClickListener{

    private lateinit var mTransactionViewModel: TransactionViewModel
    lateinit var navController: NavController
    private var balance:Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_account, container, false)
        val navHostFragment = requireActivity().supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
        // TransactionViewModel
        mTransactionViewModel = ViewModelProvider(this).get(TransactionViewModel::class.java)
        mTransactionViewModel.readAllData.observe(viewLifecycleOwner, Observer {
            getBalance(it)
        })
        view.layout_transaction.btn_submit.setOnClickListener { insertDataToDatabase() }
        view.findViewById<View>(R.id.btn_top_up).setOnClickListener(this)
        view.findViewById<View>(R.id.btn_check_transactions).setOnClickListener(this)
        return view
    }


    private fun getBalance(list: List<Transaction>){
        if (list.isNotEmpty()){
            balance = 0
            for (i in list){
                balance += i.amount!!
            }
            tv_balance.text = "Balance: $$balance"
        }

    }

    private fun insertDataToDatabase() {
        val amount = requireView().layout_transaction.et_amount.text
        val description =  requireView().layout_transaction.et_description.text.toString()
        val date =  requireView().layout_transaction.et_date.text.toString()

        if(inputCheck(amount, description, date)){
            if (Integer.parseInt(amount.toString()) <= balance){
                // Create Transaction Object
                val transaction = Transaction(0, -Integer.parseInt(amount.toString()), description, date)
                // Add Data to Database
                mTransactionViewModel.addTransaction(transaction)
                Toast.makeText(requireContext(), "Successfully withdraw!", Toast.LENGTH_LONG).show()
            } else {
                popupAlert()
            }
        }else{
            Toast.makeText(requireContext(), "Please fill out all fields.", Toast.LENGTH_LONG).show()
        }
    }
    private fun inputCheck(amount: Editable, description: String, date: String): Boolean{
        return !(amount.isEmpty() && TextUtils.isEmpty(description) && TextUtils.isEmpty(date))
    }

    private fun popupAlert(){
        AlertDialog.Builder(requireContext(), android.R.style.Theme_DeviceDefault_Dialog_Alert)
            .setTitle("Not Enough Balance")
            .setMessage("Please Top Up First")
            .setPositiveButton("OK") { _, _ ->
            }
            .show()
    }

    override fun onClick(v: View) {
        Log.i("Account", "onClick: v.id")
        when(v.id){
            R.id.btn_top_up -> navController.navigate(R.id.action_accountFragment_to_payFragment)
            R.id.btn_check_transactions -> navController.navigate(R.id.action_accountFragment_to_transactionsFragment)
        }
    }

}