package com.thedistantblue.triaryapp.mainscreen.power.detail.exercise

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.thedistantblue.triaryapp.database.room.dao.ExerciseDetailsDao

class PowerExerciseFragmentCompose : Fragment() {

    lateinit var exerciseDetailsDao: ExerciseDetailsDao


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return ComposeView(requireActivity()).apply {
            setContent {
                PowerExerciseScreen()
            }
        }
    }

}