package mashup.littleforest.wooriforest.ui.fragment

import android.os.Bundle
import android.view.View
import mashup.littleforest.wooriforest.R
import mashup.littleforest.wooriforest.base.WFFragment
import mashup.littleforest.wooriforest.databinding.FragmentLoginBinding

class LoginFragment : WFFragment<FragmentLoginBinding>(R.layout.fragment_login) {

    override var logTag = "LoginFragment"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initButton()
    }

    private fun initButton() {
        binding.btnKakaoLogin.setOnClickListener {
            goToHobbyCheck()
        }
    }

    private fun goToHobbyCheck() {
        navigate(R.id.action_loginFragment_to_hobbyCheckFragment)
    }
}