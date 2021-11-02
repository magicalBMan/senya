package com.sayers.senya.ui

import androidx.fragment.app.Fragment
import com.sayers.senya.data.Attraction

abstract class BaseFragment : Fragment() {
    protected val navController by lazy {
        (activity as MainActivity).navController
    }

    protected val attractions: List<Attraction>
        get() = (activity as MainActivity).attractionsList
}