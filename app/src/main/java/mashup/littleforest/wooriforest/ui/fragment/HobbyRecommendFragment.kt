package mashup.littleforest.wooriforest.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import com.tistory.blackjinbase.ext.toast
import mashup.littleforest.wooriforest.R
import mashup.littleforest.wooriforest.base.WFFragment
import mashup.littleforest.wooriforest.databinding.FragmentHobbyRecommendBinding
import mashup.littleforest.wooriforest.ui.adapter.HobbyNestAdapter
import mashup.littleforest.wooriforest.utils.PrefUtil


class HobbyRecommendFragment :
    WFFragment<FragmentHobbyRecommendBinding>(R.layout.fragment_hobby_recommend) {

    override var logTag = "HobbyRecommendFragment"

    private val hobbyNestAdapter by lazy { HobbyNestAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            val items = HobbyRecommendFragmentArgs.fromBundle(it).items
            val hobbies = StringBuilder()

            items.forEach { item ->
                hobbies.append(item.title)
                hobbies.append(" ")
            }

            initTitle(hobbies.toString())
            initButton()

            binding.rvHobbyNest.adapter = hobbyNestAdapter
            hobbyNestAdapter.replaceAll(items.toList())
        }
    }

    private fun initTitle(hobbies: String) {
        val name = PrefUtil.get(PrefUtil.PREF_USER_NAME, "")

        binding.tvTitle.text = String.format(resources.getString(R.string.format_recommend_hobby_title), name)
        binding.tvContent.text = String.format(resources.getString(R.string.format_recommend_hobby_content), name, hobbies)
    }

    private fun initButton() {
        binding.btnGoToJoinHobby.setOnClickListener {
            val id = hobbyNestAdapter.getSelectedItemId()
            val title = hobbyNestAdapter.getSelectedItemTitle()

            if (TextUtils.isEmpty(id)) {
                toast("취미 1가지를 선택해주세요")
            } else {
                val direction = HobbyRecommendFragmentDirections.actionHobbyRecommendFragmentToNestRecommendFragment(id!!, title!!)
                navigate(direction)
            }
        }

        binding.btnShareKakao.setOnClickListener {
            val title = hobbyNestAdapter.getShareTitle()

            val sharingIntent = Intent(Intent.ACTION_SEND)
            sharingIntent.type = "text/plain"

            sharingIntent.putExtra(Intent.EXTRA_TEXT, title)
            sharingIntent.setPackage("com.kakao.talk")

            startActivity(sharingIntent)
        }

        binding.btnExtractAgain.visibility = View.GONE
        binding.btnExtractAgain.setOnClickListener {
            toast("추출 다시하기")
        }
    }
}