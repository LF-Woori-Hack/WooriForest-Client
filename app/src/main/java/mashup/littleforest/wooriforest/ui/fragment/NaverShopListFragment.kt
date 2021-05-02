package mashup.littleforest.wooriforest.ui.fragment

import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.TextView.OnEditorActionListener
import com.tistory.blackjinbase.ext.showSoftKeyBoard
import com.tistory.blackjinbase.ext.toast
import com.tistory.blackjinbase.util.Dlog
import mashup.littleforest.wooriforest.R
import mashup.littleforest.wooriforest.base.WFFragment
import mashup.littleforest.wooriforest.data.ApiProvider
import mashup.littleforest.wooriforest.databinding.FragmentNaverShopListBinding
import mashup.littleforest.wooriforest.ui.model.ItemShop


class NaverShopListFragment
    : WFFragment<FragmentNaverShopListBinding>(R.layout.fragment_naver_shop_list) {

    override var logTag = "NaverShopListFragment"

    private val naverApi by lazy { ApiProvider.provideNaverApi() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.etHobbyItemGoal.requestFocus()
        requireActivity().showSoftKeyBoard()

        binding.etHobbyItemGoal.setOnEditorActionListener(OnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                searchItem()
                return@OnEditorActionListener true
            }
            false
        })

    }

    private fun searchItem() {
        val item = binding.etHobbyItemGoal.text.toString()
        Dlog.d("item : $item")

        if (TextUtils.isEmpty(item)) {
            toast("사고 싶은 취미 용품을 입력해 주세요.")
        } else {

            fetch {
                val shopResponse = naverApi.shop(item)
                val items = shopResponse.items?.map {
                    ItemShop(
                        image = it.image ?: "",
                        title = it.title ?: "",
                        price = it.lprice ?: 0,
                        link = it.link ?: "",
                        listener = ::itemClickListener
                    )
                }

                if (items.isNullOrEmpty()) {
                    toast("상품이 없습니다.")
                    return@fetch
                }

                binding.items = items
            }
        }
    }

    private fun itemClickListener(item: ItemShop) {
        val direction = NaverShopListFragmentDirections.actionNaverShopListFragmentToPocketMoneyRegisterFragment(item)
        navigate(direction)
    }
}