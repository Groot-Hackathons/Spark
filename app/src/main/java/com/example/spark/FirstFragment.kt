package com.example.spark
import android.util.Log
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.spark.databinding.FragmentFirstBinding
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.type.LatLng

val db = Firebase.firestore
/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
data class stationField(
    val location: LatLng,
    val superCharger: Boolean = false,
    val rating: Int,
    val report: String,
    val type: String,
    val comment: String

)


class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null
    private val TAG = "FirstFragment"
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonFirst.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
        binding.buttonAdd.setOnClickListener {
            getAllStations()
        }
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun addUser() {
        // Create a new user with a first and last name
        val user = hashMapOf(
            "first" to "FirstName",
            "last" to "LastName",
            "age" to 25
        )

        // Add a new document with a generated ID
        db.collection("vehicleDetails")
            .add(user)
            .addOnSuccessListener { documentReference ->
                Log.d(TAG, "DocumentSnapshot added with ID: ${documentReference.id}")
            }
            .addOnFailureListener { e ->
                Log.w(TAG, "Error adding document", e)
            }
        // [END addUser]
    }

    private fun getAllStations() {
        // [START get_all_users]
        var stations = ArrayList<MutableMap<String,Any>>()
        db.collection("chargingStation")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    Log.d(TAG, "${document.id} => ${document.data}")
                    println("Data is" + document.data)
                    stations.add(document.data)
                }
                println("Stations is$stations")
            }
            .addOnFailureListener { exception ->
                Log.w(TAG, "Error getting documents.", exception)
            }
        // [END get_all_users]
    }

    private fun vehicle () {
        val vehicleNumber = _binding?.vehicleNumberInput?.text.toString()
        val vehicleBattery = binding.vehicleBatteryinput.text
        val items = HashMap<String, Any>()
        items.put("Vehicle Number", vehicleNumber)
        items.put("Battery", vehicleBattery)
        db.collection("vehicleDetails")
            .add(items)
            .addOnSuccessListener { documentReference ->
                Log.d(TAG, "DocumentSnapshot added with ID: ${documentReference.id}")
            }
            .addOnFailureListener { e ->
                Log.w(TAG, "Error adding document", e)
            }
    }
}