package mashup.littleforest.wooriforest.ui.fragment

import android.os.Bundle
import android.view.View
import mashup.littleforest.wooriforest.R
import mashup.littleforest.wooriforest.base.WFFragment
import mashup.littleforest.wooriforest.databinding.FragmentNestServiceGuideBinding

class NestServiceGuideFragment
    : WFFragment<FragmentNestServiceGuideBinding>(R.layout.fragment_nest_service_guide) {

    override var logTag = "NestServiceGuideFragment"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initButton()
    }

    private fun initButton() {
        binding.btnOk.setOnClickListener {
            navigate(R.id.action_nestServiceGuideFragment_to_webViewFragment)
        }
    }
}