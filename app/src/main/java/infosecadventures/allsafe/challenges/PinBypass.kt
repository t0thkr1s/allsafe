package infosecadventures.allsafe.challenges

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import infosecadventures.allsafe.R
import infosecadventures.allsafe.utils.SnackUtil

class PinBypass : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_pin_bypass, container, false)
        val pin = view.findViewById<EditText>(R.id.pin)
        view.findViewById<View>(R.id.check).setOnClickListener {
            if (pin.text.toString().isEmpty() || !checkPin(pin.text.toString())) {
                SnackUtil.simpleMessage(requireActivity(), "Incorrect PIN, try harder!")
            } else {
                SnackUtil.simpleMessage(requireActivity(), "Access granted, good job!")
            }
        }
        return view
    }

    private fun checkPin(pin: String): Boolean {
        return pin == String(android.util.Base64.decode("NDg2Mw==", android.util.Base64.DEFAULT))
    }
}