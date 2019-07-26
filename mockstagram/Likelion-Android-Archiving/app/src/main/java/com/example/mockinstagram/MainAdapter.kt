package com.example.mockinstagram

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.view.ViewGroup

class MainAdapter (fm:FragmentManager):FragmentStatePagerAdapter(fm) {    //FragmentManager 라는 변수형의 fm 변수
                                                                          //FragmentStatePagerAdapter 라는 부모 클래스에 상속

    override fun getItem(p0: Int): Fragment { //viewpager 보여줄 뷰 객체 반환
        return when(p0) {
            0-> AFragment()    //p 값이 0이면 AFragment로 갈래
            1-> BFragment()
            2-> CFragment()

            else -> AFragment()

        }
    }

    override fun getCount() = 3 //뷰페이저에서 보여줄 뷰의 개수

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {  //viewpager에서 뷰 사라질 때 제거
        super.destroyItem(container, position, `object`)
    }
}