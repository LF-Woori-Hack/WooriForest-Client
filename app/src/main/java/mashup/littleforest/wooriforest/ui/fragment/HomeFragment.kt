package mashup.littleforest.wooriforest.ui.fragment

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.core.content.ContextCompat
import com.tistory.blackjinbase.ext.setImageUrl
import com.tistory.blackjinbase.ext.toast
import com.tistory.blackjinbase.util.Dlog
import mashup.littleforest.wooriforest.R
import mashup.littleforest.wooriforest.base.WFFragment
import mashup.littleforest.wooriforest.data.CashData
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
        arguments?.let {
            val isReload = HomeFragmentArgs.fromBundle(it).reload
            Dlog.d("isReload : $isReload")
            if (isReload) {
                loadData()
            } else {
                showFinancialDialog()
            }
        }
    }

    private fun loadData() {

        fetchTest {
            val result = CashData.nestItem ?: return@fetchTest
            Dlog.d("result : $result")

            binding.ivNest.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.ic_img_bird))
            binding.tvNest.text = CashData.linkTransItem?.title ?: "우리의 숲"
            binding.tvNestTitle.text = "용돈모으기"

            binding.ivItemNest.setImageUrl(result.image)
            binding.tvItemNestTitle.text = result.query

            binding.tvSaveMission.text = "하루 ${result.monthlyPayment!!.toInt() / 10000}만원씩"
            binding.tvCheerCount.text = "${result.cheeringCount}"

            binding.tvPrice.text = "${result.currentAmount}"

            val goal = result.goalAmount?.toInt() ?: 1
            val current = result.currentAmount?.toInt() ?: 1
            val percent = current / goal * 100 //  0 ~ 100 %

            binding.tvPriceDetail.text = "목표금액 ${result.goalAmount}원 | ${percent}% 달성 | 모으기 내역"

            binding.ivMissionStamp1.visibility = View.INVISIBLE
            binding.ivMissionStamp2.visibility = View.INVISIBLE
            binding.ivMissionStamp3.visibility = View.INVISIBLE
            binding.ivMissionStamp4.visibility = View.INVISIBLE
            binding.ivMissionStamp5.visibility = View.INVISIBLE

            when {
                percent in 21..40 -> {
                    binding.ivMissionStamp1.visibility = View.VISIBLE
                }
                percent in 41..60 -> {
                    binding.ivMissionStamp1.visibility = View.VISIBLE
                    binding.ivMissionStamp2.visibility = View.VISIBLE
                }
                percent in 61..80 -> {
                    binding.ivMissionStamp1.visibility = View.VISIBLE
                    binding.ivMissionStamp2.visibility = View.VISIBLE
                    binding.ivMissionStamp3.visibility = View.VISIBLE
                }
                percent in 81..100 -> {
                    binding.ivMissionStamp1.visibility = View.VISIBLE
                    binding.ivMissionStamp2.visibility = View.VISIBLE
                    binding.ivMissionStamp3.visibility = View.VISIBLE
                    binding.ivMissionStamp4.visibility = View.VISIBLE
                }
                percent >= 100 -> {
                    binding.ivMissionStamp1.visibility = View.VISIBLE
                    binding.ivMissionStamp2.visibility = View.VISIBLE
                    binding.ivMissionStamp3.visibility = View.VISIBLE
                    binding.ivMissionStamp4.visibility = View.VISIBLE
                    binding.ivMissionStamp5.visibility = View.VISIBLE
                }
            }

            binding.btnRegisterSaveMoney.setOnClickListener {
                //..
            }
        }

        return

        fetch {
            val result = wooriApi.getNest()
            Dlog.d("result : $result")

            binding.ivNest.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.ic_img_bird))
            binding.tvNest.text = CashData.linkTransItem?.title ?: "우리의 숲"
            binding.tvNestTitle.text = "용돈모으기"

            //binding.ivItemNest.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.img_add_circle_outline_white))
            binding.ivItemNest.setImageUrl(result.image)
            binding.tvItemNestTitle.text = result.query

            binding.tvSaveMission.text = "하루 ${result.monthlyPayment!!.toInt() / 10000}만원씩"
            binding.tvCheerCount.text = "${result.cheeringCount}"

            binding.tvPrice.text = "${result.currentAmount}"

            val goal = result.goalAmount?.toInt() ?: 1
            val current = result.currentAmount?.toInt() ?: 1
            val percent = current / goal * 100 //  0 ~ 100 %

            binding.tvPriceDetail.text = "목표금액 ${result.goalAmount}원 | ${percent}% 달성 | 모으기 내역"

            binding.ivMissionStamp1.visibility = View.INVISIBLE
            binding.ivMissionStamp2.visibility = View.INVISIBLE
            binding.ivMissionStamp3.visibility = View.INVISIBLE
            binding.ivMissionStamp4.visibility = View.INVISIBLE
            binding.ivMissionStamp5.visibility = View.INVISIBLE

            when {
                percent in 21..40 -> {
                    binding.ivMissionStamp1.visibility = View.VISIBLE
                }
                percent in 41..60 -> {
                    binding.ivMissionStamp1.visibility = View.VISIBLE
                    binding.ivMissionStamp2.visibility = View.VISIBLE
                }
                percent in 61..80 -> {
                    binding.ivMissionStamp1.visibility = View.VISIBLE
                    binding.ivMissionStamp2.visibility = View.VISIBLE
                    binding.ivMissionStamp3.visibility = View.VISIBLE
                }
                percent in 81..100 -> {
                    binding.ivMissionStamp1.visibility = View.VISIBLE
                    binding.ivMissionStamp2.visibility = View.VISIBLE
                    binding.ivMissionStamp3.visibility = View.VISIBLE
                    binding.ivMissionStamp4.visibility = View.VISIBLE
                }
                percent >= 100 -> {
                    binding.ivMissionStamp1.visibility = View.VISIBLE
                    binding.ivMissionStamp2.visibility = View.VISIBLE
                    binding.ivMissionStamp3.visibility = View.VISIBLE
                    binding.ivMissionStamp4.visibility = View.VISIBLE
                    binding.ivMissionStamp5.visibility = View.VISIBLE
                }
            }

            binding.btnRegisterSaveMoney.setOnClickListener {
                //..
            }
        }
    }

    override fun onDetach() {
        callback.remove()
        super.onDetach()
    }

    private fun initView() {
        binding.ivNest.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.ic_img_bird))
        binding.tvNest.text = "#우리의 숲"
        binding.tvNestTitle.text = "용돈모으기"

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