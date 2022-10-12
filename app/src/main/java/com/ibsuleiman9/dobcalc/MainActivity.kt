package com.ibsuleiman9.dobcalc

import android.app.DatePickerDialog
import android.icu.util.Calendar
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.ibsuleiman9.dobcalc.databinding.ActivityMainBinding
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val calendarObj = Calendar.getInstance()
        val year1 = calendarObj.get(Calendar.YEAR)
        val month1 = calendarObj.get(Calendar.MONTH)
        val day1 = calendarObj.get(Calendar.DAY_OF_MONTH)

        binding.tvSelectedDate.text = "$day1.$month1.$year1"
        binding.tvMarqueText.isSelected = true

        binding.btnDatePicker.setOnClickListener {
            showDatePickerDialog()
        }
    }

    private fun showDatePickerDialog() {
        val calendarObj = Calendar.getInstance()
        val year1 = calendarObj.get(Calendar.YEAR)
        val month1 = calendarObj.get(Calendar.MONTH)
        val day1 = calendarObj.get(Calendar.DAY_OF_MONTH)

        val datepickerdialog = DatePickerDialog(this,
            DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                val selectedDate = "$dayOfMonth/${month+1}/${year}"
                binding.tvSelectedDate.text = selectedDate

                val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)  //implement (java.text)
                val theDate = sdf.parse(selectedDate)
                val t0 = theDate.time  //Time in milliseconds since Jan 1, 1970 until selected Date
                val t1 = sdf.parse(sdf.format(System.currentTimeMillis())).time  //Time in milliseconds since Jan 1, 1970 until present time
                val td = t1 - t0
                val timeInMinutes = td/60000
                Toast.makeText(this, "Difference: $td", Toast.LENGTH_LONG).show()
                binding.tvTimeInMinutes.text = timeInMinutes.toString()
        },
            year1,
            month1,
            day1
        )
        datepickerdialog.datePicker.maxDate = System.currentTimeMillis()
        datepickerdialog.show()
    }

}