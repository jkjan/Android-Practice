package com.example.httpconnect

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.layout_test.*
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class testActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_test)

        funGit()

    }

    fun funGit() {
        val retrofit = Retrofit.Builder()
            //받은 응답을 observable 형태로 변환해줌
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                //서버에서 온 json 형식의 data를 파싱해서 받아옴 (json을 gson 형식으로 받아서 data class에다 넣어줌)
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://api.github.com/")
            .build()

        val api = retrofit.create(testinterface::class.java)
        api.getGithub()
            //subscribeOn : 옵저버블의 스케쥴러를 바꾸는 것.
                //RxJava 코드를 어느 스레드에서 실행해야 할 지 지정해줌
                //subscribeOn과 observeOn 함수를 지정해주면 데이터 흐름이 발생하는
            //스레드와 io (출력) 스레드를 분리할 수 있다.
            .subscribeOn(Schedulers.io())
                //이후 실행할 코드를 mainThread에서 실행할 수 있다
            .observeOn(AndroidSchedulers.mainThread()) //observable을 사용하는 이유
            .subscribe({result ->
                hi.text = "이메일 부분 ${result.emails_url}"
                mother.text = "emotion ${result.emojis_url}"
                fucker.text = "event ${result.events_url}"
            }, { error ->
                error.printStackTrace()
            }, {
                //에러 났을 때
                Log.e("에러", "complete")
            })

    }
}
