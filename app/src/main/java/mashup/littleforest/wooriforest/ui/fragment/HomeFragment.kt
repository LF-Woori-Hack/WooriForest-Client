package mashup.littleforest.wooriforest.ui.fragment

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.core.content.ContextCompat
import com.tistory.blackjinbase.ext.toast
import mashup.littleforest.wooriforest.R
import mashup.littleforest.wooriforest.base.WFFragment
import mashup.littleforest.wooriforest.databinding.FragmentHomeBinding
import mashup.littleforest.wooriforest.ui.dialog.FinancialTipFragmentDialog

class HomeFragment :
    WFFragment<FragmentHomeBinding>(R.layout.fragment_home) {

    override var logTag = "HomeFragment"

    companion object {

        private const val FINISH_INTERVAL_TIME = 2000
    }

    private lateinit var callback: OnBackPressedCallback

    private var backPressedTime = 0L

    override fun onAttach(context: Context) {
        super.onAttach(context)
        callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                val tempTime = System.currentTimeMillis()
                val intervalTime = tempTime - backPressedTime

                if (intervalTime in 0..FINISH_INTERVAL_TIME) {
                    activity?.finish()
                } else {
                    backPressedTime = tempTime
                    toast("'뒤로' 버튼을 한번 더 누르시면 종료됩니다.")
                }
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(this, callback)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        showFinancialDialog()
    }

    override fun onDetach() {
        callback.remove()
        super.onDetach()
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
            navigate(R.id.action_global_nestServiceGuideFragment)
        }
    }

    private fun showFinancialDialog() {
        fragmentManager?.let {
            FinancialTipFragmentDialog().show(it, FinancialTipFragmentDialog.TAG)
        }
    }
}