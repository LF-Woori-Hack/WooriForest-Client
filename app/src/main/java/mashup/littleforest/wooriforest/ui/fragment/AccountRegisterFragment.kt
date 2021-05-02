package mashup.littleforest.wooriforest.ui.fragment

import android.os.Bundle
import android.view.View
import com.tistory.blackjinbase.util.Dlog
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
            /*if (TextUtils.isEmpty(agency)) {
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
            }*/

            connectAccount(agency, phoneNumber, name, registerNumber1, registerNumber2)
        }
    }

    private fun connectAccount(carrier: String, phoneNumber: String, name: String, birthday: String, rrno: String) {
        PrefUtil.put(PrefUtil.PREF_USER_NAME, name)

        val sample = mutableListOf(
            LinkTransItem(
                id = "1",
                title = "title1",
                content = "content1",
            ),
            LinkTransItem(
                id = "2",
                title = "title2",
                content = "content2",
            ),
            LinkTransItem(
                id = "3",
                title = "title3",
                content = "content3",
            )
        ).toTypedArray()

        val direction = AccountRegisterFragmentDirections.actionAccountRegisterFragmentToHobbyRecommendFragment(sample)
        navigate(direction)
        return

        val request = LinkTransRequest(
            carrier = carrier,
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