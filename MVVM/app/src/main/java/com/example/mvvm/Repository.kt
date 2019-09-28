package com.example.mvvm

import androidx.lifecycle.MutableLiveData

class CodeBlock (val funcName : String) {
    fun getName() : String {
        return funcName
    }
}

class CodeBlocksRepository {
    private var instance : CodeBlocksRepository? = null
    private var dataSet : ArrayList<CodeBlock> = ArrayList<CodeBlock>()   //a variable that contains real data

    fun getInstance() : CodeBlocksRepository {
        if (instance == null) {
            instance = CodeBlocksRepository()
        }
        return instance!!
    }

    //pretend to get data from a webservice or online source
    fun getCodeBlock() : MutableLiveData<ArrayList<CodeBlock>> {
        var data = MutableLiveData<ArrayList<CodeBlock>>()
        data.value = dataSet
        return data
    }
}