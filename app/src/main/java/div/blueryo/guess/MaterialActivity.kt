package div.blueryo.guess

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity;

import kotlinx.android.synthetic.main.activity_material.*
import kotlinx.android.synthetic.main.content_material.*
import kotlin.system.exitProcess

class MaterialActivity : AppCompatActivity() {
    val secretNumber=SecretNumber()
    val TAG=MaterialActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_material)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            AlertDialog.Builder (this)
                .setTitle("Replay game")
                .setMessage("Are you sure??")
                .setPositiveButton(getString(R.string.ok)) { dialog, which ->
                    secretNumber.reset()
                    txt_Counter.text=secretNumber.count.toString()
                    ed_number2.setText("")
                }
                .setNeutralButton(getString(R.string.no),null)
                .show()
        }
        Log.d(TAG,"onCreate Answer-->${secretNumber.secret}")
        txt_Counter.text=secretNumber.count.toString()
    }

    fun chkNum(view: View){
        val num=ed_number2.text.toString().toInt()
        Log.d(TAG,"number:$num")
        var diff=secretNumber.validate(num)
        var msg=getString(R.string.you_got_it)
        when {
            diff<0 -> msg=getString(R.string.bigger)
            diff>0 -> msg=getString(R.string.smaller)
            else ->
                if (secretNumber.count<3) {
                    msg = "${getString(R.string.excellent_msg)} ${secretNumber.secret}"
                }
        }

//        Toast.makeText(this,msg,Toast.LENGTH_LONG)
        txt_Counter.text=secretNumber.count.toString()
        ed_number2.setText("")
        AlertDialog.Builder(this)
            .setMessage(msg)
            .setTitle("訊息")
            .setPositiveButton(getString(R.string.ok),{dialog,which ->
                if (diff==0) {
                    AlertDialog.Builder(this)
                        .setTitle(getString(R.string.again))
                        .setMessage(getString(R.string.new_game_q))
                        .setPositiveButton(getString(R.string.ok)) { dialog, which ->
                            secretNumber.reset()
                            txt_Counter.text = secretNumber.count.toString()
                            ed_number2.setText("")
                            Log.d(TAG, "New Game Answer-->${secretNumber.secret}")
                        }
                        .setNeutralButton("EXIT", {dialog,which ->
                            moveTaskToBack(true)
                            exitProcess(-1)
                        })
                        .show()
                }
            })
            .show()
    }
}
