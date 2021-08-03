package com.example.ezybanking.view

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import com.example.ezybanking.R
import com.example.ezybanking.model.Transaction
import com.example.ezybanking.viewmodel.TransactionViewModel
import kotlinx.android.synthetic.main.fragment_account.view.*
import kotlinx.android.synthetic.main.fragment_pay.*
import kotlinx.android.synthetic.main.layout_transaction.view.*

class PayFragment: Fragment() {


    private lateinit var mTransactionViewModel: TransactionViewModel
    lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_pay, container, false)
        mTransactionViewModel = ViewModelProvider(this).get(TransactionViewModel::class.java)
//        navController = Navigation.findNavController(view)
        val navHostFragment = requireActivity().supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        view.layout_transaction.btn_submit.setOnClickListener {
            insertDataToDatabase()
        }
        return view
    }

    private fun insertDataToDatabase() {
        val amount = requireView().layout_transaction.et_amount.text
        val description = requireView().layout_transaction.et_description.text.toString()
        val date = requireView().layout_transaction.et_date.text.toString()

        if(inputCheck(amount, description, date)){
            // Create Transaction Object
            val transaction = Transaction(0, Integer.parseInt(amount.toString()), description, date)
            // Add Data to Database
            mTransactionViewModel.addTransaction(transaction)
            Toast.makeText(requireContext(), "Successfully added!", Toast.LENGTH_LONG).show()
            // Navigate Back
            navController.navigate(R.id.action_payFragment_to_accountFragment)
        }else{
            Toast.makeText(requireContext(), "Please fill out all fields.", Toast.LENGTH_LONG).show()
        }
    }

    private fun inputCheck(amount: Editable, description: String, date: String): Boolean{
        return !(amount.isEmpty() && TextUtils.isEmpty(description) && TextUtils.isEmpty(date))
    }

}