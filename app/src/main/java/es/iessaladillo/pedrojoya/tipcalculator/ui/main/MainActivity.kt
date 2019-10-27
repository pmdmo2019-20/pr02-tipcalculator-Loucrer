package es.iessaladillo.pedrojoya.tipcalculator.ui.main

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import es.iessaladillo.pedrojoya.tipcalculator.R
import es.iessaladillo.pedrojoya.tipcalculator.model.tipCalculator

class MainActivity : AppCompatActivity() {


    private lateinit var btnResetTip: Button
    private lateinit var btnResetDiners: Button
    private lateinit var txtBill: EditText
    private lateinit var txtTip: EditText
    private lateinit var txtPorcentage: EditText
    private lateinit var txtTotal: EditText
    private lateinit var txtDiners: EditText
    private lateinit var txtPerDiners: EditText
    private lateinit var txtPerDinerRounded: EditText
    private lateinit var tipCalculator: tipCalculator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupViews()
        onStart()
    }

    private fun setupViews() {
        //BUTTONS
        btnResetTip = findViewById(R.id.btnResetTip)
        btnResetDiners = findViewById(R.id.btnResetDiners)
        //EDITTEXT
        txtBill = findViewById(R.id.txtBill)
        txtTip = findViewById(R.id.txtTip)
        txtPorcentage = findViewById(R.id.txtPercentage)
        txtTotal = findViewById(R.id.txtTotal)
        txtDiners = findViewById(R.id.txtDiners)
        txtPerDiners = findViewById(R.id.txtPerDiner)
        txtPerDinerRounded = findViewById(R.id.txtPerDinerRounded)
        //OBJECT
        tipCalculator = tipCalculator(
            txtBill.text.toString().replace(",", ".").toFloat(),
            txtPorcentage.text.toString().replace(",", ".").toFloat(),
            txtDiners.text.toString().toInt()
        )
    }

    override fun onStart() {
        super.onStart()
        btnResetTip.setOnClickListener { resetValuesDefault() }
        btnResetDiners.setOnClickListener { resetValuesDefaulFieldsDiners() }
        calculateBill()
        calculatePorcentage()
        calculateDinner()
    }

    private fun calculateDinner() {
        txtDiners.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(p0: Editable) {}

            override fun beforeTextChanged(p0: CharSequence, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence, p1: Int, p2: Int, p3: Int) {
                if (validateField(p0)) {
                    settleDinners(p0)
                } else {
                    txtDiners.setText(getText(R.string.defaultValueDiners))
                }
            }
        })
    }

    private fun calculatePorcentage() {
        txtPorcentage.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(p0: Editable) {}

            override fun beforeTextChanged(p0: CharSequence, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence, p1: Int, p2: Int, p3: Int) {
                if (validateField(p0)) {
                    settlePorcentage(p0)
                } else {
                    txtPorcentage.setText(getText(R.string.textDefaultPorcentage))
                }
            }
        })
    }

    private fun calculateBill() {
        txtBill.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence, p1: Int, p2: Int, p3: Int) {
                if (validateField(p0)) {
                    settleBill(p0)
                } else {
                    txtBill.setText(getText(R.string.defaultValueBill))
                }
            }

            override fun afterTextChanged(p0: Editable) {}
        })
    }

    private fun settlePorcentage(p0: CharSequence) {
        tipCalculator.porcentage = p0.toString().replace(",", ".").toFloat()
        fillData()
    }

    private fun settleDinners(p0: CharSequence) {
        tipCalculator.dinners = p0.toString().toInt()
        txtPerDiners.setText(
            String.format(
                "%.2f",
                tipCalculator.calculatePerDiner().toString().replace(",", ".").toFloat()
            )
        )
        txtPerDinerRounded.setText(
            String.format(
                "%.2f",
                tipCalculator.calculatePerDinerRounded().toString().replace(",", ".").toFloat()
            )
        )
    }

    private fun settleBill(p0: CharSequence) {
        tipCalculator.bill = p0.toString().replace(",", ".").toFloat()
        fillData()
    }

    private fun fillData() {
        txtTip.setText(
            String.format(
                "%.2f",
                tipCalculator.calculateTip().toString().replace(",", ".").toFloat()
            )
        )
        txtTotal.setText(
            String.format(
                "%.2f",
                tipCalculator.calculateTotal().toString().replace(",", ".").toFloat()
            )
        )
        txtPerDiners.setText(
            String.format(
                "%.2f",
                tipCalculator.calculatePerDiner().toString().replace(",", ".").toFloat()
            )
        )
        txtPerDinerRounded.setText(
            String.format(
                "%.2f",
                tipCalculator.calculatePerDinerRounded().toString().replace(",", ".").toFloat()
            )
        )
    }

    private fun validateField(p0: CharSequence): Boolean {
        var validate = true
        if (p0.toString().isEmpty()) {
            validate = false
        }
        return validate
    }


    private fun resetValuesDefaulFieldsDiners() {
        txtDiners.setText(getText((R.string.defaultValueDiners)))
        txtDiners.requestFocus()
    }

    private fun resetValuesDefault() {
        txtBill.setText(getText(R.string.defaultValueBill))
        txtPorcentage.setText(getText(R.string.textDefaultPorcentage))
        txtBill.requestFocus()
    }


}
