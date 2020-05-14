package com.example.dietkuyy

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView

/**
 * A simple [Fragment] subclass.
 */
class Ideal : Fragment() {
   private var weight: EditText? = null
    private var height: EditText? = null
    lateinit var fragview:View
   private var resulttext: TextView? = null
    private var calculation: String? = null
    private var BMIresult: String? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        fragview =  inflater.inflate(R.layout.fragment_ideal, container, false)
        weight = fragview.findViewById(R.id.weight)
        height = fragview.findViewById(R.id.height)
        resulttext = fragview.findViewById(R.id.result)

        return fragview
    }
    fun calculateBMI(view: View?) {
        val S1 = weight!!.text.toString()
        val S2 = height!!.text.toString()
        val weightValue = S1.toFloat()
        val heightValue = S2.toFloat() / 100
        val bmi = weightValue / (heightValue * heightValue)
        BMIresult = if (bmi < 16) {
            "Severely Under Weight"
        } else if (bmi < 18.5) {
            "Under Weight"
        } else if (bmi >= 18.5 && bmi <= 24.9) {
            "Normal Weight"
        } else if (bmi >= 25 && bmi <= 29.9) {
            "Overweight"
        } else {
            "Obese"
        }
        calculation = "Result:nn" + bmi + "n" + BMIresult
        resulttext!!.text = calculation
    }
}
