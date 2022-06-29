package com.example.news_app_android.Fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.news_app_android.R
import com.example.news_app_android.databinding.FragmentPaymentBinding


class PaymentFragment : Fragment() {
    @SuppressLint("UseCompatLoadingForColorStateLists")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var bindig = FragmentPaymentBinding.inflate(layoutInflater)

        var index = MutableLiveData<Int>()
        index.value = 0


        // run the first step of fragments :..
        bindig.btnMyCard.isSelected = true


        parentFragmentManager.beginTransaction().add(R.id.FragmentContaier, MyCardFragment())
            .commit()


        bindig.btnMyCard.setOnClickListener {
            bindig.btnMyCard.isSelected = true
            parentFragmentManager.beginTransaction()
                .replace(R.id.FragmentContaier, MyCardFragment()).commit()

        }
        bindig.ptnDelivaery.setOnClickListener {
            bindig.ptnDelivaery.isSelected = true
            parentFragmentManager.beginTransaction()
                .replace(R.id.FragmentContaier, DeleveryFragment()).commit()

        }

        bindig.btnPayment.setOnClickListener {
            if (bindig.ptnDelivaery.isSelected) {
                bindig.btnPayment.isSelected = true
                parentFragmentManager.beginTransaction()
                    .replace(R.id.FragmentContaier, PayFragment()).commit()
            }
        }
        bindig.btnOrder.setOnClickListener {
            if (bindig.btnPayment.isSelected) {
                bindig.btnText.text = "Finish"
                bindig.btnOrder.isSelected = true
                requireActivity().supportFragmentManager.beginTransaction()
                    .replace(R.id.FragmentContaier, OrderFragment()).commit()

            }

        }

        bindig.mainBtnToNext.setOnClickListener {
            if (index.value!! < 4) {
                index.postValue(index.value!! + 1)

            }

        }



        index.observe(requireActivity(), object : Observer<Int> {
            override fun onChanged(t: Int?) {
                if (t == 1) {
                    bindig.ptnDelivaery.isSelected = true

                    parentFragmentManager.beginTransaction()
                        .replace(R.id.FragmentContaier, DeleveryFragment()).commit()
                }
                if (t == 2) {
                    bindig.btnPayment.isSelected = true

                    parentFragmentManager.beginTransaction()
                        .replace(R.id.FragmentContaier, PayFragment()).commit()
                }
                if (t == 3) {
                    bindig.btnOrder.isSelected = true
                    bindig.btnText.text = "Finish"
                    requireActivity().supportFragmentManager.beginTransaction()
                        .replace(R.id.FragmentContaier, OrderFragment()).commit()

                }
            }
        })
        return bindig.root
    }

}