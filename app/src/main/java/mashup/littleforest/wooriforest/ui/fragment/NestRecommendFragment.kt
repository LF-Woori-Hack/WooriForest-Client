package mashup.littleforest.wooriforest.ui.fragment

import android.os.Bundle
import android.view.View
import com.tistory.blackjinbase.ext.toast
import com.tistory.blackjinbase.util.Dlog
import mashup.littleforest.wooriforest.R
import mashup.littleforest.wooriforest.base.WFFragment
import mashup.littleforest.wooriforest.data.model.response.LinkTransItem
import mashup.littleforest.wooriforest.databinding.FragmentNestRecommendBinding
import mashup.littleforest.wooriforest.ui.adapter.HobbyNestAdapter
import mashup.littleforest.wooriforest.utils.PrefUtil

class NestRecommendFragment :
    WFFragment<FragmentNestRecommendBinding>(R.layout.fragment_nest_recommend) {

    override var logTag = "NestRecommendFragment"

    private val hobbyNestAdapter by lazy { HobbyNestAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            val id = NestRecommendFragmentArgs.fromBundle(it).id
            val title = NestRecommendFragmentArgs.fromBundle(it).title

            initTitle(title)
            initButton(title)

            binding.rvHobbyNest.adapter = hobbyNestAdapter

            val sample = mutableListOf(
                LinkTransItem(
                    id = "1",
                    title = "nest1",
                    content = "nest content 1",
                    memberCount = 10,
                    isShowMemberCount = true
                ),
                LinkTransItem(
                    id = "2",
                    title = "nest2",
                    content = "nest content 2",
                    memberCount = 20,
                    isShowMemberCount = true
                )
            )
            hobbyNestAdapter.replaceAll(sample)

            return@let

            fetch {
                val result = wooriApi.nestDetail(id)
                hobbyNestAdapter.replaceAll(result)
            }
        }
    }

    private fun initTitle(title: String) {
        val name = PrefUtil.get(PrefUtil.PREF_USER_NAME, "")

        binding.tvTitle.text = String.format(resources.getString(R.string.format_recommend_nest_title), name, title)
        binding.tvContent.text = title
    }

    private fun initButton(title: String) {
        binding.btnJoinNest.setOnClickListener {

            val item = hobbyNestAdapter.getSelectedItem()
            if (item == null) {
                toast("취미 둥지 1개를 선택해 주세요.")
                return@setOnClickListener
            }

            val direction = NestRecommendFragmentDirections.actionNestRecommendFragmentToNestCompleteFragment(title, item)

            navigate(direction)

            return@setOnClickListener

            fetch {
                val result = wooriApi.join(id)
                Dlog.d("result : $result")
                navigate(direction)
            }
        }
    }
}