package mashup.littleforest.wooriforest.ui.fragment

import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.tistory.blackjinbase.ext.toast
import com.tistory.blackjinbase.util.Dlog
import mashup.littleforest.wooriforest.R
import mashup.littleforest.wooriforest.base.WFFragment
import mashup.littleforest.wooriforest.data.model.request.LinkTransRequest
import mashup.littleforest.wooriforest.databinding.FragmentAccountRegisterBinding
import mashup.littleforest.wooriforest.utils.PrefUtil

class AccountRegisterFragment
    : WFFragment<FragmentAccountRegisterBinding>(R.layout.fragment_account_register) {

    override var logTag = "AccountRegisterFragment"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initSpinner()
        initButton()
    }

    private var agency = 1

    private fun initSpinner() {
        //1-SKT, 2-KT, 3-LGU+, 5-SKT(알뜰폰), 6-KT(알뜰폰), 7-LGU+(알뜰폰)

        val items = arrayOf("SKT", "KT", "LGU+", "SKT(알뜰폰)", "KT(알뜰폰)", "LGU+(알뜰폰)")
        val myAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, items)

        binding.spinner.adapter = myAdapter
        binding.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                //아이템이 클릭 되면 맨 위부터 position 0번부터 순서대로 동작하게 됩니다.
                agency = when (position) {
                    0 -> 1
                    1 -> 2
                    2 -> 3
                    3 -> 5
                    4 -> 6
                    5 -> 7
                    else -> 1
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {

            }
        }
    }

    private fun initButton() {
        binding.btnOk.setOnClickListener {
            val phoneNumber = binding.etPhoneNumber.text.toString()
            val name = binding.etName.text.toString()
            val registerNumber1 = binding.etRegister1.text.toString()
            val registerNumber2 = binding.etRegister2.text.toString()

            Dlog.d("$agency / $phoneNumber / $name / $registerNumber1-$registerNumber2")

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

            connectAccount(phoneNumber, name, registerNumber1, registerNumber2)
        }
    }

    private fun connectAccount(phoneNumber: String, name: String, birthday: String, rrno: String) {
        PrefUtil.put(PrefUtil.PREF_USER_NAME, name)

        /*fetchTest {
            val sample = mutableListOf(
                LinkTransItem(
                    id = "1",
                    title = "#편의점 덕후",
                    content = "다른 소비보다도 편의점 소비 횟수가 많으시네요!",
                ),
                LinkTransItem(
                    id = "2",
                    title = "#신발 덕후",
                    content = "다른 소비보다도 신발 소비 횟수가 많으시네요!",
                ),
                LinkTransItem(
                    id = "3",
                    title = "#자전거 덕후",
                    content = "다른 소비보다도 자전거 소비 횟수가 많으시네요!",
                )
            ).toTypedArray()

            val direction = AccountRegisterFragmentDirections.actionAccountRegisterFragmentToHobbyRecommendFragment(sample)
            navigate(direction)
        }

        return*/

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
            val direction = AccountRegisterFragmentDirections.actionAccountRegisterFragmentToHobbyRecommendFragment(response.toTypedArray())
            navigate(direction)
        }
    }
}