package model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Books(var tit:String, var aut:String, var an:Int, var not:Int){
    @PrimaryKey
    var titulo:String = tit

    var autor:String = aut
    var ano:Int = an
    var nota:Int = not
}