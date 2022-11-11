package com.app.mymoodtracker.mooddetails

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.app.mymoodtracker.R
import com.app.mymoodtracker.database.MoodDatabase
import com.app.mymoodtracker.databinding.FragmentMoodDetailsBinding

class MoodDetailsFragment : Fragment() {

    lateinit var binding: FragmentMoodDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_mood_details, container, false)

        val application = requireNotNull(this.activity).application
        val arguments = MoodDetailsFragmentArgs.fromBundle(requireArguments())

        val dataSource = MoodDatabase.getInstance(application).moodDatabaseDao
        val viewModelFactory = MoodDetailsViewModelFactory(arguments.moodsKey, dataSource)

        val moodDetailsViewModel =
            ViewModelProvider(this, viewModelFactory).get(MoodDetailsViewModel::class.java)

        binding.moodDetailsViewModel = moodDetailsViewModel
        binding.lifecycleOwner = this

        return binding.root
    }

}