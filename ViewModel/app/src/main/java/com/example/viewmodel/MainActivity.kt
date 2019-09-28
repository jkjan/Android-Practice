package com.example.viewmodel

import CodeBlockAdapter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.annotation.Nullable
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.viewmodel.databinding.ActivityMainBinding
import com.example.viewmodel.viewmodels.MainActivityViewModel
import kotlinx.android.synthetic.main.activity_main.*


public class CodeBlock (val funcName : String, val funcAbbrev : String)

class MainActivity : AppCompatActivity() {
    private lateinit var mAdapter : CodeBlockAdapter
    private var  mMainActivityViewModel  = MainActivityViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        binding.vm = mMainActivityViewModel
        binding.setLifecycleOwner(this)
        //connects the view model
        mMainActivityViewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        mMainActivityViewModel.init()  //retrieves data from repository
        val simple = CodeBlock("move();", "m")
        binding.move = simple
        //to observe live data objects
        mMainActivityViewModel.getCodeBlock()?.observe(this, object : Observer<List<CodeBlock>> {
            override  fun onChanged(@Nullable codeBlock: List<CodeBlock>) {
                mAdapter.notifyDataSetChanged()
            }
        })

        initRecyclerView()

        bt_move.setOnClickListener{
            mMainActivityViewModel.addNewBlock(CodeBlock("move();", "m"))
        }

        bt_turnLeft.setOnClickListener{
            mMainActivityViewModel.addNewBlock(CodeBlock("turnLeft();", "l"))
        }

        bt_turnRight.setOnClickListener{
            mMainActivityViewModel.addNewBlock(CodeBlock("turnRight();", "r"))
        }

        bt_pickAxe.setOnClickListener{
            mMainActivityViewModel.addNewBlock(CodeBlock("pickAxe;", "p"))
        }
    }

    private fun initRecyclerView() {
        mAdapter = CodeBlockAdapter(this, mMainActivityViewModel.getCodeBlock()?.value!!)
        var linearLayoutManager = LinearLayoutManager(this)
        rc_code_block_list.layoutManager = linearLayoutManager
        rc_code_block_list.adapter = mAdapter
    }

    fun addNewBlock(view : View, codeBlock : CodeBlock) {
        mMainActivityViewModel.addNewBlock(codeBlock)
    }
}
