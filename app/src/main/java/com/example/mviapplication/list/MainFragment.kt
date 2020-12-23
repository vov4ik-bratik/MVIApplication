package com.example.mviapplication.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.mviapplication.R
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment : Fragment() {

    private lateinit var viewModel: ListViewModel
    private lateinit var itemsAdapter: ItemsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        ).get(ListViewModel::class.java)

        viewModel.viewState.observe(viewLifecycleOwner, Observer { state ->
            render(state)
        })

        itemsAdapter = ItemsAdapter()
        itemList.adapter  =itemsAdapter

        viewModel.loadData()
    }

    private fun render(viewState: ListViewState) {
        itemsAdapter.submitList(viewState.items)
    }
}
