package mashup.littleforest.wooriforest.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import mashup.littleforest.wooriforest.R
import mashup.littleforest.wooriforest.base.WFFragment
import mashup.littleforest.wooriforest.databinding.FragmentHomeBinding

class HomeFragment :
    WFFragment<FragmentHomeBinding>(R.layout.fragment_home) {

    override var logTag = "HomeFragment"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        binding.ivNest.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.ic_img_bird))
        binding.tvNest.text = "#마블 피규어 둥지"
        binding.tvNestTitle.text = "용돈모으기"

        binding.ivItemNest.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.circle_gray))
        binding.tvItemNestTitle.text = "헐크버스터 MMS510 정품 \n" + "마블 어벤져스-아이디스타"
    }
}