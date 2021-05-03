package mashup.littleforest.wooriforest.ui.fragment

import android.os.Bundle
import android.view.View
import mashup.littleforest.wooriforest.R
import mashup.littleforest.wooriforest.base.WFFragment
import mashup.littleforest.wooriforest.data.CashData
import mashup.littleforest.wooriforest.databinding.FragmentNestCompleteBinding
import mashup.littleforest.wooriforest.utils.PrefUtil

class NestCompleteFragment :
    WFFragment<FragmentNestCompleteBinding>(R.layout.fragment_nest_complete) {

    override var logTag = "NestCompleteFragment"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            val title = NestCompleteFragmentArgs.fromBundle(it).title
            val item = NestCompleteFragmentArgs.fromBundle(it).item

            CashData.linkTransItem = item

            initTitle()
            initButton()

            binding.model = item.copy(isShowSelectedBox = false)
        }
    }

    private fun initTitle() {
        val name = PrefUtil.get(PrefUtil.PREF_USER_NAME, "")

        binding.tvTitle.text = String.format(resources.getString(R.string.format_complete_nest_title), name)
        binding.tvContent.text = String.format(resources.getString(R.string.format_complete_nest_content), name)
    }

    private fun initButton() {
        binding.btnOk.setOnClickListener {
            PrefUtil.put(PrefUtil.PREF_USER_MAIN, true)
            navigate(R.id.action_global_homeFragment)
        }
    }
}