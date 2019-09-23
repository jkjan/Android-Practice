package com.example.viewmodel.viewmodels

import CodeBlock
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.viewmodel.repositories.CodeBlocksRepository
import android.os.AsyncTask

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

        object : AsyncTask<Void, Void, Void>() {
            override fun onPostExecute(aVoid: Void?) {
                super.onPostExecute(aVoid)
                val currentPlaces : ArrayList<CodeBlock> = mCodeBlock!!.value!!
                currentPlaces.add(codeBlock)
                mCodeBlock!!.postValue(currentPlaces)
                mIsUpdating.postValue(false)
            }
            override fun doInBackground(vararg voids: Void?): Void? {
                return null
            }
        }.execute()
    }
}