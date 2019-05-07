package div.blueryo.guess

import java.util.*

class SecretNumber{
    var secret = Random().nextInt(10)+1
    var count = 0

    //檢查數字，回傳差異多大
    fun validate(number:Int) : Int{
        count++
        return number-secret
    }

    fun reset(){
        secret=Random().nextInt(10)+1
        count=0
    }
}

fun main(){
    val secretNumber=SecretNumber()
    println (secretNumber.secret)
    println ("${secretNumber.validate(2)},count: ${secretNumber.count}")
}