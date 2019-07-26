package com.example.mockinstagram

import android.content.Context
import android.util.AttributeSet
import android.util.TypedValue
import android.widget.ImageView
import android.widget.LinearLayout

//1. 사용할 변수 초기화
//2. 점과 점 사이의 거리 지정 - dp 값을 pixel 값으로 변경
//3. 동그라미 만들기
//4. 선택된 점을 표시하기

class CircleIndicator :LinearLayout{
    //변수 선언
    private var mContext: Context? = null
    private var mDefaultCircle:Int = 0
    private var mSelectCircle:Int = 0
    private var imageDot : MutableList<ImageView> = mutableListOf()  //mutable 리스트는 가변함. 그 안에는 이미지뷰라는 라이브러리 들어감
    //imageDot라는 이름의 리스트, MutableList라는 자료형. 그 안에 <ImageView> 가 들어감

    //dp를 픽셀로
    private val temp = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 4.5f, resources.displayMetrics) //value는 점과 점 사이의 거리

    //view를 인스턴스화 해주기 위해 쓰이는 생성자
    constructor(context: Context):super(context) {  //상속한다
        mContext = context
    }

    //constructor는 첫번쨰 생성자를 만들고 나서 중복된 생성자를 또 생성할 때 씀
    //inflating 해주기 위해 쓰는 생성자
    constructor(context: Context, attributeSet: AttributeSet):super(context, attributeSet) {
        mContext = context
    }

    fun createDotPanel(count:Int, defaultCircle:Int, selectedCircle:Int, position:Int) {
        this.removeAllViews()

        mDefaultCircle = defaultCircle
        mSelectCircle = selectedCircle

        for (i in 0 until count) { //until은 .. 이랑 다름. until은 맨 마지막 값에서 -1을 해줌
            imageDot.add(ImageView(mContext).apply {
                setPadding(
                    temp.toInt(),
                    0,
                    temp.toInt(),
                    0
                )
            }) //temp는 픽셀값임. 따라서 int로 형변환 필요.

            this.addView(imageDot[i])
        }
        selectDot(position)

    }

    fun selectDot(position:Int) {
        for (i in imageDot.indices) {

            if (i == position)
                imageDot[i].setImageResource(mSelectCircle)

            else
                imageDot[i].setImageResource(mDefaultCircle)

            }
        }
}
