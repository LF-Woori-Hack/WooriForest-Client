package mashup.littleforest.wooriforest.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import mashup.littleforest.wooriforest.R
import mashup.littleforest.wooriforest.base.WFFragment
import mashup.littleforest.wooriforest.databinding.FragmentHomeBinding
import mashup.littleforest.wooriforest.ui.dialog.FinancialTipFragmentDialog

class HomeFragment :
    WFFragment<FragmentHomeBinding>(R.layout.fragment_home) {

    override var logTag = "HomeFragment"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        showFinancialDialog()
    }

    private fun initView() {
        binding.ivNest.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.ic_img_bird))
        binding.tvNest.text = "#우리의 숲"
        binding.tvNestTitle.text = "우리의 숲"

        binding.ivItemNest.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.img_add_circle_outline_white))
        binding.tvItemNestTitle.text = "취미모으기 \n" + "등록하러가기"

        binding.tvSaveMission.text = "하루 N만원씩"
        binding.tvCheerCount.text = "0"

        binding.tvPrice.text = "0원"
        binding.tvPriceDetail.text = "목표금액을 정할 수 있어요!"

        binding.btnRegisterSaveMoney.setOnClickListener {
            navigate(R.id.action_homeFragment_to_nestServiceGuideFragment)
        }
    }

    private fun showFinancialDialog() {
        fragmentManager?.let {
            FinancialTipFragmentDialog().show(it, FinancialTipFragmentDialog.TAG)
        }
    }
}