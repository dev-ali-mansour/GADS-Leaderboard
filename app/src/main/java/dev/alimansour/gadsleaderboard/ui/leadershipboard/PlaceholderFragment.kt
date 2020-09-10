package dev.alimansour.gadsleaderboard.ui.leadershipboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.google.android.material.snackbar.Snackbar
import dev.alimansour.gadsleaderboard.R
import dev.alimansour.gadsleaderboard.data.remote.models.LeadershipTypes
import dev.alimansour.gadsleaderboard.data.remote.models.Learner
import dev.alimansour.gadsleaderboard.data.remote.models.ResultWrapper
import dev.alimansour.gadsleaderboard.databinding.FragmentMainBinding

/**
 * GADS Leaderboard Android Application developed by: Ali Mansour
 * Copyright © 2020 Ali Mansour. All Rights Reserved.
 * This file may not be redistributed in whole or significant part.
 * ----------------- GADS Leaderboard IS FREE SOFTWARE ------------------
 * https://www.alimansour.dev   |   dev.ali.mansour@gmail.com
 */
class PlaceholderFragment : Fragment() {

    private lateinit var adapter: LeadershipAdapter

    private val pageViewModel: PageViewModel by activityViewModels()

    private val type by lazy {
        val sectionNumber = arguments?.getInt(ARG_SECTION_NUMBER) ?: 1
        if (sectionNumber == 1) {
            LeadershipTypes.LearningLeaders
        } else {
            LeadershipTypes.SkillIqLeaders
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentMainBinding.inflate(inflater, container, false)
        val view = binding.root

        adapter = LeadershipAdapter(type)
        binding.recycler.adapter = adapter

        if (type == LeadershipTypes.LearningLeaders) {
            pageViewModel.learningLeadershipList.observe(viewLifecycleOwner, {
                bindList(view, it)
            })
        } else if (type == LeadershipTypes.SkillIqLeaders) {
            pageViewModel.skillIqLeadershipList.observe(viewLifecycleOwner, {
                bindList(view, it)
            })
        }
        return view
    }

    /**
     * Bind list of learners
     * @param view View
     * @param list List of learners
     */
    private fun bindList(view: View, list: ResultWrapper<List<Learner>>) {
        when (list) {
            is ResultWrapper.Success -> list.value?.let { adapter.submitList(it) }
            is ResultWrapper.GenericError -> showErrorSnackBar(
                view,
                R.string.something_went_wrong
            )
            is ResultWrapper.NetworkError -> showErrorSnackBar(view, R.string.no_internet)
        }
    }

    companion object {
        private const val ARG_SECTION_NUMBER = "section_number"

        /**
         * Returns a new instance of this fragment for the given section number
         * @param sectionNumber Section number
         * @return a new instance of this fragment
         */
        @JvmStatic
        fun newInstance(sectionNumber: Int): PlaceholderFragment {
            return PlaceholderFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_SECTION_NUMBER, sectionNumber)
                }
            }
        }
    }

    /**
     * Show error message using SnackBar
     * @param view View
     * @param msgId Message Id
     */
    private fun showErrorSnackBar(view: View, msgId: Int) {
        Snackbar.make(view, msgId, Snackbar.LENGTH_INDEFINITE)
            .setAction(getString(R.string.retry)) {
                pageViewModel.loadLeaders()
            }.show()
    }
}