package com.example.viewmodel.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.viewmodel.repositories.CodeBlocksRepository
import android.os.AsyncTask
import android.util.Log
import com.example.viewmodel.CodeBlock

class MainActivityViewModel : ViewModel() {
    //MutableLiveData extends LivaData, which it allows a user to modify objects not only to observe
    private var mCodeBlock: MutableLiveData<ArrayList<CodeBlock>>? = null
    private var mRepo = CodeBlocksRepository()
    private var mIsUpdating = MutableLiveData<Boolean>()

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

    fun addNewBlock(codeBlock : CodeBlock) {
        mIsUpdating.value = true
                Log.i("${codeBlock.funcName} ", "${codeBlock.funcAbbrev}")
                val currentPlaces : ArrayList<CodeBlock> = mCodeBlock!!.value!!
                mCodeBlock.value.add(codeBlock)
                mCodeBlock!!.postValue(currentPlaces)
                mIsUpdating.postValue(false)
    }
}