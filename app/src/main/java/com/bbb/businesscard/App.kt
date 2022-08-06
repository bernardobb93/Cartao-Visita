package com.bbb.businesscard

import android.app.Application
import com.bbb.businesscard.data.AppDatabase
import com.bbb.businesscard.data.BusinessCardRepository

class App : Application(){
    val database by lazy {AppDatabase.getDatabase(this)}
    val repository by lazy {BusinessCardRepository(database.businessDao())}

}