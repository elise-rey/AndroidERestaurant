package fr.isen.rey.androiderestaurant.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.squareup.picasso.Picasso
import fr.isen.rey.androiderestaurant.R
import fr.isen.rey.androiderestaurant.databinding.FragmentPhotoBinding

class PhotoFragment: Fragment() {
    lateinit var binding: FragmentPhotoBinding

    companion object {
        const val URL = "URL"

        fun newInstance(url: String): PhotoFragment {
            return PhotoFragment().apply {
                arguments = Bundle().apply {
                    putString(URL, url)
                }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPhotoBinding.inflate(inflater, container, false)
        return (binding.root)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val url = arguments?.getString(URL)
        if (url?.isNotEmpty() == true) {
            Picasso.get().load(url).into(binding.photoBanner)
        }
    }
}
