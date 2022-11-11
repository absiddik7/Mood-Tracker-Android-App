package com.app.mymoodtracker.moodquality

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.databinding.ObservableField
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.app.mymoodtracker.R
import com.app.mymoodtracker.database.MoodDatabase
import com.app.mymoodtracker.databinding.FragmentMoodQualityBinding


class MoodQualityFragment : Fragment() {

    lateinit var binding: FragmentMoodQualityBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_mood_quality, container, false)

        val application = requireNotNull(this.activity).application
        //val arguments = MoodQualityFragmentArgs.fromBundle(requireArguments())
        val dataSource = MoodDatabase.getInstance(application).moodDatabaseDao

        val viewModelFactory = MoodQualityViewModelFactory(dataSource)


        val moodQualityViewModel = ViewModelProvider(this, viewModelFactory)
            .get(MoodQualityViewModel::class.java)

        binding.moodQualityViewModel = moodQualityViewModel

        moodQualityViewModel.navigationToMoodTracker.observe(viewLifecycleOwner, {
            if (it == true) {
                this.findNavController().navigate(
                    MoodQualityFragmentDirections.actionMoodQualityFragmentToMoodTrackerFragment()
                )
                moodQualityViewModel.doneNavigationToMoodTracker()
            }
        })

        moodQualityViewModel.showToast.observe(viewLifecycleOwner, Observer {
            if (it == true) {
                Toast.makeText(requireContext(), "How are you?", Toast.LENGTH_SHORT)
                    .show()
                moodQualityViewModel.doneShowToast()
            }
        })

        moodQualityViewModel.showToast2.observe(viewLifecycleOwner, Observer {
            if (it == true) {
                Toast.makeText(requireContext(), "Write something about it", Toast.LENGTH_SHORT)
                    .show()
                moodQualityViewModel.doneShowToast2()
            }
        })
        
        return binding.root
    }
}