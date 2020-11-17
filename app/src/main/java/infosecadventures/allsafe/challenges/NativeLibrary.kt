package infosecadventures.allsafe.challenges

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import infosecadventures.allsafe.R
import infosecadventures.allsafe.utils.SnackUtil

class NativeLibrary : Fragment() {

    companion object {
        init {
            System.loadLibrary("native_library")
        }
    }

    private external fun checkPassword(password: String?): Boolean

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_native_library, container, false)
        val password = view.findViewById<EditText>(R.id.password)
        view.findViewById<View>(R.id.check).setOnClickListener {
            if (password.text.toString().isEmpty() || !checkPassword(password.text.toString())) {
                SnackUtil.simpleMessage(requireActivity(), "Wrong password, try harder!")
            } else {
                SnackUtil.simpleMessage(requireActivity(), "That's it! Excellent work!")
            }
        }
        return view
    }
}