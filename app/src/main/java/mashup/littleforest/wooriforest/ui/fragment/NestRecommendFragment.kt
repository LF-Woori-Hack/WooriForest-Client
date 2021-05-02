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

            fetchTest {
                val sample = mutableListOf(
                    LinkTransItem(
                        id = "1",
                        title = "꾸안꾸를 위한 둥지",
                        content = "꾸안꾸의 시작은 신발에서 부터입니다~! 같이 정보 공유하며 패션을 가꿔보아요~! ><️",
                        memberCount = 231,
                        isShowMemberCount = true
                    ),
                    LinkTransItem(
                        id = "2",
                        title = "조던 한번 사보자 둥지",
                        content = "솔직히 살면서 조던 하나 정도는 있어야되지 않겠어?",
                        memberCount = 19,
                        isShowMemberCount = true
                    ),
                    LinkTransItem(
                        id = "3",
                        title = "캔버스의 빠지다 둥지",
                        content = "캔버스로도 얼마든지 꾸밀 수 있다! 다 모여라",
                        memberCount = 1097,
                        isShowMemberCount = true
                    ),
                    LinkTransItem(
                        id = "4",
                        title = "Just Do It! 런닝화 둥지",
                        content = "런닝의 시작은 나이키 이지 말입니다?",
                        memberCount = 97,
                        isShowMemberCount = true
                    )
                )
                hobbyNestAdapter.replaceAll(sample)
            }
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

            fetchTest {
                val direction = NestRecommendFragmentDirections.actionNestRecommendFragmentToNestCompleteFragment(title, item)
                navigate(direction)
            }
            return@setOnClickListener

            fetch {
                val result = wooriApi.join(id)
                Dlog.d("result : $result")
                val direction = NestRecommendFragmentDirections.actionNestRecommendFragmentToNestCompleteFragment(title, item)
                navigate(direction)
            }
        }
    }
}