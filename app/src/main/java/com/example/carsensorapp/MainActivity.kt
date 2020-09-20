package com.example.carsensorapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.carsensorapp.services.models.UsedCarDetailModel
import com.example.carsensorapp.services.models.UsedCarModel
import com.example.carsensorapp.ui.main.MainFragment
import com.example.carsensorapp.ui.usedcardetail.UsedCarDetailFragment

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
        //詳細のFragment
        val usedCarDetailModel = UsedCarDetailModel(
            usedCar.brand.name,
            usedCar.model,
            usedCar.grade,
            usedCar.price,
            usedCar.width,
            usedCar.height,
            usedCar.length,
            usedCar.period,
            usedCar.person,
            usedCar.series,
            usedCar.desc,
            usedCar.body.name,
            usedCar.photo.main.l,
            usedCar.urls.mobile
        )
        val usedCarDetailFragment = UsedCarDetailFragment.forUsedCar(usedCarDetailModel)
        supportFragmentManager
            .beginTransaction()
            .addToBackStack("project")
            .replace(R.id.container, usedCarDetailFragment, null)
            .commit()
    }

}