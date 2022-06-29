package com.example.news_app_android.db

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.news_app_android.Screens.SplachActivity
import com.example.news_app_android.classs.modle.productModle
import java.util.function.DoubleToLongFunction
import kotlin.properties.Delegates

class MyDataBase(var context: Context) :
    SQLiteOpenHelper(context, dataBaseNsme, null, dataVersion) {

    private lateinit var db: SQLiteDatabase

    init {
        db = writableDatabase
    }


    override fun onCreate(db: SQLiteDatabase?) {
        db!!.execSQL(
            "CREATE TABLE MYBAG(id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "productId INTEGER);"
        )

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS MYBAG")
        onCreate(db)
    }


    fun getAllProductInBag(): ArrayList<productModle> {
        var arry = ArrayList<Int>()
        val c = db.rawQuery(
            "select * from MYBAG",
            null
        )
        c.moveToFirst()

        while (!c.isAfterLast) {
            val id = c.getInt(1)
            arry.add(id)
            c.moveToNext()
        }
        c.close()
        var priducts = ArrayList<productModle>()

        for (i in arry) {

            SplachActivity.productArrayList.forEach { it ->
                if (it.id == i) {
                    priducts.add(it)
                }

            }

        }
        return priducts

    }

    fun addProductToBag(id: Int): Boolean {

        var contentValue = ContentValues()
        contentValue.put("productId", id)
        return db.insert("MYBAG", null, contentValue) > 0

    }

    fun deleteProductFromBag(id: Int): Boolean {
        return db.delete("MYBAG", "productId =$id", null) > 0
    }

    fun deleteAllProductsFromBag() {
        var nnn = dataVersion + 1
        onUpgrade(db, dataVersion, nnn)
    }



    //////////////////
    companion object {
        val dataBaseNsme = "MyDataBasess"
        val dataVersion = 6
    }


}