package fr.isen.rey.androiderestaurant.user

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import fr.isen.rey.androiderestaurant.databinding.FragmentRegisterBinding

class RegisterFragment : Fragment() {
    lateinit var binding: FragmentRegisterBinding
    private var interactor: UserActivityFragmentInteraction? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        interactor = context as? UserActivityFragmentInteraction
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.alreadyAccount.setOnClickListener {
            interactor?.showLogin()
        }
        binding.registerButton.setOnClickListener {
            interactor?.makeRequest(
                binding.email.text.toString(),
                binding.password.text.toString(),
                binding.firstname.text.toString(),
                binding.lastname.text.toString(),
                false
            )
        }
    }
}