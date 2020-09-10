package com.example.carsensorapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.carsensorapp.services.models.UsedCarModel
import com.example.carsensorapp.ui.main.MainFragment
import com.example.carsensorapp.ui.main.UsedCarDetailFragment

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

    fun show(usedCar: UsedCarModel) {
        val projectFragment = UsedCarDetailFragment.forUsedCar(usedCar.id) //詳細のFragment
        supportFragmentManager
            .beginTransaction()
            .addToBackStack("project")
            .replace(R.id.container, projectFragment, null)
            .commit()
    }

}