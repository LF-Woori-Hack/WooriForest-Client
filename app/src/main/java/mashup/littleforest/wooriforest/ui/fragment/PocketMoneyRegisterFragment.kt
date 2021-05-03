package mashup.littleforest.wooriforest.ui.fragment

import android.os.Build
import android.os.Bundle
import android.text.Html
import android.text.TextUtils
import android.view.View
import com.tistory.blackjinbase.ext.toast
import com.tistory.blackjinbase.util.Dlog
import mashup.littleforest.wooriforest.R
import mashup.littleforest.wooriforest.base.WFFragment
import mashup.littleforest.wooriforest.data.model.request.JoinRequest
import mashup.littleforest.wooriforest.databinding.FragmentPocketMoneyRegisterBinding
import mashup.littleforest.wooriforest.ui.model.ItemShop
import mashup.littleforest.wooriforest.utils.PrefUtil

class PocketMoneyRegisterFragment
    : WFFragment<FragmentPocketMoneyRegisterBinding>(R.layout.fragment_pocket_money_register) {

    override var logTag = "PocketMoneyRegisterFragment"

    private var itemShop: ItemShop? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        initButton()

        arguments?.let {
            val item = PocketMoneyRegisterFragmentArgs.fromBundle(it).item ?: return

            itemShop = item
            setView(item)
        }
    }

    private fun setView(item: ItemShop) {
        val htmlTitle = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Html.fromHtml(item.title, Html.FROM_HTML_MODE_LEGACY)
        } else {
            Html.fromHtml(item.title)
        }

        binding.etHobbyItemGoal.setText(htmlTitle)
        binding.etPocketMoneyGoal.setText(item.price.toString())
        binding.etPromise.requestFocus()

        binding.inputHobbyItemGoal.isEnabled = true
        binding.inputPocketMoneyGoal.isEnabled = true
        binding.inputPromise.isEnabled = true
    }

    private fun initView() {
        binding.etHobbyItemGoal.setText("상품을 선택해주세요")
        binding.etHobbyItemGoal.requestFocus()

        binding.inputHobbyItemGoal.isEnabled = true
        binding.inputPocketMoneyGoal.isEnabled = false
        binding.inputPromise.isEnabled = false
    }

    private fun initButton() {
        binding.btnSearchShop.setOnClickListener {
            goToSearchShop()
        }

        binding.btnRegisterPocketMoney.setOnClickListener {
            val goal = binding.etHobbyItemGoal.text.toString()
            if (goal == "상품을 선택해주세요" || TextUtils.isEmpty(goal)) {
                goToSearchShop()
                return@setOnClickListener
            }

            val money = binding.etPocketMoneyGoal.text.toString()
            if (TextUtils.isEmpty(money)) {
                toast("목표 금액을 입력해주세요.")
                return@setOnClickListener
            }

            val promise = binding.etPromise.text.toString()
            if (TextUtils.isEmpty(promise)) {
                toast("다짐을 입력해주세요.")
                return@setOnClickListener
            }

            PrefUtil.put(PrefUtil.PREF_SHOP_ITEM_IMAGE, itemShop?.image ?: "")

            /*fetchTest {
                 CashData.nestItem = NestResponse(
                    query = goal,
                    goalAmount = money.toLong(),
                    comment = promise,
                    currentAmount = 0L,
                    monthlyPayment = 10000L,
                    cheeringCount = 121,
                    image = itemShop?.image ?: ""
                )

                val direction = HomeFragmentDirections.actionGlobalHomeFragment(true)
                navigate(direction)
            }
            return@setOnClickListener*/

            val request = JoinRequest(
                query = goal,
                goalAmount = money.toLong(),
                comment = promise
            )

            fetch {
                val result = wooriApi.join(request)
                Dlog.d("result : $result")

                val direction = HomeFragmentDirections.actionGlobalHomeFragment(true)
                navigate(direction)
            }
        }
    }

    private fun goToSearchShop() {
        navigate(R.id.action_pocketMoneyRegisterFragment_to_naverShopListFragment)
    }
}