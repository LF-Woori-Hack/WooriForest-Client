package mashup.littleforest.wooriforest.ui.fragment

import android.os.Bundle
import android.view.View
import mashup.littleforest.wooriforest.R
import mashup.littleforest.wooriforest.base.WFFragment
import mashup.littleforest.wooriforest.databinding.FragmentNestRecommendBinding
import mashup.littleforest.wooriforest.ui.adapter.HobbyNestAdapter

class NestRecommendFragment :
    WFFragment<FragmentNestRecommendBinding>(R.layout.fragment_nest_recommend) {

    override var logTag = "NestRecommendFragment"

    private val hobbyNestAdapter by lazy { HobbyNestAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initTitle()
        initButton()

        binding.rvHobbyNest.adapter = hobbyNestAdapter
        hobbyNestAdapter.replaceAll(mutableListOf("test1", "test2", "test3"))
    }

    private fun initTitle() {
        binding.tvTitle.text =
            String.format(resources.getString(R.string.format_recommend_nest_title), "정아리", "피규어")
        binding.tvContent.text = "#피규어 덕후"
    }

    private fun initButton() {
        binding.btnJoinNest.setOnClickListener {
            navigate(R.id.action_nestRecommendFragment_to_nestCompleteFragment)
        }
    }
}