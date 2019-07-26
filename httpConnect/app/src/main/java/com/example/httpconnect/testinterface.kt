package com.example.httpconnect

import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface testinterface {
    //Observable 방법 사용
    //장점 : Callback 을 쓰지 않아도 됨 그리고 비즈니스 로직과 프로그래밍을 분리.
    @GET("/")  // /는 자기 자신을 가리킴, 이 친구는 경로의 모든 걸 받아 옴
    fun getGithub() : Observable<Github>

    /*
    //Call 방법 사용
    @GET("/user{user}")  //선택적으로 받음
    fun loadChange(@Query("user") status: String) :
            Call<List<Github>>
            */


}