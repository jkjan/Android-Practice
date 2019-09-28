package com.example.mvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.Nullable
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvvm.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var mMainActivityViewModel = MainActivityViewModel()
    lateinit var mAdapter : CodeBlockAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)

        //binds data
        binding.vm = mMainActivityViewModel
        binding.lifecycleOwner = this
        binding.codeBlock = mMainActivityViewModel.getBlockButton()

        //initialization
        mMainActivityViewModel.init()

        //recycler view connects
        mAdapter = CodeBlockAdapter(this, mMainActivityViewModel.getCodeBlock()?.value!!)
        var linearLayoutManager = LinearLayoutManager(this)
        rc_code_block_list.layoutManager = linearLayoutManager
        rc_code_block_list.adapter = mAdapter

        mMainActivityViewModel.getCodeBlock()?.observe(this, object : Observer<List<CodeBlock>> {
            override  fun onChanged(@Nullable codeBlock: List<CodeBlock>) {
                mAdapter.notifyDataSetChanged()
            }
        })
    }
}
