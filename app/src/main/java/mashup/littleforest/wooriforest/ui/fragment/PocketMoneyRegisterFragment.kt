package mashup.littleforest.wooriforest.ui.fragment

import android.os.Bundle
import android.view.View
import mashup.littleforest.wooriforest.R
import mashup.littleforest.wooriforest.base.WFFragment
import mashup.littleforest.wooriforest.databinding.FragmentPocketMoneyRegisterBinding

class PocketMoneyRegisterFragment
    : WFFragment<FragmentPocketMoneyRegisterBinding>(R.layout.fragment_pocket_money_register) {

    override var logTag = "PocketMoneyRegisterFragment"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.etHobbyItemGoal.setText("상품을 선택해주세요")
        binding.etHobbyItemGoal.requestFocus()

        binding.inputHobbyItemGoal.isEnabled = true
        binding.inputPocketMoneyGoal.isEnabled = false
        binding.inputPromise.isEnabled = false

        initButton()
    }

    private fun initButton() {
        binding.btnSearchShop.setOnClickListener {
            navigate(R.id.action_pocketMoneyRegisterFragment_to_naverShopListFragment)
        }

        binding.btnRegisterPocketMoney.setOnClickListener {
            navigate(R.id.action_global_homeFragment)
        }
    }
}