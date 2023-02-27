package com.example.lab2
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlin.math.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun discriminant(a: Double, b: Double, c: Double) = ((b*b) - (4 * a * c))

    fun quadraticEquationRoot1(a: Double, b: Double, c: Double) =
        (-b + sqrt(discriminant(a, b, c))) / (2 * a)

    fun quadraticEquationRoot2(a: Double, b: Double, c: Double) =
        (-b - sqrt(discriminant(a, b, c))) / (2 * a)

    fun TakeMyParametr(view: View){
        var a = findViewById<TextView>(R.id.editTextNumberA).text.toString().toDouble()
        val b = findViewById<TextView>(R.id.editTextNumberB).text.toString().toDouble()
        val c = findViewById<TextView>(R.id.editTextNumberC).text.toString().toDouble()
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Решение")
        if (discriminant(a, b, c) < 0){
            builder.setMessage("Уровнение не имеет корней")
        }else if (discriminant(a, b, c) == 0.0){
            val number = String.format("%.2f", quadraticEquationRoot1(a, b, c)).toDouble()
            val x = CheckforInt(number)
            builder.setMessage("Уровнение имеет 1 корень \n х = $x")
        }else{
            val x1 = String.format("%.2f", quadraticEquationRoot1(a, b, c)).toDouble()
            val x2 = String.format("%.2f", quadraticEquationRoot2(a, b, c)).toDouble()
            if(x1 == x2){
                val x1 = CheckforInt(x1)
                builder.setMessage("Уровнение имеет 2 корня \n x1 = x2 = $x1")
            }else{
                val x1 = CheckforInt(x1)
                val x2 = CheckforInt(x2)
                builder.setMessage("Уровнение имеет 2 корня \n x1 = $x1 \n x2 = $x2")
            }
        }
        builder.setPositiveButton("Ок"){dialogInterface, which ->
            Toast.makeText(applicationContext,"clicked yes",Toast.LENGTH_LONG).show()
        }
        val alertDialog: AlertDialog = builder.create()
        alertDialog.setCancelable(false)
        alertDialog.show()
    }

    fun CheckforInt(double: Double): Any {
        return if(double == double.roundToInt().toDouble()){
            double.roundToInt()
        }else{
            double
        }
    }
}