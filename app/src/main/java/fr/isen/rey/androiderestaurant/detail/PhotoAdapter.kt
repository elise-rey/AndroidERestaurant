package fr.isen.rey.androiderestaurant.detail

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class PhotoAdapter(activity: AppCompatActivity, val list: List<String>): FragmentStateAdapter(activity) {
    override fun getItemCount(): Int {
        return list.count()
    }

    override fun createFragment(position: Int): Fragment {
        return PhotoFragment.newInstance(list[position])
    }
}