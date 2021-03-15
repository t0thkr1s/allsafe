package infosecadventures.allsafe.challenges

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import infosecadventures.allsafe.R
import infosecadventures.allsafe.utils.SnackUtil
import java.io.File


class InsecureProviders : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val storage = Firebase.storage
        val docs = File(requireContext().filesDir.absolutePath, "/docs")
        if (!docs.exists()) {
            docs.mkdir()
        }
        val file = File(docs, "readme.txt")
        val readme = storage.getReferenceFromUrl("gs://allsafe-8cef0.appspot.com/readme.txt")

        readme.getFile(file).addOnSuccessListener {
            SnackUtil.simpleMessage(requireActivity(), "File successfully downloaded!")
        }.addOnFailureListener {
            SnackUtil.simpleMessage(requireActivity(), "Sorry, something went wrong!!")
        }

        return inflater.inflate(R.layout.fragment_insecure_providers, container, false)
    }
}