package com.example.kursy.view.main

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import com.example.kursy.R
import com.example.kursy.di.DaggerAppComponent
import com.example.kursy.domain.CurrencyTable
import com.example.kursy.view.exchangeList.ExchangeListActivity
import kotlinx.android.synthetic.main.activity_exchange_list.*
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import javax.inject.Inject

class MainActivity : AppCompatActivity(), MainContract.View {

    private val listCurrency: ArrayList<String> = ArrayList()
    private val listCode: ArrayList<String> = ArrayList()
    private val listMid: ArrayList<Float> = ArrayList()
    private val listTable: ArrayList<CurrencyTable> = ArrayList()

    @Inject
    lateinit var presenter: MainContract.Presenter

    private lateinit var adapter1: ArrayAdapter<String>
    private lateinit var adapter2: ArrayAdapter<String>

    private lateinit var valueInputTextView: TextView
    private lateinit var valueOutputTextView: TextView
    private lateinit var currencyFromSpinner: Spinner
    private lateinit var currencyToSpinner: Spinner

    override fun onCreate(savedInstanceState: Bundle?) {
        DaggerAppComponent.create().inject(this)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        adapter1 = initializeAdapter(spinnerCurrency1)
        adapter2 = initializeAdapter(spinnerCurrency2)

        presenter.onAttached(this)

        showList(listTable)

        valueInputTextView = findViewById(R.id.valueInput)
        valueOutputTextView = findViewById(R.id.valueOutput)
        currencyFromSpinner = findViewById(R.id.spinnerCurrency1)
        currencyToSpinner = findViewById(R.id.spinnerCurrency2)

        button.setOnClickListener {
            calculateExchangeRate()
        }

        viewSwap.setOnClickListener {
            Intent(this, ExchangeListActivity::class.java).let {
                startActivity(it)
            }
        }
    }

    override fun showConversionResult(amount: Float) {
        val positionOnList = currencyToSpinner.selectedItemPosition
        valueOutputTextView.text = "${amount} ${listCurrency[positionOnList]}"
    }

    override fun showList(exchangeRate: List<CurrencyTable>) {
        exchangeRate.forEach { table ->
            if (listCurrency.isEmpty()){
                table.rates.forEach{
                    listCurrency.add(it.currency)
                    listCode.add(it.code)
                    listMid.add(it.mid)
                    adapter1.notifyDataSetChanged()
                    adapter2.notifyDataSetChanged()
                }
            }else{
                listMid.clear()
                table.rates.forEach{
                    listMid.add(it.mid)
                    adapter1.notifyDataSetChanged()
                    adapter2.notifyDataSetChanged()
                }
                updateExchangeRate()

                val output: TextView = findViewById(R.id.valueOutput)
                if (output.text.toString() != "0"){
                    calculateExchangeRate()
                }
            }

        }
    }

    private fun calculateExchangeRate (){
        val currencyFrom: Int = currencyFromSpinner.selectedItemPosition
        val currencyTo: Int = currencyToSpinner.selectedItemPosition
        val valueInput: String = valueInputTextView.text.toString()
        presenter.onButtonPressed(valueInput, listMid[currencyFrom], listMid[currencyTo])
    }

    private fun updateExchangeRate() {
        val valueOne = currencyFromSpinner.selectedItemPosition
        val valueTwo = currencyToSpinner.selectedItemPosition

        val textView1: TextView = findViewById(R.id.modView1)
        textView1.text = listMid[valueOne].toString()

        val textView2: TextView = findViewById(R.id.modView2)
        textView2.text = listMid[valueTwo].toString()
    }

    private fun initializeAdapter(spinner: Spinner): ArrayAdapter<String> {
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, listCurrency)

        return adapter.apply {
            setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = this
        }
    }
}
