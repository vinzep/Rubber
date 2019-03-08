package com.example.rubber

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.firebase.database.*

class MainActivity : AppCompatActivity() {
    lateinit var dataBase: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        dataBase = FirebaseDatabase.getInstance().getReference("products")
    }

    fun send(view: View){
        val key = dataBase.push().key
        val product = Product("trojan", 3)
        dataBase.child(key!!).setValue(product).addOnCompleteListener {
            Toast.makeText(this, "Your coffe is on your way", Toast.LENGTH_LONG).show()
        }
    }

    fun getData(view: View){
        val dataListener = object : ValueEventListener{
            override fun onDataChange(p0: DataSnapshot) {
                val product =  p0.getValue(Product::class.java)
               // Toast.makeText(this, product.marca,Toast.LENGTH_LONG).show()
            }

            override fun onCancelled(p0: DatabaseError) {

            }
        }

        dataBase.addValueEventListener(dataListener)
    }
}
