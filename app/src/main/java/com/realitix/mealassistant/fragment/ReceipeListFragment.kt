package com.realitix.mealassistant.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.realitix.mealassistant.R
import com.realitix.mealassistant.adapter.GenericAdapter
import com.realitix.mealassistant.database.entity.Receipe
import com.realitix.mealassistant.repository.ReceipeRepository
import com.realitix.mealassistant.util.RecyclerItemClickListener
import com.realitix.mealassistant.util.SingleLineItemViewHolder
import com.realitix.mealassistant.viewmodel.ReceipeListViewModel
import com.realitix.mealassistant.viewmodel.RepositoryViewModelFactory
import kotlinx.android.synthetic.main.fragment_receipe_list.*

class ReceipeListFragment : Fragment() {
    private val viewModel: ReceipeListViewModel by viewModels(
        factoryProducer = {
            RepositoryViewModelFactory {
                ReceipeListViewModel(ReceipeRepository.getInstance(context!!))
            }
        }
    )

    private lateinit var adapter: GenericAdapter<SingleLineItemViewHolder, Receipe>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_receipe_list, container, false)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        collapsingToolbar.setupWithNavController(toolbar, findNavController())

        // Set RecyclerView
        adapter = GenericAdapter(
            {v: ViewGroup ->  SingleLineItemViewHolder.create(v)},
            {holder, receipe ->
                holder.text.text = receipe.name
                holder.icon.setImageDrawable(ContextCompat.getDrawable(context!!, R.drawable.ic_receipt_black_36dp))
            }
        )
        recyclerView.hasFixedSize()
        recyclerView.adapter = adapter

        viewModel.receipes.observe(viewLifecycleOwner) {
            adapter.setData(it)
        }

        recyclerView.addOnItemTouchListener(RecyclerItemClickListener(context!!, recyclerView, object: RecyclerItemClickListener.OnItemClickListener {
            override fun onItemClick(view: View, position: Int) {
                val receipe = adapter.getAtPosition(position)
                val action = ReceipeListFragmentDirections.actionReceipelistToSingle(receipe.id)
                view.findNavController().navigate(action)
            }

            override fun onLongItemClick(view: View, position: Int) {
            }
        }))

        val navController = findNavController()
        /*val fab = activity!!.fab
        fab.show()
        fab.setOnClickListener {
            val action = ReceipeListFragmentDirections.actionReceipelistToSingle(-1)
            navController.navigate(action)
        }*/
    }
}
