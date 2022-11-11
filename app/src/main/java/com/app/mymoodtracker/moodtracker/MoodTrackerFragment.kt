package com.app.mymoodtracker.moodtracker

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil.inflate
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.app.mymoodtracker.R
import com.app.mymoodtracker.database.MoodDatabase
import com.app.mymoodtracker.databinding.FragmentMoodTrackerBinding

class MoodTrackerFragment : Fragment() {

    private lateinit var binding: FragmentMoodTrackerBinding
    private lateinit var moodTrackerViewModel: MoodTrackerViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = inflate(inflater, R.layout.fragment_mood_tracker, container, false)

        val application = requireNotNull(this.activity).application
        val dataSource = MoodDatabase.getInstance(application).moodDatabaseDao
        val viewModelFactory = MoodTrackerViewModelFactory(dataSource, application)

        moodTrackerViewModel =
            ViewModelProvider(this, viewModelFactory)
                .get(MoodTrackerViewModel::class.java)

        binding.moodTrackerViewModel = moodTrackerViewModel
        binding.lifecycleOwner = this

        val adapter = MoodsAdapter(MoodListener { moodId ->
            moodTrackerViewModel.onMoodClicked(moodId)
        })
        binding.recyclerView.adapter = adapter

        moodTrackerViewModel.navigationToMoodDetails.observe(viewLifecycleOwner,{mood ->
            mood?.let{
                this.findNavController().navigate(
                    MoodTrackerFragmentDirections.actionMoodTrackerFragmentToMoodDetailsFragment(mood))

                moodTrackerViewModel.onMoodDetailsNavigated()
            }
        })

        moodTrackerViewModel.allMoods.observe(viewLifecycleOwner, {
            it?.let {
                adapter.submitList(it)
            }
        })

        moodTrackerViewModel.navigationToMoodQuality.observe(viewLifecycleOwner, { mood ->
            mood?.let {
                this.findNavController().navigate(
                    MoodTrackerFragmentDirections.actionMoodTrackerFragmentToMoodQualityFragment(
                        //mood.moodId
                    )
                )
                moodTrackerViewModel.doneNavigation()
            }
        })

        setHasOptionsMenu(true)

        return binding.root
    }

    // Option Menu for delete all data
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.option_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.delete_item -> {

                val builder = AlertDialog.Builder(requireActivity())
                builder.apply {
                    setCancelable(true)
                    setTitle("Are You Sure?")
                    setMessage("Do you want to delete all data?")
                    setPositiveButton("Yes") { _, _ -> moodTrackerViewModel.onClear() }
                    setNegativeButton("No", null)
                }
                val dialog: AlertDialog = builder.create()
                dialog.show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}