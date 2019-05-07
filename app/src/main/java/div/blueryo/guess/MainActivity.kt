package div.blueryo.guess

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    val secretNumber=SecretNumber()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        findViewById<TextView>(R.id.txtView_Hello).text=secretNumber.secret.toString()
        Log.d("MainActivity","secret=${secretNumber.secret}")
    }


    fun chkNum(view: View){
        val num=ed_number2.text.toString().toInt()
        Log.d("MainActivity","number:$num")
        var diff=secretNumber.validate(num)
        var msg="恭禧你答對了"
        if (diff < 0 ){
            msg="再大一點"
        } else {
            if (diff>0){
                msg="再小一點"
            }
        }
//        Toast.makeText(this,msg,Toast.LENGTH_LONG)
        AlertDialog.Builder(this)
            .setMessage(msg)
            .setTitle("訊息")
            .setPositiveButton("ok",null)
            .show()
    }
}
