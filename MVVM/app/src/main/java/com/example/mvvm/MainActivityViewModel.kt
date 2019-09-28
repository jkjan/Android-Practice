package com.example.mvvm

import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainActivityViewModel : ViewModel() {
    private var mCodeBlock: MutableLiveData<ArrayList<CodeBlock>>? = null
    private var mRepo = CodeBlocksRepository()
    private var mIsUpdating = MutableLiveData<Boolean>(false)
    private val blockButton = arrayListOf<CodeBlock>(CodeBlock("turnLeft"),
        CodeBlock("turnRight();"),
        CodeBlock("turnLeft();"),
        CodeBlock("pickAxe();"))

    fun getBlockButton () : ArrayList<CodeBlock> {
        return blockButton
    }

    fun init() {
        if (mCodeBlock != null) {
            return
        }
        mRepo = CodeBlocksRepository().getInstance()
        mCodeBlock = mRepo.getCodeBlock()
    }

    fun getCodeBlock () : LiveData<ArrayList<CodeBlock>>? {
        return mCodeBlock
    }

    public fun addNewBlock(view : View, codeBlock : CodeBlock) {
        mIsUpdating.value = true
        Log.i("${codeBlock.funcName} ", "")
        val currentPlaces : ArrayList<CodeBlock> = mCodeBlock!!.value!!
        mCodeBlock!!.value!!.add(codeBlock)
        mCodeBlock!!.postValue(currentPlaces)
        mIsUpdating.postValue(false)
        Log.e("Hello World", " !!! ")
    }


}