package com.example.carsensorapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.carsensorapp.ui.main.MainFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, MainFragment.newInstance())
                    .commitNow()
        }

        // アクションバー(画面上部)のタイトル変更
        supportActionBar?.title = "カーセンサーAPI 中古車検索アプリ"
    }
}