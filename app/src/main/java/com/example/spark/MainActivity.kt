package com.example.spark
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FieldPath
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.razorpay.Checkout
import com.razorpay.PaymentResultListener
import org.json.JSONObject

class MainActivity : AppCompatActivity(), PaymentResultListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        startPayment(intent.getIntExtra("Amount", 10))
    }

    private fun startPayment(amount: Int) {

        val db = Firebase.firestore
//        val result = db.collection("User").whereEqualTo("email", "bhardwajshubham843@gmail.com")

//        var firstname = "";
//        var lastname = "";
//        var email = "";
//        var phone = "";
        var firebaseAuth = FirebaseAuth.getInstance()
        var currentUser = firebaseAuth.getCurrentUser()
        Log.d("USERTEST","$currentUser is currentuser")
//        var uid  = currentUser?.getUid()
        var email = currentUser?.email
//        Log.d("USERTEST","$uid is uid")
//        Log.d("USERTEST","$email is email")
        var phone = "7819372829"



        val name = "John Doe"

        val checkout = Checkout()
        checkout.setKeyID("rzp_test_TbZG9ThnEnyf7w")
        try {
            val options = JSONObject()
            options.put("name", name)
            options.put("description", "Order Value")

            //You can omit the image option to fetch the image from dashboard
            //   options.put("image", "https://s3.amazonaws.com/rzp-mobile/images/rzp.png")

            options.put("theme.color", "#3399cc");
            options.put("currency", "INR");

            /** Generated from backend **/
            //      options.put("order_id", "order_DBJOWzybf0sJbb");

            /** Pass in paise in INR  ( Example  Rs 5 = 500 paise ) **/
            options.put("amount", "${(amount.toInt() * 100)}")//pass amount in currency subunits


            options.put("prefill.email", email)
            options.put("prefill.contact", phone)

            checkout.open(this, options)

        } catch (e: Exception) {
            Toast.makeText(this, "Error in payment: " + e.message, Toast.LENGTH_LONG)
                .show()
            e.printStackTrace()
        }

//
//        db.collection("User")
//            .whereEqualTo("email", email_forquery)
//            .get()
//            .addOnSuccessListener { documents ->
//                for (document in documents) {
//
//
////                    var firstname = document.data["firstname"].toString()
////                    var lastname =  document.data["lastname"].toString()
//                    var email = document.data["email"].toString()
////                    var phone = document.data["phone"].toString()
//
//                    if(firstname=="")
//                    {
//                        firstname="John"
//                        lastname="Doe"
//                    }
//                    if(phone=="")
//                    {
//                        phone = "7284193829"
//                    }
//                    Log.d("TAG","$firstname is firstname + $lastname")
////                    for (i in document){
//                    Log.d("TAG", "${document.id} => ${document.data}")
////                    }
//
//                    val name = "$firstname $lastname"
//
//                    val checkout = Checkout()
//                    checkout.setKeyID("rzp_test_TbZG9ThnEnyf7w")
//                    try {
//                        val options = JSONObject()
//                        options.put("name", name)
//                        options.put("description", "Order Value")
//
//                        //You can omit the image option to fetch the image from dashboard
//                        //   options.put("image", "https://s3.amazonaws.com/rzp-mobile/images/rzp.png")
//
//                        options.put("theme.color", "#3399cc");
//                        options.put("currency", "INR");
//
//                        /** Generated from backend **/
//                        //      options.put("order_id", "order_DBJOWzybf0sJbb");
//
//                        /** Pass in paise in INR  ( Example  Rs 5 = 500 paise ) **/
//                        options.put("amount", "${(amount.toInt() * 100)}")//pass amount in currency subunits
//
//
//                        options.put("prefill.email", email)
//                        options.put("prefill.contact", phone)
//
//                        checkout.open(this, options)
//
//                    } catch (e: Exception) {
//                        Toast.makeText(this, "Error in payment: " + e.message, Toast.LENGTH_LONG)
//                            .show()
//                        e.printStackTrace()
//                    }
//
//                }
//            }
//            .addOnFailureListener { exception ->
//                Log.w("TAG", "Error getting documents: ", exception)
//            }
//


    }


    override fun onPaymentSuccess(p0: String?) {
        Toast.makeText(this, "Payment Successful", Toast.LENGTH_SHORT).show()
    }

    override fun onPaymentError(p0: Int, p1: String?) {
        val TAG="TAG"
        Log.d(TAG, "onPaymentError: ${p0}")
        Log.d(TAG, "onPaymentError: ${p1}")


        Toast.makeText(this, "Payment Not Successful", Toast.LENGTH_SHORT).show()
    }
}