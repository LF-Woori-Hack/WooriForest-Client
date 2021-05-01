package mashup.littleforest.wooriforest.ui.fragment

import android.os.Bundle
import android.view.View
import com.tistory.blackjinbase.ext.toast
import mashup.littleforest.wooriforest.R
import mashup.littleforest.wooriforest.base.WFFragment
import mashup.littleforest.wooriforest.databinding.FragmentPocketMoneyRegisterBinding

class PocketMoneyRegisterFragment
    : WFFragment<FragmentPocketMoneyRegisterBinding>(R.layout.fragment_pocket_money_register) {

    override var logTag = "PocketMoneyRegisterFragment"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initButton()
    }

    private fun initButton() {
        binding.btnRegisterPocketMoney.setOnClickListener {
            toast("등록")
        }
    }
}