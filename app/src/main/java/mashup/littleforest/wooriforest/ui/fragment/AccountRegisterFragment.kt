package mashup.littleforest.wooriforest.ui.fragment

import android.os.Bundle
import android.text.TextUtils
import android.view.View
import androidx.lifecycle.lifecycleScope
import com.tistory.blackjinbase.ext.toast
import com.tistory.blackjinbase.util.Dlog
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import mashup.littleforest.wooriforest.R
import mashup.littleforest.wooriforest.base.WFFragment
import mashup.littleforest.wooriforest.data.model.request.LinkTransRequest
import mashup.littleforest.wooriforest.data.model.response.LinkTransItem
import mashup.littleforest.wooriforest.databinding.FragmentAccountRegisterBinding
import mashup.littleforest.wooriforest.utils.PrefUtil

class AccountRegisterFragment
    : WFFragment<FragmentAccountRegisterBinding>(R.layout.fragment_account_register) {

    override var logTag = "AccountRegisterFragment"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initButton()
    }

    private fun initButton() {
        binding.btnOk.setOnClickListener {

            val agency = binding.etNewsAgency.text.toString()
            val phoneNumber = binding.etPhoneNumber.text.toString()
            val name = binding.etName.text.toString()
            val registerNumber1 = binding.etRegister1.text.toString()
            val registerNumber2 = binding.etRegister2.text.toString()

            Dlog.d("$agency : $phoneNumber / $name / $registerNumber1-$registerNumber2")
            if (TextUtils.isEmpty(agency)) {
                //1-SKT, 2-KT, 3-LGU+, 5-SKT(알뜰폰), 6-KT(알뜰폰), 7-LGU+(알뜰폰)
                binding.etNewsAgency.error = "통신사를 입력해 주세요."
                return@setOnClickListener
            }
            if (TextUtils.isEmpty(phoneNumber)) {
                binding.etPhoneNumber.error = "전화번호를 입력해 주세요."
                return@setOnClickListener
            }

            if (TextUtils.isEmpty(name)) {
                binding.etName.error = "이름을 입력해 주세요."
                return@setOnClickListener
            }

            if (TextUtils.isEmpty(registerNumber1) || TextUtils.isEmpty(registerNumber2)) {
                toast("주민등록 번호를 입력해주세요.")
                return@setOnClickListener
            }

            //TODO 통신사 맞춤 버튼 작업 하기
            lifecycleScope.launch {
                showLoadingDialog()
                delay(1000)
                hideLoadingDialog()
                connectAccount(1, phoneNumber, name, registerNumber1, registerNumber2)
            }
        }
    }

    private fun connectAccount(agency: Int, phoneNumber: String, name: String, birthday: String, rrno: String) {
        PrefUtil.put(PrefUtil.PREF_USER_NAME, name)

        val sample = mutableListOf(
            LinkTransItem(
                id = "1",
                title = "#커피 덕후",
                content = "다른 소비보다도 커피 소비 횟수가 많으시네요!",
            ),
            LinkTransItem(
                id = "2",
                title = "#마블 덕후",
                content = "다른 소비보다도 마블 소비 횟수가 많으시네요!",
            ),
            LinkTransItem(
                id = "3",
                title = "#쇼핑 덕후",
                content = "다른 소비보다도 쇼핑 소비 횟수가 많으시네요!",
            )
        ).toTypedArray()

        val direction = AccountRegisterFragmentDirections.actionAccountRegisterFragmentToHobbyRecommendFragment(sample)
        navigate(direction)
        return

        val request = LinkTransRequest(
            carrier = agency,
            phone = phoneNumber,
            name = name,
            birthday = birthday,
            rrno = rrno
        )

        fetch {
            val response = wooriApi.linkTrans(request)
            Dlog.d("response : $response")
        }
    }
}