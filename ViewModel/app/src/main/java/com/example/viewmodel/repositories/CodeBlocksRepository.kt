package com.example.viewmodel.repositories

import CodeBlock
import androidx.lifecycle.MutableLiveData
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
        setCodeBlock()
        var data = MutableLiveData<ArrayList<CodeBlock>>()
        data.value = dataSet
        return data
    }

    private fun setCodeBlock() {
//        dataSet.add(CodeBlock("move();", "m"))
//        dataSet.add(CodeBlock("turnLeft();", "l"))
//        dataSet.add(CodeBlock("turnRight();", "r"))
//        dataSet.add(CodeBlock("pickAxe();", "p"))
    }
}