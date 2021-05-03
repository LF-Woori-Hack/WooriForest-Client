package mashup.littleforest.wooriforest.ui.fragment

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import com.tistory.blackjinbase.ext.toast
import mashup.littleforest.wooriforest.R
import mashup.littleforest.wooriforest.base.WFFragment
import mashup.littleforest.wooriforest.databinding.FragmentHobbyCheckBinding

class HobbyCheckFragment :
    WFFragment<FragmentHobbyCheckBinding>(R.layout.fragment_hobby_check) {

    override var logTag = "HobbyCheckFragment"

    private lateinit var callback: OnBackPressedCallback

    override fun onAttach(context: Context) {
        super.onAttach(context)
        callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                activity?.finish()
            }
        }

        requireActivity().onBackPressedDispatcher.addCallback(this, callback)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initButton()
    }

    override fun onDetach() {
        callback.remove()
        super.onDetach()
    }

    private fun initButton() {
        binding.btnNoCheckHobby.visibility = View.GONE
        binding.btnNoCheckHobby.setOnClickListener {
            toast("연결 안하고 바로 시작하기")
        }

        binding.btnCheckHobby.setOnClickListener {
            navigate(R.id.action_hobbyCheckFragment_to_accountRegisterFragment)
        }
    }
}