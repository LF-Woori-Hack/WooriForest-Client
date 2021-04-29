package mashup.littleforest.wooriforest.ui.fragment

import android.os.Bundle
import android.view.View
import com.tistory.blackjinbase.ext.toast
import mashup.littleforest.wooriforest.R
import mashup.littleforest.wooriforest.base.WFFragment
import mashup.littleforest.wooriforest.databinding.FragmentHobbyCheckBinding

class HobbyCheckFragment :
    WFFragment<FragmentHobbyCheckBinding>(R.layout.fragment_hobby_check) {

    override var logTag = "HobbyCheckFragment"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initButton()
    }

    private fun initButton() {
        binding.btnNoCheckHobby.setOnClickListener {
            toast("연결 안하고 바로 시작하기")
        }

        binding.btnCheckHobby.setOnClickListener {
            navigate(R.id.action_hobbyCheckFragment_to_hobbyRecommendFragment)
        }
    }
}