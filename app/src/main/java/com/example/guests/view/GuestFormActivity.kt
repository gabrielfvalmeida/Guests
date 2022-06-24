package com.example.guests.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.guests.model.GuestModel
import com.example.guests.R
import com.example.guests.constants.DataBaseConstants
import com.example.guests.databinding.ActivityGuestFormBinding
import com.example.guests.repository.GuestRepository
import com.example.guests.viewmodel.GuestFormViewModel

class GuestFormActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityGuestFormBinding
    private lateinit var viewModel: GuestFormViewModel

    private var guestId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityGuestFormBinding.inflate(layoutInflater)

        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(GuestFormViewModel::class.java)

        binding.buttonSave.setOnClickListener(this)
        binding.radioPresent.isChecked = true

        observe()

        loadData()
    }

    override fun onClick(v: View) {
        if (v.id == R.id.button_save) {
            val name = binding.editName.text.toString()
            val presence = binding.radioPresent.isChecked

            val model = GuestModel(guestId, name, presence)


            viewModel.save(model)

        }
    }

    private fun observe() {
        viewModel.guest.observe(this, Observer {
            binding.editName.setText(it.name)
            if (it.presence) {
                binding.radioPresent.isChecked = true
            } else {
                binding.radioAbsent.isChecked = true
            }
        })


        viewModel.saveGuest.observe(this, Observer {
            if (it){
                if (guestId == 0){
                    Toast.makeText(applicationContext,"Convidado adicionado", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(applicationContext,"Convidado atualizado", Toast.LENGTH_SHORT).show()
                }
                finish()
            } else {
                Toast.makeText(applicationContext,"Falha", Toast.LENGTH_SHORT).show()
            }
        })

    }

    private fun loadData() {
        val bundle = intent.extras
        if (bundle != null) {
            guestId = bundle.getInt(DataBaseConstants.GUEST.ID)
            viewModel.get(guestId)
        }
    }
}