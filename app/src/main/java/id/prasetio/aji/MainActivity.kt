package id.prasetio.aji

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import com.google.android.material.textfield.TextInputEditText
import java.text.NumberFormat


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btn: Button = findViewById(R.id.submit_btn)
        btn.setOnClickListener{simulasi()}
    }

    private fun currencyConvert(num: Double): String {
        return NumberFormat.getCurrencyInstance().format(num)
    }

    private fun simulasi(){

        // Get All result layout element
        val bi_layout: LinearLayout = findViewById(R.id.biaya_layanan)
        val sb_layout: LinearLayout = findViewById(R.id.suku_bunga)
        val pb_layout: LinearLayout = findViewById(R.id.pencairan_bersih)
        val tp_layout: LinearLayout = findViewById(R.id.total_pinjaman)

        // Get Input Value
        val pinjaman_inpt:TextInputEditText = findViewById(R.id.pinjaman_edit_text)
        val tenor: TextInputEditText = findViewById(R.id.tenor_edit_text)
        var pinjaman_value = pinjaman_inpt.text.toString().toDoubleOrNull()
        var tenor_value = tenor.text.toString().toDoubleOrNull()

        // Calculate Loan Simulation
        var biaya_layanan = pinjaman_value?.times(0.05)
        var suku_bunga = pinjaman_value?.times(0.0375)
        var pencairan_bersih = pinjaman_value?.minus(biaya_layanan!!)
        var total_pinjaman = tenor_value?.let { suku_bunga?.times(it)?.let { pinjaman_value?.plus(it) } }

        // Get Result Element
        val bi_res: TextView = findViewById(R.id.biaya_layanan_result)
        val sb_res: TextView = findViewById(R.id.suku_bunga_result)
        val pb_res: TextView = findViewById(R.id.pencairan_bersih_result)
        val tp_res: TextView = findViewById(R.id.total_pinjaman_result)

        // put to text
        bi_res.text = currencyConvert(biaya_layanan!!)
        sb_res.text = currencyConvert(suku_bunga!!)
        pb_res.text = currencyConvert(pencairan_bersih!!)
        tp_res.text = currencyConvert(total_pinjaman!!)

        // Make is Visible
        bi_layout.visibility = View.VISIBLE
        sb_layout.visibility = View.VISIBLE
        pb_layout.visibility = View.VISIBLE
        tp_layout.visibility = View.VISIBLE
    }
}
