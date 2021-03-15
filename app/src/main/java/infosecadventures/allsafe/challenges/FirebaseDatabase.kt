package infosecadventures.allsafe.challenges

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import infosecadventures.allsafe.R
import infosecadventures.allsafe.utils.SnackUtil


class FirebaseDatabase : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        val view: View = inflater.inflate(R.layout.fragment_firebase_database, container, false)

        val execute: Button = view.findViewById(R.id.execute)
        execute.setOnClickListener {
            val database = Firebase.database
            val reference = database.reference.child("secret")
            reference.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    SnackUtil.simpleMessage(requireActivity(), dataSnapshot.value as String)
                }

                override fun onCancelled(databaseError: DatabaseError) {
                    SnackUtil.simpleMessage(requireActivity(), "Sorry, database error!")
                }
            })
        }

        return view
    }
}