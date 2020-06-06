package br.com.listagem.util

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction


object ActivityUtils {

    fun addFragmentToActivity(
        fragmentManager: FragmentManager,
        fragment: Fragment, frameId: Int
    ) {
        val transaction: FragmentTransaction = fragmentManager.beginTransaction()
        transaction.add(frameId, fragment)
        transaction.commit()
    }
}