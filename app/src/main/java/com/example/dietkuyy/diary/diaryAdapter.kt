package com.example.dietkuyy.diary

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.os.Parcel
import android.os.Parcelable
import android.provider.SyncStateContract.Helpers.update
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.example.dietkuyy.R
import com.example.dietkuyy.diet.Beratmo
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_diary.*
@Suppress("UNREACHABLE_CODE", "NAME_SHADOWING", "DEPRECATION")
class diaryAdapter(val mCtx: Context, val layoutResId: Int, val list: List<Diarymo> )
    : ArrayAdapter<Diarymo>(mCtx,layoutResId,list), Parcelable {
    lateinit var ref: DatabaseReference
    private var auth: FirebaseAuth? = null

    constructor(parcel: Parcel) : this(
        TODO("mCtx"),
        parcel.readInt(),
        TODO("list")
    ) {

    }

    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layoutInflater: LayoutInflater = LayoutInflater.from(mCtx)
        val view: View = layoutInflater.inflate(layoutResId,null)

        val textNama = view.findViewById<TextView>(R.id.textNama)
        val textStatus = view.findViewById<TextView>(R.id. textStatus)
        val textisi = view.findViewById<TextView>(R.id. textisi)

        val update = view.findViewById<TextView>(R.id.update)
        val delete =view.findViewById<TextView>(R.id.delete)
        val diarymo = list[position]

        ref = FirebaseDatabase.getInstance().getReference()
        auth = FirebaseAuth.getInstance()
        textNama.text =diarymo. waktu
        textStatus.text =diarymo.kegiatan
        textisi.text = diarymo.cata
        update.setOnClickListener{
            showUpdateDialog(diarymo)
        }

        delete.setOnClickListener {
            Deleteinfo()
        }

        return view

    }
    private fun Deleteinfo() {
        val getUserID = auth?.getCurrentUser()?.getUid().toString()
        val progressDialog = ProgressDialog(context,
            R.style.Theme_MaterialComponents_Light_Dialog)
        progressDialog.isIndeterminate
        progressDialog.setMessage("Deleting...")
        progressDialog.show()
        val mydatabase = FirebaseDatabase.getInstance().getReference("USERS")
        mydatabase.child(getUserID).removeValue()
        Toast.makeText(mCtx,"Deleted!!", Toast.LENGTH_SHORT).show()
        val intent = Intent(context, viewdairyFragment::class.java)
        context.startActivity(intent)
    }

    private fun showUpdateDialog(diarymo: Diarymo) {
        val builder = AlertDialog.Builder(mCtx)

        builder.setTitle("Update")

        val inflater = LayoutInflater.from(mCtx)

        val view: View = inflater.inflate(R.layout.update, null)

        val textNama = view.findViewById<TextView>(R.id.textNama)
        val textStatus = view.findViewById<TextView>(R.id. textStatus)
        val textisi = view.findViewById<TextView>(R.id. textisi)
        val getUserID = auth?.getCurrentUser()?.getUid().toString()

        textNama.text =diarymo. waktu
        textStatus.text =diarymo.kegiatan
        textisi.text = diarymo.cata
        builder.setView(view)

        builder.setPositiveButton("Update") { dialog, which ->

            val dbUsers = FirebaseDatabase.getInstance().getReference("diary")

            val nama = textNama.text.toString().trim()

            val status =  textStatus.text .toString().trim()
            val isi =   textisi.text.toString().trim()

            if (nama.isEmpty()){
                textNama.error = "please enter name"
                textNama.requestFocus()
                return@setPositiveButton
            }

            if (status.isEmpty()){
                textStatus.error = "please enter status"
                textStatus.requestFocus()
                return@setPositiveButton
            }
            if (isi.isEmpty()){
                textisi.error = "please enter isi"
                textisi.requestFocus()
                return@setPositiveButton
            }

            val diarymo = Diarymo(nama,status,isi)

            dbUsers.child(getUserID).setValue(diarymo).addOnCompleteListener {
                Toast.makeText(mCtx,"Updated", Toast.LENGTH_SHORT).show()
            }

        }

        builder.setNegativeButton("No") { dialog, which ->

        }

        val alert = builder.create()
        alert.show()

    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(layoutResId)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<diaryAdapter> {
        override fun createFromParcel(parcel: Parcel): diaryAdapter {
            return diaryAdapter(parcel)
        }

        override fun newArray(size: Int): Array<diaryAdapter?> {
            return arrayOfNulls(size)
        }
    }
}