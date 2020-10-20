package com.example.finalnote_project.fragment

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.finalnote_project.R
import com.example.finalnote_project.data.Note
import com.example.finalnote_project.data.NoteDatabase
import com.example.finalnote_project.data.NoteRepository
import com.example.finalnote_project.databinding.FragmentAddlistBinding
import com.example.finalnote_project.viewmodel.NoteRecyclerViewAdapter
import com.example.finalnote_project.viewmodel.NoteViewModel
import com.example.finalnote_project.viewmodel.NoteViewModelFactory


class Addlist : Fragment() {

    private lateinit var binding: FragmentAddlistBinding
    private lateinit var noteViewModel: NoteViewModel
    private lateinit var adapter: NoteRecyclerViewAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var binding = DataBindingUtil.inflate<FragmentAddlistBinding>(
            inflater,
            R.layout.fragment_addlist,
            container,
            false
        )
        setHasOptionsMenu(true)

        val application = requireNotNull(this.activity).application
        val dao = NoteDatabase.getInstance(application).noteDao
        val repository = NoteRepository(dao)
        val factory = NoteViewModelFactory(repository)

        val noteViewModel = ViewModelProvider(
            this,factory
        ).get(NoteViewModel::class.java)
        binding.myViewModel = noteViewModel
        binding.lifecycleOwner = this

//        initRecyclerView()

        return binding.root
    }

//    private fun initRecyclerView(){
//        binding.noteRecyclerView.layoutManager = LinearLayoutManager(this)
//        adapter = NoteRecyclerViewAdapter({selectedItem:Note->listItemClicked(selectedItem)})
//        binding.noteRecyclerView.adapter = adapter
//        displaySubscribersList()
//    }
//
//    private fun displaySubscribersList(){
//        noteViewModel.note.observe(this, Observer {
//            Log.i("MYTAG",it.toString())
//            adapter.setList(it)
//            adapter.notifyDataSetChanged()
//        })
//    }
//
//    private fun listItemClicked(note: Note){
//        //Toast.makeText(this,"selected name is ${subscriber.name}",Toast.LENGTH_LONG).show()
//        noteViewModel.initUpdateAndDelete(note)
//    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(
            item!!,
            view!!.findNavController()
        ) || super.onOptionsItemSelected(item)
    }

}