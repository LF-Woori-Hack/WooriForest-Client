package mashup.littleforest.wooriforest.ui.fragment

import android.os.Bundle
import android.view.View
import mashup.littleforest.wooriforest.R
import mashup.littleforest.wooriforest.base.WFFragment
import mashup.littleforest.wooriforest.databinding.FragmentNestCompleteBinding

class NestCompleteFragment :
    WFFragment<FragmentNestCompleteBinding>(R.layout.fragment_nest_complete) {

    override var logTag = "NestCompleteFragment"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initTitle()
        initButton()
    }

    private fun initTitle() {
        binding.tvTitle.text =
            String.format(resources.getString(R.string.format_complete_nest_title), "정아리")
        binding.tvContent.text =
            String.format(resources.getString(R.string.format_complete_nest_content), "정아리")
    }

    private fun initButton() {
        binding.btnOk.setOnClickListener {
            navigate(R.id.action_nestCompleteFragment_to_homeFragment)
        }
    }
}