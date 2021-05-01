package mashup.littleforest.wooriforest.ui.fragment

import android.os.Bundle
import android.view.View
import com.tistory.blackjinbase.ext.toast
import mashup.littleforest.wooriforest.R
import mashup.littleforest.wooriforest.base.WFFragment
import mashup.littleforest.wooriforest.databinding.FragmentHobbyRecommendBinding
import mashup.littleforest.wooriforest.ui.adapter.HobbyNestAdapter

class HobbyRecommendFragment :
    WFFragment<FragmentHobbyRecommendBinding>(R.layout.fragment_hobby_recommend) {

    override var logTag = "HobbyRecommendFragment"

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
            String.format(resources.getString(R.string.format_recommend_hobby_title), "정아리")
        binding.tvContent.text = String.format(
            resources.getString(R.string.format_recommend_hobby_content),
            "정아리",
            "#피규어 #문구류"
        )
    }

    private fun initButton() {
        binding.btnGoToJoinHobby.setOnClickListener {
            navigate(R.id.action_hobbyRecommendFragment_to_nestRecommendFragment)
        }

        binding.btnShareKakao.setOnClickListener {
            toast("카카오 공유하기")
        }

        binding.btnExtractAgain.setOnClickListener {
            toast("추출 다시하기")
        }
    }
}