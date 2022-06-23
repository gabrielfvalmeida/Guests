package com.example.guests.view.viewholder


import android.app.AlertDialog
import android.content.DialogInterface
import android.content.DialogInterface.OnClickListener
import androidx.recyclerview.widget.RecyclerView
import com.example.guests.databinding.RowGuestBinding
import com.example.guests.model.GuestModel
import com.example.guests.view.listener.OnGuestListener

class GuestViewHolder(private val bind: RowGuestBinding,private val listener: OnGuestListener) : RecyclerView.ViewHolder(bind.root) {

    fun bind(guest: GuestModel){
        bind.textName.text = guest.name

        bind.textName.setOnClickListener{
            listener.onClick(guest.id)
        }

        bind.textName.setOnLongClickListener {
            AlertDialog.Builder(itemView.context)
                .setTitle("Remoção de convidado")
                .setMessage("Tem certeza?")
                .setPositiveButton("Sim") { dialog, which -> listener.onDelete(guest.id) }
                .setNegativeButton("Não", null)
                .create()
                .show()

            true }
    }



}