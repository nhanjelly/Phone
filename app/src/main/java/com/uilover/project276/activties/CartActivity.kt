package com.uilover.project276.activties

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.uilover.project276.Helper.ChangeNumberItemsListener
import com.uilover.project276.Helper.ManagmentCart
import com.uilover.project276.adapter.CartAdapter
import com.uilover.project276.databinding.ActivityCartBinding

class CartActivity : AppCompatActivity() {

    lateinit var binding: ActivityCartBinding
    lateinit var managmentCart: ManagmentCart
    private var tax: Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityCartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        managmentCart = ManagmentCart(this)

        calculatorCart()
        setVariable()
        initCartList()

    }

    private fun initCartList() {
        binding.apply {
            listView.layoutManager =
                LinearLayoutManager(this@CartActivity, LinearLayoutManager.VERTICAL, false)
            listView.adapter = CartAdapter(
                managmentCart.getListCart(),
                this@CartActivity,
                object : ChangeNumberItemsListener {
                    override fun onChanged() {
                        calculatorCart()
                    }
                }
            )
        }
    }

    private fun setVariable() {
        binding.backBtn.setOnClickListener { finish() }
    }

    private fun calculatorCart() {
        val percentTax = 0.02
        val delivery = 15
        tax = ((managmentCart.getTotalFee() * percentTax) * 100) / 100.0
        val total = String.format("%.2f",((managmentCart.getTotalFee() + tax + delivery) * 100) / 100)
        val itemTotal = (managmentCart.getTotalFee() * 100) / 100

        binding.apply {
            totalFeeTxt.text = "$$itemTotal"
            totalTaxTxt.text = "$$tax"
            deliveryTxt.text = "$$delivery"
            totalTxt.text = "$$total"
        }
    }
}