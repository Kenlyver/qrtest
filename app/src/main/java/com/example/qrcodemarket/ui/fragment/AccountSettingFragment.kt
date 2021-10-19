package com.example.qrcodemarket.ui.fragment

import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.qrcodemarket.R
import com.example.qrcodemarket.data.model.UpdateUser
import com.example.qrcodemarket.data.network.QRApi
import com.example.qrcodemarket.ui.auth.AppPreferences
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_account_setting.*
import kotlinx.android.synthetic.main.fragment_account_setting.edtDateOfBirth
import kotlinx.android.synthetic.main.fragment_account_setting.edtFullName
import kotlinx.android.synthetic.main.fragment_account_setting.edtNumberPhone
import kotlinx.android.synthetic.main.fragment_account_setting.view.*
import java.text.SimpleDateFormat
import java.util.*


class AccountSettingFragment : Fragment() {
    companion object{
        fun newInstance():AccountSettingFragment{
            return AccountSettingFragment()
        }
    }
    var citizenId:String?= null
    var fullName:String? = null
    var dateOfBirth:String? = null
    var address:String? = null
    var numberPhone:String? = null
    var loginName:String? = null
    var disposable : Disposable? = null
    private var mDatePickerDialog: DatePickerDialog? = null

    val insertApi by lazy {
        QRApi.create()
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val mView = inflater.inflate(R.layout.fragment_account_setting, container, false)
        setDateTimeField()
        mView.edtDateOfBirth.setOnTouchListener(object : View.OnTouchListener {
            override fun onTouch(v: View?, event: MotionEvent?): Boolean {
                mDatePickerDialog!!.show()
                return false
            }
        })

        val imageDistance: ImageView =mView.findViewById(R.id.imgDistance)

        Glide.with(this)
            .load(R.drawable.distance)
            .into(imageDistance);
        getInfoUser()
        mView.btnSaveChange.setOnClickListener {
            updateUser()
            Toast.makeText(context, "Update success", Toast.LENGTH_SHORT).show()
        }
        mView.imgBackArrow.setOnClickListener {
            findNavController().popBackStack()
        }

        mView.btnChangePassword.setOnClickListener {
            findNavController().navigate(R.id.action_accountSettingFragment_to_changePasswordFragment)
        }
        return mView.rootView
    }

    private fun getInfoUser(){
        loginName = AppPreferences.username
        disposable = insertApi.getInfoUser(loginName!!)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { result ->
                    fullName = result.data.fullName
                    dateOfBirth = result.data.dateOfBirth
                    address = result.data.address
                    numberPhone = result.data.numberPhone
                    edtFullName.setText(fullName)
                    edtDateOfBirth.setText(dateOfBirth)
                    edtAddress.setText(address)
                    edtNumberPhone.setText(numberPhone)
                    Log.i("abc", "abc: " + result.data.toString())
//                    Toast.makeText(context!!, "${result.message}", Toast.LENGTH_SHORT).show()
                },
                { error ->
//                    Toast.makeText(context!!, "fail get", Toast.LENGTH_SHORT).show()
                    Log.i("abc", "abc: " + error.localizedMessage + error.message + error)
                    Toast.makeText(context!!, error.message, Toast.LENGTH_SHORT).show()
                }
            )
    }


    private fun updateUser(){
        citizenId = AppPreferences.citizenId
        val updateData: UpdateUser.Data
        fullName = edtFullName.text.toString()
        dateOfBirth = edtDateOfBirth.text.toString()
        address = edtAddress.text.toString()
        numberPhone = edtNumberPhone.text.toString()
        updateData= UpdateUser.Data(fullName!!,dateOfBirth!!,address!!,numberPhone!!)
        val intCitizenId = citizenId!!.toInt()
        disposable = insertApi.updateUser(intCitizenId,updateData)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { result ->
                    Log.i("abc", "abc: " + result.message)
//                    Toast.makeText(context!!, "${result.message}", Toast.LENGTH_SHORT).show()
                },
                { error ->
//                    Toast.makeText(context!!, "fail get", Toast.LENGTH_SHORT).show()
                    Log.i("abc", "abc: " + error.localizedMessage + error.message + error)
                    Toast.makeText(context!!, error.message, Toast.LENGTH_SHORT).show()
                }
            )
    }

    private fun setDateTimeField() {
        val newCalendar: Calendar = Calendar.getInstance()
        mDatePickerDialog = context?.let {
            DatePickerDialog(
                it, R.style.DialogTheme,
                { view, year, monthOfYear, dayOfMonth ->
                    val newDate: Calendar = Calendar.getInstance()
                    newDate.set(year, monthOfYear, dayOfMonth)
                    val sd = SimpleDateFormat("dd-MM-yyyy")
                    val startDate: Date = newDate.getTime()
                    val fdate: String = sd.format(startDate)
                    edtDateOfBirth.setText(fdate)
                }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH)
            )
        }
        mDatePickerDialog!!.getDatePicker().setMaxDate(System.currentTimeMillis())
    }

}