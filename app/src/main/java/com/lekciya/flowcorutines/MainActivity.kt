package com.lekciya.flowcorutines

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.lifecycle.asLiveData
import com.lekciya.flowcorutines.DB.MainDB
import com.lekciya.flowcorutines.DB.Person
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val but = findViewById<Button>(R.id.button)
        val etName = findViewById<EditText>(R.id.editTextText)
        val etDescr = findViewById<EditText>(R.id.editTextText2)
val tv = findViewById<TextView>(R.id.tvData)
        val db = MainDB.getDb(this)
        db.Dao().getAllPersonsFlow().asLiveData().observe(this){list ->
            tv.text = ""
            list.forEach {
                val content = "id ${it.id} name ${it.name}  ${it.description}\n"
                tv.append(content)
            }
        }
        but.setOnClickListener {
            // добавление с помощью потока
            val person = Person(
                null,
                etName.text.toString(),
                etDescr.text.toString()
            )
            Thread {
                 db.Dao().insertPerson(person)
            }.start()
            // добавление с помощью корутины
            CoroutineScope(Dispatchers.IO).launch {
                val person2 = Person(
                    null,
                    etName.text.toString() + " Coroutine",
                    etDescr.text.toString() + " Coroutine"
                )
                db.Dao().insertPerson(person2)
            }

        }

        /*      ЗАДАНИЕ Лаба 3
        добавьте 2 сущности (любые)
        увеличте размер шрифта при выводе
        выводимые данные должны выглядеть как таблица, то есть заоголовки тоже нужны,
        заголовки должны соответствовать выводимым данным и браться автоматически из БД,
         использовать можно любые элементы вывода, выводить можно любую сущность
         */


    }
}